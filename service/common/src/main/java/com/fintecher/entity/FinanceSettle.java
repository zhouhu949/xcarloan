package com.fintecher.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/25 11:19
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@ApiModel("提前结清记录")
@Table(name = "finance_settle")
public class FinanceSettle extends BaseEntity {
    @ApiModelProperty(value = "收款记录ID")
    private Long receivableId;
    @ApiModelProperty(value = "订单ID")
    private Long orderId;
    @ApiModelProperty(value = "机构ID")
    private Long orgId;
    @ApiModelProperty(value = "提前结清日期")
    private Date settleDate;
    @ApiModelProperty(value = "提前结清金额")
    private BigDecimal settleMoney;
    @ApiModelProperty(value = "操作人")
    private Long operator;
    @ApiModelProperty(value = "操作日期")
    private Date operatorTime;
    @ApiModelProperty(value = "备注")
    private String remark;

}
