package com.life.bank.palm.dao.community.mapper;

import com.life.bank.palm.dao.community.pojo.BankCommentPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BankCommentMapper {

    int insert(BankCommentPO commentPO);

    List<BankCommentPO> select();
    List<BankCommentPO> sub_select(int userId);

    int delete(int id);
}
