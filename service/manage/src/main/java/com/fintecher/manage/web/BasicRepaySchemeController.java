package com.fintecher.manage.web;

import com.fintecher.common.vo.manage.ResponseResult;
import com.fintecher.entity.BasicProduct;
import com.fintecher.entity.BasicRepayScheme;
import com.fintecher.entity.BasicRepaySchemeExpense;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.service.BasicExpenseService;
import com.fintecher.manage.service.BasicProductService;
import com.fintecher.manage.service.BasicRepaySchemeExpenseService;
import com.fintecher.manage.service.BasicRepaySchemeService;
import com.fintecher.manage.vo.*;
import com.fintecher.util.Constants;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/basicRepaySchemeController")
@Api(description = "还款方案管理")
public class BasicRepaySchemeController extends BaseController {

    @Resource
    private BasicRepaySchemeService basicRepaySchemeService;
    @Resource
    private BasicProductService basicProductService;
    @Resource
    private BasicRepaySchemeExpenseService basicRepaySchemeExpenseService;
    @Autowired
    private BasicExpenseService basicExpenseService;

    @PostMapping("/repayScheme")
    @ApiOperation(value = "新增/修改还款方案", notes = "新增/修改还款方案")
    public ResponseResult addRepayScheme(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                         @Validated @RequestBody RepaySchemeParams addRepayScheme) {
        logger.debug("Rest request addRepayScheme {}", addRepayScheme);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            Example example = new Example(BasicRepayScheme.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("schemeName", addRepayScheme.getSchemeName());
            if (Objects.nonNull(addRepayScheme.getId())) {
                criteria.andNotEqualTo("id", addRepayScheme.getId());
            }
            if (basicRepaySchemeService.selectCountByExample(example) > 0) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "还款方案名称已存在");
            }
            if (addRepayScheme.getMoneyMax().compareTo(addRepayScheme.getMoneyMin()) < 0) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "融资最小金额不能大于最大金额");
            }
            if (Objects.equals(addRepayScheme.getSchemeType(), BasicRepayScheme.SchemeType.MORTGAGE.getValue())
                    && Objects.isNull(addRepayScheme.getMortgageType())) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "抵押贷款必须选择抵押方式");
            }
            basicRepaySchemeService.addBasicRepayScheme(user, addRepayScheme);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "新增成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @PutMapping("/releaseRepayScheme")
    @ApiOperation(value = "发布/取消发布还款方案", notes = "发布/取消发布还款方案")
    public ResponseResult releaseRepayScheme(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                             @Validated @RequestBody ReleaseRepaySchemeParams releaseParams) {
        logger.debug("Rest request releaseRepayScheme {}", releaseParams);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicRepayScheme repayScheme = basicRepaySchemeService.findById(releaseParams.getId());
            Integer schemeStatus = repayScheme.getSchemeStatus();
            if (Objects.equals(releaseParams.getSchemeStatus(), schemeStatus)) {
                return new ResponseResult(ResponseResult.Status.FAILURE,
                        Objects.equals(schemeStatus, BasicRepayScheme.SchemeStatus.ENABLE.getValue()) ? "该方案还未发布" : "该方案已发布");
            }
            // 取消发布
            if (Objects.equals(releaseParams.getSchemeStatus(), BasicRepayScheme.SchemeStatus.ENABLE.getValue())) {
                Example example = new Example(BasicProduct.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("schemeId", releaseParams.getId());
                if (basicProductService.selectCountByExample(example) > 0) {
                    return new ResponseResult(ResponseResult.Status.FAILURE, "该方案有产品在使用不能取消发布");
                }
            } else {
                // 发布校验
                List<String> expenseCode = basicRepaySchemeService.findRepaySchemeExpenseCode(releaseParams.getId());
                // 融资租赁
                if (Objects.equals(repayScheme.getSchemeType(), BasicRepayScheme.SchemeType.FINANCING.getValue())) {
                    if (!expenseCode.containsAll(Constants.FINANCING_EXPENSE_CODE)) {
                        return new ResponseResult(ResponseResult.Status.FAILURE, "融资租赁的还款方案必须包含押金、GPS费用、管理费、本金、利息、提前结清手续费、罚息、罚金、首付款等费用项");
                    }
                } else {
                    // 抵押贷款
                    if (!expenseCode.containsAll(Constants.MORTGAGE_EXPENSE_CODE)) {
                        return new ResponseResult(ResponseResult.Status.FAILURE, "抵押贷款的还款方案必须包含GPS费用、管理费、本金、利息、提前结清手续费、罚息、罚金等费用项");
                    }
                }
            }
            BasicRepayScheme basicRepayScheme = new BasicRepayScheme();
            basicRepayScheme.setId(releaseParams.getId());
            basicRepayScheme.setSchemeStatus(releaseParams.getSchemeStatus());
            basicRepaySchemeService.updateSelective(basicRepayScheme);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "操作成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @DeleteMapping("/deleteRepayScheme/{id}")
    @ApiOperation(value = "删除还款方案", notes = "删除还款方案")
    public ResponseResult deleteRepayScheme(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                            @PathVariable("id") @ApiParam(value = "还款方案ID") Long id) {
        logger.debug("Rest request deleteRepayScheme by id {}", id);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicRepayScheme repayScheme = basicRepaySchemeService.findById(id);
            if (Objects.equals(repayScheme.getSchemeStatus(), BasicRepayScheme.SchemeStatus.DISABLE.getValue())) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "还款方案已发布不允许删除");
            }
            basicRepaySchemeService.deleteBasicRepaySchemeById(id);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "删除成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/getAllBasicSchemeByOrgId")
    @ApiOperation(value = "获取当前用户下组织机构下所有的还款方案", notes = "获取当前用户下组织机构下所有的还款方案")
    public ResponseResult<List<BasicRepayScheme>> getAllBasicSchemeByOrgId(@RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        logger.debug("Rest request getAllBasicSchemeByOrgId");
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicRepayScheme basicRepayScheme = new BasicRepayScheme();
            basicRepayScheme.setOrgId(user.getOrgId());
            List<BasicRepayScheme> schemeByOrgId = basicRepaySchemeService.findListByWhere(basicRepayScheme);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, schemeByOrgId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/queryReleasedScheme")
    @ApiOperation(value = "获取当前用户组织机构下所有已发布的抵押贷款还款方案", notes = "获取当前用户组织机构下所有已发布的抵押贷款还款方案")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                    value = "页数 (1..N)", required = true),
            @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                    value = "每页大小", required = true),
            @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                    value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
    })
    public ResponseResult<PageInfo<BasicRepayScheme>> queryReleasedScheme(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                                                          @ApiIgnore PageParam pageParam,
                                                                          @RequestParam @ApiParam(value = "类型：10049-融资租赁，10050-抵押贷款", required = true) Integer schemeType,
                                                                          @RequestParam(required = false) @ApiParam(value = "还款方案名称") String schemeName) {
        logger.debug("Rest request queryReleasedScheme");
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            Example example = new Example(BasicRepayScheme.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("orgId", user.getOrgId());
            criteria.andEqualTo("schemeType", schemeType);
            criteria.andEqualTo("schemeStatus", BasicRepayScheme.SchemeStatus.DISABLE.getValue());
            if (StringUtils.isNotBlank(schemeName)) {
                criteria.andLike("schemeName", String.format("%s%s%s", "%", schemeName, "%"));
            }
            PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
            List<BasicRepayScheme> schemeByOrgId = basicRepaySchemeService.selectByExample(example);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, new PageInfo<>(schemeByOrgId));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/findSchemeById/{id}")
    @ApiOperation(value = "根据还款方案ID获取还款方案信息", notes = "根据还款方案ID获取还款方案信息")
    public ResponseResult<SchemeInfoModel> findSchemeById(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                                          @PathVariable("id") @ApiParam("还款方案ID") Long id) {
        logger.debug("Rest request findScheme By Id：{}", id);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            SchemeInfoModel schemeInfo = basicRepaySchemeService.findSchemeInfo(id);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, schemeInfo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/findSchemeExpenseBySchemeId/{id}")
    @ApiOperation(value = "根据还款方案获取还款方案比例详情", notes = "根据还款方案获取还款方案比例详情")
    public ResponseResult<List<RepayExpenseInfoModel>> findSchemeExpenseBySchemeId(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                                                                   @PathVariable("id") @ApiParam("还款方案ID") Long id) {
        logger.debug("Rest request findSchemeExpense By SchemeId: {}", id);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<RepayExpenseInfoModel> repaySchemeExpenseInfo = basicRepaySchemeExpenseService.findRepaySchemeExpenseInfo(id);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, repaySchemeExpenseInfo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }


    @DeleteMapping("/deleteRepaySchemeExpense/{id}")
    @ApiOperation(value = "删除还款方案比例详情", notes = "删除还款方案比例详情")
    public ResponseResult deleteRepaySchemeExpense(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                                   @PathVariable("id") @ApiParam("还款方案比例详情ID") Long id) {
        logger.debug("Rest request deleteRepaySchemeExpense by id {}", id);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            RepaySchemeInfoModel repaySchemeInfo = basicRepaySchemeExpenseService.findRepaySchemeInfo(id);
            String expenseCode = repaySchemeInfo.getExpenseCode();
            Integer schemeType = repaySchemeInfo.getSchemeType();
            if (Objects.equals(schemeType, BasicRepayScheme.SchemeType.FINANCING.getValue())
                    && Constants.FINANCING_EXPENSE_CODE.contains(expenseCode)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "该项是必选项不允许删除");
            }
            if (Objects.equals(schemeType, BasicRepayScheme.SchemeType.MORTGAGE.getValue())
                    && Constants.MORTGAGE_EXPENSE_CODE.contains(expenseCode)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "该项是必选项不允许删除");
            }
            basicRepaySchemeExpenseService.deleteById(id);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "删除成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @PostMapping("/addBasicSchemeExpense")
    @ApiOperation(value = "修改/添加还款方案比例详情", notes = "修改/添加还款方案比例详情")
    public ResponseResult addBasicSchemeExpense(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                                @Validated @RequestBody BasicSchemeExpenseParams basicExpenseParams) {
        logger.debug("Rest request addBasicSchemeExpense {}", basicExpenseParams);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            // 校验参数
            basicRepaySchemeExpenseService.validateParams(basicExpenseParams);
            BigDecimal repayProportion = basicExpenseParams.getRepayProportion();
            BasicRepaySchemeExpense expense = new BasicRepaySchemeExpense();
            BeanUtils.copyProperties(basicExpenseParams, expense);
            if (Objects.nonNull(repayProportion)) {
                expense.setRepayProportion(repayProportion.divide(new BigDecimal(100), 8, BigDecimal.ROUND_HALF_UP));
            }
            expense.setOperator(user.getId());
            expense.setOperatorTime(new Date());
            if (Objects.isNull(basicExpenseParams.getId())) {
                basicRepaySchemeExpenseService.save(expense);
            } else {
                basicRepaySchemeExpenseService.update(expense);
            }
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, "添加成功");
        } catch (IllegalArgumentException e) {
            return new ResponseResult<>(ResponseResult.Status.FAILURE, e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

}
