package com.fintecher.manage.service;

import com.fintecher.common.vo.manage.ResponseResult;
import com.fintecher.entity.BasicCustomerIntention;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.vo.BasicCustomerAndIntentionParams;
import com.fintecher.manage.vo.BasicCustomerIntentionParams;
import com.fintecher.manage.vo.EditBasicCustomerIntentionParams;

/**
 * @author ZhangYaJun
 * @Title: BasicCustomerIntentionService
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/19 0019下午 16:58
 */
public interface BasicCustomerIntentionService extends BaseService<BasicCustomerIntention> {
   void saveIntentionCustomer(BasicCustomerIntentionParams basicCustomerIntentionParams, SysUser user);

   ResponseResult addBasicCustomerIntenTion(BasicCustomerAndIntentionParams basicCustomerAndIntentionParams, SysUser user);

   ResponseResult updateBasicCustomerIntention(EditBasicCustomerIntentionParams editBasicCustomerIntentionParams, SysUser user);
}
