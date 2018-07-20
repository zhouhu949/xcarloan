package com.fintecher.manage.vo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
@Data
public class FinancialInvoiceModel {
    @ApiModelProperty(value = "ID")
    private Long id;
    @ApiModelProperty(value = "机构ID")
    private Long orgId;
    @ApiModelProperty(value = "操作人ID")
    private Long operatorId;
    @ApiModelProperty(value = "客户ID")
    private Long customerId;
    @ApiModelProperty(value = "提前结清ID")
    private Long settleId;
    @ApiModelProperty(value = "提前收回ID")
    private Long takebackId;
    @ApiModelProperty(value = "银行卡ID")
    private Long cardId;
    @ApiModelProperty(value = "订单ID")
    private Long orderId;
    @ApiModelProperty(value = "收款类型")
    private Integer receivableType;
    @ApiModelProperty(value = "收款金额")
    private BigDecimal receivableDetialMoney;
    @ApiModelProperty(value = "收款日期")
    private Date receivableDate;
    @ApiModelProperty(value = "是否已开发票")
    private Integer isInvoice;
    @ApiModelProperty(value = "是否已开收据")
    private Integer isReceipt;
    @ApiModelProperty(value = "用户姓名")
    private String customerName;
    @ApiModelProperty(value = "订单编号")
    private String orderNo;
    @ApiModelProperty(value = "用户手机")
    private String customerPhone;
    @ApiModelProperty(value = "操作人姓名")
    private String operatorName;
}
