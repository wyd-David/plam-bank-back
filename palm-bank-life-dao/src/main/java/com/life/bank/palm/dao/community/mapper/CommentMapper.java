package com.life.bank.palm.dao.community.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.life.bank.palm.dao.community.pojo.CommentPO;

/**
* @author 薯条哥搞offer
* @description 针对表【comment】的数据库操作Mapper
* @createDate 2024-05-05 15:25:01
* @Entity com.life.bank.palm.dao.community.pojo.CommentPO
*/
public interface CommentMapper {

    int deleteByPrimaryKey(Long id);

    int insert(CommentPO record);

    int insertSelective(CommentPO record);

    CommentPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CommentPO record);

    int updateByPrimaryKey(CommentPO record);

    List<CommentPO> selectByCommentEntityTypeAndCommentEntityId(@Param("commentEntityType") Integer commentEntityType, @Param("commentEntityId") Long commentEntityId);

}
