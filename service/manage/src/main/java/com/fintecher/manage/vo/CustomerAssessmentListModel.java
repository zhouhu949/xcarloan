package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/27 11:12
 * @Description:
 */
@Data
@ApiModel("客户评估列表")
public class CustomerAssessmentListModel {
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

    @ApiModelProperty(value = "评估状态")
    private Integer assessmentStatus;

    @ApiModelProperty(value = "评估编号")
    private String assessmentNo;

    @ApiModelProperty(value = "车牌号")
    private String carNo;

    @ApiModelProperty(value = "购车价格")
    private BigDecimal carPrice;

    @ApiModelProperty(value = "车辆型号")
    private Integer carType;

    @ApiModelProperty(value = "是否二手车")
    private Integer isSecondHand;

    @ApiModelProperty(value = "初次登记时间")
    private Date registerTime;

    @ApiModelProperty(value = "客户车产id")
    private Long carId;


}
