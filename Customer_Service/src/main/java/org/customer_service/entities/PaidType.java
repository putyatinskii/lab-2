package org.customer_service.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "paidType")
public class PaidType implements Cloneable {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", length = 20)
    private String name;
    @JsonIgnore
    @ManyToMany(mappedBy = "paidTypes")
    private Set<Customer> customerSet = new HashSet<>();

    public void setName(String name) {
        if (name.length() >= 3 && name.length() <= 20)
            this.name = name;
    }
}
