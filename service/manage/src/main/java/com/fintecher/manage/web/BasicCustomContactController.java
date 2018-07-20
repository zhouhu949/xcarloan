package com.fintecher.manage.web;

import com.fintecher.common.vo.manage.ResponseResult;
import com.fintecher.entity.BasicCustomContact;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.service.BasicCustomContactService;
import com.fintecher.manage.service.UserService;
import com.fintecher.manage.vo.EditCustomContact;
import com.fintecher.util.Constants;
import com.fintecher.util.ZWDateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/basicCustomContactController")
@Api(description = "客户联系人")
public class BasicCustomContactController extends BaseController {

    @Autowired
    private UserService userService;
    @Resource
    private BasicCustomContactService basicCustomContactService;

    @PostMapping("/addCustomContact")
    @ApiOperation(value = "新增客户联系人信息", notes = "新增客户联系人信息")
    public ResponseResult addCustomContact(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                            @RequestBody EditCustomContact editCustomContact) {
        logger.debug("Rest request to updateCustom {}", editCustomContact);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCustomContact basicCustomContact = new BasicCustomContact();
            BeanUtils.copyProperties(editCustomContact,basicCustomContact);
            basicCustomContact.setCustomerId(editCustomContact.getCustomerId());
            basicCustomContact.setOperator(user.getId().intValue());
            basicCustomContact.setOperatorTime(ZWDateUtil.getNowDateTime());
            basicCustomContactService.save(basicCustomContact);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, "新增成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }


    }


    /**
     * @auther:
     * @Description:删除客户联系人信息
     */
    @DeleteMapping("/deleteCustomContant")
    @ApiOperation(value = "删除客户联系人信息",notes = "删除客户联系人信息")
    public ResponseResult deleteCustomContant(@RequestParam Long contactId,@RequestHeader(value = Constants.AUTHORIZATION) String authorization){
        try{
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            Example example = new Example(BasicCustomContact.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andIsNotNull("customerId");
            basicCustomContactService.deleteById(contactId);
            return new ResponseResult(ResponseResult.Status.SUCCESS,"删除成功");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new ResponseResult(ResponseResult.Status.FAILURE,"删除失败");
        }
    }

    /**
     * @auther:
     * @Description:修改客户联系人信息
     */
    @PutMapping("/updateCustomContant")
    @ApiOperation(value = "修改客户联系人信息", notes = "修改客户联系人信息")
    public ResponseResult updateCustomContant(@RequestBody EditCustomContact editCustomContact,
                                              @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {

       logger.debug("Rest request to updateCustom {}", editCustomContact);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCustomContact basicCustomContact = new BasicCustomContact();
            basicCustomContact.setId(editCustomContact.getId());
            BeanUtils.copyProperties(editCustomContact, basicCustomContact);
            basicCustomContactService.update(basicCustomContact);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, "修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    /**
     * @auther:
     * @Description:客户联系人信息列表
     */
    @GetMapping("/findCustomerContactList/{customId}")
    @ApiOperation(value = "客户联系人信息列表")
    public ResponseResult findCustomerContactList(@PathVariable("customId") @ApiParam(value = "客户联系人ID", required = true) Long customId, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<BasicCustomContact> basicCustomContact = basicCustomContactService.findCustomContactList(customId);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, basicCustomContact);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }


    /**
     * 根据id查找客户联系人信息
     */
    @GetMapping("/getCustomContact/{id}")
    @ApiOperation(value = "根据联系人ID查找客户联系人信息", notes = "根据联系人ID查找客户联系人信息")
    public ResponseResult getCustomContact(@PathVariable("id") @ApiParam(value = "客户联系人ID", required = true) Long id, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {

        try
        {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user))
            {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCustomContact basicCustomContact =  basicCustomContactService.findById(id);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, basicCustomContact);
        } catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }

    }

}
