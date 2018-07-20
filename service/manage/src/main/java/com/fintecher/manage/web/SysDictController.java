package com.fintecher.manage.web;

import com.fintecher.common.vo.manage.ResponseResult;
import com.fintecher.entity.SysDict;
import com.fintecher.entity.SysDictItem;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.mapper.DataDictMapper;
import com.fintecher.manage.service.DataDictService;
import com.fintecher.manage.service.DataDictTypeService;
import com.fintecher.manage.vo.ExportDataDict;
import com.fintecher.manage.vo.SysDictItemModel;
import com.fintecher.manage.vo.SysDictModel;
import com.fintecher.manage.vo.SysDictSysModel;
import com.fintecher.util.Constants;
import com.fintecher.util.ExcelUtil;
import com.xiaoleilu.hutool.date.DateTime;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author: jwdstef
 * @Description: 数据字典
 * @Date 2017/5/31
 */
@RestController
@RequestMapping("/sysDictController")
@Api(description = "数据字典管理")
public class SysDictController extends BaseController {
    @Autowired
    private DataDictService dataDictService;
    @Resource
    RestTemplate restTemplate;
    @Resource
    private DataDictMapper dataDictMapper;
    @Autowired
    private DataDictTypeService dataDictTypeService;

    /**
     * @System: 车贷金融
     * @Auther: xiaqun
     * @Description: 查询数据字典项
     * @Modified By:
     */
    @GetMapping("/getAll")
    @ApiOperation(value = "查询系统数据字典项", notes = "查询系统数据字典项")
    public ResponseResult getAll() {
        logger.debug("request to get all by sys");
        try {
            List<SysDictItem> list = dataDictService.getAll();
            return new ResponseResult(ResponseResult.Status.SUCCESS,list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, e.getMessage());
        }
    }
    @GetMapping("/findOrgAll")
    @ApiOperation(value = "查询用户自定义数据字典项", notes = "查询用户自定义数据字典项")
    public ResponseResult findAll(@RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        logger.debug("request to get all by user");
        try {
            SysUser sysUser = getUserByAuth(authorization);
            if (Objects.isNull(sysUser)){
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<SysDictItem> list = dataDictService.findAll();
            return new ResponseResult(ResponseResult.Status.SUCCESS,list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, e.getMessage());
        }
    }



    /**
     * @System: 车贷金融
     * @Auther: dwx
     * @Description: 删除数据字典项
     * @Modified By:
     */
    @DeleteMapping("/deleteDataDict")
    @ApiOperation(value = "删除用户自定义数据字典项", notes = "删除用户自定义数据字典项")
    public ResponseResult deleteDataDict(@RequestParam @ApiParam(value = "数据字典项ID", required = true) Integer id, @RequestHeader(value = Constants.AUTHORIZATION)
                                         String authorization) {
        logger.debug("request to delete data dict");
        try {
            SysUser userInfo = getUserByAuth(authorization);
            if (Objects.isNull(userInfo)){
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            SysDictItem sysDictItem = new SysDictItem();
            sysDictItem.setId(id);
            dataDictService.deleteDataDict(sysDictItem);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, e.getMessage());
        }
    }

    /**
     * @System: 车贷金融
     * @Auther: dwx
     * @Description: 新增数据字典项
     * @Modified By:
     */
    @PostMapping("/createSysDataDict")
    @ApiOperation(value = "新增系统数据字典项", notes = "新增系统数据字典项")
    public ResponseResult createSysDataDict(@RequestBody SysDictSysModel sysDictItem, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser userInfo = getUserByAuth(authorization);
            if (Objects.isNull(userInfo)){
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            if (Objects.isNull(sysDictItem.getId())) {
                //查询当前数据是否存在
                SysDictItem sysDictItem1 = new SysDictItem();
                sysDictItem1.setDictItemName(sysDictItem.getDictItemName());
                SysDictItem dict = dataDictService.findOne(sysDictItem1);
                if(dict != null)
                {
                    return new ResponseResult<>(ResponseResult.Status.FAILURE, "该数据项下存在该数据");
                }
                sysDictItem1.setDictItemCode(sysDictItem.getDictItemCode());
                sysDictItem1.setDictId(sysDictItem.getDictId());
                sysDictItem1.setDictItemName(sysDictItem.getDictItemName());
                sysDictItem1.setDictItemStatus(0);
                Integer i = dataDictService.getMaxId();
                sysDictItem1.setDictItemCode("00"+(i+1));
                dataDictService.saveDataDict(sysDictItem1);
            }
            return new ResponseResult(ResponseResult.Status.SUCCESS, "增加成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, e.getMessage());
        }
    }

    /**
     * @System: 车贷金融
     * @Auther: dwx
     * @Description: 新增用户自定义数据字典项
     * @Modified By:
     */
    @PostMapping("/createUserDataDict")
    @ApiOperation(value = "新增用户自定义数据字典项", notes = "新增用户自定义数据字典项")
    public ResponseResult createUserDataDict(@RequestBody SysDictItemModel sysDictItem, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser userInfo = getUserByAuth(authorization);
            if (Objects.isNull(userInfo)){
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            if (Objects.isNull(sysDictItem.getId())) {
                //查询当前数据是否存在
                SysDictItem sysDictItem1 = new SysDictItem();
                sysDictItem1.setDictItemName(sysDictItem.getDictItemName());
                SysDictItem dict = dataDictService.findOne(sysDictItem1);
                if(dict != null)
                {
                    return new ResponseResult<>(ResponseResult.Status.FAILURE, "该数据项下存在该数据");
                }
                sysDictItem1.setDictId(sysDictItem.getDictId());
                sysDictItem1.setOrgId(userInfo.getOrgId());
                sysDictItem1.setDictItemStatus(0);
                Integer i = dataDictService.getMaxId();
                sysDictItem1.setDictItemCode("00"+(i+1));
                dataDictService.saveDataDict(sysDictItem1);
            }
            return new ResponseResult(ResponseResult.Status.SUCCESS, "增加成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, e.getMessage());
        }
    }



    /**
     * @System: 车贷金融
     * @Auther: dwx
     * @Description: 修改数据字典项
     * @Modified By:
     */
    @PostMapping("/updateDataDict")
    @ApiOperation(value = "修改数据字典项", notes = "修改数据字典项")
    public ResponseResult updateDataDict(@RequestBody SysDictItemModel sysDictItem, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        logger.debug("request to create or modify data dict");
        try {
            SysUser userInfo = getUserByAuth(authorization);
            if (Objects.isNull(userInfo)){
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            if (Objects.nonNull(sysDictItem.getId())) {
                SysDictItem sysDictItem1 = new SysDictItem();
                sysDictItem1.setId(sysDictItem.getId());
                sysDictItem1.setDictId(sysDictItem.getDictId());
                sysDictItem1.setDictItemName(sysDictItem.getDictItemName());
                BeanUtils.copyProperties(sysDictItem,sysDictItem1);
                List<SysDictItem> list = dataDictMapper.queryDictItemName(sysDictItem.getDictId());
                for (SysDictItem sysDictItem2 :list ){
                    if (sysDictItem2.getDictItemName().equals(sysDictItem.getDictItemName())){
                        return new ResponseResult(ResponseResult.Status.FAILURE,"字典项名称重复，请重新修改");
                    }
                }
                dataDictService.updateDataDict(sysDictItem1);
            }
            return new ResponseResult(ResponseResult.Status.SUCCESS, "修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, e.getMessage());
        }
    }

    /**
     * @System: 车贷金融
     * @Auther: dwx
     * @Description: 分页查询数据字典类型对应的数据字典项
     * @Modified By:
     */
    @GetMapping("/getDataDictByTypeCode")
    @ApiOperation(value = "查询数据字典类型对应的数据字典项", notes = "查询数据字典类型对应的数据字典项")
    public ResponseResult getDataDictByTypeCode(@RequestParam(required = true) @ApiParam(value = "字典类型id")Integer id,
                                                        @RequestParam(required = false) @ApiParam(value = "名称") String dictItemName,
                                                         @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        logger.debug("request to get all data dict");
        try {
            SysUser userInfo = getUserByAuth(authorization);
            if (Objects.isNull(userInfo)){
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<SysDict> list = dataDictService.findDataDictByTypeCode(id,dictItemName);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "", list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, e.getMessage());
        }
    }

    /**
     * @System: 车贷金融
     * @Auther: dwx
     * @Description: 查询所有数据字典类型
     * @Modified By:
     */
    @GetMapping("/getAllSysDictType")
    @ApiOperation(value = "查询系统数据字典类型", notes = "查询系统数据字典类型")
    public ResponseResult getAllSysDictType(@RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        logger.debug("request to get all dict type");
        try {
            SysUser userInfo = getUserByAuth(authorization);
            if (Objects.isNull(userInfo)){
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<SysDict> list = dataDictTypeService.selectListAll();
            return new ResponseResult(ResponseResult.Status.SUCCESS, "", list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, e.getMessage());
        }
    }
    /**
     * @System: 车贷金融
     * @Auther: dwx
     * @Description: 查询所有数据字典类型
     * @Modified By:
     */
    @GetMapping("/getAllUserDictType")
    @ApiOperation(value = "查询用户自定义数据字典类型", notes = "查询用户自定义数据字典类型")
    public ResponseResult getAllUserDictType(@RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        logger.debug("request to get all dict type");
        try {
            SysUser userInfo = getUserByAuth(authorization);
            if (Objects.isNull(userInfo)){
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<SysDict> list = dataDictTypeService.findAll();
            return new ResponseResult(ResponseResult.Status.SUCCESS, "", list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, e.getMessage());
        }
    }


    /**
     * @System: 车贷金融
     * @Auther: dwx
     * @Description: 新增数据字典类型
     * @Modified By:
     */
    @PostMapping("/createDataDictType")
    @ApiOperation(value = "新增数据字典类型", notes = "新增数据字典类型")
    public ResponseResult createDataDictType(@RequestBody SysDictModel dataDictType, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        logger.debug("request to create or modify data dict type");
        try {
            SysUser userInfo = getUserByAuth(authorization);
            if (Objects.isNull(userInfo)){
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            if (Objects.isNull(dataDictType.getId())) {
                SysDict oldDictType= new SysDict();
                oldDictType.setDictName(dataDictType.getDictName());
                oldDictType.setDictType(dataDictType.getDictType());
                SysDict returnDictTypes = dataDictTypeService.findOne(oldDictType);
                if (Objects.nonNull(returnDictTypes)) {
                    return new ResponseResult(ResponseResult.Status.FAILURE, "该数据类型名称已存在，请重新输入");
                }
                Integer i = dataDictTypeService.getMaxId();
                oldDictType.setDictCode("00"+(i+1));
                dataDictTypeService.saveDataDict(oldDictType);
            }
            return new ResponseResult(ResponseResult.Status.SUCCESS, "增加成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, e.getMessage());
        }
    }


    /**
     * @System: 车贷金融
     * @Auther: dwx
     * @Description: 修改数据字典类型
     * @Modified By:
     */
    @PostMapping("/modifyDataDictType")
    @ApiOperation(value = "修改数据字典类型", notes = "修改数据字典类型")
    public ResponseResult modifyDataDictType(@RequestBody SysDictModel sysDictModel, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        logger.debug("request to create or modify data dict");
        try {
            SysUser userInfo = getUserByAuth(authorization);
            if (Objects.isNull(userInfo)){
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            if (Objects.nonNull(sysDictModel.getId())) {
                SysDict oldDictType = new SysDict();
                oldDictType.setDictCode(sysDictModel.getDictCode());
                oldDictType.setDicSort(sysDictModel.getDicSort());
                oldDictType.setDictName(sysDictModel.getDictName());
                oldDictType.setDictType(sysDictModel.getDictType());
                oldDictType.setRemark(sysDictModel.getRemark());
                dataDictTypeService.updateDataDict(oldDictType);
            }
            return new ResponseResult(ResponseResult.Status.SUCCESS, "修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, e.getMessage());
        }
    }

    /**
     * @auther: dwx
     * @Description:根据数据字典类型查询数据字典项
     */
    @GetMapping("/getSysItemBySysDictType")
    @ApiOperation(value = "根据数据字典类型查询数据字典项",notes = "根据数据字典类型查询数据字典项")
    public ResponseResult getSysItemBySysDictType(@RequestParam Integer id, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try{
            SysUser userInfo = getUserByAuth(authorization);
            if (Objects.isNull(userInfo)){
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<SysDict> list = dataDictService.getSysDictList(id);
            return new ResponseResult(ResponseResult.Status.SUCCESS,list);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new ResponseResult(ResponseResult.Status.FAILURE,"查询失败");
        }
    }

    /**
     * @Description:导出数据字典项
     * @Modified By:
     */
    @PostMapping("/exportDataDict")
    @ApiOperation(value = "导出数据字典项", notes = "导出数据字典项")
    public ResponseResult exportDataDict() {
        HSSFWorkbook workbook = null;
        File file = null;
        ByteArrayOutputStream out = null;
        FileOutputStream fileOutputStream = null;
        List<ExportDataDict> allDataDict =dataDictMapper.findAllDataDict();
        List<ExportDataDict> allDataDictLists = new ArrayList<>();
        for(ExportDataDict dataDict : allDataDict){
            ExportDataDict exportDataDict = new ExportDataDict();
            exportDataDict.setDataName(Objects.isNull(dataDict.getDataName())? " " : dataDict.getDataName());
            exportDataDict.setTypeName(Objects.isNull(dataDict.getTypeName())? " " : dataDict.getTypeName());
            exportDataDict.setSort(Objects.isNull(dataDict.getSort())? " " : dataDict.getSort());
            allDataDictLists.add(exportDataDict);
        }
        try {
            String[] titleList = {"类型名称", "数据字典项名称", "分组"};
            String[] proNames = {"typeName", "dataName", "sort"};
            workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("sheet1");
            ExcelUtil.createExcel(workbook, sheet, allDataDictLists, titleList, proNames, 0, 0);
            out = new ByteArrayOutputStream();
            workbook.write(out);
            String filePath = FileUtils.getTempDirectoryPath().concat(File.separator).concat(DateTime.now().toString("yyyyMMddhhmmss") + "exportDataDict.xls");
            file = new File(filePath);
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(out.toByteArray());
            FileSystemResource resource = new FileSystemResource(file);
            MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
            param.add("file", resource);
            ResponseEntity<String> url = restTemplate.postForEntity("http://service-file/uploadFile/addUploadFileUrl", param, String.class);
            if (url == null) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "上传服务器失败");
            } else {
                return new ResponseResult(ResponseResult.Status.SUCCESS, "操作成功", url.getBody());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, e.getMessage());
        } finally {
            // 关闭流
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                    e.printStackTrace();
                }
            }
            // 删除文件
            if (file != null) {
                file.delete();
            }
        }
    }

}
