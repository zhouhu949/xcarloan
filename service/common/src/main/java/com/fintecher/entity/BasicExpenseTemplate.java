package com.fintecher.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "费用项模板实体类")
@EqualsAndHashCode(callSuper = false)
@Table(name="basic_expense_template")
public class BasicExpenseTemplate extends  BaseEntity{

    @ApiModelProperty(value = "费用项名称", notes = "费用项名称")
    @Column(name = "expense_template_name")
    private String expenseTemplateName;

    @ApiModelProperty(value = "费用项编码", notes = "费用项编码")
    @Column(name = "expense_template_code")
    private String expenseTemplateCode;

    @ApiModelProperty(value = "备注", notes = "备注")
    @Column(name = "remark")
    private String remark;

    @ApiModelProperty(value = "操作人", notes = "操作人")
    @Column(name = "operator")
    private Long operator;

    @ApiModelProperty(value = "操作时间", notes = "操作时间")
    @Column(name = "operator_time")
    private Date operatorTime;
}
