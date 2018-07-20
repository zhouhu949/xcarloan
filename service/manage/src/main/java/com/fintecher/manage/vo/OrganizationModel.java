package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


@Data
@ApiModel("获取组织机构返回实体")
public class OrganizationModel {
    @ApiModelProperty("机构ID")
    private Long id;
    @ApiModelProperty("机构名称")
    private String orgName;
    @ApiModelProperty("机构性质")
    private Integer orgType;
    @ApiModelProperty("机构编号")
    private String orgCode;
    @ApiModelProperty("上级机构ID")
    private Integer orgPid;
    @ApiModelProperty("机构树形编码")
    private String orgTreeCode;
    @ApiModelProperty("机构等级")
    private Integer orgLevel;
    @ApiModelProperty("机构状态")
    private Integer orgStatus;
}
