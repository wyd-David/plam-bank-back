package com.life.bank.palm.dao.community.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName zan_data
 */
@Data
public class ZanDataPO implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 点赞用户ID
     */
    private Integer zanUserId;

    /**
     * 点赞实体ID
     */
    private Long zanEntityId;

    /**
     * 点赞类型
     */
    private String zanEntityType;

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
        ZanDataPO other = (ZanDataPO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getZanUserId() == null ? other.getZanUserId() == null : this.getZanUserId().equals(other.getZanUserId()))
            && (this.getZanEntityId() == null ? other.getZanEntityId() == null : this.getZanEntityId().equals(other.getZanEntityId()))
            && (this.getZanEntityType() == null ? other.getZanEntityType() == null : this.getZanEntityType().equals(other.getZanEntityType()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getZanUserId() == null) ? 0 : getZanUserId().hashCode());
        result = prime * result + ((getZanEntityId() == null) ? 0 : getZanEntityId().hashCode());
        result = prime * result + ((getZanEntityType() == null) ? 0 : getZanEntityType().hashCode());
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
        sb.append(", zanUserId=").append(zanUserId);
        sb.append(", zanEntityId=").append(zanEntityId);
        sb.append(", zanEntityType=").append(zanEntityType);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}