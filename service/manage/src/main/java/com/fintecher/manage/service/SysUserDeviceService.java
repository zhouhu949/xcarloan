package com.fintecher.manage.service;


import com.fintecher.entity.SysUser;
import com.fintecher.entity.SysUserDevice;
import com.fintecher.manage.vo.UserDeviceModel;

import java.util.List;

public interface SysUserDeviceService extends BaseService<SysUserDevice> {

    void updateUserDevice(SysUser sysUser, SysUserDevice device, String mac);

    void updateBatchUserDevice(SysUserDevice sysUserDevice, List<Long> ids);

    /**
     * 获取用户设备
     * @param userIds
     * @return
     */
    List<UserDeviceModel> findUserDevice(List<Long> userIds);
}
