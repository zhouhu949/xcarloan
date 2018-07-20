package com.fintecher.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

/**
 * @System: 车贷金融
 * @Auther: hukaijia
 * @Description: 机构实体类
 * @Date:Created on 2017/12/25/025 13:13
 * @Modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "机构实体类")
@Entity
@Table(name = "sys_org")
public class SysOrg extends BaseEntity {

    @ApiModelProperty("机构名称")
    private String orgName;
    @ApiModelProperty("机构性质")
    private Integer orgType;
    @ApiModelProperty("机构编号")
    private String orgCode;
    @ApiModelProperty("上级机构ID")
    private Long orgPid;
    @ApiModelProperty("机构树形编码")
    private String orgTreeCode;
    @ApiModelProperty("机构等级")
    private Integer orgLevel;
    @ApiModelProperty("机构状态:10022-启用，10033-停用")
    private Integer orgStatus;
    @ApiModelProperty("备注")
    private String orgRemark;
    @ApiModelProperty("操作人")
    private Long operator;
    @ApiModelProperty("操作日期")
    private Date operatorTime;

    /**
     * 机构性质
     */
    public enum OrgType {
        GROUP(10036, "集团"),
        PARENT_COMPANY(10004, "总公司"),
        CHILD_COMPANY(10005, "分公司"),
        DEPARTMENT(10006, "部门");

        private Integer value;
        private String remark;

        OrgType(Integer value, String remark) {
            this.value = value;
            this.remark = remark;
        }

        public Integer getValue() {
            return value;
        }

        /**
         * 判断参数合法性
         */
        public static boolean isValidValue(Integer value) {
            for (OrgType orgType1 : OrgType.values()) {
                if (Objects.equals(orgType1.getValue(), value)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 机构等级
     */
    public enum OrgLevel {
        ONE(10007, "一级机构"),
        TWO(10008, "二级机构"),
        THREE(10009, "三级机构"),
        FOUR(10010, "四级机构"),
        FIVE(10011, "五级机构"),
        SIX(10012, "六级机构"),
        SEVEN(10013, "七级机构"),
        EIGHT(10014, "八级机构");

        private Integer value;
        private String remark;

        OrgLevel(Integer value, String remark) {
            this.value = value;
            this.remark = remark;
        }

        public Integer getValue() {
            return value;
        }

        /**
         * 判断参数合法性
         */
        public static boolean isValidValue(Integer value) {
            for (OrgLevel orgLevel1 : OrgLevel.values()) {
                if (Objects.equals(orgLevel1.getValue(), value)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 机构状态
     */
    public enum OrgStatus {

        ENABLE(10022, "启用"),
        DISABLE(10023, "停用");

        private Integer value;
        private String remark;

        OrgStatus(Integer value, String remark) {
            this.value = value;
            this.remark = remark;
        }

        public Integer getValue() {
            return value;
        }

        /**
         * 判断参数合法性
         */
        public static boolean isValidValue(Integer value) {
            for (OrgStatus status : OrgStatus.values()) {
                if (Objects.equals(status.getValue(), value)) {
                    return true;
                }
            }
            return false;
        }
    }

}
