package com.fintecher.manage.service.impl;

import com.fintecher.entity.BasicCarmodelParam;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.mapper.BasicCarModelParamMapper;
import com.fintecher.manage.service.BasicCarModelParamService;
import com.fintecher.manage.vo.BasicCarModelParamParams;
import com.fintecher.manage.vo.BasicCarmodelParamValue;
import com.fintecher.util.ZWDateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Objects;

/**
 * Created by Administrator on 2018/6/13 0013.
 */

@Transactional(rollbackFor = Exception.class)
@Service
public class BasicCarModelParamServiceImpl  extends   BaseServiceImpl<BasicCarmodelParam>  implements BasicCarModelParamService {

   @Autowired
   private BasicCarModelParamMapper basicCarModelParamMapper;


   @Override
   public void saveModelParam(BasicCarModelParamParams carModelParamParams, SysUser user) {
      BasicCarmodelParam param = new BasicCarmodelParam();
      param.setOperator(user.getId());
      param.setIsSysParam(BasicCarmodelParam.IsSysParam.NO.getValue());
      param.setOperatorTime(ZWDateUtil.getNowDateTime());
      BeanUtils.copyProperties(carModelParamParams, param);
      String remark = carModelParamParams.getRemark();
      if (Objects.nonNull(remark))
      {
         param.setRemark(remark);
      }
      this.saveSelective(param);
   }

   @Override
   public BasicCarmodelParamValue getCarParamById(Long id) {
      return basicCarModelParamMapper.getCarParamById(id);

   }

   @Override
   public int selectCountDeleteModelCar(Long id) {
      return basicCarModelParamMapper.selectCountDeleteModelCar(id);
   }
}
