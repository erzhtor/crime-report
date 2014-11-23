package com.erzhan.crimereport.API;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Erzhan on 22-Nov 14.
 */
public class MyConnection {
    private static MyConnection ourInstance = new MyConnection();

    public static MyConnection getInstance() {
        return ourInstance;
    }

    private MyConnection() {
    }

    public static JSONArray getJsonArrayOfCrimes() throws MyConnectionException {
        String url = Constants.crime_controller_url;

        return getJsonArray(url);
    }
    public static JSONArray getJsonArrayOfComments(int crime_id) throws MyConnectionException {
        String url = Constants.comment_controller_url;
        url += "?crime_id=" + crime_id;

        return getJsonArray(url);

    }
    private static JSONArray getJsonArray(String url) throws MyConnectionException {

        JSONArray jarray;
        StringBuilder builder = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = client.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
            } else {
                Log.e("==>", "Failed to download file");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            throw new MyConnectionException("Error");
        } catch (IOException e) {
            e.printStackTrace();
            throw new MyConnectionException("Error");
        }
        // Parse String to JSON object
        try {
            jarray = new JSONArray(builder.toString());
        } catch (JSONException e) {
            throw new MyConnectionException("Error");
        } // return JSON Object
        return jarray;
    }
    public static class MyConnectionException extends Exception
    {
        MyConnectionException(String msg)
        {
            super(msg);
        }
    }
}
