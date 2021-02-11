package org.offer_service.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "offers")
public class Offer {
    @Id
    @JsonIgnore
    @Setter(AccessLevel.NONE)
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

    public void setName(String name) {
        if (name.length() >= 4 && name.length() <= 20)
            this.name = name;
    }

    public void setPrice(double price) {
        if (price >= 0.1)
            this.price = price;
    }

    public void setPaidTypeId(int paidTypeId) {
        this.paidTypeId = paidTypeId;
    }

    public void setCategory(Category category) {
        try {
            this.category = category.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
