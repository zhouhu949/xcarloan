package com.fintecher.manage.mapper;

import com.fintecher.entity.SysLogs;
import com.fintecher.manage.util.MyMapper;
import com.fintecher.manage.vo.SysLogsModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SysLogsMapper extends MyMapper<SysLogs> {
    List<SysLogsModel> findSysLogsByConditions(@Param("clientIp")String clientIp,
                                               @Param("excuteMethod") String excuteMethod,
                                               @Param("operator") String operator,
                                               @Param("excuteTime") String excuteTime,
                                               @Param("excuteParams") String excuteParams,
                                               @Param("excuteType") String excuteType,
                                               @Param("startTime") Date startTime,
                                               @Param("endTime") Date endTime,
                                               @Param("sort") String sort);
}
