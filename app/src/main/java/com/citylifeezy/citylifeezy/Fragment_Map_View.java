package com.citylifeezy.citylifeezy;

/**
 * Created by Asif on 12/5/2015.
 */

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.citylifeezy.citylifeezy.activity.R;
import com.citylifeezy.citylifeezy.fragments.Fnf;
import com.citylifeezy.citylifeezy.utils.GoogleDirection;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Map_View#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Map_View extends android.support.v4.app.Fragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static String TAG = "Con";
    private static Context mContext;
    private MapView mMapView;
    private GoogleMap mMap;
    private GoogleApiClient mLocationClient;
    private int gpsCounter = 0;
    private LatLng oldLoc;
    private ArrayList<String> number_list = new ArrayList<String>();
    private ArrayList<String> contacts_list = new ArrayList<String>();
    private ArrayList<String> split_number = new ArrayList<String>();
    private ArrayList<String> check_user_phone;
    private ArrayList<String> u_phone;
    private ArrayList<String> u_location;
    private ArrayList<Integer> u_gps_availability;
    private ArrayList<String> u_time;
    private ArrayList<String> checking_receiver_name;
    private String retriveNumber, retriveUserName;
    private JSONArray user_loc, check_req;
    private int i;
    private String phnNumber;
    private Cursor cursor;
    private Marker marker;
    private String contactName;
    private ImageButton refreshView;
    private Context context;
    private List<Marker> mMarkers = new ArrayList<Marker>();
    private GoogleDirection gd;
    private ProgressDialog dialog;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * <p/>
     * //     * @param param1 Parameter 1.
     * //     * @param param2 Parameter 2.
     *
     * @return A new instance of fragment Fragment_Map_View.
     */
    // TODO: Rename and change types and number of parameters
    /*public static Fragment_Map_View newInstance(String param1, String param2) {
        Fragment_Map_View fragment = new Fragment_Map_View();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/
    public static Fragment_Map_View newInstance(Context context) {
        Fragment_Map_View fragment = new Fragment_Map_View();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, context.toString());
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        mContext = context;
        return fragment;
    }

    public Fragment_Map_View() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
        //checking_accept_user();
        /*Fragment_Map_View myListFragment = new Fragment_Map_View();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        //fragmentTransaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout, R.anim.fadein, R.anim.fadeout);
        fragmentTransaction.show(myListFragment);
        fragmentTransaction.commit();*/

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment__map__view, container, false);
        refreshView = (ImageButton) v.findViewById(R.id.refreshView);
        mMapView = (MapView) v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        sendLocation();
        setUpMapIfNeeded_1(v);
        getDefaults(getContext());
        getUserName(getContext());
        checking_accept_user();
        //getUserLoaction();

        refreshView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new loadStuff().execute();
                Fragment mFragment = new Fnf();
                getFragmentManager().beginTransaction().replace(R.id.content_frame, mFragment).commit();

            }
        });

        ContentResolver resolver = getActivity().getContentResolver();
        cursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        while (cursor.moveToNext()) {
            contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            phnNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            contacts_list.add(contactName);
            number_list.add(phnNumber);
            Log.d(TAG, "phn is: " + number_list);
        }
