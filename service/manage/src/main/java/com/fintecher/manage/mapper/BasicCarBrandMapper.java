package com.fintecher.manage.mapper;

import com.fintecher.entity.BasicCarBrand;
import com.fintecher.manage.util.MyMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/21 17:55
 * @Description:
 */
public interface BasicCarBrandMapper extends MyMapper<BasicCarBrand> {
    /**
     * @auther: dwx
     * @Description:
     */
    BasicCarBrand queryCarBrandInfo(@Param("id")Long id);

    BasicCarBrand selectByCustomerId(BasicCarBrand basicCarBrand);
}
