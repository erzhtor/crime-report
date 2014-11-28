package com.erzhan.crimereport.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.erzhan.crimereport.API.Constants;
import com.erzhan.crimereport.R;
import com.erzhan.crimereport.activities.MainActivity;
import com.erzhan.crimereport.classes.Crime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erzhan on 22-Nov 14.
 */
public class AdapterCrimes extends ArrayAdapter<Crime> {

    private final int textLimit = 100;
    private ArrayList<Integer> crimeIds;

    public void setCrimeIds(ArrayList<Integer> crimeIds) {
        this.crimeIds = crimeIds;
    }

    public AdapterCrimes(Context context, int resource,
                         List<Crime> objects, ArrayList<Integer> indices) {
        super(context, resource, objects);
        this.crimeIds = indices;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.item_crime, parent, false);

        //in order to make newest top
        final int position = getCount() - pos - 1;

        Crime crime = getItem(position);

        //set category
        TextView textView = (TextView)v.findViewById(R.id.category);
        String category = getContext().getResources().getString(
                Constants.getCrimeCategoryStringID(crime.getCategory()));
        textView.setText(category);

        //set Time
        textView = (TextView)v.findViewById(R.id.date_and_time);
        textView.setText(crime.getDate() + "\n" + crime.getTime());

        //set Description
        textView = (TextView)v.findViewById(R.id.desciption);
        String text = crime.getDescription();
        if (text.length() > textLimit)
        {
            text = text.substring(0, textLimit);
            text +=" ...";
        }
        textView.setText(text);

        //set onclick
        textView = (TextView)v.findViewById(R.id.read_more);
        if (crimeIds == null)
            textView.setId(position);
        else
            textView.setId(crimeIds.get(position));
        textView.setOnClickListener(((MainActivity)getContext()));

        return v;
    }

}
