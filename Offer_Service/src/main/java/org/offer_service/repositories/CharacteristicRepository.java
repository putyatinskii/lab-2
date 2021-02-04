package org.offer_service.repositories;

import org.offer_service.entities.Characteristic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacteristicRepository extends CrudRepository<Characteristic, Integer> {
}
