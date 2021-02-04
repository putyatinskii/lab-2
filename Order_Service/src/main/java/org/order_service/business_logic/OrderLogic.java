package org.order_service.business_logic;

import org.order_service.entities.Order;
import org.order_service.exceptions.IncorrectDataException;
import org.order_service.exceptions.NotFoundException;
import org.order_service.exceptions.UnknownStatusException;
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
        order.setStatus(statusRepository.save(order.getStatus()));
        try {
            return orderRepository.save(order);
        }
        catch (Exception ex) {
            throw new IncorrectDataException();
        }
    }

    @Override
    public Order update(Integer id, Order order) {
        Order order1 = orderRepository.findById(id).orElseThrow(NotFoundException::new);
        order1.setOfferId(order.getOfferId());
        order1.setName(order.getName());
        order1.setDeliveryTime(order.getDeliveryTime());
        order1.setCustomerId(order.getCustomerId());
        order1.setPaid(order.isPaid());
        try {
        return orderRepository.save(order1);
        }
        catch (Exception ex) {
            throw new IncorrectDataException();
        }
    }

    @Override
    public void delete(Order order) {
        orderRepository.delete(order);
    }
}
