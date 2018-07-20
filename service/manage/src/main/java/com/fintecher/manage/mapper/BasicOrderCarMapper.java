package com.fintecher.manage.mapper;

import com.fintecher.entity.BasicOrderCar;
import com.fintecher.manage.util.MyMapper;

import java.util.List;
import java.util.Map;

public interface BasicOrderCarMapper extends MyMapper<BasicOrderCar> {

   BasicOrderCar selectBasicOrderCarByOrderId(Long orderId);

   List<BasicOrderCar> findOrderCar(Map map);
}
