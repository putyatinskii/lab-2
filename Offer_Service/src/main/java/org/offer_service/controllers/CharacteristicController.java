package org.offer_service.controllers;

import org.offer_service.business_logic.CharacteristicLogic;
import org.offer_service.entities.Characteristic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("offer_service/characteristic")
public class CharacteristicController {
    @Autowired
    CharacteristicLogic characteristicLogic;

    @GetMapping(value = "/listOfCharacteristics")
    public ResponseEntity<List<Characteristic>> getAllCharacteristic() {
        return ResponseEntity.ok(characteristicLogic.getAll());
    }

    @GetMapping(value = "/id={id}")
    public ResponseEntity<Characteristic> get(@PathVariable Integer id) {
        return ResponseEntity.ok(characteristicLogic.get(id));
    }

    @PostMapping(value = "/new")
    public ResponseEntity<Characteristic> createCharacteristic(@RequestBody Characteristic characteristic) {
        return ResponseEntity.ok(characteristicLogic.create(characteristic));
    }

    @PutMapping(value = "/id={id}")
    public ResponseEntity<Characteristic> updateCharacteristic(@PathVariable Integer id, @RequestBody Characteristic characteristic) {
        return ResponseEntity.ok(characteristicLogic.update(id, characteristic));
    }

    @DeleteMapping(value = "/id={id}")
    public ResponseEntity<String> deleteCharacteristic(@PathVariable Integer id) {
        characteristicLogic.delete(characteristicLogic.get(id));
        return ResponseEntity.ok("Characteristics was removed successfully");
    }

}
