package com.erzhan.crimereport.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.erzhan.crimereport.R;
import com.erzhan.crimereport.classes.Comment;

import java.util.List;

/**
 * Created by Erzhan on 25-Nov 14.
 */
public class AdapterComments extends ArrayAdapter<Comment>{

    public AdapterComments(Context context, int resource, List<Comment> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.item_comment, parent, false);


        //set commentor name
        TextView textView = (TextView)v.findViewById(R.id.commentor);
        textView.setText(getItem(position).getCommentor_name());

        // set comment text
        textView = (TextView)v.findViewById(R.id.comment_text);
        textView.setText(getItem(position).getCommentText());

        return v;
    }
}
