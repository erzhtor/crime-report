package com.erzhan.crimereport.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.TextView;

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
    private JSONArray jsonArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime);

        Bundle extra = getIntent().getExtras();
        String str = extra.getString(Constants.CrimeJsonObject);
        try {
            JSONObject json = new JSONObject(str);
            Log.i("crime json", json.toString());
            this.crime = MyJsonParser.parseCrimeJson(json);

            setCrimeView();
            MyAsyncTask task =
                    (MyAsyncTask) new MyAsyncTask(this).execute(
                            MyAsyncTask.GET_COMMENTS, crime.getId() + "");

            jsonArray = task.get();
            this.comments = MyJsonParser.parseArrayComments(jsonArray);
            setCommentsView();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void setCrimeView()
    {
        TextView textView = (TextView)findViewById(R.id.crime_description);
        textView.setText(crime.getDescription());

        CheckedTextView checkBox = (CheckedTextView)findViewById(R.id.police_report);
        if (crime.getPoliceReport() == 1){
            checkBox.setChecked(true);
        }
        else
        {
            checkBox.setChecked(false);
        }
    }
    private void setCommentsView()
    {
        if (comments.isEmpty())
        {
            TextView textView = (TextView)findViewById(R.id.no_comments);
            textView.setVisibility(View.VISIBLE);
            return;
        }

//        for (int i = 0; i < comments.size(); ++i); listview

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
