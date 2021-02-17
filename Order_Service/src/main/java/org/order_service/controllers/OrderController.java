package org.order_service.controllers;

import com.github.fge.jsonpatch.JsonPatch;
import org.order_service.business_logic.OrderLogic;
import org.order_service.entities.Order;
import org.order_service.entities.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order_service")
public class OrderController {
    @Autowired
    private OrderLogic orderLogic;

    @GetMapping(value = "/listOfOrders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderLogic.getAll());
    }

    @GetMapping(value = "/id={id}")
    public ResponseEntity<Order> getOrder(@PathVariable Integer id) {
        return ResponseEntity.ok(orderLogic.get(id));
    }

    @PostMapping(value = "/new")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderLogic.create(order));
    }

    @PutMapping(value = "/id={id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Integer id, @RequestBody Order order) {
        return ResponseEntity.ok(orderLogic.update(id, order));
    }

    @DeleteMapping(value = "/id={id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Integer id) {
        orderLogic.delete(orderLogic.get(id));
        return ResponseEntity.ok("Order was removed successfully");
    }

    @PatchMapping(value = "/id={id}", consumes = "application/json-patch+json")
    public ResponseEntity<Order> changeStatus(@PathVariable Integer id, @RequestBody JsonPatch patch) {
        return ResponseEntity.ok(orderLogic.changeStatus(id, patch));
    }
}
