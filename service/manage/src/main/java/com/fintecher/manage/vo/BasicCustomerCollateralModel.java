package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/23 14:23
 * @Description:
 */
@Data
public class BasicCustomerCollateralModel {
    @ApiModelProperty(value = "申请评估日期")
    private Date assessmentApplyDate;

    @ApiModelProperty(value = "评估日期")
    private Date assessmentDate;

    @ApiModelProperty(value = "评估结果")
    private String assessmentResult;

    @ApiModelProperty(value = "评估状态")
    private Integer assessmentStatus;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "操作人")
    private Long operator;

    @ApiModelProperty(value = "操作日期")
    private Date operatorTime;

    @ApiModelProperty(value = "评估编号")
    private String assessmentNo;

    @ApiModelProperty(value = "抵押状态")
    private Integer car_status;

    @ApiModelProperty(value = "车辆id")
    private Long carId;
    @ApiModelProperty(value = "车牌号")
    private String carNo;




}
