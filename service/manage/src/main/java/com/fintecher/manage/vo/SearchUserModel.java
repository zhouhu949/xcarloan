package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ApiModel("分页查询用户返回数据")
public class SearchUserModel {
    @ApiModelProperty("用户ID")
    private Long id;
    @ApiModelProperty("机构ID")
    private Long orgId;
    @ApiModelProperty("机构名称")
    private String orgName;
    @ApiModelProperty("姓名")
    private String userRealname;
    @ApiModelProperty("性别")
    private Integer userSex;
    @ApiModelProperty("电话")
    private String userPhone;
    @ApiModelProperty("邮箱")
    private String userEmail;
    @ApiModelProperty("部门ID")
    private Long deptId;
    @ApiModelProperty("状态：10022-启用，10023-停用")
    private Integer userStatus;
    @ApiModelProperty("用户名")
    private String userUsername;
    @ApiModelProperty("备注")
    private String userRemark;
    @ApiModelProperty("操作人")
    private String operatorName;
    @ApiModelProperty("操作日期")
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private Date operatorTime;
}
