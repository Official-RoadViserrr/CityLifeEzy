package com.citylifeezy.citylifeezy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.citylifeezy.citylifeezy.activity.R;

/**
 * Created by Asif on 8/31/2015.
 */
public class MyListAdapter2 extends ArrayAdapter<String> {
    public MyListAdapter2(Context context, String[] values) {
        super(context, R.layout.mark, values);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        LayoutInflater theInflater = LayoutInflater.from(getContext());
        View theView = theInflater.inflate(R.layout.custom_row, parent, false);

        String tv = getItem(position);
        TextView theTextView = (TextView) theView.findViewById(R.id.textView6);

        theTextView.setText(tv);

        TextView theTextView1 = (TextView) theView.findViewById(R.id.textView7);

        theTextView1.setText(tv);

        //ImageView theImageView = (ImageView) theView.findViewById(R.id.imageView);
        //theImageView.setImageResource(R.drawable.phn);

        return theView;
    }
}
