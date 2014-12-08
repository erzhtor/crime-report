package com.erzhan.crimereport.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.erzhan.crimereport.R;
import com.erzhan.crimereport.fragments.FragmentAddCrime;
import com.erzhan.crimereport.fragments.FragmentMyGoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.Stack;

public class ActivityAddCrime extends ActionBarActivity implements View.OnClickListener {


    private static final int SLIDE_IN_FROM_LEFT = 0;
    private static final int SLIDE_IN_FROM_RIGHT = 1;
    private static final int NO_ANIM = -1;

    public LatLng pos;
    private Button next;
    private Button back;
    private Stack<Fragment> fragmentStack = new Stack<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_crime);

        back = (Button)findViewById(R.id.back);
        back.setOnClickListener(this);
        next = (Button)findViewById(R.id.next);
        next.setOnClickListener(this);

        FragmentMyGoogleMap f = new FragmentMyGoogleMap();
        f.setIsAddCrime(true);
        showFragment(f, NO_ANIM);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_add_crime, menu);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home)
        {
            onBackPressed();
        }
//        else if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
    private void showFragment(Fragment f, int animCase)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (animCase)
        {
            //fragmentMap
            case SLIDE_IN_FROM_LEFT:
                transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);
                break;
            //fragmentCrimeList
            case SLIDE_IN_FROM_RIGHT:
                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right);
                break;
        }
        transaction.replace(R.id.container, f);
        transaction.commitAllowingStateLoss();
        fragmentStack.add(f);

        if (fragmentStack.size() == 1)
        {
            back.setText(getString(R.string.cancel));
            next.setText(getString(R.string.next));
        }
        else {
            back.setText(getString(R.string.back));
            next.setText(getString(R.string.send));
        }
    }

    @Override
    public void onBackPressed() {
        if (fragmentStack.size() <= 1) {
            super.onBackPressed();
            return;
        }
        else{
            fragmentStack.pop();
            showFragment(fragmentStack.pop(), SLIDE_IN_FROM_RIGHT);
            return;
        }
    }

    @Override
    public void onClick(View view) {

        switch(view.getId())
        {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.next:
                FragmentAddCrime f = new FragmentAddCrime();
                showFragment(f, SLIDE_IN_FROM_LEFT);
                break;
        }
    }
}
