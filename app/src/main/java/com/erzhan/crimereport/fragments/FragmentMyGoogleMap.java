package com.erzhan.crimereport.fragments;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erzhan.crimereport.R;
import com.erzhan.crimereport.classes.Crime;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;

/**
 * MapView used instead MapFragment because of Custom Framgent implementation
 * https://gist.github.com/joshdholtz/4522551 - usage example
 * https://developers.google.com/maps/documentation/android/map#mapview - MapView
 */
public class FragmentMyGoogleMap extends Fragment {


    private MapView mapView;
    private GoogleMap mMap;
    private ArrayList<Crime> crimes = null;
    private float defaultZoom = 10f;
    private LatLng defaultLatLng = new LatLng(43.1, -87.9);

    public FragmentMyGoogleMap() {
        // Required empty public constructor
    }

    public void setCrimeList(ArrayList<Crime> crimes)
    {
        this.crimes = crimes;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_map, container, false);

        // Gets the MapView from the XML layout and creates it
        mapView = (MapView)v.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);

        // Gets to GoogleMap from the MapView and does initialization stuff
        mMap = mapView.getMap();
        mMap.getUiSettings().setMyLocationButtonEnabled(true);


        // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
        MapsInitializer.initialize(this.getActivity());
        // Updates the location and zoom of the MapView
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(defaultLatLng, defaultZoom);
        mMap.animateCamera(cameraUpdate);



        return v;
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

}
