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

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@ApiModel("订单车车辆实体")
@Table(name = "basic_order_car")
public class BasicOrderCar extends BaseEntity {
    @ApiModelProperty(value = "车型ID")
    @Column(name = "model_id")
    private Long modelId;
    @ApiModelProperty(value = "供应商ID")
    @Column(name = "supplier_id")
    private Long supplierId;
    @ApiModelProperty("订单ID")
    @Column(name = "order_id")
    private Long orderId;
    @ApiModelProperty(value = "库存车辆ID")
    @Column(name = "stock_id")
    private Long stockId;
    @ApiModelProperty(value = "车型名称")
    @Column(name = "order_car_name")
    private String orderCarName;
    @ApiModelProperty(value = "车型描述")
    @Column(name = "order_car_desc")
    private String orderCarDesc;
    @ApiModelProperty(value = "车型颜色")
    @Column(name = "order_car_color")
    private String orderCarColor;
    @ApiModelProperty(value = "车型参数描述")
    @Column(name = "order_car_param_desc")
    private String orderCarParamDesc;
    @ApiModelProperty(value = "是否外采")
    @Column(name = "is_supplier")
    private Integer isSupplier;
    @ApiModelProperty(value = "是否供应商放款")
    @Column(name = "has_supplier_loan")
    private Integer hasSupplierLoan;
    @ApiModelProperty(value = "备注")
    @Column(name = "remark")
    private String remark;
    @ApiModelProperty("操作人")
    @Column(name = "operator")
    private Long operator;
    @ApiModelProperty("操作日期")
    @Column(name = "operator_time")
    private Date operatorTime;

    public enum HasSupplierLoan{
        SUPPLIER_LOAN_YES(10002,"是"),
        SUPPLIER_LOAN_NO(10003,"否");
        private Integer values;
        private String name;
     HasSupplierLoan(Integer values,String name){
        this.values=values;
        this.name=name;
    }
       public Integer getValues(){
         return values;
       }

    }


}
