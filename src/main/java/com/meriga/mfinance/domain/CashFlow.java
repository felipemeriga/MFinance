package com.meriga.mfinance.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Date;


/**
 * A inbound or outbound flux.
 */
@Entity
@Table(name = "cash_flow")
public class CashFlow extends AbstractEntity<Long> {

    private BigDecimal value;

    @Column(name = "date")
    @NotNull
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    private Category category;


    @NotNull
    @Size(max = 50)
    @Column(length = 50)
    private String name;

    @NotNull
    @Size(max = 10)
    @Column(length = 10)
    private String flow;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
        this.flow = flow;
    }
}
