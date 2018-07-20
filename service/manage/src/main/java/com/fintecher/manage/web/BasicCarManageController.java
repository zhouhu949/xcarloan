package com.fintecher.manage.web;

import com.fintecher.common.vo.manage.ResponseResult;
import com.fintecher.entity.*;
import com.fintecher.manage.mapper.BasicCarBrandMapper;
import com.fintecher.manage.service.*;
import com.fintecher.manage.vo.*;
import com.fintecher.util.Constants;
import com.fintecher.util.ZWDateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * auther:zhangyajun
 * date: 20117 6 .13
 * desc: 基础数据 车辆品牌树菜单
 */

@RestController
@RequestMapping("/basicCarManageController")
@Api(description = "车辆树管理")
public class BasicCarManageController extends BaseController {

    @Autowired
    private BasicCarBrandService basicCarBrandService;
    @Autowired
    private BasicCarBrandSeriesService basicCarBrandSeriesService;
    @Autowired
    private BasicCarModelService baseCarModelService;


    @Autowired
    private BasicCarModelParamService basicCarModelParamService;

    @Autowired
    private BasicCarModelPhotoService basicCarModelPhotoService;


    @Autowired
    private BasicCarIntroduceService basicCarIntroduceService;
    @Autowired
    private BasicCarBrandMapper basicCarBrandMapper;

    @GetMapping("/getAllCarTreeList")
    @ApiOperation(value = "车辆品牌列表")
    public ResponseResult getCarAllInfo(@RequestHeader(value = Constants.AUTHORIZATION) String authorization) {

        SysUser user = getUserByAuth(authorization);
        if (Objects.isNull(user)) {
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
        }
        List<BasicCarBrand> allCarBrand = basicCarBrandService.findAllCarBrand(user.getOrgId());
        return new ResponseResult<>(ResponseResult.Status.SUCCESS, allCarBrand);
    }

    /**
     * @param editCarParams desc:车牌信息修改
     * @return
     */
    @PutMapping("/editCarBrand")
    @ApiOperation(value = "修改车辆品牌", notes = "修改车辆品牌")
    public ResponseResult editCarInfo(@Validated @RequestBody EditCarParams editCarParams, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }

