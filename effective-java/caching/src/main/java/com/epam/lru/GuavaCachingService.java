package com.epam.lru;

import com.epam.Element;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class GuavaCachingService {

    public static Map<Integer, Element> elements = new HashMap<>();

    public static void generateMap() {
        for (int i = 0; i < 100; i++) {
            elements.put(i, new Element(String.valueOf(i)));
        }
    }

    private static Element getElement(int key) throws InterruptedException {
        logger.info("Backend call...");
        Thread.sleep(2000);
        return elements.get(key);
    }

    private static Logger logger = Logger.getLogger(GuavaCachingService.class.getName());

    public LoadingCache<Integer, Optional<Element>> getGuavaCache() {
        CacheLoader<Integer, Optional<Element>> loader = new CacheLoader<Integer, Optional<Element>>() {
            @Override
            public Optional<Element> load(Integer key) throws Exception {
                return Optional.ofNullable(getElement(key));
            }
        };

        RemovalListener<Integer, Optional<Element>> listener = notification -> {
            if (notification.wasEvicted()) {
                Optional<Element> removedElement = notification.getValue();
                Element element = removedElement.get();
                logger.info("Removed element: " + element);
            }
        };

        return CacheBuilder.newBuilder()
                .maximumSize(100_000)
                .expireAfterAccess(5, TimeUnit.SECONDS)
                .removalListener(listener)
                .build(loader);
    }
}
