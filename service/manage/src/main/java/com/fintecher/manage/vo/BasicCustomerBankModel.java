package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/20 17:53
 * @Description:
 */
@Data
public class BasicCustomerBankModel {

    @ApiModelProperty(value = "银行卡id")
    private Long id;
    @ApiModelProperty(value = "客户id")
    private Long customerId;
    @ApiModelProperty(value = "开户行名称")
    private String bankName;
    @ApiModelProperty(value = "开户行支行")
    private String bankBranch;
    @ApiModelProperty(value = "银行卡号")
    private String cardNo;
    @ApiModelProperty(value = "客户号")
    private String clientNumber;
    @ApiModelProperty(value = "账户类型")
    private Integer accountType;
    @ApiModelProperty(value = "开户城市")
    private Integer depositCity;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "操作人")
    private Integer operator;
    @ApiModelProperty(value = "操作时间")
    private Date operatorTime;
    @ApiModelProperty(value = "开户省份")
    private Integer province;
    @ApiModelProperty(value = "开户状态")
    private Integer accountStatus;
}
