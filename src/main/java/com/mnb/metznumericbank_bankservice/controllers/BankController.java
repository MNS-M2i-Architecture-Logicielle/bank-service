package com.mnb.metznumericbank_bankservice.controllers;

import com.mnb.metznumericbank_bankservice.services.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/bank")
public class BankController {
    
    private final Bank bank;

    @Autowired
    public BankController(Bank bank) {
        this.bank = bank;
    }

    @GetMapping("/{id}")
    public Mono<String> getAccount(@PathVariable Long id) {
        return bank.consultAccount(id);
    }
}
