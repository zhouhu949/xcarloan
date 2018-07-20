package com.fintecher.manage.service.impl;

import com.fintecher.entity.SysDict;
import com.fintecher.entity.SysDictItem;
import com.fintecher.manage.mapper.DataDictMapper;
import com.fintecher.manage.service.DataDictService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class DataDictServiceImpl implements DataDictService {
    @Resource
    private DataDictMapper dataDictMapper;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public List<SysDict> findDataDictByTypeCode(Integer id, String dictItemName) {
        return dataDictMapper.findDataDictByTypeCode(id, dictItemName);
    }

    /**
     * @System: 车贷金融
     * @Auther: dwx
     * @Description: 查询所有数据字典项
     * @Modified By:
     */
    @Override
    @Cacheable(value = "dataDictCache", key = "'petstore:dataDict:all'")
    public List<SysDictItem> getAll() {
        return dataDictMapper.getAll();
    }

    @Override
    public List<SysDictItem>findAll(){
        return dataDictMapper.findAll();
    }
    /**
     * @System: 车贷金融
     * @Auther: xiaqun
     * @Description: 删除数据字典项
     * @Modified By:
     */
    @Override
    @CacheEvict(value = "dataDictCache", key = "'petstore:dataDict:all'")
    public Integer deleteDataDict(SysDictItem sysDictItem) {
        return dataDictMapper.delete(sysDictItem);
    }

    /**
     * @System: 车贷金融
     * @Auther: xiaqun
     * @Description: 获取数据字典项最大ID
     * @Modified By:
     */
    @Override
    public Integer getMaxId() {
        return dataDictMapper.getMaxId();
    }

    /**
     * @System: 车贷金融
     * @Auther: xiaqun
     * @Description: 保存数据字典项
     * @Modified By:
     */
    @Override
    @CacheEvict(value = "dataDictCache", key = "'petstore:dataDict:all'")
    public void saveDataDict(SysDictItem sysDictItem) {
        dataDictMapper.insert(sysDictItem);
    }

    /**
     * @System: 车贷金融
     * @Auther: xiaqun
     * @Description: 更新数据字典项
     * @Modified By:
     */
    @Override
    @CacheEvict(value = "dataDictCache", key = "'petstore:dataDict:all'")
    public void updateDataDict(SysDictItem sysDictItem) {
        dataDictMapper.updateByPrimaryKey(sysDictItem);
    }

    /**
     * @System: 车贷金融
     * @Auther: xiaqun
     * @Description: 获取最大排序
     * @Modified By:
     */
    @Override
    public Integer getMaxSort(String typeCode) {
        return dataDictMapper.getMaxSort(typeCode);
    }

    /**
     * @System: 车贷金融
     * @Auther: mouliang
     * @Description: 查询DataDict
     * @Modified By:
     */
    @Override
    public SysDictItem findOneByDataDict(SysDictItem sysDictItems) {
        return dataDictMapper.findOneByDataDict(sysDictItems);
    }

    @Override
    public List<SysDictItem> findDataDictByType(Integer dictType){
        return dataDictMapper.findDataDictByType(dictType);
    }

    @Override
    public List<SysDict>getSysDictList(Integer id){
        return dataDictMapper.getSysDictList(id);
    }

    @Override
    public SysDictItem findOne(SysDictItem record){
        return dataDictMapper.selectOne(record);
    }

}
