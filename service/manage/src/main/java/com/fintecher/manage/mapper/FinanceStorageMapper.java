package com.fintecher.manage.mapper;

import com.fintecher.entity.FinanceStorage;
import com.fintecher.manage.util.MyMapper;
import com.fintecher.manage.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FinanceStorageMapper extends MyMapper<FinanceStorage> {

   List<FinanceStorageListParams> getFinanceStorageList(@Param("orderNo") String orderNo, @Param("customerName") String customerName, @Param("mortgageStatus") Integer mortgageStatus, @Param("allDataAuth") List<String> allDataAuth, @Param("allExceptDataAuth") List<String> allExceptDataAuth);

   QueryMortgageTypeParams queryMortgageType(@Param("id") Long id);

   UpdateFinanceOutStorageParams selectFinanceStorage(@Param("id") Long id);

   GetDetainDetails getDetainDetails(@Param("id") Long id);

   GetDetainPoleRecord getDetainPoleRecord(@Param("id") Long id);

   GetDetainMortRecord getDetainMortRecord(@Param("id") Long id);

   UpdateFinanceOutStorageParams selectFinanceMortStorage(@Param("id") Long id);

   FinanceStorage selectAssessmentStatus(@Param("id") Long id);

   List<FinanceStorage> selectStatusOnIN(@Param("orderId")Long orderId);
}
