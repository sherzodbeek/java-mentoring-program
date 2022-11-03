package com.jmp.reactive.sportreactive.config;

import com.jmp.reactive.sportreactive.entity.Sport;
import com.jmp.reactive.sportreactive.repository.SportRepository;
import com.jmp.reactive.sportreactive.dto.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class Setup implements CommandLineRunner {

    private final SportRepository repository;
    Logger logger = LoggerFactory.getLogger(Setup.class);

    @Value("${sports.api.url}")
    private String sportsApiUrl;

    @Value("${setup.sport.data}")
    private boolean setupSportData;

    public Setup(SportRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        if (setupSportData) {
            WebClient webClient = WebClient.builder()
                    .exchangeStrategies(ExchangeStrategies.builder()
                            .codecs(clientCodecConfigurer -> clientCodecConfigurer
                                    .defaultCodecs()
                                    .maxInMemorySize(16 * 1024 * 1024)).build())
                    .build();

            Set<String> sportNames = new HashSet<>();
            Flux<Sport> sportFlux = webClient.get()
                    .uri(sportsApiUrl)
                    .retrieve()
                    .bodyToMono(Data.class)
                    .map(Data::getData)
                    .map(response ->
                            response.stream()
                                    .map(sport ->  new Sport(sport.getId(), sport.getAttributes().getName()).setAsNew()
                                    )
                                    .collect(Collectors.toList())
                    ).flatMapMany(Flux::fromIterable);

            repository.saveAll(sportFlux)
                    .onErrorContinue((throwable, o) -> logger.info("Error Message: {}", throwable.getMessage()))
                    .subscribe();

            logger.info("All sports have been saved");

            /* Task 3
            BackpressureReadySubscriber<Sport> sportSubscriber = new BackpressureReadySubscriber<>();
            repository.saveAll(sportFlux)
                    .onErrorContinue((throwable, o) -> logger.info("Error Message: {}", throwable.getMessage()))
                    .subscribe(sportSubscriber);
             */
        }
    }
}
