package org.samrat;

import org.samrat.reactive.FluxAndMonoGeneratorService;

import java.awt.*;

public class Main {
    static FluxAndMonoGeneratorService fmgs = new FluxAndMonoGeneratorService();
    public static void main(String[] args) {

        fmgs.namesFluxFlatMap().subscribe( n -> System.out.println("Name is ::"+ n));
        //fmgs.namesMono().subscribe(k-> System.out.println("Name is ::"+ k));
    }
}