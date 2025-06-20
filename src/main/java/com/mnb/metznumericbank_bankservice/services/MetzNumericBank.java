package com.mnb.metznumericbank_bankservice.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class MetzNumericBank implements Bank {
    private final String name = "Metz Numeric Bank";
    
    private final WebClient.Builder webClientBuilder;

    public MetzNumericBank(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public String withdraw(Long accountId, Long ClientId, double amount) {
        return "";
    }

    @Override
    public String deposit(Long accountId, Long clientId, double amount) {
        return "";
    }

    @Override
    public String openAccount(Long clientId) {
        return "";
    }

    @Override
    public Mono<String> consultAccount(Long accountId) {
        return getAccountAmount(accountId)
                .map(balance -> "Le compte n°" + accountId + " contient " + balance + "€");
    }

    @Override
    public String convertFromEuro(double amount) {
        return "";
    }

    @Override
    public String convertToEuro(double amount) {
        return "";
    }
    
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
                        sink.error(new RuntimeException("Erreur lors du parsing du JSON", e));
                    }
                });
    }
}
