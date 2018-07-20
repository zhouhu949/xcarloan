package com.fintecher.manage.mapper;

import com.fintecher.entity.BasicStockCar;
import com.fintecher.manage.util.MyMapper;
import com.fintecher.manage.vo.BasicStockCarListParams;
import com.fintecher.manage.vo.BasicStockCarSearch;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author ZhangYaJun
 * @Title: BasicStockCarMapper
 * @ProjectName xcarloan
 * @Description: TODO
 * @date 2018/6/14 0014下午 17:19
 */
public interface BasicStockCarMapper  extends MyMapper<BasicStockCar> {


   List<BasicStockCarListParams> findAllStockCarList(@Param("basicStockCarSearch") BasicStockCarSearch basicStockCarSearch, @Param("orgId") Long orgId);

   List<BasicStockCar> selectBySupplier(BasicStockCar basicStockCar);
}
