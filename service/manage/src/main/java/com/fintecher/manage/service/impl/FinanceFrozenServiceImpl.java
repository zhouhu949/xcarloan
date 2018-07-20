package com.fintecher.manage.service.impl;

import com.fintecher.entity.FinanceFrozen;
import com.fintecher.manage.service.FinanceFrozenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/26 15:41
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FinanceFrozenServiceImpl extends BaseServiceImpl<FinanceFrozen> implements FinanceFrozenService {
}
