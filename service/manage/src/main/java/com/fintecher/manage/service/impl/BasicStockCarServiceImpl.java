package com.fintecher.manage.service.impl;

import com.fintecher.common.vo.manage.ResponseResult;
import com.fintecher.entity.BasicCustomerOrder;
import com.fintecher.entity.BasicOrderRepayScheme;
import com.fintecher.entity.BasicStockCar;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.mapper.BasicCustomerOrderMapper;
import com.fintecher.manage.mapper.BasicOrderRepaySchemeMapper;
import com.fintecher.manage.mapper.BasicStockCarMapper;
import com.fintecher.manage.service.BasicStockCarService;
import com.fintecher.manage.vo.BasicStockCarListParams;
import com.fintecher.manage.vo.BasicStockCarParams;
import com.fintecher.manage.vo.BasicStockCarSearch;
import com.fintecher.manage.vo.EditBasicStockCarParams;
import com.fintecher.util.ZWDateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * @author ZhangYaJun
 * @Title: BasicStockCarServiceImpl
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/14 0014下午 17:15
 */

@Transactional(rollbackFor = Exception.class)
@Service

public class BasicStockCarServiceImpl extends BaseServiceImpl<BasicStockCar> implements BasicStockCarService {


   @Autowired
   private BasicStockCarMapper basicStockCarMapper;

   @Autowired
   private BasicCustomerOrderMapper basicCustomerOrderMapper;

   @Autowired
   private BasicOrderRepaySchemeMapper basicOrderRepaySchemeMapper;

   @Override
   public void addBasicStock(BasicStockCarParams basicStockCarParams, SysUser user) {


      BasicStockCar basicStockCar = new BasicStockCar();
      basicStockCar.setOrgId(user.getOrgId());
      basicStockCar.setOperator(user.getId());
      basicStockCar.setOperatorTime(ZWDateUtil.getNowDateTime());
      basicStockCar.setStockInDate(ZWDateUtil.getNowDateTime());
      basicStockCar.setHasSupplierLoan(BasicStockCar.HasSupplierLoan.BASIC_STOCKSTATUS_NO.getValue());
      basicStockCar.setStockStatus(BasicStockCar.StockStatus.BASIC_STOCKSTATUS_ZBZ.getValue());
      BeanUtils.copyProperties(basicStockCarParams, basicStockCar);
      String remark = basicStockCarParams.getRemark();
      if (Objects.nonNull(remark))
      {
         basicStockCar.setRemark(remark);
      }
      this.saveSelective(basicStockCar);
   }

