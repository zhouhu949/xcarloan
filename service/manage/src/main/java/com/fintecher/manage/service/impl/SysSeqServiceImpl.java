package com.fintecher.manage.service.impl;

import com.fintecher.manage.mapper.SysSeqMapper;
import com.fintecher.manage.service.SysSeqService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional(rollbackFor = Exception.class)
@Service
public class SysSeqServiceImpl implements SysSeqService {

    @Resource
    private SysSeqMapper sysSeqMapper;

    @Override
    public String nextSeq(String seqName, int length) {
        return sysSeqMapper.getNext(seqName, length);
    }
}
