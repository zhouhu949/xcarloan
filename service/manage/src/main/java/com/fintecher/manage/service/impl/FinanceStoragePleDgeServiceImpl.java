package com.fintecher.manage.service.impl;

import com.fintecher.entity.FinanceStoragePleDge;
import com.fintecher.manage.service.BaseService;
import com.fintecher.manage.service.FinanceStoragePleDgeService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author ZhangYaJun
 * @Title: FinanceStoragePleDgeServiceImpl
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/23 0023下午 14:33
 */
@Transactional(rollbackOn = Exception.class)
@Service
public class FinanceStoragePleDgeServiceImpl extends BaseServiceImpl<FinanceStoragePleDge> implements FinanceStoragePleDgeService {
}
