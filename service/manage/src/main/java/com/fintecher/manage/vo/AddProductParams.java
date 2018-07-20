package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "车型产品参数")
public class AddProductParams {

    @ApiModelProperty("方案ID")
    @NotNull(message="方案ID不能为空")
    private Long schemeId;

    @ApiModelProperty("车型ID")
    @NotNull(message="车型ID不能为空")
    private Long configId;

    @ApiModelProperty("产品名称")
    @NotBlank(message = "产品名称不能为空")
    private String productName;
    @ApiModelProperty("车型产品类型")
    @NotNull(message = "车型产品类型不能为空")
    private int productType;

    @ApiModelProperty("路径")
    private String fileUrl;
}
