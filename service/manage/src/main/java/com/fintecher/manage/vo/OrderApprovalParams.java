package com.fintecher.manage.vo;

import com.fintecher.util.EnumValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
@ApiModel("订单审批请求参数")
public class OrderApprovalParams {
    @ApiModelProperty("类型（必须）：0-通过，2-移入黑名单，3-移入灰名单，4-移入白名单，5-退回")
//    @ApiModelProperty("类型（必须）：0-通过，1-拒绝，2-移入黑名单，3-移入灰名单，4-移入白名单，5-退回")
    @NotNull
    @EnumValue(enumClass = Type.class, enumMethod = "isValidValue", message = "类型参数错误")
    private Integer type;
    @ApiModelProperty("订单ID（必须）")
    @NotNull(message = "订单ID不能为空")
    private Long orderId;
//    @ApiModelProperty("审批意见")
//    private String remark;

    public enum Type {
        //        ZERO(0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5);
        ZERO(0), TWO(2), THREE(3), FOUR(4), FIVE(5);

        private int value;

        Type(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        /**
         * 判断参数合法性
         */
        public static boolean isValidValue(Integer value) {
            for (Type type : Type.values()) {
                if (Objects.equals(type.getValue(), value)) {
                    return true;
                }
            }
            return false;
        }

        public static Type operateValue(Integer value) {
            if (value == null) {
                return null;
            }
            for (Type type : values()) {    //values()方法返回enum实例的数组
                if (value.equals(type.getValue())) return type;
            }
            return null;
        }
    }
}
