package com.fintecher.manage.service.impl;

import com.fintecher.entity.FinanceLoanDetial;
import com.fintecher.manage.service.FinanceLoanDetialService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class FinanceLoanDetialServiceImpl  extends BaseServiceImpl<FinanceLoanDetial>implements FinanceLoanDetialService {

}
