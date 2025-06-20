package com.mnb.metznumericbank_bankservice.services;

import reactor.core.publisher.Mono;

/**
 * Interface defining banking operations.
 *
 * Provides methods for withdrawing, depositing, opening accounts,
 * consulting account details, and currency conversions.
 */
public interface Bank {

    /**
     * Withdraws a specified amount from the given account of a client.
     *
     * @param accountId the account ID to withdraw from
     * @param clientId the client ID performing the withdrawal
     * @param amount the amount to withdraw
     * @return a confirmation message or result of the withdrawal operation
     */
    String withdraw(Long accountId, Long clientId, double amount);

    /**
     * Deposits a specified amount into the given account of a client.
     *
     * @param accountId the account ID to deposit into
     * @param clientId the client ID performing the deposit
     * @param amount the amount to deposit
     * @return a confirmation message or result of the deposit operation
     */
    String deposit(Long accountId, Long clientId, double amount);

    /**
     * Opens a new account for the specified client.
     *
     * @param clientId the client ID for whom to open the account
     * @return a confirmation message or result of the account opening operation
     */
    String openAccount(Long clientId);

    /**
     * Consults the account details or balance for the specified account ID.
     *
     * @param accountId the account ID to consult
     * @return a Mono emitting a string representing the account information
     */
    Mono<String> consultAccount(Long accountId);

    /**
     * Converts the specified amount from Euro to another currency.
     *
     * @param amount the amount in Euros to convert
     * @return the converted amount as a string
     */
    String convertFromEuro(double amount);

    /**
     * Converts the specified amount from another currency to Euro.
     *
     * @param amount the amount in foreign currency to convert
     * @return the converted amount as a string
     */
    String convertToEuro(double amount);
}
