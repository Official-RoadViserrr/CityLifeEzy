package com.citylifeezy.citylifeezy.fragments;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.citylifeezy.citylifeezy.PlacesAutoCompleteAdapter;
import com.citylifeezy.citylifeezy.RecyclerItemClickListener;
import com.citylifeezy.citylifeezy.activity.R;
import com.citylifeezy.citylifeezy.utils.Constants;
import com.citylifeezy.citylifeezy.utils.GoogleDirection;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Asif on 11/30/2015.
 */
public class BestRouteFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, SearchView.OnQueryTextListener{

    //code for google direction api
    LatLng start;
    LatLng end;

    private GoogleDirection gd;
    Document mDoc;

    //code for google places api

    RelativeLayout llSearchLayout;
    protected GoogleApiClient mGoogleApiClient;
    private static final LatLngBounds BOUNDS_BANGLADESH = new LatLngBounds(
            new LatLng(-0, 0), new LatLng(0, 0));

    private EditText mAutocompleteView1, mAutocompleteView2;
    private RecyclerView mRecyclerView1, mRecyclerView2;
    private LinearLayoutManager mLinearLayoutManager1, mLinearLayoutManager2;
    private PlacesAutoCompleteAdapter mAutoCompleteAdapter1, mAutoCompleteAdapter2;
//    ImageView delete1, delete2;


    private MapView mMapView;
    private GoogleMap mMap;

    SearchView mySearchView;
    ImageView gpsView;
    Button doSearchView;


    // area_info JSONArray
    JSONArray area_info = null;


    private static Context mContext;


    public static BestRouteFragment newInstance(Context context) {
        BestRouteFragment f = new BestRouteFragment();
        Bundle args = new Bundle();
        args.putString("index", context.toString());
        f.setArguments(args);

        mContext = context;
        return f;
    }


    ArrayList<String> areaIdList = new ArrayList<String>();
    ArrayList<String> areaLatLngList = new ArrayList<String>();
    ArrayList<String> Area_Stat_Cond_1List = new ArrayList<String>();
    ArrayList<String> Area_Stat_Cond_2List = new ArrayList<String>();
    ArrayList<String> Area_Stat_Cond_3List = new ArrayList<String>();
    ArrayList<String> Area_Stat_Cond_4List = new ArrayList<String>();

    String AreaLatLngArray[];
    String AreaLatLngArrayFrom[];
    String AreaLatLngArrayTo[];
    String Area_Stat_Cond_1Array[];
    String Area_Stat_Cond_2Array[];
    String Area_Stat_Cond_3Array[];
    String Area_Stat_Cond_4Array[];

//    LatLng area_geo_Code1, area_geo_Code2, area_geo_Code3, area_geo_Code4, finalGeopointsList_geo_Code1;

    String finalGeopointsList[];
//    String AreaCon1GeopointsList[];
//    String AreaCon2GeopointsList[];
//    String AreaCon3GeopointsList[];
//    String AreaCon4GeopointsList[];

    public String[] AreaName = {
            "Abdullahpur Bus Stand", "House Building", "Azampur Intersection", "Rajlaxmi Complex",
            "Jasimuddin Intersection", "Airport Intersection", "Bashundhara R/A Entrance",
            "Badda Notun Bazar Intersection", "Badda Link Road Intersection", "Rampura Bridge",
            "Khilgaon Abul Hotel", "Malibagh Rail Gate", "Mouchak Intersection", "Moghbazar Intersection",
            "Bangla Motor Intersection", "Ruposi Bangla Hotel", "Malibagh-Shantinagar Intersection", "Shapla Chattar",
            "Fakirapool Intersection", "Dainik Bangla", "Purana Paltan Intersection", "Nilkhet Intersection",
            "Highcourt Main Gate", "Press Club Gate", "Shahbagh Intersection", "Elephant Road Bata Signal",
            "Science Lab Intersection", "Jigatola Bus Stand", "Shankar Bus Stand", "Lalmatia End", "Asad Gate",
            "Khamarbari Intersection", "Farmgate Center", "Shatrasta Intersection", "Nabisco", "Gulshan 1 Intersection",
            "Gulshan 2 Intersection", "Kamal Ataturk Avenue (Mr. Baker)", "Kakoli Intersection", "Amtoli Intersection",
            "Mohakhali Intersection", "Bijoy Sarani", "Mirpur 10 Intersection", "Mirpur 1 Intersection",
            "Gabtoli Technical Intersection", "Shyamoli Hall", "FDC Intersection", "Mohakhali Rupayan Center",
            "Bijaynagar Intersection", "Khilgaon Railgate Intersection", "Russell Square Intersection",
            "Dhanmondi 27 Persona", "Karwanbazaar Intersection", "Panthapath-Green Road Intersection",
            "Kalabagan Intersection",
            "Jahangir Gate", "Badda Post Office Point", "Khilkhet CNG Station Point", "Kakrail Comet CNG Point",
            "Madani Rd Bridge Point", "Gulshan 2 HSBC Point", "Rasulbagh Point", "Trust Filling Station Point",
            "Gulshan SCB Head Office", "Hazipara CNG Station Point", "Jamuna CNG Station Point", "Merul Filling Station Point",
            "Brac University", "Labaid Dhanmondi", "Kallyanpur Point", "Navana CNG Point", "Mirpur Shahjalal CNG Station Point",
            "Mirpur Aria CNG Station Point", "Nilkhet Kataban Intersection",
            "Dhanmondi 7 Intersection", "Kataban Intersection", "Kakrail Masjid - Officers Club Intersection",
            "Mirpur Thana Intersection", "Agargaon Bus Stand Intersection", "Chankharpul Intersection", "Kuril Bus Stand",
            "Gulshan 2 Labaid Point", "Banani Artisan Point", "Westin Point", "Gulshan Agora", "Link Road Bridge Point",
            "Vasavi Point", "Brac Center", "Titumir College", "Sainik Club Point", "Square Hospital", "Green Road Middle Section",
            "Chandrima Uddan Intersection", "Gulshan 2 Sweden Embassy", "Rabindra Sarobar Front", "Dhanmondi 8 Bridge",
            "Gulshan Rd 34 Point", "Gulshan Rd to 34 Banani Bridge Point", "Gulshan 35/37 Intersection"
    };


    public int[] Jam_Area_1 = {
            1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 18, 18, 17, 18, 19, 22, 21, 22, 25, 24, 25,
            26, 29, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 43, 42, 43, 44, 52, 39, 20, 11, 30, 28,
            53, 32, 26, 41, 9, 6, 22, 7, 36, 34, 55, 36, 12, 13, 9, 35, 26, 45, 35, 42, 42, 14, 26, 24, 18, 42,
            41, 24, 6, 36, 81, 36, 36, 8, 35, 35, 35, 40, 50, 26, 42, 36, -9, -9, -9, -9, -9
    };

