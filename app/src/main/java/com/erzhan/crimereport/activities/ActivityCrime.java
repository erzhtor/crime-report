package com.erzhan.crimereport.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.erzhan.crimereport.API.Constants;
import com.erzhan.crimereport.API.MyAsyncTask;
import com.erzhan.crimereport.API.MyJsonParser;
import com.erzhan.crimereport.R;
import com.erzhan.crimereport.classes.Comment;
import com.erzhan.crimereport.classes.Crime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ActivityCrime extends ActionBarActivity {

    private Crime crime;
    private ArrayList<Comment> comments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime);

        Bundle extra = getIntent().getExtras();
        String str = extra.getString(Constants.CrimeJsonObject);
        try {
            JSONObject json = new JSONObject(str);
            crime = MyJsonParser.getCrime(json);
//            Log.i(cri)
            MyAsyncTask task =
                    (MyAsyncTask) new MyAsyncTask(this).execute(
                            MyAsyncTask.GET_COMMENTS, crime.getId() + "");

            JSONArray jsonArray = task.get();

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_crime, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
