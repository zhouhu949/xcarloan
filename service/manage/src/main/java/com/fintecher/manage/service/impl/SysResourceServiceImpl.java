package com.fintecher.manage.service.impl;

import com.fintecher.entity.SysResource;
import com.fintecher.manage.service.SysResourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jwdstef on 2017/2/8.
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class SysResourceServiceImpl extends BaseServiceImpl<SysResource> implements SysResourceService {

}
