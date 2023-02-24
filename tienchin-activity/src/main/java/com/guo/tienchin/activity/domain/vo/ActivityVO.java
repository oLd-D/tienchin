package com.guo.tienchin.activity.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.guo.tienchin.common.validator.AddGroup;
import com.guo.tienchin.common.validator.EditGroup;
import com.guo.tienchin.common.annotation.Excel;
import com.guo.tienchin.common.core.domain.BaseEntity;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

public class ActivityVO extends BaseEntity {
    @NotNull(message = "{activity.id.notNull}",groups = EditGroup.class)
    @Excel(name = "活动ID")
    private Integer activityId;

    /**
     * 活动名称
     */
    @NotBlank(message = "{activity.name.notBlank}",groups = {AddGroup.class,EditGroup.class})
    @Size(min = 0, max = 20,message = "{activity.name.size}",groups = {AddGroup.class,EditGroup.class})
    @Excel(name = "活动名称")
    private String name;

    /**
     * 渠道ID
     */
    @NotNull(message = "{activity.channelId.notNull}",groups = {AddGroup.class,EditGroup.class})
    @Excel(name = "渠道ID")
    private Integer channelId;
    @Excel(name = "渠道名称")
    private String channelName;

    /**
     * 活动简介
     */
    @NotBlank(message = "{activity.info.notNull}",groups = {AddGroup.class,EditGroup.class})
    @Size(min = 0, max = 255,message = "{activity.info.size}",groups = {AddGroup.class,EditGroup.class})
    @Excel(name = "活动简介")
    private String info;

    /**
     * 活动类型 1 折扣券 2 代金券
     */
    @NotNull(message = "{activity.type.notNull}",groups = {AddGroup.class,EditGroup.class})
    @Excel(name = "活动类型", readConverterExp = "1=折扣券,2=代金券")
    private Integer type;

    /**
     * 折扣券
     */
    @Max(value = 10,message = "{activity.discount.invalid}",groups = {AddGroup.class,EditGroup.class})
    @Min(value = 0,message = "{activity.discount.invalid}",groups = {AddGroup.class,EditGroup.class})
    @Excel(name = "活动折扣")
    private Double discount;

    /**
     * 代金券
     */
    @Min(value = 0,message = "{activity.voucher.invalid}",groups = {AddGroup.class,EditGroup.class})
    @Excel(name = "代金券面值")
    private Double voucher;

    /**
     * 活动状态 0 表示过期 1 表示有效
     */
    @Max(value = 1,message = "{activity.status.invalid}",groups = {AddGroup.class,EditGroup.class})
    @Min(value = 0,message = "{activity.status.invalid}",groups = {AddGroup.class,EditGroup.class})
    @Excel(name = "活动状态", readConverterExp = "0=过期,1=有效")
    private Integer status;

    /**
     * 活动开始时间
     */
    @NotNull(message = "{activity.beginTime.notNull}",groups = {AddGroup.class,EditGroup.class})
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    @Excel(name = "活动开始时间",dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime beginTime;

    /**
     * 活动结束时间
     */
    @NotNull(message = "{activity.endTime.notNull}",groups = {AddGroup.class,EditGroup.class})
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    @Excel(name = "活动结束时间",dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;



    private Integer delFlag;

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getVoucher() {
        return voucher;
    }

    public void setVoucher(Double voucher) {
        this.voucher = voucher;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalDateTime beginTime) {
        this.beginTime = beginTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}
