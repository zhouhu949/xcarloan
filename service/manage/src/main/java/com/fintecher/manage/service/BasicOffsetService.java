package com.fintecher.manage.service;

import com.fintecher.entity.BasicOffset;
import com.fintecher.entity.SysUser;

import java.util.List;

public interface BasicOffsetService extends BaseService<BasicOffset> {

    void  updateBasicOffset(BasicOffset basicOffset);

    int deletecheck(BasicOffset basicOffset);

    /**
     * 获取数据权限下的冲抵策略
     * @param allDataAuth
     * @param allExceptDataAuth
     * @return
     */
    List<BasicOffset> findBasicOffsetByAuth(List<String> allDataAuth, List<String> allExceptDataAuth);

    void addOffsetAndOffsetItem(BasicOffset basicOffset, SysUser user);
}
