package com.fintecher.entity;

import com.sun.tracing.dtrace.ArgsAttributes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @System: 车贷金融
 * @Auther: hukaijia
 * @Description: 客户房产实体类
 * @Date:Created on 2017/12/25/025 13:13
 * @Modified By:
 */
@Data
@ApiModel(description = "客户房产实体类")
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@Table(name = "basic_customer_house")
public class BasicCustomerHouse extends BaseEntity{


    @ApiModelProperty("客户ID")
    @Column(name = "customer_id")
    private Long customerId;

    @ApiModelProperty("房产地址")
    @Column(name = "house_address")
    private String houseAddress;

    @ApiModelProperty("户型")
    @Column(name = "house_type")
    private Integer houseType;

    @ApiModelProperty("面积")
    @Column(name = "house_area")
    private Integer houseArea;

    @ApiModelProperty("操作人")
    @Column(name = "operator")
    private Integer operator;

    @ApiModelProperty("备注")
    @Column(name = "remark")
    private String remark;

    @ApiModelProperty("操作日期")
    @Column(name = "operator_time")
    private Date operatorTime;


}