    public int[] Jam_Area_2 = {
            -9, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 24, 12, 16, 19, 20, 23, 26, 23, 20, 22, 26, 27,
            28, 51, 45, 31, 32, 33, 34, 40, 36, 37, 38, 39, 40, 41, 32, 38, 44, 45, 30, 33, 35, 19, 18, 26, 30,
            32, 52, 50, 40, 8, 5, 18, 6, 7, 40, 41, 35, 9, 46, 8, 40, 50, 44, 34, 31, 38, 22, 50, 52, 13, 43, 41,
            21, 57, 82, 37, 84, 35, 35, 40, 40, 40, 38, 53, 53, 41, 6, -9, -9, -9, -9, -9
    };

    public int[] Jam_Area_3 = {
            -9, -9, -9, -9, -9, -9, -9, 36, 35, 35, -9, 49, 16, 46, 52, 13, -9, 19, 16, 48, 48, 25, -9, -9, 15, 21,
            21, -9, 27, 30, 45, 42, 52, 46, 35, 47, 7, -9, 5, 47, 34, 34, 31, -9, -9, 29, 13, -9, -9, -9, 53, -9, 46,
            26, -9, -9, -9, -9, -9, 36, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 17, -9, 26, 22, -9, 42,
            -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 31, -9, -9, -9, -9, -9, -9
    };

    public int[] Jam_Area_4 = {
            -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 48, 25, -9, -9, -9, -9, 17, 22, 27, -9, -9, 23, 52,
            50, -9, -9, -9, -9, -9, 41, -9, -9, 8, 6, -9, -9, -9, -9, 30, 5, -9, -9, -9, 9, -9, -9, -9, -9, -9, 14,
            50, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 21, -9, -9, -9, -9,
            -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 45, -9, -9, -9, -9, -9, -9, -9
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        GetAreaInfo();

        buildGoogleApiClient();
        View rootview = inflater.inflate(R.layout.fragment_best_route, container, false);
        super.onCreate(savedInstanceState);

        mMapView = (MapView) rootview.findViewById(R.id.mapView);

        mMap = mMapView.getMap();

        llSearchLayout = (RelativeLayout) rootview.findViewById(R.id.llSearchLayout);

        mAutocompleteView1 = (EditText) rootview.findViewById(R.id.autocomplete_places1);

//        delete1 = (ImageView) rootview.findViewById(R.id.cross1);

        mAutoCompleteAdapter1 = new PlacesAutoCompleteAdapter(this.getActivity(), R.layout.searchview_adapter,
                mGoogleApiClient, BOUNDS_BANGLADESH, null);

        mRecyclerView1 = (RecyclerView) rootview.findViewById(R.id.recyclerView1);
        mLinearLayoutManager1 = new LinearLayoutManager(this.getActivity());
        mRecyclerView1.setLayoutManager(mLinearLayoutManager1);
        mRecyclerView1.setAdapter(mAutoCompleteAdapter1);
//        delete1.setOnClickListener(this);



        mAutocompleteView1.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (!s.toString().equals("") && mGoogleApiClient.isConnected()) {
                    mAutoCompleteAdapter1.getFilter().filter(s.toString());
                    mRecyclerView1.setVisibility(View.VISIBLE);
                } else if (!mGoogleApiClient.isConnected()) {
                    Toast.makeText(mContext, Constants.API_NOT_CONNECTED, Toast.LENGTH_SHORT).show();
                    Log.e(Constants.PlacesTag, Constants.API_NOT_CONNECTED);
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void afterTextChanged(Editable s) {

            }
        });
        mRecyclerView1.addOnItemTouchListener(
                new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        final PlacesAutoCompleteAdapter.PlaceAutocomplete item = mAutoCompleteAdapter1.getItem(position);
                        final String placeId = String.valueOf(item.placeId);
                        Log.i("TAG", "Autocomplete item selected: " + item.description);
                        /*
                             Issue a request to the Places Geo Data API to retrieve a Place object with additional details about the place.
                         */

                        PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                                .getPlaceById(mGoogleApiClient, placeId);
                        placeResult.setResultCallback(new ResultCallback<PlaceBuffer>() {
                            @Override
                            public void onResult(PlaceBuffer places) {
                                if (places.getCount() == 1) {
                                    //Do the things here on Click.....
//                                    mRecyclerView2.setVisibility(View.GONE);
                                    mAutocompleteView1.setText(String.valueOf(places.get(0).getAddress()));
                                    start = places.get(0).getLatLng();
                                    mRecyclerView1.setVisibility(View.GONE);
//                                    Toast.makeText(mContext, String.valueOf(places.get(0).getLatLng()), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(mContext, Constants.SOMETHING_WENT_WRONG, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        Log.i("TAG", "Clicked: " + item.description);
                        Log.i("TAG", "Called getPlaceById to get Place details for " + item.placeId);
                    }
                })
        );

        mAutocompleteView2 = (EditText) rootview.findViewById(R.id.autocomplete_places2);

//        delete2 = (ImageView) rootview.findViewById(R.id.cross2);

        mAutoCompleteAdapter2 = new PlacesAutoCompleteAdapter(this.getActivity(), R.layout.searchview_adapter,
                mGoogleApiClient, BOUNDS_BANGLADESH, null);

        mRecyclerView2 = (RecyclerView) rootview.findViewById(R.id.recyclerView2);
        mLinearLayoutManager2 = new LinearLayoutManager(this.getActivity());
        mRecyclerView2.setLayoutManager(mLinearLayoutManager2);
        mRecyclerView2.setAdapter(mAutoCompleteAdapter2);
//        delete2.setOnClickListener(this);

        mAutocompleteView2.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (!s.toString().equals("") && mGoogleApiClient.isConnected()) {

                    mAutoCompleteAdapter2.getFilter().filter(s.toString());
                    mRecyclerView2.setVisibility(View.VISIBLE);
                } else if (!mGoogleApiClient.isConnected()) {
                    Toast.makeText(mContext, Constants.API_NOT_CONNECTED, Toast.LENGTH_SHORT).show();
                    Log.e(Constants.PlacesTag, Constants.API_NOT_CONNECTED);
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void afterTextChanged(Editable s) {

            }
        });
        mRecyclerView2.addOnItemTouchListener(
                new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        final PlacesAutoCompleteAdapter.PlaceAutocomplete item = mAutoCompleteAdapter2.getItem(position);
                        final String placeId = String.valueOf(item.placeId);
                        Log.i("TAG", "Autocomplete item selected: " + item.description);
                        /*
                             Issue a request to the Places Geo Data API to retrieve a Place object with additional details about the place.
                         */

                        PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                                .getPlaceById(mGoogleApiClient, placeId);
                        placeResult.setResultCallback(new ResultCallback<PlaceBuffer>() {
                            @Override
                            public void onResult(PlaceBuffer places) {
                                if (places.getCount() == 1) {
                                    //Do the things here on Click.....
//                                    mRecyclerView2.setVisibility(View.GONE);
                                    mAutocompleteView2.setText(String.valueOf(places.get(0).getAddress()));
                                    end = places.get(0).getLatLng();
                                    mRecyclerView2.setVisibility(View.GONE);
//                                    Toast.makeText(mContext, String.valueOf(places.get(0).getLatLng()), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(mContext, Constants.SOMETHING_WENT_WRONG, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        Log.i("TAG", "Clicked: " + item.description);
                        Log.i("TAG", "Called getPlaceById to get Place details for " + item.placeId);
                    }
                })
        );


        timecounter = 0;
        // inflat and return the layout

        mMapView = (MapView) rootview.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);


