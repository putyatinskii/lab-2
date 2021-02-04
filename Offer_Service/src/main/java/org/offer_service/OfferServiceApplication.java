package org.offer_service;

import org.offer_service.entities.Category;
import org.offer_service.entities.Characteristic;
import org.offer_service.entities.Offer;
import org.offer_service.repositories.CategoryRepository;
import org.offer_service.repositories.CharacteristicRepository;
import org.offer_service.repositories.OfferRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class OfferServiceApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(OfferServiceApplication.class);
        OfferRepository offerRepository = context.getBean(OfferRepository.class);
        CharacteristicRepository characteristicRepository = context.getBean(CharacteristicRepository.class);
        CategoryRepository categoryRepository = context.getBean(CategoryRepository.class);

        Characteristic characteristic = new Characteristic("name", "description");
        Category category = new Category("NewCategory");
        Offer offer = new Offer("NewOffer", 10, 1);
        characteristicRepository.save(characteristic);
        categoryRepository.save(category);
        offer.setCategory(category);
        offerRepository.save(offer);
        context.close();
    }
}