            BasicCarBrand carBrand = new BasicCarBrand();
            carBrand.setId(editCarParams.getId());
            carBrand.setBrandName(editCarParams.getBrandName());
            carBrand.setOrgId(user.getOrgId());
            carBrand.setDeptId(user.getDeptId());
            carBrand.setOperator(user.getId());
            carBrand.setOperatorTime(ZWDateUtil.getNowDateTime());
            carBrand.setBrandPhotoUrl(editCarParams.getBrandPhotoUrl());
            String remark = editCarParams.getRemark();
            if (Objects.nonNull(remark)) {
                carBrand.setRemark(remark);
            }
            basicCarBrandService.updateSelective(carBrand);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "修改成功");

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }


    /**
     * 根据品牌id查找车辆品牌
     */
    @GetMapping("/getCarBrandById/{id}")
    @ApiOperation(value = "根据品牌ID查找车辆品牌", notes = "根据品牌ID查找车辆品牌")
    public ResponseResult getCarBrandById(@PathVariable("id") @ApiParam(value = "品牌ID", required = true) Long id, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {

        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCarBrand byId = basicCarBrandService.findById(id);
            return new ResponseResult(ResponseResult.Status.SUCCESS, byId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }

    }


    /**
     * @param editCarParams desc:  增加车辆品牌信息
     * @return
     */
    @PostMapping("/addCarBrand")
    @ApiOperation(value = "增加车辆品牌", notes = "增加车辆品牌")
    public ResponseResult addCarBrandInfo(@Validated @RequestBody BasicCarBrandParams editCarParams,
                                          @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCarBrand carBrands = new BasicCarBrand();
            carBrands.setOrgId(user.getOrgId());
            carBrands.setBrandName(editCarParams.getBrandName());
            carBrands.setBrandCode(UUID.randomUUID().toString());
            carBrands.setDeptId(user.getDeptId());
            carBrands.setOperator(user.getId());
            carBrands.setBrandPhotoUrl(editCarParams.getBrandPhotoUrl());
            carBrands.setOperatorTime(ZWDateUtil.getNowDateTime());
            String remark = editCarParams.getRemark();
            if (Objects.nonNull(remark)) {
                carBrands.setRemark(editCarParams.getRemark());
            }
            basicCarBrandService.save(carBrands);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "增加成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    /**
     * @param id
     * @param authorization desc :删除车辆品牌信息
     * @return
     */

    @DeleteMapping("/deleteCarBrand/{id}")
    @ApiOperation(value = "删除车辆品牌", notes = "删除车辆品牌")
    public ResponseResult deleteCarBrandInfo(@PathVariable("id") @ApiParam("品牌ID") Long id,
                                             @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCarBrand brand = basicCarBrandService.findById(id);
            Example example = new Example(BasicCarBrandSeries.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("brandId", brand.getId());
            if (basicCarBrandSeriesService.selectCountByExample(example) > 0) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "该品牌下存在车系不允许删除");
            } else {
                basicCarBrandService.deleteById(id);
            }
            return new ResponseResult(ResponseResult.Status.SUCCESS, "删除成功");

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }


    /**
     * @param carSeriesParams /*********************************************************************************
     * @return **************************************************************************************
     * @desc 修改车系
     */
    @PutMapping("/editCarSeries")
    @ApiOperation(value = "修改车系", notes = "修改车系")
    public ResponseResult editCarInfo(@Validated @RequestBody EditCarSeriesParams carSeriesParams, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCarBrandSeries series = new BasicCarBrandSeries();
            series.setOperator(user.getId());
            series.setOperatorTime(ZWDateUtil.getNowDateTime());
            BeanUtils.copyProperties(carSeriesParams, series);
            String remark = carSeriesParams.getRemark();
            if (Objects.nonNull(remark)) {
                series.setRemark(remark);
            }
            basicCarBrandSeriesService.updateSelective(series);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "修改成功");

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }

    }


    /**
     * 根据车系ID查找车系
     */
    @GetMapping("/getCarSeriesById/{id}")
    @ApiOperation(value = "根据车系ID查找车系", notes = "根据车系ID查找车系")
    public ResponseResult getCarSeriesById(@PathVariable("id") @ApiParam(value = "车系ID", required = true) Long id, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {

        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCarBrandSeries seriesById = basicCarBrandSeriesService.findById(id);
            return new ResponseResult(ResponseResult.Status.SUCCESS, seriesById);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }

    }


    /**
     * @param carSeriesParams
     * @return
     * @desc 添加车系
     */
    @PostMapping("/addCarSeries")
    @ApiOperation(value = "新增车系", notes = "新增车系")
    public ResponseResult addCarSeriesInfo(@Validated @RequestBody BasicCarSeriesParams carSeriesParams, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }

            if (Objects.isNull(basicCarBrandService.findById(carSeriesParams.getBrandId()))) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "没有该车品牌可以添加");
            }
            BasicCarBrandSeries series = new BasicCarBrandSeries();
            series.setOperator(user.getId());
            series.setSeriesCode(UUID.randomUUID().toString());
            series.setOperatorTime(ZWDateUtil.getNowDateTime());
            BeanUtils.copyProperties(carSeriesParams, series);
            String remark = carSeriesParams.getRemark();
            if (Objects.nonNull(remark)) {
                series.setRemark(remark);
            }
            basicCarBrandSeriesService.saveSelective(series);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "添加成功");

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }

    }


    /**
     * @param id
     * @param authorization desc :删除车系信息
     * @return
     */

    @DeleteMapping("/deleteCarSeries/{id}")
    @ApiOperation(value = "删除车系", notes = "删除车系")
    public ResponseResult deleteCarSeriesInfo(@PathVariable("id") @ApiParam("车系ID") Long id,
                                              @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {

        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            Example example = new Example(BasicCarModel.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("seriesId", id);
            if (baseCarModelService.selectCountByExample(example) > 0) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "该车系下存在车型不允许删除");
            } else {
                basicCarBrandSeriesService.deleteById(id);
            }
            return new ResponseResult(ResponseResult.Status.SUCCESS, "删除成功");

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }


    /**
     * *******************************************************************************************
     *
     * @param editCarModelParams
     * @param authorization
     * @return ***************************************************************************************
     * @desc 修改车型
     */
    @PutMapping("/editCarModel")
    @ApiOperation(value = "修改车型", notes = "修改车型")
    public ResponseResult editCarInfo(@Validated @RequestBody EditCarModelParams editCarModelParams, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }

            baseCarModelService.updateModel(editCarModelParams, user);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }

    }


    /**
     * 根据车型ID查找车型
     */
    @GetMapping("/getCarModelById/{id}")
    @ApiOperation(value = "根据车型ID查找车型", notes = "根据车型ID查找车型")
    public ResponseResult getCarModelById(@PathVariable("id") @ApiParam(value = "车型ID", required = true) Long id, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCarModel carModel = baseCarModelService.findById(id);
            return new ResponseResult(ResponseResult.Status.SUCCESS, carModel);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }


    }


    /**
     * @param carModelParams
     * @param authorization
     * @return
     */
    @PostMapping("/addCarModel")
    @ApiOperation(value = "新增车型", notes = "新增车型")
    public ResponseResult addCarSeriesInfo(@Validated @RequestBody BasicCarModelParams carModelParams, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            if (Objects.isNull(basicCarBrandSeriesService.findById(carModelParams.getSeriesId()))) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "没有该车系可以添加");
            }
            baseCarModelService.saveModel(carModelParams, user);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "添加成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }

    }


    /**
     * @param id
     * @param authorization desc :删除车型信息
     * @return
     */

    @DeleteMapping("/deleteCarModel/{id}")
    @ApiOperation(value = "删除车型", notes = "删除车型")
    public ResponseResult deleteCarModelInfo(@PathVariable("id") @ApiParam("车型ID") Long id,
                                             @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        logger.debug("REST request to addOrganization {}", id);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            //  除参数以外引用不可删除
            if (basicCarModelParamService.selectCountDeleteModelCar(id) > 0) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "该车型下存在其他关联不允许删除");
            } else {
                baseCarModelService.deleteById(id);
                baseCarModelService.deleteParamsById(id);
            }
            return new ResponseResult(ResponseResult.Status.SUCCESS, "删除成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }


    /**
     * ********************************************************************************************
     *
     * @param
     * @param authorization
     * @return ********************************************************************************************
     * @ahthor: @zhangyajun
     * desc :                              查找参数
     */

    @GetMapping("/findCarConfigParamList/{id}")
    @ApiOperation(value = "根据车型查找参数列表")
    public ResponseResult findCarConfigParamList(@PathVariable("id") @ApiParam(value = "车型ID", required = true) Long id, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            Example example1 = new Example(BasicCarmodelParam.class);
            Example.Criteria criteria1 = example1.createCriteria();
            criteria1.andEqualTo("modelId", id);
            criteria1.andEqualTo("isSysParam", BasicCarmodelParam.IsSysParam.NO.getValue());
            List<BasicCarmodelParam> basicCarmodelParams = basicCarModelParamService.selectByExample(example1);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, basicCarmodelParams);


        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }

    }

    /**
     * 修改配置参数
     */
    @PutMapping("/editCarConfigParamInfo")
    @ApiOperation(value = "修改配置参数信息", notes = "修改配置参数信息")
    public ResponseResult editCarConfigParamInfo(@Validated @RequestBody EditCarModelParamParams carModelParamParams, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCarmodelParam param = new BasicCarmodelParam();
            param.setOperator(user.getId());
            param.setOperatorTime(ZWDateUtil.getNowDateTime());
            BeanUtils.copyProperties(carModelParamParams, param);
            String remark = carModelParamParams.getRemark();
            if (Objects.nonNull(remark)) {
                param.setRemark(remark);
            }
            basicCarModelParamService.updateSelective(param);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }

    }

    /**
     * 增加 配置类型
     */
    @PostMapping("/addCarConfigParamInfo")
    @ApiOperation(value = "新增配置参数信息", notes = "新增配置参数信息")
    public ResponseResult addCarConfigParamInfo(@Validated @RequestBody BasicCarModelParamParams carModelParamParams, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            if (Objects.isNull(baseCarModelService.findById(carModelParamParams.getModelId()))) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "没有车型可以添加");
            }
            basicCarModelParamService.saveModelParam(carModelParamParams, user);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "添加成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }

    }

    /**
     * @param id
     * @param authorization 根据参数ID查找参数
     * @return
     */
    @GetMapping("/getCarTypeParamById/{id}")
    @ApiOperation(value = "根据参数ID查找参数", notes = "根据参数ID查找参数")
    public ResponseResult getCarTypeParamById(@PathVariable("id") @ApiParam(value = "参数ID", required = true) Long id, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCarmodelParamValue param = basicCarModelParamService.getCarParamById(id);
            return new ResponseResult(ResponseResult.Status.SUCCESS, param);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, "系统异常 ，请联系管理员");
        }

    }

    /**
     * @param id
     * @param authorization 删除配置参数
     * @return
     */

    @DeleteMapping("/deleteCarConfigParam/{id}")
    @ApiOperation(value = "删除配置参数", notes = "删除配置参数")
    public ResponseResult deleteCarConfigParamInfo(@PathVariable("id") @ApiParam(value = "配置参数ID", required = true) Long id, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {

        logger.debug("REST request to addOrganization {}", id);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCarmodelParam carmodelParam = basicCarModelParamService.findById(id);
            if (Objects.nonNull(carmodelParam.getId())) {
                basicCarModelParamService.deleteById(id);
                return new ResponseResult(ResponseResult.Status.SUCCESS, "删除成功");
            }
            return new ResponseResult(ResponseResult.Status.SUCCESS, "已删除");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }


    /**
     * ************************************************************************************************
     * 车型介绍
     *
     * @param id
     * @param authorization
     * @return ************************************************************************************************
     */

    @GetMapping("/findCarIntroduceList/{id}")
    @ApiOperation(value = "根据车型ID查车型介绍列表")
    public ResponseResult findCarIntroduceList(@PathVariable("id") @ApiParam(value = "车型ID", required = true) Long id, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            Example example1 = new Example(BasicCarIntroduce.class);
            Example.Criteria criteria1 = example1.createCriteria();
            criteria1.andEqualTo("modelId", id);
            List<BasicCarIntroduce> basicCarIntroduces = basicCarIntroduceService.selectByExample(example1);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, basicCarIntroduces);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }

    }


    @GetMapping("/findCarIntroduceById/{id}")
    @ApiOperation(value = "根据车型介绍ID查车型介绍")
    public ResponseResult findCarIntroduceById(@PathVariable("id") @ApiParam(value = "车型介绍ID", required = true) Long id, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {

        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }

            BasicCarIntroduce basicCarIntroduce = basicCarIntroduceService.findById(id);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, basicCarIntroduce);


        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }


    @PostMapping("/addCarIntenduceInfo")
    @ApiOperation(value = "新增车型介绍", notes = "新增车型介绍")
    public ResponseResult addCarIntenduceInfo(@Validated @RequestBody BasicCarIntroduceParams basicCarIntroduceParams, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCarIntroduce basicCarIntroduce = new BasicCarIntroduce();
            basicCarIntroduce.setOperator(user.getId());
            basicCarIntroduce.setIntroduceUrl(String.join(",", basicCarIntroduceParams.getIntroduceUrl()));
            basicCarIntroduce.setOperatorTime(ZWDateUtil.getNowDateTime());
            BeanUtils.copyProperties(basicCarIntroduceParams, basicCarIntroduce);
            basicCarIntroduceService.saveSelective(basicCarIntroduce);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "增加成功");

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }


    @PutMapping("/editCarIntenduceInfo")
    @ApiOperation(value = "修改车型介绍", notes = "修改车型介绍")
    public ResponseResult editCarIntenduceInfo(@Validated @RequestBody EditBasicCarIntroduceParams editBasicCarIntroduceParams, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCarIntroduce basicCarIntroduce = new BasicCarIntroduce();
            basicCarIntroduce.setOperator(user.getId());
            basicCarIntroduce.setIntroduceUrl(String.join(",", editBasicCarIntroduceParams.getIntroduceName()));
            basicCarIntroduce.setOperatorTime(ZWDateUtil.getNowDateTime());
            BeanUtils.copyProperties(editBasicCarIntroduceParams, basicCarIntroduce);
            basicCarIntroduceService.updateSelective(basicCarIntroduce);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "修改成功");

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }


    @DeleteMapping("/deleteCarIntenduceInfo/{id}")
    @ApiOperation(value = "删除车型介绍", notes = "删除车型介绍")
    public ResponseResult deleteCarIntenduceInfo(@PathVariable("id") @ApiParam(value = "车型介绍ID", required = true) Long id, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            basicCarIntroduceService.deleteById(id);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "删除成功");

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    /**
     * @auther: dwx
     * @Description:根据车系id查车辆品牌
     */
    @GetMapping("/getCarSeriesByCarName/{id}")
    @ApiOperation(value = "根据车系id查车辆品牌", notes = "根据车系id查车辆品牌")
    public ResponseResult getCarSeriesByCarName(@PathVariable("id") @ApiParam(value = "车系ID", required = true) Long id, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCarBrand carBrand = basicCarBrandMapper.queryCarBrandInfo(id);
            return new ResponseResult(ResponseResult.Status.SUCCESS, carBrand);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, "系统异常");
        }
    }


    /**
     * 车辆图片
     */

    @GetMapping("/getCarModelPhotoList/{id}")
    @ApiOperation(value = "车辆图片列表", notes = "车辆图片列表")
    public ResponseResult getCarModelPhotoList(@PathVariable("id") @ApiParam(value = "车系ID", required = true) Long id, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            Example example = new Example(BasicCarModelPhoto.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("modelId", id);
            List<BasicCarModelPhoto> basicCarModelPhotos = basicCarModelPhotoService.selectByExample(example);
            return new ResponseResult(ResponseResult.Status.SUCCESS, basicCarModelPhotos);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, "系统异常");
        }
    }


    @PostMapping("/addCarModelPhoto")
    @ApiOperation(value = "添加车辆图片", notes = "添加车辆图片")
    public ResponseResult addCarModelPhoto(@Validated @RequestBody BasicCarModelPhotoParams basicCarModelPhotoParams, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCarModelPhoto basicCarModelPhoto = new BasicCarModelPhoto();
            basicCarModelPhoto.setOperator(user.getId());
            basicCarModelPhoto.setPhotoUrl(String.join(",", basicCarModelPhotoParams.getPhotoUrl()));
            basicCarModelPhoto.setOperatorTime(ZWDateUtil.getNowDateTime());
            BeanUtils.copyProperties(basicCarModelPhotoParams, basicCarModelPhoto);
            basicCarModelPhotoService.saveSelective(basicCarModelPhoto);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "添加成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, "系统异常");
        }

    }


    @PutMapping("/editCarModelPhoto")
    @ApiOperation(value = "修改车辆图片", notes = "修改车辆图片")
    public ResponseResult editCarModelPhoto(@Validated @RequestBody EditBasicCarModelPhotoParams editBasicCarModelPhotoParams, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCarModelPhoto basicCarModelPhoto = new BasicCarModelPhoto();
            basicCarModelPhoto.setOperator(user.getId());
            basicCarModelPhoto.setPhotoUrl(String.join(",", editBasicCarModelPhotoParams.getPhotoUrl()));
            basicCarModelPhoto.setOperatorTime(ZWDateUtil.getNowDateTime());
            basicCarModelPhoto.setId(editBasicCarModelPhotoParams.getId());
            BeanUtils.copyProperties(editBasicCarModelPhotoParams, basicCarModelPhoto);
            basicCarModelPhotoService.updateSelective(basicCarModelPhoto);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "添加成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, "系统异常");
        }

    }

    @DeleteMapping("/deleteCarModelPhoto/{id}")
    @ApiOperation(value = "删除车辆图片", notes = "删除车辆图片")
    public ResponseResult deleteCarModelPhoto(@PathVariable("id") @ApiParam(value = "车辆图片ID", required = true) Long id, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            basicCarModelPhotoService.deleteById(id);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "删除成功");

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }


    @GetMapping("/getCarParams/{id}")
    @ApiOperation(value = "根据车型id获取基本参数", notes = "根据车型id获取基本参数")
    public ResponseResult getCarParams(@PathVariable("id") @ApiParam(value = "车型ID", required = true) Long id, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {

        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            AddCarModelParamAndModelParams carParams = baseCarModelService.getCarParams(id);
            return new ResponseResult(ResponseResult.Status.SUCCESS, carParams);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);

        }
    }

    @GetMapping("/queryCarModel")
    @ApiOperation(value = "分页获取当前登陆人组织机构下的车型", notes = "分页获取当前登陆人组织机构下的车型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                    value = "页数 (1..N)", required = true),
            @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                    value = "每页大小", required = true),
            @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                    value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
    })
    public ResponseResult<PageInfo<CarInfoModel>> queryCarModel(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                                                @ApiIgnore PageParam pageParam,
                                                                SearchCarParams searchCarParams) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
            List<CarInfoModel> carModelListByOrg = baseCarModelService.getCarModelListByOrg(user.getOrgId(), searchCarParams);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, new PageInfo<>(carModelListByOrg));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);

        }
    }
}


