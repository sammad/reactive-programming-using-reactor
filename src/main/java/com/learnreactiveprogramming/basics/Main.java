package com.learnreactiveprogramming.basics;

public class Main {
    public static void main(String[] args) {
        IndexPricePublisher indexPricePublisher = new IndexPricePublisher();
        IndexPriceSubscriber indexPriceSubscriber = new IndexPriceSubscriber();
        indexPricePublisher.subscribe(indexPriceSubscriber);
        indexPriceSubscriber.getNPricePoints(10L);
        indexPriceSubscriber.abort();
        indexPriceSubscriber.getNPricePoints(1L);
    }
}
