package com.fintecher.manage.service.impl;

import com.fintecher.entity.FinanceReceivableDetial;
import com.fintecher.manage.service.FinanceReceivableDetialService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional(rollbackOn = Exception.class)
@Service
public class FinanceReceivableDetialServiceImpl extends BaseServiceImpl<FinanceReceivableDetial> implements FinanceReceivableDetialService {

}
