package org.customer_service.controllers;

import org.customer_service.business_logic.CustomerLogic;
import org.customer_service.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer_service/customer")
public class CustomerController {
    @Autowired
    private CustomerLogic customerLogic;

    @GetMapping(value = "/listOfCustomers")
    public List<Customer> getAllCustomers() {
        return customerLogic.getAll();
    }

    @GetMapping(value = "/id={id}")
    public Customer getCustomer(@PathVariable Integer id) {
        return customerLogic.get(id);
    }

    @PostMapping(value = "/new")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerLogic.create(customer);
    }

    @PutMapping(value = "/id={id}")
    public Customer updateCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
            return customerLogic.update(id, customer);
    }

    @DeleteMapping(value = "/id={id}")
    public void deleteCustomer(@PathVariable Integer id) {
        customerLogic.delete(customerLogic.get(id));
    }
}
