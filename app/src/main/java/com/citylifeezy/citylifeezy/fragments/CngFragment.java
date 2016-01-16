package com.citylifeezy.citylifeezy.fragments;


// Below are all the Reference necessary to Make the Map Work

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.citylifeezy.citylifeezy.activity.R;
import com.citylifeezy.citylifeezy.models.RV_CngData;
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

import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Asif on 1/9/15.
 */
public class CngFragment extends android.support.v4.app.Fragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, SearchView.OnQueryTextListener {

    // Creating the MapView and Map in here!!!

    private MapView mMapView;
    private GoogleMap mMap;
    public Button update;
    SharedPreferences sp, jam;
    int resID;
    public String prefName = "marker_icon";
    public String prefStat = "jam_icon";
    Marker marker;
    private static final String TAG = "Http Connection";
    JSONArray cng_update;
    RV_CngData rv;
    String jam_stat;
    int i;
    ArrayList<Integer> cng_data_area;
    ArrayList<String> cng_data_jam;
    ArrayList<String> cng_data_time;
    Date d1 = null;
    Date d2 = null;
    long hour;

    //int hawaDiffHours[] = new int[54];

    //int hawaDiffMinutes[] = new int[54];
    // Creating the Context for the Map in here!!!

    private static Context mContext;

    /* We need the Context in order to get a reference to the Location Manager
     * (when instantiating this fragment from your activity use:
     *  PlaceMapFragment mapFragment = new PlaceMapFragment(this); ) */
    public static CngFragment newInstance(Context context) {
        CngFragment f = new CngFragment();
        Bundle args = new Bundle();
        args.putString("index", context.toString());
        f.setArguments(args);
        mContext = context;
        return f;
    }


    SearchView mySearchView;
    private Spinner spinnerCNG;
    LinearLayout llSearchLayout;

    ArrayList<LatLng> AreaLatLng;

    public String[] AreaName = {
            "Sikder Filling Station", "Aria CNG Station", "Shahjalal CNG Station", "Anudip CNG Station", "Denso CNG Station",
            "Navana CNG Station", "Khilkhet CNG Station", "Hazipara CNG Station",
            "RSR CNG Station", "Khandoker CNG Station",
            "Binimoy CNG Station", "Comet CNG Station",
            "Comfort CNG Station", "Jamuna CNG Station",
            "ABN CNG Station",
            "Modern CNG Station",
            "Trust CNG Station", "Purbachall CNG Station",
            "ABN CNG Station", "Royel CNG Station",
            "Zaman CNG Station", "Sonar Bangla CNG Filling Station", "Minerva CNG Filling Station",
            "Akij CNG Filling Station", "Best Eastern CNG Filling Station",
            "24 Feet CNG Station",
    };
    // This is where the View is being Created!!!
    private static final LatLng Sikder = new LatLng(23.770737, 90.401369);
    private static final LatLng Aria = new LatLng(23.806768, 90.371413);
    private static final LatLng Shahjalal = new LatLng(23.802840, 90.370697);
    private static final LatLng Anudip = new LatLng(23.747795, 90.403605);
    private static final LatLng Denso = new LatLng(23.782380, 90.350622);
    private static final LatLng Navana = new LatLng(23.771005, 90.411084);
    private static final LatLng Khilkhet = new LatLng(23.827510, 90.419806);
    private static final LatLng Hazipara = new LatLng(23.756899, 90.417268);
    private static final LatLng RSR = new LatLng(23.880029, 90.400699);
    private static final LatLng Khandakar = new LatLng(23.880169, 90.401396);
    private static final LatLng Binimoy = new LatLng(23.729954, 90.416235);
    private static final LatLng Comet = new LatLng(23.737354, 90.407882);
    private static final LatLng Comfort = new LatLng(23.779124, 90.358346);
    private static final LatLng Jamuna = new LatLng(23.750106, 90.402625);
    private static final LatLng ABN = new LatLng(23.778661, 90.356672);
    private static final LatLng Modern = new LatLng(23.776443, 90.425518);
    private static final LatLng Trust = new LatLng(23.766073, 90.389309);
    private static final LatLng Purbachall = new LatLng(23.781955, 90.351611);
    private static final LatLng ABN_2 = new LatLng(23.774200, 90.399039);
    private static final LatLng Royel = new LatLng(23.774623, 90.398857);
    private static final LatLng Zaman = new LatLng(23.770817, 90.424627);

    private static final LatLng sonar_bangla = new LatLng(23.724241, 90.396869);
    private static final LatLng minerva = new LatLng(23.804746, 90.369852);
    private static final LatLng akij = new LatLng(23.726163, 90.418563);
    private static final LatLng eastern = new LatLng(23.723737, 90.429088);
    private static final LatLng feet = new LatLng(23.690568, 90.450956);

    private static final LatLng nil = new LatLng(23.732353, 90.386719);


    public void processMap(final View v) {
        if (mMap == null) {
            mMap = ((MapView) v.findViewById(R.id.mapView)).getMap();
            mMap.setOnMyLocationChangeListener(myLocationChangeListener);
        }
        if (mMap != null) {

            sikdar_cng_station();

            /*mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {


                @Override
                public View getInfoWindow(Marker marker) {
                    return null;
                }

                @Override
                public View getInfoContents(Marker marker) {
                    View v = getActivity().getLayoutInflater().inflate(R.layout.mark, null);

                    *//*if (marker.getTitle().equals("Sikder Filling Station")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Sikder Filling Station");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Bir Uttam Mir Shawkat Sarak");

                    } else*//* if (marker.getTitle().equals("Aria CNG Station")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Aria CNG Station");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Shenpare,Mirpur");

                    } else if (marker.getTitle().equals("Shahjalal CNG Station")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Shahjalal CNG Station");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Shenpare,Mirpur");

                    } else if (marker.getTitle().equals("Anudip CNG Station")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Anudip CNG Station");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Boro Moghbazar");

                    } else if (marker.getTitle().equals("Denso CNG Station")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Denso CNG Station");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Gabtoli Technical");

                    } else if (marker.getTitle().equals("Navana CNG Station")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Navana CNG Station");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Tajgaon");

                    } else if (marker.getTitle().equals("Khilkhet CNG Station")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Khilkhet CNG Station");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Nikunjo");

                    } else if (marker.getTitle().equals("Hazipara CNG Station")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Hazipara CNG Station");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Chowdhury Para");

                    } else if (marker.getTitle().equals("RSR CNG Station")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("RSR CNG Station");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Abdullahpur Bus Stand");
                    } else if (marker.getTitle().equals("Khandoker CNG Station")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Khandoker CNG Station");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText(" Abdullahpur Bus Stand");
                    } else if (marker.getTitle().equals("Binimoy CNG Station")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Binimoy CNG Station");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Motijheel");
                    } else if (marker.getTitle().equals("Comet CNG Station")) {

                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Comet CNG Station");
                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Kakrail");
                    } else if (marker.getTitle().equals("Comfort CNG Station")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Comfort CNG Station");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Kallyanpur");

                    } else if (marker.getTitle().equals("Jamuna CNG Station")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Jamuna CNG Station");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Moghbazar");
                    } else if (marker.getTitle().equals("ABN CNG Station")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("ABN CNG Station");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Kallyanpur");
                    } else if (marker.getTitle().equals("Modern CNG Station")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Modern CNG Station");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Badda");
                    } else if (marker.getTitle().equals("Trust CNG Station")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Trust CNG Station");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Old Airport Road");
                    } else if (marker.getTitle().equals("Purbachall CNG Station")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Purbachall CNG Station");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Mirpur Technical");
                    } else if (marker.getTitle().equals("ABN CNG Station Rasulbagh")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("ABN CNG Station");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Rasulbagh");
                    } else if (marker.getTitle().equals("Royel CNG Station")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Royel CNG Station");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Rasulbagh");
                    } else if (marker.getTitle().equals("Zaman CNG Station")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Zaman CNG Station");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Badda");
                    } else if (marker.getTitle().equals("Sonar Bangla CNG Filling Station")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Sonar Bangla CNG Filling Station");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Bakshi Bazar Road");
                    } else if (marker.getTitle().equals("Minerva CNG Filling Station")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Minerva CNG Filling Station");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Begum Rokeya Ave");
                    } else if (marker.getTitle().equals("Akij CNG Filling Station")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Akij CNG Filling Station");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Shaheed Tajuddin Ahmed Ave");
                    } else if (marker.getTitle().equals("Best Eastern CNG Filling Station")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Best Eastern CNG Filling Station");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Atish Deepankar Road");
                    } else if (marker.getTitle().equals("24 Feet CNG Station")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("24 Feet CNG Station");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Zia Sarani Road");
                    } else if (marker.getTitle().equals("Nilkhet CNG Station")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Nilkhet CNG Station");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Nilkhet");
                    }

                    return v;
                }
            });*/

        }
        //sikdar_cng_station();
        //get_all_jam();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        AreaLatLng = new ArrayList<>();

        AreaLatLng.add(new LatLng(23.770737, 90.401369));
        AreaLatLng.add(new LatLng(23.806768, 90.371413));
        AreaLatLng.add(new LatLng(23.802840, 90.370697));
        AreaLatLng.add(new LatLng(23.747795, 90.403605));
        AreaLatLng.add(new LatLng(23.782380, 90.350622));
        AreaLatLng.add(new LatLng(23.771005, 90.411084));
        AreaLatLng.add(new LatLng(23.827510, 90.419806));
        AreaLatLng.add(new LatLng(23.756899, 90.417268));
        AreaLatLng.add(new LatLng(23.880029, 90.400699));
        AreaLatLng.add(new LatLng(23.880169, 90.401396));
        AreaLatLng.add(new LatLng(23.729954, 90.416235));
        AreaLatLng.add(new LatLng(23.737354, 90.407882));
        AreaLatLng.add(new LatLng(23.779124, 90.358346));
        AreaLatLng.add(new LatLng(23.750106, 90.402625));
        AreaLatLng.add(new LatLng(23.778661, 90.356672));
        AreaLatLng.add(new LatLng(23.776443, 90.425518));
        AreaLatLng.add(new LatLng(23.766073, 90.389309));
        AreaLatLng.add(new LatLng(23.781955, 90.351611));
        AreaLatLng.add(new LatLng(23.774200, 90.399039));
        AreaLatLng.add(new LatLng(23.774623, 90.398857));
        AreaLatLng.add(new LatLng(23.770817, 90.424627));

        AreaLatLng.add(new LatLng(23.724241, 90.396869));
        AreaLatLng.add(new LatLng(23.804746, 90.369852));
        AreaLatLng.add(new LatLng(23.726163, 90.418563));
        AreaLatLng.add(new LatLng(23.723737, 90.429088));
        AreaLatLng.add(new LatLng(23.690568, 90.450956));

        AreaLatLng.add(new LatLng(23.732353, 90.386719));


        timecounter = 0;
        // inflat and return the layout
        View v = inflater.inflate(R.layout.fragment_cng, container, false);

        //test_station = (EditText) v.findViewById(R.id.test_station);
        //test_time = (EditText) v.findViewById(R.id.test_time);

        //update = (Button) v.findViewById(R.id.update_button);

//        AutoCompleteTextView myTextView = (AutoCompleteTextView) getActivity().findViewById(R.id.autoCompleteTextViewOK);
//
//
//        actv = (AutoCompleteTextView) v.findViewById(R.id.autoCompleteTextViewOK);
//
//
//        autocomTextView = (AutoCompleteTextView) v.findViewById(R.id.autoCompleteTextViewOK);
//
//        myImageView = (ImageView) v.findViewById(R.id.searchAreaView);

        llSearchLayout = (LinearLayout) v.findViewById(R.id.llSearchLayout);
        spinnerCNG = (Spinner) v.findViewById(R.id.spinnerCNG);

        myImageView = (ImageView) v.findViewById(R.id.searchAreaView);

        mMapView = (MapView) v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);


