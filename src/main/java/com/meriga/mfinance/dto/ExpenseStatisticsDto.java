package com.meriga.mfinance.dto;


import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class ExpenseStatisticsDto {

    private Long id;
    private Long categoryId;
    private String categoryName;
    private Date date;
    private BigDecimal plannedValue;
    private BigDecimal amountSpent;
    private BigDecimal remainingValue;

    public ExpenseStatisticsDto(Long id, Long categoryId, String categoryName, Date date, BigDecimal plannedValue, BigDecimal amountSpent, BigDecimal remainingValue) {
        this.id = id;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.date = date;
        this.plannedValue = plannedValue;
        this.amountSpent = amountSpent;
        this.remainingValue = remainingValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getPlannedValue() {
        return plannedValue;
    }

    public void setPlannedValue(BigDecimal plannedValue) {
        this.plannedValue = plannedValue;
    }

    public BigDecimal getAmountSpent() {
        return amountSpent;
    }

    public void setAmountSpent(BigDecimal amountSpent) {
        this.amountSpent = amountSpent;
    }

    public BigDecimal getRemainingValue() {
        return remainingValue;
    }

    public void setRemainingValue(BigDecimal remainingValue) {
        this.remainingValue = remainingValue;
    }
}
