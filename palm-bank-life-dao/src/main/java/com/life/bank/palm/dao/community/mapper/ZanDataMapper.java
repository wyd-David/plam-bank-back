package com.life.bank.palm.dao.community.mapper;

import com.life.bank.palm.dao.community.pojo.ZanDataPO;

/**
* @author  @author ：麻薯哥搞offer
* @description 针对表【zan_data】的数据库操作Mapper
* @createDate 2024-04-22 22:58:54
* @Entity com.life.bank.palm.dao.community.pojo.ZanDataPO
*/
public interface ZanDataMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ZanDataPO record);

    int insertSelective(ZanDataPO record);

    ZanDataPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ZanDataPO record);

    int updateByPrimaryKey(ZanDataPO record);

}
