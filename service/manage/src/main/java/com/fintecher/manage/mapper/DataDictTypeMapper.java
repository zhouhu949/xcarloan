package com.fintecher.manage.mapper;


import com.fintecher.entity.SysDict;
import com.fintecher.entity.SysDictItem;
import com.fintecher.manage.util.MyMapper;

import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */
public interface DataDictTypeMapper extends MyMapper<SysDict> {
    /**
     * @System: 车贷金融
     * @Auther: xiaqun
     * @Description: 获取数据字典项类型最大ID
     * @Modified By:
     */
    Integer getMaxId();

    List<SysDict>selectListAll();
    List<SysDict>findAll();
}
