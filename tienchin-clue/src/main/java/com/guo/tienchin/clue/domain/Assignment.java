package com.guo.tienchin.clue.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author guo
 * @since 2023-02-14
 */
@TableName("tienchin_assignment")
public class Assignment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分配任务ID
     */
    @TableId(value = "aid", type = IdType.AUTO)
    private Integer aid;

    /**
     * 1 线索 2 商机
     */
    private Integer type;

    /**
     * 线索或商机的ID
     */
    private Integer assignId;

    /**
     * 被分配人ID
     */
    private Long userId;

    /**
     * 被分配人用户名
     */
    private String userName;

    /**
     * 被分配人部门ID
     */
    private Long deptId;

    /**
     * 是否是当前最新被分配人
     */
    private Boolean latest;

    private LocalDateTime createTime;

    private String createBy;

    private LocalDateTime updateTime;

    private String updateBy;

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAssignId() {
        return assignId;
    }

    public void setAssignId(Integer assignId) {
        this.assignId = assignId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Boolean getLatest() {
        return latest;
    }

    public void setLatest(Boolean latest) {
        this.latest = latest;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Override
    public String toString() {
        return "Assignment{" +
            "aid = " + aid +
            ", type = " + type +
            ", assignId = " + assignId +
            ", userId = " + userId +
            ", userName = " + userName +
            ", deptId = " + deptId +
            ", latest = " + latest +
            ", createTime = " + createTime +
            ", createBy = " + createBy +
            ", updateTime = " + updateTime +
            ", updateBy = " + updateBy +
        "}";
    }
}
