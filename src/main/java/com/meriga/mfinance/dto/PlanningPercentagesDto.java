package com.meriga.mfinance.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PlanningPercentagesDto {

    private Long id;
    private String categoryName;
    private BigDecimal plannedValue;
    private BigDecimal plannedPercentageReached;

    public PlanningPercentagesDto(Long id, String categoryName, BigDecimal plannedValue, BigDecimal plannedPercentageReached) {
        this.id = id;
        this.categoryName = categoryName;
        this.plannedValue = plannedValue;
        this.plannedPercentageReached = plannedPercentageReached;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public BigDecimal getPlannedValue() {
        return plannedValue;
    }

    public void setPlannedValue(BigDecimal plannedValue) {
        this.plannedValue = plannedValue;
    }

    public BigDecimal getPlannedPercentageReached() {
        return plannedPercentageReached;
    }

    public void setPlannedPercentageReached(BigDecimal plannedPercentageReached) {
        this.plannedPercentageReached = plannedPercentageReached;
    }
}
