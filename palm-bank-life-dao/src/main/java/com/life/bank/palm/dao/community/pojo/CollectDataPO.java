package com.life.bank.palm.dao.community.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName collect_data
 */
@Data
public class CollectDataPO implements Serializable {
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 收藏用户ID
     */
    private Integer collectUserId;

    /**
     * 收藏实体ID
     */
    private Long collectEntityId;

    /**
     * 收藏实体类型
     */
    private String collectEntityType;

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
        CollectDataPO other = (CollectDataPO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCollectUserId() == null ? other.getCollectUserId() == null : this.getCollectUserId().equals(other.getCollectUserId()))
            && (this.getCollectEntityId() == null ? other.getCollectEntityId() == null : this.getCollectEntityId().equals(other.getCollectEntityId()))
            && (this.getCollectEntityType() == null ? other.getCollectEntityType() == null : this.getCollectEntityType().equals(other.getCollectEntityType()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCollectUserId() == null) ? 0 : getCollectUserId().hashCode());
        result = prime * result + ((getCollectEntityId() == null) ? 0 : getCollectEntityId().hashCode());
        result = prime * result + ((getCollectEntityType() == null) ? 0 : getCollectEntityType().hashCode());
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
        sb.append(", collectUserId=").append(collectUserId);
        sb.append(", collectEntityId=").append(collectEntityId);
        sb.append(", collectEntityType=").append(collectEntityType);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}