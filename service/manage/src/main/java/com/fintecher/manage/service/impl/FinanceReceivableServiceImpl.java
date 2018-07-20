package com.fintecher.manage.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintecher.entity.FinanceReceivable;
import com.fintecher.manage.mapper.FinanceReceivableMapper;
import com.fintecher.manage.service.FinanceReceivableService;
import com.fintecher.manage.vo.FinancialInvoiceModel;
import com.fintecher.manage.vo.FinancialInvoiceParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Transactional(rollbackOn = Exception.class)
@Service
public class FinanceReceivableServiceImpl extends BaseServiceImpl<FinanceReceivable> implements FinanceReceivableService {

    @Autowired
    FinanceReceivableMapper financeReceivableMapper;

    @Override
    public List<FinancialInvoiceModel> selectFinancialInvoice(FinancialInvoiceParams financialInvoiceModel, List<String> dataAuth, List<String> exceptDataAuth) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = objectMapper.convertValue(financialInvoiceModel, Map.class);
        map.put("dataAuth", dataAuth);
        map.put("exceptDataAuth", exceptDataAuth);
        return financeReceivableMapper.selectFinancialInvoice(map);
    }
}
