package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @System: 车贷金融
 * @Auther: hukaijia
 * @Description:
 * @Date:Created on 2018/1/4/004 18:23
 * @Modified By:
 */
@Data
public class ModifyResoIconModel {
    /**
     * 资源id
     */
    @ApiModelProperty(value = "资源id")
    private Long id;
    /**
     * 资源图标
     */
    @ApiModelProperty(value = "资源图标")
    private String resoIcon;
}
