package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;

@Data
public class BasicProductModel {
    @ApiModelProperty("车型产品id")
    private Long id;
    @ApiModelProperty("方案ID")
    private Long schemeId;
    @ApiModelProperty("车型ID")
    private Long configId;
    @ApiModelProperty("机构ID")
    @Column(name = "org_id")
    private Long orgId;
    @ApiModelProperty("产品名称")
    private String productName;
    @ApiModelProperty("路径")
    private String fileUrl;
    @ApiModelProperty("产品类型")
    private int productType;
    @ApiModelProperty("产品是否发布")
    private Integer productStatus;
    @ApiModelProperty("方案名称")
    private String schemeName;
    @ApiModelProperty("车型名称")
    private String carModelName;
   @ApiModelProperty("期数")
    private Integer periods;
   @ApiModelProperty("利率")
    private BigDecimal interestRate;

}
