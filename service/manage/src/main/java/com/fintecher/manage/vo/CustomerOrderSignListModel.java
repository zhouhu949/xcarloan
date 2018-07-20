package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/26 09:38
 * @Description:
 */
@Data
public class CustomerOrderSignListModel {
    @ApiModelProperty(value = "客户id")
    private Long customerId;
    @ApiModelProperty(value = "客户姓名")
    private String customerName;
    @ApiModelProperty(value = "客户性别")
    private Integer customerSex;
    @ApiModelProperty(value = "身份证号")
    private String idCard;
    @ApiModelProperty(value = "客户手机号")
    private String customerPhone;
    @ApiModelProperty(value = "客户状态")
    private Integer customerStatus;
    @ApiModelProperty(value = "开户状态")
    private Integer accountStatus;
    @ApiModelProperty(value = "订单状态")
    private Integer orderType;
    @ApiModelProperty(value = "订单id")
    private Long orderId;
    @ApiModelProperty(value = "订单编号")
    private String orderNo;
    @ApiModelProperty(value = "资料数量")
    private Integer fileCount;
    @ApiModelProperty(value = "通讯地址")
    private String messageAddr;
    @ApiModelProperty(value = "现居住地址")
    private String localHomeAddr;
    @ApiModelProperty(value = "身份证有效期类型")
    private Integer idCardValidityPeriodType;
    @ApiModelProperty(value = "是否接受勘查")
    private Integer houseProspecting;
    @ApiModelProperty(value = "居住状况")
    private Integer homeStatus;
    @ApiModelProperty(value = "education")
    private Integer education;




}
