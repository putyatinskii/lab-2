package org.order_service.business_logic;

import org.order_service.entities.Order;
import org.order_service.exceptions.NotFoundException;
import org.order_service.repositories.OrderRepository;
import org.order_service.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderLogic implements BusinessLogic<Order> {
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        orderRepository.findAll().forEach(orders::add);
        return orders;
    }

    @Override
    public Order get(Integer id) {
        Order order = orderRepository.findById(id).orElseThrow(NotFoundException::new);
        return order;
    }

    @Override
    public Order create(Order order) {
        return null;
    }

    @Override
    public Order update(Integer id, Order order) {
        return null;
    }

    @Override
    public void delete(Order order) {
        orderRepository.delete(order);
    }
}