        String[] areas = getResources().
                getStringArray(R.array.list_of_cng_station);

//        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, areas);
//
//        actv.setAdapter(adapter);

        List<String> stringList = new ArrayList<String>(Arrays.asList(areas));

        Collections.sort(stringList);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.trends_spinner_list) {

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
            adapter.add(stringList.get(i));
        }
        adapter.add("Select CNG station");

        adapter.setDropDownViewResource(R.layout.trends_spinner_list);
        spinnerCNG.setAdapter(adapter);
        spinnerCNG.setSelection(adapter.getCount());

        Menu menu = null;

        setUpMapIfNeeded_1(v);

        processMap(v);
        //button_click();

        return v;
    }

    int[] my_area_id = new int[21];
    String[] my_jam_stat = new String[21];

    int[] my_a_id = new int[21];
    String[] my_j_stat = new String[21];


    private void sikdar_cng_station() {
        final String url = "http://roadviserrr.net/citylifeezy1/get_all_cng_update.php";
        cng_data_area = new ArrayList<Integer>();
        cng_data_jam = new ArrayList<String>();
        cng_data_time = new ArrayList<String>();

//        final ProgressDialog pDialog = new ProgressDialog(mContext);
//        pDialog.setMessage("Loading...");
//        pDialog.show();
        //pDialog.setCancelable(false);

        RequestQueue queue = Volley.newRequestQueue(mContext);
        StringRequest putRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response From Server:" + response);
                if (response != null) {
                    //   pDialog.dismiss();
                    try {
                        JSONObject jsonObj = new JSONObject(response);
                        cng_update = jsonObj.getJSONArray("rv_cngdata");
                        Log.d("TAG", "CNG UP:" + cng_update);

                        for (i = 0; i < cng_update.length(); i++) {

                            JSONObject c = cng_update.getJSONObject(i);
                            Log.d(TAG, "JSON Object: " + c);
                            String area_id = c.getString("area_id");
                            jam_stat = c.getString("jam_stat");
                            String update_time = c.getString("update_time");

                            Log.d(TAG, "area: " + area_id);
                            Log.d(TAG, "jam: " + jam_stat);

                            cng_data_area.add(Integer.valueOf(area_id));
                            cng_data_jam.add(jam_stat);
                            cng_data_time.add(update_time);


                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String currentTime = sdf.format(new Date());

                    DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
                    DateTime time2 = format.parseDateTime(currentTime);
                    Minutes minInterval = Minutes.minutes(60);

                    DateTime sikdar = format.parseDateTime(cng_data_time.get(0));
                    Minutes Sikdar_cng = Minutes.minutesBetween(sikdar, time2);

                    DateTime aria = format.parseDateTime(cng_data_time.get(1));
                    Minutes Aria_cng = Minutes.minutesBetween(aria, time2);

                    DateTime shahjalal = format.parseDateTime(cng_data_time.get(2));
                    Minutes Shahjalal_cng = Minutes.minutesBetween(shahjalal, time2);

                    DateTime anudip = format.parseDateTime(cng_data_time.get(3));
                    Minutes Anudip_cng = Minutes.minutesBetween(anudip, time2);

                    DateTime denso = format.parseDateTime(cng_data_time.get(4));
                    Minutes Denso_cng = Minutes.minutesBetween(denso, time2);

                    DateTime navana = format.parseDateTime(cng_data_time.get(5));
                    Minutes Navana_cng = Minutes.minutesBetween(navana, time2);

                    DateTime khilkhet = format.parseDateTime(cng_data_time.get(6));
                    Minutes Khilkhet_cng = Minutes.minutesBetween(khilkhet, time2);

                    DateTime hazipara = format.parseDateTime(cng_data_time.get(7));
                    Minutes Hazipara_cng = Minutes.minutesBetween(hazipara, time2);

                    DateTime rsr = format.parseDateTime(cng_data_time.get(8));
                    Minutes RSR_cng = Minutes.minutesBetween(rsr, time2);

                    DateTime khandakar = format.parseDateTime(cng_data_time.get(9));
                    Minutes Khandakar_cng = Minutes.minutesBetween(khandakar, time2);

                    DateTime binimoy = format.parseDateTime(cng_data_time.get(10));
                    Minutes Binimoy_cng = Minutes.minutesBetween(binimoy, time2);

                    DateTime comet = format.parseDateTime(cng_data_time.get(11));
                    Minutes Comet_cng = Minutes.minutesBetween(comet, time2);

                    DateTime comfort = format.parseDateTime(cng_data_time.get(12));
                    Minutes Comfort_cng = Minutes.minutesBetween(comfort, time2);

                    DateTime jamuna = format.parseDateTime(cng_data_time.get(13));
                    Minutes Jamuna_cng = Minutes.minutesBetween(jamuna, time2);

                    DateTime abn = format.parseDateTime(cng_data_time.get(14));
                    Minutes ABN_cng = Minutes.minutesBetween(abn, time2);

                    DateTime modern = format.parseDateTime(cng_data_time.get(15));
                    Minutes Modern_cng = Minutes.minutesBetween(modern, time2);

                    DateTime trust = format.parseDateTime(cng_data_time.get(16));
                    Minutes Trust_cng = Minutes.minutesBetween(trust, time2);

                    DateTime purbachall = format.parseDateTime(cng_data_time.get(17));
                    Minutes Purbachall_cng = Minutes.minutesBetween(purbachall, time2);

                    DateTime abn_2 = format.parseDateTime(cng_data_time.get(18));
                    Minutes ABN_2_cng = Minutes.minutesBetween(abn_2, time2);

                    DateTime royel = format.parseDateTime(cng_data_time.get(19));
                    Minutes Royel_cng = Minutes.minutesBetween(royel, time2);

                    DateTime zaman = format.parseDateTime(cng_data_time.get(20));
                    Minutes Zaman_cng = Minutes.minutesBetween(zaman, time2);

                    DateTime Sonar_bangla = format.parseDateTime(cng_data_time.get(21));
                    Minutes Sonar_bangla_cng = Minutes.minutesBetween(Sonar_bangla, time2);

                    DateTime Minerva = format.parseDateTime(cng_data_time.get(22));
                    Minutes Minerva_cng = Minutes.minutesBetween(Minerva, time2);

                    DateTime Akij = format.parseDateTime(cng_data_time.get(23));
                    Minutes Akij_cng = Minutes.minutesBetween(Akij, time2);

                    DateTime Eastern = format.parseDateTime(cng_data_time.get(24));
                    Minutes Eastern_cng = Minutes.minutesBetween(Eastern, time2);

                    DateTime Feet = format.parseDateTime(cng_data_time.get(25));
                    Minutes Feet_cng = Minutes.minutesBetween(Feet, time2);

                    DateTime Nil = format.parseDateTime(cng_data_time.get(26));
                    Minutes Nil_cng = Minutes.minutesBetween(Nil, time2);


                    if (Sikdar_cng.isGreaterThan(minInterval)) {
                        marker = mMap.addMarker(new MarkerOptions().position(Sikder).title("Sikder Filling Station"+"\n"+"Bir Uttam Mir Shawkat Sarak").icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_marker))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Sikder, 13));
                    } else {
                        if (cng_data_area.get(0).equals(1)) {
                            if (cng_data_jam.get(0).equals("Red")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Sikder).title("Sikder Filling Station"+"\n"+"Bir Uttam Mir Shawkat Sarak"+"\n"+"Last Update: " + cng_data_time.get(0)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_orange))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Sikder, 13));
                            } else if (cng_data_jam.get(0).equals("Blue")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Sikder).title("Sikder Filling Station"+"\n"+"Bir Uttam Mir Shawkat Sarak"+"\n"+"Last Update: " + cng_data_time.get(0)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_blue))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Sikder, 13));

                            } else if (cng_data_jam.get(0).equals("Green")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Sikder).title("Sikder Filling Station"+"\n"+"Bir Uttam Mir Shawkat Sarak"+"\n"+"Last Update: " + cng_data_time.get(0)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_green))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Sikder, 13));

                            } else if (cng_data_jam.get(0).equals("Yellow")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Sikder).title("Sikder Filling Station"+"\n"+"Bir Uttam Mir Shawkat Sarak"+"\n"+"Last Update: " + cng_data_time.get(0)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_yellow))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Sikder, 13));

                            } else if (cng_data_jam.get(0).equals("Pink")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Sikder).title("Sikder Filling Station"+"\n"+"Bir Uttam Mir Shawkat Sarak"+"\n"+"Last Update: " + cng_data_time.get(0)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_pink))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Sikder, 13));
                            }
                        }
                    }
                    if (Aria_cng.isGreaterThan(minInterval)) {
                        marker = mMap.addMarker(new MarkerOptions().position(Aria).title("Aria CNG Station"+"\n"+"Shenpare,Mirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_marker))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Aria, 13));
                    } else {
                        if (cng_data_area.get(1).equals(2)) {
                            if (cng_data_jam.get(1).equals("Pink")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Aria).title("Aria CNG Station"+"\n"+"Shenpare,Mirpur"+"\n"+"Last Update: " + cng_data_time.get(1)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_pink))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Aria, 13));

                            } else if (cng_data_jam.get(1).equals("Green")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Aria).title("Aria CNG Station"+"\n"+"Shenpare,Mirpur"+"\n"+"Last Update: " + cng_data_time.get(1)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_green))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Aria, 13));

                            } else if (cng_data_jam.get(1).equals("Blue")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Aria).title("Aria CNG Station"+"\n"+"Shenpare,Mirpur"+"\n"+"Last Update: " + cng_data_time.get(1)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_blue))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Aria, 13));

                            } else if (cng_data_jam.get(1).equals("Yellow")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Aria).title("Aria CNG Station"+"\n"+"Shenpare,Mirpur"+"\n"+"Last Update: " + cng_data_time.get(1)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_yellow))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Aria, 13));

                            } else if (cng_data_jam.get(1).equals("Red")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Aria).title("Aria CNG Station"+"\n"+"Shenpare,Mirpur"+"\n"+"Last Update: " + cng_data_time.get(1)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_orange))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Aria, 13));
                            }
                        }
                    }
                    if (Shahjalal_cng.isGreaterThan(minInterval)) {
                        marker = mMap.addMarker(new MarkerOptions().position(Shahjalal).title("Shahjalal CNG Station"+"\n"+"Shenpare,Mirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_marker))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Shahjalal, 13));
                    } else {
                        if (cng_data_area.get(2).equals(3)) {
                            if (cng_data_jam.get(2).equals("Pink")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Shahjalal).title("Shahjalal CNG Station"+"\n"+"Shenpare,Mirpur"+"\n"+"Last Update: " + cng_data_time.get(2)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_pink))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Shahjalal, 13));

                            } else if (cng_data_jam.get(2).equals("Green")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Shahjalal).title("Shahjalal CNG Station"+"\n"+"Shenpare,Mirpur"+"\n"+"Last Update: " + cng_data_time.get(2)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_green))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Shahjalal, 13));

                            } else if (cng_data_jam.get(2).equals("Blue")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Shahjalal).title("Shahjalal CNG Station"+"\n"+"Shenpare,Mirpur"+"\n"+"Last Update: " + cng_data_time.get(2)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_blue))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Shahjalal, 13));

                            } else if (cng_data_jam.get(2).equals("Yellow")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Shahjalal).title("Shahjalal CNG Station"+"\n"+"Shenpare,Mirpur"+"\n"+"Last Update: " + cng_data_time.get(2)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_yellow))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Shahjalal, 13));

                            } else if (cng_data_jam.get(2).equals("Red")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Shahjalal).title("Shahjalal CNG Station"+"\n"+"Shenpare,Mirpur"+"\n"+"Last Update: " + cng_data_time.get(2)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_orange))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Shahjalal, 13));
                            }
                        }
                    }

                    if (Anudip_cng.isGreaterThan(minInterval)) {
                        marker = mMap.addMarker(new MarkerOptions().position(Anudip).title("Anudip CNG Station"+"\n"+"Boro Moghbazar").icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_marker))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Anudip, 13));
                    } else {
                        if (cng_data_area.get(3).equals(4)) {
                            if (cng_data_jam.get(3).equals("Pink")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Anudip).title("Anudip CNG Station"+"\n"+"Boro Moghbazar"+"\n"+"Last Update: " + cng_data_time.get(3)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_pink))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Anudip, 13));

                            } else if (cng_data_jam.get(3).equals("Green")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Anudip).title("Anudip CNG Station"+"\n"+"Boro Moghbazar"+"\n"+"Last Update: " + cng_data_time.get(3)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_green))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Anudip, 13));

                            } else if (cng_data_jam.get(3).equals("Blue")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Anudip).title("Anudip CNG Station"+"\n"+"Boro Moghbazar"+"\n"+"Last Update: " + cng_data_time.get(3)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_blue))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Anudip, 13));

                            } else if (cng_data_jam.get(3).equals("Yellow")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Anudip).title("Anudip CNG Station"+"\n"+"Boro Moghbazar"+"\n"+"Last Update: " + cng_data_time.get(3)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_yellow))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Anudip, 13));

                            } else if (cng_data_jam.get(3).equals("Red")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Anudip).title("Anudip CNG Station"+"\n"+"Boro Moghbazar"+"\n"+"Last Update: " + cng_data_time.get(3)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_orange))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Anudip, 13));
                            }
                        }
                    }

                    if (Denso_cng.isGreaterThan(minInterval)) {
                        marker = mMap.addMarker(new MarkerOptions().position(Denso).title("Denso CNG Station"+"\n"+"Gabtoli Technical").icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_marker))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Denso, 13));
                    } else {
                        if (cng_data_area.get(4).equals(5)) {
                            if (cng_data_jam.get(4).equals("Pink")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Denso).title("Denso CNG Station"+"\n"+"Gabtoli Technical"+"\n"+"Last Update: " + cng_data_time.get(4)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_pink))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Denso, 13));

                            } else if (cng_data_jam.get(4).equals("Green")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Denso).title("Denso CNG Station"+"\n"+"Gabtoli Technical"+"\n"+"Last Update: " + cng_data_time.get(4)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_green))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Denso, 13));

                            } else if (cng_data_jam.get(4).equals("Blue")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Denso).title("Denso CNG Station"+"\n"+"Gabtoli Technical"+"\n"+"Last Update: " + cng_data_time.get(4)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_blue))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Denso, 13));

                            } else if (cng_data_jam.get(4).equals("Yellow")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Denso).title("Denso CNG Station"+"\n"+"Gabtoli Technical"+"\n"+"Last Update: " + cng_data_time.get(4)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_yellow))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Denso, 13));

                            } else if (cng_data_jam.get(4).equals("Red")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Denso).title("Denso CNG Station"+"\n"+"Gabtoli Technical"+"\n"+"Last Update: " + cng_data_time.get(4)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_orange))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Denso, 13));
                            }
                        }
                    }

                    if (Navana_cng.isGreaterThan(minInterval)) {
                        marker = mMap.addMarker(new MarkerOptions().position(Navana).title("Navana CNG Station"+"\n"+"Tajgaon").icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_marker))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Navana, 13));
                    } else {
                        if (cng_data_area.get(5).equals(6)) {
                            if (cng_data_jam.get(5).equals("Pink")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Navana).title("Navana CNG Station"+"\n"+"Tajgaon"+"\n"+"Last Update: " + cng_data_time.get(5)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_pink))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Navana, 13));

                            } else if (cng_data_jam.get(5).equals("Green")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Navana).title("Navana CNG Station"+"\n"+"Tajgaon"+"\n"+"Last Update: " + cng_data_time.get(5)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_green))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Navana, 13));

                            } else if (cng_data_jam.get(5).equals("Blue")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Navana).title("Navana CNG Station"+"\n"+"Tajgaon"+"\n"+"Last Update: " + cng_data_time.get(5)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_blue))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Navana, 13));

                            } else if (cng_data_jam.get(5).equals("Yellow")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Navana).title("Navana CNG Station"+"\n"+"Tajgaon"+"\n"+"Last Update: " + cng_data_time.get(5)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_yellow))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Navana, 13));

                            } else if (cng_data_jam.get(5).equals("Red")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Navana).title("Navana CNG Station"+"\n"+"Tajgaon"+"\n"+"Last Update: " + cng_data_time.get(5)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_orange))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Navana, 13));
                            }
                        }
                    }

                    if (Khilkhet_cng.isGreaterThan(minInterval)) {
                        marker = mMap.addMarker(new MarkerOptions().position(Khilkhet).title("Khilkhet CNG Station"+"\n"+"Nikunjo").icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_marker))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Khilkhet, 13));
                    } else {
                        if (cng_data_area.get(6).equals(7)) {
                            if (cng_data_jam.get(6).equals("Pink")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Khilkhet).title("Khilkhet CNG Station"+"\n"+"Nikunjo"+"\n"+"Last Update: " + cng_data_time.get(6)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_pink))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Khilkhet, 13));

                            } else if (cng_data_jam.get(6).equals("Green")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Khilkhet).title("Khilkhet CNG Station"+"\n"+"Nikunjo"+"\n"+"Last Update: " + cng_data_time.get(6)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_green))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Khilkhet, 13));

                            } else if (cng_data_jam.get(6).equals("Blue")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Khilkhet).title("Khilkhet CNG Station"+"\n"+"Nikunjo"+"\n"+"Last Update: " + cng_data_time.get(6)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_blue))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Khilkhet, 13));

                            } else if (cng_data_jam.get(6).equals("Yellow")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Khilkhet).title("Khilkhet CNG Station"+"\n"+"Nikunjo"+"\n"+"Last Update: " + cng_data_time.get(6)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_yellow))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Khilkhet, 13));

                            } else if (cng_data_jam.get(6).equals("Red")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Khilkhet).title("Khilkhet CNG Station"+"\n"+"Nikunjo"+"\n"+"Last Update: " + cng_data_time.get(6)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_orange))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Khilkhet, 13));
                            }
                        }
                    }

                    if (Hazipara_cng.isGreaterThan(minInterval)) {
                        marker = mMap.addMarker(new MarkerOptions().position(Hazipara).title("Hazipara CNG Station"+"\n"+"Chowdhury Para").icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_marker))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Hazipara, 13));
                    } else {
                        if (cng_data_area.get(7).equals(8)) {
                            if (cng_data_jam.get(7).equals("Pink")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Hazipara).title("Hazipara CNG Station"+"\n"+"Chowdhury Para"+"\n"+"Last Update: " + cng_data_time.get(7)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_pink))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Hazipara, 13));

                            } else if (cng_data_jam.get(7).equals("Green")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Hazipara).title("Hazipara CNG Station"+"\n"+"Chowdhury Para"+"\n"+"Last Update: " + cng_data_time.get(7)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_green))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Hazipara, 13));

                            } else if (cng_data_jam.get(7).equals("Blue")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Hazipara).title("Hazipara CNG Station"+"\n"+"Chowdhury Para"+"\n"+"Last Update: " + cng_data_time.get(7)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_blue))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Hazipara, 13));

                            } else if (cng_data_jam.get(7).equals("Yellow")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Hazipara).title("Hazipara CNG Station"+"\n"+"Chowdhury Para"+"\n"+"Last Update: " + cng_data_time.get(7)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_yellow))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Hazipara, 13));

                            } else if (cng_data_jam.get(7).equals("Red")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Hazipara).title("Hazipara CNG Station"+"\n"+"Chowdhury Para"+"\n"+"Last Update: " + cng_data_time.get(7)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_orange))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Hazipara, 13));
                            }
                        }
                    }

                    if (RSR_cng.isGreaterThan(minInterval)) {
                        marker = mMap.addMarker(new MarkerOptions().position(RSR).title("RSR CNG Station"+"\n"+"Abdullahpur Bus Stand").icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_marker))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(RSR, 13));

                    } else {
                        if (cng_data_area.get(8).equals(9)) {
                            if (cng_data_jam.get(8).equals("Pink")) {
                                marker = mMap.addMarker(new MarkerOptions().position(RSR).title("RSR CNG Station"+"\n"+"Abdullahpur Bus Stand"+"\n"+"Last Update: " + cng_data_time.get(8)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_pink))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(RSR, 13));

                            } else if (cng_data_jam.get(8).equals("Green")) {
                                marker = mMap.addMarker(new MarkerOptions().position(RSR).title("RSR CNG Station"+"\n"+"Abdullahpur Bus Stand"+"\n"+"Last Update: " + cng_data_time.get(8)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_green))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(RSR, 13));

                            } else if (cng_data_jam.get(8).equals("Blue")) {
                                marker = mMap.addMarker(new MarkerOptions().position(RSR).title("RSR CNG Station"+"\n"+"Abdullahpur Bus Stand"+"\n"+"Last Update: " + cng_data_time.get(8)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_blue))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(RSR, 13));

                            } else if (cng_data_jam.get(8).equals("Yellow")) {
                                marker = mMap.addMarker(new MarkerOptions().position(RSR).title("RSR CNG Station"+"\n"+"Abdullahpur Bus Stand"+"\n"+"Last Update: " + cng_data_time.get(8)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_yellow))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(RSR, 13));

                            } else if (cng_data_jam.get(8).equals("Red")) {
                                marker = mMap.addMarker(new MarkerOptions().position(RSR).title("RSR CNG Station"+"\n"+"Abdullahpur Bus Stand"+"\n"+"Last Update: " + cng_data_time.get(8)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_orange))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(RSR, 13));
                            }
                        }
                    }

                    if (Khandakar_cng.isGreaterThan(minInterval)) {
                        marker = mMap.addMarker(new MarkerOptions().position(Khandakar).title("Khandoker CNG Station"+"\n"+"Abdullahpur Bus Stand").icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_marker))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Khandakar, 13));

                    } else {
                        if (cng_data_area.get(9).equals(10)) {
                            if (cng_data_jam.get(9).equals("Pink")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Khandakar).title("Khandoker CNG Station"+"\n"+"Abdullahpur Bus Stand"+"\n"+"Last Update: " + cng_data_time.get(9)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_pink))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Khandakar, 13));

                            } else if (cng_data_jam.get(9).equals("Green")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Khandakar).title("Khandoker CNG Station"+"\n"+"Abdullahpur Bus Stand"+"\n"+"Last Update: " + cng_data_time.get(9)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_green))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Khandakar, 13));

                            } else if (cng_data_jam.get(9).equals("Blue")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Khandakar).title("Khandoker CNG Station"+"\n"+"Abdullahpur Bus Stand"+"\n"+"Last Update: " + cng_data_time.get(9)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_blue))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Khandakar, 13));

                            } else if (cng_data_jam.get(9).equals("Yellow")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Khandakar).title("Khandoker CNG Station"+"\n"+"Abdullahpur Bus Stand"+"\n"+"Last Update: " + cng_data_time.get(9)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_yellow))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Khandakar, 13));

                            } else if (cng_data_jam.get(9).equals("Red")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Khandakar).title("Khandoker CNG Station"+"\n"+"Abdullahpur Bus Stand"+"\n"+"Last Update: " + cng_data_time.get(9)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_orange))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Khandakar, 13));
                            }
                        }
                    }

                    if (Binimoy_cng.isGreaterThan(minInterval)) {
                        marker = mMap.addMarker(new MarkerOptions().position(Binimoy).title("Binimoy CNG Station"+"\n"+"Motijheel").icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_marker))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Binimoy, 13));

                    } else {
                        if (cng_data_area.get(10).equals(11)) {
                            if (cng_data_jam.get(10).equals("Pink")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Binimoy).title("Binimoy CNG Station"+"\n"+"Motijheel"+"\n"+"Last Update: " + cng_data_time.get(10)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_pink))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Binimoy, 13));

                            } else if (cng_data_jam.get(10).equals("Green")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Binimoy).title("Binimoy CNG Station"+"\n"+"Motijheel"+"\n"+"Last Update: " + cng_data_time.get(10)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_green))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Binimoy, 13));

                            } else if (cng_data_jam.get(10).equals("Blue")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Binimoy).title("Binimoy CNG Station"+"\n"+"Motijheel"+"\n"+"Last Update: " + cng_data_time.get(10)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_blue))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Binimoy, 13));

                            } else if (cng_data_jam.get(10).equals("Yellow")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Binimoy).title("Binimoy CNG Station"+"\n"+"Motijheel"+"\n"+"Last Update: " + cng_data_time.get(10)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_yellow))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Binimoy, 13));

                            } else if (cng_data_jam.get(10).equals("Red")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Binimoy).title("Binimoy CNG Station"+"\n"+"Motijheel"+"\n"+"Last Update: " + cng_data_time.get(10)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_orange))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Binimoy, 13));
                            }
                        }
                    }

                    if (Comet_cng.isGreaterThan(minInterval)) {
                        marker = mMap.addMarker(new MarkerOptions().position(Comet).title("Comet CNG Station"+"\n"+"Kakrail").icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_marker))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Comet, 13));
                    } else {
                        if (cng_data_area.get(11).equals(12)) {
                            if (cng_data_jam.get(11).equals("Pink")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Comet).title("Comet CNG Station"+"\n"+"Kakrail"+"\n"+"Last Update: " + cng_data_time.get(11)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_pink))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Comet, 13));

                            } else if (cng_data_jam.get(11).equals("Green")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Comet).title("Comet CNG Station"+"\n"+"Kakrail"+"\n"+"Last Update: " + cng_data_time.get(11)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_green))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Comet, 13));

                            } else if (cng_data_jam.get(11).equals("Blue")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Comet).title("Comet CNG Station"+"\n"+"Kakrail"+"\n"+"Last Update: " + cng_data_time.get(11)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_blue))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Comet, 13));

                            } else if (cng_data_jam.get(11).equals("Yellow")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Comet).title("Comet CNG Station"+"\n"+"Kakrail"+"\n"+"Last Update: " + cng_data_time.get(11)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_yellow))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Comet, 13));

                            } else if (cng_data_jam.get(11).equals("Red")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Comet).title("Comet CNG Station"+"\n"+"Kakrail"+"\n"+"Last Update: " + cng_data_time.get(11)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_orange))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Comet, 13));
                            }
                        }
                    }

                    if (Comfort_cng.isGreaterThan(minInterval)) {
                        marker = mMap.addMarker(new MarkerOptions().position(Comfort).title("Comfort CNG Station"+"\n"+"Kallyanpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_marker))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Comfort, 13));
                    } else {
                        if (cng_data_area.get(12).equals(13)) {
                            if (cng_data_jam.get(12).equals("Pink")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Comfort).title(" CNG Station"+"\n"+"Kallyanpur"+"\n"+"Last Update: " + cng_data_time.get(12)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_pink))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Comfort, 13));

                            } else if (cng_data_jam.get(12).equals("Green")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Comfort).title("Comfort CNG Station"+"\n"+"Kallyanpur"+"\n"+"Last Update: " + cng_data_time.get(12)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_green))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Comfort, 13));

                            } else if (cng_data_jam.get(12).equals("Blue")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Comfort).title("Comfort CNG Station"+"\n"+"Kallyanpur"+"\n"+"Last Update: " + cng_data_time.get(12)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_blue))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Comfort, 13));

                            } else if (cng_data_jam.get(12).equals("Yellow")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Comfort).title("Comfort CNG Station"+"\n"+"Kallyanpur"+"\n"+"Last Update: " + cng_data_time.get(12)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_yellow))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Comfort, 13));

                            } else if (cng_data_jam.get(12).equals("Red")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Comfort).title("Comfort CNG Station"+"\n"+"Kallyanpur"+"\n"+"Last Update: " + cng_data_time.get(12)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_orange))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Comfort, 13));
                            }
                        }
                    }

                    if (Jamuna_cng.isGreaterThan(minInterval)) {
                        marker = mMap.addMarker(new MarkerOptions().position(Jamuna).title("Jamuna CNG Station"+"\n"+"Moghbazar").icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_marker))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Jamuna, 13));

                    } else {
                        if (cng_data_area.get(13).equals(14)) {
                            if (cng_data_jam.get(13).equals("Pink")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Jamuna).title("Jamuna CNG Station"+"\n"+"Moghbazar"+"\n"+"Last Update: " + cng_data_time.get(13)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_pink))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Jamuna, 13));

                            } else if (cng_data_jam.get(13).equals("Green")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Jamuna).title("Jamuna CNG Station"+"\n"+"Moghbazar"+"\n"+"Last Update: " + cng_data_time.get(13)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_green))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Jamuna, 13));

                            } else if (cng_data_jam.get(13).equals("Blue")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Jamuna).title("Jamuna CNG Station"+"\n"+"Moghbazar"+"\n"+"Last Update: " + cng_data_time.get(13)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_blue))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Jamuna, 13));

                            } else if (cng_data_jam.get(13).equals("Yellow")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Jamuna).title("Jamuna CNG Station"+"\n"+"Moghbazar"+"\n"+"Last Update: " + cng_data_time.get(13)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_yellow))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Jamuna, 13));

                            } else if (cng_data_jam.get(13).equals("Red")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Jamuna).title("Jamuna CNG Station"+"\n"+"Moghbazar"+"\n"+"Last Update: " + cng_data_time.get(13)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_orange))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Jamuna, 13));
                            }
                        }
                    }

                    if (ABN_cng.isGreaterThan(minInterval)) {
                        marker = mMap.addMarker(new MarkerOptions().position(ABN).title("ABN CNG Station"+"\n"+"Kallyanpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_marker))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ABN, 13));
                    } else {
                        if (cng_data_area.get(14).equals(15)) {
                            if (cng_data_jam.get(14).equals("Pink")) {
                                marker = mMap.addMarker(new MarkerOptions().position(ABN).title("ABN CNG Station"+"\n"+"Kallyanpur"+"\n"+"Last Update: " + cng_data_time.get(14)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_pink))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ABN, 13));

                            } else if (cng_data_jam.get(14).equals("Green")) {
                                marker = mMap.addMarker(new MarkerOptions().position(ABN).title("ABN CNG Station"+"\n"+"Kallyanpur"+"\n"+"Last Update: " + cng_data_time.get(14)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_green))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ABN, 13));

                            } else if (cng_data_jam.get(14).equals("Blue")) {
                                marker = mMap.addMarker(new MarkerOptions().position(ABN).title("ABN CNG Station"+"\n"+"Kallyanpur"+"\n"+"Last Update: " + cng_data_time.get(14)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_blue))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ABN, 13));

                            } else if (cng_data_jam.get(14).equals("Yellow")) {
                                marker = mMap.addMarker(new MarkerOptions().position(ABN).title("ABN CNG Station"+"\n"+"Kallyanpur"+"\n"+"Last Update: " + cng_data_time.get(14)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_yellow))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ABN, 13));

                            } else if (cng_data_jam.get(14).equals("Red")) {
                                marker = mMap.addMarker(new MarkerOptions().position(ABN).title("ABN CNG Station"+"\n"+"Kallyanpur"+"\n"+"Last Update: " + cng_data_time.get(14)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_orange))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ABN, 13));
                            }
                        }
                    }

                    if (Modern_cng.isGreaterThan(minInterval)) {
                        marker = mMap.addMarker(new MarkerOptions().position(Modern).title("Modern CNG Station"+"\n"+"Badda").icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_marker))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Modern, 13));
                    } else {
                        if (cng_data_area.get(15).equals(16)) {
                            if (cng_data_jam.get(15).equals("Pink")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Modern).title("Modern CNG Station"+"\n"+"Badda"+"\n"+"Last Update: " + cng_data_time.get(15)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_pink))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Modern, 13));

                            } else if (cng_data_jam.get(15).equals("Green")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Modern).title("Modern CNG Station"+"\n"+"Badda"+"\n"+"Last Update: " + cng_data_time.get(15)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_green))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Modern, 13));

                            } else if (cng_data_jam.get(15).equals("Blue")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Modern).title("Modern CNG Station"+"\n"+"Badda"+"\n"+"Last Update: " + cng_data_time.get(15)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_blue))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Modern, 13));

                            } else if (cng_data_jam.get(15).equals("Yellow")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Modern).title("Modern CNG Station"+"\n"+"Badda"+"\n"+"Last Update: " + cng_data_time.get(15)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_yellow))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Modern, 13));

                            } else if (cng_data_jam.get(15).equals("Red")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Modern).title("Modern CNG Station"+"\n"+"Badda"+"\n"+"Last Update: " + cng_data_time.get(15)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_orange))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Modern, 13));
                            }
                        }
                    }

                    if (Trust_cng.isGreaterThan(minInterval)) {
                        marker = mMap.addMarker(new MarkerOptions().position(Trust).title("Trust CNG Station"+"\n"+"Old Airport Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_marker))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Trust, 13));
                    } else {
                        if (cng_data_area.get(16).equals(17)) {
                            if (cng_data_jam.get(16).equals("Pink")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Trust).title("Trust CNG Station"+"\n"+"Old Airport Road"+"\n"+"Last Update: " + cng_data_time.get(16)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_pink))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Trust, 13));

                            } else if (cng_data_jam.get(16).equals("Green")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Trust).title("Trust CNG Station"+"\n"+"Old Airport Road"+"\n"+"Last Update: " + cng_data_time.get(16)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_green))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Trust, 13));

                            } else if (cng_data_jam.get(16).equals("Blue")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Trust).title("Trust CNG Station"+"\n"+"Old Airport Road"+"\n"+"Last Update: " + cng_data_time.get(16)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_blue))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Trust, 13));

                            } else if (cng_data_jam.get(16).equals("Yellow")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Trust).title("Trust CNG Station"+"\n"+"Old Airport Road"+"\n"+"Last Update: " + cng_data_time.get(16)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_yellow))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Trust, 13));

                            } else if (cng_data_jam.get(16).equals("Red")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Trust).title("Trust CNG Station"+"\n"+"Old Airport Road"+"\n"+"Last Update: " + cng_data_time.get(16)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_orange))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Trust, 13));
                            }
                        }
                    }

                    if (Purbachall_cng.isGreaterThan(minInterval)) {
                        marker = mMap.addMarker(new MarkerOptions().position(Purbachall).title("Purbachall CNG Station"+"\n"+"Mirpur Technical").icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_marker))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Purbachall, 13));
                    } else {
                        if (cng_data_area.get(17).equals(18)) {
                            if (cng_data_jam.get(17).equals("Pink")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Purbachall).title("Purbachall CNG Station"+"\n"+"Mirpur Technical"+"\n"+"Last Update: " + cng_data_time.get(17)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_pink))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Purbachall, 13));

                            } else if (cng_data_jam.get(17).equals("Green")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Purbachall).title("Purbachall CNG Station"+"\n"+"Mirpur Technical"+"\n"+"Last Update: " + cng_data_time.get(17)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_green))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Purbachall, 13));

                            } else if (cng_data_jam.get(17).equals("Blue")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Purbachall).title("Purbachall CNG Station"+"\n"+"Mirpur Technical"+"\n"+"Last Update: " + cng_data_time.get(17)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_blue))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Purbachall, 13));

                            } else if (cng_data_jam.get(17).equals("Yellow")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Purbachall).title("Purbachall CNG Station"+"\n"+"Mirpur Technical"+"\n"+"Last Update: " + cng_data_time.get(17)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_yellow))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Purbachall, 13));

                            } else if (cng_data_jam.get(17).equals("Red")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Purbachall).title("Purbachall CNG Station"+"\n"+"Mirpur Technical"+"\n"+"Last Update: " + cng_data_time.get(17)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_orange))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Purbachall, 13));
                            }
                        }
                    }

                    if (ABN_2_cng.isGreaterThan(minInterval)) {
                        marker = mMap.addMarker(new MarkerOptions().position(ABN_2).title("ABN CNG Station"+"\n"+"Rasulbagh").icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_marker))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ABN_2, 13));
                    } else {
                        if (cng_data_area.get(18).equals(19)) {
                            if (cng_data_jam.get(18).equals("Pink")) {
                                marker = mMap.addMarker(new MarkerOptions().position(ABN_2).title("ABN CNG Station"+"\n"+"Rasulbagh"+"\n"+"Last Update: " + cng_data_time.get(18)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_pink))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ABN_2, 13));

                            } else if (cng_data_jam.get(18).equals("Green")) {
                                marker = mMap.addMarker(new MarkerOptions().position(ABN_2).title("ABN CNG Station"+"\n"+"Rasulbagh"+"\n"+"Last Update: " + cng_data_time.get(18)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_green))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ABN_2, 13));

                            } else if (cng_data_jam.get(18).equals("Blue")) {
                                marker = mMap.addMarker(new MarkerOptions().position(ABN_2).title("ABN CNG Station"+"\n"+"Rasulbagh"+"\n"+"Last Update: " + cng_data_time.get(18)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_blue))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ABN_2, 13));

                            } else if (cng_data_jam.get(18).equals("Yellow")) {
                                marker = mMap.addMarker(new MarkerOptions().position(ABN_2).title("ABN CNG Station"+"\n"+"Rasulbagh"+"\n"+"Last Update: " + cng_data_time.get(18)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_yellow))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ABN_2, 13));

                            } else if (cng_data_jam.get(18).equals("Red")) {
                                marker = mMap.addMarker(new MarkerOptions().position(ABN_2).title("ABN CNG Station"+"\n"+"Rasulbagh"+"\n"+"Last Update: " + cng_data_time.get(18)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_orange))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ABN_2, 13));
                            }
                        }
                    }

                    if (Royel_cng.isGreaterThan(minInterval)) {
                        marker = mMap.addMarker(new MarkerOptions().position(Royel).title("Royel CNG Station"+"\n"+"Rasulbagh").icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_marker))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Royel, 13));
                    } else {
                        if (cng_data_area.get(19).equals(20)) {
                            if (cng_data_jam.get(19).equals("Pink")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Royel).title("Royel CNG Station"+"\n"+"Rasulbagh"+"\n"+"Last Update: " + cng_data_time.get(19)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_pink))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Royel, 13));

                            } else if (cng_data_jam.get(19).equals("Green")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Royel).title("Royel CNG Station"+"\n"+"Rasulbagh"+"\n"+"Last Update: " + cng_data_time.get(19)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_green))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Royel, 13));

                            } else if (cng_data_jam.get(19).equals("Blue")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Royel).title("Royel CNG Station"+"\n"+"Rasulbagh"+"\n"+"Last Update: " + cng_data_time.get(19)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_blue))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Royel, 13));

                            } else if (cng_data_jam.get(19).equals("Yellow")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Royel).title("Royel CNG Station"+"\n"+"Rasulbagh"+"\n"+"Last Update: " + cng_data_time.get(19)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_yellow))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Royel, 13));

                            } else if (cng_data_jam.get(19).equals("Red")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Royel).title("Royel CNG Station"+"\n"+"Rasulbagh"+"\n"+"Last Update: " + cng_data_time.get(19)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_orange))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Royel, 13));
                            }
                        }
                    }

                    if (Zaman_cng.isGreaterThan(minInterval)) {
                        marker = mMap.addMarker(new MarkerOptions().position(Zaman).title("Zaman CNG Station"+"\n"+"Badda").icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_marker))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Zaman, 13));
                    } else {
                        if (cng_data_area.get(20).equals(21)) {
                            if (cng_data_jam.get(20).equals("Pink")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Zaman).title("Zaman CNG Station"+"\n"+"Badda"+"\n"+"Last Update: " + cng_data_time.get(20)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_pink))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Zaman, 13));

                            } else if (cng_data_jam.get(20).equals("Green")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Zaman).title("Zaman CNG Station"+"\n"+"Badda"+"\n"+"Last Update: " + cng_data_time.get(20)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_green))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Zaman, 13));

                            } else if (cng_data_jam.get(20).equals("Blue")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Zaman).title("Zaman CNG Station"+"\n"+"Badda"+"\n"+"Last Update: " + cng_data_time.get(20)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_blue))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Zaman, 13));

                            } else if (cng_data_jam.get(20).equals("Yellow")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Zaman).title("Zaman CNG Station"+"\n"+"Badda"+"\n"+"Last Update: " + cng_data_time.get(20)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_yellow))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Zaman, 13));

                            } else if (cng_data_jam.get(20).equals("Red")) {
                                marker = mMap.addMarker(new MarkerOptions().position(Zaman).title("Zaman CNG Station"+"\n"+"Badda"+"\n"+"Last Update: " + cng_data_time.get(20)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_orange))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Zaman, 13));
                            }
                        }
                    }

                    if (Sonar_bangla_cng.isGreaterThan(minInterval)) {
                        marker = mMap.addMarker(new MarkerOptions().position(sonar_bangla).title("Sonar Bangla CNG Filling Station"+"\n"+"Bakshi Bazar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_marker))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sonar_bangla, 13));
                    } else {
                        if (cng_data_area.get(21).equals(24)) {
                            if (cng_data_jam.get(21).equals("Pink")) {
                                marker = mMap.addMarker(new MarkerOptions().position(sonar_bangla).title("Sonar Bangla CNG Filling Station"+"\n"+"Bakshi Bazar Road"+"\n"+"Last Update: " + cng_data_time.get(21)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_pink))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sonar_bangla, 13));

                            } else if (cng_data_jam.get(21).equals("Green")) {
                                marker = mMap.addMarker(new MarkerOptions().position(sonar_bangla).title("Sonar Bangla CNG Filling Station"+"\n"+"Bakshi Bazar Road"+"\n"+"Last Update: " + cng_data_time.get(21)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_green))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sonar_bangla, 13));

                            } else if (cng_data_jam.get(21).equals("Blue")) {
                                marker = mMap.addMarker(new MarkerOptions().position(sonar_bangla).title("Sonar Bangla CNG Filling Station"+"\n"+"Bakshi Bazar Road"+"\n"+"Last Update: " + cng_data_time.get(21)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_blue))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sonar_bangla, 13));

                            } else if (cng_data_jam.get(21).equals("Yellow")) {
                                marker = mMap.addMarker(new MarkerOptions().position(sonar_bangla).title("Sonar Bangla CNG Filling Station" + "\n" + "Bakshi Bazar Road" +"\n"+ "Last Update: " + cng_data_time.get(21)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_yellow))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sonar_bangla, 13));

                            } else if (cng_data_jam.get(21).equals("Red")) {
                                marker = mMap.addMarker(new MarkerOptions().position(sonar_bangla).title("Sonar Bangla CNG Filling Station"+"\n"+"Bakshi Bazar Road"+"\n"+"Last Update: " + cng_data_time.get(21)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_orange))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sonar_bangla, 13));
                            }
                        }
                    }

                    if (Minerva_cng.isGreaterThan(minInterval)) {
                        marker = mMap.addMarker(new MarkerOptions().position(minerva).title("Minerva CNG Filling Station"+"\n"+"Begum Rokeya Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_marker))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(minerva, 13));

                    } else {
                        if (cng_data_area.get(22).equals(25)) {
                            if (cng_data_jam.get(22).equals("Pink")) {
                                marker = mMap.addMarker(new MarkerOptions().position(minerva).title("Minerva CNG Station"+"\n"+"Begum Rokeya Ave"+"\n"+"Last Update: " + cng_data_time.get(22)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_pink))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(minerva, 13));

                            } else if (cng_data_jam.get(22).equals("Green")) {
                                marker = mMap.addMarker(new MarkerOptions().position(minerva).title("Minerva CNG Station"+"\n"+"Begum Rokeya Ave"+"\n"+"Last Update: " + cng_data_time.get(22)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_green))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(minerva, 13));

                            } else if (cng_data_jam.get(22).equals("Blue")) {
                                marker = mMap.addMarker(new MarkerOptions().position(minerva).title("Minerva CNG Station"+"\n"+"Begum Rokeya Ave"+"\n"+"Last Update: " + cng_data_time.get(22)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_blue))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(minerva, 13));

                            } else if (cng_data_jam.get(22).equals("Yellow")) {
                                marker = mMap.addMarker(new MarkerOptions().position(minerva).title("Minerva CNG Station"+"\n"+"Begum Rokeya Ave"+"\n"+"Last Update: " + cng_data_time.get(22)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_yellow))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(minerva, 13));

                            } else if (cng_data_jam.get(22).equals("Red")) {
                                marker = mMap.addMarker(new MarkerOptions().position(minerva).title("Minerva CNG Station"+"\n"+"Begum Rokeya Ave"+"\n"+"Last Update: " + cng_data_time.get(22)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_orange))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(minerva, 13));
                            }
                        }
                    }

                    if (Akij_cng.isGreaterThan(minInterval)) {
                        marker = mMap.addMarker(new MarkerOptions().position(akij).title("Akij CNG Filling Station"+"\n"+"Shaheed Tajuddin Ahmed Ave").icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_marker))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(akij, 13));
                    } else {
                        if (cng_data_area.get(23).equals(26)) {
                            if (cng_data_jam.get(23).equals("Pink")) {
                                marker = mMap.addMarker(new MarkerOptions().position(akij).title("Akij CNG Filling Station"+"\n"+"Shaheed Tajuddin Ahmed Ave"+"\n"+"Last Update: " + cng_data_time.get(23)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_pink))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(akij, 13));

                            } else if (cng_data_jam.get(23).equals("Green")) {
                                marker = mMap.addMarker(new MarkerOptions().position(akij).title("Akij CNG Filling Station"+"\n"+"Shaheed Tajuddin Ahmed Ave"+"\n"+"Last Update: " + cng_data_time.get(23)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_green))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(akij, 13));

                            } else if (cng_data_jam.get(23).equals("Blue")) {
                                marker = mMap.addMarker(new MarkerOptions().position(akij).title("Akij CNG Filling Station"+"\n"+"Shaheed Tajuddin Ahmed Ave"+"\n"+"Last Update: " + cng_data_time.get(23)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_blue))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(akij, 13));

                            } else if (cng_data_jam.get(23).equals("Yellow")) {
                                marker = mMap.addMarker(new MarkerOptions().position(akij).title("Akij CNG Filling Station"+"\n"+"Shaheed Tajuddin Ahmed Ave"+"\n"+"Last Update: " + cng_data_time.get(23)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_yellow))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(akij, 13));

                            } else if (cng_data_jam.get(23).equals("Red")) {
                                marker = mMap.addMarker(new MarkerOptions().position(akij).title("Akij CNG Filling Station"+"\n"+"Shaheed Tajuddin Ahmed Ave"+"\n"+"Last Update: " + cng_data_time.get(23)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_orange))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(akij, 13));
                            }
                        }
                    }

                    if (Eastern_cng.isGreaterThan(minInterval)) {
                        marker = mMap.addMarker(new MarkerOptions().position(eastern).title("Best Eastern CNG Filling Station"+"\n"+"Atish Deepankar Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_marker))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eastern, 13));
                    } else {
                        if (cng_data_area.get(24).equals(27)) {
                            if (cng_data_jam.get(24).equals("Pink")) {
                                marker = mMap.addMarker(new MarkerOptions().position(eastern).title("Best Eastern CNG Filling Station"+"\n"+"Atish Deepankar Road"+"\n"+"Last Update: " + cng_data_time.get(24)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_pink))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eastern, 13));

                            } else if (cng_data_jam.get(24).equals("Green")) {
                                marker = mMap.addMarker(new MarkerOptions().position(eastern).title("Best Eastern CNG Filling Station"+"\n"+"Atish Deepankar Road"+"\n"+"Last Update: " + cng_data_time.get(24)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_green))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eastern, 13));

                            } else if (cng_data_jam.get(24).equals("Blue")) {
                                marker = mMap.addMarker(new MarkerOptions().position(eastern).title("Best Eastern CNG Filling Station"+"\n"+"Atish Deepankar Road"+"\n"+"Last Update: " + cng_data_time.get(24)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_blue))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eastern, 13));

                            } else if (cng_data_jam.get(24).equals("Yellow")) {
                                marker = mMap.addMarker(new MarkerOptions().position(eastern).title("Best Eastern CNG Filling Station"+"\n"+"Atish Deepankar Road"+"\n"+"Last Update: " + cng_data_time.get(24)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_yellow))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eastern, 13));

                            } else if (cng_data_jam.get(24).equals("Red")) {
                                marker = mMap.addMarker(new MarkerOptions().position(eastern).title("Best Eastern CNG Filling Station"+"\n"+"Atish Deepankar Road"+"\n"+"Last Update: " + cng_data_time.get(24)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_orange))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eastern, 13));
                            }
                        }
                    }

                    if (Feet_cng.isGreaterThan(minInterval)) {
                        marker = mMap.addMarker(new MarkerOptions().position(feet).title("24 Feet CNG Station"+"\n"+"Zia Sarani Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_marker))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(feet, 13));
                    } else {
                        if (cng_data_area.get(25).equals(28)) {
                            if (cng_data_jam.get(25).equals("Pink")) {
                                marker = mMap.addMarker(new MarkerOptions().position(feet).title("24 Feet CNG Station"+"\n"+"Zia Sarani Road"+"\n"+"Last Update: " + cng_data_time.get(25)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_pink))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(feet, 13));

                            } else if (cng_data_jam.get(25).equals("Green")) {
                                marker = mMap.addMarker(new MarkerOptions().position(feet).title("24 Feet CNG Station"+"\n"+"Zia Sarani Road"+"\n"+"Last Update: " + cng_data_time.get(25)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_green))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(feet, 13));

                            } else if (cng_data_jam.get(25).equals("Blue")) {
                                marker = mMap.addMarker(new MarkerOptions().position(feet).title("24 Feet CNG Station"+"\n"+"Zia Sarani Road"+"\n"+"Last Update: " + cng_data_time.get(25)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_blue))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(feet, 13));

                            } else if (cng_data_jam.get(25).equals("Yellow")) {
                                marker = mMap.addMarker(new MarkerOptions().position(feet).title("24 Feet CNG Station"+"\n"+"Zia Sarani Road"+"\n"+"Last Update: " + cng_data_time.get(25)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_yellow))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(feet, 13));

                            } else if (cng_data_jam.get(25).equals("Red")) {
                                marker = mMap.addMarker(new MarkerOptions().position(feet).title("24 Feet CNG Station"+"\n"+"Zia Sarani Road"+"\n"+"Last Update: " + cng_data_time.get(25)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_orange))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(feet, 13));
                            }
                        }
                    }

                    if (Nil_cng.isGreaterThan(minInterval)) {
                        marker = mMap.addMarker(new MarkerOptions().position(nil).title("Nilkhet CNG Station"+"\n"+"Nilkhet").icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_marker))));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nil, 13));
                    } else {
                        if (cng_data_area.get(26).equals(29)) {
                            if (cng_data_jam.get(26).equals("Pink")) {
                                marker = mMap.addMarker(new MarkerOptions().position(nil).title("Nilkhet CNG Station"+"\n"+"Nilkhet"+"\n"+"Last Update: " + cng_data_time.get(26)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_pink))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nil, 13));

                            } else if (cng_data_jam.get(26).equals("Green")) {
                                marker = mMap.addMarker(new MarkerOptions().position(nil).title("Nilkhet CNG Station"+"\n"+"Nilkhet"+"\n"+"Last Update: " + cng_data_time.get(26)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_green))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nil, 13));

                            } else if (cng_data_jam.get(26).equals("Blue")) {
                                marker = mMap.addMarker(new MarkerOptions().position(nil).title("Nilkhet CNG Station"+"\n"+"Nilkhet"+"\n"+"Last Update: " + cng_data_time.get(26)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_blue))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nil, 13));

                            } else if (cng_data_jam.get(26).equals("Yellow")) {
                                marker = mMap.addMarker(new MarkerOptions().position(nil).title("Nilkhet CNG Station"+"\n"+"Nilkhet"+"\n"+"Last Update: " + cng_data_time.get(26)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_yellow))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nil, 13));

                            } else if (cng_data_jam.get(26).equals("Red")) {
                                marker = mMap.addMarker(new MarkerOptions().position(nil).title("Nilkhet CNG Station"+"\n"+"Nilkhet"+"\n"+"Last Update: " + cng_data_time.get(26)).icon((BitmapDescriptorFactory.fromResource(R.drawable.cng_orange))));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nil, 13));
                            }
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(putRequest);

        //get_all_data();
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

        CngFragment myListFragment = new CngFragment();

        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        fragmentTransaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout, R.anim.fadein, R.anim.fadeout);

        fragmentTransaction.show(myListFragment);

        fragmentTransaction.commit();
    }


    int searchCounter = 0;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Log.e("Search", "Search Selected");
                searchCounter++;
                if (searchCounter % 2 == 0) {
                    llSearchLayout.setVisibility(View.GONE);
                    searchCounter = 0;
                } else {
                    llSearchLayout.setVisibility(View.VISIBLE);
                }
                return true;
            default:
                break;
        }

        return false;

    }


