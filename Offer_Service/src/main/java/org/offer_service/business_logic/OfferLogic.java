package org.offer_service.business_logic;

import org.offer_service.entities.Category;
import org.offer_service.entities.Offer;
import org.offer_service.exception.NotFoundException;
import org.offer_service.repositories.CategoryRepository;
import org.offer_service.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfferLogic implements BusinessLogic<Offer> {
    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Offer> getAll() {
        List<Offer> offers = new ArrayList<>();
        offerRepository.findAll().forEach(offers::add);
        return offers;
    }

    @Override
    public Offer get(Integer id) {
        Offer offer = offerRepository.findById(id).orElseThrow(NotFoundException::new);
        return offer;
    }

    @Override
    public Offer create(Offer offer) {
        return null;
    }

    @Override
    public Offer update(Integer id, Offer offer) {
        return null;
    }

    @Override
    public void delete(Offer offer) {
        offerRepository.delete(offer);
    }
}
