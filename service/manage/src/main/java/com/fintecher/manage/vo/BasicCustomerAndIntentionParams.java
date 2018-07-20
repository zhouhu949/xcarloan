package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ZhangYaJun
 * @Title: BasicCustomerParams
 * @ProjectName xcarloan
 * @Description: TODO
 * @date 2018/6/19 0019下午 15:15
 */
@Data
@ApiModel(value = "客户与意向客户信息")
public class BasicCustomerAndIntentionParams {


   @ApiModelProperty(value = "客户编号")
   private String customerCode;

   @ApiModelProperty(value = "客户姓名")
   private String customerName;

   @ApiModelProperty(value = "客户状态")
   private Integer customerStatus;

   @ApiModelProperty(value = "客户性别")
   private Integer customerSex;

   @ApiModelProperty(value = "出生时间")
   private String birthTime;

   @ApiModelProperty(value = "qq")
   private String qq;

   @ApiModelProperty(value = "微信")
   private String wechat;

   @ApiModelProperty(value = "facebook")
   private String facebook;


   @ApiModelProperty(value = "邮箱")
   private String email;


   @ApiModelProperty(value = "手机主号码")
   private String mobileMain;

   @ApiModelProperty(value = "手机次号码")
   private String mobileMinor;

   @ApiModelProperty(value = "教育程度")
   private Integer education;


   @ApiModelProperty(value = "毕业院校")
   private String school;

   @ApiModelProperty(value = "婚姻状况")
   private Integer marital;

   @ApiModelProperty(value = "身份证")
   private String idCard;

   @ApiModelProperty(value = "发证机关")
   private String customerIssuer;

   @ApiModelProperty(value = "身份证地址")
   private String idCardAddress;

   @ApiModelProperty(value = "身份证地址门牌号")
   private String idCardAddressDetail;


   @ApiModelProperty(value = "身份证有效期类型")
   private Integer idCardValidityPeriodType;

   @ApiModelProperty(value = "身份证有效期区间")
   private String idCardValidityPeriodSection;

   @ApiModelProperty(value = "现居住地址")
   private Integer localHomeAddr;

   @ApiModelProperty(value = "现居住地址门牌号")
   private String localHomeAddrDetail;

   @ApiModelProperty(value = "居住地家庭座机")
   private String localHomePhone;

   @ApiModelProperty(value = "本市生活时长")
   private String cityLiveTime;

   @ApiModelProperty(value = "现居住地生活时长")
   private String localLiveTime;

   @ApiModelProperty(value = "现居住地房产归属")
   private Integer localLiveHouseOwner;

   @ApiModelProperty(value = "本市自有房产状况")
   private Integer cityOwnhouseCondition;

   @ApiModelProperty(value = "现租房租金")
   private String localLiveHouseMoney;

   @ApiModelProperty(value = "本市自有房产状况其他")
   private String cityOwnhouseOther;

   @ApiModelProperty(value = "电费账号")
   private String electricityAccount;

   @ApiModelProperty(value = "电费密码")
   private String electricityPassword;



   @ApiModelProperty(value = "本市自有房产地址类型")
   private Integer cityOwnhouseAddressType;

   @ApiModelProperty(value = "本市自有房产地址")
   private Integer cityOwnhouseAddress;

   @ApiModelProperty(value = "本市自有房产地址门牌号")
   private String cityOwnhouseAddressDetail;

   @ApiModelProperty(value = "是否接受勘查")
   private Integer houseProspecting;


   @ApiModelProperty(value = "客户是否删除")
   private Integer isDelete;


   @ApiModelProperty(value = "公司编码")
   private String companyCode;


   @ApiModelProperty(value = "证件类型")
   private Integer certificateType;

   @ApiModelProperty(value = "证件号码  ")
   private String certificateNumber;

   @ApiModelProperty(value = "邮政编码")
   private String postalCode;

   @ApiModelProperty(value = "登录App密码")
   private String personalPassword;

   @ApiModelProperty(value = "客户上传头像")
   private String personalUrl;

   @ApiModelProperty(value = "身份证省份")
   private Integer province;

   @ApiModelProperty(value = "")
   private String nation;

   @ApiModelProperty(value = "")
   private Integer healthStatus;

   @ApiModelProperty(value = "")
   private Integer homeStatus;

   @ApiModelProperty(value = "")
   private String messageAddr;

   @ApiModelProperty(value = "城市")
   private Integer city;

   @ApiModelProperty(value = "现居住省市")
   private Integer province1;

   @ApiModelProperty(value = "现居住城市")
   private Integer city1;

   @ApiModelProperty(value = "本市房产省市")
   private Integer province2;

   @ApiModelProperty(value = "本市房产乘市")
   private Integer city2;

   @ApiModelProperty(value = "意向客户居住地址")
   private String intentionHomeAddr;

   @ApiModelProperty(value = "客户电话")
   private String customerPhone;

   @ApiModelProperty(value = "备注")
   private String remark;


   @ApiModelProperty(value = "订单id")
   private Long orderId;

   @ApiModelProperty(value = "客户id")
   private Long customerId;

   @ApiModelProperty(value = "意向类型")
   private Integer intentionType;


   @ApiModelProperty(value = "意向等级")
   private BigDecimal intentionLevel;


   @ApiModelProperty(value = "意向客户描述", notes = "车型描述")
   private String intentionRemark;


}
