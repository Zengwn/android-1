# Markers

To add a marker to the map, create a `Marker` object and call `MapzenMap#addMarker(Marker)`. Keep a reference to the returned `MapData` object and remove your markers from the map in `Activity#onDestroy()`.

```java
MapFragment mapFragment;
MapData markers;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sample_mapzen);

    MapFragment mapFragment = (MapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment);
    mapFragment.getMapAsync(new OnMapReadyCallback() {
        @Override public void onMapReady(MapzenMap map) {
            configureMap(map);
        }
    });
}

private void configureMap(MapzenMap map) {
    markers = map.addMarker(new Marker(-73.9903, 40.74433));
    map.addMarker(new Marker(-73.984770, 40.734807));
    map.addMarker(new Marker(-73.998674, 40.732172));
    map.addMarker(new Marker(-73.996142, 40.741050));
}

@Override protected void onDestroy() {
    super.onDestroy();
    markers.clear();
}
```
