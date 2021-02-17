package org.customer_service.controllers;

import org.customer_service.business_logic.PaidTypeLogic;
import org.customer_service.entities.Customer;
import org.customer_service.entities.PaidType;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer_service/paid_type")
public class PaidTypeController {
    @Autowired
    private PaidTypeLogic paidTypeLogic;

    @GetMapping(value = "/listOfPaidTypes")
    public ResponseEntity<List<PaidType>> getAllPaidType() {
        return ResponseEntity.ok(paidTypeLogic.getAll());
    }

    @GetMapping(value = "/id={id}")
    public ResponseEntity<PaidType> get(@PathVariable Integer id) {
        return ResponseEntity.ok(paidTypeLogic.get(id));
    }

    @PostMapping(value = "/new")
    public ResponseEntity<PaidType> createPaidType(@RequestBody PaidType paidType) {
        return ResponseEntity.ok(paidTypeLogic.create(paidType));
    }

    @PutMapping(value = "/id={id}")
    public ResponseEntity<PaidType> updatePaidType(@PathVariable Integer id, @RequestBody PaidType paidType) {
        return ResponseEntity.ok(paidTypeLogic.update(id, paidType));
    }

    @DeleteMapping(value = "/id={id}")
    public ResponseEntity<String> deletePaidType(@PathVariable Integer id) {
        paidTypeLogic.delete(paidTypeLogic.get(id));
        return ResponseEntity.ok("PaidType was removed successfully");
    }
}
