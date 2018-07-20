package com.fintecher.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * @System: 车贷金融
 * @Auther: zhangyudong
 * @Description: 费用项实体类
 * @Date:Created on 2018/6/14:27
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "费用项实体类")
@Table(name="basic_expense")
public class BasicExpense extends BaseEntity{


    @ApiModelProperty(value = "机构ID", notes = "机构ID")
    @Column(name = "org_id")
    private Long orgId;

    @ApiModelProperty(value = "费用项模板ID", notes = "费用项模板ID")
    @Column(name = "expense_template_id")
    private Long expenseTemplateId;

    @ApiModelProperty(value = "费用项名称", notes = "费用项名称")
    @Column(name = "expense_name")
    private String expenseName;

    @ApiModelProperty(value = "费用项编码", notes = "费用项编码")
    @Column(name = "expense_code")
    private String expenseCode;

    @ApiModelProperty(value = "是否系统费用项", notes = "是否系统费用项")
    @Column(name = "is_system")
    private Integer isSystem;

    @ApiModelProperty(value = "备注", notes = "备注")
    @Column(name = "remark")
    private String remark;

    @ApiModelProperty(value = "操作人", notes = "操作人")
    @Column(name = "operator")
    private Long operator;

    @ApiModelProperty(value = "操作日期", notes = "操作日期")
    @Column(name = "operator_time")
    private Date operatorTime;

    /**
     * 费用项模板
     */
    public enum ExpenseCode{
        /**
         * 利息
         */
        LX("lx"),
        /**
         * 押金
         */
        YJ("yj"),
        /**
         * GPS费用
         */
        GPSFY("gpsfy"),
        /**
         * 管理费
         */
        GLF("glf"),
        /**
         * 本金
         */
        BJ("bj"),
        /**
         * 罚息
         */
        FX("fx"),
        /**
         * 减免本金
         */
        JMBJ("jmbj"),
        /**
         * 减免利息
         */
        JMLX("jmlx"),
        /**
         * 减免罚息
         */
        JMFX("jmfx"),
        /**
         * 冻结罚息
         */
        DJFX("djfx"),
        /**
         * 罚金
         */
        FJ("fj"),
        /**
         * 提前结清手续费
         */
        TQJQSXF("tqjqsxf"),
        /**
         * 首付款
         */
        SFK("sfk");

        private String value;

        ExpenseCode(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static ExpenseCode value(String value) {
            if (StringUtils.isBlank(value)) {
                return null;
            }
            for(ExpenseCode s : values()) {    //values()方法返回enum实例的数组
                if(value.equals(s.getValue()))
                    return s;
            }
            return null;
        }
    }
}
