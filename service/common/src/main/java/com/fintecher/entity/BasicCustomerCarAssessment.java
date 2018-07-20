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
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/14 18:51
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@ApiModel("车辆评估")
@Table(name = "basic_customer_car_assessment")
public class BasicCustomerCarAssessment extends BaseEntity{
    @ApiModelProperty(value = "申请评估日期")
    @Column(name = "assessment_apply_date")
    private Date assessmentApplyDate;

    @ApiModelProperty(value = "评估日期")
    @Column(name = "assessment_date")
    private Date assessmentDate;

    @ApiModelProperty(value = "评估结果")
    @Column(name = "assessment_result")
    private String assessmentResult;

    @ApiModelProperty(value = "评估状态")
    @Column(name = "assessment_status")
    private Integer assessmentStatus;

    @ApiModelProperty(value = "备注")
    @Column(name = "remark")
    private String remark;

    @ApiModelProperty(value = "操作人")
    @Column(name = "operator")
    private Long operator;

    @ApiModelProperty(value = "操作日期")
    @Column(name = "operator_time")
    private Date operatorTime;

    @ApiModelProperty(value = "车产id")
    @Column(name = "car_id")
    private Long carId;

    @ApiModelProperty(value = "评估编号")
    @Column(name = "assessment_no")
    private String assessmentNo;

    public enum AssessmentStatus {

        UNDER_REVIEW("待评估", 10062),
        PASS_THROUGH("已评估", 10061),
        ;
        private Integer value;

        private String name;

        AssessmentStatus(String name, Integer value) {
            this.value = value;
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * 判断参数合法性
     */
    public static boolean isValidValue(Integer value) {
        for (AssessmentStatus assessmentStatus : AssessmentStatus.values()) {
            if (Objects.equals(assessmentStatus.getValue(), value)) {
                return true;
            }
        }
        return false;
    }
}
