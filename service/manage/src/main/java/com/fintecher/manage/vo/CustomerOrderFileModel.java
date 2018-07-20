package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/27 10:28
 * @Description:
 */
@Data
@ApiModel("订单资料封装类")
public class CustomerOrderFileModel {
    @ApiModelProperty(value = "订单资料类型")
    private Integer fileType;
    @ApiModelProperty(value = "资料url地址")
    private String fileUrl;
    @ApiModelProperty(value = "订单id")
    private Long orderId;
    @ApiModelProperty(value = "资料类型")
    private String fileName;
    @ApiModelProperty(value = "文件id")
    private Long fileId;
}
