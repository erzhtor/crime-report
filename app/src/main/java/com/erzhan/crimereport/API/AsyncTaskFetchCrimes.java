package com.erzhan.crimereport.API;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.erzhan.crimereport.Message;
import com.erzhan.crimereport.activities.MainActivity;

import org.json.JSONArray;

/**
 * Created by Erzhan on 8-Nov 14.
 */
public class AsyncTaskFetchCrimes extends AsyncTask<Void, Void, JSONArray> {

    private Activity context;
    private ProgressDialog progressDialog;
    private boolean success = true;
    private JSONArray jsonArray = null;

    public AsyncTaskFetchCrimes(Activity context)
    {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("fetching crimes list from server...");
        progressDialog.show();

        super.onPreExecute();
    }

    @Override
    protected JSONArray doInBackground(Void... voids) {

        try {
            jsonArray = MyConnection.getJsonArrayOfCrimes();
//            Log.i("jsonArray", jsonArray.toString());
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
            ((MainActivity)context).setAsyncTaskFetchCrimesResult(o);
        }
    }


}
