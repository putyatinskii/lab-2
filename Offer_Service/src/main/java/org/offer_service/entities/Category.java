package org.offer_service.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category implements Cloneable {
    @Id
    @Getter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Setter(AccessLevel.NONE)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "name", length = 20)
    private String name;

    @JsonIgnore
    public void setName(String name) {
        if (name != null && name.length() >= 4 && name.length() <= 20)
            this.name = name;
    }

    @Override
    public Category clone() throws CloneNotSupportedException {
        Category category = new Category();
        category.setName(this.name);
        return category;
    }

    @JsonIgnore
    public int getId() {
        return id;
    }
}
