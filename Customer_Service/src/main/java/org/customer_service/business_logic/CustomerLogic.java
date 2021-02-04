package org.customer_service.business_logic;

import org.customer_service.entities.Address;
import org.customer_service.entities.Customer;
import org.customer_service.exceptions.NotFoundException;
import org.customer_service.exceptions.PutException;
import org.customer_service.repositories.AddressRepository;
import org.customer_service.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerLogic implements BusinessLogicMethods<Customer>{
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);
        return customers;
    }

    @Override
    public Customer get(Integer id) {
        Customer customer = customerRepository.findById(id).orElseThrow(NotFoundException::new);
        return customer;
    }

    @Override
    public Customer create(Customer customer) {
        if (customer.getPhone().matches("\\+\\d{11}") && customer.getEmail().matches("\\w+@\\w+\\.\\w+")) {
            if (!customerRepository.existsByPhone(customer.getPhone()) &&
                    !customerRepository.existsByEmail(customer.getEmail()) &&
                    customer.getFirstname().length() <=20 &&
                    customer.getLastname().length() <= 20)
                addressRepository.save(customer.getAddress());
        }
        if (customer.getAddress().getId() != null)
            return customerRepository.save(customer);
        else {
            throw new PutException();
        }
    }

    @Override
    public Customer update(Integer id, Customer customer) {
        Customer customerFromDB = get(id);
        Address addressThisCustomer = customerFromDB.getAddress();
        Customer customer1 = new Customer();
        if (customer.getPhone().matches("\\+\\d{11}") && customer.getEmail().matches("\\w+@\\w+\\.\\w+")) {
            if (customer.getFirstname().length() <= 20 && customer.getLastname().length() <= 20) {
                customer.getAddress().setId(addressThisCustomer.getId());
                addressRepository.save(customer.getAddress());
                try {
                    customer1 = customer.clone();
                    customer1.setId(customerFromDB.getId());
                }
                catch (CloneNotSupportedException ex) {

                }
            }
        }
        return customerRepository.save(customer1);
    }

    @Override
    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }
}
