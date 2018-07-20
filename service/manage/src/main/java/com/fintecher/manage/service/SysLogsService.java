package com.fintecher.manage.service;


import com.fintecher.entity.SysLogs;
import com.fintecher.manage.vo.SysLogsModel;

import java.util.Date;
import java.util.List;

public interface SysLogsService extends BaseService<SysLogs> {
    List<SysLogsModel> findSysLogsByConditions( String clientIp,String excuteMethod,String operator, String excuteTime, String excuteParams, String excuteType,Date startTime, Date endTime,String sort);
}
