package com.erzhan.crimereport.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.erzhan.crimereport.API.AsyncTaskFetchComments;
import com.erzhan.crimereport.API.Constants;
import com.erzhan.crimereport.API.AsyncTaskFetchCrimes;
import com.erzhan.crimereport.API.MyJsonParser;
import com.erzhan.crimereport.R;
import com.erzhan.crimereport.adapters.AdapterComments;
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
    private AsyncTaskFetchComments asyncTaskFetchComments;
    private LinearLayout commentsCont;
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

            //set Comments
            asyncTaskFetchComments = (AsyncTaskFetchComments)
                    new AsyncTaskFetchComments(this).execute(crime.getId());

            setCrimeView();
        } catch (JSONException e) {
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
    public void setCommentsView(JSONArray jsonArray)
    {
        this.jsonArray = jsonArray;
        try {
            this.comments = MyJsonParser.parseArrayComments(this.jsonArray);

            //no comments
            TextView textView = (TextView) findViewById(R.id.no_comments);
            if (comments.isEmpty()) {
                textView.setVisibility(View.VISIBLE);
                return;
            }
            //firs load
            else if (commentsCont == null) {
                textView.setVisibility(View.GONE);

                commentsCont = (LinearLayout)findViewById(R.id.comments_cont);
                for (int i = 0; i < comments.size(); ++i) {
                    commentsCont.addView(getCommentView(comments.get(i)));
                }
            }
            //reload
            else
            {
                textView.setVisibility(View.GONE);

                commentsCont.removeAllViews();
                for (int i = 0; i < comments.size(); ++i) {
                    commentsCont.addView(getCommentView(comments.get(i)));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private View getCommentView(Comment comment)
    {
        View v = getLayoutInflater().inflate(R.layout.item_comment, null);

        TextView textView = (TextView)v.findViewById(R.id.commentor);
        textView.setText(comment.getCommentor_name());

        // set comment text
        textView = (TextView)v.findViewById(R.id.comment_text);
        textView.setText(comment.getCommentText());

        return v;
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
        if (id == R.id.action_reload_comments)
        {
            asyncTaskFetchComments = new AsyncTaskFetchComments(this);
            asyncTaskFetchComments.execute(crime.getId());
            return true;
        }
        else if (id == R.id.add_comment) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}