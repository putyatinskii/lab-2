package org.offer_service.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "characteristics")
public class Characteristic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", length = 20)
    private String name;
    @Column(name = "description", length = 1000)
    private String description;

    public Characteristic(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
