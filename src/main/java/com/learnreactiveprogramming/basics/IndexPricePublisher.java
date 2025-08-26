package com.learnreactiveprogramming.basics;

import com.learnreactiveprogramming.exception.UnregisteredSubscriberException;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

public class IndexPricePublisher implements Publisher<Double> {
    private final List<Subscription> priceSubscriptions = new ArrayList<>();
    private Boolean isPriceEmitCanceled = Boolean.FALSE;
    private Logger LOGGER = LoggerFactory.getLogger(IndexPricePublisher.class);
    @Override
    public void subscribe(Subscriber<? super Double> subscriber) {
        Subscription priSubscription= new IndexPriceSubscription(subscriber,this);
        subscriber.onSubscribe(priSubscription);
        priceSubscriptions.add(priSubscription);
    }

    public Double emitPrice(IndexPriceSubscription indexPriceSubscription){
        if(!priceSubscriptions.contains(indexPriceSubscription)){
            LOGGER.error("This subscriber is not subscribed or subscription is cancelled. Please subscribe again");
            throw new UnregisteredSubscriberException("Subscriber not registered");
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return RandomGenerator.getDefault().nextDouble();
    }

    public void cancelPriceEmit(IndexPriceSubscription indexPriceSubscription) {
        priceSubscriptions.remove(indexPriceSubscription);
    }

}
