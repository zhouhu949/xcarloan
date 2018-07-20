package com.fintecher.task.mapper;

import com.fintecher.entity.SysSeq;
import com.fintecher.task.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface SysSeqMapper extends MyMapper<SysSeq> {

    /**
     * 根据序列名称重置当前值
     *
     * @param seqName 序列名称
     * @param value   初始值
     */
    @Update("UPDATE sys_seq SET current_seq = #{value} WHERE seq_name = #{seqName}")
    void resetSeq(@Param("seqName") String seqName, @Param("value") int value);
}
