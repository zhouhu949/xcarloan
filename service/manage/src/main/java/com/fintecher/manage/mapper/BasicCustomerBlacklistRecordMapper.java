package com.fintecher.manage.mapper;

import com.fintecher.entity.BasicCustomerBlacklistRecord;
import com.fintecher.manage.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/22 20:12
 * @Description:
 */
public interface BasicCustomerBlacklistRecordMapper extends MyMapper<BasicCustomerBlacklistRecord> {
    List queryCustomerBlackListRecord(@Param("id")Long id);
}
