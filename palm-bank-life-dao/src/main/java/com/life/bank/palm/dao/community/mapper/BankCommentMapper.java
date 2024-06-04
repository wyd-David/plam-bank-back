package com.life.bank.palm.dao.community.mapper;
import java.util.Collection;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.life.bank.palm.dao.community.pojo.BankCommentPO;

/**
* @author shutiaoge
* @description 针对表【bank_comment】的数据库操作Mapper
* @createDate 2024-06-04 16:16:05
* @Entity com.life.bank.palm.dao.community.pojo.BankCommentPO
*/
public interface BankCommentMapper {

    int deleteByPrimaryKey(Long id);

    int insert(BankCommentPO record);

    int insertSelective(BankCommentPO record);

    BankCommentPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BankCommentPO record);

    int updateByPrimaryKey(BankCommentPO record);

    List<BankCommentPO> selectByEntityIdAndEntityType(@Param("entityId") Integer entityId, @Param("entityType") Integer entityType);

    List<BankCommentPO> selectByEntityTypeAndEntityIdIn(@Param("entityType") Integer entityType, @Param("entityIdList") Collection<Integer> entityIdList);
}
