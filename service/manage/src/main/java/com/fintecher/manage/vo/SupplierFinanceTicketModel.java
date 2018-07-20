package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/23 14:05
 * @Description:
 */
@Data
public class SupplierFinanceTicketModel {
    @ApiModelProperty(value = "放款记录ID")
    private Long id;
    @ApiModelProperty(value = "图片URL")
    private String url;
}