//        Log.d(TAG, "Contact is: " + contacts_list);
        cursor.close();

        userLatestLocationUpdate();
        //progressD();



        return v;
    }

    private void progressD() {
        dialog = new ProgressDialog(getContext());
        dialog.setProgress(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Getting Most Recent User Data. Please wait for a while....");
        dialog.show();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void userLatestLocationUpdate() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 10000ms
                getUserLoaction();
            }
        }, 5000);
    }

    private void checking_accept_user() {
        String req_url_2 = "http://roadviserrr.net/citylifeezy1/cheking_friend.php";
        checking_receiver_name = new ArrayList<String>();
        check_user_phone = new ArrayList<String>();
        RequestQueue queue = Volley.newRequestQueue(mContext);
        StringRequest putRequest = new StringRequest(Request.Method.GET, req_url_2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response From Server:" + response);
                if (response != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(response);
                        check_req = jsonObj.getJSONArray("already_friend");
                        Log.d("TAG", "Getting request:" + check_req);

                        for (int i = 0; i < check_req.length(); i++) {
                            JSONObject c = check_req.getJSONObject(i);
                            Log.d(TAG, "Req: " + c);

                            String phone = c.getString("sender_number");
                            String u_req_checking = c.getString("receiver");

                            check_user_phone.add(phone);
                            Log.d(TAG, "req_num: " + check_user_phone);

                            checking_receiver_name.add(u_req_checking);
                            Log.d(TAG, "req_state: " + checking_receiver_name);


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

    String AreaLatLngArray[];
    double AreaLatLng_Latitude, AreaLatLng_Longitude;
    String delimeter = ",";
    String cityName, stateName, countryName;

    private void getUserLoaction() {
        final String user_url = "http://roadviserrr.net/citylifeezy1/get_user_location.php";
        u_phone = new ArrayList<String>();
        u_location = new ArrayList<String>();
        u_gps_availability = new ArrayList<Integer>();
        u_time = new ArrayList<String>();
        //check_user_phone = new ArrayList<String>();
        //req_checking = new ArrayList<Integer>();
        RequestQueue queue = Volley.newRequestQueue(mContext);
        StringRequest putRequest = new StringRequest(Request.Method.GET, user_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response From Server:" + response);
                if (response != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(response);
                        user_loc = jsonObj.getJSONArray("friend_request");
                        Log.d("TAG", "Loca:" + user_loc);

                        for (i = 0; i < user_loc.length(); i++) {
                            JSONObject c = user_loc.getJSONObject(i);
                            Log.d(TAG, "JSON Object: " + c);
                            String phone = c.getString("phone");
                            String loc_time = c.getString("new_time");
                            String location = c.getString("location");
                            int gps_availability = Integer.parseInt(c.getString("gps_availability"));
                            Log.d(TAG, "area: " + phone);

                            /*int u_req_checking = Integer.parseInt(c.getString("req_checking"));*/

                            u_phone.add(phone);
                            Log.d(TAG, "user phone number: " + u_phone);

                            u_time.add(loc_time);
                            Log.d(TAG, "user location time: " + u_time);

                            u_location.add(location);
                            Log.d(TAG, "user location: " + u_location);

                            u_gps_availability.add(gps_availability);
                            Log.d(TAG, "user gps availability: " + u_gps_availability);

                            /*req_checking.add(u_req_checking);
                            Log.d(TAG, "user request checker: " + req_checking);*/
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    for (int j = 0; j < u_phone.size(); j++) {
                        String sp_num = u_phone.get(j);
                        String new_num = sp_num.substring(3);
                        split_number.add(new_num);
                        Log.d(TAG, "No User " + split_number);

                        //for (int l = 0; l < check_user_phone.size(); l++) {
                            for (int p = 0; p < checking_receiver_name.size() && p <check_user_phone.size(); p++) {

                                if (checking_receiver_name.get(p).equals(retriveUserName) && u_phone.get(j).equals(check_user_phone.get(p))) {

                                    for (int k = 0; k < number_list.size(); k++) {

                                        if ((u_phone.get(j).equals(number_list.get(k))) || (split_number.get(j).equals(number_list.get(k))) && u_gps_availability.get(j).equals(1)) {

                                            AreaLatLngArray = u_location.get(j).split(delimeter);
                                            try {
                                                AreaLatLng_Latitude = Double.parseDouble(AreaLatLngArray[0]);
                                                AreaLatLng_Longitude = Double.parseDouble(AreaLatLngArray[1]);

                                            } catch (NumberFormatException e) {
                                                AreaLatLng_Latitude = 0; // your default value
                                                AreaLatLng_Longitude = 0;
                                            }

                                            final LatLng AreaLatLng_geo_Code = new LatLng(AreaLatLng_Latitude, AreaLatLng_Longitude);

                                            Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
                                            try {
                                                List<Address> addresses = geocoder.getFromLocation(AreaLatLng_Latitude, AreaLatLng_Longitude, 1);
                                                cityName = addresses.get(0).getAddressLine(0);
                                                stateName = addresses.get(0).getAddressLine(1);
                                                countryName = addresses.get(0).getAddressLine(2);
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }

                                            marker = mMap.addMarker(new MarkerOptions().title(contacts_list.get(k) + "\n" + "City: " + cityName + "\n" + "State: " + stateName + "\n" + "Country: " + countryName + "\n" + "Last Update : " + u_time.get(j)).position(AreaLatLng_geo_Code).icon(BitmapDescriptorFactory.fromResource(R.drawable.user_location)));
                                        } else {
                                            Log.d(TAG, "Location Not Found");
                                        }
                                    }
                                //}
                            }
                        }
                    }
                    //marker.showInfoWindow();
                    dialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
            }
        });
        queue.add(putRequest);

    }

    private void setUpMapIfNeeded_1(View inflatedView) {

        if (mMap == null) {
            mMap = ((MapView) inflatedView.findViewById(R.id.mapView)).getMap();
            //mMap = ((SupportMapFragment) Fnf.fragmentManager.findFragmentById(R.id.mapView)).getMap();


            if (mMap != null) {
                mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                    @Override
                    public View getInfoWindow(Marker marker) {
                        return null;
                    }

                    @Override
                    public View getInfoContents(Marker marker) {

                        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View v = inflater.inflate(R.layout.windowlayout2, null);

                        TextView areaName = (TextView) v.findViewById(R.id.AreaName);
                        //TextView areaCondition_1 = (TextView) v.findViewById(R.id.AreaCond1);
                        //TextView areaCondition_2 = (TextView) v.findViewById(R.id.AreaCond2);
                        //TextView areaCondition_3 = (TextView) v.findViewById(R.id.AreaCond3);
                        //TextView areaCondition_4 = (TextView) v.findViewById(R.id.AreaCond4);
                        //TextView lastupdated = (TextView) v.findViewById(R.id.LastUpdated);


                        //LinearLayout myLinearLayout = (LinearLayout) v.findViewById(R.id.linearWindowLayout);

                        //String temp[], temp_area4[], temp_area3[], temp_area2[], temp_area1[];

                        areaName.setText(marker.getTitle());

                        return v;
                    }
                });
            }
//            mLocationClient = new GoogleApiClient.Builder(mContext)
//                    .addApi(Drive.API)
//                    .addScope(Drive.SCOPE_FILE)
//                    .addConnectionCallbacks(this)
//                    .addOnConnectionFailedListener(this)
//                    .build();

            if (mMap != null) {
                setUpMap_1();
            }
        }

    }

    private void setUpMap_1() {
        // Check if we were successful in obtaining the map.
        if (mMap != null) {
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);

            GetMyLocation();

            mMap.setOnCameraChangeListener(getCameraChangeListener());

            GoToLoadMethod();

        }

    }

    @Override
    public void onResume() {

        timecounter = 0;
        super.onResume();
        mMapView.onResume();
        setUpMapIfNeeded_2();

    }

    private void setUpMapIfNeeded_2() {

        try {
//            mLocationClient = new GoogleApiClient.Builder(mContext)
//                    .addApi(Drive.API)
//                    .addScope(Drive.SCOPE_FILE)
//                    .addConnectionCallbacks(this)
//                    .addOnConnectionFailedListener(this)
//                    .build();


            if (mMap != null) {
                setUpMap_2();
            }
        } catch (Exception e) {
            Toast.makeText(mContext, "Please turn on your GPS and Internet and then try again!", Toast.LENGTH_LONG).show();
        }

    }

    private void setUpMap_2() {

        try {
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                GoToLoadMethod();
            }
        } catch (Exception e) {
            Toast.makeText(mContext, "Please turn on your GPS and Internet and then try again!", Toast.LENGTH_LONG).show();
        }
    }

    int timecounter = 0;

    // What happens if the Zoom Level of the Map is Changed through Listener

    private float currentZoom = -1;


    public GoogleMap.OnCameraChangeListener getCameraChangeListener() {
        return new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition position) {
                if (currentZoom != position.zoom) {
                    {
                        currentZoom = position.zoom;

                        if (timecounter == 1) {

                            if (currentZoom < 12) {
                                for (int i = 0; i < mMarkers.size(); i++) {
                                    mMarkers.get(i).setVisible(false);
                                }
                            } else {
                                for (int i = 0; i < mMarkers.size(); i++) {
                                    mMarkers.get(i).setVisible(true);
                                }
                            }
                        }


                    }

                }
            }

        };
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
        progressD();
        dialog.dismiss();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    // What happens if the Map is running on Low Memory

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    private void GetMyLocation() {
        mMap.setOnMyLocationChangeListener(myLocationChangeListener);
    }

    LatLng loc;

    GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {

        @Override
        public void onMyLocationChange(Location location) {
            // iAmHereMarker.remove();

            if (gpsCounter == 0) {

                gpsCounter = 1;

                loc = new LatLng(location.getLatitude(), location.getLongitude());
                /*Log.d(TAG, "My Location: " + loc);*/
                passUserLocation();
                oldLoc = loc;

                Log.d(TAG, "Old Location: " + oldLoc);
                if (mMap != null) {
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 16.0f));
                }


            }

        }
    };

    public String getDefaults(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        retriveNumber = preferences.getString("phoneNumb", null);
        Log.d(TAG, "Fetching number is: " + retriveNumber);
        return retriveNumber;
    }

    public String getUserName(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        retriveUserName = preferences.getString("username", null);
        Log.d(TAG, "Fetching username is: " + retriveUserName);
        return retriveUserName;
    }

    private void sendLocation() {
        LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(getContext(), "GPS is Enabled in your devide", Toast.LENGTH_SHORT).show();
            progressD();
        } else {
            showGPSDisabledAlertToUser();
            //progressD();

        }
    }

    private void passUserLocation() {
        String loc_url = "http://roadviserrr.net/roadviserrr/send_location.php";
        //final int gps_on = 1;
        RequestQueue queue = Volley.newRequestQueue(mContext);

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
                String temp_loc = String.valueOf(loc);
                String new_loc = temp_loc.replaceAll("[^\\d.,]", "");
                params.put("phone", retriveNumber);
                params.put("loc", String.valueOf(new_loc));
                //params.put("gps_availability", String.valueOf(gps_on));
                return params;
            }
        };
        queue.add(putRequest);
    }


    private void showGPSDisabledAlertToUser() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setMessage("GPS is disabled in your device. Would you like to enable it?")
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
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    private void GoToLoadMethod() {
        Handler mHandler = new Handler();

        mHandler.postDelayed(new Runnable() {
            public void run() {
                //MainWorkingMethod();
                //retrieveUserLocation();
            }
        }, 3000);

    }/*private void retrieveUserLocation() {

    }*/


    @Override
    public void onConnected(Bundle bundle) {
        Toast.makeText(mContext, "Connected to Location Service", Toast.LENGTH_SHORT).show();
    }


    // What happens if the Connection to Location Service is Suspended for some Reason

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(mContext, "Connection to Location Service Disrupted", Toast.LENGTH_SHORT).show();
    }


    // What happens if No Connection to the Location Service is available

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(mContext, "Can not connect to Location Service", Toast.LENGTH_SHORT).show();
    }

    public class loadStuff extends AsyncTask<Void, Integer, Void> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(getContext());
            dialog.setProgress(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("Getting Most Recent User Data...");
            dialog.setMax(100);
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            for (int i = 0; i < 50; i++) {
                publishProgress(5);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            dialog.incrementProgressBy(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            dialog.dismiss();
            Fragment mFragment = new Fnf();
            getFragmentManager().beginTransaction().replace(R.id.content_frame, mFragment).commit();
            //getLatestUserLoaction();
            //Toast.makeText(Main.this,"Load Successfully",Toast.LENGTH_SHORT).show();
        }
    }
}
