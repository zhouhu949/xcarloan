package com.fintecher.task.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "还款方案比例详情Model")
public class BasicRepaySchemeExpenseModel{
    @ApiModelProperty("方案ID")
    private Long schemeId;
    @ApiModelProperty("费用项ID")
    private Long expenseId;
    @ApiModelProperty("费用项名称")
    private String expenseName;
    @ApiModelProperty("费用项编码")
    private String expenseCode;
    @ApiModelProperty("收取总额比例")
    private BigDecimal repayProportion;

    public enum ExpenseCode{
        FX("fx","fx"),
        FJ("fj","fx");
        private String name;
        private String value;
        ExpenseCode(String name,String value){
            this.name=name;
            this.value=value;
        }
        public String getValue(){ return value; }
    }
}
