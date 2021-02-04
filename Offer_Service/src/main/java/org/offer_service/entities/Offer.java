package org.offer_service.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "offers")
public class Offer {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", length = 20)
    private String name;
    @Column(name = "price")
    private double price;
    @Column(name = "paid_type_id")
    private int paidTypeId;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
