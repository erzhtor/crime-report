package com.erzhan.crimereport.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.erzhan.crimereport.R;
import com.erzhan.crimereport.classes.Crime;

import java.util.List;

/**
 * Created by Erzhan on 22-Nov 14.
 */
public class AdapterCrimes extends ArrayAdapter<Crime> {

    public AdapterCrimes(Context context, int resource, List<Crime> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.item_crime, parent, false);

        Crime crime = getItem(position);

        //set category
        TextView textView = (TextView)v.findViewById(R.id.category);
        textView.setText("" + crime.getCategory());

        //set Time
        textView = (TextView)v.findViewById(R.id.date_and_time);
       textView.setText(crime.getDate() + " " + crime.getTime());

        //set Description
        textView = (TextView)v.findViewById(R.id.desciption);
        textView.setText(crime.getDescription());

        //set Time ago
//        textView = (TextView)v.findViewById(R.id.desciption);
//        textView.setText(crime);

        return v;
    }
}
