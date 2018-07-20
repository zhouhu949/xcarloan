package com.fintecher.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/19 15:10
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Api("客户车产配置")
@Table(name = "basic_car_config")
public class BasicCarConfig extends BaseEntity {
    @ApiModelProperty(value = "车辆id")
    private Long carId;
    @ApiModelProperty(value = "出厂日期")
    private Date factoryTime;
    @ApiModelProperty(value = "行驶里程")
    private Long mileAge;
    @ApiModelProperty(value = "行驶证号")
    private String drivingNo;
    @ApiModelProperty(value = "过户次数")
    private Integer transferNo;
    @ApiModelProperty(value = "车辆用途")
    private Integer carPurpose;
    @ApiModelProperty(value = "变速箱形式")
    private Integer transmission;
    @ApiModelProperty(value = "驱动形式")
    private Integer driver;
    @ApiModelProperty(value = "排量")
    private String displacement;
    @ApiModelProperty(value = "车况")
    private Integer carSituation;
    @ApiModelProperty(value = "估价")
    private BigDecimal evaluation;
    @ApiModelProperty(value = "备注")
    private String remark;



}
