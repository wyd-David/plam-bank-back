package com.life.bank.palm.web.controller.comunity.convertor;

import com.life.bank.palm.dao.community.pojo.BankCommentPO;
import com.life.bank.palm.dao.community.pojo.CommunityPostPO;
import com.life.bank.palm.dao.user.mapper.UserMapper;
import com.life.bank.palm.dao.user.pojo.UserPO;
import com.life.bank.palm.web.controller.comunity.vo.BankCommentVo;
import com.life.bank.palm.web.controller.comunity.vo.CommunityPostVo;
import com.life.bank.palm.web.controller.user.convertor.UserVOConvertor;
import com.life.bank.palm.web.controller.user.vo.UserVO;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class BankCommentVoConvertorImpl implements BankCommentVoConvertor{

    @Autowired
    private UserMapper userMapper;

    @Override
    public BankCommentVo toVO(BankCommentPO po) {
        if(po == null) return null;

        BankCommentVo vo = new BankCommentVo();
        if ( po.getId() != null ) {
            vo.setId(po.getId());
        }

        UserPO userPO = userMapper.selectOneByIdAndIsDelete(po.getUserId(), NumberUtils.INTEGER_ZERO);
        UserVO userVO = UserVOConvertor.INSTANCE.toVO(userPO);

        vo.setUserVo(userVO);
        vo.setUserId(po.getUserId());
        vo.setEntityId(po.getEntityId());
        vo.setEntityType(po.getEntityType());
        vo.setCommentContent(po.getCommentContent());
        vo.setCreateTime(po.getCreateTime());
        vo.setUpdateTime(po.getUpdateTime());
        //vo.setSubList(po.getSubList());


        return null;
    }

    @Override
    public BankCommentPO toPO(BankCommentVo vo) {
        return null;
    }

    @Override
    public List<BankCommentVo> toRecords(List<BankCommentPO> pos) {
        if ( pos == null ) {
            return null;
        }

        List<BankCommentVo> list = new ArrayList<BankCommentVo>( pos.size() );
        for ( BankCommentPO po : pos ) {
            list.add( toVO( po ) );
        }

        return list;
    }
}
