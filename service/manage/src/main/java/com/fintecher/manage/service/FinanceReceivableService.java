package com.fintecher.manage.service;

import com.fintecher.entity.FinanceReceivable;
import com.fintecher.manage.vo.FinancialInvoiceModel;
import com.fintecher.manage.vo.FinancialInvoiceParams;

import java.util.List;

public interface FinanceReceivableService extends BaseService<FinanceReceivable> {

    List<FinancialInvoiceModel> selectFinancialInvoice(FinancialInvoiceParams financialInvoiceModel, List<String> dataAuth, List<String> exceptDataAuth);
}
