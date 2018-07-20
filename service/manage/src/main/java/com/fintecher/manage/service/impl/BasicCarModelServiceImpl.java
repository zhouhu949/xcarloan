package com.fintecher.manage.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintecher.entity.BasicCarModel;
import com.fintecher.entity.BasicCarmodelParam;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.mapper.BasicCarModelParamMapper;
import com.fintecher.manage.mapper.CarModelMapper;
import com.fintecher.manage.service.BasicCarModelService;
import com.fintecher.manage.vo.*;
import com.fintecher.util.ZWDateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Administrator on 2018/6/12 0012.
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class BasicCarModelServiceImpl extends BaseServiceImpl<BasicCarModel>  implements BasicCarModelService {

    @Autowired
    private CarModelMapper carModelMapper;

   @Autowired
   private BasicCarModelParamMapper basicCarModelParamMapper;

    @Override
    public CarModelInfo findCarModel(Long modelId) {
        return carModelMapper.findCarModel(modelId);
    }

   @Override
   public AddCarModelParamAndModelParams getCarParams(Long id) {

      return basicCarModelParamMapper.getCarParams(id);

   }

   @Override
   public void saveModel(BasicCarModelParams carModelParams, SysUser user) {
      BasicCarModel carModel = new BasicCarModel();
      carModel.setOperator(user.getId());
      carModel.setOperatorTime(ZWDateUtil.getNowDateTime());
      BeanUtils.copyProperties(carModelParams, carModel);
      String remark = carModelParams.getRemark();
      if (Objects.nonNull(remark))
      {
         carModel.setRemark(remark);
      }
      this.saveSelective(carModel);

      BasicCarmodelParam basicCarmodelParam = new BasicCarmodelParam();
      basicCarmodelParam.setModelId(carModel.getId());
      basicCarmodelParam.setOperator(user.getId());
      basicCarmodelParam.setOperatorTime(ZWDateUtil.getNowDateTime());
      basicCarmodelParam.setIsSysParam(BasicCarmodelParam.IsSysParam.YES.getValue());
      BeanUtils.copyProperties(carModelParams, basicCarmodelParam);
      basicCarModelParamMapper.insertSelective(basicCarmodelParam);
   }

   @Override
   public void updateModel(EditCarModelParams editCarModelParams, SysUser user) {
      BasicCarModel carModel = new BasicCarModel();
      carModel.setOperator(user.getId());
      carModel.setOperatorTime(ZWDateUtil.getNowDateTime());
      BeanUtils.copyProperties(editCarModelParams, carModel);
      String remark = editCarModelParams.getRemark();
      if (Objects.nonNull(remark))
      {
         carModel.setRemark(remark);
      }
      this.updateSelective(carModel);
      Long id = basicCarModelParamMapper.queryParamId(editCarModelParams.getId());
      BasicCarmodelParam basicCarmodelParam = new BasicCarmodelParam();
      BeanUtils.copyProperties(editCarModelParams, basicCarmodelParam);
      basicCarmodelParam.setId(id);
      basicCarModelParamMapper.updateByPrimaryKeySelective(basicCarmodelParam);

   }

   @Override
   public List<CarInfoModel> getCarModelListByOrg(Long orgId, SearchCarParams searchCarParams) {
      ObjectMapper objectMapper = new ObjectMapper();
      Map paramsMap = objectMapper.convertValue(searchCarParams, Map.class);
      paramsMap.put("orgId", orgId);
      return carModelMapper.getCarModelListByOrg(paramsMap);
   }

   @Override
   public void deleteParamsById(Long id) {

      Example example = new Example(BasicCarmodelParam.class);
      Example.Criteria criteria = example.createCriteria();
      criteria.andEqualTo("modelId", id);

      List<BasicCarmodelParam> basicCarmodelParams = basicCarModelParamMapper.selectByExample(example);
      for (BasicCarmodelParam params : basicCarmodelParams)
      {
         basicCarModelParamMapper.deleteByPrimaryKey(params.getId());
      }

   }
}
