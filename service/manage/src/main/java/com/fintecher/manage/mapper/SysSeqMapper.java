package com.fintecher.manage.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SysSeqMapper {

    /**
     * 获取当前值
     * @param seqName 序列号名称
     * @param length 序列号需要长度
     * @return 序列号 长度不够前面补0
     */
    @Select("SELECT next_seq(#{seqName}, #{length})")
    String getNext(@Param("seqName") String seqName,@Param("length") int length);
}
