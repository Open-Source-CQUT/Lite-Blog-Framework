package com.lite.cos.utils;

import com.lite.common.utils.DateUtils;
import com.lite.cos.config.CosConfig;
import com.lite.cos.entity.File;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.Headers;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.GeneratePresignedUrlRequest;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.ResponseHeaderOverrides;
import com.qcloud.cos.model.UploadResult;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.TransferManager;
import com.qcloud.cos.transfer.TransferManagerConfiguration;
import com.qcloud.cos.transfer.Upload;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CosUtils {

    //预签名URL过期时间默认30分钟
    public static final Long DEFAULT_EXPIRED = DateUtils.MINUTES * 30;

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

    /**
     * 上传一个文件
     *
     * @param transferManager 上传文件的对象
     * @param bucket          目标桶
     * @param key             对象的键值
     * @param file            要上传的文件对象
     * @return UploadResult 上传结果对象
     */
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
        } finally {
            //关闭连接
            shutDownTransferManager(transferManager);
        }

        return uploadResult;
    }

    public static URL generatePreDownloadUrl(COSClient cosClient, File fileInfo) {

        URL preSignedURL = null;

        try {
            //获取 GeneratePresignedUrlRequest 对象
            GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(
                    fileInfo.getBucket(), fileInfo.getFileName(), HttpMethodName.GET);

            //设置响应头重载对象
            ResponseHeaderOverrides headerOverrides = new ResponseHeaderOverrides();
            //设置下载时显示的文件名
            String responseContentDispositon = String.format("filename=%s", fileInfo.getOriginalName());
            //设置语言
            String responseContentLanguage = Locale.getDefault().toString();

            headerOverrides.setContentDisposition(responseContentDispositon);
            headerOverrides.setContentLanguage(responseContentLanguage);

            //设置响应头headers
            urlRequest.setResponseHeaders(headerOverrides);
            //设置过期时间 默认30分钟
            urlRequest.setExpiration(getExpiredDate());

            //设置请求头
            urlRequest.putCustomRequestHeader(Headers.HOST,
                    cosClient.getClientConfig().getEndpointBuilder().buildGeneralApiEndpoint(fileInfo.getBucket()));

            //生成url
            preSignedURL = cosClient.generatePresignedUrl(urlRequest);

        } finally {
            shutDownCosClient(cosClient);
        }

        return preSignedURL;
    }

    public static Date getExpiredDate() {
        return new Date(System.currentTimeMillis() + DEFAULT_EXPIRED);
    }

    public static Date getExpiredDate(Long interval) {
        return new Date(System.currentTimeMillis() + interval);
    }


}
