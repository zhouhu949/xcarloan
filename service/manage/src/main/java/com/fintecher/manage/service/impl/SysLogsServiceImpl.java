package com.fintecher.manage.service.impl;

import com.fintecher.entity.SysLogs;
import com.fintecher.manage.mapper.SysLogsMapper;
import com.fintecher.manage.service.SysLogsService;
import com.fintecher.manage.vo.SysLogsModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service
public class SysLogsServiceImpl extends BaseServiceImpl<SysLogs> implements SysLogsService {
    @Resource
    private SysLogsMapper SysLogsMapper;

    @Override
    public List<SysLogsModel> findSysLogsByConditions(String clientIp,String excuteMethod,String operator, String excuteTime, String excuteParams, String excuteType,Date startTime, Date endTime,String sort) {
        List<SysLogsModel> SysLogsModelList = SysLogsMapper.findSysLogsByConditions(clientIp,excuteMethod,operator, excuteTime, excuteParams, excuteType,startTime,endTime,sort);
        return SysLogsModelList;
    }
}
