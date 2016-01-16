package com.citylifeezy.citylifeezy.fragments;


// This is the Home Fragment //


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.citylifeezy.citylifeezy.activity.R;


/**
 * Created by tanvirsourov on 5/10/15.
 */
public class FeedbackFragment extends Fragment {



    private static Context mContext;


    public static FeedbackFragment newInstance(Context context) {
        FeedbackFragment f = new FeedbackFragment();
        Bundle args = new Bundle();
        args.putString("index", context.toString());
        f.setArguments(args);

        mContext = context;
        return f;
    }



    View rootview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootview = inflater.inflate(R.layout.fragment_feedback, container, false);

        return rootview;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        FeedbackFragment myListFragment = new FeedbackFragment();

        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        fragmentTransaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout, R.anim.fadein, R.anim.fadeout);

        fragmentTransaction.show(myListFragment);

        fragmentTransaction.commit();


    }




    Button buttonSend;
    EditText textTitle, textSubject, textMessage;

    Button myButton;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        // get the button view

        buttonSend = (Button) getActivity().findViewById(R.id.buttonSend);
        textMessage = (EditText) getActivity().findViewById(R.id.editTextMessage);

        buttonSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String subject = "CityLifeEzy - User Feedback";

                String message = textMessage.getText().toString();

                String fullMessage = "Feedback: " + message;

                String to[] = {"citylifeezy@gmail.com"};
                String cc[] = {"shahriar@cmtechbd.com"};
                String bcc[] = {"shahriar@cmtechbd.com"};

                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

                String aEmailList[] = to;
                String aEmailCCList[] = cc;
                String aEmailBCCList[] = bcc;


                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, aEmailList);
                emailIntent.putExtra(android.content.Intent.EXTRA_CC, aEmailCCList);
                emailIntent.putExtra(android.content.Intent.EXTRA_BCC, aEmailBCCList);
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                        subject);

                emailIntent.setType("plain/text");
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, fullMessage);

                startActivity(emailIntent);
            }

        });

    }
}