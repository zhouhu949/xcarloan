package com.fintecher.manage.service.impl;

import com.fintecher.entity.BasicOrderCar;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.mapper.BasicOrderCarMapper;
import com.fintecher.manage.service.BasicOrderCarService;
import com.fintecher.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(rollbackFor = Exception.class)
@Service
public class BasicOrderCarServiceImpl extends BaseServiceImpl<BasicOrderCar> implements BasicOrderCarService {
    @Autowired
    BasicOrderCarMapper basicOrderCarMapper;
    @Autowired
    UserService userService;
    @Override
    public List<BasicOrderCar> findOrderCar(SysUser user, Integer hasSupplierLoan) {
        Map map = new HashMap();
        List<String> dataAuth = userService.findAllDataAuth(user.getId());
        List<String> exceptDataAuth = userService.findAllExceptDataAuth(user.getId());
        map.put("dataAuth", dataAuth);
        map.put("exceptDataAuth", exceptDataAuth);
        map.put("hasSupplierLoan", hasSupplierLoan);
        List<BasicOrderCar> list = basicOrderCarMapper.findOrderCar(map);
        return list;
    }
}
