package com.mapzen.android;

import com.mapzen.android.dagger.DI;
import com.mapzen.android.model.MapStyle;
import com.mapzen.tangram.MapController;

import javax.inject.Inject;

import static com.mapzen.android.MapStyleToSceneFile.MAP_STYLE_TO_SCENE_FILE;

/**
 * Class responsible for initializing the map.
 */
public class MapInitializer {

  @Inject TileHttpHandler httpHandler;

  /**
   * Creates a new instance.
   */
  @Inject MapInitializer() {
    DI.component().inject(this);
  }

  /**
   * Initialize map for the current {@link MapView} and notify via {@link OnMapReadyCallback}.
   */
  public void init(final MapView mapView, final OnMapReadyCallback callback) {
    init(mapView, MapStyle.BUBBLE_WRAP, callback);
  }

  /**
   * Initialize map for current {@link MapView} and {@link MapStyle} before notifying via
   * {@link OnMapReadyCallback}.
   */
  public void init(final MapView mapView, MapStyle mapStyle, final OnMapReadyCallback callback) {
    loadMap(mapView, mapStyle, callback);
  }

  /**
   * Initialize map for the current {@link MapView} with given API key and notify via
   * {@link OnMapReadyCallback}.
   */
  public void init(final MapView mapView, String key, final OnMapReadyCallback callback) {
    httpHandler.setApiKey(key);
    loadMap(mapView, MapStyle.BUBBLE_WRAP, callback);
  }

  /**
   * Initialize map for the current {@link MapView} with given API key and notify via
   * {@link OnMapReadyCallback}.
   */
  public void init(final MapView mapView, String key, MapStyle mapStyle,
      final OnMapReadyCallback callback) {
    httpHandler.setApiKey(key);
    loadMap(mapView, mapStyle, callback);
  }

  private TangramMapView getTangramView(final MapView mapView) {
    return mapView.getTangramMapView();
  }

  private void loadMap(final MapView mapView, MapStyle mapStyle,
      final OnMapReadyCallback callback) {
    getTangramView(mapView).getMapAsync(new com.mapzen.tangram.MapView.OnMapReadyCallback() {
      @Override public void onMapReady(MapController mapController) {
        mapController.setHttpHandler(httpHandler);
        callback.onMapReady(
            new MapzenMap(mapController, new OverlayManager(mapView, mapController)));
      }
    }, MAP_STYLE_TO_SCENE_FILE.get(mapStyle));
  }
}
