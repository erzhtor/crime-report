package com.erzhan.crimereport.API;

import android.util.Log;

import com.erzhan.crimereport.classes.Comment;
import com.erzhan.crimereport.classes.Crime;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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

        JSONArray jarray = null;
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
            e.printStackTrace();
            throw new MyConnectionException("Error");
        } // return JSON Object
        return jarray;
    }
    public static void postComment(Comment comment) throws MyConnectionException {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(Constants.comment_controller_url);

        try {
            // add comment
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair(Constants.CRIME_ID,
                    comment.getCrime_id() + ""));
            nameValuePairs.add(new BasicNameValuePair(Constants.COMMENT_TEXT,
                    comment.getCommentText()));
            nameValuePairs.add(new BasicNameValuePair(Constants.COMMENTOR_NAME,
                    comment.getCommentor_name()));

            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            throw new MyConnectionException("ERROR");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new MyConnectionException("ERROR");
        }
    }
    public static void postCrime(Crime crime) throws MyConnectionException {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(Constants.crime_controller_url);

        try {
            // add comment
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair(Constants.DESCRIPTION,
                    crime.getDescription()));
            nameValuePairs.add(new BasicNameValuePair(Constants.CATEGORY,
                    crime.getCategory() + ""));
            nameValuePairs.add(new BasicNameValuePair(Constants.DATE,
                    crime.getDate()));
            nameValuePairs.add(new BasicNameValuePair(Constants.TIME,
                    crime.getTime()));
            nameValuePairs.add(new BasicNameValuePair(Constants.POLICE_REPORT,
                    crime.getPoliceReport() + ""));
            nameValuePairs.add(new BasicNameValuePair(Constants.LATITUDE,
                    crime.getLatitude() + ""));
            nameValuePairs.add(new BasicNameValuePair(Constants.LONGITUDE,
                    crime.getLongitude() + ""));

            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            throw new MyConnectionException("ERROR");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new MyConnectionException("ERROR");
        }
    }
    public static class MyConnectionException extends Exception
    {
        MyConnectionException(String msg)
        {
            super(msg);
        }
    }
}
