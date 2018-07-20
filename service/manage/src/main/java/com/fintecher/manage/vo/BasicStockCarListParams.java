package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ZhangYaJun
 * @Title: BasicStockCarListParams
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/7/4 0004下午 18:22
 */
@Data
@ApiModel(value = "库存车辆列表")
public class BasicStockCarListParams {


   @ApiModelProperty(value = "库存id")
   private Long id;

   @ApiModelProperty(value = "供应商id")
   private Long supplierId;

   @ApiModelProperty(value = "机构id")
   private Long orgId;

   @ApiModelProperty(value = "订单id")
   private Long orderId;


   @ApiModelProperty(value = "车型id")
   @Column(name = "model_id")
   private Long modelId;

   @ApiModelProperty(value = "车架号")
   private String stockCarNo;

   @ApiModelProperty(value = "发动机号")
   private String stockEngineNo;

   @ApiModelProperty(value = "颜色")
   private String stockCarColor;


   @ApiModelProperty(value = "库存状态", notes = "数据字典项code")
   private Integer stockStatus;


   @ApiModelProperty(value = "是否供应商放款")
   private Integer hasSupplierLoan;

   @ApiModelProperty(value = "采购价格")
   private BigDecimal stockPrice;


   @ApiModelProperty(value = "入库时间")
   private Date stockInDate;


   @ApiModelProperty(value = "出库时间")
   private Date stockOutDate;

   @ApiModelProperty(value = "品牌描述")
   private String remark;

   @ApiModelProperty(value = "操作人")
   private Long operator;

   @ApiModelProperty(value = "操作时间")
   private Date operatorTime;

   @ApiModelProperty(value = "供应商名称")
   private String supplierName;


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
