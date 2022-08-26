package com.epam.lfuCache;

import com.epam.Element;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.logging.Logger;

public class JavaLFUCacheService {

    private Map<Integer, Element> values = new HashMap<>(); // to store values
    private Map<Integer, Integer> counters = new HashMap<>(); // to store counters with key
    private Map<Integer, LinkedHashSet<Integer>> lists = new HashMap<>(); // to store used number of values from cache

    public JavaLFUCacheService() {
        lists.put(1, new LinkedHashSet<>());
    }

    private final int MAX_CACHE_SIZE = 50;
    private BigInteger cacheEvictions = BigInteger.ZERO;
    private int min = -1; // minimal number of using

    private Logger logger = Logger.getLogger(JavaLFUCacheService.class.getName());

    public Element get(int key) {
        if (!values.containsKey(key)) {
            return null;
        }

        // update counter
        int count = counters.get(key);
        counters.put(key, count + 1);

        lists.get(count).remove(key);

        // is getting same value
        if (count == min && lists.get(count).isEmpty()) {
            min++;
        }

        // is it first time getting this key
        if (!lists.containsKey(count + 1)) {
            lists.put(count + 1, new LinkedHashSet<>());
        }
        lists.get(count + 1).add(key);
        return values.get(key);
    }


    public void put(int key, Element element) {
        // is used key before
        if (values.containsKey(key)) {
            values.put(key, element);
            get(key);
            return;
        }

        // remove lfu value from cache
        if (values.size() >= MAX_CACHE_SIZE) {
            Integer lfuKey = lists.get(min).iterator().next();
            lists.get(min).remove(lfuKey);
            Element removingElement = values.get(lfuKey);
            logger.info("Removing element: " + removingElement);
            values.remove(lfuKey);
            counters.remove(lfuKey);
            cacheEvictions = cacheEvictions.add(BigInteger.ONE);
        }
        // add new key with value
        values.put(key, element);
        counters.put(key, 1);
        min = 1;
        lists.get(1).add(key);
    }

    public void showStatistic() {
        logger.info("Number of cached elements: " + values.size());
        logger.info("Number of evections: " + cacheEvictions);
    }
}
