package com.fintecher.manage.web;

import com.fintecher.common.vo.manage.ResponseResult;
import com.fintecher.entity.BasicCustomerJob;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.service.BasicCustomerJobService;
import com.fintecher.manage.vo.EditCustomerJobParams;
import com.fintecher.util.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/basicPersonalJobController")
@Api(description = "客户职业信息")
public class BasicCustomerJobController extends BaseController {
    @Autowired
    private BasicCustomerJobService basicPersonalJobService;

    @PostMapping("/addPersonalJob")
    @ApiOperation(value = "新增客户职业信息", notes = "新增客户职业信息")
    public ResponseResult addPersonalJob(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                          @RequestBody EditCustomerJobParams editCustomerJobParams) {
        logger.debug("Rest request to add custom {}", editCustomerJobParams);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCustomerJob basicCustomerJob = new BasicCustomerJob();
            BeanUtils.copyProperties(editCustomerJobParams,basicCustomerJob);
            basicPersonalJobService.save(basicCustomerJob);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, "新增成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @PutMapping(value = "/updatePersonalJob")
    @ApiOperation(value = "根据客户ID修改客户职业信息", notes = "根据客户ID修改客户职业信息")
    public ResponseResult updatePersonalJob(@RequestBody EditCustomerJobParams editPersonalJobParams,
                                            @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        logger.debug("Rest request to updateCustom {}", editPersonalJobParams);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCustomerJob basicPersonalJob = new BasicCustomerJob();
            BeanUtils.copyProperties(editPersonalJobParams, basicPersonalJob);
            basicPersonalJobService.update(basicPersonalJob);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, "修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    /**
     * @auther:
     * @Description:删除客户联系人
     */
    @DeleteMapping("/deletePersonalJob")
    @ApiOperation(value = "删除客户职业信息",notes = "删除客户职业信息")
    public ResponseResult deletePersonalJob(@RequestParam Long id,@RequestHeader(value = Constants.AUTHORIZATION) String authorization){
        try{
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            basicPersonalJobService.deleteById(id);
            return new ResponseResult(ResponseResult.Status.SUCCESS,"删除成功");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new ResponseResult(ResponseResult.Status.FAILURE,"删除失败");
        }
    }

    @GetMapping("/findCustomPersonalJob/{id}")
    @ApiOperation(value = "客户职业信息列表")
    public ResponseResult<BasicCustomerJob> findCustomPersonalJob(@PathVariable("id") @ApiParam(value = "客户联系人ID", required = true) Long customId, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<BasicCustomerJob> personalJobList = basicPersonalJobService.findPersonalJobList(customId);
            return new ResponseResult(ResponseResult.Status.SUCCESS, personalJobList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    /**
     * 根据id查找客户联系人
     */
    @PostMapping("/getPersonalJob/{id}")
    @ApiOperation(value = "根据客户Id查找客户职业信息", notes = "根据客户Id查找客户职业信息")
    public ResponseResult getPersonalJob(@PathVariable("id") @ApiParam(value = "客户联系人ID", required = true) Long id, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {

        try
        {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user))
            {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCustomerJob basicPersonalJob =  basicPersonalJobService.findById(id);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, basicPersonalJob);
        } catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }

    }
}
