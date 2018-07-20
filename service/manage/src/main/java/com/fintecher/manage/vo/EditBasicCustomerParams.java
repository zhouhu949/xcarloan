package com.fintecher.manage.vo;

import com.fintecher.entity.BasicCustomer;
import com.fintecher.util.EnumValue;
import com.fintecher.util.Status;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author ZhangYaJun
 * @Title: EditBasicCustomerParams
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/19 0019下午 20:21
 */
@Data
@ApiModel(value = "修改客户信息")
public class EditBasicCustomerParams {

   @ApiModelProperty(value = "客户id")
   @NotNull
   private Long id;
   @ApiModelProperty(value = "客户姓名")
   private String customerName;
   @ApiModelProperty(value = "客户性别")
   private Integer customerSex;
   @ApiModelProperty(value = "出生时间")
   private Date birthTime;
   @ApiModelProperty(value = "qq")
   private String qq;
   @ApiModelProperty(value = "微信")
   private String wechat;
   @ApiModelProperty(value = "facebook")
   private String facebook;
   @ApiModelProperty(value = "邮箱")
   @Email
   private String email;
   @ApiModelProperty(value = "教育程度")
   @EnumValue(enumClass = BasicCustomer.Education.class, enumMethod = "isValidValue", message = "教育程度参数错误")
   private Integer education;
   @ApiModelProperty(value = "毕业院校")
   private String school;
   @ApiModelProperty(value = "婚姻状况")
   @EnumValue(enumClass = BasicCustomer.Marital.class, enumMethod = "isValidValue", message = "婚姻状况参数错误")
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
   private String localHomeAddr;
   @ApiModelProperty(value = "现居住地址门牌号")
   private String localHomeAddrDetail;
   @ApiModelProperty(value = "居住地家庭座机")
   private String localHomePhone;
   @ApiModelProperty(value = "是否接受勘查")
   @EnumValue(enumClass = Status.class, enumMethod = "isValidValue", message = "是否接受勘查参数错误")
   private Integer houseProspecting;
   @ApiModelProperty(value = "邮政编码")
   private String postalCode;
   @ApiModelProperty(value = "民族")
   private String nation;
   @ApiModelProperty(value = "健康状况")
   @EnumValue(enumClass = BasicCustomer.HealthStatus.class, enumMethod = "isValidValue", message = "健康状况参数错误")
   private Integer healthStatus;
   @ApiModelProperty(value = "居住状况")
   @EnumValue(enumClass = BasicCustomer.HomeStatus.class, enumMethod = "isValidValue", message = "居住状况参数错误")
   private Integer homeStatus;
   @ApiModelProperty(value = "通讯地址")
   private String messageAddr;
   @ApiModelProperty(value = "客户电话")
   private String customerPhone;

}