        setUpMapIfNeeded_1(rootview);

        return rootview;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected synchronized void buildGoogleApiClient() {


        mGoogleApiClient = new GoogleApiClient.Builder(mContext)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .addConnectionCallbacks(this)
                .build();

    }

    @Override
    public void onStart() {
        super.onStart();
        if (mGoogleApiClient != null)
            mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }



//    @Override
//    public void onClick(View v) {
//        if (v == delete1) {
//            mAutocompleteView1.setText("");
//        } else if (v == delete2) {
//            mAutocompleteView2.setText("");
//        }
//    }


    // Calling the GPSListener and getting the Locate Me to Center


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        // get the button view
        gpsView = (ImageView) getView().findViewById(R.id.gpslocationView);

        doSearchView = (Button) getView().findViewById(R.id.searchAreaView);


        // set a onclick listener for when the button gets clicked
        gpsView.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                gpsCounter = 0;

                BestRouteFragment.this.GetMyLocation();
            }
        });
        doSearchView.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                llSearchLayout.setVisibility(View.GONE);
                CalculateBestRoute();

                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
            }
        });


    }


    public void CalculateBestRoute() {


        mRecyclerView1.addOnItemTouchListener(
                new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        final PlacesAutoCompleteAdapter.PlaceAutocomplete item = mAutoCompleteAdapter1.getItem(position);
                        final String placeId = String.valueOf(item.placeId);
                        Log.i("TAG", "Autocomplete item selected: " + item.description);
                        /*
                             Issue a request to the Places Geo Data API to retrieve a Place object with additional details about the place.
                         */

                        PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                                .getPlaceById(mGoogleApiClient, placeId);
                        placeResult.setResultCallback(new ResultCallback<PlaceBuffer>() {
                            @Override
                            public void onResult(PlaceBuffer places) {
                                if (places.getCount() == 1) {
                                    //Do the things here on Click.....
//                                    mRecyclerView1.setVisibility(View.GONE);
                                    mAutocompleteView1.setText(String.valueOf(places.get(0).getAddress()));
                                    start = places.get(0).getLatLng();
                                    mRecyclerView1.setVisibility(View.GONE);
//                                    Toast.makeText(mContext, String.valueOf(places.get(0).getLatLng()), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(mContext, Constants.SOMETHING_WENT_WRONG, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        Log.i("TAG", "Clicked: " + item.description);
                        Log.i("TAG", "Called getPlaceById to get Place details for " + item.placeId);
                    }
                })
        );


        mRecyclerView2.addOnItemTouchListener(
                new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        final PlacesAutoCompleteAdapter.PlaceAutocomplete item = mAutoCompleteAdapter2.getItem(position);
                        final String placeId = String.valueOf(item.placeId);
                        Log.i("TAG", "Autocomplete item selected: " + item.description);
                        /*
                             Issue a request to the Places Geo Data API to retrieve a Place object with additional details about the place.
                         */

                        PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                                .getPlaceById(mGoogleApiClient, placeId);
                        placeResult.setResultCallback(new ResultCallback<PlaceBuffer>() {
                            @Override
                            public void onResult(PlaceBuffer places) {
                                if (places.getCount() == 1) {
                                    //Do the things here on Click.....
//                                    mRecyclerView2.setVisibility(View.GONE);
                                    mAutocompleteView2.setText(String.valueOf(places.get(0).getAddress()));
                                    end = places.get(0).getLatLng();
                                    mRecyclerView2.setVisibility(View.GONE);
//                                    Toast.makeText(mContext, String.valueOf(places.get(0).getLatLng()), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(mContext, Constants.SOMETHING_WENT_WRONG, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        Log.i("TAG", "Clicked: " + item.description);
                        Log.i("TAG", "Called getPlaceById to get Place details for " + item.placeId);
                    }
                })
        );

        mMapView = (MapView) getView().findViewById(R.id.mapView);

        mMap = mMapView.getMap();

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(start, 14));

        gd = new GoogleDirection(getActivity());

        gd.setOnDirectionResponseListener(new GoogleDirection.OnDirectionResponseListener() {
            public void onResponse(String status, Document doc, GoogleDirection gd) {

                mDoc = doc;

                mMap.addMarker(new MarkerOptions().position(start)
                        .title("Starting point")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_source)));

                mMap.addMarker(new MarkerOptions().position(end)
                        .title("Ending point")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_source)));

                gd.animateDirection(mMap, gd.getDirection(doc), GoogleDirection.SPEED_VERY_FAST
                        , true, true, true, false, null, false, true,
                        new PolylineOptions().width(5).color(Color.parseColor("#6a84ef")).geodesic(true));


            }
        });

        gd.request(start, end, GoogleDirection.MODE_DRIVING);

    }


    private void GetAreaInfo() {

        RequestQueue queue = Volley.newRequestQueue(this.getActivity());

        StringRequest putRequest = new StringRequest(Request.Method.GET,
                "http://roadviserrr.net/all_points_json.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response:::", response);

                        if (response != null) {

                            try {
                                JSONObject jsonObj = new JSONObject(response);

                                area_info = jsonObj.getJSONArray("area_info");

                                Log.d("Response area_info: ", ">");

                                for (int i = 0; i < area_info.length(); i++) {
                                    JSONObject c = area_info.getJSONObject(i);

                                    // Storing each json item in variable

                                    String area_id = c.getString("area_id");
                                    String area_LatLng = c.getString("area_LatLng");
                                    String Area_Stat_Cond_1 = c.getString("Area_Stat_Cond_1");
                                    String Area_Stat_Cond_2 = c.getString("Area_Stat_Cond_2");
                                    String Area_Stat_Cond_3 = c.getString("Area_Stat_Cond_3");
                                    String Area_Stat_Cond_4 = c.getString("Area_Stat_Cond_4");

                                    areaIdList.add(area_id);
                                    areaLatLngList.add(area_LatLng);
                                    Area_Stat_Cond_1List.add(Area_Stat_Cond_1);
                                    Area_Stat_Cond_2List.add(Area_Stat_Cond_2);
                                    Area_Stat_Cond_3List.add(Area_Stat_Cond_3);
                                    Area_Stat_Cond_4List.add(Area_Stat_Cond_4);

                                    Log.d("this is my array", "Areacon" + Area_Stat_Cond_1List);


                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

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
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        BestRouteFragment myListFragment = new BestRouteFragment();

        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        fragmentTransaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout, R.anim.fadein, R.anim.fadeout);

        fragmentTransaction.show(myListFragment);

        fragmentTransaction.commit();

    }


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
                        View v = inflater.inflate(R.layout.windowlayout, null);

                        TextView areaName = (TextView) v.findViewById(R.id.AreaName);
                        TextView areaCondition_1 = (TextView) v.findViewById(R.id.AreaCond1);
                        TextView areaCondition_2 = (TextView) v.findViewById(R.id.AreaCond2);
                        TextView areaCondition_3 = (TextView) v.findViewById(R.id.AreaCond3);
                        TextView areaCondition_4 = (TextView) v.findViewById(R.id.AreaCond4);
                        TextView lastupdated = (TextView) v.findViewById(R.id.LastUpdated);

//                        LinearLayout myLinearLayout = (LinearLayout) v.findViewById(R.id.linearWindowLayout);

                        String temp[], temp_area4[], temp_area3[], temp_area2[], temp_area1[];

                        try {
                            String Details = marker.getSnippet();
                            String Title = marker.getTitle();

                            if (Title.equals(" ") || Title == null || Title.equals("") || Title.isEmpty()) {
                                areaCondition_1.setText("A");
                                areaCondition_2.setText("A");
                                areaCondition_3.setText("A");
                                areaCondition_4.setText("A");
                                lastupdated.setText("A");
                                areaName.setText("A");

                                v.setVisibility(View.GONE);
//                                myLinearLayout.setVisibility(View.GONE);

                                areaCondition_1.setVisibility(View.GONE);
                                areaCondition_2.setVisibility(View.GONE);
                                areaCondition_3.setVisibility(View.GONE);
                                areaCondition_4.setVisibility(View.GONE);
                                lastupdated.setVisibility(View.GONE);
                                areaName.setVisibility(View.GONE);

                            }

                            if (Title.equals("Red") || Title.equals("Pink") || Title.equals("Yellow") || Title.equals("Blue") || Title.equals("Green")) {
                                if (Title.equals("Red")) {
                                    areaName.setText("Extreme traffic | No movement | Min 20 min to cross");
                                }

                                if (Title.equals("Pink")) {
                                    areaName.setText("Heavy traffic | Very slow movement | Apprx 10-20 min to cross");
                                }

                                if (Title.equals("Yellow")) {
                                    areaName.setText("Moderate traffic | Moderate movement | Apprx 5-10 min to cross");
                                }

                                if (Title.equals("Blue")) {
                                    areaName.setText("Low traffic | Normal movement | Apprx 2-5 min to cross");
                                }

                                if (Title.equals("Green")) {
                                    areaName.setText("Free road | Uninterrupted movement | Apprx 2 min to cross");
                                }

                                areaCondition_1.setVisibility(View.GONE);
                                areaCondition_2.setVisibility(View.GONE);
                                areaCondition_3.setVisibility(View.GONE);
                                areaCondition_4.setVisibility(View.GONE);
                                lastupdated.setVisibility(View.GONE);
                            } else if (Details.equals("") || Details == null || Details.equals("A")) {


                                areaCondition_1.setText("A");
                                areaCondition_2.setText("A");
                                areaCondition_3.setText("A");
                                areaCondition_4.setText("A");
                                lastupdated.setText("A");

                                areaCondition_1.setVisibility(View.GONE);
                                areaCondition_2.setVisibility(View.GONE);
                                areaCondition_3.setVisibility(View.GONE);
                                areaCondition_4.setVisibility(View.GONE);
                                lastupdated.setVisibility(View.GONE);


                                areaName.setText(marker.getTitle());

                            } else {
                                String delimeter = "§";

                                temp = Details.split(delimeter);

                                areaCondition_1.setText(temp[0]);
                                areaCondition_2.setText(temp[1]);
                                areaCondition_3.setText(temp[2]);
                                areaCondition_4.setText(temp[3]);
                                lastupdated.setText(temp[4]);


                                if (temp[3].contains("Green")) {
                                    temp[3] = temp[3].replace(" : Green", "");
                                    areaCondition_4.setText(temp[3]);
                                    areaCondition_4.setBackgroundColor(Color.parseColor("#22FF1C"));
                                }

                                if (temp[2].contains("Green")) {
                                    temp[2] = temp[2].replace(" : Green", "");
                                    areaCondition_3.setText(temp[2]);
                                    areaCondition_3.setBackgroundColor(Color.parseColor("#22FF1C"));
                                }

                                if (temp[1].contains("Green")) {
                                    temp[1] = temp[1].replace(" : Green", "");
                                    areaCondition_2.setText(temp[1]);
                                    areaCondition_2.setBackgroundColor(Color.parseColor("#22FF1C"));
                                }


                                if (temp[0].contains("Green")) {
                                    temp[0] = temp[0].replace(" : Green", "");
                                    areaCondition_1.setText(temp[0]);
                                    areaCondition_1.setBackgroundColor(Color.parseColor("#22FF1C"));
                                }


                                if (temp[3].contains("Blue")) {
                                    temp[3] = temp[3].replace(" : Blue", "");
                                    areaCondition_4.setText(temp[3]);
                                    areaCondition_4.setBackgroundColor(Color.parseColor("#2732E9"));
                                }

                                if (temp[2].contains("Blue")) {
                                    temp[2] = temp[2].replace(" : Blue", "");
                                    areaCondition_3.setText(temp[2]);
                                    areaCondition_3.setBackgroundColor(Color.parseColor("#2732E9"));
                                }

                                if (temp[1].contains("Blue")) {
                                    temp[1] = temp[1].replace(" : Blue", "");
                                    areaCondition_2.setText(temp[1]);
                                    areaCondition_2.setBackgroundColor(Color.parseColor("#2732E9"));
                                }

                                if (temp[0].contains("Blue")) {
                                    temp[0] = temp[0].replace(" : Blue", "");
                                    areaCondition_1.setText(temp[0]);
                                    areaCondition_1.setBackgroundColor(Color.parseColor("#2732E9"));
                                }


                                if (temp[3].contains("Yellow")) {
                                    temp[3] = temp[3].replace(" : Yellow", "");
                                    areaCondition_4.setText(temp[3]);
                                    areaCondition_4.setBackgroundColor(Color.parseColor("#F0E72C"));
                                }

                                if (temp[2].contains("Yellow")) {
                                    temp[2] = temp[2].replace(" : Yellow", "");
                                    areaCondition_3.setText(temp[2]);
                                    areaCondition_3.setBackgroundColor(Color.parseColor("#F0E72C"));
                                }

                                if (temp[1].contains("Yellow")) {
                                    temp[1] = temp[1].replace(" : Yellow", "");
                                    areaCondition_2.setText(temp[1]);
                                    areaCondition_2.setBackgroundColor(Color.parseColor("#F0E72C"));
                                }

                                if (temp[0].contains("Yellow")) {
                                    temp[0] = temp[0].replace(" : Yellow", "");
                                    areaCondition_1.setText(temp[0]);
                                    areaCondition_1.setBackgroundColor(Color.parseColor("#F0E72C"));
                                }


                                if (temp[3].contains("Pink")) {
                                    temp[3] = temp[3].replace(" : Pink", "");
                                    areaCondition_4.setText(temp[3]);
                                    areaCondition_4.setBackgroundColor(Color.parseColor("#F329F3"));
                                }

                                if (temp[2].contains("Pink")) {
                                    temp[2] = temp[2].replace(" : Pink", "");
                                    areaCondition_3.setText(temp[2]);
                                    areaCondition_3.setBackgroundColor(Color.parseColor("#F329F3"));
                                }

                                if (temp[1].contains("Pink")) {
                                    temp[1] = temp[1].replace(" : Pink", "");
                                    areaCondition_2.setText(temp[1]);
                                    areaCondition_2.setBackgroundColor(Color.parseColor("#F329F3"));
                                }

                                if (temp[0].contains("Pink")) {
                                    temp[0] = temp[0].replace(" : Pink", "");
                                    areaCondition_1.setText(temp[0]);
                                    areaCondition_1.setBackgroundColor(Color.parseColor("#F329F3"));
                                }


                                if (temp[3].contains("Red")) {
                                    temp[3] = temp[3].replace(" : Red", "");
                                    areaCondition_4.setText(temp[3]);
                                    areaCondition_4.setBackgroundColor(Color.parseColor("#FA0F0F"));
                                }

                                if (temp[2].contains("Red")) {
                                    temp[2] = temp[2].replace(" : Red", "");
                                    areaCondition_3.setText(temp[2]);
                                    areaCondition_3.setBackgroundColor(Color.parseColor("#FA0F0F"));
                                }

                                if (temp[1].contains("Red")) {
                                    temp[1] = temp[1].replace(" : Red", "");
                                    areaCondition_2.setText(temp[1]);
                                    areaCondition_2.setBackgroundColor(Color.parseColor("#FA0F0F"));
                                }

                                if (temp[0].contains("Red")) {
                                    temp[0] = temp[0].replace(" : Red", "");
                                    areaCondition_1.setText(temp[0]);
                                    areaCondition_1.setBackgroundColor(Color.parseColor("#FA0F0F"));
                                }


                                if (temp[3].equals("B")) {
                                    areaCondition_4.setVisibility(View.GONE);
                                }

                                if (temp[2].equals("B")) {
                                    areaCondition_3.setVisibility(View.GONE);
                                }

                                if (temp[1].equals("B")) {
                                    areaCondition_2.setVisibility(View.GONE);
                                }

                                if (temp[0].equals("B")) {
                                    areaCondition_1.setVisibility(View.GONE);
                                }


                                areaName.setText(marker.getTitle());

                            }
                        } catch (Exception e) {
                            v.setVisibility(View.GONE);

//                            myLinearLayout.setVisibility(View.GONE);

                            areaCondition_1.setVisibility(View.GONE);
                            areaCondition_2.setVisibility(View.GONE);
                            areaCondition_3.setVisibility(View.GONE);
                            areaCondition_4.setVisibility(View.GONE);
                            lastupdated.setVisibility(View.GONE);
                            areaName.setVisibility(View.GONE);

                        }


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

//            buildGoogleApiClient();

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

            GetMyLocation();

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

//            buildGoogleApiClient();


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




    static final LatLng DHAKA = new LatLng(23.727640, 90.410578);

    List<Marker> mMarkers = new ArrayList<Marker>();


    int gpsCounter = 0;

    // This Method is Called to Get my Current Location

    private void GetMyLocation() {
        mMap.setOnMyLocationChangeListener(myLocationChangeListener);
    }


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


    // This Method Waits 3 Seconds so that Camera Can Load and then Loads all the PushPins where they belong

    private void GoToLoadMethod() {
        Handler mHandler = new Handler();

        mHandler.postDelayed(new Runnable() {
            public void run() {
                MainWorkingMethod();
            }
        }, 3000);

    }


    Marker marker_Of_Incident, marker_of_areaInfo_1, marker_of_areaInfo_2, marker_of_areaInfo_3, marker_of_areaInfo_4;


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


    public int[] getFromPrefs() {
        int[] ret;
        SharedPreferences prefs = mContext.getSharedPreferences("AREAID", Context.MODE_PRIVATE);
        int count = prefs.getInt("Count", 0);
        ret = new int[count];
        for (int i = 0; i < count; i++) {
            ret[i] = prefs.getInt("IntValue_" + i, i);
        }
        return ret;
    }


    public String[] getFromPrefsString_1() {
        String[] ret;
        SharedPreferences prefs = mContext.getSharedPreferences("AREA_STAT_1", Context.MODE_PRIVATE);
        int count = prefs.getInt("Count", 0);
        ret = new String[count];
        for (int i = 0; i < count; i++) {
            ret[i] = prefs.getString("IntValue_" + i, Integer.toString(i));
        }
        return ret;
    }


    public String[] getFromPrefsString_2() {
        String[] ret;
        SharedPreferences prefs = mContext.getSharedPreferences("AREA_STAT_2", Context.MODE_PRIVATE);
        int count = prefs.getInt("Count", 0);
        ret = new String[count];
        for (int i = 0; i < count; i++) {
            ret[i] = prefs.getString("IntValue_" + i, Integer.toString(i));
        }
        return ret;
    }


    public String[] getFromPrefsString_3() {
        String[] ret;
        SharedPreferences prefs = mContext.getSharedPreferences("AREA_STAT_3", Context.MODE_PRIVATE);
        int count = prefs.getInt("Count", 0);
        ret = new String[count];
        for (int i = 0; i < count; i++) {
            ret[i] = prefs.getString("IntValue_" + i, Integer.toString(i));
        }
        return ret;
    }


    public String[] getFromPrefsString_4() {
        String[] ret;
        SharedPreferences prefs = mContext.getSharedPreferences("AREA_STAT_4", Context.MODE_PRIVATE);
        int count = prefs.getInt("Count", 0);
        ret = new String[count];
        for (int i = 0; i < count; i++) {
            ret[i] = prefs.getString("IntValue_" + i, Integer.toString(i));
        }
        return ret;
    }


    public String[] getFromPrefsString_5() {
        String[] ret;
        SharedPreferences prefs = mContext.getSharedPreferences("UPDATETIME", Context.MODE_PRIVATE);
        int count = prefs.getInt("Count", 0);
        ret = new String[count];
        for (int i = 0; i < count; i++) {
            ret[i] = prefs.getString("IntValue_" + i, Integer.toString(i));
        }
        return ret;
    }


    public String[] getFromPrefsString_6() {
        String[] ret;
        SharedPreferences prefs = mContext.getSharedPreferences("ISACTIVE", Context.MODE_PRIVATE);
        int count = prefs.getInt("Count", 0);
        ret = new String[count];
        for (int i = 0; i < count; i++) {
            ret[i] = prefs.getString("IntValue_" + i, Integer.toString(i));
        }
        return ret;
    }


    public String[] getFromPrefsString_IncidentUpdateTime() {
        String[] ret;
        SharedPreferences prefs = mContext.getSharedPreferences("INCIDENT_UPDATE_TIME", Context.MODE_PRIVATE);
        int count = prefs.getInt("Count", 0);
        ret = new String[count];
        for (int i = 0; i < count; i++) {
            ret[i] = prefs.getString("IntValue_" + i, Integer.toString(i));
        }
        return ret;
    }


    public String[] getFromPrefsString_IncidentIsActive() {
        String[] ret;
        SharedPreferences prefs = mContext.getSharedPreferences("INCIDENT_IS_ACTIVE", Context.MODE_PRIVATE);
        int count = prefs.getInt("Count", 0);
        ret = new String[count];
        for (int i = 0; i < count; i++) {
            ret[i] = prefs.getString("IntValue_" + i, Integer.toString(i));
        }
        return ret;
    }


    public String[] getFromPrefsString_IncidentCentralCord() {
        String[] ret;
        SharedPreferences prefs = mContext.getSharedPreferences("INCIDENT_CENTRAL_CORD", Context.MODE_PRIVATE);
        int count = prefs.getInt("Count", 0);
        ret = new String[count];
        for (int i = 0; i < count; i++) {
            ret[i] = prefs.getString("IntValue_" + i, Integer.toString(i));
        }
        return ret;
    }


    public String[] getFromPrefsString_IncidentName() {
        String[] ret;
        SharedPreferences prefs = mContext.getSharedPreferences("INCIDENT_NAME", Context.MODE_PRIVATE);
        int count = prefs.getInt("Count", 0);
        ret = new String[count];
        for (int i = 0; i < count; i++) {
            ret[i] = prefs.getString("IntValue_" + i, Integer.toString(i));
        }
        return ret;
    }


    private void MainWorkingMethod() {

        incidentName = new String[getFromPrefsString_IncidentName().length];
        incidentCentral_Cord = new String[getFromPrefsString_IncidentCentralCord().length];
        incidentUpdate_Time = new String[getFromPrefsString_IncidentUpdateTime().length];
        incidentIs_Active = new String[getFromPrefsString_IncidentIsActive().length];

        incidentName = getFromPrefsString_IncidentName();
        incidentCentral_Cord = getFromPrefsString_IncidentCentralCord();
        incidentUpdate_Time = getFromPrefsString_IncidentUpdateTime();
        incidentIs_Active = getFromPrefsString_IncidentIsActive();

        String temp_inc[];

        for (int i = 0; i < incidentName.length; i++) {

            double inc_Latitude, inc_Longitude;

            String delimeter = ",";

            temp_inc = incidentCentral_Cord[i].split(delimeter);

            try {
                inc_Latitude = Double.parseDouble(temp_inc[0]);
                inc_Longitude = Double.parseDouble(temp_inc[1]);

            } catch (NumberFormatException e) {
                inc_Latitude = 0; // your default value
                inc_Longitude = 0;
            }

            LatLng incident_geo_Code = new LatLng(inc_Latitude, inc_Longitude);


            if (incidentName[i].equals("Long Procession; traffic movement stopped")) {
                marker_Of_Incident = mMap.addMarker(new MarkerOptions()
                        .title("Long Procession; traffic movement stopped")
                        .position(incident_geo_Code)
                        .snippet("A")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.meeting_icon)));
            } else if (incidentName[i].equals("Special Meeting in Progress; disrupted traffic movement")) {
                marker_Of_Incident = mMap.addMarker(new MarkerOptions()
                        .title("Special Meeting in Progress; disrupted traffic movement")
                        .position(incident_geo_Code)
                        .snippet("A")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.meeting_icon)));
            } else if (incidentName[i].equals("Small procession; slow traffic movement")) {
                marker_Of_Incident = mMap.addMarker(new MarkerOptions()
                        .title("Small procession; slow traffic movement")
                        .position(incident_geo_Code)
                        .snippet("A")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.meeting_icon)));
            } else if (incidentName[i].equals("Rainwater clogging on sideways; slow traffic movement")) {
                marker_Of_Incident = mMap.addMarker(new MarkerOptions()
                        .title("Rainwater clogging on sideways; slow traffic movement")
                        .position(incident_geo_Code)
                        .snippet("A")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.rainwater_icon)));
            } else if (incidentName[i].equals("Submerged road; very slow traffic movement")) {
                marker_Of_Incident = mMap.addMarker(new MarkerOptions()
                        .title("Submerged road; very slow traffic movement")
                        .position(incident_geo_Code)
                        .snippet("A")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.rainwater_icon)));
            } else if (incidentName[i].equals("Maintenance work; disrupted and slow traffic movement")) {
                marker_Of_Incident = mMap.addMarker(new MarkerOptions()
                        .title("Maintenance work; disrupted and slow traffic movement")
                        .position(incident_geo_Code)
                        .snippet("A")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.maintenance_icon)));
            } else if (incidentName[i].equals("Road blockade due to maintenance work; no vehicle entrance")) {
                marker_Of_Incident = mMap.addMarker(new MarkerOptions()
                        .title("Road blockade due to maintenance work; no vehicle entrance")
                        .position(incident_geo_Code)
                        .snippet("A")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.maintenance_icon)));
            } else if (incidentName[i].equals("Road Accident; disrupted and slow traffic movement")) {
                marker_Of_Incident = mMap.addMarker(new MarkerOptions()
                        .title("Road Accident; disrupted and slow traffic movement")
                        .position(incident_geo_Code)
                        .snippet("A")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.roadaccident_icon)));
            } else if (incidentName[i].equals("Big road accident; traffic movement stopped")) {
                marker_Of_Incident = mMap.addMarker(new MarkerOptions()
                        .title("Big road accident; traffic movement stopped")
                        .position(incident_geo_Code)
                        .snippet("A")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.roadaccident_icon)));
            } else if (incidentName[i].equals("Long VIP Motorcade Passing Through, slow traffic movement")) {
                marker_Of_Incident = mMap.addMarker(new MarkerOptions()
                        .title("Long VIP Motorcade Passing Through, slow traffic movement")
                        .position(incident_geo_Code)
                        .snippet("A")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.vip_icon)));
            } else if (incidentName[i].equals("Criminal activities in Progress")) {
                marker_Of_Incident = mMap.addMarker(new MarkerOptions()
                        .title("Criminal activities in Progress")
                        .position(incident_geo_Code)
                        .snippet("A")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.criminal_icon)));
            } else if (incidentName[i].equals("Chaos and Disorder")) {
                marker_Of_Incident = mMap.addMarker(new MarkerOptions()
                        .title("Chaos and Disorder")
                        .position(incident_geo_Code)
                        .snippet("A")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.chaos_icon)));
            }
            else if (incidentName[i].equals("")) {
                continue;
            }else {
//                if (incidentName[i].contains("Extra Points : ")) {
//
//                    incidentName[i] = incidentName[i].replace("Extra Points : ", "");
//
//                    marker_Of_Incident = mMap.addMarker(new MarkerOptions()
//                            .title(incidentName[i])
//                            .position(incident_geo_Code)
//                            .snippet("A")
//                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.trafficpoint)));
//                } else {
//                    marker_Of_Incident = mMap.addMarker(new MarkerOptions()
//                            .title(incidentName[i])
//                            .position(incident_geo_Code)
//                            .snippet("A")
//                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.others_icon)));
//                }
                marker_Of_Incident = mMap.addMarker(new MarkerOptions()
                        .title(incidentName[i])
                        .position(incident_geo_Code)
                        .snippet("A")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.others_icon)));
            }

        }

        myAreaId = new int[getFromPrefs().length];
        myArea_Stat_1 = new String[getFromPrefsString_1().length];
        myArea_Stat_2 = new String[getFromPrefsString_2().length];
        myArea_Stat_3 = new String[getFromPrefsString_3().length];
        myArea_Stat_4 = new String[getFromPrefsString_4().length];
        myUpdate_Time = new String[getFromPrefsString_5().length];
        myIs_Active = new String[getFromPrefsString_6().length];

        myAreaId = getFromPrefs();
        myArea_Stat_1 = getFromPrefsString_1();
        myArea_Stat_2 = getFromPrefsString_2();
        myArea_Stat_3 = getFromPrefsString_3();
        myArea_Stat_4 = getFromPrefsString_4();
        myUpdate_Time = getFromPrefsString_5();
        myIs_Active = getFromPrefsString_6();

        for(int i = 0; i < myAreaId.length; i++) {

            int dummy = myAreaId[i];

            if (dummy == i) {

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentDateAndTime = sdf.format(new Date());

                String updateTime = myUpdate_Time[i];

                DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
                DateTime time2 = format.parseDateTime(currentDateAndTime);

                DateTime time3 = format.parseDateTime(myUpdate_Time[i]);

                Minutes minInterval = Minutes.minutes(60);

                Minutes trafficMin = Minutes.minutesBetween(time3, time2);
                if (trafficMin.isGreaterThan(minInterval)) {
                    //todoitem
                } else {


                    String ar_cond_1, ar_cond_2, ar_cond_3, ar_cond_4;

                    if (Jam_Area_1[i] == -9) {
                        ar_cond_1 = "B";
                    } else {
                        ar_cond_1 = AreaName[Jam_Area_1[i]];

                        ar_cond_1 = "- " + ar_cond_1 + " : " + myArea_Stat_1[i];
                    }


                    if (Jam_Area_2[i] == -9) {
                        ar_cond_2 = "B";
                    } else {
                        ar_cond_2 = AreaName[Jam_Area_2[i]];

                        ar_cond_2 = "- " + ar_cond_2 + " : " + myArea_Stat_2[i];
                    }


                    if (Jam_Area_3[i] == -9) {
                        ar_cond_3 = "B";
                    } else {
                        ar_cond_3 = AreaName[Jam_Area_3[i]];

                        ar_cond_3 = "- " + ar_cond_3 + " : " + myArea_Stat_3[i];
                    }


                    if (Jam_Area_4[i] == -9) {
                        ar_cond_4 = "B";
                    } else {
                        ar_cond_4 = AreaName[Jam_Area_4[i]];

                        ar_cond_4 = "- " + ar_cond_4 + " : " + myArea_Stat_4[i];
                    }



                    double AreaLatLng_Latitude, AreaLatLng_Longitude;

                    String delimeter = ",";

                    AreaLatLngArray = areaLatLngList.get(i).split(delimeter);

                    try {
                        AreaLatLng_Latitude = Double.parseDouble(AreaLatLngArray[0]);
                        AreaLatLng_Longitude = Double.parseDouble(AreaLatLngArray[1]);

                    } catch (NumberFormatException e) {
                        AreaLatLng_Latitude = 0; // your default value
                        AreaLatLng_Longitude = 0;
                    }

                    LatLng AreaLatLng_geo_Code = new LatLng(AreaLatLng_Latitude, AreaLatLng_Longitude);

                /*SetMarkerMethod(AreaName[i], ar_cond_1, ar_cond_2, ar_cond_3, ar_cond_4, "Updated: "
                        + diffInHours + diffInMinutes + "ago", AreaLatLng_geo_Code);*/

                    SetMarkerMethod(AreaName[i], ar_cond_1, ar_cond_2, ar_cond_3, ar_cond_4,
                            "Last Update:" + updateTime, AreaLatLng_geo_Code);


                    double Area_Stat_Cond_1_Latitude, Area_Stat_Cond_1_Longitude;

                    Area_Stat_Cond_1Array = Area_Stat_Cond_1List.get(i).split(delimeter);

                    try {
                        Area_Stat_Cond_1_Latitude = Double.parseDouble(Area_Stat_Cond_1Array[0]);
                        Area_Stat_Cond_1_Longitude = Double.parseDouble(Area_Stat_Cond_1Array[1]);

                    } catch (NumberFormatException e) {
                        Area_Stat_Cond_1_Latitude = 0;
                        Area_Stat_Cond_1_Longitude = 0;

                    }

                    LatLng area_geo_Code1 = new LatLng(Area_Stat_Cond_1_Latitude, Area_Stat_Cond_1_Longitude);


                    if (myArea_Stat_1[i].equals("Red")) {
                        marker_of_areaInfo_1 = mMap.addMarker(new MarkerOptions()
                                        .title(myArea_Stat_1[i])
                                        .position(area_geo_Code1)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.red_traffic_indicator))
                        );
                    } else if (myArea_Stat_1[i].equals("Pink")) {
                        marker_of_areaInfo_1 = mMap.addMarker(new MarkerOptions()
                                        .title(myArea_Stat_1[i])
                                        .position(area_geo_Code1)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pink_traffic_indicator))
                        );
                    } else if (myArea_Stat_1[i].equals("Yellow")) {
                        marker_of_areaInfo_1 = mMap.addMarker(new MarkerOptions()
                                        .title(myArea_Stat_1[i])
                                        .position(area_geo_Code1)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.yellow_traffic_indicator))
                        );
                    } else if (myArea_Stat_1[i].equals("Blue")) {
                        marker_of_areaInfo_1 = mMap.addMarker(new MarkerOptions()
                                        .title(myArea_Stat_1[i])
                                        .position(area_geo_Code1)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.blue_traffic_indicator))
                        );
                    } else if (myArea_Stat_1[i].equals("Green")) {
                        marker_of_areaInfo_1 = mMap.addMarker(new MarkerOptions()
                                        .title(myArea_Stat_1[i])
                                        .position(area_geo_Code1)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.green_traffic_indicator))
                        );
                    }
