package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@ApiModel("还款方案和还款比例详情信息")
public class SchemeAndExpenseInfo {
    @ApiModelProperty("还款方案ID")
    private Long schemeId;
    @ApiModelProperty("方案类型: 10049-融资租赁，10050-抵押贷款")
    private Integer schemeType;
    @ApiModelProperty("方案名称")
    private String schemeName;
    @ApiModelProperty("还款方式：10051-按期付息还本,10052-等额本息,10053-等额本金")
    private Integer repayType;
    @ApiModelProperty("期数")
    private Integer periods;
    @ApiModelProperty("利率")
    private BigDecimal interestRate;
    @ApiModelProperty("还款日")
    private Integer accountDay;
    @ApiModelProperty("还款方案比例详情详细信息，包含费用项信息")
    List<RepaySchemeExpenseModel> schemeExpenseModelList;
}
