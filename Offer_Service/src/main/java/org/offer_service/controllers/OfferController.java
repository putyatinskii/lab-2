package org.offer_service.controllers;

import org.offer_service.business_logic.OfferLogic;
import org.offer_service.entities.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("offer_service/offer")
public class OfferController {
    @Autowired
    OfferLogic offerLogic;

    @GetMapping(value = "/listOfOffers")
    public ResponseEntity<List<Offer>> getAllOffer() {
        return ResponseEntity.ok(offerLogic.getAll());
    }

    @GetMapping(value = "/id={id}")
    public ResponseEntity<Offer> getOffer(@PathVariable Integer id) {
        return ResponseEntity.ok(offerLogic.get(id));
    }

    @PostMapping(value = "/new")
    public ResponseEntity<Offer> createOffer(@RequestBody Offer offer) {
        return ResponseEntity.ok(offerLogic.create(offer));
    }

    @PutMapping(value = "/id={id}")
    public ResponseEntity<Offer> updateOffer(@PathVariable Integer id, @RequestBody Offer offer) {
        return ResponseEntity.ok(offerLogic.update(id, offer));
    }

    @DeleteMapping(value = "/id={id}")
    public ResponseEntity<String> deleteOffer(@PathVariable Integer id) {
        offerLogic.delete(offerLogic.get(id));
        return ResponseEntity.ok("Offer was removed successfully");
    }
}
