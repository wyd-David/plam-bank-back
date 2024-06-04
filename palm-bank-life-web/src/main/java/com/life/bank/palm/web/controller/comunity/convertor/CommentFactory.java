package com.life.bank.palm.web.controller.comunity.convertor;

import com.life.bank.palm.dao.community.pojo.BankCommentPO;
import com.life.bank.palm.dao.user.mapper.UserMapper;
import com.life.bank.palm.dao.user.pojo.UserPO;
import com.life.bank.palm.service.community.BankCommentService;
import com.life.bank.palm.web.controller.comunity.vo.BankCommentVo;
import com.life.bank.palm.web.controller.user.convertor.UserVOConvertor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: 薯条哥搞offer
 * @createTime: 2024/06/04 16:24
 * @company: <a href="https://www.neituiya.top">内推鸭小程序</a>
 */
@Service
public class CommentFactory {
    @Autowired
    private BankCommentService bankCommentService;
    @Autowired
    private UserMapper userMapper;


    /**
     * 展示评论组件
     * @param entityId
     * @param entityType
     * @return
     */
    public List<BankCommentVo> buildCommentVoList(Integer entityId, Integer entityType) {
        ArrayList<Integer> userIds = new ArrayList<>();
        // entity_id = 11000 and entity_type = 1
        List<BankCommentPO> bankCommentPOS = bankCommentService.selectByEntityIdAndEntityType(entityId, entityType);
        bankCommentPOS.forEach(bankCommentPO -> userIds.add(bankCommentPO.getUserId()));
        // entity_id = 11000 and entity_type = 2
        List<Integer> entityIds = bankCommentPOS.stream().map(BankCommentPO::getId).collect(Collectors.toList());
        List<BankCommentPO> bankCommentPOS1 = bankCommentService.selectByEntityTypeAndEntityIdIn(2, entityIds);
        bankCommentPOS1.forEach(bankCommentPO -> userIds.add(bankCommentPO.getUserId()));
        Map<Integer, List<BankCommentPO>> commentMap = new HashMap<>();
        for (BankCommentPO bankCommentPO : bankCommentPOS1) {
            List<BankCommentPO> bankCommentPOS2 = commentMap.get(bankCommentPO.getEntityId());
            if (CollectionUtils.isEmpty(bankCommentPOS2)) {
                List<BankCommentPO> newOne = new ArrayList<>();
                newOne.add(bankCommentPO);
                commentMap.put(bankCommentPO.getEntityId(), newOne);
            } else {
                bankCommentPOS2.add(bankCommentPO);
            }
        }
        bankCommentPOS1.forEach(bankCommentPO -> userIds.add(bankCommentPO.getUserId()));
        List<UserPO> userPOS = userMapper.selectByIdIn(userIds);
        Map<Integer, UserPO> userPOMap = new HashMap<>();
        userPOS.forEach(userPO -> userPOMap.put(userPO.getId(), userPO));
        List<BankCommentVo> records = BankCommentVoConvertor.INSTANCE.toRecords(bankCommentPOS);
        for (BankCommentVo record : records) {
            record.setUserVo(UserVOConvertor.INSTANCE.toVO(userPOMap.get(record.getUserId())));
            Integer commentId = record.getId();
            List<BankCommentPO> bankCommentPOS2 = commentMap.get(commentId);
            if (CollectionUtils.isNotEmpty(bankCommentPOS2)){
                List<BankCommentVo> records1 = BankCommentVoConvertor.INSTANCE.toRecords(bankCommentPOS2);
                for (BankCommentVo bankCommentVo : records1) {
                    bankCommentVo.setUserVo(UserVOConvertor.INSTANCE.toVO(userPOMap.get(bankCommentVo.getUserId())));
                }
                record.setSubList(records1);
            }

        }
        return records;
    }
}
