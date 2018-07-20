package com.fintecher.manage.service;
import com.fintecher.entity.FinanceLoan;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.vo.SupplierLoanRecordModel;
import java.util.List;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/25 10:55
 * @Description:
 */
public interface FinanceLoanService extends BaseService<FinanceLoan> {

    void addFinanceLoan(FinanceLoan financeLoan, SysUser user);

    void supplierFinanceLoan(FinanceLoan financeLoan,Long carId);

    List<SupplierLoanRecordModel> querySupplierLoan(Long supplierId, Integer hasInvoice,SysUser user);
}
