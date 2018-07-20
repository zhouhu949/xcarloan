package com.fintecher.manage.mapper;

import com.fintecher.entity.BasicCarModel;
import com.fintecher.manage.util.MyMapper;
import com.fintecher.manage.vo.CarInfoModel;
import com.fintecher.manage.vo.CarModelInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/6/12 0012.
 */
public interface CarModelMapper extends MyMapper<BasicCarModel>  {

    CarModelInfo findCarModel(Long modelId);

    List<CarInfoModel> getCarModelListByOrg(Map map);
}
