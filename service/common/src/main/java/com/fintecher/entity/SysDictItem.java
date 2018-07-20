package com.fintecher.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @System: 车贷金融
 * @Auther: hukaijia
 * @Description: 数据字典项实体类
 * @Date:Created on 2017/12/25/025 13:13
 * @Modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "数据字典项实体类")
@EqualsAndHashCode(callSuper = false)
@Table(name = "sys_dict_item")
public class SysDictItem implements java.io.Serializable {
    /**
     * 主键id
     */
    @Id
    @ApiModelProperty(value = "主键ID", notes = "主键ID")
    @Column(name = "id")
    private Integer id;

    @ApiModelProperty(value = "字典id", notes = "字典id")
    @Column(name = "dict_id")
    private Integer dictId;

    @ApiModelProperty(value = "字典树形编码", notes = "字典树形编码")
    @Column(name = "dict_item_tree_code")
    private String dictItemTreeCode;


    /**
     * 数据字典项code
     */
    @ApiModelProperty(value = "数据字典项code", notes = "数据字典项code")
    @Column(name = "dict_item_code")
    private String dictItemCode;

    /**
     * 机构id
     */
    @ApiModelProperty(value = "机构id", notes = "机构id")
    @Column(name = "org_id")
    private Long orgId;
    /**
     * 数据字典项名称
     */
    @ApiModelProperty(value = "数据字典项名称", notes = "数据字典项名称")
    @Column(name = "dict_item_name")
    private String dictItemName;
    /**
     * 数据字典项类型  0 业务类型  1 非业务类型
     */
    @ApiModelProperty(value = "数据字典状态", notes = " 0 业务类型  1 非业务类型")
    @Column(name = "dict_item_status")
    private Integer dictItemStatus;
}
