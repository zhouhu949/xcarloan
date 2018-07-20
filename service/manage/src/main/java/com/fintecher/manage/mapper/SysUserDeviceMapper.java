package com.fintecher.manage.mapper;

import com.fintecher.entity.SysUserDevice;
import com.fintecher.manage.util.MyMapper;
import com.fintecher.manage.vo.UserDeviceModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysUserDeviceMapper extends MyMapper<SysUserDevice> {

    void updateBatchUserDevice(Map<String, Object> params);

    List<UserDeviceModel> findUserDevice(@Param("userIds") List<Long> userIds);

}
