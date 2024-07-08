package com.life.bank.palm.service.community;

import com.life.bank.palm.dao.community.mapper.BankCommentMapper;
import com.life.bank.palm.dao.community.pojo.BankCommentPO;
import com.life.bank.palm.dao.user.mapper.UserMapper;
import com.life.bank.palm.dao.user.pojo.UserPO;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankCommentService {

    @Autowired
    private BankCommentMapper bankCommentMapper;

    public int insert(BankCommentPO commentPO){

        return bankCommentMapper.insert(commentPO);
    }

    public List<BankCommentPO> getComment(){
        List<BankCommentPO> parentComments = bankCommentMapper.select();
        List<BankCommentPO> resultList = new ArrayList<>();
        for(BankCommentPO commentPO : parentComments){
            //子评论的entity_id
            int user_id = commentPO.getUserId();
            List<BankCommentPO> subList = bankCommentMapper.sub_select(user_id);
            commentPO.setSubList(subList);
            resultList.add(commentPO);
        }
        return resultList;
    }

    public int delete(int id){
        return bankCommentMapper.delete(id);
    }

}
