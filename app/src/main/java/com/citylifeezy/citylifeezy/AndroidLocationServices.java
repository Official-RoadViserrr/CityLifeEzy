package com.citylifeezy.citylifeezy;


import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Asif on 12/12/2015.
 */
public class AndroidLocationServices extends Service {

    PowerManager.WakeLock wakeLock;

    private LocationManager locationManager;
    LatLng loc;
    String retriveNumber;

    public AndroidLocationServices() {
        // TODO Auto-generated constructor stub
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();

        getDefaults(getApplicationContext());
        PowerManager pm = (PowerManager) getSystemService(this.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "DoNotSleep");

        // Toast.makeText(getApplicationContext(), "Service Created",
        // Toast.LENGTH_SHORT).show();

        Log.e("Google", "Service Created");

    }

    @Override
    @Deprecated
    public void onStart(Intent intent, int startId) {
        // TODO Auto-generated method stub
        super.onStart(intent, startId);

        Log.e("Google", "Service Started");

        locationManager = (LocationManager) getApplicationContext()
                .getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                5000, 5, listener);

    }

    private LocationListener listener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            // TODO Auto-generated method stub

            Log.e("Google", "Location Changed");

            if (location == null)
                return;

            if (isConnectingToInternet(getApplicationContext())) {

                loc = new LatLng(location.getLatitude(), location.getLongitude());
                Log.d("gg", "My Location: " + loc);
                passUserLocation();
                /*JSONArray jsonArray = new JSONArray();
                JSONObject jsonObject = new JSONObject();

                try {
                    Log.e("latitude", location.getLatitude() + "");
                    Log.e("longitude", location.getLongitude() + "");

                    jsonObject.put("latitude", location.getLatitude());
                    jsonObject.put("longitude", location.getLongitude());

                    jsonArray.put(jsonObject);

                    Log.e("request", jsonArray.toString());

                    *//*new LocationWebService().execute(new String[] {
                            SyncStateContract.Constants.TRACK_URL, jsonArray.toString() });*//*
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }*/

            }

        }

        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub

        }
    };

    public String getDefaults(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        retriveNumber = preferences.getString("phoneNumb", null);
        //Log.d(TAG, "Fetching number is: " + retriveNumber);
        return retriveNumber;
    }

    /*@Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Log.e(TAG, "onStartCommand");
        return START_STICKY;
    }*/

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        //new ToggleGPS(getApplicationContext()).turnGPSOff();
        //wakeLock.release();
        wakeLock.release();
        //sendBroadcast(new Intent("YouWillNeverKillMe"));

    }

    public static boolean isConnectingToInternet(Context _context) {
        ConnectivityManager connectivity = (ConnectivityManager) _context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }

    private void passUserLocation() {
        String loc_url = "http://roadviserrr.net/roadviserrr/send_location.php";
        //final int gps_on = 1;
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

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
}
