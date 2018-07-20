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

/**
 * @author ZhangYaJun
 * @Title: BasicCustomerIntention
 * @ProjectName xcarloan
 * @Description: TODO
 * @date 2018/6/19 0019下午 16:45
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@ApiModel("意向记录")
@Table(name = "basic_customer_intention")
public class BasicCustomerIntention extends BaseEntity {

   @ApiModelProperty(value = "订单id")
   @Column(name = "order_id")
   private Long orderId;

   @ApiModelProperty(value = "客户id")
   @Column(name = "customer_id")
   private Long customerId;

   @ApiModelProperty(value = "意向类型")
   @Column(name = "intention_type")
   private Integer intentionType;

   @ApiModelProperty(value = "意向状态")
   @Column(name = "intention_status")
   private Integer intentionStatus;

   @ApiModelProperty(value = "意向等级")
   @Column(name = "intention_level")
   private Double intentionLevel;


   @ApiModelProperty(value = "车型描述", notes = "车型描述")
   @Column(name = "remark")
   private String remark;

   @ApiModelProperty(value = "操作人", notes = "操作人")
   @Column(name = "operator")
   private Long operator;

   @ApiModelProperty(value = "操作时间")
   @Column(name = "operator_time")
   private Date operatorTime;

   @ApiModelProperty(value = "是否最后一次跟进记录")
   private Integer isLastIntention;


   public enum IntentionStatus {
      BASIC_INTENTION_STATUS_WGJ("未跟进", 10065),
      BASIC_INTENTION_STATUS_YGJ("已跟进", 10066),
      BASIC_INTENTION_STATUS_YXD("已下单", 10067),
      BASIC_INTENTION_STATUS_YWC("已完成", 10068),
      BASIC_INTENTION_STATUS_YGQ("已过期", 10069);


      private String name;
      private Integer value;

      IntentionStatus(String name, Integer value) {
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

    /**
     * 客户意向类型
     */
   public enum IntentionType {
      INTENTION_TYPE_RZZR("融资租赁", 10049),
      INTENTION_TYPE_DYDK("抵押贷款", 10050);


      private String name;
      private Integer value;

      IntentionType(String name, Integer value) {
         this.value = value;
         this.name = name;
      }

      public Integer getValue() {
         return value;
      }

      public String getName(String name) {
         return name;
      }

        /**
         * 判断参数合法性
         */
        public static boolean isValidValue(Integer value) {
            for (IntentionType type : IntentionType.values()) {
                if (Objects.equals(type.getValue(), value)) {
                    return true;
                }
            }
            return false;
        }
   }

   public enum IsLastIntention{
      TRUE("是",10002),
      FALSE("否",10003);
      private Integer value;
      private String remark;
      IsLastIntention(String remark,Integer value){
         this.remark = remark;
         this.value = value;
      }
      public String getRemark(){
         return remark;
      }
      public Integer getValue(){
         return value;
      }

   }
}
