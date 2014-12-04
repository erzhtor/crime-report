package com.erzhan.crimereport.API;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;

import com.erzhan.crimereport.Message;
import com.erzhan.crimereport.activities.ActivityCrime;
import com.erzhan.crimereport.activities.MainActivity;

import org.json.JSONArray;

/**
 * Created by Erzhan on 24-Nov 14.
 */
public class AsyncTaskFetchComments extends AsyncTask<Integer, Void, JSONArray>{

    private Activity context;
    private ProgressDialog progressDialog;
    private boolean success = true;
    private JSONArray jsonArray = null;


    public AsyncTaskFetchComments(Activity context)
    {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("fetching comment list from server...");
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
    protected JSONArray doInBackground(Integer... id) {

        try {
            jsonArray = MyConnection.getJsonArrayOfComments(id[0]); //by crime_Id
        } catch (MyConnection.MyConnectionException e) {
            success = false;
            e.printStackTrace();
        }
        return jsonArray;
    }

    @Override
    protected void onPostExecute(JSONArray o) {
        super.onPostExecute(o);
        progressDialog.dismiss();

        if (!success) // look for standard solution
        {
            Message.message(context, "Sorry,\n Unable to connect to server :(");
        }
        else if (o == null)
        {
            Message.message(context, "Sorry,\n SOME ERROR OCCURED\n (JSONARRAY == NULL) :(");
        }

        else
        {
            ((ActivityCrime)context).setCommentsView(o);
        }
    }


}
