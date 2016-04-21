package com.mapzen.android;

import com.mapzen.tangram.LngLat;
import com.mapzen.tangram.MapController;

import org.mockito.Mockito;

import android.content.Context;
import android.content.res.Resources;

import static org.mockito.Mockito.when;

public class TestMapController extends MapController {

    private double latitude = 0;
    private double longitude = 0;
    private float mapZoom = 0;
    private float mapRotation = 0;
    private float mapTilt = 0;

    public TestMapController() {
        super(getMockContext(), "");
    }

    @Override public void setPosition(LngLat lngLat, int duration) {
        longitude = lngLat.longitude;
        latitude = lngLat.latitude;
    }

    @Override public LngLat getPosition() {
        return new LngLat(longitude, latitude);
    }

    @Override public void setZoom(float zoom) {
        mapZoom = zoom;
    }

    @Override public float getZoom() {
        return mapZoom;
    }

    @Override public void setRotation(float radians) {
        mapRotation = radians;
    }

    @Override public float getRotation() {
        return mapRotation;
    }

    @Override public void setTilt(float radians) {
        mapTilt = radians;
    }

    @Override public float getTilt() {
        return mapTilt;
    }

    private static Context getMockContext() {
        final Context context = Mockito.mock(Context.class);
        when(context.getResources()).thenReturn(Mockito.mock(Resources.class));
        return context;
    }
}