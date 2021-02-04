package org.offer_service.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "offers")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", length = 20)
    private String name;
    private double price;
    private int paidTypeId;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Offer(String name, double price, int paidTypeId) {
        this.name = name;
        this.price = price;
        this.paidTypeId = paidTypeId;
    }
}
