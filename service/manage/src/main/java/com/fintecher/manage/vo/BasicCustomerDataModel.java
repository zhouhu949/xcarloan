package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/21 14:45
 * @Description:
 */
@Data
public class BasicCustomerDataModel {

    @ApiModelProperty(value = "客户id")
    private Long customerId;
    @ApiModelProperty(value = "资料类型")
    private Integer dataType;
    @ApiModelProperty(value = "文件类型")
    private Integer fileType;
    @ApiModelProperty(value = "路径")
    private String fileUrl;
    @ApiModelProperty(value = "文件名称")
    private String fileName;

}
