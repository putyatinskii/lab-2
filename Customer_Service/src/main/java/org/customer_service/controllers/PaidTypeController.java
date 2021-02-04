package org.customer_service.controllers;

import org.customer_service.business_logic.PaidTypeLogic;
import org.customer_service.entities.Customer;
import org.customer_service.entities.PaidType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer_service/paid_type")
public class PaidTypeController {
    @Autowired
    private PaidTypeLogic paidTypeLogic;

    @GetMapping(value = "/listOfPaidTypes")
    public List<PaidType> getAllPaidType() {
        return paidTypeLogic.getAll();
    }

    @GetMapping(value = "/id={id}")
    public PaidType get(@PathVariable Integer id) {
        return paidTypeLogic.get(id);
    }

    @PostMapping(value = "/new")
    public PaidType createPaidType(@RequestBody PaidType paidType) {
        return paidTypeLogic.create(paidType);
    }

    @PutMapping(value = "/id={id}")
    public PaidType updatePaidType(@PathVariable Integer id, @RequestBody PaidType paidType) {
        return paidTypeLogic.update(id, paidType);
    }

    @DeleteMapping(value = "/id={id}")
    public void deletePaidType(@PathVariable Integer id) {
        paidTypeLogic.delete(paidTypeLogic.get(id));
    }
}
