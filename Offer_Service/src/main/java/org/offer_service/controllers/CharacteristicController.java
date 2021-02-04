package org.offer_service.controllers;

import org.offer_service.business_logic.CharacteristicLogic;
import org.offer_service.entities.Characteristic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("offer_service/characteristic")
public class CharacteristicController {
    @Autowired
    CharacteristicLogic characteristicLogic;

    @GetMapping(value = "/listOfCharacteristics")
    public List<Characteristic> getAllCharacteristic() {
        return characteristicLogic.getAll();
    }

    @GetMapping(value = "/id={id}")
    public Characteristic get(@PathVariable Integer id) {
        return characteristicLogic.get(id);
    }

    @PostMapping(value = "/new")
    public Characteristic createCharacteristic(@RequestBody Characteristic characteristic) {
        return characteristicLogic.create(characteristic);
    }

    @PutMapping(value = "/id={id}")
    public Characteristic updateCharacteristic(@PathVariable Integer id, @RequestBody Characteristic characteristic) {
        return characteristicLogic.update(id, characteristic);
    }

    @DeleteMapping(value = "/id={id}")
    public void deleteCharacteristic(@PathVariable Integer id) {
        characteristicLogic.delete(characteristicLogic.get(id));
    }

}
