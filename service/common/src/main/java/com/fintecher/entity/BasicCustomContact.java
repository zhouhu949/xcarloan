package com.fintecher.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.omg.CORBA.INTERNAL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @System: 车贷金融
 * @Auther: hukaijia
 * @Description: 客户职业实体类
 * @Date:Created on 2017/12/25/025 13:13
 * @Modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@ApiModel("客户联系人实体类")
@Table(name = "basic_customer_contact")
public class BasicCustomContact extends BaseEntity{


    @ApiModelProperty("客户ID")
    @Column(name = "customer_id")
    private Long customerId;


    @ApiModelProperty("姓名")
    @Column(name = "contact_name")
    private String contactName;


    @ApiModelProperty("性别")
    @Column(name = "contact_sex")
    private Integer contactSex;

    @ApiModelProperty("关系")
    @Column(name = "contact_relation")
    private Integer contactRelation;


    @ApiModelProperty("联系电话")
    @Column(name = "contact_phone")
    private String contactPhone;

    @ApiModelProperty("邮件")
    @Column(name = "contact_email")
    private String contactEmail;

    @ApiModelProperty("备注")
    @Column(name = "remark")
    private String remark;

    @ApiModelProperty("操作人")
    @Column(name = "operator")
    private Integer operator;

    @ApiModelProperty("操作日期")
    @Column(name = "operator_time")
    private Date operatorTime;

}
