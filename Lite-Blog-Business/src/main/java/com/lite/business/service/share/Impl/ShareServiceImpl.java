package com.lite.business.service.share.Impl;

import com.lite.business.entity.share.Share;
import com.lite.business.dao.share.ShareMapper;
import com.lite.business.service.share.IShareService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 动态信息表 服务实现类
 * </p>
 *
 * @author stranger
 * @since 2022-08-31
 */
@Service
public class ShareServiceImpl extends ServiceImpl<ShareMapper, Share> implements IShareService {

}
