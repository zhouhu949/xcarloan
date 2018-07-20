package com.fintecher.manage.web;

import com.fintecher.common.vo.manage.ResponseResult;
import com.fintecher.entity.BasicStockCar;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.service.BasicCarModelService;
import com.fintecher.manage.service.BasicStockCarService;
import com.fintecher.manage.service.BasicSupplierService;
import com.fintecher.manage.vo.*;
import com.fintecher.util.Constants;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Objects;

/**
 * @author ZhangYaJun
 * @Title: BasicStockCarController
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/15 0015上午 9:45
 */

@RestController
@RequestMapping("/basicStockCarManageController")
@Api(description = "库存管理")
public class BasicStockCarController extends BaseController {

   @Autowired
   private BasicStockCarService basicStockCarService;

   @Autowired
   private BasicSupplierService basicSupplierService;

   @Autowired
   private BasicCarModelService basicCarModelService;


   @GetMapping("/findAllStockCarList")
   @ApiOperation(value = "车辆库存列表")
   @ApiImplicitParams({
           @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                   value = "页数 (1..N)", required = true),
           @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                   value = "每页大小", required = true),
           @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                   value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
   })
   public ResponseResult findAllStockCarList(@RequestParam(required = true) Long modelId, @RequestParam(required = false) Long supplierId, @ApiIgnore PageParam pageParam, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
      try
      {
         SysUser user = getUserByAuth(authorization);
         if (Objects.isNull(user))
         {
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
         }
         PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
         BasicStockCarSearch basicStockCarSearch = new BasicStockCarSearch();
         basicStockCarSearch.setModelId(modelId);
         basicStockCarSearch.setSupplierId(supplierId);
         List<BasicStockCarListParams> basicStockCarsList = basicStockCarService.findAllStockCarList(basicStockCarSearch, user.getOrgId());
         return new ResponseResult<>(ResponseResult.Status.SUCCESS, new PageInfo<>(basicStockCarsList));
      } catch (Exception e)
      {
         logger.error(e.getMessage(), e);
         return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
      }

   }


   @GetMapping("/findBasicStockById/{id}")
   @ApiOperation(value = "根据库存ID查找库存")
   public ResponseResult findBasicStockById(@PathVariable("id") @ApiParam(value = "库存ID", required = true) Long id, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
      try
      {
         SysUser user = getUserByAuth(authorization);
         if (Objects.isNull(user))
         {
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
         }
         BasicStockCar stockCar = basicStockCarService.findById(id);
         if (Objects.isNull(stockCar))
         {
            return new ResponseResult(ResponseResult.Status.FAILURE, "无返回结果");
         }
         return new ResponseResult(ResponseResult.Status.SUCCESS, stockCar);
      } catch (Exception e)
      {
         logger.error(e.getMessage(), e);
         return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
      }
   }


   @PostMapping("/addBasicStock")
   @ApiOperation(value = "新增库存")
   public ResponseResult addBasicStock(@Validated @RequestBody BasicStockCarParams basicStockCarParams, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {

      try
      {
         SysUser user = getUserByAuth(authorization);
         if (Objects.isNull(user))
         {
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
         }
         if (Objects.isNull(basicSupplierService.findById(basicStockCarParams.getSupplierId())))
         {
            return new ResponseResult(ResponseResult.Status.FAILURE, "供应商不存在");
         }
         if (Objects.isNull(basicCarModelService.findById(basicStockCarParams.getModelId())))
         {
            return new ResponseResult(ResponseResult.Status.FAILURE, "车型不存在");
         }
         basicStockCarService.addBasicStock(basicStockCarParams, user);
         return new ResponseResult(ResponseResult.Status.SUCCESS, "增加成功");
      } catch (Exception e)
      {
         logger.error(e.getMessage(), e);
         return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
      }
   }



   @PutMapping("/editCsrSupplierLoan")
   @ApiOperation(value = "修改供应商是否放款")
   public ResponseResult editCsrSupplierLoan(@Validated @RequestBody EditStockSupplierLoanParams editStockSupplierLoanParams, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {

      try
      {
         SysUser user = getUserByAuth(authorization);
         if (Objects.isNull(user))
         {
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
         }
         Integer hasSupplierLoan = editStockSupplierLoanParams.getHasSupplierLoan();
         if (hasSupplierLoan.equals(BasicStockCar.HasSupplierLoan.BASIC_STOCKSTATUS_YES.getValue()) ||
                 hasSupplierLoan.equals(BasicStockCar.HasSupplierLoan.BASIC_STOCKSTATUS_NO.getValue()))
         {
            BasicStockCar basicStockCar = new BasicStockCar();
            BeanUtils.copyProperties(editStockSupplierLoanParams, basicStockCar);
            basicStockCarService.updateSelective(basicStockCar);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "修改成功!");
         }
         return new ResponseResult(ResponseResult.Status.FAILURE, "修改还款方式不存在");

      } catch (Exception e)
      {
         logger.error(e.getMessage(), e);
         return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);

      }
   }


   @PutMapping("/updateCarStockStatus")
   @ApiOperation(value = "根据库存ID修改库存状态")
   public ResponseResult updateCarStockStatus(@Validated @RequestBody UpdateCarStocktusParams updateCarStocktusParams, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
      try
      {
         SysUser user = getUserByAuth(authorization);
         if (Objects.isNull(user))
         {
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
         }
         return basicStockCarService.editCarStockStatus(updateCarStocktusParams.getId(), user, updateCarStocktusParams.getOrderId());
      } catch (Exception e)
      {
         logger.error(e.getMessage(), e);
         return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);

      }
   }


}
