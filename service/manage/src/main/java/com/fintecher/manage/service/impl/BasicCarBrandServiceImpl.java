package com.fintecher.manage.service.impl;

import com.fintecher.entity.BasicCarBrand;
import com.fintecher.entity.BasicCarBrandSeries;
import com.fintecher.entity.BasicCarModel;
import com.fintecher.manage.service.BasicCarBrandSeriesService;
import com.fintecher.manage.service.BasicCarBrandService;
import com.fintecher.manage.service.BasicCarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/12 0012.
 */

@Service
@Transactional(rollbackOn = Exception.class)
public class BasicCarBrandServiceImpl extends  BaseServiceImpl<BasicCarBrand>  implements BasicCarBrandService {

   @Autowired
   private BasicCarBrandSeriesService basicCarBrandSeriesService;

    @Autowired
   private BasicCarModelService baseCarModelService;



   @Override
   public List<BasicCarBrand> findAllCarBrand(Long  orgId) {

     try{

        List<BasicCarBrand> list ;
        List<BasicCarBrandSeries> seriesList ;
        List<BasicCarModel> carList;

        Example example = new Example(BasicCarBrand.class);
        Example.Criteria criteria = example.createCriteria();
        //  获取该组织下的数据
        criteria.andEqualTo("orgId",orgId);
        list = this.selectByExample(example); // 所有的品牌
        list.forEach(carBrand -> carBrand.setType(1));
        for(BasicCarBrand carBrand : list){
           Example example1 = new Example(BasicCarBrandSeries.class);
           Example.Criteria criteria1 = example1.createCriteria();
           criteria1.andEqualTo("brandId", carBrand.getId());
           // 查找品牌下的所有系列
           seriesList = basicCarBrandSeriesService.selectByExample(example1);
           seriesList.forEach(carSeries -> carSeries.setType(2));
           for(BasicCarBrandSeries carSeries1 : seriesList){
              Example example2 = new Example(BasicCarModel.class);
              Example.Criteria criteria2 = example2.createCriteria();
              criteria2.andEqualTo("seriesId",carSeries1.getId());
              // 查找该系列下的所有的车型
              carList = baseCarModelService.selectByExample(example2);
              carList.forEach(car -> car.setType(3));
              carSeries1.setCarModel(carList);
           }
           carBrand.setCarBrandSeries(seriesList);
        }
        return list;
     }catch (Exception e ){
        e.printStackTrace();
        return null;
     }
   }
}
