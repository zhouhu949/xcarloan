package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/13 16:00
 * @Description:
 */
@Data
public class SysDictItemModel {

    @Id
    @ApiModelProperty(value = "主键ID", notes = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "字典树形编码", notes = "字典树形编码")
    private String dictItemTreeCode;


    @ApiModelProperty(value = "字典id", notes = "字典id")
    private Integer dictId;


    /**
     * 数据字典项code
     */
    @ApiModelProperty(value = "数据字典项code", notes = "数据字典项code")
    private String dictItemCode;

    /**
     * 机构id
     */
    @ApiModelProperty(value = "机构id", notes = "机构id")
    private Long orgId;
    /**
     * 数据字典项名称
     */
    @ApiModelProperty(value = "数据字典项名称", notes = "数据字典项名称")
    private String dictItemName;
    /**
     * 数据字典项类型  0 业务类型  1 非业务类型
     */
    @ApiModelProperty(value = "数据字典状态", notes = " 0 业务类型  1 非业务类型")
    private Integer dictItemStatus;
}
