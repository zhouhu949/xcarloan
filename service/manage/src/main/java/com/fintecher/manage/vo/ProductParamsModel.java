package com.fintecher.manage.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("车型产品Model")
public class ProductParamsModel {

    @ApiModelProperty("车型产品ID主键")
    Long id;

    @ApiModelProperty("方案ID")
    private Long schemeId;

    @ApiModelProperty("配置ID")
    private Long configId;

    @ApiModelProperty("产品名称")
    private String productName;

    @ApiModelProperty("机构ID")
    private Long  orgId;

    @ApiModelProperty("路径")
    private String fileUrl;

    @ApiModelProperty("车型配置名称")
    private String configName;

    @ApiModelProperty("还款方案名称")
    private String schemeName;
}
