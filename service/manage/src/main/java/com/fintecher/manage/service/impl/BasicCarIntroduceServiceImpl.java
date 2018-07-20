package com.fintecher.manage.service.impl;

import com.fintecher.entity.BasicCarIntroduce;
import com.fintecher.manage.service.BasicCarIntroduceService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author ZhangYaJun
 * @Title: BasicCarIntroduceServiceImpl
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/20 0020下午 15:57
 */

@Service
@Transactional(rollbackOn = Exception.class)
public class BasicCarIntroduceServiceImpl extends BaseServiceImpl<BasicCarIntroduce> implements BasicCarIntroduceService {
}
