package com.fintecher.manage.service;

import com.fintecher.entity.BasicCustomerFollow;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.vo.BasicCustomerFollowParams;

/**
 * @author ZhangYaJun
 * @Title: BasicCustomerFollowService
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/19 0019下午 18:37
 */
public interface BasicCustomerFollowService extends BaseService<BasicCustomerFollow> {
   void saveBasicCustomerFollow(BasicCustomerFollowParams basicCustomerFollowParams, SysUser user);
}
