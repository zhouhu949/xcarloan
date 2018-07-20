package com.fintecher.task.vo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BasicOrderRepaySchemeModel {
    @ApiModelProperty("费用项id")
    private Long expenseId;
    @ApiModelProperty("还款计划id")
    private Long repayTemplateId;
    @ApiModelProperty("已还金额")
    private BigDecimal isRepayMoney;
    @ApiModelProperty("还款状态")
    private Integer repayStatus;
    @ApiModelProperty("订单id")
    private Long orderId;
    @ApiModelProperty("期数")
    private Integer periods;
    @ApiModelProperty("方案id")
    private Long schemeId;
    @ApiModelProperty("应还id")
    private BigDecimal repayMoney;
    @ApiModelProperty("还款时间")
    private Date repaymentDate;
    @ApiModelProperty("费用项名称")
    private String expenseName;
    @ApiModelProperty("费用项编码")
    private String expenseCode;
    @ApiModelProperty("操作人")
    private Long operator;
    @ApiModelProperty("操作时间")
    private Date operatorTime;
    @ApiModelProperty("备注")
    private String remark;
    /**
     * 还款状态
     */
    public enum RepayStatus {
        OVER(10119, "逾期");
        private Integer value;
        private String remark;
        RepayStatus(Integer value, String remark) {
            this.value = value;
            this.remark = remark;
        }
    }
}
