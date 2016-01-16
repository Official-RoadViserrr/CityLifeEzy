package com.citylifeezy.citylifeezy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.citylifeezy.citylifeezy.activity.R;
import com.citylifeezy.citylifeezy.callbacks.OnVerificationSuccessListener;
import com.citylifeezy.citylifeezy.utils.CheckValidityAsyncTask;
import com.citylifeezy.citylifeezy.callbacks.OnValidityDateArrivedListener;
import com.citylifeezy.citylifeezy.utils.VerifyAsyncTask;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.NextServiceFilterCallback;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilter;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterRequest;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/*import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;*/

import static com.microsoft.windowsazure.mobileservices.table.query.QueryOperations.val;

/**
 * Created by Sourov00 on 23-06-15.
 */

public class AndroidAnimationActivity_Before extends Activity implements OnVerificationSuccessListener {


    // roaddata JSONArray
    JSONArray roaddata = null;

    // roaddata JSONArray
    JSONArray incidentdata = null;

    // roaddata JSONArray
    JSONArray user = null;
    JSONArray check_req;

    //    private MobileServiceTable<RV_RoadData> RV_RoadData_Table;
//    private MobileServiceTable<RV_IncidentData> RV_IncidentData_Table;
    private ProgressBar mProgressBar;
    private MobileServiceClient mClient;
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private final long ONE_DAY = 24 * 60 * 60 * 1000;
    int trialCounter = 0;
    AnimationDrawable myAnimationDrawable;

    Timer timer;
    MyTimerTask myTimerTask;

    int duration;
    int counter = 0;
    String finalPhoneNumber, finalPinNumber;
    String realPhoneNum, finalUserName, rpn;

    Context context;
    public static String TAG = "Con";

    //Activity Components
    Button regButton, verButton;
    EditText phoneNum, verifyNum, userName;
    LinearLayout pinVerLayout, userRegLayout, llProgressHolder;
    Animation cToRightAnim, lToCenterAnim;

