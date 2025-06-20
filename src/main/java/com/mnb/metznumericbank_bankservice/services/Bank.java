package com.mnb.metznumericbank_bankservice.services;

import reactor.core.publisher.Mono;

public interface Bank {
    String withdraw(Long accountId, Long ClientId, double amount);
    String deposit(Long accountId, Long clientId, double amount);
    String openAccount(Long clientId);
    Mono<String> consultAccount(Long accountId);
    String convertFromEuro(double amount);
    String convertToEuro(double amount);
}
