package com.epam;

import com.epam.lru.GuavaCachingService;
import com.google.common.cache.LoadingCache;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class GuavaLRUCacheTest {

    private GuavaCachingService cachingService;


    @BeforeAll
    static void createData() {
        GuavaCachingService.generateMap();
    }
    @BeforeEach
    void init() {
        cachingService = new GuavaCachingService();
    }


    @Test
    void shouldReturnElementFromCache() {
        LoadingCache<Integer, Optional<Element>> cache = cachingService.getGuavaCache();

        Optional<Element> element = cache.getUnchecked(2);
        Element result = element.get();
        Assertions.assertEquals("2", result.getValue());
    }

    @Test
    void shouldReturnElementFromCache2() throws InterruptedException {
        LoadingCache<Integer, Optional<Element>> cache = cachingService.getGuavaCache();

        Optional<Element> element = cache.getUnchecked(10);
        Element result = element.get();
        Assertions.assertEquals("10", result.getValue());
    }

}
