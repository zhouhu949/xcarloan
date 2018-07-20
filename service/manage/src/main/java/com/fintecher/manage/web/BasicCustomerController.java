package com.fintecher.manage.web;

import com.fintecher.common.vo.manage.ResponseResult;
import com.fintecher.entity.*;
import com.fintecher.manage.service.BasicCustomerBankService;
import com.fintecher.manage.service.BasicCustomerFollowService;
import com.fintecher.manage.service.BasicCustomerIntentionService;
import com.fintecher.manage.service.BasicCustomerService;
import com.fintecher.manage.vo.*;
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
 * @author ZhangYaJun
 * @Title: BasicCustomerController
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/19 0019下午 14:54
 */

@RestController
@RequestMapping("/BasicCustomerController")
@Api(description = "客户管理")
public class BasicCustomerController extends BaseController {

    @Autowired
    private BasicCustomerService basicCustomerService;

    @Autowired
    private BasicCustomerIntentionService basicCustomerIntentionService;

    @Autowired
    private BasicCustomerFollowService basicCustomerFollowService;

    @Autowired
    private BasicCustomerBankService basicCustomerBankService;


    @GetMapping("/findAllCustomerList")
    @ApiOperation(value = "客户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                    value = "页数 (1..N)", required = true),
            @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                    value = "每页大小", required = true),
            @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                    value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
    })
    public ResponseResult<BasicCustomer> findAllCustomerList(@RequestParam(required = false) String customerName, @ApiIgnore PageParam pageParam, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
            List<BasicCustomer> allCustomerCarList = basicCustomerService.findAllCustomerList(customerName, user.getOrgId());
            return new ResponseResult(ResponseResult.Status.SUCCESS, new PageInfo<>(allCustomerCarList));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }

    }


    @PostMapping("/addBasicCustomer")
    @ApiOperation(value = "添加意向客户", notes = "添加意向客户")
    public ResponseResult addBasicCustomer(@Validated @RequestBody BasicCustomerParams basicCustomerParams,
                                           @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            basicCustomerService.addBasicCustomer(basicCustomerParams, user);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, "添加成功");
         }catch (RuntimeException e){
            logger.error(e.getMessage(),e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE,e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }


    @PostMapping("/addBasicIntentionalCustomer")
    @ApiOperation(value = "新增意向记录")
    public ResponseResult addBasicIntentionalCustomer(@Validated @RequestBody BasicCustomerIntentionParams basicCustomerIntentionParams, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {

        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }

            basicCustomerIntentionService.saveIntentionCustomer(basicCustomerIntentionParams, user);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "增加成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }


    @PostMapping("/addBasicCustomerFollow")
    @ApiOperation(value = "新增跟进记录")
    public ResponseResult addBasicCustomerFollow(@Validated @RequestBody BasicCustomerFollowParams basicCustomerFollowParams,
                                                 @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            basicCustomerFollowService.saveBasicCustomerFollow(basicCustomerFollowParams, user);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, "新增成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }


    @PostMapping("/addBasicCustomerIntenTion")
    @ApiOperation(value = "新增客户与意向记录")
    public ResponseResult addBasicCustomerIntenTion(@Validated @RequestBody BasicCustomerAndIntentionParams basicCustomerAndIntentionParams, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {

        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            return basicCustomerIntentionService.addBasicCustomerIntenTion(basicCustomerAndIntentionParams, user);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }


    @PutMapping("/editBasicCustomer")
    @ApiOperation(value = "修改客户", notes = "修改客户")
    public ResponseResult editBasicCustomer(@Validated @RequestBody EditBasicCustomerParams editBasicCustomerParams,
                                            @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {

        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            basicCustomerService.editBasicCustomer(editBasicCustomerParams, user);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, "修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }


    @PutMapping("/editBasicCustomerIntention")
    @ApiOperation(value = "修改意向记录", notes = "修改意向记录")
    public ResponseResult editBasicCustomerIntention(@Validated @RequestBody EditBasicCustomerIntentionParams editBasicCustomerIntentionParams, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {

        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCustomerIntention basicCustomerIntention = new BasicCustomerIntention();
            int status = editBasicCustomerIntentionParams.getIntentionStatus().intValue();
            if (status == BasicCustomerIntention.IntentionStatus.BASIC_INTENTION_STATUS_WGJ.getValue() || status == BasicCustomerIntention.IntentionStatus.BASIC_INTENTION_STATUS_YGJ.getValue() || status == BasicCustomerIntention.IntentionStatus.BASIC_INTENTION_STATUS_YGQ.getValue() || status == BasicCustomerIntention.IntentionStatus.BASIC_INTENTION_STATUS_YWC.getValue() || status == BasicCustomerIntention.IntentionStatus.BASIC_INTENTION_STATUS_YXD.getValue()) {
                basicCustomerIntention.setIntentionStatus(status);

            } else {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, "该状态不存在");
            }
            basicCustomerIntention.setOperatorTime(ZWDateUtil.getNowDateTime());
            basicCustomerIntention.setOperator(user.getId());
            basicCustomerIntention.setIsLastIntention(BasicCustomerIntention.IsLastIntention.FALSE.getValue());
            BeanUtils.copyProperties(editBasicCustomerIntentionParams, basicCustomerIntention);
            if (Objects.nonNull(editBasicCustomerIntentionParams.getRemark())) {
                basicCustomerIntention.setRemark(editBasicCustomerIntentionParams.getRemark());
            }
            basicCustomerIntentionService.updateSelective(basicCustomerIntention);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }


    @GetMapping("/findAllCustomerIntentionList")
    @ApiOperation(value = "意向记录列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                    value = "页数 (1..N)", required = true),
            @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                    value = "每页大小", required = true),
            @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                    value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
    })
    public ResponseResult findAllCustomerIntentionList(@ApiIgnore PageParam pageParam, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {

        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
            Example example1 = new Example(BasicCustomerIntention.class);

            List<BasicCustomerIntention> basicCustomerIntentions = basicCustomerIntentionService.selectByExample(example1);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, new PageInfo<>(basicCustomerIntentions));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }


    @PostMapping("/findAllCustomerFollowList")
    @ApiOperation(value = "跟进记录列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                    value = "页数 (1..N)", required = true),
            @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                    value = "每页大小", required = true),
            @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                    value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
    })
    public ResponseResult findAllCustomerFollowList(@ApiIgnore PageParam pageParam, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
            Example example1 = new Example(BasicCustomerFollow.class);

            List<BasicCustomerFollow> basicCustomerFollows = basicCustomerFollowService.selectByExample(example1);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, new PageInfo<>(basicCustomerFollows));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }


    @GetMapping("/findCustomerById/{id}")
    @ApiOperation(value = "查找客户")
    public ResponseResult findCustomerById(@PathVariable("id") @ApiParam(value = "客户ID", required = true) Long id, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCustomer byId = basicCustomerService.findById(id);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, byId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }


    @GetMapping("/findCustomerIntentionById/{id}")
    @ApiOperation(value = "查找意向记录")
    public ResponseResult findCustomerIntentionById(@PathVariable("id") @ApiParam(value = "意向ID", required = true) Long id, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCustomerIntention customerIntention = basicCustomerIntentionService.findById(id);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, customerIntention);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    /**
     * @auther: dwx
     * @Description:新增银行卡
     */
    @PostMapping("/addBasicCustomerBank")
    @ApiOperation(value = "新增银行卡", notes = "新增银行卡")
    public ResponseResult addBasicCustomerBank(@RequestBody BasicCustomerBankModel basicCustomerBankModel,
                                               @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCustomerBank basicCustomerBank = new BasicCustomerBank();
            BeanUtils.copyProperties(basicCustomerBankModel, basicCustomerBank);
            basicCustomerBank.setOperator(user.getId());
            basicCustomerBank.setOperatorTime(ZWDateUtil.getNowDateTime());
            basicCustomerBank.setAccountStatus(BasicCustomerBank.OpenAccountStatus.FAILURE.getValue());
            basicCustomerBankService.save(basicCustomerBank);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "新增成功");
        } catch (RuntimeException r) {
            logger.error(r.getMessage(), r);
            return new ResponseResult(ResponseResult.Status.FAILURE, r.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, "系统异常");
        }
    }

    /**
     * @auther: dwx
     * @Description:银行卡信息查询
     */
    @GetMapping("/getCustomerBankInfo")
    @ApiOperation(value = "银行卡信息查询", notes = "银行卡信息查询")
    public ResponseResult getCustomerBankInfo(@RequestParam(required = true) Long customerId,
                                              @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<BasicCustomerBank> list = basicCustomerBankService.queryCustomerBankInfo(customerId);
            return new ResponseResult(ResponseResult.Status.SUCCESS, list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, "系统异常");
        }
    }

    /**
     * @auther: dwx
     * @Description:删除银行卡信息
     */
    @DeleteMapping("/deleteCustomerBankInfo")
    @ApiOperation(value = "删除银行卡信息", notes = "删除银行卡信息")
    public ResponseResult deleteCustomerBankInfo(@RequestParam Long bankId, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCustomerBank basicCustomerBank = basicCustomerBankService.findById(bankId);
            if (Objects.nonNull(basicCustomerBank.getAccountStatus()) && basicCustomerBank.getAccountStatus().equals(BasicCustomerBank.OpenAccountStatus.SUCCESS.getValue())) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "已开户银行卡不能删除");
            }
            basicCustomerBankService.deleteById(bankId);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, "删除失败");
        }
    }

    /**
     * @auther: dwx
     * @Description:编辑客户开户银行卡
     */
    @PutMapping("/updateCustomerBank")
    @ApiOperation(value = "编辑客户开户银行卡", notes = "编辑客户开户银行卡")
    public ResponseResult updateCustomerBank(@RequestHeader(value = Constants.AUTHORIZATION) String authorization, @RequestBody BasicCustomerBankModel basicCustomerBankModel) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCustomerBank basicCustomerBank = new BasicCustomerBank();
            BeanUtils.copyProperties(basicCustomerBankModel, basicCustomerBank);
            basicCustomerBank.setId(basicCustomerBankModel.getId());
            basicCustomerBank.setCustomerId(basicCustomerBankModel.getCustomerId());
            basicCustomerBank.setAccountStatus(basicCustomerBankModel.getAccountStatus());
            BasicCustomerBank basicCustomerBank1 = basicCustomerBankService.findById(basicCustomerBankModel.getId());
            if (Objects.isNull(basicCustomerBank1)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "无此银行卡，请重新输入");
            }
            basicCustomerBank.setOperator(user.getId());
            basicCustomerBank.setOperatorTime(ZWDateUtil.getNowDateTime());
            basicCustomerBankService.updateSelective(basicCustomerBank);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, "系统异常");
        }
    }


    @GetMapping("/findCustomerFollowById/{id}")
    @ApiOperation(value = "查找跟踪记录")
    public ResponseResult findCustomerFollowById(@PathVariable("id") @ApiParam(value = "跟踪记录ID", required = true) Long id, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCustomerFollow customerFollow = basicCustomerFollowService.findById(id);
            if (Objects.isNull(customerFollow)) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, "没有数据");
            }
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, customerFollow);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }


    @PutMapping("/editBasicCustomerFollow")
    @ApiOperation(value = "修改跟踪记录", notes = "修改跟踪记录")
    public ResponseResult editBasicCustomerFollow(@Validated @RequestBody EditBasicCustomerFollowParams editBasicCustomerFollowParams, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {

        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCustomerFollow basicCustomerFollow = new BasicCustomerFollow();
            basicCustomerFollow.setId(editBasicCustomerFollowParams.getId());
            int intValue = editBasicCustomerFollowParams.getFollowType().intValue();
            if (intValue == BasicCustomerFollow.FollowType.BASIC_FOLLOWTYPE_PHONR.getValue() ||
                    intValue == BasicCustomerFollow.FollowType.BASIC_INTENTION_STATUS_EMAIL.getValue()) {
                basicCustomerFollow.setFollowType(intValue);
            } else {
                return new ResponseResult(ResponseResult.Status.FAILURE, "跟进方式错误");
            }
            int intValue1 = editBasicCustomerFollowParams.getFollowResult().intValue();
            if (intValue1 == BasicCustomerFollow.FollowResult.BASIC_FOLLOWRESULT_JXGJ.getValue() ||
                    intValue1 == BasicCustomerFollow.FollowResult.BASIC_FOLLOWRESULT_BZGJ.getValue() ||
                    intValue1 == BasicCustomerFollow.FollowResult.BASIC_FOLLOWRESULT_GJZG.getValue()) {
                basicCustomerFollow.setFollowResult(intValue1);
            } else {
                return new ResponseResult(ResponseResult.Status.FAILURE, "跟进结果错误");
            }
            basicCustomerFollow.setOperatorTime(ZWDateUtil.getNowDateTime());
            basicCustomerFollow.setOperator(user.getId());
            if (Objects.nonNull(editBasicCustomerFollowParams.getRemark())) {
                basicCustomerFollow.setRemark(editBasicCustomerFollowParams.getRemark());
            }

            basicCustomerFollowService.updateSelective(basicCustomerFollow);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }


    @GetMapping("/findCustomerFollowList/{id}")
    @ApiOperation(value = "根据意向id查找跟踪记录列表")
    public ResponseResult findCustomerFollowByIntentionId(@PathVariable("id") @ApiParam(value = "意向ID", required = true) Long id, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            Example example = new Example(BasicCustomerFollow.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("intentionId", id);
            List<BasicCustomerFollow> basicCustomerFollows = basicCustomerFollowService.selectByExample(example);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, basicCustomerFollows);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }


    @GetMapping("/findCustomerIntentionList/{id}")
    @ApiOperation(value = "根据客户id查找意向记录列表")
    public ResponseResult findCustomerIntentionList(@PathVariable("id") @ApiParam(value = "客户ID", required = true) Long id, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            Example example = new Example(BasicCustomerIntention.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("customerId", id);
            List<BasicCustomerIntention> basicCustomerIntentions = basicCustomerIntentionService.selectByExample(example);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, basicCustomerIntentions);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    /**
     * @auther: dwx
     * @Description:获取客户签约列表
     */
    @GetMapping("/getCustomerSignList")
    @ApiOperation(value = "获取客户签约列表", notes = "获取客户签约列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                    value = "页数 (1..N)", required = true),
            @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                    value = "每页大小", required = true),
            @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                    value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
    })
    public ResponseResult getCustomerSignList(@ApiIgnore PageParam pageParam, @RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                              @RequestParam(value = "customerName", required = false) @ApiParam(value = "客户姓名") String customerName) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
            List<BasicCustomer> list = basicCustomerService.findCustomerSignList(customerName);
            return new ResponseResult(ResponseResult.Status.SUCCESS, new PageInfo<>(list));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, e.getMessage());
        }
    }
}


