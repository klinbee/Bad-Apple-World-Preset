package com.klinbee.badapple;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class SimpleCache<K, V> {
    private final int maxSize;
    private final Map<K, V> cache;

    public SimpleCache(int maxSize) {
        this.maxSize = maxSize;
        this.cache = Collections.synchronizedMap(new LinkedHashMap<>(maxSize, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > SimpleCache.this.maxSize;
            }
        });
    }

    public void put(K key, V value) {
        cache.put(key, value);
    }

    public V get(K key) {
        return cache.get(key);
    }

    public void remove(K key) {
        cache.remove(key);
    }

    public int size() {
        return cache.size();
    }
}
