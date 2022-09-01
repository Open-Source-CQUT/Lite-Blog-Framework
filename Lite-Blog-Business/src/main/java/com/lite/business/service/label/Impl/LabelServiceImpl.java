package com.lite.business.service.label.Impl;

import com.lite.business.entity.label.Label;
import com.lite.business.dao.label.LabelMapper;
import com.lite.business.service.label.ILabelService;
import com.lite.business.convert.label.LabelConvert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 标签信息表 服务实现类
 * </p>
 *
 * @author stranger
 * @since 2022-09-01
 */
@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements ILabelService {


        @Autowired
        LabelMapper labelMapper;

        @Autowired
        LabelConvert labelConvert;
}
