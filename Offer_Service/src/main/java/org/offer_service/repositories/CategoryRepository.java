package org.offer_service.repositories;

import org.offer_service.entities.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
    boolean existsByName(String name);
}
