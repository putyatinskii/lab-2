package org.order_service.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders", schema = "public")
public class Order {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "offer_id")
    private int offerId;
    @Column(name = "name", length = 20)
    private String name;
    @Column(name = "delivery_time")
    private LocalDateTime deliveryTime;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;
    @Column(name = "customer_id")
    private int customerId;
    @Column(name = "paid")
    private boolean paid;


}
