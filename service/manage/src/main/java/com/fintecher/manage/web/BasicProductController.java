package com.fintecher.manage.web;

import com.fintecher.common.vo.manage.ResponseResult;
import com.fintecher.entity.BasicProduct;
import com.fintecher.entity.BasicProductRepayTemplate;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.service.BasicProductRepayTemplateService;
import com.fintecher.manage.service.BasicProductService;
import com.fintecher.manage.vo.*;
import com.fintecher.util.Constants;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/basicProductController")
@Api(description = "车型产品管理")
public class BasicProductController extends BaseController {
    @Resource
    BasicProductService basicProductService;
    @Resource
    BasicProductRepayTemplateService basicProductRepayTemplateService;

    @PostMapping("/addBasicProduct")
    @ApiOperation(value = "新增车型产品", notes = "新增车型产品")
    public ResponseResult addBasicProduct(
            @Validated @RequestBody AddProductParams addProductParams,
            @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicProduct basicProduct = new BasicProduct();
            BeanUtils.copyProperties(addProductParams, basicProduct);
            basicProduct.setOrgId(user.getOrgId());
            basicProduct.setProductStatus(BasicProduct.ProductStatus.PUBLISHED_NO.getValue());
            basicProductService.save(basicProduct);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "新增成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @DeleteMapping("/deleteBasicProduct/{id}")
    @ApiOperation(value = "删除车型产品同时删除车型产品下的还款计划模板", notes = "删除车型产品同时删除车型产品下的还款计划模板")
    public ResponseResult deleteBasicProduct(
            @PathVariable("id") @ApiParam(value = "车型产品id", required = true) Long id,
            @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {

        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            basicProductService.deleteById(id);
            basicProductRepayTemplateService.deleteByProductId(id);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "删除成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @PostMapping("/editBasicProduct")
    @ApiOperation(value = "修改车型产品", notes = "修改车型产品")
    public ResponseResult editBasicProduct(@Validated @RequestBody EditProductParams editProductParams,
                                           @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {

        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicProduct basicProduct = new BasicProduct();
            BeanUtils.copyProperties(editProductParams, basicProduct);
            basicProductService.updateSelective(basicProduct);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/findBasicProduct/{id}")
    @ApiOperation(value = "根据车型产品Id获取车型产品（单条数据查询）", notes = "根据车型产品Id获取车型产品（单条数据查询）")
    public ResponseResult getBasicProduct(
            @PathVariable("id") @ApiParam(value = "车型产品id", required = true) Long id,
            @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {

        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }

            BasicProduct basicProduct = basicProductService.findById(id);
            return new ResponseResult(ResponseResult.Status.SUCCESS, basicProduct);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/findBasicProductList/{carId}")
    @ApiOperation(value = "根据车型Id获取车型产品列表(分页查询)", notes = "根据车型Id获取车型产品列表(分页查询)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                    value = "页数 (1..N)"),
            @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                    value = "每页大小"),
            @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                    value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
    })
    public ResponseResult findBasicProductList(
            @PathVariable("carId") @ApiParam(value = "车型Id", required = true) Long carId,
            @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<BasicProductModel> list = basicProductService.selectBasicProductList(carId);
            PageInfo<BasicProductModel> pageInfo = new PageInfo<>(list);
            return new ResponseResult(ResponseResult.Status.SUCCESS, pageInfo);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @PostMapping("/editBasicProductConfig")
    @ApiOperation(value = "车型配置", notes = "车型配置")
    public ResponseResult editBasicProductConfig(
            @Validated @RequestBody EditProductConfigParams editProductConfigParams,
            @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {

        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicProduct basicProduct = new BasicProduct();
            basicProduct.setId(editProductConfigParams.getId());
            basicProduct.setConfigId(editProductConfigParams.getConfigId());
            basicProductService.update(basicProduct);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @PostMapping("/editBasicProductScheme")
    @ApiOperation(value = "还款方案配置", notes = "还款方案配置")
    public ResponseResult editBasicProductScheme(
            @Validated @RequestBody EditProductSchemeParams editProductSchemeParams,
            @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {

        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicProduct basicProduct = new BasicProduct();
            basicProduct.setId(editProductSchemeParams.getId());
            basicProduct.setSchemeId(editProductSchemeParams.getSchemeId());
            basicProductService.update(basicProduct);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/publishedBasicProduct/{id}")
    @ApiOperation(value = "发布车型产品", notes = "发布车型产品")
    public ResponseResult publishedBasicProduct(
            @PathVariable("id") @ApiParam(value = "车型产品Id", required = true) Long id,
            @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {

        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicProduct product = basicProductService.findById(id);
                BasicProductRepayTemplate template = new BasicProductRepayTemplate();
                template.setProductId(id);
                List<BasicProductRepayTemplate> list = basicProductRepayTemplateService.findListByWhere(template);
                if(list.isEmpty()) {
                    product.setProductStatus(BasicProduct.ProductStatus.PUBLISHED_YES.getValue());
                    template.setOperator(user.getId());
                    template.setOperatorTime(new Date());
                    basicProductRepayTemplateService.addCarScheme(template);
                }
                    basicProductService.updateSelective(product);
                    return new ResponseResult(ResponseResult.Status.SUCCESS, "发布成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/cancelPublishedBasicProduct/{id}")
    @ApiOperation(value = "取消发布车型产品", notes = "取消发布车型产品")
    public ResponseResult cancelPublishedBasicProduct(
            @PathVariable("id") @ApiParam(value = "车型产品Id", required = true) Long id,
            @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {

        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicProduct product = basicProductService.findById(id);
            if (product.getSchemeId() != null) {
                product.setProductStatus(BasicProduct.ProductStatus.PUBLISHED_NO.getValue());
                basicProductService.updateSelective(product);
                return new ResponseResult(ResponseResult.Status.SUCCESS, "取消成功");
            } else {
                return new ResponseResult(ResponseResult.Status.FAILURE, "该产品没有方案，请绑定方案再发布");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/queryReleaseProductByCar")
    @ApiOperation(value = "根据车型分页获取车型产品", notes = "根据车型分页获取车型产品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                    value = "页数 (1..N)", required = true),
            @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                    value = "每页大小", required = true),
            @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                    value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
    })
    public ResponseResult<PageInfo<ProductSchemeModel>> queryReleaseProductByCar(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                                                                 @ApiIgnore PageParam pageParam,
                                                                                 @RequestParam @ApiParam(value = "车型Id", required = true) Long carId,
                                                                                 @RequestParam(required = false) @ApiParam("方案名称") String schemeName) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
            List<ProductSchemeModel> basicProductModels = basicProductService.queryReleaseProductByCar(carId, schemeName);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, new PageInfo<>(basicProductModels));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

}
