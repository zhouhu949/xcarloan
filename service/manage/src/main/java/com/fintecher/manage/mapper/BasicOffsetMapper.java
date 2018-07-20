package com.fintecher.manage.mapper;

import com.fintecher.entity.BasicOffset;
import com.fintecher.manage.util.MyMapper;

import java.util.List;
import java.util.Map;

public interface BasicOffsetMapper extends MyMapper<BasicOffset> {

   void  updateBasicOffset(BasicOffset basicOffset);

   int deletecheck(BasicOffset basicOffset);

   /**
    * 获取数据权限下的冲抵策略
    * @param params
    * @return
    */
   List<BasicOffset> findBasicOffsetByAuth(Map<String, List<String>> params);

}
