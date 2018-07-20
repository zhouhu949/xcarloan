package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;

@Data
@ApiModel("还款方案信息")
public class SchemeInfoModel {
    @ApiModelProperty("还款方案ID")
    private Long id;
    @ApiModelProperty("冲抵ID")
    private Long offsetId;
    @ApiModelProperty("冲抵策略名称")
    private String offsetName;
    @ApiModelProperty("方案类型: 10049-融资租赁，10050-抵押贷款")
    private Integer schemeType;
    @ApiModelProperty("方案名称")
    private String schemeName;
    @ApiModelProperty("还款方式：10051-按期付息还本,10052-等额本息,10053-等额本金")
    private Integer repayType;
    @ApiModelProperty("抵押方式:10054-质押，10055-抵押")
    private Integer mortgageType;
    @ApiModelProperty("发布状态：10056-未发布，10057-已发布")
    private Integer schemeStatus;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("征信保护天数")
    private Integer creditDays;
    @ApiModelProperty("逾期保护天数")
    private Integer overdueDays;
    @ApiModelProperty("期数")
    private Integer periods;
    @ApiModelProperty("利率")
    private BigDecimal interestRate;
    @ApiModelProperty("周期类型:10058-月")
    private Integer cycleType;
    @ApiModelProperty("融资最小金额")
    private BigDecimal moneyMin;
    @ApiModelProperty("融资最大金额")
    private BigDecimal moneyMax;
    @ApiModelProperty("账期类型：10059-正常账期，10060-固定账期")
    private Integer accountPeriodType;
    @ApiModelProperty("还款日")
    private Integer accountDay;
    @ApiModelProperty("审核流程Key")
    private String workFlowKey;
}
