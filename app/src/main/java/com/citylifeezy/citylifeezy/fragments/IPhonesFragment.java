package com.citylifeezy.citylifeezy.fragments;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.citylifeezy.citylifeezy.ImportantPhoneDetailActivity;
import com.citylifeezy.citylifeezy.adapters.MyAdapter;
import com.citylifeezy.citylifeezy.activity.R;

/**
 * Created by Asif on 8/24/2015.
 */

public class IPhonesFragment extends android.support.v4.app.Fragment {


    private static Context mContext;
    LinearLayout llEmergency, llOthers;
    TextView tvEmergency, tvOthers;
    ListView listview;

    final String[] titles = {"RAB", "Ambulance Service", "Hospitals", "WASA Emergency Water Delivery",
            "Dhaka Police Thana", "Blood Banks", "Fire Brigades & Services"};

    public static IPhonesFragment newInstance(Context context) {
        IPhonesFragment f = new IPhonesFragment();
        Bundle args = new Bundle();
        args.putString("index", context.toString());
        f.setArguments(args);

        mContext = context;
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_important_phones, container, false);

        return rootview;


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
    }

    private void initUI(View view){
        tvEmergency = (TextView) view.findViewById(R.id.tvEmergency);
        tvOthers = (TextView) view.findViewById(R.id.tvOthes);
        llEmergency = (LinearLayout) view.findViewById(R.id.llEmergency);
        llOthers = (LinearLayout) view.findViewById(R.id.llOthers);

        tvEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTabLayout(tvEmergency,llEmergency,tvOthers, llOthers);
            }
        });

        tvOthers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTabLayout(tvOthers,llOthers,tvEmergency,llEmergency);
            }
        });

        ListAdapter theAdapter = new MyAdapter(getActivity(), titles);
        listview = (ListView) view.findViewById(R.id.listView);
        listview.setAdapter(theAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("Position >>", titles[position]);
                String cheese = titles[position];
                Intent intent = new Intent(getActivity(), ImportantPhoneDetailActivity.class);
                intent.putExtra("intent_name", cheese);
                startActivity(intent);
            }
        });
    }

    private void setTabLayout(TextView tvSet, LinearLayout llSet, TextView tvUnset, LinearLayout llUnset){
        tvSet.setLayoutParams(getParams(true,tvSet));
        llSet.setVisibility(View.VISIBLE);
        tvUnset.setLayoutParams(getParams(false, tvUnset));
        llUnset.setVisibility(View.INVISIBLE);
    }

    private LinearLayout.LayoutParams getParams(boolean flag, TextView tv){
        if(flag){
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)tv.getLayoutParams();
            params.setMargins(0, 0, 0, 5);
            return params;
        }else{
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)tv.getLayoutParams();
            params.setMargins(0, 0, 0, 0);
            return params;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

    }

}
