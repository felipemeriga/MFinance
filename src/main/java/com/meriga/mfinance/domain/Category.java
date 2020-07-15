package com.meriga.mfinance.domain;

import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

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

    @Column(name = "updated_when")
    @UpdateTimestamp
    private Timestamp updatedWhen;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getUpdatedWhen() {
        return updatedWhen;
    }

    public void setUpdatedWhen(Timestamp updatedWhen) {
        this.updatedWhen = updatedWhen;
    }
}
