package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/28 13:57
 * @Description:
 */
@Data
@ApiModel("押品基础信息")
public class CustomerCarInfoModel {
    @ApiModelProperty(value = "客户姓名")
    private String customerName;
    @ApiModelProperty(value = "身份证")
    private String idCard;
    @ApiModelProperty(value = "客户电话")
    private String customerPhone;
    @ApiModelProperty(value = "车牌号")
    private String carNo;
    @ApiModelProperty(value = "车辆型号")
    private Integer carType;
    @ApiModelProperty(value = "购买方式")
    private Integer buyType;


}
