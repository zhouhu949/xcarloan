package com.fintecher.manage.web;


import com.fintecher.common.vo.manage.ResponseResult;
import com.fintecher.entity.BasicOffset;
import com.fintecher.entity.BasicOffsetItem;
import com.fintecher.entity.BasicRepayScheme;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.service.BasicOffsetItemService;
import com.fintecher.manage.service.BasicOffsetService;
import com.fintecher.manage.vo.AddBasicOffsetItemParams;
import com.fintecher.manage.vo.AddBasicOffsetParams;
import com.fintecher.manage.vo.EditBasicOffsetParams;
import com.fintecher.util.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/basicOffsetController")
@Api(description = "冲抵配置")
public class BasicOffsetController extends BaseController {
    @Resource
    private BasicOffsetService basicOffsetService;
    @Resource
    private BasicOffsetItemService basicOffsetItemService;

    @PostMapping("/addBasicOffset")
    @ApiOperation(value = "新增冲抵策略", notes = "新增冲抵策略")
    public ResponseResult addBasicOffsetParams(@Validated @RequestBody AddBasicOffsetParams addBasicOffsetParams,
                                               @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        logger.debug("REST request to addBasicOffset {}", addBasicOffsetParams);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicOffset basicOffset = new BasicOffset();
            basicOffset.setOffsetName(addBasicOffsetParams.getOffsetName());
            basicOffset.setOrgId(user.getOrgId());
            BasicOffset one = basicOffsetService.findOne(basicOffset);
            if (Objects.nonNull(one)) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, "冲抵策略名称已存在");
            }
            BasicOffset offset = new BasicOffset();
            BeanUtils.copyProperties(addBasicOffsetParams, offset);
            offset.setOrgId(user.getOrgId());
            offset.setOffsetStatus(BasicOffset.OffsetStatus.UNPUBLISHED.getValue());
            offset.setOperatorTime(new Date());
            offset.setOperator(user.getId());
            basicOffsetService.addOffsetAndOffsetItem(offset, user);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "新增成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }


    @DeleteMapping("/deleteBasicOffset/{id}")
    @ApiOperation(value = "根据主键删除冲抵策略", notes = "根据主键删除冲抵策略")
    public ResponseResult deleteBasicOffset(@PathVariable("id") @ApiParam("主键ID") Long id,
                                            @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicOffset offset = basicOffsetService.findById(id);
            if (Objects.equals(offset.getOffsetStatus(), BasicOffset.OffsetStatus.PUBLISH.getValue())) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "该策略已发布，不允许删除");
            }
            basicOffsetService.deleteById(id);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "删除成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @PostMapping("/editBasicOffset")
    @ApiOperation(value = "修改冲抵策略", notes = "修改冲抵策略")
    public ResponseResult editBasicOffset(@Validated @RequestBody EditBasicOffsetParams editBasicOffsetParams,
                                          @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicOffset offset = basicOffsetService.findById(editBasicOffsetParams.getId());
            if (Objects.equals(offset.getOffsetStatus(), BasicOffset.OffsetStatus.PUBLISH.getValue())) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "该策略已发布，不允许修改");
            }
            Example example = new Example(BasicOffset.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("offsetName", editBasicOffsetParams.getOffsetName());
            criteria.andNotEqualTo("id", editBasicOffsetParams.getId());
            if (basicOffsetService.selectCountByExample(example) > 0) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "费用项名称已存在");
            }
            BasicOffset basicOffset = new BasicOffset();
            basicOffset.setId(editBasicOffsetParams.getId());
            basicOffset.setOperatorTime(new Date());
            basicOffset.setOperator(user.getId());
            basicOffset.setOffsetName(editBasicOffsetParams.getOffsetName());
            basicOffset.setRemark(editBasicOffsetParams.getRemark());
            basicOffsetService.updateSelective(basicOffset);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/findBasicOffsetList")
    @ApiOperation(value = "查询冲抵策略列表", notes = "查询冲抵策略列表")
    public ResponseResult<List<BasicOffset>> findBasicOffsetList(@RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicOffset basicOffset = new BasicOffset();
            basicOffset.setOrgId(user.getOrgId());
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, basicOffsetService.findListByWhere(basicOffset));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/findBasicOffsetInfo/{id}")
    @ApiOperation(value = "查单条询冲抵策略", notes = "查单条询冲抵策略")
    public ResponseResult<BasicOffset> findBasicOffsetInfo(@PathVariable("id") @ApiParam(value = "冲抵Id", required = true) Long id,
                                                           @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, basicOffsetService.findById(id));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @PutMapping("/publishOffset/{id}")
    @ApiOperation(value = "冲抵策略发布", notes = "冲抵策略发布")
    public ResponseResult publishOffset(@PathVariable("id") @ApiParam("冲抵策略ID") Long id,
                                        @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            Example example = new Example(BasicOffsetItem.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("offsetId", id);
            if (basicOffsetItemService.selectCountByExample(example) <= 0) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, "请先配置冲抵项");
            }
            BasicOffset basicOffset = new BasicOffset();
            basicOffset.setId(id);
            basicOffset.setOffsetStatus(BasicOffset.OffsetStatus.PUBLISH.getValue());
            basicOffset.setOperatorTime(new Date());
            basicOffset.setOperator(user.getId());
            basicOffsetService.updateSelective(basicOffset);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, "发布成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @PutMapping("/cancelPublishOffset/{id}")
    @ApiOperation(value = "冲抵策略取消发布", notes = "冲抵策略取消发布")
    public ResponseResult cancelPublishOffset(@PathVariable("id") @ApiParam("冲抵策略ID") Long id,
                                              @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            Example example = new Example(BasicRepayScheme.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("offsetId", id);
            if (basicOffsetItemService.selectCountByExample(example) > 0) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, "冲抵项正在使用，不允许取消发布");
            }
            BasicOffset basicOffset = new BasicOffset();
            basicOffset.setId(id);
            basicOffset.setOffsetStatus(BasicOffset.OffsetStatus.UNPUBLISHED.getValue());
            basicOffset.setOperatorTime(new Date());
            basicOffset.setOperator(user.getId());
            basicOffsetService.updateSelective(basicOffset);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, "取消发布成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @PostMapping("/addBasicOffsetItem")
    @ApiOperation(value = "新增冲抵项", notes = "新增冲抵项")
    public ResponseResult addBasicOffsetItem(@Validated @RequestBody AddBasicOffsetItemParams addBasicOffsetItemParams,
                                             @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        logger.debug("REST request to addBasicExpense {}", addBasicOffsetItemParams);

        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicOffsetItem basicOffsetItem = new BasicOffsetItem();
            basicOffsetItem.setOffsetId(addBasicOffsetItemParams.getOffsetId());
            basicOffsetItem.setExpenseId(addBasicOffsetItemParams.getExpenseId());
            BasicOffsetItem item = basicOffsetItemService.findOne(basicOffsetItem);
            if (item != null) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, "已有冲抵项，不能添加了");
            } else {
                basicOffsetItemService.insertBasicOffsetItem(basicOffsetItem);
                return new ResponseResult(ResponseResult.Status.SUCCESS, "新增成功");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @DeleteMapping("/deleteBasicOffsetItem/{id}")
    @ApiOperation(value = "删除冲抵项", notes = "删除冲抵项")
    public ResponseResult deleteBasicOffsetItem(
            @PathVariable("id") @ApiParam(value = "id", required = true) Long id,
            @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        logger.debug("Rest request deleteBasicOffsetItem");

        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            if (basicOffsetItemService.findById(id) == null) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, "没有该项");
            } else {
                BasicOffsetItem basicOffsetItem = basicOffsetItemService.findById(id);
                basicOffsetItemService.updateItemOrder(basicOffsetItem);
                basicOffsetItemService.deleteById(id);
                return new ResponseResult(ResponseResult.Status.SUCCESS, "删除成功");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/findBasicOffsetItemList/{offsetId}")
    @ApiOperation(value = "查询冲抵项列表", notes = "查询冲抵项列表")
    public ResponseResult findBasicOffsetItemList(
            @PathVariable("offsetId") @ApiParam(value = "冲抵Id", required = true) Long offsetId,
            @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            return new ResponseResult(ResponseResult.Status.SUCCESS, basicOffsetItemService.selectByOffsetId(offsetId));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/moveDown/{id}")
    @ApiOperation(value = "下移冲抵项", notes = "下移冲抵项")
    public ResponseResult moveDown(
            @PathVariable("id") @ApiParam(value = "冲抵项Id") Long id,
            @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicOffsetItem item = new BasicOffsetItem();
            BasicOffsetItem basicOffsetItem = basicOffsetItemService.findById(id);
            if (basicOffsetItem != null) {
                item.setOffsetId(basicOffsetItem.getOffsetId());
                Long i = basicOffsetItem.getItemOrder();
                item.setItemOrder(i + 1);
                BasicOffsetItem item2 = basicOffsetItemService.findOne(item);
                if (item2 == null) {
                    return new ResponseResult(ResponseResult.Status.FAILURE, "已经是最低位置，不能下移");
                } else {
                    basicOffsetItem.setItemOrder(i + 1);
                    basicOffsetItemService.updateSelective(basicOffsetItem);
                    item2.setItemOrder(i);
                    basicOffsetItemService.updateSelective(item2);
                    return new ResponseResult(ResponseResult.Status.SUCCESS, "移动成功");
                }
            } else {
                return new ResponseResult(ResponseResult.Status.FAILURE, "该冲抵项不存在");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/moveUP/{id}")
    @ApiOperation(value = "上移冲抵项", notes = "上移冲抵项")
    public ResponseResult moveUP(
            @PathVariable("id") @ApiParam(value = "冲抵项Id", required = true) Long id,
            @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicOffsetItem offsetItem = new BasicOffsetItem();
            BasicOffsetItem basicOffsetItem = basicOffsetItemService.findById(id);
            if (basicOffsetItem != null) {
                offsetItem.setOffsetId(basicOffsetItem.getOffsetId());
                Long i = basicOffsetItem.getItemOrder();
                offsetItem.setItemOrder(i - 1);
                BasicOffsetItem item = basicOffsetItemService.findOne(offsetItem);
                if (item == null) {
                    return new ResponseResult(ResponseResult.Status.FAILURE, "已经是最高位置，不能上移");
                } else {
                    basicOffsetItem.setItemOrder(i - 1);
                    basicOffsetItemService.updateSelective(basicOffsetItem);
                    item.setItemOrder(i);
                    basicOffsetItemService.updateSelective(item);
                    return new ResponseResult(ResponseResult.Status.SUCCESS, "移动成功");

                }
            } else {
                return new ResponseResult(ResponseResult.Status.FAILURE, "该冲抵项不存在");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/findBasicOffsetItemInfo/{id}")
    @ApiOperation(value = "按单条查询冲抵项", notes = "按单条查询冲抵项")
    public ResponseResult findBasicOffsetItemInfo(
            @PathVariable("id") @ApiParam(value = "Id", required = true) Long id,
            @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        logger.debug("REST request to findBasicOffsetItemInfo");
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            return new ResponseResult(ResponseResult.Status.SUCCESS, basicOffsetItemService.selectById(id));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }


    @GetMapping("/findBasicOffsetByOrg")
    @ApiOperation(value = "获取当前用户所属机构下的冲抵策略", notes = "获取当前用户所属机构下的冲抵策略")
    public ResponseResult<List<BasicOffset>> findBasicOffsetByOrg(@RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicOffset basicOffset = new BasicOffset();
            basicOffset.setOrgId(user.getOrgId());
            List<BasicOffset> listByWhere = basicOffsetService.findListByWhere(basicOffset);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, listByWhere);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/findPublishBasicOffsetByOrg")
    @ApiOperation(value = "获取当前用户所属机构下的已发布的冲抵策略", notes = "获取当前用户所属机构下的已发布的冲抵策略")
    public ResponseResult<List<BasicOffset>> findPublishBasicOffsetByOrg(@RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicOffset basicOffset = new BasicOffset();
            basicOffset.setOrgId(user.getOrgId());
            basicOffset.setOffsetStatus(BasicOffset.OffsetStatus.PUBLISH.getValue());
            List<BasicOffset> listByWhere = basicOffsetService.findListByWhere(basicOffset);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, listByWhere);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

}
