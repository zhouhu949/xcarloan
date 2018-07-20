package com.fintecher.manage.vo;

import com.fintecher.entity.BasicRepaySchemeExpense;
import com.fintecher.util.EnumValue;
import com.fintecher.util.Status;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@ApiModel("还款方案详情修改请求参数")
public class EditExpenseParams {
    @ApiModelProperty("还款方案详情ID")
    @NotNull(message = "还款方案详情ID不能为空")
    private Long id;
    @ApiModelProperty("是否首付款:10002-是，10003-否")
    @NotNull(message = "是否首付款不能为空")
    @EnumValue(enumClass = Status.class, enumMethod = "isValidValue", message = "是否首付款参数错误")
    private Integer isFirst;
    @ApiModelProperty("收取总额比例")
    private BigDecimal repayProportion;
    @ApiModelProperty("固定费用")
    private BigDecimal fixedCost;
    @ApiModelProperty("还款方式：10063-一次性收取，10064-分期")
    @NotNull(message = "还款方式不能为空")
    @EnumValue(enumClass = BasicRepaySchemeExpense.RepayType.class, enumMethod = "isValidValue", message = "还款方式参数错误")
    private Integer repayType;
    @ApiModelProperty("是否尾款：10002-是，10003-否")
    @NotNull(message = "是否尾款不能为空")
    @EnumValue(enumClass = Status.class, enumMethod = "isValidValue", message = "是否尾款参数错误")
    private Integer isLast;
    @ApiModelProperty("是否退款：10002-是，10003-否")
    @NotNull(message = "是否尾款不能为空")
    @EnumValue(enumClass = Status.class, enumMethod = "isValidValue", message = "是否尾款参数错误")
    private Integer isRefund;
    @ApiModelProperty("备注")
    private String remark;

}
