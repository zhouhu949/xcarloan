package com.fintecher.manage.service.impl;

import com.fintecher.entity.FinanceRefundDetial;
import com.fintecher.manage.service.FinanceRefundDetialService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class FinanceRefundDetialServiceImpl extends BaseServiceImpl<FinanceRefundDetial> implements FinanceRefundDetialService {

}
