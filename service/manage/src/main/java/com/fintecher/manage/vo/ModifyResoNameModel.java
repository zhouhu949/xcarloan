package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @System: 车贷金融
 * @Auther: hukaijia
 * @Description:
 * @Date:Created on 2018/1/4/004 17:41
 * @Modified By:
 */
@Data
public class ModifyResoNameModel {
    /**
     * 资源id
     */
    @ApiModelProperty(value = "资源id")
    private Long id;
    /**
     * 资源名称
     */
    @ApiModelProperty(value = "资源名称")
    private String resoName;
}
