package com.fintecher.manage.vo;

import com.fintecher.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @System: 车贷金融
 * @Description: 客户联系人
 * @Modified By:
 */
@Data
@ApiModel("客户联系人")
public class EditCustomContact{

    @ApiModelProperty("联系人id")
    private Long id;

    @ApiModelProperty("客户ID")
    private Long customerId;


    @ApiModelProperty("姓名")
    private String contactName;


    @ApiModelProperty("性别")
    private Integer contactSex;

    @ApiModelProperty("关系")
    private Integer contactRelation;


    @ApiModelProperty("联系电话")
    private String contactPhone;

    @ApiModelProperty("邮件")
    private String contactEmail;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("操作人")
    private Integer operator;
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("操作日期")
    private Date operatorTime;

}
