package org.samrat.reactive;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FluxAndMonoGeneratorServiceTest {

    FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();

    @Test
    void namesFlux() {
        var namesFlux = fluxAndMonoGeneratorService.namesFlux();
        String samrat = "samrat";
        StepVerifier.create(namesFlux) // Takes case of subscription
                .expectNext(samrat)
                .expectNextCount(2)  // count is 2 because 1 event is consumed above
                .verifyComplete();
    }

    @Test
    void namesFluxMap() {
        StepVerifier.create(fluxAndMonoGeneratorService.namesFluxMap())
                .expectNext("SAMRAT","IS","GADHA")
                .expectComplete()
                .verify();
    }

    @Test
    void namesFluxFlatMap() {
        var namesFlux = fluxAndMonoGeneratorService.namesFluxFlatMap();
        StepVerifier.create(namesFlux)
                .recordWith(ArrayList::new)
                .thenConsumeWhile(x-> true)
                .consumeRecordedWith(i-> System.out.println(i+ ", "))
                //.expectNext("S","A","M","R","A","T","I","S","G","A","D","H","A")
                .verifyComplete();

    }

    @Test
    void namesMono(){
        var namesMono = fluxAndMonoGeneratorService.namesMono();
        StepVerifier.create(namesMono)
                .expectNext("KRISHNA")
                .verifyComplete();
    }

    @Test
    void namesMononFlatMapMany(){
        var namesFlux = fluxAndMonoGeneratorService.namesMononFlatMapMany();
        StepVerifier.create(namesFlux)
                .recordWith(ArrayList::new)
                .thenConsumeWhile(x-> true)
                .consumeRecordedWith(i-> System.out.println(i+ ", "))
                //.expectNext("S","A","M","R","A","T","I","S","G","A","D","H","A")
                .verifyComplete();
    }
}