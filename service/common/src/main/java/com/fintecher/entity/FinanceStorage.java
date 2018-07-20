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
import java.util.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "押品出入库记录")
@Entity
@Table(name = "finance_storage")
public class FinanceStorage extends BaseEntity {
    @ApiModelProperty("订单ID")
    @Column(name = "order_id")
    private Long orderId;
    @ApiModelProperty("车辆ID")
    @Column(name = "car_id")
    private Long carId;
    @ApiModelProperty("机构ID")
    @Column(name = "org_id")
    private Long orgId;
    @ApiModelProperty("入库日期")
    @Column(name = "stock_in_date")
    private Date stockInDate;
    @ApiModelProperty("出库日期")
    @Column(name = "stock_out_date")
    private Date stockOutDate;
    @ApiModelProperty("押品状态")
    @Column(name = "mortgage_status")
    private Integer mortgageStatus;
    @ApiModelProperty("操作人")
    @Column(name = "operator")
    private Long operator;
    @ApiModelProperty("备注")
    @Column(name = "remark")
    private String remark;
    @ApiModelProperty("操作日期")
    @Column(name = "operator_time")
    private Date operatorTime;
    @ApiModelProperty("评估id")
    private Long assessmentId;


    /**
     * 押品状态
     */
    public enum MortgageStatus {
        WAIT(10139, "未入库"),
        IN(10140, "已入库"),
        OUT(10141, "已出库");

        private Integer value;
        private String remark;

        MortgageStatus(Integer value, String remark) {
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
            for (MortgageStatus type : MortgageStatus.values()) {
                if (Objects.equals(type.getValue(), value)) {
                    return true;
                }
            }
            return false;
        }
    }
}
