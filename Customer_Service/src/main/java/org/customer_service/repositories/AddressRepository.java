package org.customer_service.repositories;

import org.customer_service.entities.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface AddressRepository extends CrudRepository<Address, Integer> {
}
