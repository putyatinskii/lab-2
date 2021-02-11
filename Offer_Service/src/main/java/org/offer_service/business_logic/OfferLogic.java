package org.offer_service.business_logic;

import org.offer_service.entities.Category;
import org.offer_service.entities.Offer;
import org.offer_service.exception.DataErrorException;
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
        return offerRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Offer create(Offer offer) {
        if (categoryRepository.existsById(offer.getCategory().getId())) {
            Category category = categoryRepository.findById(offer.getCategory().getId())
                    .orElseThrow(NotFoundException::new);
            offer.setCategory(category);
            return offerRepository.save(offer);
        } else {
            throw new DataErrorException();
        }
    }

    @Override
    public Offer update(Integer id, Offer offer) {
        return null;
    }

    @Override
    public void delete(Offer offer) {
        categoryRepository.delete(offer.getCategory());
    }
}
