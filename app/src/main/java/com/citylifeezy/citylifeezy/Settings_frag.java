package com.citylifeezy.citylifeezy;

/**
 * Created by Asif on 12/5/2015.
 */
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.citylifeezy.citylifeezy.activity.R;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Settings_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Settings_frag extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String retriveNumber;
    public static String TAG = "Con";
    private ToggleButton tb,tboff;
    private LocationManager locationManager;
    private TextView tv,tvhide;
    private SharedPreferences sp;
    private static Context mContext;


    public Settings_frag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * <p/>
     * //     * @param param1 Parameter 1.
     * //     * @param param2 Parameter 2.
     *
     * @return A new instance of fragment Settings_frag.
     */
    // TODO: Rename and change types and number of parameters
    /*public static Settings_frag newInstance(String param1,String param2) {
        Settings_frag fragment = new Settings_frag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

    public static Settings_frag newInstance(Context context) {
        Settings_frag fragment = new Settings_frag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, context.toString());
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        mContext = context;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        /*Settings_frag myListFragment = new Settings_frag();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        //fragmentTransaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout, R.anim.fadein, R.anim.fadeout);
        fragmentTransaction.show(myListFragment);
        fragmentTransaction.commit();*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_settings_frag, container, false);
        getDefaults(getContext());
        tv = (TextView) v.findViewById(R.id.stv);
        tvhide = (TextView) v.findViewById(R.id.tvhide);
        tb = (ToggleButton) v.findViewById(R.id.stb);
        tboff = (ToggleButton) v.findViewById(R.id.tboff);
        locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);

        sp = PreferenceManager.getDefaultSharedPreferences(getContext());

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            tb.setChecked(true);
        } else if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            tb.setChecked(false);
        }

        tboff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tboff.isChecked()){
                    visibleAlert();
                    /*SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("toggleButton", tboff.isChecked());
                    editor.commit();*/
                }else{
                    hideAlert();
                    /*SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("toggleButton", tboff.isChecked());
                    editor.commit();*/
                }
            }
        });

        toggleButtonStateChanged();

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void hideAlert() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setMessage("Would you like to hide your location?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                gpsConnectionOff();
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putBoolean("toggleButton", tboff.isChecked());
                                editor.commit();
                            }
                        });
        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        tboff.setChecked(true);

                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    private void visibleAlert() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setMessage("Would you like to visible your location?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                gpsConnectionOn();
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putBoolean("toggleButton", tboff.isChecked());
                                editor.commit();
                            }
                        });
        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        tboff.setChecked(false);

                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            //gpsConnectionOff();
            tv.setText("GPS is disabled");
            tb.setChecked(false);
        }
        else{
            tv.setText("GPS is visible");
            tb.setChecked(true);
        }

        sp = PreferenceManager.getDefaultSharedPreferences(getContext());
        tboff.setChecked(sp.getBoolean("toggleButton", false));
    }

    private void toggleButtonStateChanged() {
        tb.setOnClickListener(new View.OnClickListener() {
            //boolean checked = true;
            @Override
            public void onClick(View v) {
                if (tb.isChecked()) {
                    showGPSEnabledAlertToUser();

                } else {
                    showGPSDisabledAlertToUser();
                }
            }
        });
    }

    private void showGPSEnabledAlertToUser() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setMessage("Would you like to enable your location?")
                .setCancelable(false)
                .setPositiveButton("Goto Settings To Enable GPS",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent callGPSSettingIntent = new Intent(
                                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(callGPSSettingIntent);
                            }
                        });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        tb.setChecked(false);

                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }


    private void showGPSDisabledAlertToUser() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setMessage("Would you like to disable your location?")
                .setCancelable(false)
                .setPositiveButton("Goto Settings To Disable GPS",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent callGPSSettingIntent = new Intent(
                                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(callGPSSettingIntent);
                            }
                        });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        tb.setChecked(true);
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    private void gpsConnectionOff() {
        String loc_url = "http://roadviserrr.net/roadviserrr/gps_connection_off.php";
        final int gps_on = 0;
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        StringRequest putRequest = new StringRequest(Request.Method.POST, loc_url, new Response.Listener<String>() {
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
//                String temp_loc = String.valueOf(loc);
//                String new_loc = temp_loc.replaceAll("[^\\d.,]", "");
                params.put("phone", retriveNumber);
                //params.put("loc", String.valueOf(new_loc));
                params.put("gps_availability", String.valueOf(gps_on));
                return params;
            }
        };
        queue.add(putRequest);
    }

    private void gpsConnectionOn() {
        String loc_url = "http://roadviserrr.net/roadviserrr/gps_connection_off.php";
        final int gps_on = 1;
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        StringRequest putRequest = new StringRequest(Request.Method.POST, loc_url, new Response.Listener<String>() {
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
//                String temp_loc = String.valueOf(loc);
//                String new_loc = temp_loc.replaceAll("[^\\d.,]", "");
                params.put("phone", retriveNumber);
                //params.put("loc", String.valueOf(new_loc));
                params.put("gps_availability", String.valueOf(gps_on));
                return params;
            }
        };
        queue.add(putRequest);
    }

    public String getDefaults(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        retriveNumber = preferences.getString("phoneNumb", null);
        Log.d(TAG, "Fetching number is: " + retriveNumber);
        return retriveNumber;
    }
}
