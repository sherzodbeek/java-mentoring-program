package com.jmp.reactive.sportreactive.subscriber;

import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.BaseSubscriber;

public class BackpressureReadySubscriber<T> extends BaseSubscriber<T> {

    Logger logger = LoggerFactory.getLogger(BackpressureReadySubscriber.class);
    private int consumed;
    private static final int LIMIT = 20;

    @Override
    protected void hookOnSubscribe(Subscription subscription) {
        logger.info("Request for first 20 elements");
        request(LIMIT);
    }


    @Override
    protected void hookOnNext(T value) {
        consumed++;
        logger.info("Data: {}", value);
        if (consumed == LIMIT) {
            logger.info("Request for next 20 elements");
            consumed = 0;
            request(LIMIT);
        }
    }
}