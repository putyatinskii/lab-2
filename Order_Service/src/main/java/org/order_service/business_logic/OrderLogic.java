package org.order_service.business_logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.order_service.entities.Order;
import org.order_service.entities.Status;
import org.order_service.exceptions.IncorrectDataException;
import org.order_service.exceptions.NotFoundException;
import org.order_service.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderLogic implements BusinessLogic<Order> {
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
        if (order.getName() != null && order.getDeliveryTime() != null && order.getStatus() != null) {
            order1.setOfferId(order.getOfferId());
            order1.setName(order.getName());
            order1.setDeliveryTime(order.getDeliveryTime());
            order1.setStatus(order.getStatus());
            order1.setCustomerId(order.getCustomerId());
            order1.setPaid(order.isPaid());
            return orderRepository.save(order1);
        }
        else {
            throw new IncorrectDataException();
        }
    }

    @Override
    public void delete(Order order) {
        orderRepository.delete(order);
    }

    public Order changeStatus(Integer id, Order order) {
        Order orderFromDB = get(id);
        if (order.getStatus() != null) {
            orderFromDB.setStatus(order.getStatus());
            return orderRepository.save(orderFromDB);
        }
        else {
            throw new IncorrectDataException();
        }
    }
}
