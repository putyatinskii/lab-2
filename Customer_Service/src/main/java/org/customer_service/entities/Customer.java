package org.customer_service.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.util.DigestUtils;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer implements Cloneable {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "firstname", length = 20)
    private String firstname;
    @Column(name = "lastname", length = 20)
    private String lastname;
    @Column(name = "email", length = 30)
    private String email;
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    @Column(name = "password", length = 32)
    private String password;
    @Column(name = "phone", length = 12)
    private String phone;
    @ManyToOne
    @JoinColumn(name = "addressId")
    private Address address;

    @JsonProperty
    public void setPassword(String password) {
        this.password = DigestUtils.md5DigestAsHex(password.getBytes());
    }

    @Override
    public Customer clone() throws CloneNotSupportedException {
        Customer c = new Customer();
        c.setFirstname(this.firstname);
        c.setLastname(this.lastname);
        c.setEmail(this.email);
        c.setPhone(this.phone);
        c.setPassword(this.password);
        c.setAddress(this.address);
        return c;
    }
}
