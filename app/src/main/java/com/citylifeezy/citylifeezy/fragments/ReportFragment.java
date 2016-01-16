package com.citylifeezy.citylifeezy.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.citylifeezy.citylifeezy.activity.R;
import com.citylifeezy.citylifeezy.fragments.TrafficFragment;
import com.citylifeezy.citylifeezy.models.RV_ReportData;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.NextServiceFilterCallback;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilter;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterRequest;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Asif on 11/28/2015.
 */
public class ReportFragment extends Fragment {


    private MobileServiceTable<RV_ReportData> RV_ReportData_Table;


    private static Context mContext;
    Spinner spinner,spinner2;
    ArrayAdapter<String> adapter1;
    ArrayAdapter<String> adapter2;


    public static ReportFragment newInstance(Context context) {
        ReportFragment f = new ReportFragment();
        Bundle args = new Bundle();
        args.putString("index", context.toString());
        f.setArguments(args);

        mContext = context;
        return f;
    }


    View rootview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_report, container, false);
        onCreate(savedInstanceState);
        //from_actv = (Spinner) rootview.findViewById(R.id.reportFromAreaName);


        String[] areas = getResources().
                getStringArray(R.array.list_of_areas);

        spinner = (Spinner) rootview.findViewById(R.id.reportFromAreaName);

        List<String> stringList = new ArrayList<String>(Arrays.asList(areas));

        Collections.sort(stringList);

        adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView) v.findViewById(android.R.id.text1)).setText("");
                    ((TextView) v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }

                return v;
            }

            @Override
            public int getCount() {
                return super.getCount() - 1; // you dont display last item. It is used as hint.
            }

        };

        for (int i = 0; i < areas.length; i++) {
            adapter1.add(stringList.get(i));
        }
        adapter1.add("Select Area");

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter1);
        spinner.setSelection(adapter1.getCount());

        spinner2 = (Spinner) rootview.findViewById(R.id.trafficCondition);

        adapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView) v.findViewById(android.R.id.text1)).setText("");
                    ((TextView) v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }

                return v;
            }

            @Override
            public int getCount() {
                return super.getCount() - 1; // you dont display last item. It is used as hint.
            }

        };

        adapter2.add("Free road -> 2 mins to cross");
        adapter2.add("Low Traffic -> 2-5 mins to cross");
        adapter2.add("Moderate traffic -> 5-10 mins to cross");
        adapter2.add("Heavy traffic -> 10-20 mins to cross");
        adapter2.add("Extreme traffic -> Min 20 mins to cross");
        adapter2.add("Select Traffic Condition");

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setSelection(adapter2.getCount());


        /*ArrayAdapter adapter = new ArrayAdapter
                (getActivity(),android.R.layout.simple_list_item_1,areas);*/


        /*Spinner dropdown_TrafficCondition = (Spinner) rootview.findViewById(R.id.trafficCondition);

        String[] trafficCondi = new String[]{"Free road -> 2 mins to cross",
                "Low Traffic -> 2-5 mins to cross",
                "Moderate traffic -> 5-10 mins to cross", "Heavy traffic -> 10-20 mins to cross"
                , "Extreme traffic -> Min 20 mins to cross"};


        ArrayAdapter<String> trafAdapter = new ArrayAdapter<String>(getActivity(), R.layout.trends_spinner_list, trafficCondi);


        //from_actv.setAdapter(adapter);


        dropdown_TrafficCondition.setAdapter(trafAdapter);*/


        return rootview;


    }

    private Spinner from_actv;


    private MobileServiceClient mClient;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        try {
            // Create the Mobile Service Client instance, using the provided
            // Mobile Service URL and key
            mClient = new MobileServiceClient(
                    "https://roadviserrrok.azure-mobile.net/",
                    "KCoGmJFoxpfxOfkAQvHTUOwNpbplcs63",
                    mContext).withFilter(new ProgressFilter());

            RV_ReportData_Table = mClient.getTable(RV_ReportData.class);

        } catch (MalformedURLException e) {
            createAndShowDialog(new Exception("There was an error creating the Mobile Service. Verify the URL"), "Error");
        }

        ReportFragment myListFragment = new ReportFragment();

        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        fragmentTransaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout, R.anim.fadein, R.anim.fadeout);

        fragmentTransaction.show(myListFragment);

        fragmentTransaction.commit();

    }


    Spinner conditionSpinner;

    Button cancelButton, reportButton;
    Spinner fromAutoComText;

    AutoCompleteTextView toAutoComText;

    EditText remarksEditText;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        // get the button view

        cancelButton = (Button) getView().findViewById(R.id.cancelReportButton);

        reportButton = (Button) getView().findViewById(R.id.reportTrafficButton);


        // set a onclick listener for when the button gets clicked
        cancelButton.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {

                FragmentManager fm = getFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack();
                } else {
                    Fragment objFragment = TrafficFragment.newInstance(getActivity());

                    FragmentManager fragmentManager = getFragmentManager();

                    fragmentManager.beginTransaction()
                            .replace(R.id.content_frame, objFragment)
                            .commit();
                }

            }

        });

        reportButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String areaSpinner = spinner.getSelectedItem().toString();
                String trafficConditionSpinner = spinner2.getSelectedItem().toString();

                if((areaSpinner.isEmpty())|| (trafficConditionSpinner.isEmpty())){

                    Toast.makeText(mContext, "Please select Area and Traffic Condition!", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(mContext, "Your report submitted successfully !", Toast.LENGTH_SHORT).show();
                }



//                fromAutoComText = (Spinner) getView().findViewById(R.id.reportFromAreaName);
//
//                remarksEditText = (EditText) getView().findViewById(R.id.reportRemarks);
//
//                conditionSpinner = (Spinner) getView().findViewById(R.id.trafficCondition);
//
//
//                String fromAutoComString, remarksEditTextString, trafficConditionString;
//
//
//                fromAutoComString = fromAutoComText.getText().toString();
//
//                trafficConditionString = conditionSpinner.getSelectedItem().toString();
//
//                remarksEditTextString = remarksEditText.getText().toString();
//
//
//                if (fromAutoComString.isEmpty()) {
//                    Toast.makeText(mContext, "Please fill in the Area Name!", Toast.LENGTH_LONG).show();
//                } else {
//
//                    ReportSuccessMethod(fromAutoComString, trafficConditionString, remarksEditTextString);
//
//                }
            }

        });

    }

    private void ReportSuccessMethod(String fromAutoComString, String trafficConditionString, String remarksEditTextString) {
        if (mClient == null) {
            return;
        }

        final RV_ReportData reportData = new RV_ReportData();

        reportData.setTraffic_From(fromAutoComString);
        reportData.setTraffic_To("");
        reportData.setRemarks(remarksEditTextString);
        reportData.setTraffic_Condition(trafficConditionString);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        String currentDateAndTime = sdf.format(new Date());

        reportData.setReport_Time(currentDateAndTime);


        // Insert the new item
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {

                    RV_ReportData_Table.insert(reportData);

                } catch (Exception e) {
                    createAndShowDialog(e, "Error");

                    InsertFailMethod();
                }

                return null;
            }
        }.execute();

        InsertSuccessMethod();
    }


    private void createAndShowDialog(Exception exception, String title) {
        Throwable ex = exception;
        if (exception.getCause() != null) {
            ex = exception.getCause();
        }
        createAndShowDialog(ex.getMessage(), title);
    }


    private void createAndShowDialog(final String message, final String title) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        builder.setMessage(message);
        builder.setTitle(title);
        builder.create().show();
    }


    private void InsertSuccessMethod() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(mContext).setMessage("Your Report has been successfully sent");

        final AlertDialog alert = dialog.create();
        alert.show();

