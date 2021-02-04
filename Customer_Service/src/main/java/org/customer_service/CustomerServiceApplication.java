package org.customer_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CustomerServiceApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CustomerServiceApplication.class, args);

//        PaidType paidType = new PaidType("card");
//        paidTypeRepository.save(paidType);
//        Address address = addressRepository.findById(5).orElse(new Address());
//        address.setId(13);
//        addressRepository.save(address);
//        Customer customer = new Customer("Dmitry",
//                "Sidorov",
//                "123Dim123@mail.ru",
//                "qwerty",
//                "+79274539323");
//        customer.setAddress(address);
//        customerRepository.save(customer);

        //context.close();
    }
}
