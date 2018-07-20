package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/23 17:48
 * @Description:
 */
@Data
@ApiModel("抵押记录参数")
public class BasicFinanceMortgageModel {

    @ApiModelProperty(value = "抵押记录ID")
    private Long mortgageRecordId;

    @ApiModelProperty(value = "入库日期")
    private Date stockInDate;

    @ApiModelProperty(value = "出库日期")
    private Date stockOutDate;


    @ApiModelProperty(value = "押品状态")
    private Integer mortgageStatus;


    @ApiModelProperty(value = "抵押号")
    private String mortgageNo;
    @ApiModelProperty(value = "抵押状态")
    private Integer car_status;

    @ApiModelProperty(value = "车辆id")
    private Long carId;
    @ApiModelProperty(value = "车牌号")
    private String carNo;

    @ApiModelProperty(value = "抵押状态")
    private Integer carStatus;

    @ApiModelProperty(value = "GPS安装状态")
    private String gpsStatus;


    @ApiModelProperty(value = "设备号")
    private String gpsNo;


    @ApiModelProperty(value = "设备厂家")
    private Integer gpsManufactor;

}
