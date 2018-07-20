package com.fintecher.manage.service.impl;

import com.fintecher.entity.SysUserColumn;
import com.fintecher.manage.mapper.SysUserColumnMapper;
import com.fintecher.manage.service.SysUserColumnService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service
public class SysUserColumnServiceImpl extends BaseServiceImpl<SysUserColumn> implements SysUserColumnService {

    @Resource
    private SysUserColumnMapper sysUserColumnMapper;

    @Override
    public void updateBatchUserColumn(List<SysUserColumn> sysUserColumnList) {
        sysUserColumnMapper.updateBatchUserColumn(sysUserColumnList);
    }
}
