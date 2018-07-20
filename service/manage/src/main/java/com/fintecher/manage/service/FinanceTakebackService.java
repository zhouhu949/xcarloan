package com.fintecher.manage.service;

import com.fintecher.entity.FinanceTakeback;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.vo.EarlyRecycleParams;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/25 15:37
 * @Description:
 */
public interface FinanceTakebackService extends BaseService<FinanceTakeback> {
   void earlyRecovery(EarlyRecycleParams earlyRecycleParams, SysUser user);
}
