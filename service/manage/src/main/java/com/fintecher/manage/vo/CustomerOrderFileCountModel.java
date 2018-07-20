package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/27 20:25
 * @Description:
 */
@Data
@ApiModel("订单资料数量")
public class    CustomerOrderFileCountModel {
    @ApiModelProperty(value = "订单资料类型")
    private Integer orderFileType;

    @ApiModelProperty(value = "路径")
    private String fileUrl;

    @ApiModelProperty(value = "订单id")
    private Long orderId;

    @ApiModelProperty(value = "资料类型")
    private Integer fileType;

    @ApiModelProperty(value = "文件数量")
    private Integer fileCount;
    @ApiModelProperty(value = "资料名称")
    private String fileName;

}
