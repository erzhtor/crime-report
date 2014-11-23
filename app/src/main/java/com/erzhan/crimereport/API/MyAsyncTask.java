package com.erzhan.crimereport.API;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.erzhan.crimereport.Message;

import org.json.JSONArray;

/**
 * Created by Erzhan on 8-Nov 14.
 */
public class MyAsyncTask extends AsyncTask<Void, Void, JSONArray> {

    private Context context;
    private ProgressDialog progressDialog;
    private boolean success = true;
    private JSONArray jsonArray = null;

    public MyAsyncTask(Context context)
    {
        this.context = context;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("fetching data from server...");
        progressDialog.show();
    }

    @Override
    protected JSONArray doInBackground(Void... voids) {

        try {
            jsonArray = MyConnection.getJsonArrayOfCrimes(context);
        } catch (MyConnection.MyConnectionException e) {
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
            Message.message(context, "Sorry,\n Some ERROR ocurred :(");
        }
    }
}
