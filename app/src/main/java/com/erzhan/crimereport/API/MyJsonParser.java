package com.erzhan.crimereport.API;

import com.erzhan.crimereport.classes.Crime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Erzhan on 18-Nov 14.
 */
public class MyJsonParser {
    public static ArrayList<Crime> parseArrayCrimes(JSONArray jsonArray) throws JSONException {
        ArrayList<Crime> list = new ArrayList<Crime>();

        for (int i = 0; i < jsonArray.length(); ++i)
        {
            list.add(getCrime(jsonArray.getJSONObject(i)));
        }

        return list;
    }
    public static Crime getCrime(JSONObject json) throws JSONException {
        Crime crime = new Crime();

        crime.setId(json.getInt(Constants.CRIME_ID));
        crime.setDescription(json.getString(Constants.DESCRIPTION));
        crime.setCategory(json.getInt(Constants.CATEGORY));
        crime.setDate(json.getString(Constants.DATE));
        crime.setTime(json.getString(Constants.TIME));
        crime.setPoliceReport(json.getInt(Constants.POLICE_REPORT));
        crime.setLatitude(json.getLong(Constants.LATITUDE));
        crime.setLongitude(json.getLong(Constants.LONGITUDE));

        return crime;
    }
}
