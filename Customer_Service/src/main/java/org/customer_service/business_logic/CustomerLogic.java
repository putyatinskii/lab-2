package org.customer_service.business_logic;

import lombok.RequiredArgsConstructor;
import org.customer_service.entities.Address;
import org.customer_service.entities.Customer;
import org.customer_service.entities.PaidType;
import org.customer_service.exceptions.NotFoundException;
import org.customer_service.exceptions.DataErrorException;
import org.customer_service.repositories.AddressRepository;
import org.customer_service.repositories.CustomerRepository;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CustomerLogic implements BusinessLogicMethods<Customer>{
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private PaidTypeLogic paidTypeLogic;

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);
        return customers;
    }

    @Override
    public Customer get(Integer id) {
        return customerRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Customer create(Customer customer) {
        if (customer.getPhone() != null &&
                customer.getEmail() != null &&
                customer.getFirstname() != null &&
                customer.getLastname() != null &&
                customer.getPassword() != null) {
            if (!customerRepository.existsByPhone(customer.getPhone()) &&
                    !customerRepository.existsByEmail(customer.getEmail()) &&
                    customer.getAddress().getCity() != null &&
                    customer.getAddress().getState() != null &&
                    customer.getAddress().getCountry() != null) {
                addressRepository.save(customer.getAddress());
                return customerRepository.save(customer);
            }
        }
        throw new DataErrorException();
    }

    @Override
    public Customer update(Integer id, Customer customer) {
        if (customer.getPhone() != null &&
                customer.getEmail() != null &&
                customer.getFirstname() != null &&
                customer.getLastname() != null &&
                customer.getPassword() != null) {
            if (customer.getAddress().getCity() != null &&
                    customer.getAddress().getState() != null &&
                    customer.getAddress().getCountry() != null) {
                Customer customerFromDB = get(id);
                if (!customerFromDB.getPhone().equals(customer.getPhone()))
                        if (customerRepository.existsByPhone(customer.getPhone())) {
                            throw new DataErrorException();
                }
                else if (!customerFromDB.getEmail().equals(customer.getEmail()))
                        if (customerRepository.existsByEmail(customer.getEmail())) {
                            throw new DataErrorException();
                }
                customerFromDB.getAddress().setCity(customer.getAddress().getCity());
                customerFromDB.getAddress().setState(customer.getAddress().getState());
                customerFromDB.getAddress().setCountry(customer.getAddress().getCountry());

                addressRepository.save(customerFromDB.getAddress());
                customerFromDB.setPhone(customer.getPhone());
                customerFromDB.setEmail(customer.getEmail());
                customerFromDB.setFirstname(customer.getFirstname());
                customerFromDB.setLastname(customer.getLastname());
                try {
                    Field field = customerFromDB.getClass().getDeclaredField("password");
                    field.setAccessible(true);
                    field.set(customerFromDB, customer.getPassword());
                    field.setAccessible(false);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                return customerRepository.save(customerFromDB);
            }
        }
        throw new DataErrorException();
    }

    @Override
    public void delete(Customer customer) {
        addressRepository.delete(customer.getAddress());
    }

    public void addPaidType(Integer id, Integer paidTypeId) {
        Customer customer = get(id);
        customer.setPaidType(paidTypeLogic.get(paidTypeId));
        customerRepository.save(customer);
    }

    public Set<PaidType> getPaidTypes(Integer id) {
        Customer customer = get(id);
        return customer.getPaidTypes();
    }
}
