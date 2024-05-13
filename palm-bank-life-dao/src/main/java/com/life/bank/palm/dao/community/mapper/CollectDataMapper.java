package com.life.bank.palm.dao.community.mapper;

import com.life.bank.palm.dao.community.pojo.CollectDataPO;

/**
* @author  @author ：麻薯哥搞offer
* @description 针对表【collect_data】的数据库操作Mapper
* @createDate 2024-04-22 22:58:45
* @Entity com.life.bank.palm.dao.community.pojo.CollectDataPO
*/
public interface CollectDataMapper {

    int deleteByPrimaryKey(Long id);

    int insert(CollectDataPO record);

    int insertSelective(CollectDataPO record);

    CollectDataPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CollectDataPO record);

    int updateByPrimaryKey(CollectDataPO record);

}