   @Override
   public List<BasicStockCarListParams> findAllStockCarList(BasicStockCarSearch basicStockCarSearch, Long orgId) {

      List<BasicStockCarListParams> allStockCarList = basicStockCarMapper.findAllStockCarList(basicStockCarSearch, orgId);
      return allStockCarList;
   }
   @Override
   public ResponseResult editCarStockStatus(Long id, SysUser user, Long orderId) {
      // 验证状态 防止修改回滚
      BasicStockCar byId = this.findById(id);
      if (byId.getStockStatus().equals(BasicStockCar.StockStatus.BASIC_STOCKSTATUS_DCG.getValue()))
      {
         byId.setStockStatus(BasicStockCar.StockStatus.BASIC_STOCKSTATUS_ZBZ.getValue());
      } else if (byId.getStockStatus().equals(BasicStockCar.StockStatus.BASIC_STOCKSTATUS_ZBZ.getValue()))
      {
         byId.setStockStatus(BasicStockCar.StockStatus.BASIC_STOCKSTATUS_ZBWC.getValue());
      } else if (byId.getStockStatus().equals(BasicStockCar.StockStatus.BASIC_STOCKSTATUS_ZBWC.getValue()))
      {
         //  审核状态是待提车才可以提车
         BasicCustomerOrder basicCustomerOrder = basicCustomerOrderMapper.selectByPrimaryKey(orderId);
         if (BasicCustomerOrder.OrderStatus.NOT_MENTIONED_CAR.getValue().equals(basicCustomerOrder.getOrderStatus()))
         {
            byId.setStockStatus(BasicStockCar.StockStatus.BASIC_STOCKSTATUS_YTC.getValue());
            Date date = new Date();
            byId.setStockOutDate(date);
            // 已提车的同时 修改订单状态为待还款
            BasicCustomerOrder basicCustomerOrder1 = new BasicCustomerOrder();
            basicCustomerOrder1.setOrderStatus(BasicCustomerOrder.OrderStatus.REPAYMENT.getValue());
            basicCustomerOrder1.setId(orderId);
            basicCustomerOrder1.setOperator(user.getId());
            basicCustomerOrder1.setOperatorTime(ZWDateUtil.getNowDateTime());
            basicCustomerOrderMapper.updateByPrimaryKeySelective(basicCustomerOrder1);
            //  已提车的同时  生成下个月还款时间
            Integer orderPeriods = basicCustomerOrder.getOrderPeriods();
            Map map = new HashMap();
            //按期付息还本直接还最后一个月
            if (basicCustomerOrder.getOrderRepayType().equals(BasicCustomerOrder.OrderRepayType.PAYMENT_SCHEDULE.getValue()))
            {
               date = ZWDateUtil.getNextMonthDay(date, basicCustomerOrder.getOrderAccountDay(), orderPeriods);

               map.put("orderId", orderId);
               map.put("repaymentDate", date);
               map.put("periods", 1);
               basicOrderRepaySchemeMapper.updateRepaymentDate(map);
            } else
            {
               //  除按期付息还本以外的情况  每期都要还
               for (int i = 1; i <= orderPeriods; i++)
               {
                  date = ZWDateUtil.getNextMonth(date, basicCustomerOrder.getOrderAccountDay());
                  map.put("orderId", orderId);
                  map.put("repaymentDate", date);
                  map.put("periods", i);
                  basicOrderRepaySchemeMapper.updateRepaymentDate(map);
               }
            }
         } else if (BasicCustomerOrder.OrderStatus.PENDING_PAYMENT.getValue().equals(basicCustomerOrder.getOrderStatus()))
         {
            return new ResponseResult(ResponseResult.Status.FAILURE, "首付款未收取!");
         } else
         {
            return new ResponseResult(ResponseResult.Status.FAILURE, "未通过审核!");
         }
      } else if (byId.getStockStatus().equals(BasicStockCar.StockStatus.BASIC_STOCKSTATUS_YTC.getValue()))
      {
         return new ResponseResult(ResponseResult.Status.SUCCESS, "已提车");
      }
      byId.setOperator(user.getId());
      byId.setOperatorTime(ZWDateUtil.getNowDateTime());
      basicStockCarMapper.updateByPrimaryKeySelective(byId);
      return new ResponseResult(ResponseResult.Status.SUCCESS, "修改成功");
   }

   @Override
   public List<BasicStockCar> selectBySupplier(BasicStockCar basicStockCar) {
      return basicStockCarMapper.selectBySupplier(basicStockCar);
   }

   @Override
   public ResponseResult updateeditCarStockStatus(EditBasicStockCarParams editBasicStockCarParams, SysUser user) {
      Integer stockStatus = editBasicStockCarParams.getStockStatus();
      if (stockStatus.equals(BasicStockCar.StockStatus.BASIC_STOCKSTATUS_ZBZ.getValue()) ||
              stockStatus.equals(BasicStockCar.StockStatus.BASIC_STOCKSTATUS_ZBWC.getValue()) ||
              stockStatus.equals(BasicStockCar.StockStatus.BASIC_STOCKSTATUS_YTC.getValue()) ||
              stockStatus.equals(BasicStockCar.StockStatus.BASIC_STOCKSTATUS_DCG.getValue()))
      {
         BasicStockCar basicStockCar = new BasicStockCar();
         basicStockCar.setOperatorTime(ZWDateUtil.getNowDateTime());
         basicStockCar.setId(editBasicStockCarParams.getId());
         basicStockCar.setOperator(user.getId());
         if (stockStatus.equals(BasicStockCar.StockStatus.BASIC_STOCKSTATUS_YTC.getValue()))
         {
            basicStockCar.setStockOutDate(ZWDateUtil.getNowDateTime());
         }
         BeanUtils.copyProperties(editBasicStockCarParams, basicStockCar);
         this.updateSelective(basicStockCar);
         return new ResponseResult(ResponseResult.Status.SUCCESS, "修改成功");
      } else
      {
         return new ResponseResult(ResponseResult.Status.FAILURE, "修改状态不存在");
      }

   }
}
