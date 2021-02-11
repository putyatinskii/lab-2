package org.customer_service.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.util.DigestUtils;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
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
    @ManyToMany
    @JoinTable(name = "customers_paid_types",
    joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "paid_type_id", referencedColumnName = "id"))
    private Set<PaidType> paidTypes = new HashSet<>();

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setFirstname(String firstname) {
        if (firstname.length() >= 3 && firstname.length() <= 20)
            this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        if (lastname.length() >= 3 && lastname.length() <= 20)
            this.lastname = lastname;
    }

    public void setEmail(String email) {
        if (email.length() >= 10 && email.length() <= 30 && email.matches("\\w+@\\w+\\.\\w+"))
            this.email = email;
    }

    public void setPhone(String phone) {
        if (phone.length() == 12 && phone.matches("\\+\\d{11}"))
            this.phone = phone;
    }

    @JsonProperty
    public void setPassword(String password) {
        if (password.length() > 5 && password.length() <= 20)
            this.password = DigestUtils.md5DigestAsHex(password.getBytes());
    }

    public void setAddress(Address address) {
        try {
            this.address = address.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public void setPaidType(PaidType paidType) {
        paidTypes.add(paidType);
    }

}
