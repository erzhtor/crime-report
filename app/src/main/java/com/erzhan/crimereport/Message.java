package com.erzhan.crimereport;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Erzhan on 8-Nov 14.
 */
public class Message {
    public static void message(Context context, String text)
    {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }
}
