package org.offer_service.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "characteristics")
public class Characteristic {
    @Id
    @JsonIgnore
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", length = 20)
    private String name;
    @Column(name = "description", length = 1000)
    private String description;
    @ManyToMany(mappedBy = "characteristics")
    @JsonIgnore
    private Set<Offer> offerSet = new HashSet<>();

    public void setName(String name) {
        if (name.length() >= 4 && name.length() <= 20)
            this.name = name;
    }

    public void setDescription(String description) {
        if (description != null && description.length() >= 5 && description.length() <= 1000)
            this.description = description;
    }
}
