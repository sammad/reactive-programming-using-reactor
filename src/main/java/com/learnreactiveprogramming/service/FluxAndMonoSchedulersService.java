package com.learnreactiveprogramming.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.learnreactiveprogramming.util.CommonUtil.delay;

public class FluxAndMonoSchedulersService {

    static List<String> namesList = List.of("ram", "krishna", "shiv");
    static List<String> namesList1 = List.of("badrinath", "hari", "hirambh");
    static Logger LOGGER = LoggerFactory.getLogger(FluxAndMonoSchedulersService.class);
    private String upperCase(String name) {
        delay(1000);
        return name.toUpperCase();
    }

    public Flux<String> namesFlux(){
        return Flux.fromIterable(namesList);
    }
    public Mono<String> namesMono(){
        return Mono.just("Ram");
    }
    public static void main(String[] args) {
        FluxAndMonoSchedulersService fluxAndMonoSchedulersService = new FluxAndMonoSchedulersService();
        fluxAndMonoSchedulersService.namesFlux().subscribe(name-> LOGGER.info("The flux name is {}",name));
        fluxAndMonoSchedulersService.namesMono().subscribe(name->LOGGER.info("The mono name is {}",name));
    }

}
