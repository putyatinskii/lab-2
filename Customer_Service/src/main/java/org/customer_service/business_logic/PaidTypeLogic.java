package org.customer_service.business_logic;

import org.customer_service.entities.Customer;
import org.customer_service.entities.PaidType;
import org.customer_service.exceptions.NotFoundException;
import org.customer_service.repositories.PaidTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaidTypeLogic implements BusinessLogicMethods<PaidType> {
    @Autowired
    private PaidTypeRepository paidTypeRepository;

    @Override
    public List<PaidType> getAll() {
        List<PaidType> paidTypes = new ArrayList<>();
        paidTypeRepository.findAll().forEach(paidTypes::add);
        return paidTypes;
    }

    @Override
    public PaidType get(Integer id) {
        PaidType paidType = paidTypeRepository.findById(id).orElseThrow(NotFoundException::new);
        return paidType;
    }

    @Override
    public PaidType create(PaidType paidType) {
        paidType = paidTypeRepository.save(paidType);
        return paidType;
    }

    @Override
    public PaidType update(Integer id, PaidType paidType) {
        PaidType paidTypeFormDB = get(id);
        PaidType paidType1 = new PaidType();
        try {
            paidType1 = paidType.clone();
            paidType1.setId(paidTypeFormDB.getId());
        }
        catch (CloneNotSupportedException ex){

        }
        return paidTypeRepository.save(paidType1);
    }

    @Override
    public void delete(PaidType paidType) {
        paidTypeRepository.delete(paidType);
    }
}
