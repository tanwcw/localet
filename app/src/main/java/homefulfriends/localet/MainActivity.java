package homefulfriends.localet;

import android.app.Activity;
import android.os.Bundle;

import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapquest.mapping.maps.OnMapReadyCallback;
import com.mapquest.mapping.maps.MapboxMap;
import com.mapquest.mapping.maps.MapView;

public class MainActivity extends Activity {
    private final LatLng SAN_FRAN = new LatLng(37.775643, -122.386743);
    private final LatLng COLT = new LatLng(37.802378, -122.405823);
    private MapboxMap mMapboxMap;
    private MapView mMapView;

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
                mMapboxMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SAN_FRAN, 15));
                addMarker(mMapboxMap);
                addMarker2(mMapboxMap);
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
        markerOptions.position(COLT);
        markerOptions.title("Colt Tower");
        markerOptions.snippet("Colt Tower");
        mapboxMap.addMarker(markerOptions);
    }
}