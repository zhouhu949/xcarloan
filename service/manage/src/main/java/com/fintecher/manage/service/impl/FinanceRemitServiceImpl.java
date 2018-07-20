package com.fintecher.manage.service.impl;

import com.fintecher.entity.FinanceRemit;
import com.fintecher.manage.service.BaseService;
import com.fintecher.manage.service.FinanceRemitService;
import com.fintecher.manage.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/26 16:10
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FinanceRemitServiceImpl extends BaseServiceImpl<FinanceRemit> implements FinanceRemitService {
}
