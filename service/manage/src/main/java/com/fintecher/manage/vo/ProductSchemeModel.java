package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductSchemeModel {
    @ApiModelProperty("车型产品id")
    private Long productId;
    @ApiModelProperty("方案ID")
    private Long schemeId;
    @ApiModelProperty("车型ID")
    private Long configId;
    @ApiModelProperty("产品名称")
    private String productName;
    @ApiModelProperty("路径")
    private String fileUrl;
    @ApiModelProperty("产品类型")
    private int productType;
    @ApiModelProperty("方案名称")
    private String schemeName;
    @ApiModelProperty("车型名称")
    private String carModelName;
    @ApiModelProperty("期数")
    private Integer periods;
    @ApiModelProperty("利率")
    private BigDecimal interestRate;
    @ApiModelProperty("还款方式：10051-按期付息还本,10052-等额本息,10053-等额本金")
    private Integer repayType;
    @ApiModelProperty("还款日")
    private Integer accountDay;

}
