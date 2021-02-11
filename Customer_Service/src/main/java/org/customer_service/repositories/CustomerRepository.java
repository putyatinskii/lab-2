package org.customer_service.repositories;

import org.customer_service.entities.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    boolean existsByPhone(String phone);
    //boolean existsByPhoneAndById(String phone);
    boolean existsByEmail(String email);
}
