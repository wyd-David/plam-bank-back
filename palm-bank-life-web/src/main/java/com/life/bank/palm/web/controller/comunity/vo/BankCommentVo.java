package com.life.bank.palm.web.controller.comunity.vo;

import com.life.bank.palm.web.controller.user.vo.UserVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
* 
* @TableName bank_comment
*/
@Data
@ApiModel("社区评论实体")
public class BankCommentVo extends UserVO {

    /**
    * 
    */
    @ApiModelProperty("评论id")
    private Integer id;
    /**
    * 用户id
    */
    @ApiModelProperty("用户id")
    private Integer userId;
    /**
     * 用户信息
     */
    @ApiModelProperty("用户信息")
    private UserVO userVO;
    /**
    * 评论的主体ID
    */
    @ApiModelProperty("评论的主体ID")
    private Integer entityId;
    /**
    * 评论的类型
    */
    @ApiModelProperty("评论的类型")
    private Integer entityType;
    /**
    * 评论的内容
    */
    @ApiModelProperty("评论的内容")
    private String commentContent;
    /**
    * 创建时间
    */
    @ApiModelProperty("创建时间")
    private Date createTime;
    /**
    * 更新时间
    */
    @ApiModelProperty("更新时间")
    private Date updateTime;

    /**
     * 子评论
     */
    @ApiModelProperty("子评论")
    private List<BankCommentVo> subList;


    public UserVO getUserVo() {
        return userVO;
    }

    public void setUserVo(UserVO userVO) {
        this.userVO = userVO;
    }

}
