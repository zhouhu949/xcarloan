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
 * @Title: BasicCustomerFollow
 * @ProjectName xcarloan
 * @Description: TODO
 * @date 2018/6/19 0019下午 17:44
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@ApiModel("跟进记录")
@Table(name = "basic_customer_follow")
public class BasicCustomerFollow extends BaseEntity {

   @ApiModelProperty(value = "用户主键")
   @Column(name = "user_id")
   private Long userId;

   @ApiModelProperty(value = "意向主键")
   @Column(name = "intention_id")
   private Long intentionId;

   @ApiModelProperty(value = "跟进方式")
   @Column(name = "follow_type")
   private Integer followType;

   @ApiModelProperty(value = "跟进结果")
   @Column(name = "follow_result")
   private Integer followResult;

   @ApiModelProperty(value = "车型描述", notes = "车型描述")
   @Column(name = "remark")
   private String remark;

   @ApiModelProperty(value = "操作人", notes = "操作人")
   @Column(name = "operator")
   private Long operator;

   @ApiModelProperty(value = "操作时间")
   @Column(name = "operator_time")
   private Date operatorTime;


   @ApiModelProperty(value = "跟进时间")
   @Column(name = "follow_date")
   private Date followDate;
   @ApiModelProperty(value = "是否最后一次跟进记录")
   private Integer isLastIntention;

   public enum FollowResult {
      BASIC_FOLLOWRESULT_JXGJ("继续跟进", 10070),
      BASIC_FOLLOWRESULT_BZGJ("不再跟进", 10071),
      BASIC_FOLLOWRESULT_GJZG("已预约成功", 10072);


      private String name;
      private Integer value;

      FollowResult(String name, Integer value) {
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
           for (FollowResult status : FollowResult.values()) {
               if (Objects.equals(status.getValue(), value)) {
                   return true;
               }
           }
           return false;
       }
   }

   public enum FollowType {
      BASIC_FOLLOWTYPE_PHONR("电话", 10073),
      BASIC_FOLLOWTYPE_FACETALL("到店面谈", 10131),
      BASIC_INTENTION_STATUS_EMAIL("邮件", 10074);


      private String name;
      private Integer value;

      FollowType(String name, Integer value) {
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
           for (FollowType status : FollowType.values()) {
               if (Objects.equals(status.getValue(), value)) {
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
