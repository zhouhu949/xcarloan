package com.fintecher.manage.vo;

import com.fintecher.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel("客户职业")
public class EditCustomerJobParams {

    @ApiModelProperty(value = "职业id")
    private Long id;
    @ApiModelProperty("客户ID")
    private Long customerId;

    @ApiModelProperty("职业类型")
    private Integer jobType;

    @ApiModelProperty("单位名称")
    private String companyName;

    @ApiModelProperty("单位性质")
    private Integer companyNature;

    @ApiModelProperty("职级")
    private String rank;

    @ApiModelProperty("单位地址")
    private String companyAddress;

    @ApiModelProperty("单位地址详细")
    private String companyAddressDetail;

    @ApiModelProperty("单位固定电话")
    private String companyPhone;

    @ApiModelProperty("部门")
    private String department;

    @ApiModelProperty("职务")
    private String duty;
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("何时进入公司")
    private Date accessCompanyTime;

    @ApiModelProperty("基本月薪")
    private BigDecimal basicSalary;

    @ApiModelProperty("每月发薪日")
    private String payDay;

    @ApiModelProperty("发薪方式")
    private String payWay;

    @ApiModelProperty("年收入")
    private BigDecimal yearlySalaries;

    @ApiModelProperty("每月其它收入")
    private BigDecimal monthOtherIncome;

    @ApiModelProperty("其它收入来源")
    private String otherIncomeSource;

    @ApiModelProperty("身份")
    private String identity;

    @ApiModelProperty("股占比")
    private BigDecimal stockScale;

    @ApiModelProperty("企业经营年限")
    private String enterpriseManageYears;

    @ApiModelProperty("经营归属地")
    private String enterpriseManageBelong;

    @ApiModelProperty("员工人数")
    private Integer employeesNumber;

    @ApiModelProperty("注册资本")
    private BigDecimal registeredCapital;

    @ApiModelProperty("所属行业")
    private Integer industry;

    @ApiModelProperty("过去一年营业收入")
    private Integer pastyearIncome;

    @ApiModelProperty("过去一年利润")
    private Integer pastyearProfit;

    @ApiModelProperty("操作员")
    private Integer operator;

    @ApiModelProperty("操作时间")
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private Date operateTime;

    @ApiModelProperty("单位省份")
    private Integer province;

    @ApiModelProperty("单位城市")
    private Integer city;

    @ApiModelProperty("其它行业")
    private Integer otherIndustry;
}
