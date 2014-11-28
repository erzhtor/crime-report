package com.erzhan.crimereport.fragments;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

import com.erzhan.crimereport.R;
import com.erzhan.crimereport.adapters.AdapterCrimes;
import com.erzhan.crimereport.classes.Crime;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class FragmentCrimes extends Fragment implements SearchView.OnQueryTextListener {

    public void setAllCrimes(ArrayList<Crime> allCrimes) {
        this.allCrimes = allCrimes;
    }

    private ArrayList<Crime> allCrimes = null;
    private ArrayList<Crime> crimes = null;

    private ListView listView = null;
    private AdapterCrimes adapter;
    private SearchView searchView;

    public FragmentCrimes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_crimes_list, container, false);

        if (allCrimes != null)
        {
            listView = (ListView)v.findViewById(R.id.crimesList);

            adapter = new AdapterCrimes(getActivity(), R.layout.item_crime, getCopyOfArray(allCrimes), null);
            listView.setAdapter(adapter);

            searchView = (SearchView)v.findViewById(R.id.searchCrimes);
            searchView.setOnQueryTextListener(this);

        }

        return v;
    }

    public boolean reloadListView(ArrayList<Crime> crimes)
    {
        this.allCrimes = crimes;
        if (adapter != null)
        {
            adapter.clear();
            adapter.addAll(getCopyOfArray(allCrimes));
            adapter.setCrimeIds(null);
            adapter.notifyDataSetChanged();

            //clear search text
            searchView.setQuery(null, false);
            //disable searchview focus
            searchView.clearFocus();
            return true;
        }
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        //filter
        adapter.clear();
        Log.i("size", allCrimes.size() + "_");
        if (s == null || s.isEmpty())
        {
            adapter.addAll(allCrimes);
            adapter.setCrimeIds(null);
        }
        else
        {
            crimes = new ArrayList<Crime>();
            ArrayList<Integer> crimeIds = new ArrayList<Integer>();
            for (int i = 0; i < allCrimes.size(); ++i)
            {

                if (allCrimes.get(i).getDescription().contains(s)) {
                    crimes.add(allCrimes.get(i));
                    crimeIds.add(i);
                }
            }
            adapter.setCrimeIds(crimeIds);
            adapter.addAll(crimes);
        }
        adapter.notifyDataSetChanged();
        return false;
    }
    private ArrayList<Crime> getCopyOfArray(ArrayList<Crime> crimes)
    {
        ArrayList<Crime> list = new ArrayList<Crime>();

        for (Crime crime: crimes)
        {
            list.add(crime);
        }
        return list;
    }
}
