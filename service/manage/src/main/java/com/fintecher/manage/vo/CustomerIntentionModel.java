package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/25 18:10
 * @Description:
 */
@Data
public class CustomerIntentionModel {
    @ApiModelProperty(value = "客户姓名")
    private String customerName;
    @ApiModelProperty(value = "客户性别")
    private Integer customerSex;
    @ApiModelProperty(value = "身份证")
    private String idCard;
    @ApiModelProperty(value = "客户电话")
    private String customerPhone;
    @ApiModelProperty(value = "意向类型")
    private Integer intentionType;
    @ApiModelProperty(value = "意向等级")
    private Double intentionLevel;
    @ApiModelProperty(value = "跟进结果")
    private Integer followResult;
    @ApiModelProperty(value = "客户id")
    private Integer id;

}
