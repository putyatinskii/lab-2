package org.customer_service.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCity(String city) {
        if (city.length() >= 3 && city.length() <= 20)
            this.city = city;
    }

    public void setState(String state) {
        if (state.length() >= 4 && state.length() <= 40)
            this.state = state;
    }

    public void setCountry(String country) {
        if (country.length() >= 4 && country.length() <= 20)
            this.country = country;
    }

    @Override
    public Address clone() throws CloneNotSupportedException {
        Address address = new Address();
        address.setCity(this.getCity());
        address.setState(this.getState());
        address.setCountry(this.getCountry());
        return address;
    }
}
