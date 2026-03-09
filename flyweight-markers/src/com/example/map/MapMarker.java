package com.example.map;

/**
 * Represents a map marker containing **extrinsic state** plus a shared
 * intrinsic style object.  The style is produced by
 * {@link MarkerStyleFactory} and reused across markers with the same
 * configuration (flyweight pattern).
 */
public class MapMarker {

    private final double lat;
    private final double lng;
    private final String label;

    // shared intrinsic state
    private final MarkerStyle style;

    public MapMarker(double lat, double lng, String label, MarkerStyle style) {
        this.lat = lat;
        this.lng = lng;
        this.label = label;
        this.style = style;
    }

    public double getLat() { return lat; }
    public double getLng() { return lng; }
    public String getLabel() { return label; }
    public MarkerStyle getStyle() { return style; }
}