// Hide after some seconds
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (alert.isShowing()) {
                    alert.dismiss();
                }
            }
        };

        alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                handler.removeCallbacks(runnable);
            }
        });

        handler.postDelayed(runnable, 2000);

        Fragment objFragment = null;

        objFragment = new TrafficFragment();
        TrafficFragment.newInstance(getActivity());

        FragmentManager fragmentManager = getFragmentManager();

        /*fragmentManager.beginTransaction()
                .replace(R.id.container, objFragment)
                .commit();*/
    }


    private void InsertFailMethod() {
        Toast.makeText(mContext, "Report Insertion Failed", Toast.LENGTH_LONG).show();
    }


    private class ProgressFilter implements ServiceFilter {

        @Override
        public ListenableFuture<ServiceFilterResponse> handleRequest(ServiceFilterRequest request, NextServiceFilterCallback nextServiceFilterCallback) {

            final SettableFuture<ServiceFilterResponse> resultFuture = SettableFuture.create();


            getActivity().runOnUiThread(new Runnable() {

                @Override
                public void run() {
                }
            });

            ListenableFuture<ServiceFilterResponse> future = nextServiceFilterCallback.onNext(request);

            Futures.addCallback(future, new FutureCallback<ServiceFilterResponse>() {
                @Override
                public void onFailure(Throwable e) {
                    resultFuture.setException(e);
                }

                @Override
                public void onSuccess(ServiceFilterResponse response) {
                    getActivity().runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                        }
                    });

                    resultFuture.set(response);
                }
            });

            return resultFuture;
        }
    }

}
