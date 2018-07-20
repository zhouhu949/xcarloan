package com.fintecher.manage.vo;

import lombok.Data;

import java.util.List;

/**
 * @System:
 * @Auther: DangWenXiang
 * @Description:
 * @Date:Created on 2018/2/7 10:46
 * @Modified By:
 */
@Data
public class ExportSystemLogsList {
    private List<Long> sysLogsIds;
    private List<ExportSystemLogs> exportSystemBackups;
} 