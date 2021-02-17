package org.order_service.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

public enum Status {
    ORDER_PROCESSING("Order_processing"),
    ORDER_DELIVERY("Order_delivery"),
    ORDER_DELIVERED("Order_delivered");

    private String name;

    Status(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
