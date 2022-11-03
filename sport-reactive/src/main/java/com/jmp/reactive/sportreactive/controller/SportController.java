package com.jmp.reactive.sportreactive.controller;

import com.jmp.reactive.sportreactive.entity.Sport;
import com.jmp.reactive.sportreactive.exception.ServerErrorException;
import com.jmp.reactive.sportreactive.exception.UniqueNameException;
import com.jmp.reactive.sportreactive.repository.SportRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/v1/sport")
public class SportController {

    private final SportRepository repository;


    public SportController(SportRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/{sportName}")
    public Mono<Sport> createSport(@PathVariable String sportName) {
        return repository.save(new Sport(sportName))
                .onErrorResume(e -> {
                    if (e instanceof DataIntegrityViolationException) {
                        return Mono.error(new UniqueNameException());
                    } else {
                        return Mono.error(new ServerErrorException());
                    }
                });
    }

    @GetMapping
    public Mono<Sport> getSportByName(@RequestParam String q) {
        return repository.findByName(q);
    }
}
