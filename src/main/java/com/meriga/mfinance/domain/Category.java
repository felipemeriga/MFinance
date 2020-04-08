package com.meriga.mfinance.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;

/**
 * A category.
 */
@Entity
@Table(name = "category")
public class Category extends AbstractEntity<Long> {

    @NotNull
    @Size(max = 50)
    @Column(length = 50)
    private String name;

    @Column(name = "created_when")
    private Date createdWhen;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedWhen() {
        return createdWhen;
    }

    public void setCreatedWhen(Date createdWhen) {
        this.createdWhen = createdWhen;
    }
}
