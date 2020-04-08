package com.meriga.mfinance.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

/**
 * A planning.
 */
@Entity
@Table(name = "planning")
public class Planning extends AbstractEntity<Long> {

    @NotNull
    private BigDecimal value;


    @Column(name = "date")
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;



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


}
