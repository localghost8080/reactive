package org.samrat.reactive;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class FluxAndMonoGeneratorService {

    public Flux<String> namesFlux(){
        return Flux.fromIterable(List.of("samrat","is","gadha"))
                .log();
    }

    public String getName(){
        return "Samrat";
    }
    public  Flux<String> namesFluxMap(){
        return Flux.fromIterable(List.of("samrat","is","gadha")).doOnNext(n -> {
            int length = n.length();
            System.out.println("doOnNext print item length::" + length);
        }).map(String::toUpperCase);
    }

    public  Flux<String> namesFluxFlatMap(){
        return namesFlux()
                .map(String::toUpperCase)
                .flatMap(this::namesSplitFlux).log();
    }

    public Flux<String> namesSplitFlux(String name){

        Random rand = new Random();
        return Flux.fromArray(name.split("")).delayElements(Duration.ofMillis(rand.nextInt(500))).log();
    }

    public Mono<String> namesMono(){
        return Mono.just("Krishna")
                .map(String::toUpperCase).log();
    }

    public Flux<String> namesMononFlatMapMany() {
        return namesMono().flatMapMany(this::namesSplitFlux);

    }
}
