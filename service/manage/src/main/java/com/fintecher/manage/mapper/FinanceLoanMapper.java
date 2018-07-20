package com.fintecher.manage.mapper;

import com.fintecher.entity.FinanceLoan;
import com.fintecher.manage.util.MyMapper;
import com.fintecher.manage.vo.FinanceLoanModel;
import com.fintecher.manage.vo.FinanceLoanParams;
import com.fintecher.manage.vo.SupplierLoanRecordModel;

import java.util.List;
import java.util.Map;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/25 10:55
 * @Description:
 */
public interface FinanceLoanMapper extends MyMapper<FinanceLoan> {

   List<SupplierLoanRecordModel> selectSupplierLoan(Map map);

}
