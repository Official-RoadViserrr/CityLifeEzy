package com.citylifeezy.citylifeezy.fragments;


// Below are all the Reference necessary to Make the Map Work

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.citylifeezy.citylifeezy.activity.R;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanvirsourov on 5/10/15.
 */
public class PoliceStFragment extends android.support.v4.app.Fragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, SearchView.OnQueryTextListener {

    // Creating the MapView and Map in here!!!

    private MapView mMapView;
    private GoogleMap mMap;

    //int hawaDiffHours[] = new int[54];

    //int hawaDiffMinutes[] = new int[54];
    // Creating the Context for the Map in here!!!

    private static Context mContext;

    /* We need the Context in order to get a reference to the Location Manager
     * (when instantiating this fragment from your activity use:
     *  PlaceMapFragment mapFragment = new PlaceMapFragment(this); ) */
    public static PoliceStFragment newInstance(Context context) {
        PoliceStFragment f = new PoliceStFragment();
        Bundle args = new Bundle();
        args.putString("index", context.toString());
        f.setArguments(args);

        mContext = context;
        return f;
    }


    SearchView mySearchView;

    ArrayList<LatLng> AreaLatLng;

    public String[] AreaName = {
            "Adabor", "Airport", "Badda", "Banani", "Bangshal", "Cantonment", "Chalkbazar",
            "Dakshin Khan", "Darus Salam", "Demra", "Dhanmondi",
            "Gandaria", "Gulshan", "Hajaribag", "Jatrabari"
            , "Kafrul", "Kalabagan", "Khilgaon", "Khilkhet",
            "Kadamtali", "Kotwali", "Lalbag", "Mirpur",
            "Mohammodpur", "New Market", "Motijheel",
            "Mugda", "Pallabi", "Paltan"
    };
    // This is where the View is being Created!!!
    private static final LatLng Adabor = new LatLng(23.771122, 90.359425);
    private static final LatLng Airport = new LatLng(23.850233, 90.409091);
    private static final LatLng Badda = new LatLng(23.771435, 90.427403);
    private static final LatLng Banani = new LatLng(23.789989, 90.401869);
    private static final LatLng Bangshal = new LatLng(23.714114, 90.410493);
    private static final LatLng Cantonment = new LatLng(23.824479, 90.405577);
    private static final LatLng Chalkbazar = new LatLng(23.721171, 90.395023);
    private static final LatLng Daksin_Khan = new LatLng(23.859549, 90.426182);
    private static final LatLng Darus_Salam = new LatLng(23.792041, 90.342724);
    private static final LatLng Demra = new LatLng(23.724795, 90.494798);
    private static final LatLng Dhanmondi = new LatLng(23.743185, 90.381292);
    private static final LatLng Gandaria = new LatLng(23.699681, 90.420942);
    private static final LatLng Gulshan = new LatLng(23.791206, 90.415479);
    private static final LatLng Hajaribag = new LatLng(23.734733, 90.365193);
    private static final LatLng Jatrabari = new LatLng(23.710396, 90.435517);
    private static final LatLng Kafrul = new LatLng(23.801289, 90.381468);
    private static final LatLng Kalabagan = new LatLng(23.743460, 90.388296);
    private static final LatLng Khilgaon = new LatLng(23.750945, 90.425201);
    private static final LatLng Khilkhet = new LatLng(23.828108, 90.419519);
    private static final LatLng Kadamtali = new LatLng(23.698061, 90.457526);
    private static final LatLng Kotwali = new LatLng(23.707309, 90.409203);
    private static final LatLng Lalbag = new LatLng(23.716827, 90.381643);
    private static final LatLng Mirpur = new LatLng(23.804222, 90.363119);
    private static final LatLng Mohammodpur = new LatLng(23.755667, 90.363841);
    private static final LatLng New_Market = new LatLng(23.733159, 90.386807);
    private static final LatLng Motijheel = new LatLng(23.729970, 90.417232);
    private static final LatLng Mugda = new LatLng(23.728792, 90.433414);
    private static final LatLng Pallabi = new LatLng(23.826089, 90.366392);
    private static final LatLng Paltan = new LatLng(23.736048, 90.416105);


    public void processMap(final View v) {
        if (mMap == null) {
            mMap = ((MapView) v.findViewById(R.id.mapView)).getMap();
            mMap.setOnMyLocationChangeListener(myLocationChangeListener);
        }
        if (mMap != null) {
            mMap.addMarker(new MarkerOptions().position(Adabor).title("Adabor Thana").icon((BitmapDescriptorFactory.fromResource(R.drawable.police_ico))));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Adabor, 13));

