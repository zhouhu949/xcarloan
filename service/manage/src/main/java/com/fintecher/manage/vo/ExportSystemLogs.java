package com.fintecher.manage.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @System:
 * @Auther: DangWenXiang
 * @Description:
 * @Date:Created on 2018/2/7 10:45
 * @Modified By:
 */
@Data
public class ExportSystemLogs {
    private String clientIp;
    private String operator;
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private String operateTime;
    private String logRemark;
    private String exeTime;//执行时间
    private String exeMethod;
    private String exeType;
}
