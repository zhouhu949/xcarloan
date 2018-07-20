package com.fintecher.manage.web;

import com.fintecher.common.vo.manage.ResponseResult;
import com.fintecher.entity.*;
import com.fintecher.manage.service.BasicCarModelService;
import com.fintecher.manage.service.BasicProductRepayTemplateService;
import com.fintecher.manage.service.BasicProductService;
import com.fintecher.manage.service.BasicRepaySchemeService;
import com.fintecher.manage.vo.AddBasicProductParams;
import com.fintecher.manage.vo.BasicProductTemplateNotCar;
import com.fintecher.util.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/basicProductRepayTemplateController")
@Api(description = "产品还款计划模板")
public class BasicProductRepayTemplateController extends BaseController {
    @Resource
    BasicProductService basicProductService;
    @Resource
    private BasicRepaySchemeService basicRepaySchemeService;
    @Resource
    private BasicCarModelService basicCarModelService;
    @Resource
    private BasicProductRepayTemplateService basicProductRepayTemplateService;

    @PostMapping("/queryBasicProductCarScheme")
    @ApiOperation(value = "预览融资租赁产品还款计划", notes = "预览融资租赁产品还款计划")
    public ResponseResult queryBasicProductCarScheme(
            @Validated @RequestBody AddBasicProductParams addBasicProductParams,
            @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser userInfo = getUserByAuth(authorization);
            if (Objects.isNull(userInfo)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicProduct basicProduct = basicProductService.findById(addBasicProductParams.getId());
            if (basicProduct == null) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, "没有该车型产品");
            } else {
                BasicRepayScheme repayScheme = basicRepaySchemeService.findById(basicProduct.getSchemeId());
                if (repayScheme == null) {
                    return new ResponseResult<>(ResponseResult.Status.FAILURE, "该车型产品下的方案不存在");
                } else {
                    BasicCarModel carModel = basicCarModelService.findById(basicProduct.getConfigId());

                    if (carModel == null) {
                        return new ResponseResult<>(ResponseResult.Status.FAILURE, "该产品车型不存在");
                    } else {
                        Map<String, Object> list = basicProductRepayTemplateService.repaymentSchedule(repayScheme.getId(), carModel.getReferencePrice());
                        return new ResponseResult<>(ResponseResult.Status.SUCCESS, list);
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @PostMapping("/publishBasicProduct")
    @ApiOperation(value = "确认生成产品计划", notes = "确认生成产品计划")
    public ResponseResult publishBasicProduct(
            @Validated @RequestBody AddBasicProductParams addBasicProductParams,
            @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser userInfo = getUserByAuth(authorization);
            if (Objects.isNull(userInfo)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicProduct basicProduct = basicProductService.findById(addBasicProductParams.getId());
            if (basicProduct == null) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, "没有该车型产品");
            } else {
                BasicProductRepayTemplate template = new BasicProductRepayTemplate();
                template.setProductId(addBasicProductParams.getId());
                template.setRemark(addBasicProductParams.getRemark());
                template.setOperator(userInfo.getId());
                template.setOperatorTime(new Date());
                basicProductRepayTemplateService.addCarScheme(template);
                return new ResponseResult<>(ResponseResult.Status.SUCCESS, "发布成功");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }
    @PostMapping("/queryBasicLoanProductTemplate")
    @ApiOperation(value = "预览抵押贷款还款计划模板", notes = "预览抵押贷款还款计划模板")
    public ResponseResult queryBasicLoanProductTemplate(
            @Validated @RequestBody BasicProductTemplateNotCar basicProductTemplateNotCar,
            @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser userInfo = getUserByAuth(authorization);
            if (Objects.isNull(userInfo)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicRepayScheme repayScheme = basicRepaySchemeService.findById(basicProductTemplateNotCar.getSchemeId());
            if (repayScheme == null) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, "该方案不存在");
            } else {
                Map<String, Object> list = basicProductRepayTemplateService.repaymentSchedule(basicProductTemplateNotCar.getSchemeId(), basicProductTemplateNotCar.getMoney());
                return new ResponseResult<>(ResponseResult.Status.SUCCESS, list);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/queryBasicProductCarSchemeIsSave/{id}")
    @ApiOperation(value = "查询数据库融资租赁产品还款计划", notes = "查询数据库融资租赁产品还款计划")
    public ResponseResult queryBasicProductCarSchemeIsSave(
            @PathVariable("id") @ApiParam(value = "产品Id", required = true) Long id,
            @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser userInfo = getUserByAuth(authorization);
            if (Objects.isNull(userInfo)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicProduct basicProduct = basicProductService.findById(id);
            if (basicProduct == null) return new ResponseResult<>(ResponseResult.Status.FAILURE, "没有该车型产品");
            BasicRepayScheme repayScheme = basicRepaySchemeService.findById(basicProduct.getSchemeId());
            if (repayScheme == null) return new ResponseResult<>(ResponseResult.Status.FAILURE, "该车型产品下的方案不存在");
            BasicCarModel carModel = basicCarModelService.findById(basicProduct.getConfigId());
            if (carModel == null) return new ResponseResult<>(ResponseResult.Status.FAILURE, "该产品车型不存在");
            List<Map> list = basicProductRepayTemplateService.selectTemplate(id);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

}
