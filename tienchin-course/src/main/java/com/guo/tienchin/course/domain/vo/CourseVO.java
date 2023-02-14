package com.guo.tienchin.course.domain.vo;

public class CourseVO {
    private String name;
    private Integer type;
    private Integer applyTo;
    private Integer minPrice;
    private Integer maxPrice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getApplyTo() {
        return applyTo;
    }

    public void setApplyTo(Integer applyTo) {
        this.applyTo = applyTo;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }
}
