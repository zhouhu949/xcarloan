package com.fintecher.manage.service.impl;

import com.fintecher.entity.FinanceStorageMortgage;
import com.fintecher.manage.service.FinanceStorageMortgageService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author ZhangYaJun
 * @Title: FinanceStorageMortgageServiceImpl
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/23 0023下午 14:38
 */

@Transactional(rollbackOn = Exception.class)
@Service
public class FinanceStorageMortgageServiceImpl extends BaseServiceImpl<FinanceStorageMortgage> implements FinanceStorageMortgageService {


}
