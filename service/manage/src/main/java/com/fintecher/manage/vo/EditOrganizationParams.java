package com.fintecher.manage.vo;

import com.fintecher.entity.SysOrg;
import com.fintecher.util.EnumValue;
import com.fintecher.util.Status;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("修改组织机构参数")
public class EditOrganizationParams {
    @ApiModelProperty("机构ID")
    @NotNull
    private Long id;
    @ApiModelProperty("机构名称")
    @NotBlank(message = "组织机构名称不能为空")
    private String orgName;
    @ApiModelProperty("机构性质：10004-总公司，10005-分公司，10006-部门，10036-集团")
    @NotNull(message = "机构性质不能为空")
    @EnumValue(enumClass = SysOrg.OrgType.class, enumMethod = "isValidValue", message = "机构性质参数错误")
    private Integer orgType;
    @ApiModelProperty("上级机构ID")
    @NotNull
    private Long orgPid;
    @ApiModelProperty("机构状态：10022-启用，10023-停用")
    @NotNull
    @EnumValue(enumClass = SysOrg.OrgStatus.class, enumMethod = "isValidValue", message = "机构状态参数错误")
    private Integer orgStatus;
    @ApiModelProperty("备注")
    private String orgRemark;
}
