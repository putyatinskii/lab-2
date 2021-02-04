package org.customer_service.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "addresses")
public class Address implements Cloneable {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "city", length = 20)
    private String city;
    @Column(name = "state", length = 40)
    private String state;
    @Column(name = "country", length = 20)
    private String country;

    @Override
    public Address clone() throws CloneNotSupportedException {
        Address address = new Address();
        address.setCity(this.getCity());
        address.setState(this.getState());
        address.setCountry(this.getCountry());
        return address;
    }
}
