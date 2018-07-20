package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/22 20:24
 * @Description:
 */
@Data
public class BasicCustomerBlackListRecordModel {
    @ApiModelProperty(value = "组织id")
    private Long orgId;

    @ApiModelProperty(value = "客户手机号")
    private String customerPhone;

    @ApiModelProperty(value = "客户性别")
    private Integer customerSex;

    @ApiModelProperty(value = "客户编号")
    private String customerCode;

    @ApiModelProperty(value = "客户姓名")
    private String customerName;

    @ApiModelProperty(value = "客户状态")
    private Integer customerStatus;

    @ApiModelProperty(value = "客户id")
    private Long customerId;

    @ApiModelProperty(value = "开始时间")
    private Date beginTime;

    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "名单类型")
    private Integer blacklistType;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "操作人")
    private Integer operator;

    @ApiModelProperty(value = "操作时间")
    private Date operator_time;
}
