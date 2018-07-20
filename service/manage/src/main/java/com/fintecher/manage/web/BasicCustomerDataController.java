package com.fintecher.manage.web;

import com.fintecher.common.vo.manage.ResponseResult;
import com.fintecher.entity.BasicCustomerData;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.service.BasicCustomerDataService;
import com.fintecher.manage.vo.BasicCustomerDataListModel;
import com.fintecher.util.Constants;
import com.fintecher.util.ZWDateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Objects;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/21 13:55
 * @Description:
 */
@RestController
@RequestMapping("/basicCustomerDataController")
@Api(description = "客户资料上传")
public class BasicCustomerDataController extends BaseController {

    @Autowired
    private BasicCustomerDataService basicCustomerDataService;
    /**
     * @auther: dwx
     * @Description:新增客户资料上传
     */
    @PostMapping("/addCustomerData")
    @ApiOperation(value = "新增客户资料上传",notes = "新增客户资料上传")
    public ResponseResult addCustomerData(@RequestHeader(value = Constants.AUTHORIZATION) String authorization, @RequestBody BasicCustomerDataListModel basicCustomerDataModel,
                                          @ApiIgnore SysUser sysUser) {
        try {
            SysUser userInfo = getUserByAuth(authorization);
            if (Objects.isNull(userInfo)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            basicCustomerDataService.addCustomerData(basicCustomerDataModel, sysUser);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "新增成功");
        }catch (RuntimeException e){
            return new ResponseResult(ResponseResult.Status.FAILURE,e.getMessage());
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new ResponseResult(ResponseResult.Status.FAILURE,"系统异常");
        }
    }
    /**
     * @auther: dwx
     * @Description:修改客户资料
     */
    @PutMapping("/updateCustomerData")
    @ApiOperation(value = "修改客户资料",notes = "修改客户资料")
    public ResponseResult updateCustomerData(@RequestHeader(value = Constants.AUTHORIZATION) String authorization, @RequestBody BasicCustomerDataListModel basicCustomerDataModel) {
        try {
            SysUser userInfo = getUserByAuth(authorization);
            if (Objects.isNull(userInfo)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCustomerData basicCustomerData = new BasicCustomerData();
            BeanUtils.copyProperties(basicCustomerDataModel, basicCustomerData);
            basicCustomerData.setId(basicCustomerDataModel.getId());
            basicCustomerData.setOperator(userInfo.getId());
            basicCustomerData.setOperatorTime(ZWDateUtil.getNowDateTime());
            basicCustomerDataService.updateSelective(basicCustomerData);
            return new ResponseResult(ResponseResult.Status.SUCCESS,"修改成功");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new ResponseResult(ResponseResult.Status.FAILURE,e.getMessage());
        }
    }
    /**
     * @auther: dwx
     * @Description:删除客户资料
     */
    @DeleteMapping("/deleteCustomerData")
    @ApiOperation(value = "删除客户资料",notes = "删除客户资料")
    public ResponseResult deleteCustomerData(@RequestHeader(value = Constants.AUTHORIZATION) String authorization, @RequestParam Long id) {
        try{
            SysUser userInfo = getUserByAuth(authorization);
            if (Objects.isNull(userInfo)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCustomerData basicCustomerData = basicCustomerDataService.findById(id);
            if (Objects.isNull(basicCustomerData)){
                return new ResponseResult(ResponseResult.Status.FAILURE,"无此附件");
            }
            basicCustomerDataService.deleteById(id);
            return new ResponseResult(ResponseResult.Status.SUCCESS,"删除成功");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new ResponseResult(ResponseResult.Status.FAILURE,"删除失败");
        }
    }
    /**
     * @auther: dwx
     * @Description:查询资料
     */
    @GetMapping("/getCustomerData/{id}")
    @ApiOperation(value = "查询资料",notes = "查询资料")
    public ResponseResult getCustomerData(@PathVariable("id") @ApiParam(value = "客户ID", required = true) Long customerId, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try{
            SysUser userInfo = getUserByAuth(authorization);
            if (Objects.isNull(userInfo)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCustomerData basicCustomerData = new BasicCustomerData();
            basicCustomerData.setCustomerId(customerId);
            List<BasicCustomerData> list = basicCustomerDataService.findListByWhere(basicCustomerData);
            return new ResponseResult(ResponseResult.Status.SUCCESS,list);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new ResponseResult(ResponseResult.Status.FAILURE,"查询失败");
        }
    }

}
