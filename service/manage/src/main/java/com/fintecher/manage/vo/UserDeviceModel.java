package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("获取用户设备返回模型")
public class UserDeviceModel {
    @ApiModelProperty("设备ID")
    private Long id;
    @ApiModelProperty("用户ID")
    private Long userId;
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("用户姓名")
    private String realName;
    @ApiModelProperty("设备编号")
    private String deviceCode;
    @ApiModelProperty("设备类型")
    private Integer deviceType;
    @ApiModelProperty("设备名称")
    private String deviceName;
    @ApiModelProperty("是否启用设备锁")
    private Integer deviceValidate;
    @ApiModelProperty("是否启用设备")
    private Integer deviceStatus;
    @ApiModelProperty("设备识别码")
    private String deviceMac;
}
