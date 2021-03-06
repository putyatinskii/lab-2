package org.order_service;

import org.order_service.business_logic.OrderLogic;
import org.order_service.entities.Status;
import org.order_service.repositories.OrderRepository;
import org.order_service.repositories.StatusRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class OrderServiceApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(OrderServiceApplication.class);

        //        Status status = new Status();
//        status.setName("stat2");
//        statusRepository.save(status);
//        //orderRepository.existsById(1);
//        context.close();
    }
}
