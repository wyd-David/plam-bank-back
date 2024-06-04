package com.life.bank.palm.web.controller.comunity.convertor;

import com.life.bank.palm.dao.community.pojo.BankCommentPO;
import com.life.bank.palm.dao.community.pojo.CommunityPostPO;
import com.life.bank.palm.service.community.enums.EntityTypeEnum;
import com.life.bank.palm.web.controller.comunity.vo.BankCommentVo;
import com.life.bank.palm.web.controller.comunity.vo.CommunityPostVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * author:薯条哥搞offer
 */
@Mapper
public interface BankCommentVoConvertor {
    BankCommentVoConvertor INSTANCE = Mappers.getMapper(BankCommentVoConvertor.class);

    BankCommentVo toVO(BankCommentPO po);

    BankCommentPO toVO(BankCommentVo vo);


    List<BankCommentVo> toRecords(List<BankCommentPO> pos);
}
