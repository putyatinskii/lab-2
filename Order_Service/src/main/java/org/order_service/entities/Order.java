package org.order_service.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "offer_id")
    private int offerId;
    @Column(name = "name", length = 20)
    private String name;
    @Setter(AccessLevel.NONE)
    @Column(name = "delivery_time")
    private LocalDateTime deliveryTime;
    @ManyToOne
    @JoinColumn(name = "status_id")
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
}
