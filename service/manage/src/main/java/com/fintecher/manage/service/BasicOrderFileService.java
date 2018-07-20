package com.fintecher.manage.service;

import com.fintecher.entity.BasicOrderFile;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.vo.BasicOrderFileModel;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/14 11:18
 * @Description:
 */
public interface BasicOrderFileService extends BaseService<BasicOrderFile> {

    void uploadBasicOrderFile(BasicOrderFileModel basicOrderFileModel, SysUser sysUser);
    void updateBasicOrderFile(BasicOrderFileModel basicOrderFileModel,SysUser sysUser);

    BasicOrderFile findUploadBasicFile(Long fileId);

}
