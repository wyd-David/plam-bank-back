package com.life.bank.palm.dao.community.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName community_post
 */
@Data
public class CommunityPostPO implements Serializable {
    /**
     * 使用雪花算法
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 富文本内容
     */
    private String richContent;

    /**
     * 发布di
     */
    private Integer userId;

    /**
     * 赞的数量
     */
    private String zanCnt;

    /**
     * 收藏数量
     */
    private String collectCnt;

    /**
     * 浏览数量
     */
    private String viewCnt;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CommunityPostPO other = (CommunityPostPO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getRichContent() == null ? other.getRichContent() == null : this.getRichContent().equals(other.getRichContent()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getZanCnt() == null ? other.getZanCnt() == null : this.getZanCnt().equals(other.getZanCnt()))
            && (this.getCollectCnt() == null ? other.getCollectCnt() == null : this.getCollectCnt().equals(other.getCollectCnt()))
            && (this.getViewCnt() == null ? other.getViewCnt() == null : this.getViewCnt().equals(other.getViewCnt()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getRichContent() == null) ? 0 : getRichContent().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getZanCnt() == null) ? 0 : getZanCnt().hashCode());
        result = prime * result + ((getCollectCnt() == null) ? 0 : getCollectCnt().hashCode());
        result = prime * result + ((getViewCnt() == null) ? 0 : getViewCnt().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", richContent=").append(richContent);
        sb.append(", userId=").append(userId);
        sb.append(", zanCnt=").append(zanCnt);
        sb.append(", collectCnt=").append(collectCnt);
        sb.append(", viewCnt=").append(viewCnt);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}