package com.fintecher.manage.web;

import com.fintecher.common.vo.manage.ResponseResult;
import com.fintecher.entity.*;
import com.fintecher.manage.mapper.BasicCustomerCarAssessmentMapper;
import com.fintecher.manage.mapper.BasicCustomerCarMapper;
import com.fintecher.manage.service.BasicCustomerCarAssessmentService;
import com.fintecher.manage.service.BasicCustomerCarService;
import com.fintecher.manage.vo.*;
import com.fintecher.util.Constants;
import com.fintecher.util.CurrentUser;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import tk.mybatis.mapper.entity.Example;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/15 11:04
 * @Description:
 */
@RestController
@RequestMapping("/basicCustomerAssessmentCarController")
@Api(description = "车辆评估")
public class BasicCustomerAssessmentCarController extends BaseController {

    @Autowired
    private BasicCustomerCarAssessmentService basicCustomerCarAssessmentService;
    @Autowired
    private BasicCustomerCarService basicCustomerCarService;
    @Autowired
    private BasicCustomerCarMapper basicCustomerCarMapper;
    @Autowired
    private BasicCustomerCarAssessmentMapper customerCarAssessmentMapper;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * @auther: dwx
     * @Description:新增客户车产
     */
    @PostMapping("/addAssessmentApplication")
    @ApiOperation(value = "新增客户车产", notes = "新增客户车产")
    public ResponseResult addAssessmentApplication(@RequestBody BasicCustomerCarModel basicCustomerCarModel,
                                                   @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            basicCustomerCarService.addAssessmentApplication(basicCustomerCarModel, user);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "新增成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "新增失败");
        }
    }

    /**
     * @auther: dwx
     * @Description:删除客户车产
     */
    @DeleteMapping("/deleteBasicCustomerCar")
    @ApiOperation(value = "删除客户车产", notes = "删除客户车产")
    public ResponseResult deleteBasicCustomerCar(@RequestParam Long id, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCustomerCar basicCustomerCar = basicCustomerCarService.findById(id);
            if (Objects.isNull(basicCustomerCar)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "oh no,don't delete");
            }

            BasicCustomerCarAssessment status = basicCustomerCarMapper.findByStatus(id);
            if (Objects.nonNull(status) && status.getAssessmentStatus().equals(BasicCustomerCarAssessment.AssessmentStatus.PASS_THROUGH.getValue())) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "已评估车产不允许删除");
            }
            basicCustomerCarService.deleteById(id);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "删除成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, "删除失败");
        }

    }

    /**
     * @auther: dwx
     * @Description:修改客户车产
     */
    @PutMapping("/updateBasicCustomerCar")
    @ApiOperation(value = "修改客户车产", notes = "修改客户车产")
    public ResponseResult updateBasicCustomerCar(@RequestBody BasicCustomerCarModel basicCustomerCarModel,
                                                 @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            basicCustomerCarService.updateAssessmentApplication(basicCustomerCarModel, user);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "修改成功");
        } catch (RuntimeException s) {
            logger.error(s.getMessage(), s);
            return new ResponseResult(ResponseResult.Status.FAILURE, s.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, "修改失败");
        }
    }

    /**
     * @auther: dwx
     * @Description:获取客户车产信息
     */
    @GetMapping("/getBasicCustomerCar")
    @ApiOperation(value = "获取客户车产信息", notes = "获取客户车产信息")
    public ResponseResult getBasicCustomerCar(@RequestParam Long carId, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCustomerCar basicCustomerCar = basicCustomerCarService.findById(carId);
            return new ResponseResult(ResponseResult.Status.SUCCESS, basicCustomerCar);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, "系统异常");
        }

    }

    /**
     * @auther: dwx
     * @Description:客户车产列表
     */
    @GetMapping("/getBasicCustomerCarList/{customerId}")
    @ApiOperation(value = "客户车产列表", notes = "客户车产列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constants.AUTHORIZATION, value = Constants.AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
    })
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Correct response", response = BasicCustomerCarModel.class)})
    public ResponseResult getBasicCustomerCarList(@PathVariable("customerId") @ApiParam(value = "客户ID", required = true) Long customerId, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser sysUser = getUserByAuth(authorization);
            if (Objects.isNull(sysUser)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<Map<String, Object>> resultList = basicCustomerCarService.findCustomerCarList(customerId);
            return new ResponseResult(ResponseResult.Status.SUCCESS, (resultList));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, "系统异常");
        }

    }

    /**
     * @auther: dwx
     * @Description:客户车评估配置列表
     */
    @GetMapping("/getAssessmentConfigList")
    @ApiOperation(value = "客户车评估配置列表", notes = "客户车评估配置列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constants.AUTHORIZATION, value = Constants.AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
    })
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Correct response", response = BasicCustomerCarModel.class)})
    public ResponseResult getAssessmentConfigList( @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser sysUser = getUserByAuth(authorization);
            if (Objects.isNull(sysUser)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<HashMap> resultList = basicCustomerCarAssessmentService.findAssessmentConfigList();
            Map  <String,List> assessmentConfig = new HashMap();
            List configList = new ArrayList();
            resultList.forEach(e -> {
                String attrType = e.get("attrType").toString();
                List list = new ArrayList();
                if(Objects.nonNull(assessmentConfig.get(attrType))){
                    list = assessmentConfig.get(attrType);
                }
                list.add(e);
                assessmentConfig.put(e.get("attrType").toString(),list);
            });
            for(Map.Entry itor : assessmentConfig.entrySet()){
                Map map = new HashMap();
                map.put("configType",Integer.parseInt(itor.getKey().toString()));
                map.put("data",assessmentConfig.get(itor.getKey().toString()));
                configList.add(map);
            }
            return new ResponseResult(ResponseResult.Status.SUCCESS, (configList));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, "系统异常");
        }

    }

    /**
     * @auther: dwx
     * @Description:新增评估
     */
    @PostMapping("/addBasicCustomerAssessment")
    @ApiOperation(value = "新增评估", notes = "新增评估")
    public ResponseResult<BasicCustomerCarAssessmentModel> addBasicCustomerAssessment(@RequestBody BasicCustomerCarAssessmentModel basicCustomerCarAssessmentModel,
                                                                                      @ApiIgnore @CurrentUser SysUser sysUser,
                                                                                      @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            basicCustomerCarAssessmentService.addBasicCustomerAssessment(basicCustomerCarAssessmentModel, sysUser);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "评估成功");

            }catch (RuntimeException e){
            logger.error(e.getMessage(),e);
            return new ResponseResult(ResponseResult.Status.FAILURE,e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, "系统异常");
        }

    }

    /**
     * @auther: dwx
     * @Description:删除评估订单
     */
    @DeleteMapping("/deleteBasicCarAssessment")
    @ApiOperation(value = "删除评估订单", notes = "删除评估订单")
    public ResponseResult deleteBasicCarAssessment(@RequestParam Long id, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        SysUser user = getUserByAuth(authorization);
        if (Objects.isNull(user)) {
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
        }
        basicCustomerCarAssessmentService.deleteById(id);
        return new ResponseResult(ResponseResult.Status.SUCCESS, "ok");
    }


    /**
     * @auther: dwx
     * @Description:客户评估列表
     */
    @GetMapping("/findCustomerAssessmentList")
    @ApiOperation(value = "客户评估列表", notes = "客户评估列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constants.AUTHORIZATION, value = Constants.AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                    value = "页数 (1..N)"),
            @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                    value = "每页大小")
    })
    public ResponseResult<CustomerAssessmentListModel> findCustomerAssessmentList(@ApiIgnore PageParam pageParam, @RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                                     @RequestParam(value = "customerName", required = false) @ApiParam(value = "客户姓名") String customerName,
                                                     @RequestParam(value = "carNo", required = false) @ApiParam(value = "车牌号") String carNo,
                                                     @RequestParam(value = "assessmentStatus", required = false) @ApiParam(value = "评估状态") Integer assessmentStatus) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
            List<CustomerAssessmentListModel> listModels = basicCustomerCarAssessmentService.findCustomerAssessmentList(customerName, carNo, assessmentStatus);
            return new ResponseResult(ResponseResult.Status.SUCCESS, new PageInfo<>(listModels));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, "系统异常");
        }
    }

    /**
     * @auther: dwx
     * @Description:查看评估报告列表
     */
    @GetMapping("/findAssessmentReportList")
    @ApiOperation(value = "查看评估报告列表", notes = "查看评估报告列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constants.AUTHORIZATION, value = Constants.AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                    value = "页数 (1..N)"),
            @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                    value = "每页大小")
    })
    public ResponseResult<CustomerAssessmentReportModel> findAssessmentReportList(@ApiIgnore PageParam pageParam, @RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                                   @RequestParam(value = "carId", required = true) @ApiParam(value = "车辆id") Long carId) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
            List<CustomerAssessmentReportModel> list = basicCustomerCarAssessmentService.findAssessmentReportList(carId);
            return new ResponseResult(ResponseResult.Status.SUCCESS, new PageInfo<>(list));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, "系统异常");
        }
    }

    /**
     * @auther: dwx
     * @Description:查看评估详情
     */
    @GetMapping("/findAssessmentReportInfo")
    @ApiOperation(value = "查看评估详情", notes = "查看评估详情")
    public ResponseResult<CustomerAssessmentInfoModel> findAssessmentReportInfo(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                                   @RequestParam(value = "assessmentReportId", required = true) @ApiParam(value = "评估报告id") Long assessmentReportId) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            CustomerAssessmentInfoModel assessmentReportInfo = basicCustomerCarAssessmentService.findAssessmentReportInfo(assessmentReportId);
            List<CarAttributeModel> allCarAttrValue = basicCustomerCarAssessmentService.findAllCarAttrValue(assessmentReportId);
            List<CustomerAssessmentInfo> customerAssessmentInfoList = new ArrayList<>();
            if (allCarAttrValue.isEmpty()) {
                assessmentReportInfo.setCarAttributeModels(customerAssessmentInfoList);
                return new ResponseResult<>(ResponseResult.Status.SUCCESS, assessmentReportInfo);
            }
            List<CarAttributeModel> list1 = Lists.newArrayList();
            List<CarAttributeModel> list2 = Lists.newArrayList();
            List<CarAttributeModel> list3 = Lists.newArrayList();
            List<CustomerAssessmentInfo> assessmentInfoList = Lists.newArrayList();
            for (CarAttributeModel carAttributeModel : allCarAttrValue) {
                if (Objects.equals(carAttributeModel.getAttrType(),BasicCarAttribute.AttrType.OUTWARD.getValue())) {
                    list1.add(carAttributeModel);
                }
                if (Objects.equals(carAttributeModel.getAttrType(), BasicCarAttribute.AttrType.INWARD.getValue())) {
                    list2.add(carAttributeModel);
                }
                if (Objects.equals(carAttributeModel.getAttrType(), BasicCarAttribute.AttrType.BASE.getValue())){
                    list3.add(carAttributeModel);
                }
            }
            CustomerAssessmentInfo info1 = new CustomerAssessmentInfo();
            info1.setConfigType(BasicCarAttribute.AttrType.OUTWARD.getValue());
            info1.setData(list1);
            CustomerAssessmentInfo info2 = new CustomerAssessmentInfo();
            info2.setConfigType(BasicCarAttribute.AttrType.INWARD.getValue());
            info2.setData(list2);
            CustomerAssessmentInfo info3 = new CustomerAssessmentInfo();
            info3.setConfigType(BasicCarAttribute.AttrType.BASE.getValue());
            info3.setData(list3);
            assessmentInfoList.add(info1);
            assessmentInfoList.add(info2);
            assessmentInfoList.add(info3);
            assessmentReportInfo.setCarAttributeModels(assessmentInfoList);
            return new ResponseResult(ResponseResult.Status.SUCCESS, assessmentReportInfo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, "系统异常");
        }
    }

    @GetMapping("/getCustomerCarList/{customerId}")
    @ApiOperation(value = "获取客户未抵押车产", notes = "获取客户未抵押车产")
    public ResponseResult<List<BasicCustomerCarModel>> getCustomerCarList(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                                                          @PathVariable("customerId") @ApiParam("客户ID") Long customerId) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            Example example = new Example(BasicCustomerCar.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("customerId", customerId);
            criteria.andIsNull("orderId");
            List<BasicCustomerCar> customerCars = basicCustomerCarService.selectByExample(example);
           List<BasicCustomerCarModel> carModels = customerCars.stream()
                   .map(customerCar -> modelMapper.map(customerCar, BasicCustomerCarModel.class))
                    .collect(Collectors.toList());
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, carModels);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }

    }

    /**
     * @auther: dwx
     * @Description:获取押品基础信息
     */
    @GetMapping("/findCustomerCarInfo")
    @ApiOperation(value = "获取押品基础信息",notes = "获取押品基础信息")
    public ResponseResult<CustomerCarInfoModel> findCustomerCarInfo(@RequestParam(value = "carId",required = true) @ApiParam("车辆id")Long carId,
                                                                    @RequestHeader(value = Constants.AUTHORIZATION) String authorization){
        try{
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            CustomerCarInfoModel customerCarInfoModel = customerCarAssessmentMapper.findCustomerCarInfo(carId);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS,customerCarInfoModel);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE,"系统异常");
        }

    }

}
