package com.fintecher.manage.service;

import com.fintecher.entity.BasicCustomer;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.vo.BasicCustomerBankModel;
import com.fintecher.manage.vo.BasicCustomerParams;
import com.fintecher.manage.vo.BasicCustomerSearch;
import com.fintecher.manage.vo.EditBasicCustomerParams;

import java.util.List;

/**
 * @author ZhangYaJun
 * @Title: BasicCustomerService
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/19 0019下午 14:50
 */
public interface BasicCustomerService extends BaseService<BasicCustomer> {


   List<BasicCustomer> findAllCustomerList(String customerName, Long orgId);
   List findCustomerList(BasicCustomerSearch basicCustomerSearch, Long orgId,Integer customerStatus);
   List findNotBlackCustomerList(BasicCustomerSearch basicCustomerSearch, Long orgId);


   void saveCustomerAccount(BasicCustomerBankModel basicCustomerBankModel, SysUser sysUser);


   void addBasicCustomer(BasicCustomerParams basicCustomerParams, SysUser user);


   void editBasicCustomer(EditBasicCustomerParams editBasicCustomerParams, SysUser user);

   List<BasicCustomer> findCustomerSignList(String customerName);

   List findCustomerIntentionList(BasicCustomerSearch basicCustomerSearch,Long orgId,Integer customerStatus);

   BasicCustomer findCustomerInfoByOrderId(Long orderId);


}
