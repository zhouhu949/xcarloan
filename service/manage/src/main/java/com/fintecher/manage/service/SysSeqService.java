package com.fintecher.manage.service;

public interface SysSeqService {

    /**
     * 获取当前序列号
     * @param seqName 序列名称
     * @param length 序列长度
     * @return 前面补0的序列值
     */
    String nextSeq(String seqName, int length);
}
