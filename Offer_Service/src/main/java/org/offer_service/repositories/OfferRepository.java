package org.offer_service.repositories;

import org.offer_service.entities.Offer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends CrudRepository<Offer, Integer> {
}
