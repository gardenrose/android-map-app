package com.example.icebear.mapp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.icebear.mapp.station;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference();
        ref.addValueEventListener(new ValueEventListener() {
            HashMap<String,Marker> markers= new HashMap<>();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren()) {
                    station s = child.getValue(station.class);
                    Marker m=markers.get(s.getName());
                    if(m!=null){
                        m.setSnippet(s.getSensorHumidity() + " " + s.getSensorTemperature() + " " + s.getSensorWindSpeed());
                        if(m.isInfoWindowShown()==true){
                            m.hideInfoWindow();
                            m.showInfoWindow();
                        }

                    }
                    else{
                        markers.put(s.getName(),mMap.addMarker(new MarkerOptions().title(s.getName()).position(new LatLng(s.getLat(),s.getLng())).snippet(s.getSensorHumidity() + " " + s.getSensorTemperature() + " " + s.getSensorWindSpeed())));
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
