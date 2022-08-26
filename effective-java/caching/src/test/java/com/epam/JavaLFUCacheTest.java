package com.epam;

import com.epam.lfuCache.JavaLFUCacheService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JavaLFUCacheTest {

    private JavaLFUCacheService cacheService;

    @BeforeEach
    void init() {
        cacheService = new JavaLFUCacheService();
    }

    @Test
    void shouldReturnValueFromCache() {
        for (int i = 0; i < 50; i++) {
            cacheService.put(i, new Element(String.valueOf(i)));
        }
        Element result = cacheService.get(10);

        Assertions.assertEquals("10", result.getValue());
    }

    @Test
    void shouldEvictLFU() {
        for (int i = 0; i < 50; i++) {
            cacheService.put(i, new Element(String.valueOf(i)));
        }
        for (int i = 0; i < 10; i++) {
            cacheService.get(i);
        }
        for (int i = 60; i < 70; i++) {
            cacheService.put(i, new Element(String.valueOf(i)));
        }

        Element result = cacheService.get(11);

        Assertions.assertNull(result);
    }
}
