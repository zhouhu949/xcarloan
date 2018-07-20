package com.fintecher.manage.vo;

import com.fintecher.util.EnumValue;
import com.fintecher.util.Status;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel("更改用户设备锁请求参数")
public class UpdateUserDeviceParams {
    @ApiModelProperty("设备锁ID集合")
    @NotEmpty
    private List<Long> ids;
    @ApiModelProperty("操作类型：0-启用/禁用设备，1-启用/禁用设备锁，2-重置")
    @NotNull
    private Integer type;
    @ApiModelProperty("状态：10002-启用，10003-禁用。当type=2时此字段不需要传值。")
    @EnumValue(enumClass = Status.class, enumMethod = "isValidValue", message = "参数错误")
    private Integer deviceStatus;

}
