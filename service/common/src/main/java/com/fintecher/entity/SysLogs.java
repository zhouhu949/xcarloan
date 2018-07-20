package com.fintecher.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * @System: 车贷金融
 * @Auther: hukaijia
 * @Description: 操作日志实体类
 * @Date:Created on 2017/12/25/025 13:13
 * @Modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "操作日志实体类")
@EqualsAndHashCode(callSuper = false)
@Table(name = "sys_logs")
public class SysLogs extends BaseEntity {

    /**
     * 用户ip
     */
    @ApiModelProperty(value = "用户ip", notes = "用户ip")
    @Column(name = "client_ip")
    private String clientIp;
    /**
     * 操作时间
     */
    @ApiModelProperty(value = "操作时间", notes = "操作时间")
    @Column(name = "operate_time")
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private Date operateTime;
    /**
     * 操作人
     */
    @ApiModelProperty(value = "操作人", notes = "操作人")
    @Column(name = "operator")
    private String operator;



    /**
     * 执行时间
     */
    @ApiModelProperty(value = "执行时间 ", notes = "执行时间 ")
    @Column(name = "excute_time")
    private Long excuteTime;
    /**
     * 执行方法
     */
    @ApiModelProperty(value = "执行方法 ", notes = "执行方法")
    @Column(name = "excute_method")
    private String excuteMethod;
    /**
     * 执行参数
     */
    @ApiModelProperty(value = "执行参数", notes = "执行参数")
    @Column(name = "excute_params")
    private String excuteParams;
    /**
     * 执行类型
     */
    @ApiModelProperty(value = "执行类型", notes = "执行类型")
    @Column(name = "excute_type")
    private String excuteType;

    @ApiModelProperty(value = "日志描述")
    @Column(name = "log_remark")
    private String logRemark;

}