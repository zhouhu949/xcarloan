package com.fintecher.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/20 17:29
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Api("客户银行卡信息")
@Table(name = "basic_customer_bank")
public class BasicCustomerBank extends BaseEntity {
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
    private Long operator;
    @ApiModelProperty(value = "操作时间")
    private Date operatorTime;
    @ApiModelProperty(value = "开户状态")
    private Integer accountStatus;

    @ApiModelProperty(value = "开户省份")
    private Integer province;

    public enum OpenAccountStatus {
        SUCCESS(10093, "已开户"), FAILURE(10094, "未开户");
        private Integer value;
        private String remark;

        OpenAccountStatus(Integer value, String remark) {
            this.value = value;
            this.remark = remark;
        }

        public Integer getValue() {
            return value;
        }

        public String getRemark() {
            return remark;
        }
    }
}
