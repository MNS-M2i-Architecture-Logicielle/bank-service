package com.mnb.metznumericbank_bankservice.controllers;

import com.mnb.metznumericbank_bankservice.services.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * REST controller for bank-related operations.
 *
 * Provides endpoints to access bank account information.
 * Uses reactive programming with Project Reactor's Mono for asynchronous responses.
 * All endpoints are prefixed with "/api/bank".
 */
@RestController
@RequestMapping("/api/bank")
public class BankController {

    /**
     * Service responsible for bank business logic.
     */
    private final Bank bank;

    /**
     * Constructs a new BankController with the specified Bank service.
     *
     * @param bank the bank service to delegate business operations
     */
    @Autowired
    public BankController(Bank bank) {
        this.bank = bank;
    }

    /**
     * Retrieves account information for the account with the given ID.
     *
     * @param id the identifier of the account to consult
     * @return a Mono wrapping the account details as a String
     */
    @GetMapping("/{id}")
    public Mono<String> getAccount(@PathVariable Long id) {
        return bank.consultAccount(id);
    }
}
