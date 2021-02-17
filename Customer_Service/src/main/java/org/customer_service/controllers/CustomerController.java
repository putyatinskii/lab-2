package org.customer_service.controllers;

import org.customer_service.business_logic.CustomerLogic;
import org.customer_service.business_logic.PaidTypeLogic;
import org.customer_service.entities.Customer;
import org.customer_service.entities.PaidType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("customer_service/customer")
public class CustomerController {
    @Autowired
    private CustomerLogic customerLogic;

    @GetMapping(value = "/listOfCustomers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerLogic.getAll());
    }

    @GetMapping(value = "/id={id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Integer id) {
        return ResponseEntity.ok(customerLogic.get(id));
    }

    @PostMapping(value = "/new")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerLogic.create(customer));
    }

    @PutMapping(value = "/id={id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
            return ResponseEntity.ok(customerLogic.update(id, customer));
    }

    @DeleteMapping(value = "/id={id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer id) {
        customerLogic.delete(customerLogic.get(id));
        return ResponseEntity.ok("Customer was removed successfully");
    }

    @PutMapping(value = "/id={id}/paidTypeId={paidTypeId}")
    public ResponseEntity<String> addPaidType(@PathVariable Integer id, @PathVariable Integer paidTypeId) {
        customerLogic.addPaidType(id, paidTypeId);
        return ResponseEntity.ok("PaidType was added successfully");
    }

    @GetMapping(value = "/id={id}/getPaidTypes")
    public ResponseEntity<Set<PaidType>> getSetOfPaidTypes(@PathVariable Integer id) {
        return ResponseEntity.ok(customerLogic.getPaidTypes(id));
    }
}
