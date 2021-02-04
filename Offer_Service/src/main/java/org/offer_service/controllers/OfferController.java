package org.offer_service.controllers;

import org.offer_service.business_logic.OfferLogic;
import org.offer_service.entities.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("offer_service/offer")
public class OfferController {
    @Autowired
    OfferLogic offerLogic;

    @GetMapping(value = "/listOfOffer")
    public List<Offer> getAllOffer() {
        return offerLogic.getAll();
    }

    @GetMapping(value = "/id={id}")
    public Offer getOffer(@PathVariable Integer id) {
        return offerLogic.get(id);
    }

    @PostMapping(value = "/new")
    public Offer createOffer(@RequestBody Offer offer) {
        return offerLogic.create(offer);
    }

    @PutMapping(value = "/id={id}")
    public Offer updateOffer(@PathVariable Integer id, @RequestBody Offer offer) {
        return offerLogic.update(id, offer);
    }

    @DeleteMapping(value = "/id={id}")
    public void deleteOffer(@PathVariable Integer id) {
        offerLogic.delete(offerLogic.get(id));
    }
}
