package com.fintecher.manage.web;

import com.fintecher.common.vo.manage.ResponseResult;
import com.fintecher.entity.BasicOrderFile;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.mapper.BasicOrderFileMapper;
import com.fintecher.manage.service.BasicOrderFileService;
import com.fintecher.manage.service.UserService;
import com.fintecher.manage.vo.BasicOrderFileModel;
import com.fintecher.manage.vo.CustomerOrderFileCountModel;
import com.fintecher.util.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/14 11:28
 * @Description:
 */
@RestController
@RequestMapping("/basicOrderFileController")
@Api(description = "订单资料上传")
public class BasicOrderFileController extends BaseController {

    @Autowired
    private BasicOrderFileService basicOrderFileService;

    @Autowired
    private UserService userService;
    @Autowired
    private BasicOrderFileMapper basicOrderFileMapper;
    /**
     * @auther: dwx
     * @Description:新增上传资料
     */
    @PostMapping("/addUploadBasicOrderFile")
    @ApiOperation(value = "新增上传资料",notes = "新增上传资料")
    public ResponseResult addUploadBasicOrderFile(@RequestBody BasicOrderFileModel basicOrderFileModel,
                                                  @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try{
            SysUser userInfo = getUserByAuth(authorization);
            if (Objects.isNull(userInfo)){
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            basicOrderFileService.uploadBasicOrderFile(basicOrderFileModel, userInfo);
            return new ResponseResult(ResponseResult.Status.SUCCESS,"上传成功");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new ResponseResult(ResponseResult.Status.FAILURE,"上传失败");
        }
    }
    /**
     * @auther: dwx
     * @Description:修改上传资料
     */
    @PutMapping("/updateUploadBasicOrderFile")
    @ApiOperation(value = "修改上传资料",notes = "修改上传资料")
    public ResponseResult updateUploadBasicOrderFile(@RequestBody BasicOrderFileModel basicOrderFileModel,
                                                     @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try{
            SysUser userInfo = getUserByAuth(authorization);
            if (Objects.isNull(userInfo)){
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            basicOrderFileService.updateBasicOrderFile(basicOrderFileModel, userInfo);
            return new ResponseResult(ResponseResult.Status.SUCCESS,"修改成功");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new ResponseResult(ResponseResult.Status.FAILURE,"修改失败");
        }
    }




    /**
     * @auther: dwx
     * @Description:查看上传资料
     */
    @GetMapping("/findUploadBasicOrderFile")
    @ApiOperation(value = "查看上传资料",notes = "查看上传资料")
    public ResponseResult findUploadBasicOrderFile(@RequestParam Long fileId, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try{
            SysUser userInfo = getUserByAuth(authorization);
            if (Objects.isNull(userInfo)){
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicOrderFile basicOrderFileModel = basicOrderFileService.findUploadBasicFile(fileId);
            return new ResponseResult(ResponseResult.Status.SUCCESS,basicOrderFileModel);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new ResponseResult(ResponseResult.Status.FAILURE,"系统异常");
        }
    }
    /**
     * @auther: dwx
     * @Description:删除资料
     */
    @DeleteMapping("/deleteUploadBasicFile")
    @ApiOperation(value = "删除资料",notes = "删除资料")
    public ResponseResult deleteUploadBasicFile(@RequestParam Long fileId, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try{
            SysUser userInfo = getUserByAuth(authorization);
            if (Objects.isNull(userInfo)){
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            basicOrderFileService.deleteById(fileId);
            return new ResponseResult(ResponseResult.Status.SUCCESS,"删除成功");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new ResponseResult(ResponseResult.Status.FAILURE,"删除失败");
        }
    }

    @GetMapping("/getOrderFile/{orderId}")
    @ApiOperation(value = "查询订单附件" ,notes = "查询订单附件")
    public ResponseResult<CustomerOrderFileCountModel> getOrderCar(@PathVariable("orderId") @ApiParam("订单ID") Long orderId, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser userInfo = getUserByAuth(authorization);
            if (Objects.isNull(userInfo)){
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<CustomerOrderFileCountModel> list = basicOrderFileMapper.findCountByOrderFile(orderId);
            return new ResponseResult(ResponseResult.Status.SUCCESS, list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }


}
