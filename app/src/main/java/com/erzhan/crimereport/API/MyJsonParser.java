package com.erzhan.crimereport.API;

import com.erzhan.crimereport.classes.Comment;
import com.erzhan.crimereport.classes.Crime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Erzhan on 24-Nov 14.
 */
public class MyJsonParser {
    private static MyJsonParser ourInstance = new MyJsonParser();

    public static MyJsonParser getInstance() {
        return ourInstance;
    }

    private MyJsonParser() {
    }
    public static ArrayList<Crime> parseArrayCrimes(JSONArray jsonArray) throws JSONException {
        ArrayList<Crime> list = new ArrayList<Crime>();

        for (int i = 0; i < jsonArray.length(); ++i)
        {
            list.add(parseCrimeJson(jsonArray.getJSONObject(i)));
        }

        return list;
    }
    public static ArrayList<Comment> parseArrayComments(JSONArray jsonArray) throws JSONException {

        ArrayList<Comment> list = new ArrayList<Comment>();

        for (int i = 0; i < jsonArray.length(); ++i)
        {
            list.add(parseCommentJson(jsonArray.getJSONObject(i)));
        }

        return list;
    }
    public static Comment parseCommentJson(JSONObject json) throws JSONException {
        Comment comment = new Comment();

        comment.setCrime_id(json.getInt(Constants.CRIME_ID));
        comment.setCommentor_name(json.getString(Constants.COMMENTOR_NAME));
        comment.setCommentText(json.getString(Constants.COMMENT_TEXT));

        return comment;
    }
    public static Crime parseCrimeJson(JSONObject json) throws JSONException {
        Crime crime = new Crime();

        crime.setId(json.getInt(Constants.CRIME_ID));
        crime.setDescription(json.getString(Constants.DESCRIPTION));
        crime.setCategory(json.getInt(Constants.CATEGORY));
        crime.setDate(json.getString(Constants.DATE));
        crime.setTime(json.getString(Constants.TIME));
        crime.setPoliceReport(json.getInt(Constants.POLICE_REPORT));
        crime.setLatitude((float)json.getDouble(Constants.LATITUDE));
        crime.setLongitude((float)json.getDouble(Constants.LONGITUDE));
//
//        Log.i("id", crime.getId() + "");
//        Log.i("desc", crime.getDescription());
//        Log.i("cat", crime.getCategory() + "");
//        Log.i("date", crime.getDate());
//        Log.i("time", crime.getTime());
//        Log.i("polic", crime.getPoliceReport() + "");
//        Log.i("lat", crime.getLatitude() + "");
//        Log.i("long", crime.getLongitude() + "");

        return crime;
    }
}