//                }

                    double Area_Stat_Cond_2_Latitude, Area_Stat_Cond_2_Longitude;

                    Area_Stat_Cond_2Array = Area_Stat_Cond_2List.get(i).split(delimeter);

                    try {

                        Area_Stat_Cond_2_Latitude = Double.parseDouble(Area_Stat_Cond_2Array[0]);
                        Area_Stat_Cond_2_Longitude = Double.parseDouble(Area_Stat_Cond_2Array[1]);

                    } catch (NumberFormatException e) {

                        Area_Stat_Cond_2_Latitude = 0;
                        Area_Stat_Cond_2_Longitude = 0;

                    }

                    LatLng area_geo_Code2 = new LatLng(Area_Stat_Cond_2_Latitude, Area_Stat_Cond_2_Longitude);


                    if (myArea_Stat_2[i].equals("Red")) {
                        marker_of_areaInfo_2 = mMap.addMarker(new MarkerOptions()
                                        .title(myArea_Stat_2[i])
                                        .position(area_geo_Code2)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.red_traffic_indicator))
                        );
                    } else if (myArea_Stat_2[i].equals("Pink")) {
                        marker_of_areaInfo_2 = mMap.addMarker(new MarkerOptions()
                                        .title(myArea_Stat_2[i])
                                        .position(area_geo_Code2)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pink_traffic_indicator))
                        );
                    } else if (myArea_Stat_2[i].equals("Yellow")) {
                        marker_of_areaInfo_2 = mMap.addMarker(new MarkerOptions()
                                        .title(myArea_Stat_2[i])
                                        .position(area_geo_Code2)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.yellow_traffic_indicator))
                        );
                    } else if (myArea_Stat_2[i].equals("Blue")) {
                        marker_of_areaInfo_2 = mMap.addMarker(new MarkerOptions()
                                        .title(myArea_Stat_2[i])
                                        .position(area_geo_Code2)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.blue_traffic_indicator))
                        );
                    } else if (myArea_Stat_2[i].equals("Green")) {
                        marker_of_areaInfo_2 = mMap.addMarker(new MarkerOptions()
                                        .title(myArea_Stat_2[i])
                                        .position(area_geo_Code2)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.green_traffic_indicator))
                        );
                    }
