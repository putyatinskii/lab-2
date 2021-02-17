package org.offer_service.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    @Column(name = "category_id")
    @Getter(AccessLevel.NONE)
    private CategoryEnum category;
    @ManyToMany
    @JoinTable(name = "characteristics_offers",
            joinColumns = @JoinColumn(name = "offer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "characteristic_id", referencedColumnName = "id"))
    private Set<Characteristic> characteristics = new HashSet<>();

    public void setName(String name) {
        if (name.length() >= 4 && name.length() <= 20)
            this.name = name;
    }

    public void setPrice(double price) {
        if (price >= 0.1)
            this.price = price;
    }

    public void setPaidTypeId(int paidTypeId) {
        if (paidTypeId > 0)
            this.paidTypeId = paidTypeId;
    }

    public String getCategory() {
        return category.getName();
    }

    public void setCategory(String category) {
        this.category = CategoryEnum.valueOf(category.toUpperCase());
    }

    public void setCharacteristics(Set<Characteristic> characteristics) {
        this.characteristics = new HashSet<>(characteristics);
    }
}
