package com.fintecher.manage.service.impl;

import com.fintecher.entity.SysDict;
import com.fintecher.entity.SysDictItem;
import com.fintecher.manage.mapper.DataDictTypeMapper;
import com.fintecher.manage.service.DataDictTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/6/15.
 */

@Transactional(rollbackFor = Exception.class)
@Service
public class DataDictTypeServiceImpl  implements DataDictTypeService {
    @Resource
    DataDictTypeMapper dataDictTypeMapper;

    /**
     * @System: 车贷金融
     * @Auther: xiaqun
     * @Description: 获取数据字典项类型最大ID
     * @Modified By:
     */
    @Override
    public Integer getMaxId() {
        return dataDictTypeMapper.getMaxId();
    }

    /**
     * @System: 车贷金融
     * @Auther: xiaqun
     * @Description: 保存数据字典项类型
     * @Modified By:
     */
    @Override
    public Integer saveDataDict(SysDict dataDictType) {
        return dataDictTypeMapper.insert(dataDictType);
    }

    /**
     * @System: 车贷金融
     * @Auther: xiaqun
     * @Description: 更新数据字典项类型
     * @Modified By:
     */
    @Override
    public Integer updateDataDict(SysDict dataDictType) {
        return dataDictTypeMapper.updateByPrimaryKey(dataDictType);
    }

    @Override
    public SysDict findOne(SysDict record) {
        return dataDictTypeMapper.selectOne(record);
    }
    @Override
    public List<SysDict> selectListAll(){
        return dataDictTypeMapper.selectListAll();
    }
    @Override
    public List<SysDict>findAll(){
        return dataDictTypeMapper.findAll();
    }


}
