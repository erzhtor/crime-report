package com.erzhan.crimereport.activities;


import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.erzhan.crimereport.API.Constants;
import com.erzhan.crimereport.Message;
import com.erzhan.crimereport.R;
import com.erzhan.crimereport.classes.Crime;
import com.erzhan.crimereport.API.MyAsyncTask;
import com.erzhan.crimereport.API.MyJsonParser;
import com.erzhan.crimereport.fragments.FragmentCrimes;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.ExecutionException;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private Stack<Fragment> fragmentStack = new Stack<Fragment>();
    private ArrayList<Crime> crimes;
    private JSONArray crimesJson;
    private FragmentCrimes fragmentCrimes;
    private MyAsyncTask myAsyncTask;
    public boolean isInternetAvailable()
    {
        ConnectivityManager cm =
                (ConnectivityManager)getSystemService(this.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }
    public void setMyAsyncTaskResult(JSONArray jsonArray)
    {
        if (isInternetAvailable()) {

            try {
                this.crimesJson = jsonArray;
                if (crimesJson != null) {
                    crimes = MyJsonParser.parseArrayCrimes(crimesJson);
                    //if first initialization
                    if (fragmentCrimes != null) {
                        fragmentCrimes = new FragmentCrimes();
                        fragmentCrimes.setCrimes(crimes);
                        showFragment(fragmentCrimes );
                    }
                    //if reload
                    else{
                        fragmentCrimes.reloadListView(crimes);
                    }

                } else {
                    //error
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else
        {
            Message.message(this,
                    getResources().getString(R.string.internet_not_available));
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myAsyncTask = (MyAsyncTask) new MyAsyncTask(this).execute(MyAsyncTask.GET_CRIMES);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.reload)
        {
            myAsyncTask.execute(MyAsyncTask.GET_CRIMES);
            return true;
        }
        else if (id == R.id.add_report) {
            return true;
        }
        else if (id == R.id.action_about)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    private void showFragment(Fragment f)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, f);
        transaction.commitAllowingStateLoss();

        fragmentStack.add(f);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    public void onClick(View view) {

        Intent intent = new Intent(this, ActivityCrime.class);
        int index = view.getId();
        //put crime to intent

        try {
            intent.putExtra(Constants.CrimeJsonObject, crimesJson.get(index).toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        startActivity(intent);
    }
}
