package com.fintecher.manage.vo;

import com.fintecher.entity.BasicCarAttrvalue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/27 14:54
 * @Description:
 */
@Data
@ApiModel("评估报告详情")
public class CustomerAssessmentInfoModel {
    @ApiModelProperty(value = "客户编号")
    private String customerCode;

    @ApiModelProperty(value = "客户姓名")
    private String customerName;

    @ApiModelProperty(value = "客户状态")
    private Integer customerStatus;

    @ApiModelProperty(value = "客户性别")
    private Integer customerSex;
    @ApiModelProperty(value = "车牌号")
    private String carNo;
    @ApiModelProperty(value = "购车价格")
    private BigDecimal carPrice;
    @ApiModelProperty(value = "车辆型号")
    private Integer carType;
    @ApiModelProperty(value = "是否二手车")
    private Integer isSecondHand;
    @ApiModelProperty(value = "初次登记时间")
    private Date registerTime;
    @ApiModelProperty(value = "抵押登记次数")
    private Integer mortgageNum;
    @ApiModelProperty(value = "购买方式")
    private Integer buyType;
    @ApiModelProperty(value = "是否贷款已还请")
    private Integer isLoanFinished;
    @ApiModelProperty(value = "抵押状态")
    private Integer carStatus;
    @ApiModelProperty(value = "出厂日期")
    private Date factoryTime;
    @ApiModelProperty(value = "行驶里程")
    private Long mileAge;
    @ApiModelProperty(value = "行驶证号")
    private String drivingNo;
    @ApiModelProperty(value = "过户次数")
    private Integer transferNo;
    @ApiModelProperty(value = "车辆用途")
    private Integer carPurpose;
    @ApiModelProperty(value = "变速箱形式")
    private Integer transmission;
    @ApiModelProperty(value = "驱动形式")
    private Integer driver;
    @ApiModelProperty(value = "排量")
    private String displacement;
    @ApiModelProperty(value = "车况")
    private Integer carSituation;
    @ApiModelProperty(value = "估价")
    private BigDecimal evaluation;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "评估id")
    private Long assessmentId;
    @ApiModelProperty(value = "车辆属性与车辆属性值")
    private List<CustomerAssessmentInfo> carAttributeModels;

}
