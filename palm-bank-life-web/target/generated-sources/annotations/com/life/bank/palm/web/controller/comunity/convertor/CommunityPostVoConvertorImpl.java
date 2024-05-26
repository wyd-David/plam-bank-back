package com.life.bank.palm.web.controller.comunity.convertor;

import com.life.bank.palm.dao.community.pojo.CommunityPostPO;
import com.life.bank.palm.service.community.enums.EntityTypeEnum;
import com.life.bank.palm.web.controller.comunity.vo.CommunityPostVo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-26T20:04:55+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_371 (Oracle Corporation)"
)
public class CommunityPostVoConvertorImpl implements CommunityPostVoConvertor {

    @Override
    public CommunityPostVo toVO(CommunityPostPO po) {
        if ( po == null ) {
            return null;
        }

        CommunityPostVo communityPostVo = new CommunityPostVo();

        if ( po.getId() != null ) {
            communityPostVo.setId( String.valueOf( po.getId() ) );
        }
        communityPostVo.setTitle( po.getTitle() );
        communityPostVo.setContent( po.getContent() );
        communityPostVo.setRichContent( po.getRichContent() );
        communityPostVo.setZanCnt( po.getZanCnt() );
        communityPostVo.setCollectCnt( po.getCollectCnt() );
        communityPostVo.setViewCnt( po.getViewCnt() );
        communityPostVo.setCreateTime( po.getCreateTime() );

        return communityPostVo;
    }

    @Override
    public CommunityPostPO toVO(CommunityPostVo po) {
        if ( po == null ) {
            return null;
        }

        CommunityPostPO communityPostPO = new CommunityPostPO();

        if ( po.getId() != null ) {
            communityPostPO.setId( Long.parseLong( po.getId() ) );
        }
        communityPostPO.setTitle( po.getTitle() );
        communityPostPO.setContent( po.getContent() );
        communityPostPO.setRichContent( po.getRichContent() );
        communityPostPO.setZanCnt( po.getZanCnt() );
        communityPostPO.setCollectCnt( po.getCollectCnt() );
        communityPostPO.setViewCnt( po.getViewCnt() );
        communityPostPO.setCreateTime( po.getCreateTime() );

        return communityPostPO;
    }

    @Override
    public List<CommunityPostVo> toRecords(List<CommunityPostPO> pos) {
        if ( pos == null ) {
            return null;
        }

        List<CommunityPostVo> list = new ArrayList<CommunityPostVo>( pos.size() );
        for ( CommunityPostPO communityPostPO : pos ) {
            list.add( toVO( communityPostPO ) );
        }

        return list;
    }
}
