package com.life.bank.palm.web.controller.comunity.convertor;

import com.life.bank.palm.dao.community.pojo.BankCommentPO;
import com.life.bank.palm.dao.community.pojo.CommunityPostPO;
import com.life.bank.palm.web.controller.comunity.vo.BankCommentVo;
import com.life.bank.palm.web.controller.comunity.vo.CommunityPostVo;
import org.mapstruct.factory.Mappers;

import java.util.List;

public interface BankCommentVoConvertor {

    BankCommentVoConvertor INSTANCE = Mappers.getMapper(BankCommentVoConvertor.class);

    BankCommentVo toVO(BankCommentPO po);

    BankCommentPO toPO(BankCommentVo vo);


    List<BankCommentVo> toRecords(List<BankCommentPO> pos);
}
