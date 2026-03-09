package com.example.metrics;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * INTENTION: Global metrics registry (should be a Singleton).
 *
 * CURRENT STATE (BROKEN ON PURPOSE):
 * - Constructor is public -> anyone can create instances.
 * - getInstance() is lazy but NOT thread-safe -> can create multiple instances.
 * - Reflection can call the constructor to create more instances.
 * - Serialization can create a new instance when deserialized.
 *
 * TODO (student):
 *  1) Make it a proper lazy, thread-safe singleton (private ctor)
 *  2) Block reflection-based multiple construction
 *  3) Preserve singleton on serialization (readResolve)
 */
public class MetricsRegistry implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /*
     * Guard flag used by the private constructor to detect reflective or
     * second-time instantiation attempts.  Once the singleton has been created
     * the flag flips and any subsequent constructor invocation will throw.
     */
    private static boolean instanceCreated = false;

    private final Map<String, Long> counters = new HashMap<>();

    // constructor is private to prevent normal instantiation
    private MetricsRegistry() {
        if (instanceCreated) {
            throw new IllegalStateException("Singleton already created");
        }
        instanceCreated = true;
    }

    /**
     * Holder class leverages JVM class loading guarantees to perform lazy,
     * thread-safe initialization without synchronization.
     */
    private static class Holder {
        static final MetricsRegistry INSTANCE = new MetricsRegistry();
    }

    public static MetricsRegistry getInstance() {
        return Holder.INSTANCE;
    }

    public synchronized void setCount(String key, long value) {
        counters.put(key, value);
    }

    public synchronized void increment(String key) {
        counters.put(key, getCount(key) + 1);
    }

    public synchronized long getCount(String key) {
        return counters.getOrDefault(key, 0L);
    }

    public synchronized Map<String, Long> getAll() {
        return Collections.unmodifiableMap(new HashMap<>(counters));
    }

    /**
     * Ensure that deserialization returns the singleton instance instead of a
     * new object.
     */
    private Object readResolve() {
        return getInstance();
    }
}