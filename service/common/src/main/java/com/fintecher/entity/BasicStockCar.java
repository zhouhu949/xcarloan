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
 * @author ZhangYaJun
 * @Title: BasicStockCar
 * @ProjectName xcarloan
 * @Description: T
 * @date 2018/6/14 0014下午 16:40
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "库存实体类")
@Entity
@Table(name = "basic_stock_car")
public class BasicStockCar extends BaseEntity {


    @ApiModelProperty(value = "供应商id")
    @Column(name = "supplier_id")
    private Long supplierId;

    @ApiModelProperty(value = "机构id")
    @Column(name = "org_id")
    private Long orgId;

    @ApiModelProperty(value = "订单id")
    @Column(name = "order_id")
    private Long orderId;


    @ApiModelProperty(value = "车型id")
    @Column(name = "model_id")
    private Long modelId;

    @ApiModelProperty(value = "车架号")
    @Column(name = "stock_car_no")
    private String stockCarNo;

    @ApiModelProperty(value = "发动机号")
    @Column(name = "stock_engine_no")
    private String stockEngineNo;

    @ApiModelProperty(value = "颜色")
    @Column(name = "stock_car_color")
    private String stockCarColor;


    @ApiModelProperty(value = "库存状态", notes = "数据字典项code")
    @Column(name = "stock_status")
    private Integer stockStatus;


    @ApiModelProperty(value = "是否供应商放款")
    @Column(name = "has_supplier_loan")
    private Integer hasSupplierLoan;

    @ApiModelProperty(value = "采购价格")
    @Column(name = "stock_price")
    private BigDecimal stockPrice;


    @ApiModelProperty(value = "入库时间")
    @Column(name = "stock_in_date")
    private Date stockInDate;


    @ApiModelProperty(value = "出库时间")
    @Column(name = "stock_out_date")
    private Date stockOutDate;

    @ApiModelProperty(value = "品牌描述")
    @Column(name = "remark")
    private String remark;

    @ApiModelProperty(value = "操作人")
    @Column(name = "operator")
    private Long operator;

    @ApiModelProperty(value = "操作时间")
    @Column(name = "operator_time")
    private Date operatorTime;


   /**
     * 数据字典类型枚举
     */
    public enum StockStatus {
        BASIC_STOCKSTATUS_DCG("待采购", 10123),
        BASIC_STOCKSTATUS_ZBZ("整备中", 10046),
        BASIC_STOCKSTATUS_ZBWC("整备完成", 10047),
        BASIC_STOCKSTATUS_YTC("已提车", 10048);
        private String name;
        private Integer value;

        StockStatus(String name, Integer value) {
            this.value = value;
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public String getName(String name) {
            return name;
        }
    }

    public enum HasSupplierLoan {
        BASIC_STOCKSTATUS_YES("是", 10002),
        BASIC_STOCKSTATUS_NO("否", 10003);

        private String name;
        private Integer value;

        HasSupplierLoan(String name, Integer value) {
            this.value = value;
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public String getName(String name) {
            return name;
        }
    }

}
