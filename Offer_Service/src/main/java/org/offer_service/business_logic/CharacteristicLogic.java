package org.offer_service.business_logic;

import org.offer_service.entities.Characteristic;
import org.offer_service.exception.NotFoundException;
import org.offer_service.repositories.CharacteristicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CharacteristicLogic implements BusinessLogic<Characteristic> {
    @Autowired
    private CharacteristicRepository characteristicRepository;

    @Override
    public List<Characteristic> getAll() {
        List<Characteristic> characteristics = new ArrayList<>();
        characteristicRepository.findAll().forEach(characteristics::add);
        return characteristics;
    }

    @Override
    public Characteristic get(Integer id) {
        Characteristic characteristic = characteristicRepository.findById(id).orElseThrow(NotFoundException::new);
        return characteristic;
    }

    @Override
    public Characteristic create(Characteristic characteristic) {
        return characteristicRepository.save(characteristic);
    }

    @Override
    public Characteristic update(Integer id, Characteristic characteristic) {
        return null;
    }

    @Override
    public void delete(Characteristic characteristic) {
        characteristicRepository.delete(characteristic);
    }
}
