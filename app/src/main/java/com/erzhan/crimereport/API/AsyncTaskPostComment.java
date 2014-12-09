package com.erzhan.crimereport.API;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;

import com.erzhan.crimereport.Message;
import com.erzhan.crimereport.activities.ActivityCrime;
import com.erzhan.crimereport.classes.Comment;

import org.json.JSONArray;

/**
 * Created by Erzhan on 26-Nov 14.
 */
public class AsyncTaskPostComment
        extends AsyncTask<Comment, Void, Void> {

    private Activity context;
    private ProgressDialog progressDialog;
    private boolean success = true;


    public AsyncTaskPostComment(Activity context)
    {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("posting comment ...");
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
    protected Void doInBackground(Comment... comment) {

        try {
            MyConnection.postComment(comment[0]);
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
            Message.message(context, "Sorry,\n Could not post comment :(");
        }
        else
        {
            Message.message(context, ":) Comment successfully added :)");
        }
    }
}
