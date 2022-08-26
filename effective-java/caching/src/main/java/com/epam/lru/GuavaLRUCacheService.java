package com.epam.lru;

import com.epam.Element;
import com.google.common.cache.LoadingCache;

import java.util.Optional;

public class GuavaLRUCacheService {

    public static void main(String[] args) throws InterruptedException {
        GuavaCachingService.generateMap();

        GuavaCachingService cachingService = new GuavaCachingService();

        LoadingCache<Integer, Optional<Element>> cache = cachingService.getGuavaCache();

        Optional<Element> element = cache.getUnchecked(2);
        element.ifPresent(System.out::println);
        Optional<Element> element2 = cache.getUnchecked(2);
        element2.ifPresent(System.out::println);

        Thread.sleep(5000);
        Optional<Element> element3 = cache.getUnchecked(2);
        element3.ifPresent(System.out::println);
    }
}
