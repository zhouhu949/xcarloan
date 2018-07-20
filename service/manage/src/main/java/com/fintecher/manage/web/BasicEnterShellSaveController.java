package com.fintecher.manage.web;

import com.fintecher.common.vo.manage.ResponseResult;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.service.BasicEnterShellSaveService;
import com.fintecher.manage.service.UserService;
import com.fintecher.manage.vo.AddOrderCarStockParams;
import com.fintecher.manage.vo.BasicEnterShellSaveParams;
import com.fintecher.manage.vo.BasicGetOrderCarDetil;
import com.fintecher.manage.vo.PageParam;
import com.fintecher.util.Constants;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Objects;

/**
 * @author ZhangYaJun
 * @Title: BasicCustomerOrderController
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/21 0021上午 10:23
 */

@RestController
@RequestMapping("/basicEnterShellSaveController")
@Api(description = "进销存管理")
public class BasicEnterShellSaveController extends BaseController {

   @Autowired
    private BasicEnterShellSaveService basicEnterShellSaveService;
   @Autowired
   private UserService userService;

    @GetMapping("/queryOutSideList")
    @ApiOperation(value = "外采列表", notes = "外采列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                    value = "页数 (1..N)", required = true),
            @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                    value = "每页大小", required = true),
            @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                    value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
    })
    public ResponseResult queryOutSideList(@RequestHeader(value = Constants.AUTHORIZATION) String authorization, @ApiIgnore PageParam pageParam, @RequestParam(required = false) String orderNo, @RequestParam(required = false) String customerName, @RequestParam(required = false) Long stockStatus) {

        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<String> allDataAuth = userService.findAllDataAuth(user.getId());
            List<String> allExceptDataAuth = userService.findAllExceptDataAuth(user.getId());
            PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
           List<BasicEnterShellSaveParams> basicEnterShellSaveList = basicEnterShellSaveService.getOutSideList(orderNo, customerName, stockStatus, user,allDataAuth,allExceptDataAuth);

            return new ResponseResult(ResponseResult.Status.SUCCESS, new PageInfo<>(basicEnterShellSaveList));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/getOrderCarById/{id}")
    @ApiOperation(value = "查看详情", notes = "查看详情")
    public ResponseResult<BasicGetOrderCarDetil> getOrderCarById(@RequestHeader(value = Constants.AUTHORIZATION) String authorization, @PathVariable("id") @ApiParam(value = "订单ID", required = true) Long id) {

        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicGetOrderCarDetil orderCarById = basicEnterShellSaveService.getOrderCarById(id);
            return new ResponseResult(ResponseResult.Status.SUCCESS, orderCarById);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }


    @PostMapping("/addOrderCarStock")
    @ApiOperation(value = "外采时添加库存", notes = "外采时添加库存")
    public ResponseResult getOrderCarById(@RequestHeader(value = Constants.AUTHORIZATION) String authorization, @Validated @RequestBody AddOrderCarStockParams addOrderCarStockParams) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
           basicEnterShellSaveService.updateOrderCarStock(addOrderCarStockParams, user);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "添加成功");

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

}
