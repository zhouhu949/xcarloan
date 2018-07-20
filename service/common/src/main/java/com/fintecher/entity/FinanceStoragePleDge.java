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
 * @Title: FinanceStoragePleDge
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/23 0023下午 14:19
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "质押记录")
@Entity
@Table(name = "finance_storage_pledge")
public class FinanceStoragePleDge extends BaseEntity {

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



   @ApiModelProperty(value = "质押地点")
   @Column(name = "pledge_place")
   private String pledgePlace;


   @ApiModelProperty(value = "质押位置")
   @Column(name = "pledge_position")
   private String pledgePosition;


   @ApiModelProperty(value = "质押号")
   @Column(name = "pledge_no")
   private String pledgeNo;


   @ApiModelProperty(value = "操作人")
   @Column(name = "operator")
   private Long operator;

   @ApiModelProperty(value = "操作时间")
   @Column(name = "operator_time")
   private Date operatorTime;

   @ApiModelProperty(value = "备注")
   @Column(name = "remark")
   private String remark;


}
