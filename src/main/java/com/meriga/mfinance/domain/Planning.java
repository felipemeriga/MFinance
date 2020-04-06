package com.meriga.mfinance.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
public class Planning implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    private Long id;

    @NotNull
    private BigDecimal value;


    @Column(name = "date")
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Category category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planning planning = (Planning) o;
        return Objects.equals(id, planning.id) &&
            Objects.equals(value, planning.value) &&
            Objects.equals(date, planning.date) &&
            Objects.equals(category, planning.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, date, category);
    }

    @Override
    public String toString() {
        return "Planning{" +
            "id=" + id +
            ", value=" + value +
            ", date=" + date +
            ", category=" + category +
            '}';
    }
}
