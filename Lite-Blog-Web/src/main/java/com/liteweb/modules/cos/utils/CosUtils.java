package com.liteweb.modules.cos.utils;

import com.liteweb.config.CosConfig;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.UploadResult;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.TransferManager;
import com.qcloud.cos.transfer.TransferManagerConfiguration;
import com.qcloud.cos.transfer.Upload;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CosUtils {

    public static COSClient initCosClient(CosConfig cosConfig) {

        //创建cos凭证
        COSCredentials cosCredentials = new BasicCOSCredentials(
                cosConfig.getSecretId(),
                cosConfig.getSecretKey());

        //创建客户端配置类
        ClientConfig clientConfig = new ClientConfig();

        //设置地区归属
        clientConfig.setRegion(new Region(cosConfig.getRegion()));

        //设置https请求协议
        clientConfig.setHttpProtocol(HttpProtocol.https);

        //设置连接超时
        clientConfig.setConnectionTimeout(cosConfig.getConnectionTimeout());

        //设置socket连接超时
        clientConfig.setSocketTimeout(cosConfig.getSocketTimeout());

        //创建客户端
        return new COSClient(cosCredentials, clientConfig);
    }

    public static TransferManager initTransferManager(CosConfig cosConfig, COSClient cosClient) {

        //自定义线程池大小
        ExecutorService executorService = Executors.newFixedThreadPool(cosConfig.getThreadSizes());

        //创建manager
        TransferManager manager = new TransferManager(cosClient, executorService);

        //创建配置类
        TransferManagerConfiguration transferManagerConfiguration = new TransferManagerConfiguration();

        //设置分块上传的阈值
        transferManagerConfiguration.setMultipartUploadThreshold(cosConfig.getMultipartUploadThreshold());

        //设置分块的大小
        transferManagerConfiguration.setMinimumUploadPartSize(cosConfig.getMinimumUploadPartSize());

        //设置配置类
        manager.setConfiguration(transferManagerConfiguration);

        return manager;
    }

    public static TransferManager initTransferManager(CosConfig cosConfig) {
        return initTransferManager(cosConfig, initCosClient(cosConfig));
    }

    //关闭CosClient
    public static void shutDownCosClient(COSClient cosClient) {
        cosClient.shutdown();
    }

    //关闭TransferManager
    // 指定参数为 true, 则同时会关闭 transferManager 内部的 COSClient 实例。
    // 指定参数为 false, 则不会关闭 transferManager 内部的 COSClient 实例。
    public static void shutDownTransferManager(TransferManager transferManager) {
        transferManager.shutdownNow(true);
    }

    //上传一个文件
    public static UploadResult uploadFile(
            TransferManager transferManager,
            String bucket,
            String key,
            MultipartFile file) {

        //上传结果对象
        UploadResult uploadResult = null;

        //创建元数据信息
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());

        try {
            //异步上传
            Upload upload = transferManager.upload(bucket, key, file.getInputStream(), objectMetadata);

            //同步获取结果
            uploadResult = upload.waitForUploadResult();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return uploadResult;
    }

}