//                }

                    double Area_Stat_Cond_3_Latitude, Area_Stat_Cond_3_Longitude;

                    Area_Stat_Cond_3Array = Area_Stat_Cond_3List.get(i).split(delimeter);

                    try {

                        Area_Stat_Cond_3_Latitude = Double.parseDouble(Area_Stat_Cond_3Array[0]);
                        Area_Stat_Cond_3_Longitude = Double.parseDouble(Area_Stat_Cond_3Array[1]);

                    } catch (NumberFormatException e) {

                        Area_Stat_Cond_3_Latitude = 0;
                        Area_Stat_Cond_3_Longitude = 0;

                    }

                    LatLng area_geo_Code3 = new LatLng(Area_Stat_Cond_3_Latitude, Area_Stat_Cond_3_Longitude);


                    if (myArea_Stat_3[i].equals("Red")) {
                        marker_of_areaInfo_3 = mMap.addMarker(new MarkerOptions()
                                        .title(myArea_Stat_3[i])
                                        .position(area_geo_Code3)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.red_traffic_indicator))
                        );
                    } else if (myArea_Stat_3[i].equals("Pink")) {
                        marker_of_areaInfo_3 = mMap.addMarker(new MarkerOptions()
                                        .title(myArea_Stat_3[i])
                                        .position(area_geo_Code3)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pink_traffic_indicator))
                        );
                    } else if (myArea_Stat_3[i].equals("Yellow")) {
                        marker_of_areaInfo_3 = mMap.addMarker(new MarkerOptions()
                                        .title(myArea_Stat_3[i])
                                        .position(area_geo_Code3)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.yellow_traffic_indicator))
                        );
                    } else if (myArea_Stat_3[i].equals("Blue")) {
                        marker_of_areaInfo_3 = mMap.addMarker(new MarkerOptions()
                                        .title(myArea_Stat_3[i])
                                        .position(area_geo_Code3)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.blue_traffic_indicator))
                        );
                    } else if (myArea_Stat_3[i].equals("Green")) {
                        marker_of_areaInfo_3 = mMap.addMarker(new MarkerOptions()
                                        .title(myArea_Stat_3[i])
                                        .position(area_geo_Code3)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.green_traffic_indicator))
                        );
                    }
