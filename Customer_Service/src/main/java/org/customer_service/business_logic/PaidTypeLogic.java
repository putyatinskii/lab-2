package org.customer_service.business_logic;

import org.customer_service.entities.Customer;
import org.customer_service.entities.PaidType;
import org.customer_service.exceptions.DataErrorException;
import org.customer_service.exceptions.DeleteException;
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
        return paidTypeRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public PaidType create(PaidType paidType) {
        if (paidType.getName() != null)
            return paidTypeRepository.save(paidType);
        else {
            throw new DataErrorException();
        }
    }

    @Override
    public PaidType update(Integer id, PaidType paidType) {
        if (paidType.getName() != null) {
            PaidType paidTypeFormDB = get(id);
            paidTypeFormDB.setName(paidType.getName());
            return paidTypeRepository.save(paidTypeFormDB);
        }
        else {
            throw new DataErrorException();
        }
    }

    @Override
    public void delete(PaidType paidType) {
        try {
        paidTypeRepository.delete(paidType);
    } catch (Exception ex) {
            throw new DeleteException();
        }
    }
}
