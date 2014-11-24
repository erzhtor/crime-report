package com.erzhan.crimereport.fragments;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.erzhan.crimereport.R;
import com.erzhan.crimereport.activities.MainActivity;
import com.erzhan.crimereport.adapters.AdapterCrimes;
import com.erzhan.crimereport.classes.Crime;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class FragmentCrimes extends Fragment {

    public void setCrimes(ArrayList<Crime> crimes) {
        this.crimes = crimes;
    }

    private ArrayList<Crime> crimes = null;
    private ListView listView = null;
    private AdapterCrimes adapter;
    public FragmentCrimes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_crimes_list, container, false);

        if (crimes != null)
        {
            listView = (ListView)v.findViewById(R.id.crimesList);

            adapter = new AdapterCrimes(getActivity(), R.layout.item_crime, crimes);
            listView.setAdapter(adapter);

        }

        return v;
    }

    public boolean reloadListView(ArrayList<Crime> crimes)
    {
        this.crimes = crimes;
        if (adapter != null)
        {
            adapter.clear();
            adapter.addAll(this.crimes);
            adapter.notifyDataSetChanged();
            return true;
        }
        return false;
    }
}
