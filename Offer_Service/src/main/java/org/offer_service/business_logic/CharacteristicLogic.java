package org.offer_service.business_logic;

import org.offer_service.entities.Characteristic;
import org.offer_service.exception.DataErrorException;
import org.offer_service.exception.DeleteException;
import org.offer_service.exception.NotFoundException;
import org.offer_service.repositories.CharacteristicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        return characteristicRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Characteristic create(Characteristic characteristic) {
        if (characteristic.getName() != null &&
                characteristic.getDescription() != null &&
                !characteristicRepository.existsByName(characteristic.getName()))
            return characteristicRepository.save(characteristic);
        else {
            throw new DataErrorException();
        }
    }

    @Override
    public Characteristic update(Integer id, Characteristic characteristic) {
        if (characteristic.getName() != null &&
                characteristic.getDescription() != null) {
            Characteristic characteristicFromDB = get(id);
            if (!characteristic.getName().equals(characteristicFromDB.getName()))
                if (characteristicRepository.existsByName(characteristic.getName())) {
                    throw new DataErrorException();
                }
            characteristicFromDB.setName(characteristic.getName());
            characteristicFromDB.setDescription(characteristic.getDescription());
            return characteristicRepository.save(characteristicFromDB);
        }
        else {
            throw new DataErrorException();
        }
    }

    @Override
    public void delete(Characteristic characteristic) {
        try {
            characteristicRepository.delete(characteristic);
        } catch (Exception ex) {
            throw new DeleteException();
        }
    }

    public Set<Characteristic> saveCharacteristics(Set<Characteristic> characteristics) {
        Set<Characteristic> characteristicSet = new HashSet<>();
        for (Characteristic characteristic : characteristics) {
            if(!characteristicRepository.existsByName(characteristic.getName())) {
                create(characteristic);
                characteristicSet.add(characteristic);
            }
            else {
                Characteristic characteristic1 = characteristicRepository.findByName(characteristic.getName())
                        .orElseThrow(DataErrorException::new);
                characteristic1.setDescription(characteristic.getDescription());
                update(characteristic1.getId(), characteristic1);
                characteristicSet.add(characteristic1);
            }
        }
        return characteristicSet;
    }
}
