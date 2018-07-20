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
 * @Description: 用户实体类
 * @Date:Created on 2017/12/25/025 13:13
 * @Modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "用户实体类")
@Entity
@Table(name = "sys_user")
public class SysUser extends BaseEntity{
    @ApiModelProperty("机构ID")
    private Long orgId;
    @ApiModelProperty("姓名")
    private String userRealname;
    @ApiModelProperty("性别")
    private Integer userSex;
    @ApiModelProperty("电话")
    private String userPhone;
    @ApiModelProperty("邮箱")
    private String userEmail;
    @ApiModelProperty("用户头像")
    private String userPhoto;
    @ApiModelProperty("用户类型")
    private Integer userType;
    @ApiModelProperty("试用时长")
    private Integer tralTime;
    @ApiModelProperty("登录类型")
    private Integer loginType;
    @ApiModelProperty("登录设备类型")
    private Integer loginDevice;
    @ApiModelProperty("登录地址")
    private String loginAddress;
    @ApiModelProperty("密码过期时间")
    private Date passwordTime;
    @ApiModelProperty("部门ID")
    private Long deptId;
    @ApiModelProperty("用户绑定的消息推送")
    private String userMessagePushid;
    @ApiModelProperty("状态：0-启用，1-停用")
    private Integer userStatus;
    @ApiModelProperty("用户名")
    private String userUsername;
    @ApiModelProperty("密码")
    private String userPassword;
    @ApiModelProperty("备注")
    private String userRemark;
    @ApiModelProperty("操作人")
    private Long operator;
    @ApiModelProperty("操作日期")
    private Date operatorTime;

    /**
     * 用户类型
     */
    public enum UserType {
        FORMAL(1, "正式用户"),
        INFOEMAL(2, "试用用户");

        private Integer value;
        private String remark;

        UserType(Integer value, String remark) {
            this.value = value;
            this.remark = remark;
        }

        public Integer getValue() {
            return value;
        }
    }

    /**
     * 登陆类型
     */
    public enum LoginType {
        PASSWORD(1, "密码登陆"),
        QR(2, "二维码登陆"),
        FACE(3, "人脸识别登陆");

        private Integer value;
        private String remark;

        LoginType(Integer value, String remark) {
            this.value = value;
            this.remark = remark;
        }

        public Integer getValue() {
            return value;
        }
    }



    /**
     * 用户状态
     */
    public enum UserStatus {
        ENABLE(10022, "启用"),
        DISABLE(10023, "停用");

        private Integer value;
        private String remark;

        UserStatus(Integer value, String remark) {
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
            for (UserStatus userStatus : UserStatus.values()) {
                if (Objects.equals(userStatus.getValue(), value)) {
                    return true;
                }
            }
            return false;
        }
    }


}
