package com.life.bank.palm.web.controller.comunity.convertor;

import com.life.bank.palm.dao.community.pojo.BankCommentPO;
import com.life.bank.palm.web.controller.comunity.vo.BankCommentVo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-04T16:56:04+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_221 (Oracle Corporation)"
)
public class BankCommentVoConvertorImpl implements BankCommentVoConvertor {

    @Override
    public BankCommentVo toVO(BankCommentPO po) {
        if ( po == null ) {
            return null;
        }

        BankCommentVo bankCommentVo = new BankCommentVo();

        bankCommentVo.setId( po.getId() );
        bankCommentVo.setUserId( po.getUserId() );
        bankCommentVo.setEntityId( po.getEntityId() );
        bankCommentVo.setEntityType( po.getEntityType() );
        bankCommentVo.setCommentContent( po.getCommentContent() );
        bankCommentVo.setCreateTime( po.getCreateTime() );
        bankCommentVo.setUpdateTime( po.getUpdateTime() );

        return bankCommentVo;
    }

    @Override
    public BankCommentPO toVO(BankCommentVo vo) {
        if ( vo == null ) {
            return null;
        }

        BankCommentPO bankCommentPO = new BankCommentPO();

        bankCommentPO.setId( vo.getId() );
        bankCommentPO.setUserId( vo.getUserId() );
        bankCommentPO.setEntityId( vo.getEntityId() );
        bankCommentPO.setEntityType( vo.getEntityType() );
        bankCommentPO.setCommentContent( vo.getCommentContent() );
        bankCommentPO.setCreateTime( vo.getCreateTime() );
        bankCommentPO.setUpdateTime( vo.getUpdateTime() );

        return bankCommentPO;
    }

    @Override
    public List<BankCommentVo> toRecords(List<BankCommentPO> pos) {
        if ( pos == null ) {
            return null;
        }

        List<BankCommentVo> list = new ArrayList<BankCommentVo>( pos.size() );
        for ( BankCommentPO bankCommentPO : pos ) {
            list.add( toVO( bankCommentPO ) );
        }

        return list;
    }
}
