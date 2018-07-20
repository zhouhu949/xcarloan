package com.fintecher.manage.vo;

import com.fintecher.entity.BasicRepaySchemeExpense;
import com.fintecher.util.EnumValue;
import com.fintecher.util.Status;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@ApiModel("费用项参数")
public class BasicExpenseParams {
    @ApiModelProperty("费用项ID")
    @NotNull(message = "费用项ID不能为空")
    private Long expenseId;
    @ApiModelProperty("是否首付款:10002-是，10003-否")
    @NotNull(message = "请选择是否首付款")
    @EnumValue(enumClass = Status.class, enumMethod = "isValidValue", message = "是否首付款参数错误")
    private Integer isFirst;
    @ApiModelProperty("收取总额比例（%）")
    @Min(value = 0, message = "收取总额比例不能小于0")
    private BigDecimal repayProportion;
    @ApiModelProperty("固定费用")
    @Min(value = 0, message = "固定费用不能小于0")
    private BigDecimal fixedCost;
    @ApiModelProperty("还款方式")
    @NotNull(message = "还款方式不能为空")
    @EnumValue(enumClass = BasicRepaySchemeExpense.RepayType.class, enumMethod = "isValidValue", message = "还款方式参数错误")
    private Integer repayType;
    @ApiModelProperty("是否尾款:10002-是，10003-否")
    @NotNull(message = "请选择是否尾款")
    @EnumValue(enumClass = Status.class, enumMethod = "isValidValue", message = "是否尾款参数错误")
    private Integer isLast;
    @ApiModelProperty("是否退款:10002-是，10003-否")
    @NotNull(message = "请选择是否退款")
    @EnumValue(enumClass = Status.class, enumMethod = "isValidValue", message = "是否退款参数错误")
    private Integer isRefund;


}
