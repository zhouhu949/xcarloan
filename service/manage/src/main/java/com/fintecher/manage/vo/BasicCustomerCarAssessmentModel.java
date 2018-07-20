package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/15 11:08
 * @Description:
 */
@Data
public class BasicCustomerCarAssessmentModel {

    @ApiModelProperty(value = "申请评估日期")
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private Date assessmentApplyDate;
    @ApiModelProperty(value = "评估日期")
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private Date assessmentDate;
    @ApiModelProperty(value = "评估结果")
    private String assessmentResult;
    @ApiModelProperty(value = "评估状态")
    private Integer assessmentStatus;
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
    @ApiModelProperty(value = "车辆id")
    private Long carId;
    @ApiModelProperty(value = "客户id")
    private Long customerId;
    @ApiModelProperty(value = "评估id")
    private Long assessmentId;

    @ApiModelProperty(value = "车辆属性与车辆属性值")
    private List<CarAttributeModel> carAttributeModelList;

}
