package org.customer_service.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.order_service.entities.Order;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "paidType")
public class PaidType implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", length = 20)
    private String name;

    @Override
    public PaidType clone() throws CloneNotSupportedException {
        PaidType paidType = new PaidType();
        paidType.setName(this.getName());
        return paidType;
    }
}
