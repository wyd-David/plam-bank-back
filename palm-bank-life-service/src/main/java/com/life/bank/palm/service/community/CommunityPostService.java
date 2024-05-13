package com.life.bank.palm.service.community;

import cn.hutool.core.util.PageUtil;
import com.life.bank.palm.common.result.PageResult;
import com.life.bank.palm.dao.community.mapper.CommunityPostMapper;
import com.life.bank.palm.dao.community.pojo.CommunityPostPO;
import com.life.bank.palm.service.snowflake.SnowflakeIdUtils;
import com.life.bank.palm.service.user.utils.RequestContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * author:薯条哥搞offer
 * 社区
 */
@Service
public class CommunityPostService {
    @Autowired
    private CommunityPostMapper communityPostMapper;

    /**
     * 发布接口
     */
    public void createOne(CommunityPostPO communityPostPO) {
        communityPostPO.setId(SnowflakeIdUtils.nextId());
        Integer currentUserId = RequestContextUtil.getCurrentUserId();
        communityPostPO.setUserId(currentUserId);
        communityPostPO.setCreateTime(new Date());
        communityPostMapper.insertSelective(communityPostPO);
    }

    /**
     * 列表接口
     * 这个翻页还有另外一种写法，后面可以给大家当做作业进行使用
     * @return
     */
    public PageResult index(int pageNo, int pageSize) {
        long offset = PageUtil.getStart(pageNo - 1, pageSize);
        int limit = pageSize;
        List<CommunityPostPO> communityPostPOList =
                communityPostMapper.search(null, offset, limit);
        int total = communityPostMapper.searchCount(null);
        return PageResult.buildByResult(total, pageNo, pageSize, communityPostPOList);
    }


    public List<CommunityPostPO> search(String keywords, long offset, int limit) {
        List<CommunityPostPO> communityPostPOList =
                communityPostMapper.getByTitleLikeOrderByIdDesc(keywords, offset, limit);
        return communityPostPOList;
    }

    /**
     * 更新文章信息，包含更新ces字段：评论、点赞、收藏
     *
     * @param communityPostPO
     * @return
     */
    public int updateOne(CommunityPostPO communityPostPO) {
        return communityPostMapper.updateByPrimaryKeySelective(communityPostPO);
    }

}
