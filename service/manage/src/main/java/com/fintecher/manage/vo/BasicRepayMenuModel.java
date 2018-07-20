package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("还款方案菜单列表")
public class BasicRepayMenuModel {
    @ApiModelProperty("方案ID")
    private Long id;
    @ApiModelProperty("方案名称")
    private String schemeName;
}
