package com.fintecher.manage.service.impl;

import com.fintecher.entity.SysColumnResource;
import com.fintecher.manage.service.SysColumnResourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service
public class SysColumnResourceServiceImpl extends BaseServiceImpl<SysColumnResource> implements SysColumnResourceService{
}
