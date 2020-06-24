package com.meriga.mfinance.dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class AverageExpenses {

    private Long id;
    private String name;
    private BigDecimal average;
    private int numberOfMonths;

    public AverageExpenses(Long id, String name, BigDecimal average, int numberOfMonths) {
        this.id = id;
        this.name = name;
        this.average = average;
        this.numberOfMonths = numberOfMonths;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAverage() {
        return average;
    }

    public void setAverage(BigDecimal average) {
        this.average = average;
    }

    public int getNumberOfMonths() {
        return numberOfMonths;
    }

    public void setNumberOfMonths(int numberOfMonths) {
        this.numberOfMonths = numberOfMonths;
    }
}
