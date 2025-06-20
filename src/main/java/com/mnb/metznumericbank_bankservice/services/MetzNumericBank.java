package com.mnb.metznumericbank_bankservice.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Implementation of the {@link Bank} interface representing
 * the Metz Numeric Bank service.
 * This service provides operations such as withdrawal, deposit,
 * account opening, account consultation, and currency conversion.
 * It uses Spring WebClient for reactive HTTP calls to external APIs.
 */
@Service
public class MetzNumericBank implements Bank {

    /**
     * The name of the bank.
     */
    private final String name = "Metz Numeric Bank";

    /**
     * Builder for creating WebClient instances to make HTTP requests.
     */
    private final WebClient.Builder webClientBuilder;

    /**
     * Constructs a new MetzNumericBank service with the specified WebClient builder.
     *
     * @param webClientBuilder the WebClient builder to build WebClient instances
     */
    public MetzNumericBank(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    /**
     * Withdraws a specified amount from the given account of a client.
     * Currently not implemented.
     *
     * @param accountId the account ID
     * @param clientId the client ID
     * @param amount the amount to withdraw
     * @return an empty string (not implemented)
     */
    @Override
    public String withdraw(Long accountId, Long clientId, double amount) {
        return "";
    }

    /**
     * Deposits a specified amount into the given account of a client.
     * Currently not implemented.
     *
     * @param accountId the account ID
     * @param clientId the client ID
     * @param amount the amount to deposit
     * @return an empty string (not implemented)
     */
    @Override
    public String deposit(Long accountId, Long clientId, double amount) {
        return "";
    }

    /**
     * Opens a new account for the specified client.
     * Currently not implemented.
     *
     * @param clientId the client ID
     * @return an empty string (not implemented)
     */
    @Override
    public String openAccount(Long clientId) {
        return "";
    }

    /**
     * Consults the balance of the specified account.
     * Returns a Mono wrapping a message containing the account number and its balance.
     *
     * @param accountId the account ID to consult
     * @return a Mono emitting a formatted string with the account balance
     */
    @Override
    public Mono<String> consultAccount(Long accountId) {
        return getAccountAmount(accountId)
                .map(balance -> "Le compte n°" + accountId + " contient " + balance + "€");
    }

    /**
     * Converts the given amount from Euros to another currency.
     * Currently not implemented.
     *
     * @param amount the amount in Euros
     * @return an empty string (not implemented)
     */
    @Override
    public String convertFromEuro(double amount) {
        return "";
    }

    /**
     * Converts the given amount from another currency to Euros.
     * Currently not implemented.
     *
     * @param amount the amount in foreign currency
     * @return an empty string (not implemented)
     */
    @Override
    public String convertToEuro(double amount) {
        return "";
    }

    /**
     * Retrieves the account balance for the specified account ID
     * by making a reactive HTTP GET request to an external account service.
     * Parses the JSON response to extract the balance.
     *
     * @param accountId the account ID
     * @return a Mono emitting the account balance as a Double
     */
    public Mono<Double> getAccountAmount(Long accountId) {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8080/api/account/" + accountId)
                .retrieve()
                .bodyToMono(String.class)
                .handle((json, sink) -> {
                    try {
                        ObjectMapper mapper = new ObjectMapper();
                        JsonNode root = mapper.readTree(json);
                        sink.next(root.get("balance").asDouble());
                    } catch (Exception e) {
                        sink.error(new RuntimeException("Error parsing JSON response", e));
                    }
                });
    }
}
