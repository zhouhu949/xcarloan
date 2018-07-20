package com.fintecher.manage.service;

import com.fintecher.entity.FinanceStorage;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.vo.*;

import java.util.List;

public interface FinanceStorageService extends BaseService<FinanceStorage> {




   QueryMortgageTypeParams queryMortgageType(Long id);

   void addPledge(FinanceInStoragePledgeParams financeInStorageParams, QueryMortgageTypeParams queryMortgageTypeParams, SysUser user);

   Integer updatePoleStatus(FinanceInStoragePledgeParams financeInStorageParams, SysUser user);

   Integer updateMortStatus(FinanceInStorageMortgageParams financeInStorageMortgageParams, SysUser user);

   void addMortgage(FinanceInStorageMortgageParams financeInStorageMortgageParams, QueryMortgageTypeParams queryMortgageTypeParams, SysUser user);

   void updateOutMortStatus(FinanceOutStorageMortgageParams financeOutStorageMortgageParams, SysUser user);

   void updateOutPleStatus(FinanceOutStorageMortgageParams financeOutStorageMortgageParams, SysUser user);

   List<FinanceStorageListParams> getFinanceStorageList(SysUser user, String orderNo, String customerName, Integer mortgageStatus, List<String> allDataAuth, List<String> allExceptDataAuth);

   GetDetainDetails getDetainDetails(Long id);

   GetDetainPoleRecord getDetainPoleRecord(Long id);

   GetDetainMortRecord getDetainMortRecord(Long id);


   FinanceStorage selectAssessmentStatus(Long id);

   List<FinanceStorage> selectStatusOnIN(Long orderId);
}
