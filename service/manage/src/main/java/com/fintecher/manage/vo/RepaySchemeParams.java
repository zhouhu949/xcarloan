package com.fintecher.manage.vo;

import com.fintecher.entity.BasicRepayScheme;
import com.fintecher.util.EnumValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@ApiModel("新增/修改还款方案")
public class RepaySchemeParams {
    @ApiModelProperty("还款方案ID")
    private Long id;
    @ApiModelProperty("冲抵ID")
    @NotNull(message = "冲抵策略不能为空")
    private Long offsetId;
    @ApiModelProperty("方案类型: 10049-融资租赁，10050-抵押贷款")
    @NotNull(message = "方案类型不能为空")
    @EnumValue(enumClass = BasicRepayScheme.SchemeType.class, enumMethod = "isValidValue", message = "方案类型错误")
    private Integer schemeType;
    @ApiModelProperty("方案名称")
    @NotBlank(message = "方案名称不能为空")
    private String schemeName;
    @ApiModelProperty("还款方式：10051-按期付息还本,10052-等额本息,10053-等额本金")
    @NotNull(message = "还款方式不能为空")
    @EnumValue(enumClass = BasicRepayScheme.RepayType.class, enumMethod = "isValidValue", message = "还款方式错误")
    private Integer repayType;
    @ApiModelProperty("抵押方式:10054-质押，10055-抵押")
    @EnumValue(enumClass = BasicRepayScheme.MortgageType.class, enumMethod = "isValidValue", message = "抵押方式错误")
    private Integer mortgageType;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("征信保护天数")
    private Integer creditDays = 0;
    @ApiModelProperty("逾期保护天数")
    private Integer overdueDays = 0;
    @ApiModelProperty("期数")
    @NotNull(message = "期数不能为空")
    private Integer periods;
    @ApiModelProperty("利率")
    @NotNull(message = "利率不能为空")
    private BigDecimal interestRate;
    @ApiModelProperty("周期类型:10058-月")
    @NotNull(message = "周期不能为空")
    @EnumValue(enumClass = BasicRepayScheme.CycleType.class, enumMethod = "isValidValue", message = "周期类型错误")
    private Integer cycleType;
    @ApiModelProperty("融资最小金额")
    @NotNull(message = "融资最小金额不能为空")
    @Min(value = 0, message = "融资最小金额有误")
    private BigDecimal moneyMin;
    @NotNull(message = "融资最大金额不能为空")
    @Min(value = 0, message = "融资最大金额有误")
    @ApiModelProperty("融资最大金额")
    private BigDecimal moneyMax;
    //    @ApiModelProperty("账期类型：10059-正常账期，10060-固定账期")
//    @NotNull(message = "账期类型为空")
//    @EnumValue(enumClass = BasicRepayScheme.AccountPeriodType.class, enumMethod = "isValidValue", message = "账期类型错误")
//    private Integer accountPeriodType;
    @ApiModelProperty("还款日")
    @NotNull
    @Min(value = 1, message = "还款日在1~31之间")
    @Max(value = 31, message = "还款日在1~31之间")
    private Integer accountDay;
    @ApiModelProperty("工作流KEY")
    @NotBlank(message = "工作流必选")
    private String workFlowKey;

}
