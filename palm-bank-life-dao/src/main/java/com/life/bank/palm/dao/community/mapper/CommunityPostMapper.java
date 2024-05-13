package com.life.bank.palm.dao.community.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.life.bank.palm.dao.community.pojo.CommunityPostPO;

/**
 * @author @author ：麻薯哥搞offer
 * @description 针对表【community_post】的数据库操作Mapper
 * @createDate 2024-04-22 22:59:01
 * @Entity com.life.bank.palm.dao.community.pojo.CommunityPostPO
 */
public interface CommunityPostMapper {

    int deleteByPrimaryKey(Long id);

    int insert(CommunityPostPO record);

    int insertSelective(CommunityPostPO record);

    CommunityPostPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CommunityPostPO record);

    int updateByPrimaryKey(CommunityPostPO record);

    List<CommunityPostPO> getByUserId(@Param("userId") Integer userId,
                                      @Param("offset") Long offset,
                                      @Param("limit") Integer limit);


    List<CommunityPostPO> getByTitleLikeOrderByIdDesc(@Param("title") String title,
                                                      @Param("offset") Long offset,
                                                      @Param("limit") Integer limit);

    int getByTitleLikeCount(@Param("title") String title);

    List<CommunityPostPO> search(@Param("keywords") String keywords,
                                                      @Param("offset") Long offset,
                                                      @Param("limit") Integer limit);

    int searchCount(@Param("keywords") String keywords);

}
