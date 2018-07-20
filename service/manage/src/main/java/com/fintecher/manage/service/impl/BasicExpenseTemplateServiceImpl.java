package com.fintecher.manage.service.impl;

import com.fintecher.entity.BasicExpenseTemplate;
import com.fintecher.manage.service.BasicExpenseTemplateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service
public class BasicExpenseTemplateServiceImpl extends BaseServiceImpl<BasicExpenseTemplate> implements BasicExpenseTemplateService {

}
