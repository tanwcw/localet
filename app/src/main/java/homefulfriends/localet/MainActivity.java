package homefulfriends.localet;

import android.app.Activity;
import android.hardware.camera2.CameraManager;
import android.content.Context;
import android.os.Bundle;

import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapquest.mapping.maps.OnMapReadyCallback;
import com.mapquest.mapping.maps.MapboxMap;
import com.mapquest.mapping.maps.MapView;

public class MainActivity extends Activity {
    CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mMapView = (MapView) findViewById(R.id.mapquestMapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {
                mMapboxMap = mapboxMap;
                mMapboxMap.moveCamera(CameraUpdateFactory.newLatLngZoom(GOLDEN, 12));
                addMarker(mMapboxMap);
                addMarker2(mMapboxMap);
                addMarker3(mMapboxMap);
            }
        });
    }

    private void addMarker(MapboxMap mapboxMap) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SAN_FRAN);
        markerOptions.title("Place of rainbows and unicorns");
        markerOptions.snippet("Welcome to SF DISRUPT!");
        mapboxMap.addMarker(markerOptions);
    }

    private void addMarker2(MapboxMap mapboxMap) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(COIT);
        markerOptions.title("Coit Tower");
        markerOptions.snippet("Coit Tower");
        mapboxMap.addMarker(markerOptions);
    }

    private void addMarker3(MapboxMap mapboxMap) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(GOLDEN);
        markerOptions.title("Golden Gate Bridge");
        markerOptions.snippet("Is it really golden?");
        mapboxMap.addMarker(markerOptions);
    }
}