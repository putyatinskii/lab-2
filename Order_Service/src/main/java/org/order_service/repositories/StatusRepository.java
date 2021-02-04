package org.order_service.repositories;

import org.order_service.entities.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends CrudRepository<Status, Integer> {

}
