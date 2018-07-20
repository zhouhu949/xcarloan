package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/27 14:11
 * @Description:
 */
@Data
@ApiModel("评估报告列表")
public class CustomerAssessmentReportModel {
    @ApiModelProperty(value = "客户编号")
    private String customerCode;

    @ApiModelProperty(value = "客户姓名")
    private String customerName;

    @ApiModelProperty(value = "客户状态")
    private Integer customerStatus;

    @ApiModelProperty(value = "客户性别")
    private Integer customerSex;
    @ApiModelProperty(value = "申请评估日期")
    private Date assessmentApplyDate;

    @ApiModelProperty(value = "评估日期")
    private Date assessmentDate;

    @ApiModelProperty(value = "评估结果")
    private String assessmentResult;
    @ApiModelProperty(value = "车牌号")
    private String carNo;
    @ApiModelProperty(value = "购车价格")
    private BigDecimal carPrice;
    @ApiModelProperty(value = "车辆类型")
    private Integer carType;
    @ApiModelProperty(value = "评估报告id")
    private Long carAssessmentId;


}