//                }

                    double Area_Stat_Cond_4_Latitude, Area_Stat_Cond_4_Longitude;

                    Area_Stat_Cond_4Array = Area_Stat_Cond_4List.get(i).split(delimeter);

                    try {

                        Area_Stat_Cond_4_Latitude = Double.parseDouble(Area_Stat_Cond_4Array[0]);
                        Area_Stat_Cond_4_Longitude = Double.parseDouble(Area_Stat_Cond_4Array[1]);

                    } catch (NumberFormatException e) {

                        Area_Stat_Cond_4_Latitude = 0;
                        Area_Stat_Cond_4_Longitude = 0;

                    }

                    LatLng area_geo_Code4 = new LatLng(Area_Stat_Cond_4_Latitude, Area_Stat_Cond_4_Longitude);


                    if (myArea_Stat_4[i].equals("Red")) {
                        marker_of_areaInfo_4 = mMap.addMarker(new MarkerOptions()
                                        .title(myArea_Stat_4[i])
                                        .position(area_geo_Code4)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.red_traffic_indicator))
                        );
                    } else if (myArea_Stat_4[i].equals("Pink")) {
                        marker_of_areaInfo_4 = mMap.addMarker(new MarkerOptions()
                                        .title(myArea_Stat_4[i])
                                        .position(area_geo_Code4)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pink_traffic_indicator))
                        );
                    } else if (myArea_Stat_4[i].equals("Yellow")) {
                        marker_of_areaInfo_4 = mMap.addMarker(new MarkerOptions()
                                        .title(myArea_Stat_4[i])
                                        .position(area_geo_Code4)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.yellow_traffic_indicator))
                        );
                    } else if (myArea_Stat_4[i].equals("Blue")) {
                        marker_of_areaInfo_4 = mMap.addMarker(new MarkerOptions()
                                        .title(myArea_Stat_4[i])
                                        .position(area_geo_Code4)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.blue_traffic_indicator))
                        );
                    } else if (myArea_Stat_4[i].equals("Green")) {
                        marker_of_areaInfo_4 = mMap.addMarker(new MarkerOptions()
                                        .title(myArea_Stat_4[i])
                                        .position(area_geo_Code4)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.green_traffic_indicator))
                        );
                    }
                }
            }
        }

        TimeCounterIncreaseMethod();
    }

    private void TimeCounterIncreaseMethod() {
        timecounter = 1;
    }


    // Method for Instantiating Custom Info Window!!!


    int markerCounter = 0;

    private void SetMarkerMethod(String area_name,
                                 String area_cond_1,
                                 String area_cond_2,
                                 String area_cond_3,
                                 String area_cond_4,
                                 String last_updated,
                                 LatLng latilongi) {

        MarkerOptions mOptions = new MarkerOptions()
                .position(latilongi)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.trafficpoint))
                .visible(true);
        if (area_name.length() > 0) {
            mOptions.title(area_name);
        }

        if (area_cond_1.length() > 0 &&
                area_cond_2.length() > 0 && area_cond_3.length() > 0 &&
                area_cond_4.length() > 0 && last_updated.length() > 0) {
            mOptions.snippet(area_cond_1 + "§" + area_cond_2
                    + "§" + area_cond_3 + "§" + area_cond_4 + "§" + last_updated);

        }


        for (int i = 0; i < myAreaId.length; i++) {
            if (markerCounter == i) {
                mMarkers.add(mMap.addMarker(mOptions));
            }
        }


        markerCounter++;

    }

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
