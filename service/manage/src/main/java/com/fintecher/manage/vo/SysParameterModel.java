package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @System: 车贷金融
 * @Auther: hukaijia
 * @Description: 系统参数实体类
 * @Date:Created on 2017/12/25/025 13:13
 * @Modified By:
 */
@Data
public class SysParameterModel {
    /**
     * 参数名称
     */
    @ApiModelProperty(value = "id", notes = "id")
    private Long id;
    /**
     * 参数名称
     */
    @ApiModelProperty(value = "参数名称", notes = "参数名称")
    private String paramName;
    /**
     * 参数自定义code
     */
    @ApiModelProperty(value = "参数自定义code", notes = "参数自定义code")
    private String paramCode;
    /**
     * 参数状态 435 启用 436 停用
     */
    @ApiModelProperty(value = "参数状态", notes = "435 启用 436 停用")
    private Integer paramStatus;
    /**
     * 参数类型（服务的端口号）
     */
    @ApiModelProperty(value = "参数类型（服务的端口号）", notes = "参数类型（服务的端口号）")
    private String paramType;
    /**
     * 参数值value
     */
    @ApiModelProperty(value = "参数值value", notes = "参数值value")
    private String paramValue;
    /**
     * 是否允许修改
     */
    @ApiModelProperty(value = "是否允许修改", notes = "是否允许修改")
    private Integer isReadonly;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", notes = "备注")
    private String remark;

    @ApiModelProperty(notes = "操作员")
    private String operatorName;
    @ApiModelProperty(notes = "操作时间")
    private Date operateTime;
}
