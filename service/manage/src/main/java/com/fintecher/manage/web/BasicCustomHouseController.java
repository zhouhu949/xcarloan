package com.fintecher.manage.web;

import com.fintecher.common.vo.manage.ResponseResult;
import com.fintecher.entity.BasicCustomerHouse;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.service.BasicCustomHouseService;
import com.fintecher.manage.vo.EditCustomHouseParams;
import com.fintecher.util.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/basicCustomHouseController")
@Api(description = "客户房产")
public class BasicCustomHouseController extends BaseController {

    @Autowired
    private BasicCustomHouseService basicCustomHouseService;

    @PostMapping("/addCustomHouse")
    @ApiOperation(value = "新增客户房产信息", notes = "新增客户房产信息")
    public ResponseResult addCustomHouse(@RequestHeader(value = "authorization") String authorization,
                                   @RequestBody EditCustomHouseParams editCustomHouseParams) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCustomerHouse basicCustomerHouse = new BasicCustomerHouse();
            BeanUtils.copyProperties(editCustomHouseParams,basicCustomerHouse);
            basicCustomerHouse.setCustomerId(editCustomHouseParams.getCustomerId());
            basicCustomHouseService.save(basicCustomerHouse);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, "新增成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }


    }


    /**
     * @auther:
     * @Description:删除客户房产信息
     */
    @DeleteMapping("/deleteCustomHouse")
    @ApiOperation(value = "删除客户房产信息",notes = "删除客户房产信息")
    public ResponseResult deleteCustomHouse(@RequestParam(required = true) Long id,@RequestHeader(value = Constants.AUTHORIZATION) String authorization){
        try{
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCustomerHouse basicCustomerHouse = basicCustomHouseService.findById(id);
            if(Objects.isNull(basicCustomerHouse)){
                return new ResponseResult(ResponseResult.Status.FAILURE,"无此房产，请重新输入");
            }
             basicCustomHouseService.deleteById(id);
            return new ResponseResult(ResponseResult.Status.SUCCESS,"删除成功");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new ResponseResult(ResponseResult.Status.FAILURE,"删除失败");
        }
    }

    /**
     * @auther:
     * @Description:修改客户房产信息
     */
    @PutMapping(value = "/updateCustomHouse")
    @ApiOperation(value = "修改客户房产信息", notes = "修改客户房产信息")
    public ResponseResult updateCustomHouse(@Validated @RequestBody EditCustomHouseParams editCustomHouseParams,
                                             @RequestHeader(value = "authorization") String authorization) {
        logger.debug("Rest request to updateCustom {}", editCustomHouseParams);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCustomerHouse basicCustomerHouse  = new BasicCustomerHouse();
            BeanUtils.copyProperties(editCustomHouseParams, basicCustomerHouse);
            basicCustomerHouse.setId(editCustomHouseParams.getId());
            basicCustomerHouse.setCustomerId(editCustomHouseParams.getCustomerId());
            basicCustomerHouse.setHouseAddress(editCustomHouseParams.getHouseAddress());
            basicCustomHouseService.updateSelective(basicCustomerHouse);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, "修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    /**
     * @auther:
     * @Description:客户房产信息列表
     */
    @GetMapping("/findCustomerHouseList/{id}")
    @ApiOperation(value = "客户房产信息列表")
    public ResponseResult findCustomerHouseList(@PathVariable("id") @ApiParam(value = "客户联系人ID", required = true) Long customId,@RequestHeader(value = "authorization") String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<BasicCustomerHouse> basicCustomeHouse = basicCustomHouseService.findCustomHouseList(customId);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, basicCustomeHouse);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }


    /**
     * 根据id查找客户房产
     */
    @PostMapping("/getCustomHouse/{id}")
    @ApiOperation(value = "根据客户ID查找客户房产", notes = "根据客户ID查找客户房产")
    public ResponseResult customHouse(@PathVariable("id") @ApiParam(value = "客户联系人ID", required = true) Long id, @RequestHeader(value = "authorization") String authorization) {

        try
        {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user))
            {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCustomerHouse basicCustomeHouse =  basicCustomHouseService.findById(id);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, basicCustomeHouse);
        } catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }

    }

}
