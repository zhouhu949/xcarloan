package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @System: 车贷金融
 * @Auther: hukaijia
 * @Description: 操作日志实体类
 * @Date:Created on 2017/12/25/025 13:13
 * @Modified By:
 */
@Data
public class SysLogsModel {
    @ApiModelProperty(value = "id", notes = "id")
    private Long id;
    /**
     * 用户ip
     */
    @ApiModelProperty(value = "用户ip", notes = "用户ip")
    private String clientIp;
    /**
     * 操作时间
     */
    @ApiModelProperty(value = "操作时间", notes = "操作时间")
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private Date operateTime;
    /**
     * 操作人
     */
    @ApiModelProperty(value = "操作人", notes = "操作人")
    private String operator;

    /**
     * 执行时间
     */
    @ApiModelProperty(value = "执行时间 ", notes = "执行时间 ")
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private String excuteTime;
    /**
     * 执行方法
     */
    @ApiModelProperty(value = "执行方法 ", notes = "执行方法")
    private String excuteMethod;
    /**
     * 执行参数
     */
    @ApiModelProperty(value = "执行参数", notes = "执行参数")
    private String excuteParams;
    /**
     * 执行类型
     */
    @ApiModelProperty(value = "执行类型", notes = "执行类型")
    private String excuteType;

    @ApiModelProperty(value = "日志描述")
    private String logRemark;

}