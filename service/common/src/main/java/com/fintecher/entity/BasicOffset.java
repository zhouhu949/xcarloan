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
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "冲抵策略实体类")
@EqualsAndHashCode(callSuper = false)
@Table(name="basic_offset")
public class BasicOffset extends BaseEntity{

    @ApiModelProperty(value = "机构ID", notes = "机构ID")
    @Column(name = "org_id")
    private Long orgId ;

    @ApiModelProperty(value = "冲抵策略名称", notes = "冲抵策略名称")
    @Column(name = "offset_name")
    private String offsetName;

    @ApiModelProperty(value = "冲抵类型", notes = "冲抵类型")
    @Column(name = "offset_type")
    private Integer offsetType;

    @ApiModelProperty("发布状态：10207-未发布，10208-已发布")
    @Column(name = "offset_status")
    private Integer offsetStatus;

    @ApiModelProperty(value = "备注", notes = "备注")
    @Column(name = "remark")
    private String remark ;

    @ApiModelProperty(value = "操作人", notes = "操作人")
    @Column(name = "operator")
    private Long operator ;

    @ApiModelProperty(value = "操作时间", notes = "操作时间")
    @Column(name = "operator_time")
    private Date operatorTime;

    /**
     * 冲抵类型
     */
    public enum OffsetType{
        SETTLE(10075, "提前结清"),
        STAGE(10076, "分期还款");

        private Integer value;
        private String remark;

        OffsetType(Integer value, String remark) {
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
            for (OffsetType type : OffsetType.values()) {
                if (Objects.equals(type.getValue(), value)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 发布状态
     */
    public enum OffsetStatus {
        UNPUBLISHED(10207, "未发布"),
        PUBLISH(10208, "已发布");

        private Integer value;
        private String remark;

        OffsetStatus(Integer value, String remark) {
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
            for (OffsetStatus type : OffsetStatus.values()) {
                if (Objects.equals(type.getValue(), value)) {
                    return true;
                }
            }
            return false;
        }
    }
}
