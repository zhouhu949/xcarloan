package com.fintecher.manage.service.impl;

import com.fintecher.entity.FinanceStorage;
import com.fintecher.entity.FinanceStorageMortgage;
import com.fintecher.entity.FinanceStoragePleDge;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.mapper.FinanceStorageMapper;
import com.fintecher.manage.mapper.FinanceStorageMortgageMapper;
import com.fintecher.manage.mapper.FinanceStoragePleDgeMapper;
import com.fintecher.manage.service.FinanceStorageService;
import com.fintecher.manage.service.UserService;
import com.fintecher.manage.vo.*;
import com.fintecher.util.ZWDateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Transactional(rollbackOn = Exception.class)
@Service
public class FinanceStorageServiceImpl extends BaseServiceImpl<FinanceStorage> implements FinanceStorageService {

   @Autowired
   private FinanceStorageMapper financeStorageMapper;

   @Autowired
   private FinanceStoragePleDgeMapper financeStoragePleDgeMapper;

   @Autowired
   private FinanceStorageMortgageMapper financeStorageMortgageMapper;

   @Autowired
   private UserService userService;

   @Override
   public List<FinanceStorageListParams> getFinanceStorageList(SysUser user, String orderNo, String customerName, Integer mortgageStatus, List<String> allDataAuth, List<String> allExceptDataAuth) {
      return financeStorageMapper.getFinanceStorageList(orderNo, customerName, mortgageStatus, allDataAuth, allExceptDataAuth);
   }

   @Override
   public GetDetainDetails getDetainDetails(Long id) {
      return financeStorageMapper.getDetainDetails(id);
   }

   @Override
   public GetDetainPoleRecord getDetainPoleRecord(Long id) {
      return financeStorageMapper.getDetainPoleRecord(id);
   }

   @Override
   public GetDetainMortRecord getDetainMortRecord(Long id) {
      return financeStorageMapper.getDetainMortRecord(id);
   }

   @Override
   public FinanceStorage selectAssessmentStatus(Long id) {
      return financeStorageMapper.selectAssessmentStatus(id);
   }

   @Override
   public List<FinanceStorage> selectStatusOnIN(Long orderId) {

      return financeStorageMapper.selectStatusOnIN(orderId);
   }

   @Override
   public QueryMortgageTypeParams queryMortgageType(Long id) {
      return financeStorageMapper.queryMortgageType(id);
   }

   @Override
   public Integer updatePoleStatus(FinanceInStoragePledgeParams financeInStorageParams, SysUser user) {
      return this.copyProperties(financeInStorageParams, user);
   }

   @Override
   public Integer updateMortStatus(FinanceInStorageMortgageParams financeInStorageMortgageParams, SysUser user) {
      FinanceStorage financeStorage = new FinanceStorage();
      financeStorage.setMortgageStatus(FinanceStorage.MortgageStatus.IN.getValue());
      financeStorage.setOperator(user.getId());
      financeStorage.setOperatorTime(ZWDateUtil.getNowDateTime());
      financeStorage.setId(financeInStorageMortgageParams.getId());
      financeStorage.setStockInDate(financeInStorageMortgageParams.getStockDate());
      return financeStorageMapper.updateByPrimaryKeySelective(financeStorage);
   }

   @Override
   public void addMortgage(FinanceInStorageMortgageParams financeInStorageMortgageParams, QueryMortgageTypeParams queryMortgageTypeParams, SysUser user) {

      FinanceStorageMortgage financeStorageMortgage = new FinanceStorageMortgage();
      financeStorageMortgage.setMortgageNo(UUID.randomUUID().toString());
      financeStorageMortgage.setGpsNo(financeInStorageMortgageParams.getGpsNo());
      financeStorageMortgage.setGpsStatus(FinanceStorageMortgage.GpsStatus.YAZ.getValue());
      financeStorageMortgage.setGpsManufactor(financeInStorageMortgageParams.getGpsManufactor());
      BeanUtils.copyProperties(queryMortgageTypeParams, financeStorageMortgage);
      financeStorageMortgageMapper.insertSelective(financeStorageMortgage);
   }


