package com.life.bank.palm.web.controller.comunity.vo;

import com.life.bank.palm.web.controller.user.vo.UserVO;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author: 薯条哥搞offer
 * @createTime: 2024/06/04 16:19
 * @company: <a href="https://www.neituiya.top">内推鸭小程序</a>
 */
@Data
public class BankCommentVo {
    /**
     *
     */
    private Integer id;

    /**
     * 用户详情信息
     */
    private UserVO userVo;


    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 评论的主体ID
     */
    private Integer entityId;

    /**
     * 评论的类型
     */
    private Integer entityType;

    /**
     * 评论的内容
     */
    private String commentContent;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


    private List<BankCommentVo> subList;
}
