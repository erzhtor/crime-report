package com.erzhan.crimereport.fragments;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erzhan.crimereport.API.Constants;
import com.erzhan.crimereport.Message;
import com.erzhan.crimereport.R;
import com.erzhan.crimereport.activities.MainActivity;
import com.erzhan.crimereport.classes.Crime;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * MapView used instead MapFragment because of Custom Framgent implementation
 * https://gist.github.com/joshdholtz/4522551 - usage example
 * https://developers.google.com/maps/documentation/android/map#mapview - MapView
 * http://mobiforge.com/design-development/developing-with-google-maps-v2-android
 */
public class FragmentMyGoogleMap extends Fragment {


    private MapView mapView;
    private GoogleMap mMap;
    private ArrayList<Crime> crimes = null;

    private float defaultZoom = 13f;
    private LatLng defaultLatLng = new LatLng(42.87973793695002, 74.60605144500732);

    private int focusedCrime = -1;
    private final char splitChar = '|';
    boolean firstLoad = true;
    public FragmentMyGoogleMap() {
        // Required empty public constructor
    }
    /**
     * shows infoWindow of mapFragment
     * @param crimeIndex index of crime in ArrayList
     */
    public void focusOnCrime(int crimeIndex)
    {
        this.focusedCrime = crimeIndex;
        this.defaultLatLng = new LatLng(crimes.get(focusedCrime).getLatitude(),
                crimes.get(focusedCrime).getLongitude());
    }
    public void setCrimeList(ArrayList<Crime> crimes)
    {
        this.crimes = crimes;
    }
    public void setCrimeMarkers()
    {
        if (mMap != null && crimes != null)
        {
            mMap.clear();
            for (int i = 0; i < crimes.size(); ++i)
            {
                Crime c = crimes.get(i);
                String crimeType = getActivity().getResources().
                        getString(Constants.getCrimeCategoryStringID(c.getCategory()));
                String title = crimeType + " " + splitChar + " ID=" + i;
                MarkerOptions markerOptions =
                        new MarkerOptions().
                                position(new LatLng(c.getLatitude(), c.getLongitude())).
                                title(title).snippet(c.getDescription());

                Marker marker = mMap.addMarker(markerOptions);
                if (focusedCrime == i)
                    marker.showInfoWindow();
                else
                    marker.hideInfoWindow();
            }

            mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {
                    int index = Integer.parseInt(
                            marker.getTitle().split("\\|")[1].split("\\=")[1]);
                    ((MainActivity)getActivity()).startCrimeActivity(index);
//                    Message.message(getActivity(), index + "=") ;
//                    Log.i("log", marker.getTitle().split("\\|")[1]);

                }
            });
        }
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

        if (firstLoad) {
            mMap.animateCamera(cameraUpdate);
            firstLoad = false;
        }
        else {
            mMap.moveCamera(cameraUpdate);
        }

        setCrimeMarkers();

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
