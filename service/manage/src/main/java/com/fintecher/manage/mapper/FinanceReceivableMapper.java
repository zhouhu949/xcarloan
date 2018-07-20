package com.fintecher.manage.mapper;

import com.fintecher.entity.FinanceReceivable;
import com.fintecher.manage.util.MyMapper;
import com.fintecher.manage.vo.FinancialInvoiceModel;
import com.fintecher.manage.vo.FinancialInvoiceParams;

import java.util.List;
import java.util.Map;

public interface FinanceReceivableMapper extends MyMapper<FinanceReceivable> {

    List<FinancialInvoiceModel> selectFinancialInvoice(Map map);
}
