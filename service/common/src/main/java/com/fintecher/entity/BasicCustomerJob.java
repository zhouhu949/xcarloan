package com.fintecher.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/14 19:06
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@ApiModel("客户职业")
@Table(name = "basic_customer_job")
public class BasicCustomerJob extends BaseEntity {

    @ApiModelProperty("客户ID")
    @Column(name = "customer_id")
    private Long customerId;

    @ApiModelProperty("职业类型")
    @Column(name = "job_type")
    private Integer jobType;

    @ApiModelProperty("单位名称")
    @Column(name = "company_name")
    private String companyName;

    @ApiModelProperty("单位性质")
    @Column(name = "company_nature")
    private Integer companyNature;

    @ApiModelProperty("职级")
    @Column(name = "rank")
    private String rank;

    @ApiModelProperty("单位地址")
    @Column(name = "company_address")
    private String companyAddress;

    @ApiModelProperty("单位地址详细")
    @Column(name = "company_address_detail")
    private String companyAddressDetail;

    @ApiModelProperty("单位固定电话")
    @Column(name = "company_phone")
    private String companyPhone;

    @ApiModelProperty("部门")
    @Column(name = "department")
    private String department;

    @ApiModelProperty("职务")
    @Column(name = "duty")
    private String duty;

    @ApiModelProperty("何时进入公司")
    @Column(name = "access_company_time")
    private Date accessCompanyTime;

    @ApiModelProperty("基本月薪")
    @Column(name = "basic_salary")
    private BigDecimal basicSalary;

    @ApiModelProperty("每月发薪日")
    @Column(name = "pay_day")
    private String payDay;

    @ApiModelProperty("发薪方式")
    @Column(name = "pay_way")
    private String payWay;

    @ApiModelProperty("年收入")
    @Column(name = "yearly_salaries")
    private BigDecimal yearlySalaries;

    @ApiModelProperty("每月其它收入")
    @Column(name = "month_other_income")
    private BigDecimal monthOtherIncome;

    @ApiModelProperty("其它收入来源")
    @Column(name = "other_income_source")
    private String otherIncomeSource;

    @ApiModelProperty("身份")
    @Column(name = "identity")
    private String identity;

    @ApiModelProperty("股占比")
    @Column(name = "stock_scale")
    private BigDecimal stockScale;

    @ApiModelProperty("企业经营年限")
    @Column(name = "enterprise_manage_years")
    private String enterpriseManageYears;

    @ApiModelProperty("经营归属地")
    @Column(name = "enterprise_manage_belong")
    private String enterpriseManageBelong;

    @ApiModelProperty("员工人数")
    @Column(name = "employees_number")
    private Integer employeesNumber;

    @ApiModelProperty("注册资本")
    @Column(name = "registered_capital")
    private BigDecimal registeredCapital;

    @ApiModelProperty("所属行业")
    @Column(name = "industry")
    private Integer industry;

    @ApiModelProperty("过去一年营业收入")
    @Column(name = "pastyear_income")
    private Integer pastyearIncome;

    @ApiModelProperty("过去一年利润")
    @Column(name = "pastyear_profit")
    private Integer pastyearProfit;

    @ApiModelProperty("操作员")
    @Column(name = "operator")
    private Integer operator;

    @ApiModelProperty("操作时间")
    @Column(name = "operate_time")
    private Date operateTime;

    @ApiModelProperty("单位省份")
    @Column(name = "province")
    private Integer province;

    @ApiModelProperty("单位城市")
    @Column(name = "city")
    private Integer city;

    @ApiModelProperty("其它行业")
    @Column(name = "other_industry")
    private Integer otherIndustry;
}
