package org.customer_service.repositories;

import org.customer_service.entities.PaidType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface PaidTypeRepository extends CrudRepository<PaidType, Integer> {
}
