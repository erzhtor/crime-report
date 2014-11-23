package com.erzhan.crimereport.activities;


import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.erzhan.crimereport.R;
import com.erzhan.crimereport.classes.Crime;
import com.erzhan.crimereport.API.MyAsyncTask;
import com.erzhan.crimereport.API.MyJsonParser;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.ExecutionException;


public class MainActivity extends FragmentActivity {

    private Stack<Fragment> fragmentStack = new Stack<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AsyncTask<Void, Void, JSONArray> myAsyncTask = new MyAsyncTask(this).execute();

        try {
            ArrayList<Crime> lst = MyJsonParser.parseArrayCrimes(myAsyncTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
    public void startFragmentCrimes()
    {
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
