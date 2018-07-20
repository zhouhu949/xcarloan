package com.fintecher.task.service;

public interface SysSeqService {

    /**
     * 根据序列名称重置序列值
     *
     * @param seqName 序列名称
     * @param value   初始值
     */
    void resetSeq(String seqName, int value);
}
