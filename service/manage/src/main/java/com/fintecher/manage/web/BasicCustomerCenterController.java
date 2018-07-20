package com.fintecher.manage.web;

import com.fintecher.common.vo.manage.ResponseResult;
import com.fintecher.entity.BasicCustomer;
import com.fintecher.entity.BasicCustomerOrder;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.mapper.BasicCustomerBlacklistRecordMapper;
import com.fintecher.manage.mapper.BasicCustomerMapper;
import com.fintecher.manage.service.BasicCustomerOrderService;
import com.fintecher.manage.service.BasicCustomerService;
import com.fintecher.manage.vo.*;
import com.fintecher.util.Constants;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Objects;

/**
 * @author Dongyuan
 * @Title: BasicCustomerController
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/19 0019下午 14:54
 */

@RestController
@RequestMapping("/BasicCustomerCenterController")
@Api(description = "客户中心管理")
public class BasicCustomerCenterController extends BaseController {


   @Autowired
   private BasicCustomerService basicCustomerService;
   @Autowired
   private BasicCustomerMapper basicCustomerMapper;
   @Autowired
   private BasicCustomerBlacklistRecordMapper basicCustomerBlacklistRecordMapper;
   @Autowired
   private BasicCustomerOrderService basicCustomerOrderService;

   @GetMapping("/findPotentialCustomerList")
   @ApiOperation(value = "意向客户列表")
   @ApiImplicitParams({
           @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                   value = "页数 (1..N)", required = true),
           @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                   value = "每页大小", required = true),
           @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                   value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
   })
   public ResponseResult findPotentialCustomerList(@RequestParam(required = false) @ApiParam(value = "客户姓名") String customerName,
                                                   @RequestParam(required = false) @ApiParam(value = "手机号") String customerPhone,
                                                   @RequestParam(required = false) @ApiParam(value = "身份证号") String idCard,
                                                   @ApiIgnore PageParam pageParam,
                                                   @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
      try
      {
         SysUser user = getUserByAuth(authorization);
         if (Objects.isNull(user))
         {
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
         }
         PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
         BasicCustomerSearch basicCustomerSearch = new BasicCustomerSearch();
         basicCustomerSearch.setCustomerName(customerName);
         basicCustomerSearch.setCustomerPhone(customerPhone);
         basicCustomerSearch.setIdCard(idCard);
         List allCustomerCarList = basicCustomerService.findCustomerIntentionList(basicCustomerSearch, user.getOrgId(),BasicCustomer.CustomerStatus.CUSTOMER_STATUS_YXKH.getValue());
         return new ResponseResult<>(ResponseResult.Status.SUCCESS, new PageInfo<>(allCustomerCarList));
      } catch (Exception e)
      {
         logger.error(e.getMessage(), e);
         return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
      }

   }
   @GetMapping("/findFormalCustomerList")
   @ApiOperation(value = "正式客户列表")
   @ApiImplicitParams({
           @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                   value = "页数 (1..N)", required = true),
           @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                   value = "每页大小", required = true),
           @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                   value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
   })
   public ResponseResult findFormalCustomerList(@RequestParam(value = "customerName", required = false) @ApiParam(value = "客户姓名") String customerName, @RequestParam(value = "customerPhone", required = false) @ApiParam(value = "手机号") String customerPhone, @RequestParam(value = "idCard", required = false) @ApiParam(value = "身份证号") String idCard, @ApiIgnore PageParam pageParam, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
      try
      {
         SysUser user = getUserByAuth(authorization);
         if (Objects.isNull(user))
         {
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
         }
         PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
         BasicCustomerSearch basicCustomerSearch = new BasicCustomerSearch();
         basicCustomerSearch.setCustomerName(customerName);
         basicCustomerSearch.setCustomerPhone(customerPhone);
         basicCustomerSearch.setIdCard(idCard);
         List allCustomerCarList = basicCustomerService.findCustomerList(basicCustomerSearch, user.getOrgId(),BasicCustomer.CustomerStatus.CUSTOMER_STATUS_ZSKH.getValue());
         return new ResponseResult<>(ResponseResult.Status.SUCCESS, new PageInfo<>(allCustomerCarList));
      } catch (Exception e)
      {
         logger.error(e.getMessage(), e);
         return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
      }

   }
   @GetMapping("/findNotBlackCustomerList")
   @ApiOperation(value = "非黑名单客户列表")
   @ApiImplicitParams({
           @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                   value = "页数 (1..N)", required = true),
           @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                   value = "每页大小", required = true),
           @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                   value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
   })
   public ResponseResult findNotBlackCustomerList(@RequestParam(value = "customerName", required = false) @ApiParam(value = "客户姓名") String customerName, @RequestParam(value = "customerPhone", required = false) @ApiParam(value = "手机号") String customerPhone, @RequestParam(value = "idCard", required = false) @ApiParam(value = "身份证号") String idCard, @ApiIgnore PageParam pageParam, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
      try
      {
         SysUser user = getUserByAuth(authorization);
         if (Objects.isNull(user))
         {
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
         }
         PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
         BasicCustomerSearch basicCustomerSearch = new BasicCustomerSearch();
         basicCustomerSearch.setCustomerName(customerName);
         basicCustomerSearch.setCustomerPhone(customerPhone);
         basicCustomerSearch.setIdCard(idCard);
         List<BasicCustomerModel> allCustomerCarList = basicCustomerService.findNotBlackCustomerList(basicCustomerSearch, user.getOrgId());
         return new ResponseResult<>(ResponseResult.Status.SUCCESS, new PageInfo<>(allCustomerCarList));
      } catch (Exception e)
      {
         logger.error(e.getMessage(), e);
         return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
      }

   }
   @GetMapping("/findHistoryCustomerList")
   @ApiOperation(value = "历史客户列表")
   @ApiImplicitParams({
           @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                   value = "页数 (1..N)", required = true),
           @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                   value = "每页大小", required = true),
           @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                   value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
   })
   public ResponseResult findHistoryCustomerList(@RequestParam(value = "customerName", required = false) @ApiParam(value = "客户姓名") String customerName, @RequestParam(value = "customerPhone", required = false) @ApiParam(value = "手机号") String customerPhone, @RequestParam(value = "idCard", required = false) @ApiParam(value = "身份证号") String idCard, @ApiIgnore PageParam pageParam, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
      try
      {
         SysUser user = getUserByAuth(authorization);
         if (Objects.isNull(user))
         {
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
         }
         PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
         BasicCustomerSearch basicCustomerSearch = new BasicCustomerSearch();
         basicCustomerSearch.setCustomerName(customerName);
         basicCustomerSearch.setCustomerPhone(customerPhone);
         basicCustomerSearch.setIdCard(idCard);
         List allCustomerCarList = basicCustomerService.findCustomerList(basicCustomerSearch, user.getOrgId(),BasicCustomer.CustomerStatus.CUSTOMER_STATUS_LSKH.getValue());
         return new ResponseResult<>(ResponseResult.Status.SUCCESS, new PageInfo<>(allCustomerCarList));
      } catch (Exception e)
      {
         logger.error(e.getMessage(), e);
         return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
      }

   }
   @GetMapping("/findBlackCustomerList")
   @ApiOperation(value = "黑名单客户列表")
   @ApiImplicitParams({
           @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                   value = "页数 (1..N)", required = true),
           @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                   value = "每页大小", required = true),
           @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                   value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
   })
   public ResponseResult findBlackCustomerList(@RequestParam(value = "customerName", required = false) @ApiParam(value = "客户姓名") String customerName, @RequestParam(value = "customerPhone", required = false) @ApiParam(value = "手机号") String customerPhone, @RequestParam(value = "idCard", required = false) @ApiParam(value = "身份证号") String idCard, @ApiIgnore PageParam pageParam, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
      try
      {
         SysUser user = getUserByAuth(authorization);
         if (Objects.isNull(user))
         {
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
         }
         PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
         BasicCustomerSearch basicCustomerSearch = new BasicCustomerSearch();
         basicCustomerSearch.setCustomerName(customerName);
         basicCustomerSearch.setCustomerPhone(customerPhone);
         basicCustomerSearch.setIdCard(idCard);
         List allCustomerCarList = basicCustomerService.findCustomerList(basicCustomerSearch, user.getOrgId(),BasicCustomer.CustomerStatus.CUSTOMER_STATUS_HMD.getValue());
         return new ResponseResult<>(ResponseResult.Status.SUCCESS, new PageInfo<>(allCustomerCarList));
      } catch (Exception e)
      {
         logger.error(e.getMessage(), e);
         return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
      }

   }
   @GetMapping("/findGrayCustomerList")
   @ApiOperation(value = "灰名单客户列表")
   @ApiImplicitParams({
           @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                   value = "页数 (1..N)", required = true),
           @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                   value = "每页大小", required = true),
           @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                   value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
   })
   public ResponseResult findGrayCustomerList(@RequestParam(value = "customerName", required = false) @ApiParam(value = "客户姓名") String customerName, @RequestParam(value = "customerPhone", required = false) @ApiParam(value = "手机号") String customerPhone, @RequestParam(value = "idCard", required = false) @ApiParam(value = "身份证号") String idCard, @ApiIgnore PageParam pageParam, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
      try
      {
         SysUser user = getUserByAuth(authorization);
         if (Objects.isNull(user))
         {
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
         }
         PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
         BasicCustomerSearch basicCustomerSearch = new BasicCustomerSearch();
         basicCustomerSearch.setCustomerName(customerName);
         basicCustomerSearch.setCustomerPhone(customerPhone);
         basicCustomerSearch.setIdCard(idCard);
         List allCustomerCarList = basicCustomerService.findCustomerList(basicCustomerSearch, user.getOrgId(),BasicCustomer.CustomerStatus.CUSTOMER_STATUS_HND.getValue());
         return new ResponseResult<>(ResponseResult.Status.SUCCESS, new PageInfo<>(allCustomerCarList));
      } catch (Exception e)
      {
         logger.error(e.getMessage(), e);
         return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
      }

   }
   @GetMapping("/findWhiteCustomerList")
   @ApiOperation(value = "黑名单客户列表")
   @ApiImplicitParams({
           @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                   value = "页数 (1..N)", required = true),
           @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                   value = "每页大小", required = true),
           @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                   value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
   })
   public ResponseResult findWhiteCustomerList(@RequestParam(value = "customerName", required = false) @ApiParam(value = "客户姓名") String customerName, @RequestParam(value = "customerPhone", required = false) @ApiParam(value = "手机号") String customerPhone, @RequestParam(value = "idCard", required = false) @ApiParam(value = "身份证号") String idCard, @ApiIgnore PageParam pageParam, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
      try
      {
         SysUser user = getUserByAuth(authorization);
         if (Objects.isNull(user))
         {
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
         }
         PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
         BasicCustomerSearch basicCustomerSearch = new BasicCustomerSearch();
         basicCustomerSearch.setCustomerName(customerName);
         basicCustomerSearch.setCustomerPhone(customerPhone);
         basicCustomerSearch.setIdCard(idCard);
         List allCustomerCarList = basicCustomerService.findCustomerList(basicCustomerSearch, user.getOrgId(),BasicCustomer.CustomerStatus.CUSTOMER_STATUS_BMD.getValue());
         return new ResponseResult<>(ResponseResult.Status.SUCCESS, new PageInfo<>(allCustomerCarList));
      } catch (Exception e)
      {
         logger.error(e.getMessage(), e);
         return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
      }

   }

   @GetMapping("/findCustomerList")
   @ApiOperation(value = "补填资料客户列表")
   @ApiImplicitParams({
           @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                   value = "页数 (1..N)", required = true),
           @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                   value = "每页大小", required = true),
           @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                   value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
   })
   public ResponseResult findCustomerList(@RequestParam(value = "customerName", required = false) @ApiParam(value = "客户姓名") String customerName, @RequestParam(value = "customerPhone", required = false) @ApiParam(value = "手机号") String customerPhone, @RequestParam(value = "idCard", required = false) @ApiParam(value = "身份证号") String idCard,
                                          @RequestParam(value = "customerStatus", required = false) @ApiParam(value = "客户状态") String customerStatus,
                                          @ApiIgnore PageParam pageParam, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
      try
      {
         SysUser user = getUserByAuth(authorization);
         if (Objects.isNull(user))
         {
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
         }
         PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
         BasicCustomerSearch basicCustomerSearch = new BasicCustomerSearch();
         basicCustomerSearch.setCustomerName(customerName);
         basicCustomerSearch.setCustomerPhone(customerPhone);
         basicCustomerSearch.setIdCard(idCard);
         List allCustomerList;
         if(!Objects.isNull(customerStatus) && !StringUtils.isBlank(customerStatus))
            allCustomerList = basicCustomerService.findCustomerList(basicCustomerSearch, user.getOrgId(),Integer.parseInt(customerStatus));
         else
            allCustomerList = basicCustomerService.findCustomerList(basicCustomerSearch, user.getOrgId(),null);
         return new ResponseResult<>(ResponseResult.Status.SUCCESS, new PageInfo<>(allCustomerList));
      } catch (Exception e)
      {
         logger.error(e.getMessage(), e);
         return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
      }

   }
   /**
    * @auther: dwx
    * @Description:订单记录
    */
      @GetMapping("/findBasicCustomerOrderList")
      @ApiOperation(value = "订单记录",notes = "订单记录")

      public ResponseResult findBasicCustomerOrderList(@RequestParam( value = "customerId",required = true ) @ApiParam(value = "客户id")Long customerId,
                                                       @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
         try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
               return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List list = basicCustomerMapper.findBasicCustomerOrderList(customerId);
            return new ResponseResult(ResponseResult.Status.SUCCESS,list);
         }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new ResponseResult(ResponseResult.Status.FAILURE,"系统异常");
         }
      }
    /**
     * @auther: dwx
     * @Description:获取客户黑白灰名单记录
     */
    @GetMapping("/findCustomerBlackListRecord")
    @ApiOperation(value = "获取客户黑白灰名单记录",notes = "获取客户黑白灰名单记录")

    public ResponseResult<BasicCustomerBlackListRecordModel> findCustomerBlackListRecord(@RequestParam( value = "customerId",required = true ) @ApiParam(value = "客户id")Long customerId,
                                                                                         @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try{
           SysUser user = getUserByAuth(authorization);
           if (Objects.isNull(user)) {
              return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
           }
            List list = basicCustomerBlacklistRecordMapper.queryCustomerBlackListRecord(customerId);
           return new ResponseResult(ResponseResult.Status.SUCCESS,list);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new ResponseResult(ResponseResult.Status.FAILURE,"系统异常");
        }

    }


   /**
    * @auther: dwx
    * @Description:黑名单客户移入意向客户
    */
   @PostMapping("/updateCustomerStatusBlack")
   @ApiOperation(value = "黑名单客户移入意向客户",notes = "黑名单客户移入意向客户")
   public ResponseResult updateCustomerStatusBlack(@RequestBody CustomerMobileModel basicCustomerModel, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
      try{
         SysUser user = getUserByAuth(authorization);
         if (Objects.isNull(user)) {
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
         }
         BasicCustomer basicCustomer = new BasicCustomer();
         basicCustomer.setId(basicCustomerModel.getCustomerId());
         basicCustomer.setCustomerStatus(BasicCustomer.CustomerStatus.CUSTOMER_STATUS_YXKH.getValue());
         basicCustomerService.updateSelective(basicCustomer);
         return new ResponseResult(ResponseResult.Status.SUCCESS,"移入成功");
      }catch (Exception e){
         logger.error(e.getMessage(),e);
         return new ResponseResult(ResponseResult.Status.FAILURE,"系统异常");
      }
   }

   /**
    * @auther: dwx
    * @Description:白/灰名单移出
    */
   @PostMapping("/updateCustomerStatusWhite")
   @ApiOperation(value = "白/灰名单移出",notes = "白/灰名单移出")
   public ResponseResult updateCustomerStatusWhite(@RequestBody CustomerMobileModel basicCustomerModel, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
      try{
         SysUser user = getUserByAuth(authorization);
         if (Objects.isNull(user)) {
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
         }
         BasicCustomer basicCustomer = new BasicCustomer();
         basicCustomer.setId(basicCustomerModel.getCustomerId());
         BasicCustomerOrder basicCustomerOrder = new BasicCustomerOrder();
         basicCustomerOrder.setCustomerId(basicCustomerModel.getCustomerId());
         List<BasicCustomerOrder> list = basicCustomerOrderService.findListByWhere(basicCustomerOrder);
         for (BasicCustomerOrder basicCustomerOrder1 : list) {
            if (!basicCustomerOrder1.getOrderStatus().equals(BasicCustomerOrder.OrderStatus.PENDING_CLOSED.getValue())) {
               basicCustomer.setCustomerStatus(BasicCustomer.CustomerStatus.CUSTOMER_STATUS_ZSKH.getValue());
               basicCustomerService.updateSelective(basicCustomer);
               return new ResponseResult(ResponseResult.Status.SUCCESS, "客户已移入正式客户列表");
            }else {
               basicCustomer.setCustomerStatus(BasicCustomer.CustomerStatus.CUSTOMER_STATUS_LSKH.getValue());
               basicCustomerService.updateSelective(basicCustomer);
               return new ResponseResult(ResponseResult.Status.SUCCESS, "客户已移入历史客户列表");
            }
         }
         return new ResponseResult(ResponseResult.Status.FAILURE,"该客户暂无订单，无法移出");
      }catch (Exception e){
         logger.error(e.getMessage(),e);
         return new ResponseResult(ResponseResult.Status.FAILURE,e.getMessage());
      }
   }

   /**
    * @auther: dwx
    * @Description:历史客户移入意向客户
    */
   @PostMapping("updateCustomerStatusHistoryByIntention")
   @ApiOperation(value ="历史客户移入意向客户" ,notes = "历史客户移入意向客户")
   public ResponseResult updateCustomerStatusHistoryByIntention(@RequestBody CustomerMobileModel basicCustomerModel, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
      try{
         SysUser user = getUserByAuth(authorization);
         if (Objects.isNull(user)) {
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
         }
         BasicCustomer basicCustomer = new BasicCustomer();
         basicCustomer.setId(basicCustomerModel.getCustomerId());
         basicCustomer.setCustomerStatus(BasicCustomer.CustomerStatus.CUSTOMER_STATUS_YXKH.getValue());
         basicCustomerService.updateSelective(basicCustomer);
         return new ResponseResult(ResponseResult.Status.SUCCESS,"移动成功");
      }catch (Exception e){
         logger.error(e.getMessage(),e);
         return new ResponseResult(ResponseResult.Status.FAILURE,"系统异常");
      }
   }

}


