package com.life.bank.palm.web.controller.comunity.convertor;

import com.life.bank.palm.dao.community.pojo.CommunityPostPO;

import com.life.bank.palm.service.community.enums.EntityTypeEnum;
import com.life.bank.palm.web.controller.comunity.vo.CommunityPostVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * author:薯条哥搞offer
 */
@Mapper(imports = EntityTypeEnum.class)
public interface CommunityPostVoConvertor {
    CommunityPostVoConvertor INSTANCE = Mappers.getMapper(CommunityPostVoConvertor.class);

    CommunityPostVo toVO(CommunityPostPO po);

    CommunityPostPO toVO(CommunityPostVo po);


    List<CommunityPostVo> toRecords(List<CommunityPostPO> pos);
}
