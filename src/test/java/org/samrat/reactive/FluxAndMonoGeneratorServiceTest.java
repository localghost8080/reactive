package org.samrat.reactive;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class FluxAndMonoGeneratorServiceTest {

    FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();

    @Test
    void namesFlux() {
        var namesFlux = fluxAndMonoGeneratorService.namesFlux();
        StepVerifier.create(namesFlux) // Takes case of subscription
                .expectNext("samrat")
                .expectNextCount(2)  // count is 2 because 1 event is consumed above
                .verifyComplete();

        namesFlux = fluxAndMonoGeneratorService.namesFluxMap();
        StepVerifier.create(namesFlux)
                .expectNext("SAMRAT","IS","GADHA1")
                .expectComplete();
    }

    @Test
    void namesMono() {
    }

    @Test
    void namesFluxMap() {
        var namesFlux = fluxAndMonoGeneratorService.namesFluxMap();
        StepVerifier.create(namesFlux)
                .expectNext("SAMRAT","IS","GADHA1")
                .expectComplete();
    }

    @Test
    void namesFluxFlatMap() {
        var namesFlux = fluxAndMonoGeneratorService.namesFluxFlatMap();
        StepVerifier.create(namesFlux)
                .consumeNextWith(System.out::println)
                //.expectNextCount(12)
                //.expectNext("S","A","M","R","A","T","I","S","G","A","D","H","A")
                .expectComplete();
    }

    @Test
    void namesMononFlatMapMany() {
        var namesFlux = fluxAndMonoGeneratorService.namesFluxFlatMap();
        StepVerifier.create(namesFlux)
                .expectNext("K","R","I","S","H","N","A")
                .expectComplete();
    }
}