            mMap.addMarker(new MarkerOptions().position(Airport).title("Airport Thana").icon((BitmapDescriptorFactory.fromResource(R.drawable.police_ico))));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Airport, 13));

            mMap.addMarker(new MarkerOptions().position(Badda).title("Badda Thana").icon((BitmapDescriptorFactory.fromResource(R.drawable.police_ico))));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Badda, 13));

            mMap.addMarker(new MarkerOptions().position(Banani).title("Banani Thana").icon((BitmapDescriptorFactory.fromResource(R.drawable.police_ico))));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Banani, 13));

            mMap.addMarker(new MarkerOptions().position(Bangshal).title("Bangshal Thana").icon((BitmapDescriptorFactory.fromResource(R.drawable.police_ico))));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Bangshal, 13));

            mMap.addMarker(new MarkerOptions().position(Cantonment).title("Cantonment Thana").icon((BitmapDescriptorFactory.fromResource(R.drawable.police_ico))));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Cantonment, 13));

            mMap.addMarker(new MarkerOptions().position(Chalkbazar).title("Chalkbazar Thana").icon((BitmapDescriptorFactory.fromResource(R.drawable.police_ico))));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Chalkbazar, 13));

            mMap.addMarker(new MarkerOptions().position(Daksin_Khan).title("Daksin Khan Thana").icon((BitmapDescriptorFactory.fromResource(R.drawable.police_ico))));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Daksin_Khan, 13));

            mMap.addMarker(new MarkerOptions().position(Darus_Salam).title("Darus Salam Thana").icon((BitmapDescriptorFactory.fromResource(R.drawable.police_ico))));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Darus_Salam, 13));

            mMap.addMarker(new MarkerOptions().position(Demra).title("Demra Thana").icon((BitmapDescriptorFactory.fromResource(R.drawable.police_ico))));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Demra, 13));

            mMap.addMarker(new MarkerOptions().position(Dhanmondi).title("Dhanmondi Thana").icon((BitmapDescriptorFactory.fromResource(R.drawable.police_ico))));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Dhanmondi, 13));

            mMap.addMarker(new MarkerOptions().position(Gandaria).title("Gandaria Thana").icon((BitmapDescriptorFactory.fromResource(R.drawable.police_ico))));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Gandaria, 13));

            mMap.addMarker(new MarkerOptions().position(Gulshan).title("Gulshan Thana").icon((BitmapDescriptorFactory.fromResource(R.drawable.police_ico))));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Gulshan, 13));

            mMap.addMarker(new MarkerOptions().position(Hajaribag).title("Hajaribag Thana").icon((BitmapDescriptorFactory.fromResource(R.drawable.police_ico))));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Hajaribag, 13));

            mMap.addMarker(new MarkerOptions().position(Jatrabari).title("Jatrabari Thana").icon((BitmapDescriptorFactory.fromResource(R.drawable.police_ico))));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Jatrabari, 13));

            mMap.addMarker(new MarkerOptions().position(Kafrul).title("Kafrul Thana").icon((BitmapDescriptorFactory.fromResource(R.drawable.police_ico))));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Kafrul, 13));

            mMap.addMarker(new MarkerOptions().position(Kalabagan).title("Kalabagan Thana").icon((BitmapDescriptorFactory.fromResource(R.drawable.police_ico))));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Kalabagan, 13));

            mMap.addMarker(new MarkerOptions().position(Khilgaon).title("Khilgaon Thana").icon((BitmapDescriptorFactory.fromResource(R.drawable.police_ico))));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Khilgaon, 13));

            mMap.addMarker(new MarkerOptions().position(Khilkhet).title("Khilkhet Thana").icon((BitmapDescriptorFactory.fromResource(R.drawable.police_ico))));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Khilkhet, 13));

            mMap.addMarker(new MarkerOptions().position(Kadamtali).title("Kadamtali Thana").icon((BitmapDescriptorFactory.fromResource(R.drawable.police_ico))));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Kadamtali, 13));

            mMap.addMarker(new MarkerOptions().position(Kotwali).title("Kotwali Thana").icon((BitmapDescriptorFactory.fromResource(R.drawable.police_ico))));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Kotwali, 13));

            mMap.addMarker(new MarkerOptions().position(Lalbag).title("Lalbag Thana").icon((BitmapDescriptorFactory.fromResource(R.drawable.police_ico))));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Lalbag, 13));

            mMap.addMarker(new MarkerOptions().position(Mirpur).title("Mirpur Thana").icon((BitmapDescriptorFactory.fromResource(R.drawable.police_ico))));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Mirpur, 13));

            mMap.addMarker(new MarkerOptions().position(Mohammodpur).title("Mohammodpur Thana").icon((BitmapDescriptorFactory.fromResource(R.drawable.police_ico))));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Mohammodpur, 13));

            mMap.addMarker(new MarkerOptions().position(New_Market).title("New Market Thana").icon((BitmapDescriptorFactory.fromResource(R.drawable.police_ico))));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(New_Market, 13));

            mMap.addMarker(new MarkerOptions().position(Motijheel).title("Motijheel Thana").icon((BitmapDescriptorFactory.fromResource(R.drawable.police_ico))));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Motijheel, 13));

            mMap.addMarker(new MarkerOptions().position(Mugda).title("Mugda Thana").icon((BitmapDescriptorFactory.fromResource(R.drawable.police_ico))));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Mugda, 13));

            mMap.addMarker(new MarkerOptions().position(Pallabi).title("Pallabi Thana").icon((BitmapDescriptorFactory.fromResource(R.drawable.police_ico))));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Pallabi, 13));

            mMap.addMarker(new MarkerOptions().position(Paltan).title("Paltan Thana").icon((BitmapDescriptorFactory.fromResource(R.drawable.police_ico))));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Paltan, 13));

            mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                @Override
                public View getInfoWindow(Marker marker) {
                    return null;
                }

                @Override
                public View getInfoContents(Marker marker) {
                    View v = getActivity().getLayoutInflater().inflate(R.layout.mark, null);

                    if (marker.getTitle().equals("Adabor Thana")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Adabor Thana");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("02-9133265");
                    } else if (marker.getTitle().equals("Airport Thana")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Airport Thana");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("02-8951281");
                    } else if (marker.getTitle().equals("Badda Thana")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Badda Thana");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("02-9882352");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("02-9897475");
                    } else if (marker.getTitle().equals("Banani Thana")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Banani Thana");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("02-9894032");
                    } else if (marker.getTitle().equals("Bangshal Thana")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Bangshal Thana");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("02-9565700");
                    } else if (marker.getTitle().equals("Cantonment Thana")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Cantonment Thana");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("02-8712350");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("02-8829179");
                    } else if (marker.getTitle().equals("Chalkbazar Thana")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Chalkbazar Thana");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("02-7313966");
                    } else if (marker.getTitle().equals("Daksin Khan Thana")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Daksin Khan Thana");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("02-8931777");
                    } else if (marker.getTitle().equals("Darus Salam Thana")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Darus Salam Thana");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("02-8032333");
                    } else if (marker.getTitle().equals("Demra Thana")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Demra Thana");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("02-7401155");
                    } else if (marker.getTitle().equals("Dhanmondi Thana")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Dhanmondi Thana");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("02-8631941");
                    } else if (marker.getTitle().equals("Gandaria Thana")) {

                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Gandaria Thana");
                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("02-7453294");
                    } else if (marker.getTitle().equals("Gulshan Thana")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Gulshan Thana");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("02-9880234");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("02-9895826");
                    } else if (marker.getTitle().equals("Hajaribag Thana")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Hajaribag Thana");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("02-9669900");
                    } else if (marker.getTitle().equals("Jatrabari Thana")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Jatrabari Thana");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("02-7546244");
                    } else if (marker.getTitle().equals("Kafrul Thana")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Kafrul Thana");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("02-9001061");
                    } else if (marker.getTitle().equals("Kalabagan Thana")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Kalabagan Thana");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("02-9665254");
                    } else if (marker.getTitle().equals("Khilgaon Thana")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Khilgaon Thana");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("02-7219090");
                    } else if (marker.getTitle().equals("Khilkhet Thana")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Khilkhet Thana");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("02-8911786");
                    } else if (marker.getTitle().equals("Kadamtali Thana")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Kadamtali Thana");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("02-7547755");
                    } else if (marker.getTitle().equals("Kotwali Thana")) {

                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Kotwali Thana");
                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("02-7116255");
                    } else if (marker.getTitle().equals("Lalbag Thana")) {

                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Lalbag Thana");
                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("02-9660105");
                    } else if (marker.getTitle().equals("Mirpur Thana")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Mirpur Thana");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("02-9001000");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("02-9001001");
                    } else if (marker.getTitle().equals("Mohammodpur Thana")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Mohammodpur Thana");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("02- 9119943");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("02-9119960");
                    } else if (marker.getTitle().equals("New Market Thana")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("New Market Thana");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("02-8631942");
                    } else if (marker.getTitle().equals("Motijheel Thana")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Motijheel Thana");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("02-9571000");
                    } else if (marker.getTitle().equals("Mugda Thana")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Mugda Thana");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("02-7549722");
                    } else if (marker.getTitle().equals("Pallabi Thana")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Pallabi Thana");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("02-8015122");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("02-9015922");
                    } else if (marker.getTitle().equals("Paltan Thana")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Paltan Thana");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("02-9360802");
                    }


                    return v;
                }
            });

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        AreaLatLng = new ArrayList<>();

        AreaLatLng.add(new LatLng(23.771122, 90.359425));
        AreaLatLng.add(new LatLng(23.850233, 90.409091));
        AreaLatLng.add(new LatLng(23.771435, 90.427403));
        AreaLatLng.add(new LatLng(23.789989, 90.401869));
        AreaLatLng.add(new LatLng(23.714114, 90.410493));
        AreaLatLng.add(new LatLng(23.824479, 90.405577));
        AreaLatLng.add(new LatLng(23.721171, 90.395023));
        AreaLatLng.add(new LatLng(23.859549, 90.426182));
        AreaLatLng.add(new LatLng(23.792041, 90.342724));
        AreaLatLng.add(new LatLng(23.724795, 90.494798));
        AreaLatLng.add(new LatLng(23.743185, 90.381292));
        AreaLatLng.add(new LatLng(23.699681, 90.420942));
        AreaLatLng.add(new LatLng(23.791206, 90.415479));
        AreaLatLng.add(new LatLng(23.734733, 90.365193));
        AreaLatLng.add(new LatLng(23.710396, 90.435517));
        AreaLatLng.add(new LatLng(23.801289, 90.381468));
        AreaLatLng.add(new LatLng(23.743460, 90.388296));
        AreaLatLng.add(new LatLng(23.750945, 90.425201));
        AreaLatLng.add(new LatLng(23.828108, 90.419519));
        AreaLatLng.add(new LatLng(23.698061, 90.457526));
        AreaLatLng.add(new LatLng(23.707309, 90.409203));
        AreaLatLng.add(new LatLng(23.716827, 90.381643));
        AreaLatLng.add(new LatLng(23.804222, 90.363119));
        AreaLatLng.add(new LatLng(23.755667, 90.363841));
        AreaLatLng.add(new LatLng(23.733159, 90.386807));
        AreaLatLng.add(new LatLng(23.729970, 90.417232));
        AreaLatLng.add(new LatLng(23.728792, 90.433414));
        AreaLatLng.add(new LatLng(23.826089, 90.366392));
        AreaLatLng.add(new LatLng(23.736048, 90.416105));


        timecounter = 0;
        // inflat and return the layout
        View v = inflater.inflate(R.layout.fragment_policest, container, false);


        AutoCompleteTextView myTextView = (AutoCompleteTextView) getActivity().findViewById(R.id.autoCompleteTextViewOK);


        actv = (AutoCompleteTextView) v.findViewById(R.id.autoCompleteTextViewOK);


        autocomTextView = (AutoCompleteTextView) v.findViewById(R.id.autoCompleteTextViewOK);

        myImageView = (ImageView) v.findViewById(R.id.searchAreaView);

        mMapView = (MapView) v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);


        String[] areas = getResources().
                getStringArray(R.array.list_of_police_thana);

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, areas);

        actv.setAdapter(adapter);

        Menu menu = null;

        setUpMapIfNeeded_1(v);

        processMap(v);

        //populatePhoneNumber();

        //Toast.makeText(mContext, "Our Traffic Update Service is Available 8AM-10PM from Saturday to Thursday and 3PM-10PM On Fridays", Toast.LENGTH_LONG).show();
        //Toast.makeText(mContext, "All colored flags show incoming traffic flow to respective traffic points!", Toast.LENGTH_LONG).show();

        return v;
    }

    private void markClick() {
        if (mMap != null) {
            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(com.google.android.gms.maps.model.Marker marker) {
                    marker.showInfoWindow();
                    return true;
                }
            });
        }
    }


    private AutoCompleteTextView actv;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        PoliceStFragment myListFragment = new PoliceStFragment();

        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        fragmentTransaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout, R.anim.fadein, R.anim.fadeout);

        fragmentTransaction.show(myListFragment);

        fragmentTransaction.commit();
    }


    int searchCounter = 0;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search: {

                searchCounter++;

                if (searchCounter % 2 == 0) {
                    autocomTextView.setVisibility(View.GONE);
                    myImageView.setVisibility(View.GONE);

                    searchCounter = 0;
                } else {

                    autocomTextView.setVisibility(View.VISIBLE);

                    myImageView.setVisibility(View.VISIBLE);

                }
            }
        }

        return false;

    }


    AutoCompleteTextView autocomTextView;

    ImageView myImageView;


    // Setting Up the Map after OnCreateView is called

    private void setUpMapIfNeeded_1(View inflatedView) {

        if (mMap == null) {
            mMap = ((MapView) inflatedView.findViewById(R.id.mapView)).getMap();
            mMap.setOnMyLocationChangeListener(myLocationChangeListener);

            if (mMap != null) {
                mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                    @Override
                    public View getInfoWindow(Marker marker) {
                        return null;
                    }

                    @Override
                    public View getInfoContents(Marker marker) {

                        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View v = inflater.inflate(R.layout.windowlayout1, null);

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


    // Initially Starting the Map and Everything

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

//            GetMyLocation();
            mMap.setOnMyLocationChangeListener(myLocationChangeListener);

            mMap.setOnCameraChangeListener(getCameraChangeListener());

            GoToLoadMethod();

        }

    }


    // What happens if the Map resumes - i.e : After a Phone Call

    @Override
    public void onResume() {

        timecounter = 0;
        super.onResume();
        mMapView.onResume();

        setUpMapIfNeeded_2();

    }


    // Setting up the Map when it resumes!!!

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


    // This is the Method where the Actual Setting up happens!

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


    ImageView gpsView, gpsCenterView, doSearchView, rectangleView_1, rectangleView_2, rectangleView_3, rectangleView_4, rectangleView_5;

    AutoCompleteTextView userInputTextView;


    // Calling the GPSListener and getting the Locate Me to Center

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        // get the button view
        gpsView = (ImageView) getView().findViewById(R.id.gpslocationView);

        doSearchView = (ImageView) getView().findViewById(R.id.searchAreaView);

        // set a onclick listener for when the button gets clicked
//        gpsView.setOnClickListener(new View.OnClickListener() {
//            // Start new list activity
//            public void onClick(View v) {
//                gpsCounter = 0;
//                ss
//                PoliceStFragment.this.GetMyLocation();
//            }
//        });


        doSearchView.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                userInputTextView = (AutoCompleteTextView) getView().findViewById(R.id.autoCompleteTextViewOK);

                String userSearchedArea = userInputTextView.getText().toString().trim();

                ArrayList<LatLng> coordList = new ArrayList<LatLng>();


                coordList = new ArrayList<LatLng>(AreaLatLng);


                String[] areas = getResources().
                        getStringArray(R.array.list_of_police_thana);

                for (int i = 0; i < areas.length; i++) {
                    if (areas[i].equals(userSearchedArea)) {
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordList.get(i), 16.0f));
                    }
                }
            }
        });

    }


    // What happens if the Maps is Paused

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }


    // What happens if the Map is Destroyed

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


    //------------------------------------------>Ignoring this section start here<-------------------------------------------

    static final LatLng DHAKA = new LatLng(23.727640, 90.410578);

    List<Marker> mMarkers = new ArrayList<Marker>();


    int gpsCounter = 0;

    // This Method is Called to Get my Current Location

