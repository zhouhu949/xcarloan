package com.fintecher.manage.web;

import com.fintecher.common.vo.manage.ResponseResult;
import com.fintecher.entity.BasicStockCar;
import com.fintecher.entity.BasicSupplier;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.service.BasicStockCarService;
import com.fintecher.manage.service.BasicSupplierService;
import com.fintecher.manage.vo.BasicSupplierParams;
import com.fintecher.manage.vo.BasicSupplierSearch;
import com.fintecher.manage.vo.EditBasicSupplierParams;
import com.fintecher.manage.vo.PageParam;
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

/**
 * auther:zhangyajun
 * date: 20117 6 .14
 * desc: 基础数据 供应商管理
 */

@RestController
@RequestMapping("/basicSupplierController")
@Api(description = "供应商管理")
public class BasicSupplierController extends BaseController {

   @Autowired
   private BasicSupplierService basicSupplierService;

   @Autowired
   private BasicStockCarService basicStockCarService;

   @GetMapping("/queryBasicSupplierPage")
   @ApiOperation(value = "查询供应商列表 ")
   @ApiImplicitParams({
           @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                   value = "页数 (1..N)", required = true),
           @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                   value = "每页大小", required = true),
           @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                   value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
   })
   public ResponseResult queryBasicSupplierPage(BasicSupplierSearch basicSupplierSearch, @ApiIgnore PageParam pageParam, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {

      SysUser user = getUserByAuth(authorization);
      if (Objects.isNull(user))
      {
         return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
      }
      PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
      List<BasicSupplier> supplierInfo = basicSupplierService.getSupplierInfo(basicSupplierSearch, user.getOrgId());
      return new ResponseResult(ResponseResult.Status.SUCCESS, new PageInfo<>(supplierInfo));
   }


   @GetMapping("/getBasicSupplierList")
   @ApiOperation(value = "查询供应商列表无分页 ")

   public ResponseResult getBasicSupplierList(@RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
      try
      {
         SysUser user = getUserByAuth(authorization);
         if (Objects.isNull(user))
         {
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
         }
         Example example1 = new Example(BasicSupplier.class);
         Example.Criteria criteria = example1.createCriteria();
         criteria.andEqualTo("orgId", user.getOrgId());
         List<BasicSupplier> supplierInfo = basicSupplierService.selectByExample(example1);
         return new ResponseResult(ResponseResult.Status.SUCCESS, supplierInfo);
      } catch (Exception e)
      {
         logger.error(e.getMessage(), e);
         return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
      }

   }


   /**
    * @param id
    * @param authorization 根据供应商ID查找供应商
    * @return
    */
   @GetMapping("/getBasicSupplierById/{id}")
   @ApiOperation(value = "根据供应商ID查找供应商", notes = "根据供应商ID查找供应商")
   public ResponseResult getBasicSupplierById(@PathVariable("id") @ApiParam(value = "供应商ID", required = true) Long id, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
      try
      {
         SysUser user = getUserByAuth(authorization);
         if (Objects.isNull(user))
         {
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
         }
         BasicSupplier supplier = basicSupplierService.findById(id);
         return new ResponseResult(ResponseResult.Status.SUCCESS, supplier);
      } catch (Exception e)
      {
         logger.error(e.getMessage(), e);
         return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);

      }


   }


   /**
    * 修改供应商信息
    */

   @PutMapping("/editBasicSupplier")
   @ApiOperation(value = "修改供应商", notes = "修改供应商")
   public ResponseResult editBasicSupplierInfo(@Validated @RequestBody EditBasicSupplierParams basicSupplierParams, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
      try
      {
         SysUser user = getUserByAuth(authorization);
         if (Objects.isNull(user))
         {
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
         }
         BasicSupplier supplier = new BasicSupplier();
         supplier.setOperator(user.getId());
         supplier.setOperatorTime(ZWDateUtil.getNowDateTime());
         BeanUtils.copyProperties(basicSupplierParams, supplier);
         String remark = basicSupplierParams.getRemark();
         if (Objects.nonNull(remark))
         {
            supplier.setRemark(remark);
         }
         basicSupplierService.updateSelective(supplier);
         return new ResponseResult(ResponseResult.Status.SUCCESS, "修改成功");
      } catch (Exception e)
      {
         logger.error(e.getMessage(), e);
         return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
      }

   }


   /**
    * 增加供应商信息
    */

   @PostMapping("/addBasicSupplier")
   @ApiOperation(value = "增加供应商", notes = "增加供应商")
   public ResponseResult addBasicSupplier(@Validated @RequestBody BasicSupplierParams basicSupplierParams, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
      try
      {
         SysUser user = getUserByAuth(authorization);
         if (Objects.isNull(user))
         {
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
         }
         BasicSupplier supplier = new BasicSupplier();
         supplier.setOrgId(user.getOrgId());
         supplier.setOperator(user.getId());
         supplier.setOperatorTime(ZWDateUtil.getNowDateTime());
         BeanUtils.copyProperties(basicSupplierParams, supplier);
         String remark = basicSupplierParams.getRemark();
         if (Objects.nonNull(remark))
         {
            supplier.setRemark(remark);
         }
         basicSupplierService.save(supplier);
         return new ResponseResult(ResponseResult.Status.SUCCESS, "增加成功");
      } catch (Exception e)
      {
         logger.error(e.getMessage(), e);
         return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
      }

   }


   /**
    * @param id
    * @param authorization
    * @return desc :删除供应商
    */
   @DeleteMapping("/deleteBasicSupplier/{id}")
   @ApiOperation(value = "删除供应商", notes = "删除供应商")
   public ResponseResult deleteBasicSupplier(@PathVariable("id") @ApiParam(value = "供应商ID", required = true) Long id,
                                             @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {

      try
      {
         SysUser user = getUserByAuth(authorization);
         if (Objects.isNull(user))
         {
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
         }
         Example example = new Example(BasicStockCar.class);
         Example.Criteria criteria = example.createCriteria();
         criteria.andEqualTo("supplierId", id);
         if (basicStockCarService.selectCountByExample(example) > 0)
         {
            return new ResponseResult(ResponseResult.Status.FAILURE, "该供应商下存在库存不允许删除");
         } else
         {
            basicSupplierService.deleteById(id);

         }
         return new ResponseResult(ResponseResult.Status.SUCCESS, "删除成功");
      } catch (Exception e)
      {
         logger.error(e.getMessage(), e);
         return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
      }
   }


}
