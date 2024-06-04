package com.life.bank.palm.service.community;

import com.life.bank.palm.dao.community.mapper.BankCommentMapper;
import com.life.bank.palm.dao.community.pojo.BankCommentPO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author: 薯条哥搞offer
 * @createTime: 2024/06/04 16:18
 * @company: <a href="https://www.neituiya.top">内推鸭小程序</a>
 */
@Service
public class BankCommentService {
    @Autowired
    private BankCommentMapper bankCommentMapper;

    public List<BankCommentPO> selectByEntityIdAndEntityType(Integer entityId,Integer entityType){
        return bankCommentMapper.selectByEntityIdAndEntityType(entityId,entityType);
    }

    public List<BankCommentPO> selectByEntityTypeAndEntityIdIn(Integer entityType, Collection<Integer> entityIdList){
        if (CollectionUtils.isEmpty(entityIdList)){
            return new ArrayList<>();
        }
        return bankCommentMapper.selectByEntityTypeAndEntityIdIn(entityType, entityIdList);
    }
}
