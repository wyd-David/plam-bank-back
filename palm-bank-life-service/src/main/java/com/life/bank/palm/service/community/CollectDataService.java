package com.life.bank.palm.service.community;

import com.life.bank.palm.dao.community.mapper.CollectDataMapper;
import com.life.bank.palm.dao.community.pojo.CollectDataPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author:薯条哥搞offer
 * 点赞
 */
@Service
public class CollectDataService {
    @Autowired
    private CollectDataMapper collectDataMapper;

    public int insertOne(CollectDataPO collectDataPO){
       return collectDataMapper.insert(collectDataPO);
    }

    public int deleteOne(Long  id){
        return collectDataMapper.deleteByPrimaryKey(id);
    }
}
