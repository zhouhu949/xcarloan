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

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "用户设备实体类")
@Entity
@Table(name = "sys_user_device")
public class SysUserDevice extends BaseEntity {
    @ApiModelProperty("用户ID")
    @Column(name = "user_id")
    private Long userId;
    @ApiModelProperty("设备编号")
    @Column(name = "device_code")
    private String deviceCode;
    @ApiModelProperty("设备类型")
    @Column(name = "device_type")
    private Integer deviceType;
    @ApiModelProperty("设备名称")
    @Column(name = "device_name")
    private String deviceName;
    @ApiModelProperty("是否启用设备锁")
    @Column(name = "device_validate")
    private Integer deviceValidate;
    @ApiModelProperty("是否启用设备")
    @Column(name = "device_status")
    private Integer deviceStatus;
    @ApiModelProperty("设备识别码")
    @Column(name = "device_mac")
    private String deviceMac;
    @ApiModelProperty("操作人")
    @Column(name = "operator")
    private Long operator;
    @ApiModelProperty("操作日期")
    @Column(name = "operator_time")
    private Date operatorTime;

    /**
     * 设备类型
     */
    public enum DeviceType {
        PC(10020, "PC"),
        APP(10021, "APP");

        private Integer value;
        private String remark;

        DeviceType(Integer value, String remark) {
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
            for (DeviceType deviceType : DeviceType.values()) {
                if (Objects.equals(deviceType.getValue(), value)) {
                    return true;
                }
            }
            return false;
        }
    }
} 