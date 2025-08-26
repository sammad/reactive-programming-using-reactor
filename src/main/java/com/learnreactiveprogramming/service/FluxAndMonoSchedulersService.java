package com.learnreactiveprogramming.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

import static com.learnreactiveprogramming.util.CommonUtil.delay;

public class FluxAndMonoSchedulersService {

    static List<String> namesList = List.of("ram", "krishna", "shiv");
    static List<String> namesList1 = List.of("sita", "radha", "parvati");
    static Logger LOGGER = LoggerFactory.getLogger(FluxAndMonoSchedulersService.class);
    private String upperCase(String name) {
        delay(1000);
        return name.toUpperCase();
    }

    public Flux<String> namesFlux(){
        return Flux.fromIterable(namesList).log();
    }
    public Flux<String> namesFluxMap(){
        return Flux.fromIterable(namesList).map(String::toUpperCase);
    }
    public Flux<String> namesFluxQuiz(){
        Flux<String> flux1 = Flux.fromIterable(namesList);
        flux1.map(this::upperCase);
        return flux1;
    }

    public Flux<String> namesConcatFluxes(){
        Flux<String> flux1 = Flux.fromIterable(namesList);
        Flux<String> flux2 = Flux.fromIterable(namesList1);
        return Flux.concat(flux1,flux2);
    }

    public Flux<String> namesMergeFluxes(){
        Flux<String> flux1 = Flux.fromIterable(namesList).delayElements(Duration.ofMillis(100));
        Flux<String> flux2 = Flux.fromIterable(namesList1).delayElements(Duration.ofMillis(125));
        return Flux.merge(flux1,flux2);
    }
    public Flux<String> namesMergeSequentialFluxes(){
        Flux<String> flux1 = Flux.fromIterable(namesList).delayElements(Duration.ofMillis(100));
        Flux<String> flux2 = Flux.fromIterable(namesList1).delayElements(Duration.ofMillis(125));
        return Flux.mergeSequential(flux1,flux2);
    }

    public Flux<String> namesZipFlux(){
        Flux<String> flux1 = Flux.fromIterable(namesList);
        Flux<String> flux2 = Flux.fromIterable(namesList1);
        //return flux1.zipWith(flux2);
        return Flux.zip(flux1,flux2,(first,second)->first+"-"+second);
    }
    public Mono<String> namesMono(){
        return Mono.just("Ram");
    }
    public static void main(String[] args) {
        FluxAndMonoSchedulersService fluxAndMonoSchedulersService = new FluxAndMonoSchedulersService();
        /*fluxAndMonoSchedulersService.namesFlux().subscribe(name-> LOGGER.info("The flux name is {}",name));
        fluxAndMonoSchedulersService.namesMono().subscribe(name->LOGGER.info("The mono name is {}",name));
        fluxAndMonoSchedulersService.namesFluxMap().subscribe(name->LOGGER.info("The flux name after map is {}",name));
        fluxAndMonoSchedulersService.namesConcatFluxes().subscribe(name->LOGGER.info("The concat flux {}",name));*/
        //In real life code we don't do that because it is blocking the main thread
        fluxAndMonoSchedulersService.namesMergeFluxes().doOnNext(name->LOGGER.info("The merge flux {}",name)).blockLast();
        fluxAndMonoSchedulersService.namesMergeSequentialFluxes().doOnNext(name->LOGGER.info("The merge sequencial flux {}",name)).blockLast();
        fluxAndMonoSchedulersService.namesZipFlux().doOnNext(name->LOGGER.info("The zip flux {}",name)).blockLast();

    }

}
