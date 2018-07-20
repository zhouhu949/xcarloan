package com.fintecher.manage.vo;

import com.fintecher.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/14 19:06
 * @Description:
 */
@Data
@ApiModel(description = "客户房产实体类")
public class EditCustomHouseParams {

    @ApiModelProperty(value = "房产id")
    private Long id;

    @ApiModelProperty("客户ID")
    private Long customerId;


    @ApiModelProperty("房产地址")
    private String houseAddress;

    @ApiModelProperty("户型")
    private Integer houseType;

    @ApiModelProperty("面积")
    private Integer houseArea;

    @ApiModelProperty("操作人")
    private Integer operator;

    @ApiModelProperty("备注")
    private String remark;
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("操作日期")
    private Date operatorTime;
}
