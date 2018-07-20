package com.fintecher.manage.vo;

import com.fintecher.entity.BasicRepayScheme;
import com.fintecher.entity.BasicRepaySchemeExpense;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("还款方案详情")
public class BasicRepaySchemeModel {
    @ApiModelProperty("还款方案")
    private BasicRepayScheme basicRepayScheme;
    @ApiModelProperty("还款方案比例详情")
    private List<BasicRepaySchemeExpense> basicRepaySchemeExpenseList;
}
