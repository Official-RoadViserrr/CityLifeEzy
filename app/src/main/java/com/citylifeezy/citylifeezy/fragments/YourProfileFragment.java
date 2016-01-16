package com.citylifeezy.citylifeezy.fragments;


// This is the Home Fragment //


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.citylifeezy.citylifeezy.activity.R;


/**
 * Created by tanvirsourov on 5/10/15.
 */
public class YourProfileFragment extends Fragment {



    private static Context mContext;


    public static YourProfileFragment newInstance(Context context) {
        YourProfileFragment f = new YourProfileFragment();
        Bundle args = new Bundle();
        args.putString("index", context.toString());
        f.setArguments(args);

        mContext = context;
        return f;
    }


    String dummy;

    View rootview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootview = inflater.inflate(R.layout.fragment_your_profile, container, false);




        Spinner dropdown = (Spinner) rootview.findViewById(R.id.spinner1);

        Spinner dropdown_Prof = (Spinner) rootview.findViewById(R.id.spinnerProfession);

        String[] items = new String[]{"Male", "Female"};

        String[] professions = new String[]{"Student","Job Holder","Business","Others"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.trends_spinner_list, items);

        ArrayAdapter<String> adapter_2 = new ArrayAdapter<String>(getActivity(), R.layout.trends_spinner_list, professions);

        dropdown.setAdapter(adapter);

        dropdown_Prof.setAdapter(adapter_2);


        return rootview;


    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        YourProfileFragment myListFragment = new YourProfileFragment();

        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        fragmentTransaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout, R.anim.fadein, R.anim.fadeout);

        fragmentTransaction.show(myListFragment);

        fragmentTransaction.commit();

    }



    Button myButton;

    int counter = 0;



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        // get the button view

        myButton = (Button) getView().findViewById(R.id.submit);


        // set a onclick listener for when the button gets clicked
        myButton.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {

                Toast.makeText(mContext, "Your Registration has been successfully completed!",
                        Toast.LENGTH_LONG).show();

            }

        });

    }

}

