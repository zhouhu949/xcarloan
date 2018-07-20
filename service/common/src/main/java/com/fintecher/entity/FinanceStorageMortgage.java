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

/**
 * @author ZhangYaJun
 * @Title: FinanceStorageMortgage
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/23 0023下午 13:49
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "抵押记录")
@Entity
@Table(name = "finance_storage_mortgage")
public class FinanceStorageMortgage extends BaseEntity {

   @ApiModelProperty(value = "订单id")
   @Column(name = "order_id")
   private Long orderId;


   @ApiModelProperty(value = "车辆id")
   @Column(name = "car_id")
   private Long carId;

   @ApiModelProperty(value = "机构ID")
   @Column(name = "org_id")
   private Long orgId;


   @ApiModelProperty(value = "抵押记录ID")
   @Column(name = "mortgage_record_id")
   private Long mortgageRecordId;

   @ApiModelProperty(value = "入库日期")
   @Column(name = "stock_in_date")
   private Date stockInDate;

   @ApiModelProperty(value = "出库日期")
   @Column(name = "stock_out_date")
   private Date stockOutDate;


   @ApiModelProperty(value = "押品状态")
   @Column(name = "mortgage_status")
   private Integer mortgageStatus;




   @ApiModelProperty(value = "抵押号")
   @Column(name = "mortgage_no")
   private String mortgageNo;


   @ApiModelProperty(value = "GPS安装状态")
   @Column(name = "gps_status")
   private Integer gpsStatus;


   @ApiModelProperty(value = "设备号")
   @Column(name = "gps_no")
   private String gpsNo;


   @ApiModelProperty(value = "设备厂家")
   @Column(name = "gps_manufactor")
   private Integer gpsManufactor;

   @ApiModelProperty(value = "操作人")
   @Column(name = "operator")
   private Long operator;

   @ApiModelProperty(value = "操作时间")
   @Column(name = "operator_time")
   private Date operatorTime;

   @ApiModelProperty(value = "备注")
   @Column(name = "remark")
   private String remark;

   public enum GpsStatus {
      YAZ("已安装", 10150),
      WAZ("未安装", 10151);


      private Integer value;
      private String name;

      GpsStatus(String name, Integer value) {
         this.value = value;
         this.name = name;
      }

      public Integer getValue() {
         return value;
      }

   }


}
