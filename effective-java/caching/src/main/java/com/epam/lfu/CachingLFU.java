package com.epam.lfu;

import com.epam.Element;

public class CachingLFU {

    public static void main(String[] args) {
        JavaLFUCacheService cacheService = new JavaLFUCacheService();
        for (int i = 0; i < 50; i++) {
            cacheService.put(i, new Element(String.valueOf(i)));
        }
        for (int i = 0; i < 15; i++) {
            cacheService.get(i);
        }
        for (int i = 60; i < 80; i++) {
            cacheService.put(i, new Element(String.valueOf(i)));
        }
        cacheService.showStatistic();
    }
}