   @Override
   public void addPledge(FinanceInStoragePledgeParams financeInStorageParams, QueryMortgageTypeParams queryMortgageTypeParams, SysUser user) {

      FinanceStoragePleDge financeStoragePleDge = new FinanceStoragePleDge();
      BeanUtils.copyProperties(queryMortgageTypeParams, financeStoragePleDge);
      financeStoragePleDge.setPledgeNo(UUID.randomUUID().toString());
      financeStoragePleDge.setPledgePlace(financeInStorageParams.getPledgePlace());
      financeStoragePleDge.setPledgePosition(financeInStorageParams.getPledgePosition());
      financeStoragePleDgeMapper.insertSelective(financeStoragePleDge);
   }

   //   抵押出库
   @Override
   public void updateOutMortStatus(FinanceOutStorageMortgageParams financeOutStorageMortgageParams, SysUser user) {
      this.updateStatus(financeOutStorageMortgageParams, user);
      UpdateFinanceOutStorageParams updateFinanceOutStorageParams = financeStorageMapper.selectFinanceMortStorage(financeOutStorageMortgageParams.getId());
      if (Objects.nonNull(updateFinanceOutStorageParams))
      {
         FinanceStorageMortgage financeStorageMortgage = new FinanceStorageMortgage();
         financeStorageMortgage.setId(updateFinanceOutStorageParams.getId());
         financeStorageMortgage.setGpsStatus(FinanceStorageMortgage.GpsStatus.WAZ.getValue());
         BeanUtils.copyProperties(updateFinanceOutStorageParams, financeStorageMortgage);
         financeStorageMortgageMapper.updateByPrimaryKeySelective(financeStorageMortgage);
      }
   }

   //  质押出库
   @Override
   public void updateOutPleStatus(FinanceOutStorageMortgageParams financeOutStorageMortgageParams, SysUser user) {
      this.updateStatus(financeOutStorageMortgageParams, user);
      UpdateFinanceOutStorageParams updateFinanceOutStorageParams = financeStorageMapper.selectFinanceStorage(financeOutStorageMortgageParams.getId());
      if (Objects.nonNull(updateFinanceOutStorageParams))
      {
         FinanceStoragePleDge financeStoragePleDge = new FinanceStoragePleDge();
         financeStoragePleDge.setId(updateFinanceOutStorageParams.getId());
         financeStoragePleDge.setStockOutDate(updateFinanceOutStorageParams.getStockOutDate());
         BeanUtils.copyProperties(updateFinanceOutStorageParams, financeStoragePleDge);
         financeStoragePleDgeMapper.updateByPrimaryKeySelective(financeStoragePleDge);
      }
   }


   private void updateStatus(FinanceOutStorageMortgageParams financeOutStorageMortgageParams, SysUser user) {
      FinanceStorage financeStorage = new FinanceStorage();
      financeStorage.setId(financeOutStorageMortgageParams.getId());
      financeStorage.setMortgageStatus(FinanceStorage.MortgageStatus.OUT.getValue());
      financeStorage.setStockOutDate(financeOutStorageMortgageParams.getStockOutDate());
      financeStorage.setOperatorTime(ZWDateUtil.getNowDateTime());
      financeStorage.setOperator(user.getId());
      financeStorageMapper.updateByPrimaryKeySelective(financeStorage);
   }


   private Integer copyProperties(FinanceInStoragePledgeParams financeInStorageParams, SysUser user) {
      FinanceStorage financeStorage = new FinanceStorage();
      financeStorage.setMortgageStatus(FinanceStorage.MortgageStatus.IN.getValue());
      financeStorage.setOperator(user.getId());
      financeStorage.setId(financeInStorageParams.getId());
      financeStorage.setOperatorTime(ZWDateUtil.getNowDateTime());
      financeStorage.setStockInDate(financeInStorageParams.getStockDate());
      return financeStorageMapper.updateByPrimaryKeySelective(financeStorage);
   }

}
