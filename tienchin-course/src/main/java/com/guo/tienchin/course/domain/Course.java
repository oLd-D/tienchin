package com.guo.tienchin.course.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.guo.tienchin.common.annotation.Excel;
import com.guo.tienchin.common.validator.AddGroup;
import com.guo.tienchin.common.validator.EditGroup;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author guo
 * @since 2023-02-11
 */
@TableName("tienchin_course")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "course_id", type = IdType.AUTO)
    @NotNull(message = "{course.id.notnull}",groups = {EditGroup.class})
    @Excel(name = "课程编号")
    private Integer courseId;

    /**
     * 课程类型 1 舞蹈类 2 游泳类 3 拳击类
     */
    @NotNull(message = "{course.type.notnull}",groups = {AddGroup.class,EditGroup.class})
    @Excel(name = "课程类型", readConverterExp = "1=舞蹈类,2=游泳类,3=拳击类")
    private Integer type;

    /**
     * 课程名
     */
    @NotBlank(message = "{course.name.notblank}",groups = {AddGroup.class,EditGroup.class})
    @Excel(name = "课程名称")
    private String name;

    /**
     * 课程价格
     */
    @NotNull(message = "{course.price.notnull}",groups = {AddGroup.class,EditGroup.class})
    @Min(value = 0, message = "{course.price.invalid}",groups = {AddGroup.class,EditGroup.class})
    @Excel(name = "课程价格")
    private Double price;

    /**
     * 课程适用人群
     */
    @NotNull(message = "{course.applyTo.notnull}",groups = {AddGroup.class,EditGroup.class})
    @Excel(name = "适用人群", readConverterExp = "1=零基础,2=简单,3=中等,4=困难")
    private Integer applyTo;

    /**
     * 课程简介
     */
    @NotBlank(message = "{course.info.notblank}",groups = {AddGroup.class,EditGroup.class})
    @Excel(name = "课程简介")
    private String info;


    @Excel(name = "创建时间")
    private LocalDateTime createTime;

    private String createBy;

    private LocalDateTime updateTime;

    private String updateBy;

    private Integer delFlag;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getApplyTo() {
        return applyTo;
    }

    public void setApplyTo(Integer applyTo) {
        this.applyTo = applyTo;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId = " + courseId +
                ", type = " + type +
                ", name = " + name +
                ", price = " + price +
                ", applyTo = " + applyTo +
                ", info = " + info +
                ", createTime = " + createTime +
                ", createBy = " + createBy +
                ", updateTime = " + updateTime +
                ", updateBy = " + updateBy +
                ", delFlag = " + delFlag +
                "}";
    }
}

