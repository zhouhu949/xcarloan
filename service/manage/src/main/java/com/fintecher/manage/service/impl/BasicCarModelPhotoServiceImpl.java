package com.fintecher.manage.service.impl;

import com.fintecher.entity.BasicCarModelPhoto;
import com.fintecher.manage.service.BasicCarModelPhotoService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author ZhangYaJun
 * @Title: BasicCarModelPhotoServiceImpl
 * @ProjectName xcarloan
 * @Description: TODO
 * @date 2018/6/21 0021下午 21:10
 */

@Service
@Transactional(rollbackOn = Exception.class)
public class BasicCarModelPhotoServiceImpl extends BaseServiceImpl<BasicCarModelPhoto> implements BasicCarModelPhotoService {
}
