package com.learnreactiveprogramming.basics;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class IndexPriceSubscriber implements Subscriber<Double> {
    private Subscription priceSubscription;
    private Long howManyPrices=0L;

    public IndexPriceSubscriber() {
    }

    public IndexPriceSubscriber(Long howManyPrices) {
        this.howManyPrices = howManyPrices;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.priceSubscription=subscription;
        subscription.request(howManyPrices);
    }

    @Override
    public void onNext(Double aDouble) {
        System.out.println("Saved Price "+aDouble+" in price database");
    }

    @Override
    public void onError(Throwable throwable) {
        System.err.println(throwable);
    }

    @Override
    public void onComplete() {
        System.out.println("Price relay is complete");
    }

    public void getNPricePoints(Long N) {
        priceSubscription.request(N);
    }

    public void abort() {
        priceSubscription.cancel();
    }
}
