package com.fintecher.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/21 12:04
 * @Description:
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Api("客户上传资料")
@Table(name = "basic_customer_data")
public class BasicCustomerData extends BaseEntity {
    @ApiModelProperty(value = "客户id")
    private Long customerId;
    @ApiModelProperty(value = "资料类型")
    private Integer dataType;
    @ApiModelProperty(value = "路径")
    private String fileUrl;
    @ApiModelProperty(value = "文件名称")
    private String fileName;
    @ApiModelProperty(value = "操作人")
    private Long operator;
    @ApiModelProperty(value = "操作时间")
    private Date operatorTime;
    @ApiModelProperty(value = "备注")
    private String remark;
}
