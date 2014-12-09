package com.erzhan.crimereport.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.erzhan.crimereport.Message;
import com.erzhan.crimereport.R;
import com.erzhan.crimereport.classes.Crime;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAddCrime extends Fragment {

    private Crime crime;

    public EditText desciption;
    public Spinner category;
    public CheckBox police_report;
    public DatePicker date;
    public TimePicker time;

    private String[] categories;
    public FragmentAddCrime() {
        // Required empty public constructor

    }
    public void setCrime(Crime crime)
    {
        this.crime = crime;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_crime, container, false);

        categories = new String[]{getString(R.string.crime_category_1),
                getString(R.string.crime_category_2),
                getString(R.string.crime_category_3),
                getString(R.string.crime_category_4),
                getString(R.string.crime_category_5),
                getString(R.string.crime_category_6),
                getString(R.string.crime_category_7)};
        desciption = (EditText)v.findViewById(R.id.desc);

        category = (Spinner)v.findViewById(R.id.cat);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                                            android.R.layout.simple_spinner_item, categories);
        category.setAdapter(adapter);
        category.setSelection(0); //default item

        police_report = (CheckBox)v.findViewById(R.id.p_report);
        date = (DatePicker)v.findViewById(R.id.date);
        time = (TimePicker)v.findViewById(R.id.time);
        time.setIs24HourView(true); //format 24 hour

        if (crime != null)
        {
            desciption.setText(crime.getDescription());
            category.setSelection(crime.getCategory());
            police_report.setChecked((crime.getPoliceReport()==1)?true:false);

            //parse date and set
            String[] s = crime.getDate().split("-");
            int year = Integer.parseInt(s[0]); //
            int month = Integer.parseInt(s[1]); //
            int day = Integer.parseInt(s[2]); //
            date.updateDate(year, --month, day);

            //parse time and set
            s = crime.getTime().split(":");
            int hour = Integer.parseInt(s[0]);
            int minute = Integer.parseInt(s[1]);
            time.setCurrentHour(hour);
            time.setCurrentMinute(minute);
        }
        return v;
    }
}
