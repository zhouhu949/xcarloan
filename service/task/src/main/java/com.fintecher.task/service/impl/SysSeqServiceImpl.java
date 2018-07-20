package com.fintecher.task.service.impl;

import com.fintecher.task.mapper.SysSeqMapper;
import com.fintecher.task.service.SysSeqService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional(rollbackFor = Exception.class)
@Service
public class SysSeqServiceImpl implements SysSeqService {

    @Resource
    private SysSeqMapper sysSeqMapper;

    @Override
    public void resetSeq(String seqName, int value) {
        sysSeqMapper.resetSeq(seqName, value);
    }
}
