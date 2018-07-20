package com.fintecher.manage.vo;

import com.fintecher.entity.BasicProduct;
import com.fintecher.util.EnumValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "修改车型产品参数")
public class EditProductParams {

    @ApiModelProperty("车型产品ID主键")
    @NotNull(message="主键不能为空")
    Long id;

    @ApiModelProperty("方案ID")
    @NotNull(message = "还款方案不能为空")
    private Long schemeId;

    @ApiModelProperty("配置ID")
    @NotNull(message = "车型不能为空")
    private Long configId;

    @ApiModelProperty("产品名称")
    private String productName;

    @ApiModelProperty("产品类型")
    @NotNull
    @EnumValue(enumClass = BasicProduct.ProductType.class, enumMethod = "isValidValue", message = "产品类型参数错误")
    private Integer productType;

    @ApiModelProperty("路径")
    private String fileUrl;
}
