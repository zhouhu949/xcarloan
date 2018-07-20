package com.fintecher.manage.service.impl;

import com.fintecher.entity.BasicOrderRecord;
import com.fintecher.manage.mapper.BasicOrderRecordMapper;
import com.fintecher.manage.mapper.BasicOrderRepaySchemeMapper;
import com.fintecher.manage.service.BasicOrderRecordService;
import com.fintecher.manage.vo.PrepaymentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/14 15:12
 * @Description:
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class BasicOrderRecordServiceImpl extends BaseServiceImpl<BasicOrderRecord> implements BasicOrderRecordService {
    @Autowired
    BasicOrderRepaySchemeMapper basicOrderRepaySchemeMapper;

}
