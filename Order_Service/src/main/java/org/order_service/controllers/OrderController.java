package org.order_service.controllers;

import org.order_service.business_logic.OrderLogic;
import org.order_service.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order_service")
public class OrderController {
    @Autowired
    private OrderLogic orderLogic;

    @GetMapping(value = "/listOfOrders")
    public List<Order> getAllOrders() {
        return orderLogic.getAll();
    }

    @GetMapping(value = "/id={id}")
    public Order getOrder(@PathVariable Integer id) {
        return orderLogic.get(id);
    }

    @PostMapping(value = "/new")
    public Order createOrder(@RequestBody Order order) {
        return orderLogic.create(order);
    }

    @PutMapping(value = "/id={id}")
    public Order updateOrder(@PathVariable Integer id, @RequestBody Order order) {
        return orderLogic.update(id, order);
    }

    @DeleteMapping(value = "/id={id}")
    public void deleteOrder(@PathVariable Integer id) {
        orderLogic.delete(orderLogic.get(id));
    }
}
