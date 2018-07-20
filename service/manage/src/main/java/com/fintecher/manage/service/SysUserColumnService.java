package com.fintecher.manage.service;

import com.fintecher.entity.SysUserColumn;

import java.util.List;

public interface SysUserColumnService extends BaseService<SysUserColumn> {

    /**
     * 批量更新用户列配置
     * @param sysUserColumnList
     */
    void updateBatchUserColumn(List<SysUserColumn> sysUserColumnList);
}
