package org.order_service.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @JsonIgnore
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "offer_id")
    private int offerId;
    @Column(name = "name", length = 20)
    @Setter(AccessLevel.NONE)
    private String name;
    @Setter(AccessLevel.NONE)
    @Column(name = "delivery_time")
    private LocalDateTime deliveryTime;
    @Column(name = "status_id")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Status status;
    @Column(name = "customer_id")
    private int customerId;
    @Column(name = "paid")
    private boolean paid;

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = LocalDateTime.parse(deliveryTime);
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getStatus() {
        return status.getName();
    }

    public void setStatus(String status) {
        this.status = Status.valueOf(status.toUpperCase());
    }

    public void setName(String name) {
        if (name.length() >= 3 && name.length() <= 20)
            this.name = name;
    }
}
