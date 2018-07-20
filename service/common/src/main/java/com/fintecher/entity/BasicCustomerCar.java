package com.fintecher.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/14 19:06
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@ApiModel("客户车产")
@Table(name = "basic_customer_car")
public class BasicCustomerCar extends BaseEntity {

    @ApiModelProperty("订单ID")
    @Column(name = "order_id")
    private Long orderId;
    @ApiModelProperty("客户ID")
    @Column(name = "customer_id")
    private Long customerId;
    @ApiModelProperty(value = "车牌号")
    @Column(name = "car_no")
    private String carNo;
    @ApiModelProperty(value = "购车价格")
    @Column(name = "car_price")
    private BigDecimal carPrice;
    @ApiModelProperty(value = "车辆型号")
    @Column(name = "car_type")
    private Integer carType;
    @ApiModelProperty(value = "是否二手车")
    @Column(name = "is_second_hand")
    private Integer isSecondHand;
    @ApiModelProperty(value = "初次登记时间")
    @Column(name = "register_time")
    private Date registerTime;
    @ApiModelProperty(value = "抵押登记次数")
    @Column(name = "mortgage_num")
    private Integer mortgageNum;
    @ApiModelProperty(value = "购买方式")
    @Column(name = "buy_type")
    private Integer buyType;
    @ApiModelProperty(value = "是否贷款已还请")
    @Column(name = "is_loan_finished")
    private Integer isLoanFinished;
    @ApiModelProperty(value = "抵押状态")
    @Column(name = "car_status")
    private Integer carStatus;
    @ApiModelProperty(value = "备注")
    @Column(name = "remark")
    private String remark;
    @ApiModelProperty(value = "操作人")
    @Column(name = "operator")
    private Long operator;
    @ApiModelProperty(value = "操作时间")
    @Column(name = "operator_time")
    private Date operatorTime;
    @ApiModelProperty(value = "车型名称")
    @Column(name = "car_model_name")
    private String carModelName;

}
