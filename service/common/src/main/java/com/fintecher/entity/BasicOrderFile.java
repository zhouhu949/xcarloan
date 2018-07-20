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

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/14 10:34
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@ApiModel("上传订单资料")
@Table(name = "basic_order_file")
public class BasicOrderFile extends BaseEntity{

    @ApiModelProperty(value = "订单资料类型")
    @Column(name = "order_file_type")
    private Integer orderFileType;

    @ApiModelProperty(value = "路径")
    @Column(name = "file_url")
    private String fileUrl;

    @ApiModelProperty(value = "订单id")
    @Column(name = "order_id")
    private Long orderId;

    @ApiModelProperty(value = "资料类型")
    @Column(name = "data_type")
    private Integer dataType;

    @ApiModelProperty(value = "资料名称")
    @Column(name = "file_name")
    private String fileName;
    public enum TYPE{
        //资料类型
        IDCARD("身份证",10039),
        DRIVERLICENSE("驾驶证",10040),
        DEED("房产证",10041),
        OTHERDOCUMENT("其他证件",10042);
        private int value;
        private String name;

        TYPE(String name, int value) {
            this.name = name;
            this.value = value;
        }
        public int getValue(){
            return value;
        }
        public String getName(){
            return name;
        }
    }
}
