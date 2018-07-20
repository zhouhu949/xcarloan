package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/15 13:50
 * @Description:
 */
@Data
public class BasicCustomerCarModel {

    @ApiModelProperty(value = "车辆id")
    private Long id;
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
    @ApiModelProperty(value = "抵押登记次数")
    private Integer mortgageNum;
    @ApiModelProperty(value = "购买方式")
    private Integer buyType;
    @ApiModelProperty(value = "是否贷款已还请")
    private Integer isLoanFinished;
    @ApiModelProperty(value = "抵押状态")
    private Integer carStatus;
    @ApiModelProperty(value = "客户id")
    private Long customerId;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "操作人")
    private Long operator;
    @ApiModelProperty(value = "操作时间")
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private Date operatorTime;
    @ApiModelProperty(value = "车型名称")
    private String carModelName;
}
