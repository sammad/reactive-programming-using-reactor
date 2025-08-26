package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class FluxAndMonoGeneratorServiceTest {
    FluxAndMonoSchedulersService fluxAndMonoGeneratorService = new FluxAndMonoSchedulersService();

    @Test
    @DisplayName("Given Flux With 3 Names Subscriber Should Get The Values Successfully")
    void givenNameFluxWith3ValuesSubscriberShouldGetTheValuesSuccessfully() {
        StepVerifier.create(fluxAndMonoGeneratorService.namesFlux())
                .expectNext("ram", "krishna", "shiv")
                .verifyComplete();
    }

    @Test
    @DisplayName("Given Flux With 1 Name Subscriber Should Get The Value Successfully")
    void givenNameMonoWith1ValueSubscriberShouldGetTheValuesSuccessfully() {
        StepVerifier.create(fluxAndMonoGeneratorService.namesMono())
                .expectNext("Ram")
                .verifyComplete();
    }

    @Test
    void testFluxMap() {
        StepVerifier.create(fluxAndMonoGeneratorService.namesFluxMap())
                .expectNext("RAM", "KRISHNA", "SHIV")
                .verifyComplete();
    }
    @Test
    void testFluxQuiz() {
        StepVerifier.create(fluxAndMonoGeneratorService.namesFluxQuiz())
                .expectNext("RAM", "KRISHNA", "SHIV")
                .verifyComplete();
    }

    @Test
    void testConcatFlux() {
        StepVerifier.create(fluxAndMonoGeneratorService.namesConcatFluxes())
                .expectNext("ram", "krishna", "shiv","sita", "radha", "parvati")
                .verifyComplete();
    }

    @Test
    void testMergeSequencialFluxes() {
        StepVerifier.create(fluxAndMonoGeneratorService.namesMergeSequentialFluxes())
                .expectNext("ram", "krishna", "shiv","sita", "radha", "parvati")
                .verifyComplete();
    }

    @Test
    void testNamesZipFluxes() {
        StepVerifier.create(fluxAndMonoGeneratorService.namesZipFlux())
                .expectNext("ram-sita", "krishna-radha", "shiv-parvati")
                .verifyComplete();
    }
}
