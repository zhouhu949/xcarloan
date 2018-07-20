package com.fintecher.manage.service.impl;

import com.fintecher.entity.SysUser;
import com.fintecher.entity.SysUserDevice;
import com.fintecher.manage.mapper.SysUserDeviceMapper;
import com.fintecher.manage.service.SysUserDeviceService;
import com.fintecher.manage.vo.UserDeviceModel;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Transactional(rollbackFor = Exception.class)
@Service
public class SysUserDeviceServiceImpl extends BaseServiceImpl<SysUserDevice> implements SysUserDeviceService {

    @Resource
    private SysUserDeviceMapper sysUserDeviceMapper;

    @Override
    public void updateUserDevice(SysUser sysUser,SysUserDevice device, String mac) {
        if (mac == null) return;
        SysUserDevice sysUserDevice = new SysUserDevice();
        sysUserDevice.setId(device.getId());
        sysUserDevice.setOperatorTime(new Date());
        sysUserDevice.setOperator(sysUser.getId());
        sysUserDevice.setDeviceMac(mac);
        this.updateSelective(sysUserDevice);
    }

    @Override
    public void updateBatchUserDevice(SysUserDevice sysUserDevice, List<Long> ids) {
        Map<String, Object> paramsMap = Maps.newHashMap();
        paramsMap.put("sysUserDevice", sysUserDevice);
        paramsMap.put("ids", ids);
        sysUserDeviceMapper.updateBatchUserDevice(paramsMap);
    }

    @Override
    public List<UserDeviceModel> findUserDevice(List<Long> userIds) {
        return sysUserDeviceMapper.findUserDevice(userIds);
    }
}
