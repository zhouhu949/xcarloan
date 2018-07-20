package com.fintecher.manage.service.impl;

import com.fintecher.entity.BasicCarmodelParam;
import com.fintecher.entity.BasicSupplier;
import com.fintecher.manage.mapper.BasicSupplierMapper;
import com.fintecher.manage.service.BasicSupplierService;
import com.fintecher.manage.vo.BasicSupplierParams;
import com.fintecher.manage.vo.BasicSupplierSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Objects;

/**
 * @author ZhangYaJun
 * @Title: BasicSupplierServiceImpl
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/14 0014上午 10:46
 */

@Transactional(rollbackFor = Exception.class)
@Service
public class BasicSupplierServiceImpl extends   BaseServiceImpl<BasicSupplier>  implements BasicSupplierService {


   @Autowired
   private  BasicSupplierService  basicSupplierService;



   @Override
   public List<BasicSupplier> getSupplierInfo(BasicSupplierSearch basicSupplierSearch, Long orgId) {
     try{
        Example example1 = new Example(BasicSupplier.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("orgId",orgId);
        if(Objects.nonNull(basicSupplierSearch.getSupplierName())){
           criteria1.andLike("supplierName","%"+basicSupplierSearch.getSupplierName()+"%") ;
        }
        List<BasicSupplier> basicSuppliers = basicSupplierService.selectByExample(example1);
        return basicSuppliers;

     }catch (Exception  e){


     }



      return null;
   }
}