    private int generatedRandomPIN;
    private ArrayList<String> checking_username;
    private ArrayList<String> check_user_phone;
    private android.content.Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_androidanimation_beforereg);
        context = this;

        startService(new Intent(this, AndroidLocationServices.class));
        checking_username_phone();
        initUI();
        if (isVerified()) {
            //checkValidity();
            //GoToNextPageMethod();
            refreshItemsFromTable_RoadData();
        } else {
            llProgressHolder.setVisibility(View.GONE);
            userRegLayout.setVisibility(View.VISIBLE);
        }
    }

    private void initUI() {
        regButton = (Button) findViewById(R.id.userRegisterButton);
        phoneNum = (EditText) findViewById(R.id.userPhoneNumber);
        verifyNum = (EditText) findViewById(R.id.userVerificationPin);
        pinVerLayout = (LinearLayout) findViewById(R.id.pinVerificationLayout);
        userRegLayout = (LinearLayout) findViewById(R.id.userRegistrationLayout);
        llProgressHolder = (LinearLayout) findViewById(R.id.llProgressHolder);
        verButton = (Button) findViewById(R.id.userVerifyButton);
        userName = (EditText) findViewById(R.id.userName);

        lToCenterAnim = AnimationUtils.loadAnimation(context, R.anim.left_to_center);
        cToRightAnim = AnimationUtils.loadAnimation(context, R.anim.center_to_right);

        regButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onRegButtonClick();
            }
        });

        verButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onVerifuButtonClick();
            }
        });
    }

    private void onRegButtonClick() {
        String firstUserName = userName.getText().toString().toLowerCase();
        String phoneNumInp = phoneNum.getText().toString();
        if (firstUserName == "" || firstUserName.toString().isEmpty() || firstUserName.toString() == null && phoneNumInp == "" || phoneNumInp.toString().isEmpty() || phoneNumInp.toString() == null) {
            Toast.makeText(getApplicationContext(), "Please insert your first name and phone number and then try again", Toast.LENGTH_LONG).show();
        } else {
            /*for (int l = 0; l < checking_username.size(); l++) {
                //for (int p = 0; p < check_user_phone.size(); p++) {
                if (checking_username.get(l).equals(firstUserName)) {

                    Toast.makeText(getApplicationContext(), "This username is already exist in the database", Toast.LENGTH_LONG).show();
                    break;
                }

            }*/
            finalUserName = firstUserName;
            realPhoneNum = "880" + phoneNumInp;
            rpn = "+" + realPhoneNum;
            finalPhoneNumber = realPhoneNum;
            //            CompleteRegistrationProcessToAzure(realPhoneNum);
            Random r = new Random();
            generatedRandomPIN = r.nextInt(9999 - 1000 + 1) + 1000;
            llProgressHolder.setVisibility(View.VISIBLE);
            sendVerificationSMS(realPhoneNum);
            setDefaults(rpn, getApplicationContext());
            setUserName(finalUserName, getApplicationContext());
            sender_name_update();
            receiver_name_update();
            //Toast.makeText(getApplicationContext(), "This username or phone number is already exist in the database", Toast.LENGTH_LONG).show();
        }
    }

    private void receiver_name_update() {
        String reg_url = "http://roadviserrr.net/roadviserrr/receiver_name_update.php";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest putRequest = new StringRequest(Request.Method.POST, reg_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("receiver", finalUserName);
                params.put("receiver_number", rpn);
                return params;
            }
        };
        queue.add(putRequest);
    }

    private void sender_name_update() {
        String reg_url = "http://roadviserrr.net/roadviserrr/sender_name_update.php";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest putRequest = new StringRequest(Request.Method.POST, reg_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("sender", finalUserName);
                params.put("sender_number", rpn);
                return params;
            }
        };
        queue.add(putRequest);
    }

    private void onVerifuButtonClick() {
        if (verifyNum.getText().toString() == "" || verifyNum.getText().toString().isEmpty() || verifyNum.getText().toString() == null) {
            Toast.makeText(getApplicationContext(), "Please insert your Verification Pin and then try again", Toast.LENGTH_LONG).show();
        } else {
            finalPinNumber = verifyNum.getText().toString();
            llProgressHolder.setVisibility(View.VISIBLE);
            if (finalPinNumber.equals(Integer.toString(generatedRandomPIN))) {
                saveVerificationDataToPref(finalPhoneNumber);
                //GoToNextPageMethodAfterVerification();
                refreshItemsFromTable_RoadData();
            } else {
                Toast.makeText(AndroidAnimationActivity_Before.this, "Invalid PIN", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean isVerified() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isVerified = preferences.getBoolean("isVerified", false);
        return isVerified;
    }

    /////------////
    private void sendVerificationSMS(String phone) {
        //   phone = "88" + phone; // format the number for twilio

        String regex = "8801\\d{9}"; // regex of the number format "8801XXXXXXXXX"
        if (!phone.matches(regex)) {
            llProgressHolder.setVisibility(View.GONE);
            Toast.makeText(this, "Number format incorrect. The number must be bangladeshi and in '01XXXXXXXXX' format",
                    Toast.LENGTH_LONG).show();
        } else {
            new VerifyAsyncTask(this, phone, generatedRandomPIN, this).execute();
            user_reg();
        }
    }

    private void user_reg() {
        String reg_url = "http://roadviserrr.net/roadviserrr/register_data.php";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest putRequest = new StringRequest(Request.Method.POST, reg_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("finalUserName", finalUserName);
                params.put("realPhoneNum", rpn);
                return params;
            }
        };
        queue.add(putRequest);
    }

    private void makeTransition() {
        userRegLayout.startAnimation(cToRightAnim);
        userRegLayout.setVisibility(LinearLayout.GONE);
        pinVerLayout.setVisibility(LinearLayout.VISIBLE);
        pinVerLayout.startAnimation(lToCenterAnim);
    }

    private void saveVerificationDataToPref(String phone) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("phone", phone);
        editor.putBoolean("isVerified", true);
        editor.commit();
    }

    public static void setDefaults(String value, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("phoneNumb", value);
        editor.commit();
    }

    public static void setUserName(String name, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("username", name);
        editor.commit();
    }

    ////-----////
    private void checkValidity() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String phone = preferences.getString("phone", null);

        if (phone != null) {
            //new CheckValidityAsyncTask(this, this, phone).execute();
        } else {
            Log.e("CheckValidity - XXXX", "Phone number must not be null");
        }
    }

    private void checking_username_phone() {
        String req_url_2 = "http://roadviserrr.net/citylifeezy1/check_username_phone.php";
        checking_username = new ArrayList<String>();
        check_user_phone = new ArrayList<String>();
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest putRequest = new StringRequest(Request.Method.GET, req_url_2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response From Server:" + response);
                if (response != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(response);
                        check_req = jsonObj.getJSONArray("friend_request");
                        Log.d("TAG", "Getting request:" + check_req);

                        for (int i = 0; i < check_req.length(); i++) {
                            JSONObject c = check_req.getJSONObject(i);
                            Log.d(TAG, "Req: " + c);

                            String username = c.getString("username");
                            String phone = c.getString("phone");

                            checking_username.add(username);
                            Log.d(TAG, "req_num: " + check_user_phone);

                            check_user_phone.add(phone);
                            Log.d(TAG, "req_state: " + check_user_phone);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(putRequest);
    }


    /*@Override
    public void onValidityDateArrived(DateTime validityDate) {
        llProgressHolder.setVisibility(View.GONE);
        if (validityDate == null) {
            return;
        }
        DateTime today = new DateTime();
        Log.e("CheckValidity - XXXXX", "validity from azure: " + validityDate.toString());

        if (today.isBefore(validityDate)) {
            //user is allowed to access
            //start the main activity here
            int daysBetween = Days.daysBetween(today, validityDate).getDays();
            Log.e("CheckValidityCallback", daysBetween + " days of subscription left");
            if (daysBetween <= 3) {

                int realdaysremaining = daysBetween + 1;
                Toast.makeText(this, "You are Enjoying Your 3 Day Trial Service. You have remaining " + realdaysremaining + " days.", Toast.LENGTH_SHORT).show();
            }

            refreshItemsFromTable_RoadData();

        } else {

            new AlertDialog.Builder(this)
                    .setTitle("Your Subscription Expired!")
                    .setMessage("Dear subscriber, to continue enjoying CityLifeEzy, please do the followings:\n\n" +
                            "1. Make bKash payment (Option#3) of BDT 99 to 01990 442819 from any personal (non merchant) bKash wallet\n" +
                            "\n" +
                            "2. Use your 11 digit mobile number in the reference field and 1 in the counter number field\n\n" +
                            "Your CityLifeEzy service will be activated within 15 minutes")
                    .setPositiveButton(R.string.exit, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            AndroidAnimationActivity_Before.this.finish();
                            System.exit(0);
                        }
                    })
                    .show();
        }
    }*/


    String[] verificationPinNumber;
    int goToNextMethodHandler = 0;


    /*private void GoToNextPageMethodAfterVerification() {
        checkValidity();
    }*/


    private void createAndShowDialog(final String message, final String title) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setMessage(message);
        builder.setTitle(title);
        builder.create().show();
    }

    @Override
    public void onVerificationSuccess(boolean flag) {
        if (flag) {
            llProgressHolder.setVisibility(View.GONE);
            makeTransition();
        } else {
            llProgressHolder.setVisibility(View.GONE);
        }
    }

    private class ProgressFilter_2 implements ServiceFilter {
        @Override
        public ListenableFuture<ServiceFilterResponse> handleRequest(ServiceFilterRequest request, NextServiceFilterCallback nextServiceFilterCallback) {
            final SettableFuture<ServiceFilterResponse> resultFuture = SettableFuture.create();

            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if (mProgressBar != null) mProgressBar.setVisibility(ProgressBar.VISIBLE);
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
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            if (mProgressBar != null) mProgressBar.setVisibility(ProgressBar.GONE);
                        }
                    });
                    resultFuture.set(response);
                }
            });
            return resultFuture;
        }
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    int[] myAreaId;
    String[] myArea_Stat_1;
    String[] myArea_Stat_2;
    String[] myArea_Stat_3;
    String[] myArea_Stat_4;
    String[] myUpdate_Time;
    String[] myIs_Active;

    String[] incidentUpdate_Time;
    String[] incidentIs_Active;
    String[] incidentName;
    String[] incidentCentral_Cord;

    ////-----////
    public void refreshItemsFromTable_RoadData() {
        boolean isConnectedToInternet = isNetworkAvailable();
        if (isConnectedToInternet) {

            final ArrayList<Integer> roadDataAreaId = new ArrayList<Integer>();
            final ArrayList<String> roadDataJamStat1 = new ArrayList<String>();
            final ArrayList<String> roadDataJamStat2 = new ArrayList<String>();
            final ArrayList<String> roadDataJamStat3 = new ArrayList<String>();
            final ArrayList<String> roadDataJamStat4 = new ArrayList<String>();
            final ArrayList<String> roadDataUpdate_time = new ArrayList<String>();
            final ArrayList<String> roadDataIs_active = new ArrayList<String>();

            RequestQueue queue = Volley.newRequestQueue(this);

            StringRequest putRequest = new StringRequest(Request.Method.GET,
                    "http://roadviserrr.net/citylifeezy1/get_all_rv_roaddata.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // response
                            Log.e("Response:::", response);

                            if (response != null) {
                                try {
                                    JSONObject jsonObj = new JSONObject(response);

                                    roaddata = jsonObj.getJSONArray("rv_roaddata");

                                    Log.e("Response roaddata: ", "> ");

                                    for (int i = 0; i < roaddata.length(); i++) {
                                        JSONObject c = roaddata.getJSONObject(i);

                                        // Storing each json item in variable
                                        String id = c.getString("id");
                                        String area_id = c.getString("area_id");
                                        String jam_stat_1 = c.getString("jam_stat_1");
                                        String jam_stat_2 = c.getString("jam_stat_2");
                                        String jam_stat_3 = c.getString("jam_stat_3");
                                        String jam_stat_4 = c.getString("jam_stat_4");
                                        String update_time = c.getString("update_time");
                                        String is_active = c.getString("is_active");


                                        Log.d("Response : id ", "> " + id);
                                        Log.d("Response : area_id ", "> " + area_id);
                                        Log.d("Response : jam_stat_1 ", "> " + jam_stat_1);
                                        Log.d("Response : jam_stat_2 ", "> " + jam_stat_2);
                                        Log.d("Response : jam_stat_3 ", "> " + jam_stat_3);
                                        Log.d("Response : jam_stat_4 ", "> " + jam_stat_4);
                                        Log.d("Response : update_time ", "> " + update_time);
                                        Log.d("Response: is_active ", "> " + is_active);


                                        roadDataAreaId.add(Integer.valueOf(area_id));
                                        roadDataJamStat1.add(jam_stat_1);
                                        roadDataJamStat2.add(jam_stat_2);
                                        roadDataJamStat3.add(jam_stat_3);
                                        roadDataJamStat4.add(jam_stat_4);
                                        roadDataUpdate_time.add(update_time);
                                        roadDataIs_active.add(is_active);

                                        Log.d("Response : area_id ", "> " + roadDataAreaId);
                                        Log.d("Response : jam_stat_1 ", "> " + roadDataJamStat1);
                                        Log.d("Response : jam_stat_2 ", "> " + roadDataJamStat2);
                                        Log.d("Response : jam_stat_3 ", "> " + roadDataJamStat3);
                                        Log.d("Response : jam_stat_4 ", "> " + roadDataJamStat4);
                                        Log.d("Response : update_time ", "> " + roadDataUpdate_time);
                                        Log.d("Response: is_active ", "> " + roadDataIs_active);

                                         /*ArrayList to Array Conversion */

                                        myAreaId = new int[roadDataAreaId.size()];
                                        for (int j = 0; j < roadDataAreaId.size(); j++) {
                                            myAreaId[j] = roadDataAreaId.get(j);
                                            Log.d("this is my array", "arr: " + Arrays.toString(myAreaId));
                                        }

                                        myArea_Stat_1 = new String[roadDataJamStat1.size()];
                                        for (int j = 0; j < roadDataJamStat1.size(); j++) {
                                            myArea_Stat_1[j] = roadDataJamStat1.get(j);
                                            Log.d("this is my array", "arr: " + Arrays.toString(myArea_Stat_1));
                                        }

                                        myArea_Stat_2 = new String[roadDataJamStat2.size()];
                                        for (int j = 0; j < roadDataJamStat2.size(); j++) {
                                            myArea_Stat_2[j] = roadDataJamStat2.get(j);
                                            Log.d("this is my array", "arr: " + Arrays.toString(myArea_Stat_2));
                                        }

                                        myArea_Stat_3 = new String[roadDataJamStat3.size()];
                                        for (int j = 0; j < roadDataJamStat3.size(); j++) {
                                            myArea_Stat_3[j] = roadDataJamStat3.get(j);
                                            Log.d("this is my array", "arr: " + Arrays.toString(myArea_Stat_3));
                                        }

                                        myArea_Stat_4 = new String[roadDataJamStat4.size()];
                                        for (int j = 0; j < roadDataJamStat4.size(); j++) {
                                            myArea_Stat_4[j] = roadDataJamStat4.get(j);
                                            Log.d("this is my array", "arr: " + Arrays.toString(myArea_Stat_4));
                                        }

                                        myUpdate_Time = new String[roadDataUpdate_time.size()];
                                        for (int j = 0; j < roadDataUpdate_time.size(); j++) {
                                            myUpdate_Time[j] = roadDataUpdate_time.get(j);
                                            Log.d("this is my array", "arr: " + Arrays.toString(myUpdate_Time));
                                        }

                                        myIs_Active = new String[roadDataIs_active.size()];
                                        for (int j = 0; j < roadDataIs_active.size(); j++) {
                                            myIs_Active[j] = roadDataIs_active.get(j);
                                            Log.d("this is my array", "arr: " + Arrays.toString(myIs_Active));
                                        }


                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                storeIntArray(myAreaId);
                                storeStringArray_1(myArea_Stat_1);
                                storeStringArray_2(myArea_Stat_2);
                                storeStringArray_3(myArea_Stat_3);
                                storeStringArray_4(myArea_Stat_4);
                                storeStringArray_5(myUpdate_Time);
                                storeStringArray_6(myIs_Active);


                            } else {
                                Log.e("ServiceHandler", "Couldn't get any data from the url");
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // error
//                        Log.d("Error.Response", error.getMessage());
                            error.printStackTrace();
                        }
                    }
            ) {


            };


            // Adding request to request queue

            queue.add(putRequest);

            AzureLoadComplete = 1;

            refreshItemsFromTable_IncidentData();

        } else {
            Toast.makeText(getApplicationContext(), "Please turn on your GPS and Internet and then try again!", Toast.LENGTH_LONG).show();
        }
    }


    int IncidentLoadComplete = 0;
/////-----////

    public void refreshItemsFromTable_IncidentData() {
        boolean isConnectedToInternet = isNetworkAvailable();
        if (isConnectedToInternet) {


            final ArrayList<String> incident_nameList = new ArrayList<String>();
            final ArrayList<String> central_cordList = new ArrayList<String>();
            final ArrayList<String> update_timeList = new ArrayList<String>();
            final ArrayList<String> is_activeList = new ArrayList<String>();

            RequestQueue queue = Volley.newRequestQueue(this);

            StringRequest putRequest = new StringRequest(Request.Method.GET,
                    "http://roadviserrr.net/citylifeezy1/get_all_rv_incidentdata.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // response
                            Log.e("Response:::", response);

                            if (response != null) {
                                try {
                                    JSONObject jsonObj = new JSONObject(response);

                                    incidentdata = jsonObj.getJSONArray("rv_incidentdata");

                                    Log.d("Response incidentdata: ", "> ");


                                    for (int i = 0; i < incidentdata.length(); i++) {
                                        JSONObject c = incidentdata.getJSONObject(i);

                                        // Storing each json item in variable
                                        String id = c.getString("id");
                                        String incident_name = c.getString("incident_name");
                                        String central_cord = c.getString("central_cord");
                                        String update_time = c.getString("update_time");
                                        String is_active = c.getString("is_active");

                                        Log.e("Response sadia: TTTTT ", "> " + id);
                                        Log.e("Response sadia: Title ", "> " + incident_name);
                                        Log.e("Response sadia: Title ", "> " + central_cord);
                                        Log.e("Response sadia: Title ", "> " + update_time);
                                        Log.e("Response sadia: Title ", "> " + is_active);

                                        incident_nameList.add(incident_name);
                                        central_cordList.add(central_cord);
                                        update_timeList.add(update_time);
                                        is_activeList.add(is_active);

                                        /*ArrayList to Array Conversion */

                                        incidentName = new String[incident_nameList.size()];
                                        for (int j = 0; j < incident_nameList.size(); j++) {
                                            incidentName[j] = incident_nameList.get(j);
                                            Log.d("this is my array", "arr: " + Arrays.toString(incidentName));
                                        }


                                        incidentCentral_Cord = new String[central_cordList.size()];
                                        for (int j = 0; j < central_cordList.size(); j++) {
                                            incidentCentral_Cord[j] = central_cordList.get(j);
                                            Log.d("this is my array", "arr: " + Arrays.toString(incidentCentral_Cord));
                                        }


                                        incidentUpdate_Time = new String[update_timeList.size()];
                                        for (int j = 0; j < update_timeList.size(); j++) {
                                            incidentUpdate_Time[j] = update_timeList.get(j);
                                            Log.d("this is my array", "arr: " + Arrays.toString(incidentUpdate_Time));

                                        }

                                        incidentIs_Active = new String[is_activeList.size()];
                                        for (int j = 0; j < is_activeList.size(); j++) {
                                            incidentIs_Active[j] = is_activeList.get(j);
                                            Log.d("this is my array", "arr: " + Arrays.toString(incidentIs_Active));
                                        }


                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                storeStringArray_IncidentName(incidentName);
                                storeStringArray_IncidentCentralCord(incidentCentral_Cord);
                                storeStringArray_IncidentUpdateTime(incidentUpdate_Time);
                                storeStringArray_IncidentIsActive(incidentIs_Active);
//                                GoToNextPageMethod();

                            } else {
                                Log.e("ServiceHandler", "Couldn't get any data from the url");
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // error
//                        Log.d("Error.Response", error.getMessage());
                            error.printStackTrace();
                        }
                    }
            ) {


            };

            // Adding request to request queue

            queue.add(putRequest);

//            storeStringArray_IncidentName(incidentName);
//            storeStringArray_IncidentCentralCord(incidentCentral_Cord);
//            storeStringArray_IncidentUpdateTime(incidentUpdate_Time);
//            storeStringArray_IncidentIsActive(incidentIs_Active);

            IncidentLoadComplete = 1;
            GoToNextPageMethod();


        } else {
            Toast.makeText(getApplicationContext(), "Please turn on your GPS and Internet and then try again!", Toast.LENGTH_LONG).show();
        }
    }


    public void storeStringArray_IncidentUpdateTime(String[] array) {
        SharedPreferences.Editor edit = getSharedPreferences("INCIDENT_UPDATE_TIME", Context.MODE_PRIVATE).edit();
        edit.putInt("Count", array.length);
        int count = 0;
        for (String i : array) {
            edit.putString("IntValue_" + count++, i);
        }
        edit.commit();
    }

    public void storeStringArray_IncidentIsActive(String[] array) {
        SharedPreferences.Editor edit = getSharedPreferences("INCIDENT_IS_ACTIVE", Context.MODE_PRIVATE).edit();
        edit.putInt("Count", array.length);
        int count = 0;
        for (String i : array) {
            edit.putString("IntValue_" + count++, i);
        }
        edit.commit();
    }


    public void storeStringArray_IncidentCentralCord(String[] array) {
        SharedPreferences.Editor edit = getSharedPreferences("INCIDENT_CENTRAL_CORD", Context.MODE_PRIVATE).edit();
        edit.putInt("Count", array.length);
        int count = 0;
        for (String i : array) {
            edit.putString("IntValue_" + count++, i);
        }
        edit.commit();
    }


    public void storeStringArray_IncidentName(String[] array) {
        SharedPreferences.Editor edit = getSharedPreferences("INCIDENT_NAME", Context.MODE_PRIVATE).edit();
        edit.putInt("Count", array.length);
        int count = 0;
        for (String i : array) {
            edit.putString("IntValue_" + count++, i);
        }
        edit.commit();
    }


    public void storeIntArray(int[] array) {
        SharedPreferences.Editor edit = getSharedPreferences("AREAID", Context.MODE_PRIVATE).edit();
        edit.putInt("Count", array.length);
        int count = 0;
        for (int i : array) {
            edit.putInt("IntValue_" + count++, i);
        }
        edit.commit();
    }


    public void storeStringArray_1(String[] array) {
        SharedPreferences.Editor edit = getSharedPreferences("AREA_STAT_1", Context.MODE_PRIVATE).edit();
        edit.putInt("Count", array.length);
        int count = 0;
        for (String i : array) {
            edit.putString("IntValue_" + count++, i);
        }
        edit.commit();
    }


    public void storeStringArray_2(String[] array) {
        SharedPreferences.Editor edit = getSharedPreferences("AREA_STAT_2", Context.MODE_PRIVATE).edit();
        edit.putInt("Count", array.length);
        int count = 0;
        for (String i : array) {
            edit.putString("IntValue_" + count++, i);
        }
        edit.commit();
    }


    public void storeStringArray_3(String[] array) {
        SharedPreferences.Editor edit = getSharedPreferences("AREA_STAT_3", Context.MODE_PRIVATE).edit();
        edit.putInt("Count", array.length);
        int count = 0;
        for (String i : array) {
            edit.putString("IntValue_" + count++, i);
        }
        edit.commit();
    }


    public void storeStringArray_4(String[] array) {
        SharedPreferences.Editor edit = getSharedPreferences("AREA_STAT_4", Context.MODE_PRIVATE).edit();
        edit.putInt("Count", array.length);
        int count = 0;
        for (String i : array) {
            edit.putString("IntValue_" + count++, i);
        }
        edit.commit();
    }


    public void storeStringArray_5(String[] array) {
        SharedPreferences.Editor edit = getSharedPreferences("UPDATETIME", Context.MODE_PRIVATE).edit();
        edit.putInt("Count", array.length);
        int count = 0;
        for (String i : array) {
            edit.putString("IntValue_" + count++, i);
        }
        edit.commit();
    }


    public void storeStringArray_6(String[] array) {
        SharedPreferences.Editor edit = getSharedPreferences("ISACTIVE", Context.MODE_PRIVATE).edit();
        edit.putInt("Count", array.length);
        int count = 0;
        for (String i : array) {
            edit.putString("IntValue_" + count++, i);
        }
        edit.commit();
    }

    int AzureLoadComplete = 0, AnimationComplete = 0;

    private void GoToNextPageMethod() {
        if (AzureLoadComplete == 1 && IncidentLoadComplete == 1) {
            Intent intent = new Intent(AndroidAnimationActivity_Before.this, DrawerActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }


    class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            timer.cancel();
            AnimationComplete = 1;
            GoToNextPageMethod();
        }
    }

    /*@Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }*/
}
