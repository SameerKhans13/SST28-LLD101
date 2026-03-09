package com.example.map;

import java.util.HashMap;
import java.util.Map;

/**
 * Flyweight factory for {@link MarkerStyle}.  A simple string key is built
 * from the style attributes and used to cache a single immutable instance per
 * configuration.  Clients should always obtain styles through this factory
 * rather than constructing them directly.
 *
 * The cache size can be inspected via {@link #cacheSize()} for debugging
 * or verification purposes.
 */
public class MarkerStyleFactory {

    private final Map<String, MarkerStyle> cache = new HashMap<>();

    public MarkerStyle get(String shape, String color, int size, boolean filled) {
        String key = shape + "|" + color + "|" + size + "|" + (filled ? "F" : "O");
        MarkerStyle style = cache.get(key);
        if (style == null) {
            style = new MarkerStyle(shape, color, size, filled);
            cache.put(key, style);
        }
        return style;
    }

    public int cacheSize() {
        return cache.size();
    }
}