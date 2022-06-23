package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {
    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
            SoftReference<V> ref = new SoftReference<>(value);
            cache.put(key, ref);
    }

    public V get(K key) {
        V value = cache.getOrDefault(key, new SoftReference<>(null)).get();
        if (value == null) {
            put(key, load(key));
            value = cache.get(key).get();
        }
        return value;
    }

    protected abstract V load(K key);
}
