package com.fintecher.manage.service.impl;

import com.fintecher.entity.BasicCustomerBlacklistRecord;
import com.fintecher.manage.service.BasicCustomerBlacklistRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/22 20:16
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BasicCustomerBlackListRecordServiceImpl extends BaseServiceImpl<BasicCustomerBlacklistRecord> implements BasicCustomerBlacklistRecordService {
}
