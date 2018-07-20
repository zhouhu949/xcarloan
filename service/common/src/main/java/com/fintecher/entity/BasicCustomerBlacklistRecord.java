package com.fintecher.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/22 19:52
 * @Description:
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "客户黑白灰名单")
@Table(name = "basic_customer_blacklist_record")
public class BasicCustomerBlacklistRecord extends BaseEntity{
    @ApiModelProperty(value = "客户id")
    private Long customerId;
    @ApiModelProperty(value = "开始时间")
    private Date beginTime;
    @ApiModelProperty(value = "结束时间")
    private Date endTime;
    @ApiModelProperty(value = "名单类型")
    private Integer blacklistType;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "操作人")
    private Long operator;
    @ApiModelProperty(value = "操作时间")
    private Date operatorTime;


    /**
     * 名单类型
     */
    public enum BlacklistType {
        BLACK(10146, "黑名单"),
        WHITE(10147, "白名单"),
        GRAY(10148, "灰名单");

        private Integer value;
        private String remark;

        BlacklistType(Integer value, String remark) {
            this.value = value;
            this.remark = remark;
        }

        public Integer getValue() {
            return value;
        }

        /**
         * 判断参数合法性
         */
        public static boolean isValidValue(Integer value) {
            for (BlacklistType type : BlacklistType.values()) {
                if (Objects.equals(type.getValue(), value)) {
                    return true;
                }
            }
            return false;
        }
    }

}
