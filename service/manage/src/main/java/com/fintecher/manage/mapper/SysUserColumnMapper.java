package com.fintecher.manage.mapper;

import com.fintecher.entity.SysUserColumn;
import com.fintecher.manage.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserColumnMapper extends MyMapper<SysUserColumn> {

    /**
     * 批量更新列配置
     * @param sysUserColumnList
     */
    void updateBatchUserColumn(@Param("params") List<SysUserColumn> sysUserColumnList);
}
