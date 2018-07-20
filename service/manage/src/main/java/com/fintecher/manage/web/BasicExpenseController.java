package com.fintecher.manage.web;

import com.fintecher.common.vo.manage.ResponseResult;
import com.fintecher.entity.BasicExpense;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.service.BasicExpenseService;
import com.fintecher.manage.vo.AddBasicExpenseParams;
import com.fintecher.manage.vo.EditBasicExpenseParams;
import com.fintecher.manage.vo.PageParam;
import com.fintecher.util.Constants;
import com.fintecher.util.Status;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/basicExpenseController")
@Api(description = "费用项管理")
public class BasicExpenseController extends BaseController {
    @Resource
    BasicExpenseService basicExpenseService;
    @PostMapping("/addBasicExpense")
    @ApiOperation(value = "添加费用项", notes = "添加费用项")
    public ResponseResult addBasicExpense(@Validated @RequestBody AddBasicExpenseParams addBasicExpenseParams,
                                          @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicExpense basicExpense = new BasicExpense();
            basicExpense.setExpenseName(addBasicExpenseParams.getExpenseName());
            basicExpense.setExpenseCode(addBasicExpenseParams.getExpenseCode());
            if(basicExpenseService.addCheck(basicExpense)>0){
                return new ResponseResult(ResponseResult.Status.FAILURE, "已有该名称或该编码");
            }else {
                basicExpense.setRemark(addBasicExpenseParams.getRemark());
                basicExpense.setOrgId(user.getOrgId());
                basicExpense.setIsSystem(Status.Disable.getValue());
                basicExpense.setOperator(user.getId());
                basicExpense.setOperatorTime(new Date());
                basicExpenseService.insert(basicExpense);
                return new ResponseResult(ResponseResult.Status.SUCCESS, "新增成功");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }
    @DeleteMapping("/deleteBasicExpense/{id}")
    @ApiOperation(value = "根据主键删除费用项", notes = "根据主键删除费用项")
    public ResponseResult deleteBasicExpense(
            @PathVariable("id") @ApiParam("主键ID") Long id,
            @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicExpense basicExpense = new BasicExpense();
            basicExpense.setId(id);
            Integer i = basicExpenseService.check(basicExpense);
            if (i > 0) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, "该费用项已被引用，不能删除");
            } else {
                if(basicExpenseService.findById(id).getIsSystem().equals(Status.Enable.getValue())){
                    return new ResponseResult<>(ResponseResult.Status.FAILURE, "系统选项，不能删除");
                }else {
                basicExpenseService.delete(basicExpense);
                return new ResponseResult(ResponseResult.Status.SUCCESS, "删除成功");
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }
    @PostMapping("/editBasicExpense")
    @ApiOperation(value = "修改费用项", notes = "修改费用项")
    public ResponseResult editBasicExpense(@Validated @RequestBody EditBasicExpenseParams editBasicExpenseParams,
                                           @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicExpense basicExpense = new BasicExpense();
            basicExpense.setId(editBasicExpenseParams.getId());
            if (basicExpenseService.findOne(basicExpense) == null) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, "没有该费用项");
            } else {
                if(basicExpenseService.findById(editBasicExpenseParams.getId()).getIsSystem().equals(Status.Enable.getValue())){
                    return new ResponseResult<>(ResponseResult.Status.FAILURE,"系统选项，不能修改");
                }else {
                    basicExpense.setRemark(editBasicExpenseParams.getRemark());
                    basicExpense.setOperator(user.getId());
                    basicExpense.setExpenseName(editBasicExpenseParams.getExpenseName());
                    basicExpense.setOperatorTime(new Date());
                    basicExpenseService.updatePrimaryKey(basicExpense);
                    return new ResponseResult(ResponseResult.Status.SUCCESS, "修改成功");
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }


    @GetMapping("/copyTemplate")
    @ApiOperation(value = "复制模板", notes = "复制模板")
    public ResponseResult copyTemplate(@RequestHeader(value = Constants.AUTHORIZATION) String authorization) {

        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicExpense basicExpense = new BasicExpense();
            basicExpense.setOrgId(user.getOrgId());
            basicExpense.setOperator(user.getId());
            basicExpense.setOperatorTime(new Date());
            basicExpense.setIsSystem(Status.Enable.getValue());
            basicExpenseService.copyTemplate(basicExpense);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "复制成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }


    @GetMapping("/findBasicExpensePageByOrg")
    @ApiOperation(value = "分页查询获取当前用户所属机构下的费用项", notes = "分页查询获取当前用户所属机构下的费用项")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                    value = "页数 (1..N)"),
            @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                    value = "每页大小"),
            @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                    value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
    })
    public ResponseResult<List<BasicExpense>> findBasicExpensePageByOrg(@ApiIgnore PageParam pageParam, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        logger.debug("Rest request findBasicExpenseByOrg");
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicExpense basicExpense = new BasicExpense();
            basicExpense.setOrgId(user.getOrgId());
            PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
            List<BasicExpense> listByWhere = basicExpenseService.findListByWhere(basicExpense);
            PageInfo<BasicExpense> pageInfo = new PageInfo<>(listByWhere);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "", pageInfo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }
    @GetMapping("/findBasicExpenseByOrg")
    @ApiOperation(value = "获取当前用户所属机构下的费用项(下拉选用)", notes = "获取当前用户所属机构下的费用项(下拉选用)")
    public ResponseResult<List<BasicExpense>> findBasicExpenseByOrg(@RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        logger.debug("Rest request findBasicExpenseByOrg");
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicExpense basicExpense = new BasicExpense();
            basicExpense.setOrgId(user.getOrgId());
            List<BasicExpense> listByWhere = basicExpenseService.findListByWhere(basicExpense);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, listByWhere);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }


}
