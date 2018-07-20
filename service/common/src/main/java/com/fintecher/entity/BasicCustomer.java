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
 * @Title: BasicCustomer
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/19 0019下午 13:52
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@ApiModel("客户信息")
@Table(name = "basic_customer")
public class   BasicCustomer extends BaseEntity {

   @ApiModelProperty(value = "组织id")
   @Column(name = "org_id")
   private Long orgId;

   @ApiModelProperty(value = "客户编号")
   @Column(name = "customer_code")
   private String customerCode;

   @ApiModelProperty(value = "客户姓名")
   @Column(name = "customer_name")
   private String customerName;

   @ApiModelProperty(value = "客户状态")
   @Column(name = "customer_status")
   private Integer customerStatus;

   @ApiModelProperty(value = "客户性别")
   @Column(name = "customer_sex")
   private Integer customerSex;

   @ApiModelProperty(value = "出生时间")
   @Column(name = "birth_time")
   private Date birthTime;

   @ApiModelProperty(value = "qq")
   @Column(name = "qq")
   private String qq;

   @ApiModelProperty(value = "微信")
   @Column(name = "wechat")
   private String wechat;

   @ApiModelProperty(value = "facebook")
   @Column(name = "facebook")
   private String facebook;



   @ApiModelProperty(value = "邮箱")
   @Column(name = "email")
   private String email;

   @ApiModelProperty(value = "教育程度")
   @Column(name = "education")
   private Integer education;


   @ApiModelProperty(value = "毕业院校")
   @Column(name = "school")
   private String school;

   @ApiModelProperty(value = "婚姻状况")
   @Column(name = "marital")
   private Integer marital;

   @ApiModelProperty(value = "身份证")
   @Column(name = "id_card")
   private String idCard;

   @ApiModelProperty(value = "发证机关")
   @Column(name = "customer_issuer")
   private String customerIssuer;

   @ApiModelProperty(value = "身份证地址")
   @Column(name = "id_card_address")
   private String idCardAddress;

   @ApiModelProperty(value = "身份证地址门牌号")
   @Column(name = "id_card_address_detail")
   private String idCardAddressDetail;


   @ApiModelProperty(value = "身份证有效期类型")
   @Column(name = "id_card_validity_period_type")
   private Integer idCardValidityPeriodType;

   @ApiModelProperty(value = "身份证有效期区间")
   @Column(name = "id_card_validity_period_section")
   private String idCardValidityPeriodSection;

   @ApiModelProperty(value = "现居住地址")
   @Column(name = "local_home_addr")
   private String localHomeAddr;

   @ApiModelProperty(value = "现居住地址门牌号")
   @Column(name = "local_home_addr_detail")
   private String localHomeAddrDetail;

   @ApiModelProperty(value = "居住地家庭座机")
   @Column(name = "local_home_phone")
   private String localHomePhone;



   @ApiModelProperty(value = "是否接受勘查")
   @Column(name = "house_prospecting")
   private Integer houseProspecting;


   @ApiModelProperty(value = "客户是否删除")
   @Column(name = "is_delete")
   private Integer isDelete;



   @ApiModelProperty(value = "邮政编码")
   @Column(name = "postal_code")
   private String postalCode;

   @ApiModelProperty(value = "登录App密码")
   @Column(name = "personal_password")
   private String personalPassword;

   @ApiModelProperty(value = "客户上传头像")
   @Column(name = "personal_url")
   private String personalUrl;

   @ApiModelProperty(value = "民族")
   @Column(name = "nation")
   private String nation;

   @ApiModelProperty(value = "健康状况")
   @Column(name = "health_status")
   private Integer healthStatus;

   @ApiModelProperty(value = "居住状况")
   @Column(name = "home_status")
   private Integer homeStatus;

   @ApiModelProperty(value = "通讯地址")
   @Column(name = "message_addr")
   private String messageAddr;

   @ApiModelProperty(value = "客户电话")
   @Column(name = "customer_phone")
   private String customerPhone;

   @ApiModelProperty(value = "备注")
   @Column(name = "remark")
   private String remark;

   @ApiModelProperty(value = "创建时间")
   @Column(name = "create_time")
   private Date createTime;

   @ApiModelProperty(value = "操作人")
   @Column(name = "operator")
   private Long operator;

   @ApiModelProperty(value = "操作时间")
   @Column(name = "operator_time")
   private Date operatorTime;


   @ApiModelProperty(value = "开户状态")
   @Column(name = "account_status")
   private Integer accountStatus;


    /**
     * 婚姻状况
     */
   public enum Marital {
      MARITAL_MARRIED("已婚", 10090),
      MARITAL_UNMARRIED("未婚", 10089),
      MARITAL_DIVOECE("离婚", 10091),
      MARITAL_DEATH("丧偶", 10092);


      private String name;
      private Integer value;

      Marital(String name, Integer value) {
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
            for (Marital marital : Marital.values()) {
                if (Objects.equals(marital.getValue(), value)) {
                    return true;
                }
            }
            return false;
        }
    }


    /**
     * 教育程度
     */
    public enum Education {
        EDUCATION_MORE_DR("博士及以上", 10083),
        EDUCATION_DEGREE("硕士", 10084),
        EDUCATION_COURSE("本科", 10085),
        EDUCATION_COLLEGE("大专", 10086),
        EDUCATION_HIGE("高中", 10087),
        EDUCATION_TECHNICAL("中专/技校", 10088);


        private String name;
        private Integer value;

        Education(String name, Integer value) {
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
            for (Education education : Education.values()) {
                if (Objects.equals(education.getValue(), value)) {
                    return true;
                }
            }
            return false;
        }
    }


    public enum OpenAccountStatus {
       SUCCESS(10093, "已开户"),
       FAILURE(10094, "未开户");
        private Integer value;
        private String remark;

        OpenAccountStatus(Integer value, String remark) {
            this.value = value;
            this.remark = remark;
        }

        public Integer getValue() {
            return value;
        }


    }

    /**
     * 客户状态
     */
   public enum CustomerStatus {
      CUSTOMER_STATUS_YXKH("意向客户", 10077),
      CUSTOMER_STATUS_ZSKH("正式客户", 10078),
      CUSTOMER_STATUS_LSKH("历史订单客户", 10079),
      CUSTOMER_STATUS_HMD("黑名单客户", 10080),
      CUSTOMER_STATUS_HND("灰名单客户", 10081),
      CUSTOMER_STATUS_BMD("白名单客户", 10082);


      private Integer value;
      private String name;

      CustomerStatus(String name, Integer value) {
         this.value = value;
         this.name = name;
      }

      public Integer getValue() {
         return value;
      }

    }


    /**
     * 健康状况
     */
    public enum HealthStatus {
        JK("健康", 10154),
        YB("一般", 10155);


        private Integer value;
        private String name;

        HealthStatus(String name, Integer value) {
            this.value = value;
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        /**
         * 判断参数合法性
         */
        public static boolean isValidValue(Integer value) {
            for (HealthStatus status : HealthStatus.values()) {
                if (Objects.equals(status.getValue(), value)) {
                    return true;
                }
            }
            return false;
        }

    }

    /**
     * 居住状况
     */
    public enum HomeStatus {
        JK("全款", 10156),
        YB("按揭", 10157);


        private Integer value;
        private String name;

        HomeStatus(String name, Integer value) {
            this.value = value;
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        /**
         * 判断参数合法性
         */
        public static boolean isValidValue(Integer value) {
            for (HomeStatus status : HomeStatus.values()) {
                if (Objects.equals(status.getValue(), value)) {
                    return true;
                }
            }
            return false;
        }

    }


}