//    AutoCompleteTextView autocomTextView;

    ImageView myImageView;


    // Setting Up the Map after OnCreateView is called

    private void setUpMapIfNeeded_1(View inflatedView) {

        if (mMap == null) {
            mMap = ((MapView) inflatedView.findViewById(R.id.mapView)).getMap();


            if (mMap != null) {
                mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                    @Override
                    public View getInfoWindow(Marker marker) {
                        return null;
                    }

                    @Override
                    public View getInfoContents(Marker marker) {

                        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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


    // Initially Starting the Map and Everything

    private void setUpMap_1() {
        // Check if we were successful in obtaining the map.
        if (mMap != null) {

            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                            PackageManager.PERMISSION_GRANTED) {
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
        sikdar_cng_station();

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


                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                                PackageManager.PERMISSION_GRANTED) {
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

//    AutoCompleteTextView userInputTextView;
      Spinner userInputTextView;


    // Calling the GPSListener and getting the Locate Me to Center

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        // get the button view
        gpsView = (ImageView) getView().findViewById(R.id.gpslocationView);

        doSearchView = (ImageView) getView().findViewById(R.id.searchAreaView);

        // set a onclick listener for when the button gets clicked
        gpsView.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                gpsCounter = 0;

//                CngFragment.this.GetMyLocation();
            }
        });


        doSearchView.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
//                userInputTextView = (AutoCompleteTextView) getView().findViewById(R.id.autoCompleteTextViewOK);
//
//                String userSearchedArea = userInputTextView.getText().toString().trim();

                userInputTextView = (Spinner)getView().findViewById(R.id.spinnerCNG);
                String userSearchedArea = userInputTextView.getSelectedItem().toString();

                ArrayList<LatLng> coordList = new ArrayList<LatLng>();


                coordList = new ArrayList<LatLng>(AreaLatLng);


                String[] areas = getResources().
                        getStringArray(R.array.list_of_cng_station);

                for (int i = 0; i < areas.length; i++) {
                    if (areas[i].equals(userSearchedArea)) {
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordList.get(i), 16.0f));
                    }
                }

                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
            }
        });

        rectangleView_1 = (ImageView) getView().findViewById(R.id.rectimage_1);
        rectangleView_2 = (ImageView) getView().findViewById(R.id.rectimage_2);
        rectangleView_3 = (ImageView) getView().findViewById(R.id.rectimage_3);
        rectangleView_4 = (ImageView) getView().findViewById(R.id.rectimage_4);
        rectangleView_5 = (ImageView) getView().findViewById(R.id.rectimage_5);


        rectangleView_1.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {

                new AlertDialog.Builder(mContext)
                        .setTitle("CNG Station Color Indicator")
                        .setMessage("Approx. Waiting Time: 5 mins")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            }
                        })
                        .show();
            }
        });


        rectangleView_2.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {

                new AlertDialog.Builder(mContext)
                        .setTitle("CNG Station Color Indicator")
                        .setMessage("Approx. Waiting Time: 15 mins")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            }
                        })
                        .show();
            }
        });


        rectangleView_3.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {

                new AlertDialog.Builder(mContext)
                        .setTitle("CNG Station Color Indicator")
                        .setMessage("Approx. Waiting Time: 30 mins")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            }
                        })
                        .show();
            }
        });


        rectangleView_4.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {

                new AlertDialog.Builder(mContext)
                        .setTitle("CNG Station Color Indicator")
                        .setMessage("Approx. Waiting Time: 45 mins")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            }
                        })
                        .show();
            }
        });


        rectangleView_5.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {

                new AlertDialog.Builder(mContext)
                        .setTitle("CNG Station Color Indicator")
                        .setMessage("Approx. Waiting Time: Min 1 hr")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            }
                        })
                        .show();
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
                //MainWorkingMethod();
            }
        }, 3000);

    }


    //------------------------------------------>Ignoring this section start here<-------------------------------------------


    /////////////// Traffic Point 1
    private void TimeCounterIncreaseMethod() {
        timecounter = 1;
    }


    // Method for Instantiating Custom Info Window!!!


    int markerCounter = 0;


//    GoogleApiClient mLocationClient;

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