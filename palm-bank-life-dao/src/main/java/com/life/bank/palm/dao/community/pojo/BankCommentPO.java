package com.life.bank.palm.dao.community.pojo;

import com.life.bank.palm.dao.user.pojo.UserPO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
* 
* @TableName bank_comment
*/
@Data
public class BankCommentPO implements Serializable {

    /**
    * 
    */
    private Integer id;
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
    /**
     * 子评论
     */
    private List<BankCommentPO> subList;

    public List<BankCommentPO> getSubList() {
        return subList;
    }

    public void setSubList(List<BankCommentPO> subList) {
        this.subList = subList;
    }

}