//    private void GetMyLocation() {
//        mMap.setOnMyLocationChangeListener(myLocationChangeListener);
//    }


    LatLng oldLoc;

    // This Method is Called to Get My GPS Change - the myLocationChangeListener

    GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {

        @Override
        public void onMyLocationChange(Location location) {
            // iAmHereMarker.remove();

            if (gpsCounter == 0) {

                gpsCounter = 1;

                LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());

                oldLoc = loc;


                if (mMap != null) {
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 16.0f));
                }
            }

        }
    };

    //-------------------------------------------->Stop<---------------------------------------------------
    // This Method Waits 3 Seconds so that Camera Can Load and then Loads all the PushPins where they belong

    private void GoToLoadMethod() {
        Handler mHandler = new Handler();

        mHandler.postDelayed(new Runnable() {
            public void run() {
                MainWorkingMethod();
            }
        }, 3000);

    }


    //------------------------------------------>Ignoring this section start here<-------------------------------------------


    Marker marker_Of_Incident;
    // This Method Normally Gets the Location Data From Azure and Sends the Location Points to Map


    int[] myAreaId = new int[54];
    String[] myArea_Stat_1 = new String[54];
    String[] myArea_Stat_2 = new String[54];
    String[] myArea_Stat_3 = new String[54];
    String[] myArea_Stat_4 = new String[54];
    String[] myUpdate_Time = new String[54];
    String[] myIs_Active = new String[54];


    String[] incidentUpdate_Time;
    String[] incidentIs_Active;
    String[] incidentName;
    String[] incidentCentral_Cord;


    public int[] getFromPrefs() {
        int[] ret;
        SharedPreferences prefs = mContext.getSharedPreferences("AREAID1", Context.MODE_PRIVATE);
        int count = prefs.getInt("Count1", 0);
        ret = new int[count];
        for (int i = 0; i < count; i++) {
            ret[i] = prefs.getInt("IntValue_1" + i, i);
        }
        return ret;
    }


    public String[] getFromPrefsString_1() {
        String[] ret;
        SharedPreferences prefs = mContext.getSharedPreferences("AREA_STAT_1_2", Context.MODE_PRIVATE);
        int count = prefs.getInt("Count1", 0);
        ret = new String[count];
        for (int i = 0; i < count; i++) {
            ret[i] = prefs.getString("IntValue_1" + i, Integer.toString(i));
        }
        return ret;
    }


    public String[] getFromPrefsString_2() {
        String[] ret;
        SharedPreferences prefs = mContext.getSharedPreferences("AREA_STAT_2_2", Context.MODE_PRIVATE);
        int count = prefs.getInt("Count1", 0);
        ret = new String[count];
        for (int i = 0; i < count; i++) {
            ret[i] = prefs.getString("IntValue_1" + i, Integer.toString(i));
        }
        return ret;
    }


    public String[] getFromPrefsString_3() {
        String[] ret;
        SharedPreferences prefs = mContext.getSharedPreferences("AREA_STAT_3_2", Context.MODE_PRIVATE);
        int count = prefs.getInt("Count1", 0);
        ret = new String[count];
        for (int i = 0; i < count; i++) {
            ret[i] = prefs.getString("IntValue_1" + i, Integer.toString(i));
        }
        return ret;
    }


    public String[] getFromPrefsString_4() {
        String[] ret;
        SharedPreferences prefs = mContext.getSharedPreferences("AREA_STAT_4_2", Context.MODE_PRIVATE);
        int count = prefs.getInt("Count1", 0);
        ret = new String[count];
        for (int i = 0; i < count; i++) {
            ret[i] = prefs.getString("IntValue_1" + i, Integer.toString(i));
        }
        return ret;
    }


    public String[] getFromPrefsString_5() {
        String[] ret;
        SharedPreferences prefs = mContext.getSharedPreferences("UPDATETIME1", Context.MODE_PRIVATE);
        int count = prefs.getInt("Count1", 0);
        ret = new String[count];
        for (int i = 0; i < count; i++) {
            ret[i] = prefs.getString("IntValue_1" + i, Integer.toString(i));
        }
        return ret;
    }


    public String[] getFromPrefsString_6() {
        String[] ret;
        SharedPreferences prefs = mContext.getSharedPreferences("ISACTIVE1", Context.MODE_PRIVATE);
        int count = prefs.getInt("Count1", 0);
        ret = new String[count];
        for (int i = 0; i < count; i++) {
            ret[i] = prefs.getString("IntValue_1" + i, Integer.toString(i));
        }
        return ret;
    }


    public String[] getFromPrefsString_IncidentUpdateTime() {
        String[] ret;
        SharedPreferences prefs = mContext.getSharedPreferences("INCIDENT_UPDATE_TIME1", Context.MODE_PRIVATE);
        int count = prefs.getInt("Count1", 0);
        ret = new String[count];
        for (int i = 0; i < count; i++) {
            ret[i] = prefs.getString("IntValue_1" + i, Integer.toString(i));
        }
        return ret;
    }


    public String[] getFromPrefsString_IncidentIsActive() {
        String[] ret;
        SharedPreferences prefs = mContext.getSharedPreferences("INCIDENT_IS_ACTIVE1", Context.MODE_PRIVATE);
        int count = prefs.getInt("Count1", 0);
        ret = new String[count];
        for (int i = 0; i < count; i++) {
            ret[i] = prefs.getString("IntValue_1" + i, Integer.toString(i));
        }
        return ret;
    }


    public String[] getFromPrefsString_IncidentCentralCord() {
        String[] ret;
        SharedPreferences prefs = mContext.getSharedPreferences("INCIDENT_CENTRAL_CORD1", Context.MODE_PRIVATE);
        int count = prefs.getInt("Count1", 0);
        ret = new String[count];
        for (int i = 0; i < count; i++) {
            ret[i] = prefs.getString("IntValue_1" + i, Integer.toString(i));
        }
        return ret;
    }


    public String[] getFromPrefsString_IncidentName() {
        String[] ret;
        SharedPreferences prefs = mContext.getSharedPreferences("INCIDENT_NAME1", Context.MODE_PRIVATE);
        int count = prefs.getInt("Count1", 0);
        ret = new String[count];
        for (int i = 0; i < count; i++) {
            ret[i] = prefs.getString("IntValue_1" + i, Integer.toString(i));
        }
        return ret;
    }

    private void MainWorkingMethod() {
        myAreaId = getFromPrefs();
        myArea_Stat_1 = getFromPrefsString_1();
        myArea_Stat_2 = getFromPrefsString_2();
        myArea_Stat_3 = getFromPrefsString_3();
        myArea_Stat_4 = getFromPrefsString_4();
        myUpdate_Time = getFromPrefsString_5();
        myIs_Active = getFromPrefsString_6();


        incidentName = new String[getFromPrefsString_IncidentName().length];
        incidentCentral_Cord = new String[getFromPrefsString_IncidentCentralCord().length];
        incidentUpdate_Time = new String[getFromPrefsString_IncidentUpdateTime().length];
        incidentIs_Active = new String[getFromPrefsString_IncidentIsActive().length];

        incidentName = getFromPrefsString_IncidentName();
        incidentCentral_Cord = getFromPrefsString_IncidentCentralCord();
        incidentUpdate_Time = getFromPrefsString_IncidentUpdateTime();
        incidentIs_Active = getFromPrefsString_IncidentIsActive();


        String temp[];

        int j = incidentName.length;

        for (int i = 0; i < incidentName.length; i++) {
            double inc_Latitude, inc_Longitude;

            String delimeter = ",";

            temp = incidentCentral_Cord[i].split(delimeter);

            inc_Latitude = Double.parseDouble(temp[0]);

            inc_Longitude = Double.parseDouble(temp[1]);

            LatLng geo_Code = new LatLng(inc_Latitude, inc_Longitude);

            if (incidentName[i].contains("Extra Points : ")) {

                incidentName[i] = incidentName[i].replace("Extra Points : ", "");

                marker_Of_Incident = mMap.addMarker(new MarkerOptions()
                        .title(incidentName[i])
                        .position(geo_Code)
                        .snippet("A")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.police)));
            }


        }

        for (int i = 0; i < myAreaId.length; i++) {
            int dummy = myAreaId[i];
            SetMarkerMethod(AreaName[i], AreaLatLng.get(i));
        }
        TimeCounterIncreaseMethod();
    }

    /////////////// Traffic Point 1
    private void TimeCounterIncreaseMethod() {
        timecounter = 1;
    }


    // Method for Instantiating Custom Info Window!!!


    int markerCounter = 0;

    private void SetMarkerMethod(String area_name,
                                 LatLng latilongi) {

        MarkerOptions mOptions = new MarkerOptions()
                .position(latilongi)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.police))
                .visible(true);
        mOptions.title(area_name);

        for (int i = 0; i < myAreaId.length; i++) {
            if (markerCounter == i) {
                mMarkers.add(mMap.addMarker(mOptions));
            }
        }


        markerCounter++;

    }


    GoogleApiClient mLocationClient;

    // What Happens if the Map is Connected to the Location Service

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
    public boolean onQueryTextSubmit(String query) {
        mySearchView.clearFocus();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}