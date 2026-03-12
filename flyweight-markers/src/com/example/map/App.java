package com.example.map;

import java.util.List;

/**
 * Problem: Earlier, every map marker was storing its own style (color, shape, etc.). 
 * For thousands of markers, this was wasting a lot of memory because of many duplicate style objects.
 * 
 * Solution: We used the Flyweight Design Pattern. We separated the 'intrinsic' state (shared styles) 
 * from the 'extrinsic' state (lat, lng, label). We use a MarkerStyleFactory to cache and reuse 
 * these styles. Now, even with 30,000 markers, we only create a few style objects.
 */
public class App {

    public static void main(String[] args) {
        int n = 30_000;

        MapDataSource ds = new MapDataSource();
        List<MapMarker> markers = ds.loadMarkers(n);

        new MapRenderer().render(markers);

        System.out.println();
        System.out.println("Run QuickCheck to verify Flyweight sharing:");
        System.out.println("  java com.example.map.QuickCheck");
    }
}