package org.offer_service.repositories;

import org.offer_service.entities.Characteristic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CharacteristicRepository extends CrudRepository<Characteristic, Integer> {
    boolean existsByName(String name);
    Optional<Characteristic> findByName(String name);
}
