package com.erzhan.crimereport.API;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.erzhan.crimereport.Message;

import org.json.JSONArray;

/**
 * Created by Erzhan on 8-Nov 14.
 */
public class MyAsyncTask extends AsyncTask<String, Void, JSONArray> {

    private Context context;
    private ProgressDialog progressDialog;
    private boolean success = true;
    private JSONArray jsonArray = null;
    public static String GET_CRIMES = "GET_CRIMES";
    public static String GET_COMMENTS = "GET_COMMENTS";


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
    protected JSONArray doInBackground(String... strings) {

        try {
            if (strings[0] == GET_CRIMES)
                jsonArray = MyConnection.getJsonArrayOfCrimes();
            else if (strings[0] == GET_COMMENTS)
                jsonArray = MyConnection.getJsonArrayOfComments(Integer.parseInt(strings[1]));
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
