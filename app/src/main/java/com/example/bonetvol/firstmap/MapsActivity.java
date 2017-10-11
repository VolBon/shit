package com.example.bonetvol.firstmap;

import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.database.sqlite.*;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public SQLiteDatabase mydatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mydatabase = openOrCreateDatabase("All here", MODE_PRIVATE, null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS Places(Lat FLOAT,Long FLOAT);");
        mydatabase.execSQL("INSERT INTO Places VALUES(50, 50);");
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        List<Integer> latlng = getDataFromDatabase();
        String name = "dupa";
        LatLng marker = new LatLng(latlng.get(0), latlng.get(1));
        mMap.addMarker(new MarkerOptions().position(marker).title("Marker in: " + name));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
    }

    public List<Integer> getDataFromDatabase(){
        //get lat, long, name and schedules (ints, strings)
        Cursor resultSet = mydatabase.rawQuery("Select * from Places",null);
        resultSet.moveToFirst();
        String lat = resultSet.getString(0);
        String lng = resultSet.getString(1);
        List<Integer> list = new ArrayList<Integer>();
        list.add(50);
        list.add(50);
        return list;
    }

}
