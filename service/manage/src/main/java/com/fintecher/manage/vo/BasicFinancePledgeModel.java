package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/23 17:49
 * @Description:
 */
@Data
@ApiModel("质押记录参数")
public class BasicFinancePledgeModel {

    @ApiModelProperty(value = "入库日期")
    private Date stockInDate;

    @ApiModelProperty(value = "出库日期")
    private Date stockOutDate;

    @ApiModelProperty(value = "押品状态")
    private Integer mortgageStatus;

    @ApiModelProperty(value = "质押地点")
    private String pledgePlace;

    @ApiModelProperty(value = "质押位置")
    private String pledgePosition;

    @ApiModelProperty(value = "质押号")
    private String pledgeNo;

    @ApiModelProperty(value = "操作人")
    private Long operator;

    @ApiModelProperty(value = "操作时间")
    private Date operatorTime;

    @ApiModelProperty(value = "抵押状态")
    private Integer carStatus;
    @ApiModelProperty(value = "车辆id")
    private Long carId;
    @ApiModelProperty(value = "车牌号")
    private String carNo;

}
