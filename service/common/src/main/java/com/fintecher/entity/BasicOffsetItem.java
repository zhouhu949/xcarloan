package com.fintecher.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "冲抵项实体类")
@EqualsAndHashCode(callSuper = false)
@Table(name="basic_offset_item")
public class BasicOffsetItem extends BaseEntity{

    @ApiModelProperty(value = "序号", notes = "序号")
    @Column(name = "item_order")
    private Long itemOrder;

    @ApiModelProperty(value = "冲抵ID", notes = "冲抵ID")
    @Column(name = "offset_id")
    private Long offsetId;

    @ApiModelProperty(value = "费用项ID", notes = "费用项ID")
    @Column(name = "expense_id")
    private Long expenseId;

}
