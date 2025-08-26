package com.learnreactiveprogramming.basics;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Random;
import java.util.random.RandomGenerator;

public class IndexPriceSubscription implements Subscription {
    private final Subscriber<? super Double> subscriber;
    private final IndexPricePublisher publisher;

    public IndexPriceSubscription(Subscriber<? super Double> subscriber, IndexPricePublisher publisher) {
        this.subscriber = subscriber;
        this.publisher = publisher;
    }

    @Override
    public void request(long l) {
        for(Long index =0L;index<l;index++){
            this.subscriber.onNext(this.publisher.emitPrice(this));
        }
        this.subscriber.onComplete();
    }

    @Override
    public void cancel() {
        this.publisher.cancelPriceEmit(this);
    }
}
