package com.erzhan.crimereport.API;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;

import com.erzhan.crimereport.Message;
import com.erzhan.crimereport.activities.ActivityAddCrime;
import com.erzhan.crimereport.classes.Crime;

/**
 * Created by eragon on 12/9/14.
 */
public class AsyncTaskPostCrime extends AsyncTask<Crime, Void, Void> {


    private Activity context;
    private ProgressDialog progressDialog;
    private boolean success = true;


    public AsyncTaskPostCrime(Activity context)
    {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("posting crime ...");
        progressDialog.show();
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
//                Log.i("asynTask", "canceled - ");
                cancel(true);
            }
        });
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Crime... crime) {

        try {
            MyConnection.postCrime(crime[0]);
        } catch (MyConnection.MyConnectionException e) {
            success = false;
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void o) {
        super.onPostExecute(o);
        progressDialog.dismiss();

        if (!success) // look for standard solution
        {
            Message.message(context, "Sorry,\n Could not post crime :(");
        }
        else
        {
            Message.message(context, ":) crime successfully added :)");
            ((ActivityAddCrime)context).finish();
        }
    }
}
