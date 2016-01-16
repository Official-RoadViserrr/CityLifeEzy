package com.citylifeezy.citylifeezy.fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Spinner;
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
 * Created by Asif on 9/6/2015.
 */
public class RestaurantFragment extends android.support.v4.app.Fragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, SearchView.OnQueryTextListener {

    private MapView mMapView;
    private GoogleMap mMap;
    public EditText test_station, test_time;
    public Button update;
    SharedPreferences sp;
    int resID;
    public String prefName = "marker_icon";
    Marker marker;
    TextView tvs2;
    Spinner spinner, spinner2;
    Button res_menu;
    ArrayAdapter<String> adapter1;
    ArrayAdapter<String> adapter2;

    private static Context mContext;

    public static RestaurantFragment newInstance(Context context) {
        RestaurantFragment f = new RestaurantFragment();
        Bundle args = new Bundle();
        args.putString("index", context.toString());
        f.setArguments(args);

        mContext = context;
        return f;
    }


    SearchView mySearchView;

    ArrayList<LatLng> AreaLatLng;


    public String[] AreaName = {
            "Kabab Factory", "Barbecue Flames", "Aadi Dhaka", "Kasturi Garden", "The Dinning Room", "Cilantro", "Bread and Beyond", "Burger n' Boost",
            "Cafe Droom", "Cafe Hollywood", "Cafe Italiano", "Coffee Rpublic Bangladesh", "Crossroads Lounge", "Fiesta Restaurant", "George's Cafe", "Gloria Jean's",
            "Moka Cafe and Bistro", "Fiesta Oven Fresh bake and brew", "Tastebud", "The Oriental Lounge", "The Stage",
            "White Hen Gourmet and Pastry Shop", "FFC Gulshan", "FFC Mirpur", "KFC Restaurant Dhanmondi",
            "KFC Restaurant Baily Road", "KFC Restaurant Mirpur", "KFC Restaurant Gulshan", "KFC Restaurant Paltan",
            "KFC Restaurant Panthapath", "KFC Restaurant Banani", "Nando's Gulshan", "Nando's Dhanmondi",
            "Mainland China", "Black Tent", "Bukhara Restaurant", "Dhaba", "Great Indian Restaurant",
            "Handi Indian Bistro", "Khana Khazana", "Locknow Restaurant", "The Mughal Kitchen", "Nawaab's", "Santoor",
            "Tarka", "Angaar", "Hakka Dhaka", "Sajna", "Koyla", "Casa Greek", "Emma N Uelles Inn", "Hungry Duck", "Live Kitchen", "Rice n Noodies", "The Chef",
            "Voot the Restaurant", "Loiter D85", "Spaghetti Jazz", "Veni Vidi Vici", "Terra Bistro", "Japanese Restaurant Ichi",
            "(Teriyaki) Kobe Japanese Restaurant", "Goong:The Castle", "Korean Restaurant", "Dae Jang Geum- Korean Cuisine",
            "Suraon", "DuMiOk", "Quesadilla La Mexicana Grill", "Sbarro Bangladesh", "Pizza Hut Baily Road", "Pizza Hut Dhanmondi",
            "Pizza Hut Gulshan", "Pizza Hut Uttara", "Pizza Hut Banani", "Absolute Thai", "Corner (Fusion) Thai Restaurant",
            "Red Tomato", "Soi 71",


    };
    // This is where the View is being Created!!!
    //bbq
    private static final LatLng kabab_factory = new LatLng(23.8675231, 90.393985);
    private static final LatLng bbq_flames = new LatLng(23.7748592, 90.4164103);

    //bengali
    private static final LatLng aadi = new LatLng(23.7922934, 90.4071356);
    private static final LatLng kasturi_garden = new LatLng(23.780741, 90.412582);
    private static final LatLng dinning_room = new LatLng(23.790062, 90.409208);

    //brazilian
    private static final LatLng cilantro = new LatLng(23.7464836, 90.3708556);

    //cafe
    private static final LatLng bread_b = new LatLng(23.7941337, 90.4023394);
    private static final LatLng burger_boost = new LatLng(23.7916017, 90.4028049);
    private static final LatLng cafe_droom = new LatLng(23.7519387, 90.3700081);
    private static final LatLng cafe_hollywood = new LatLng(23.79017, 90.4077253);
    private static final LatLng cafe_italiano = new LatLng(23.790749, 90.404144);
    private static final LatLng coffee_rep_bd = new LatLng(23.7932342, 90.4056975);
    private static final LatLng crossroads = new LatLng(23.8724414, 90.3908849);
    private static final LatLng fiesta_res = new LatLng(23.7911873, 90.4013202);
    private static final LatLng george_cafe = new LatLng(23.859406, 90.40061);
    private static final LatLng gloria_jeans = new LatLng(23.7791694, 90.4163942);
    private static final LatLng moka_cafe = new LatLng(23.7900314, 90.4091737);
    private static final LatLng fiesta_oven = new LatLng(23.791056, 90.40135);
    private static final LatLng tastebud = new LatLng(23.7899886, 90.4083459);
    private static final LatLng oriental_lounge = new LatLng(23.7917089, 90.4080753);
    private static final LatLng stage = new LatLng(23.7416567, 90.3740877);
    private static final LatLng white_hen = new LatLng(23.792162, 90.4065017);
    private static final LatLng ffc_gulshan = new LatLng(23.8146947, 90.4117061);
    private static final LatLng ffc_mirpur = new LatLng(23.8160652, 90.3663683);

    //chicken fragment_restaurant
    private static final LatLng kfc_dhanmondi = new LatLng(23.7435263, 90.3734159);
    private static final LatLng kfc_baily_road = new LatLng(23.741921, 90.409687);
    private static final LatLng kfc_mirpur = new LatLng(23.816756, 90.36621);
    private static final LatLng kfc_gulshan = new LatLng(23.7753465, 90.4164758);
    private static final LatLng kfc_paltan = new LatLng(23.7300124, 90.4111464);
    private static final LatLng kfc_panthapath = new LatLng(23.7527219, 90.3808549);
    private static final LatLng kfc_banani = new LatLng(23.7937659, 90.4055305);
    private static final LatLng nandos_gul = new LatLng(23.7765065, 90.4166861);
    private static final LatLng nandos_dhan = new LatLng(23.7528937, 90.3699088);

    //chinese
    private static final LatLng mainland = new LatLng(23.8624979, 90.3995916);

    //indian
    private static final LatLng black_tent = new LatLng(23.7805243, 90.4163378);
    private static final LatLng bukhara_res = new LatLng(23.793453, 90.405625);
    private static final LatLng dhaba = new LatLng(23.7919592, 90.4067516);
    private static final LatLng great_indian_res = new LatLng(23.8623038, 90.3995422);
    private static final LatLng handi_indian = new LatLng(23.7907394, 90.4061173);
    private static final LatLng khana_khazana = new LatLng(23.7815527, 90.4160294);
    private static final LatLng loknow_res = new LatLng(23.793282, 90.409186);
    private static final LatLng mughal = new LatLng(23.7795916, 90.4145623);
    private static final LatLng nawaab = new LatLng(23.7913776, 90.4048285);
    private static final LatLng santoor = new LatLng(23.7517616, 90.3772631);
    private static final LatLng tarka = new LatLng(23.7913726, 90.4062018);
    private static final LatLng angaar = new LatLng(23.7768125, 90.4151322);
    private static final LatLng hakka_dhaka = new LatLng(23.7913492, 90.4061964);
    private static final LatLng sajna = new LatLng(23.7903135, 90.4070327);
    private static final LatLng koyla = new LatLng(23.771037, 90.410760);

    //international
    private static final LatLng casa_greek = new LatLng(23.792222, 90.406484);
    private static final LatLng emma = new LatLng(23.8146947, 90.4117061);
    private static final LatLng hungry_duck = new LatLng(23.8639891, 90.3993461);
    private static final LatLng live_kitchen = new LatLng(23.7914421, 90.4043662);
    private static final LatLng rice_noodles = new LatLng(23.875478, 90.384135);
    private static final LatLng chef = new LatLng(23.8645275, 90.4012877);
    private static final LatLng voot_res = new LatLng(23.8000712, 90.393107);
    private static final LatLng loiter = new LatLng(23.7911763, 90.4042411);

    //italian
    private static final LatLng spaghetti = new LatLng(23.7953583, 90.4143155);
    private static final LatLng veni = new LatLng(23.866336, 90.397706);
    private static final LatLng terra = new LatLng(23.7907891, 90.40291);

    //japanese
    private static final LatLng japanese_res = new LatLng(23.790714, 90.404123);
    private static final LatLng kobe = new LatLng(23.7903565, 90.4082295);

    //korean
    private static final LatLng goong = new LatLng(23.797308, 90.410514);
    private static final LatLng korean_res = new LatLng(23.785118, 90.419266);
    private static final LatLng dae_jang = new LatLng(23.795184, 90.410712);
    private static final LatLng suraon = new LatLng(23.803771, 90.413552);
    private static final LatLng dumi = new LatLng(23.7906094, 90.4067919);

    //mexican
    private static final LatLng mexican_grill = new LatLng(23.7903698, 90.408209);

    //pizza
    private static final LatLng sbarro = new LatLng(23.7963265, 90.4132077);
    private static final LatLng pizza_hut_baily = new LatLng(23.7418787, 90.4099441);
    private static final LatLng pizza_hut_dhan = new LatLng(23.7475012, 90.3702629);
    private static final LatLng pizza_hut_gul = new LatLng(23.776444, 90.4168491);
    private static final LatLng pizza_hut_uttara = new LatLng(23.8730892, 90.3914297);
    private static final LatLng pizza_hut_banani = new LatLng(23.791234, 90.4018546);

    //thai
    private static final LatLng absolute_thai = new LatLng(23.789293, 90.409268);
    private static final LatLng corner_thai = new LatLng(23.7805685, 90.4163003);
    private static final LatLng red_tomato = new LatLng(23.7527022, 90.3689003);
    private static final LatLng soi = new LatLng(23.8040873, 90.4136828);


    public void processMap(final View v) {
        if (mMap == null) {
            mMap = ((MapView) v.findViewById(R.id.mapView)).getMap();
            mMap.setOnMyLocationChangeListener(myLocationChangeListener);
        }
        if (mMap != null) {

            full_res();

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position == 0) {
                        //bbq
                        //if(spinner.)
                        if (marker != null) {
                            mMap.clear();
                            spinner2.setSelection(adapter2.getCount());
                            marker = mMap.addMarker(new MarkerOptions().position(kabab_factory).title("Kabab Factory").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_bbq_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kabab_factory, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(bbq_flames).title("Barbecue Flames").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_bbq_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bbq_flames, 11));
                        }
                        //Toast.makeText(mContext, "You Have Selected:" + parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                    } else if (position == 1) {
                        //bengali
                        if (marker != null) {
                            mMap.clear();
                            spinner2.setSelection(adapter2.getCount());
                            marker = mMap.addMarker(new MarkerOptions().position(aadi).title("Aadi Dhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_bengali_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(aadi, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(kasturi_garden).title("Kasturi Garden").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_bengali_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kasturi_garden, 11));
                        }
                        //Toast.makeText(mContext, "You Have Selected:" + parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                    } else if (position == 2) {
                        //brazilian
                        if (marker != null) {
                            mMap.clear();
                            spinner2.setSelection(adapter2.getCount());
                            marker = mMap.addMarker(new MarkerOptions().position(cilantro).title("Cilantro").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_brazilian_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cilantro, 11));
                        }
                        //Toast.makeText(mContext, "You Have Selected:" + parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                    } else if (position == 3) {
                        //cafe
                        if (marker != null) {
                            mMap.clear();
                            spinner2.setSelection(adapter2.getCount());
                            marker = mMap.addMarker(new MarkerOptions().position(bread_b).title("Bread and Beyond").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bread_b, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(burger_boost).title("Burger n' Boost").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(burger_boost, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(cafe_droom).title("Cafe Droom").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cafe_droom, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(cafe_hollywood).title("Cafe Hollywood").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cafe_hollywood, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(cafe_italiano).title("Cafe Italiano").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cafe_italiano, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(coffee_rep_bd).title("Coffee Rpublic Bangladesh").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coffee_rep_bd, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(crossroads).title("Crossroads Lounge").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(crossroads, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(fiesta_res).title("Fiesta Restaurant").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fiesta_res, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(george_cafe).title("George's Cafe").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(george_cafe, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(gloria_jeans).title("Gloria Jean's").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(gloria_jeans, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(moka_cafe).title("Moka Cafe and Bistro").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(moka_cafe, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(fiesta_oven).title("Fiesta Oven Fresh bake and brew").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fiesta_oven, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(tastebud).title("Tastebud").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tastebud, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(oriental_lounge).title("The Oriental Lounge").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(oriental_lounge, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(stage).title("The Stage").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(stage, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(white_hen).title("White Hen Gourmet and Pastry Shop").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(white_hen, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ffc_gulshan).title("FFC Gulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ffc_gulshan, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(ffc_mirpur).title("FFC Mirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ffc_mirpur, 11));
                        }
                        //Toast.makeText(mContext, "You Have Selected:" + parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                    } else if (position == 4) {
                        //chicken
                        if (marker != null) {
                            mMap.clear();
                            spinner2.setSelection(adapter2.getCount());
                            marker = mMap.addMarker(new MarkerOptions().position(kfc_dhanmondi).title("KFC Restaurant Dhanmondi").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_chicken_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kfc_dhanmondi, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(kfc_baily_road).title("KFC Restaurant Baily Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_chicken_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kfc_baily_road, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(kfc_mirpur).title("KFC Restaurant Mirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_chicken_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kfc_mirpur, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(kfc_gulshan).title("KFC Restaurant Gulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_chicken_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kfc_gulshan, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(kfc_paltan).title("KFC Restaurant Paltan").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_chicken_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kfc_paltan, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(kfc_panthapath).title("KFC Restaurant Panthapath").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_chicken_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kfc_panthapath, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(kfc_banani).title("KFC Restaurant Banani").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_chicken_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kfc_banani, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(nandos_gul).title("Nando's Gulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_chicken_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nandos_gul, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(nandos_dhan).title("Nando's Dhanmondi").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_chicken_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nandos_dhan, 11));
                        }
                        //Toast.makeText(mContext, "You Have Selected:" + parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                    } else if (position == 5) {
                        //chinese
                        if (marker != null) {
                            mMap.clear();
                            spinner2.setSelection(adapter2.getCount());
                            marker = mMap.addMarker(new MarkerOptions().position(mainland).title("Mainland China").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_chinese_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mainland, 11));
                        }
                        //Toast.makeText(mContext, "You Have Selected:" + parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                    } else if (position == 6) {
                        //indian
                        if (marker != null) {
                            mMap.clear();
                            spinner2.setSelection(adapter2.getCount());
                            marker = mMap.addMarker(new MarkerOptions().position(black_tent).title("Black Tent").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_indian_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(black_tent, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(bukhara_res).title("Bukhara Restaurant").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_indian_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bukhara_res, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(dhaba).title("Dhaba").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_indian_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dhaba, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(great_indian_res).title("Great Indian Restaurant").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_indian_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(great_indian_res, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(handi_indian).title("Handi Indian Bistro").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_indian_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(handi_indian, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(khana_khazana).title("Khana Khazana").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_indian_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(khana_khazana, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(loknow_res).title("Locknow Restaurant").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_indian_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loknow_res, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(mughal).title("The Mughal Kitchen").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_indian_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mughal, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(nawaab).title("Nawaab's").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_indian_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nawaab, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(santoor).title("Santoor").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_indian_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(santoor, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(tarka).title("Tarka").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_indian_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tarka, 11));
                        }
                        //Toast.makeText(mContext, "You Have Selected:" + parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                    } else if (position == 7) {
                        //international
                        if (marker != null) {
                            mMap.clear();
                            spinner2.setSelection(adapter2.getCount());
                            marker = mMap.addMarker(new MarkerOptions().position(casa_greek).title("Casa Greek").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_international_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(casa_greek, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(emma).title("Emma N Uelles Inn").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_international_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(emma, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(hungry_duck).title("Hungry Duck").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_international_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hungry_duck, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(live_kitchen).title("Live Kitchen").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_international_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(live_kitchen, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(rice_noodles).title("Rice n Noodies").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_international_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(rice_noodles, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(chef).title("The Chef").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_international_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(chef, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(voot_res).title("Voot the Restaurant").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_international_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(voot_res, 11));
                        }
                        //Toast.makeText(mContext, "You Have Selected:" + parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                    } else if (position == 8) {
                        //italian
                        if (marker != null) {
                            mMap.clear();
                            spinner2.setSelection(adapter2.getCount());
                            marker = mMap.addMarker(new MarkerOptions().position(spaghetti).title("Spaghetti Jazz").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_italian_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(spaghetti, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(veni).title("Veni Vidi Vici").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_italian_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(veni, 11));
                        }
                        //Toast.makeText(mContext, "You Have Selected:" + parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                    } else if (position == 9) {
                        //japanese
                        if (marker != null) {
                            mMap.clear();
                            spinner2.setSelection(adapter2.getCount());
                            marker = mMap.addMarker(new MarkerOptions().position(japanese_res).title("Japanese Restaurant Ichi").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_japanese_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(japanese_res, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(kobe).title("(Teriyaki) Kobe Japanese Restaurant").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_japanese_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kobe, 11));
                        }
                        //Toast.makeText(mContext, "You Have Selected:" + parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                    } else if (position == 10) {
                        //korean
                        if (marker != null) {
                            mMap.clear();
                            spinner2.setSelection(adapter2.getCount());
                            marker = mMap.addMarker(new MarkerOptions().position(goong).title("Goong:The Castle").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_korean_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(goong, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(korean_res).title("Korean Restaurant").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_korean_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(korean_res, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(dae_jang).title("Dae Jang Geum- Korean Cuisine").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_korean_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dae_jang, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(suraon).title("Suraon").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_korean_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(suraon, 11));
                        }
                        //Toast.makeText(mContext, "You Have Selected:" + parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                    } else if (position == 11) {
                        //mexican
                        if (marker != null) {
                            mMap.clear();
                            spinner2.setSelection(adapter2.getCount());
                            marker = mMap.addMarker(new MarkerOptions().position(mexican_grill).title("Quesadilla La Mexicana Grill").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_mexican))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mexican_grill, 11));
                        }
                        //Toast.makeText(mContext, "You Have Selected:" + parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                    } else if (position == 12) {
                        //pizza
                        if (marker != null) {
                            mMap.clear();
                            spinner2.setSelection(adapter2.getCount());
                            marker = mMap.addMarker(new MarkerOptions().position(sbarro).title("Sbarro Bangladesh").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_pizza_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sbarro, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(pizza_hut_baily).title("Pizza Hut Baily Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_pizza_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pizza_hut_baily, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(pizza_hut_dhan).title("Pizza Hut Dhanmondi").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_pizza_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pizza_hut_dhan, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(pizza_hut_gul).title("Pizza Hut Gulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_pizza_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pizza_hut_gul, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(pizza_hut_uttara).title("Pizza Hut Uttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_pizza_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pizza_hut_uttara, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(pizza_hut_banani).title("Pizza Hut Banani").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_pizza_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pizza_hut_banani, 11));
                        }
                        //Toast.makeText(mContext, "You Have Selected:" + parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                    } else if (position == 13) {
                        //thai
                        if (marker != null) {
                            mMap.clear();
                            spinner2.setSelection(adapter2.getCount());
                            marker = mMap.addMarker(new MarkerOptions().position(absolute_thai).title("Absolute Thai").icon((BitmapDescriptorFactory.fromResource(R.drawable.thai_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(absolute_thai, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(corner_thai).title("Corner (Fusion) Thai Restaurant").icon((BitmapDescriptorFactory.fromResource(R.drawable.thai_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(corner_thai, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(red_tomato).title("Red Tomato").icon((BitmapDescriptorFactory.fromResource(R.drawable.thai_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(red_tomato, 11));
                            marker = mMap.addMarker(new MarkerOptions().position(soi).title("Soi 71").icon((BitmapDescriptorFactory.fromResource(R.drawable.thai_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(soi, 11));
                        }
                        //Toast.makeText(mContext, "You Have Selected:" + parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                    } else if (position == 14) {
                        if (marker != null) {
                            full_res();
                        }
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position == 0) {
                        if (marker != null) {
                            //uttara
                            mMap.clear();
                            spinner.setSelection(adapter1.getCount());
                            marker = mMap.addMarker(new MarkerOptions().position(kabab_factory).title("Kabab Factory").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_bbq_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kabab_factory, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(crossroads).title("Crossroads Lounge").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(crossroads, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(george_cafe).title("George's Cafe").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(george_cafe, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(mainland).title("Mainland China").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_chinese_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mainland, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(great_indian_res).title("Great Indian Restaurant").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_indian_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(great_indian_res, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(hungry_duck).title("Hungry Duck").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_international_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hungry_duck, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(rice_noodles).title("Rice n Noodies").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_international_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(rice_noodles, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(chef).title("The Chef").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_international_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(chef, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(veni).title("Veni Vidi Vici").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_italian_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(veni, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(pizza_hut_uttara).title("Pizza Hut Uttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_pizza_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pizza_hut_uttara, 12));
                        }
                    } else if (position == 1) {
                        if (marker != null) {
                            //gulshan
                            mMap.clear();
                            spinner.setSelection(adapter1.getCount());
                            marker = mMap.addMarker(new MarkerOptions().position(bbq_flames).title("Barbecue Flames").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_bbq_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bbq_flames, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(kasturi_garden).title("Kasturi Garden").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_bengali_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kasturi_garden, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(coffee_rep_bd).title("Coffee Rpublic Bangladesh").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coffee_rep_bd, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(gloria_jeans).title("Gloria Jean's").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(gloria_jeans, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(ffc_gulshan).title("FFC Gulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ffc_gulshan, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(kfc_gulshan).title("KFC Restaurant Gulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_chicken_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kfc_gulshan, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(nandos_gul).title("Nando's Gulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_chicken_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nandos_gul, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(black_tent).title("Black Tent").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_indian_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(black_tent, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(khana_khazana).title("Khana Khazana").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_indian_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(khana_khazana, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(mughal).title("The Mughal Kitchen").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_indian_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mughal, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(emma).title("Emma N Uelles Inn").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_international_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(emma, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(spaghetti).title("Spaghetti Jazz").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_italian_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(spaghetti, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(goong).title("Goong:The Castle").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_korean_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(goong, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(korean_res).title("Korean Restaurant").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_korean_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(korean_res, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(dae_jang).title("Dae Jang Geum- Korean Cuisine").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_korean_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dae_jang, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(suraon).title("Suraon").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_korean_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(suraon, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(sbarro).title("Sbarro Bangladesh").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_pizza_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sbarro, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(pizza_hut_gul).title("Pizza Hut Gulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_pizza_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pizza_hut_gul, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(corner_thai).title("Corner (Fusion) Thai Restaurant").icon((BitmapDescriptorFactory.fromResource(R.drawable.thai_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(corner_thai, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(soi).title("Soi 71").icon((BitmapDescriptorFactory.fromResource(R.drawable.thai_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(soi, 12));
                        }
                    } else if (position == 2) {
                        if (marker != null) {
                            //banani
                            mMap.clear();
                            spinner.setSelection(adapter1.getCount());
                            marker = mMap.addMarker(new MarkerOptions().position(aadi).title("Aadi Dhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_bengali_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(aadi, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(bread_b).title("Bread and Beyond").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bread_b, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(burger_boost).title("Burger n' Boost").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(burger_boost, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(cafe_hollywood).title("Cafe Hollywood").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cafe_hollywood, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(cafe_italiano).title("Cafe Italiano").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cafe_italiano, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(fiesta_res).title("Fiesta Restaurant").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fiesta_res, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(moka_cafe).title("Moka Cafe and Bistro").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(moka_cafe, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(fiesta_oven).title("Fiesta Oven Fresh bake and brew").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fiesta_oven, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(tastebud).title("Tastebud").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tastebud, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(oriental_lounge).title("The Oriental Lounge").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(oriental_lounge, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(white_hen).title("White Hen Gourmet and Pastry Shop").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(white_hen, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(kfc_banani).title("KFC Restaurant Banani").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_chicken_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kfc_banani, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(bukhara_res).title("Bukhara Restaurant").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_indian_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bukhara_res, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(dhaba).title("Dhaba").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_indian_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dhaba, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(handi_indian).title("Handi Indian Bistro").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_indian_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(handi_indian, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(loknow_res).title("Locknow Restaurant").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_indian_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loknow_res, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(nawaab).title("Nawaab's").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_indian_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nawaab, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(tarka).title("Tarka").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_indian_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tarka, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(casa_greek).title("Casa Greek").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_international_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(casa_greek, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(live_kitchen).title("Live Kitchen").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_international_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(live_kitchen, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(voot_res).title("Voot the Restaurant").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_international_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(voot_res, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(japanese_res).title("Japanese Restaurant Ichi").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_japanese_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(japanese_res, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(kobe).title("(Teriyaki) Kobe Japanese Restaurant").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_japanese_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kobe, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(mexican_grill).title("Quesadilla La Mexicana Grill").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_mexican))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mexican_grill, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(pizza_hut_banani).title("Pizza Hut Banani").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_pizza_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pizza_hut_banani, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(absolute_thai).title("Absolute Thai").icon((BitmapDescriptorFactory.fromResource(R.drawable.thai_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(absolute_thai, 12));

                        }
                    } else if (position == 3) {
                        //dhanmondi
                        if (marker != null) {
                            mMap.clear();
                            spinner.setSelection(adapter1.getCount());
                            marker = mMap.addMarker(new MarkerOptions().position(cilantro).title("Cilantro").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_brazilian_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cilantro, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(cafe_droom).title("Cafe Droom").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cafe_droom, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(stage).title("The Stage").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(stage, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(kfc_dhanmondi).title("KFC Restaurant Dhanmondi").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_chicken_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kfc_dhanmondi, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(nandos_dhan).title("Nando's Dhanmondi").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_chicken_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nandos_dhan, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(santoor).title("Santoor").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_indian_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(santoor, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(pizza_hut_dhan).title("Pizza Hut Dhanmondi").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_pizza_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pizza_hut_dhan, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(red_tomato).title("Red Tomato").icon((BitmapDescriptorFactory.fromResource(R.drawable.thai_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(red_tomato, 12));
                        }
                    } else if (position == 4) {
                        //mirpur
                        if (marker != null) {
                            mMap.clear();
                            spinner.setSelection(adapter1.getCount());
                            marker = mMap.addMarker(new MarkerOptions().position(kfc_mirpur).title("KFC Restaurant Mirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_chicken_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kfc_mirpur, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(ffc_mirpur).title("FFC Mirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ffc_mirpur, 12));
                        }
                    } else if (position == 5) {
                        //baily road
                        if (marker != null) {
                            mMap.clear();
                            spinner.setSelection(adapter1.getCount());
                            marker = mMap.addMarker(new MarkerOptions().position(kfc_baily_road).title("KFC Restaurant Baily Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_chicken_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kfc_baily_road, 12));
                            marker = mMap.addMarker(new MarkerOptions().position(pizza_hut_baily).title("Pizza Hut Baily Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_pizza_icon))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pizza_hut_baily, 12));

                        }
                    } else if (position == 6) {
                        if (marker != null) {
                            full_res();
                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

                @Override
                public View getInfoWindow(Marker marker) {
                    return null;
                }

                @Override
                public View getInfoContents(Marker marker) {
                    View v = getActivity().getLayoutInflater().inflate(R.layout.mark2, null);

                    if (marker.getTitle().equals("Kabab Factory")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Kabab Factory");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Barbeque\n12:00 pm - 12:00 am");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Uttara,Dhaka");

                        /*res_menu = (Button) v.findViewById(R.id.res_menu);
                        res_menu.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(mContext,"Menu button is clicked",Toast.LENGTH_LONG).show();
                            }
                        });*/
                    } else if (marker.getTitle().equals("Barbecue Flames")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Barbecue Flames");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Barbeque\n12:15 pm - 11:45 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Bir Uttam Mir Shawkat Sarak");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("+8802 8822293");
                    } else if (marker.getTitle().equals("Aadi Dhaka")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Aadi Dhaka");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Bengali\nHouse No. 11, Rd No.17A, Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("+8801618-881020");
                    } else if (marker.getTitle().equals("Kasturi Garden")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Kasturi Garden");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Bengali\n11:00 am – 10:00 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Gulshan-1, Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("+8801732172589");
                    } else if (marker.getTitle().equals("The Dinning Room")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("The Dinning Room");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Bengali\n12:00 pm – 11:00 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Banani, Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("01984332299");


                    } else if (marker.getTitle().equals("Cilantro")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Cilantro");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Brazilian\nDhanmondi,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("+8801766449912");
                    } else if (marker.getTitle().equals("Bread and Beyond")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Bread and Beyond");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Cafe\n7:00 am - 11:00 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Banani,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("+8801841320946-47");
                    } else if (marker.getTitle().equals("Burger n' Boost")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Burger n' Boost");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Cafe\nBanani,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("+8801760822356");
                    } else if (marker.getTitle().equals("Cafe Droom")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Cafe Droom");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Cafe\n11:00 am - 11:00 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Dhanmondi,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("+8801793399749");
                    } else if (marker.getTitle().equals("Cafe Hollywood")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Cafe Hollywood");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Cafe\n11:00 am - 11:00 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Banani Model Town,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("+8801716416005");
                    } else if (marker.getTitle().equals("Cafe Italiano")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Cafe Italiano");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Cafe\n11:00 am - 11:00 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Banani,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("01736-112233");
                    } else if (marker.getTitle().equals("Coffee Rpublic Bangladesh")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Coffee Rpublic Bangladesh");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Cafe\n11:00 am - 11:00 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Gulshan,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("+8801782510686");
                    } else if (marker.getTitle().equals("Crossroads Lounge")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Crossroads Lounge");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Cafe\n12:30 pm - 11:00 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Uttara,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("+88027913972");
                    } else if (marker.getTitle().equals("Fiesta Restaurant")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Fiesta Restaurant");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Cafe\n11:00 a.m – 11:30 p.m");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Banani,Dhaka");
                    } else if (marker.getTitle().equals("George's Cafe")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("George's Cafe");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Cafe\n11:00 am - 11:00 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Uttara,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("+8801784436743");
                    } else if (marker.getTitle().equals("Gloria Jean's")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Gloria Jean's");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Cafe\n7:00 a.m – 1:00 a.m");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Gulshan,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("+88 01970008989");
                    } else if (marker.getTitle().equals("Moka Cafe and Bistro")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Moka Cafe and Bistro");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Cafe\n12:00 pm – 1:00 am");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Banani,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("01788800303");
                    } else if (marker.getTitle().equals("Fiesta Oven Fresh bake and brew")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Fiesta Oven Fresh bake and brew");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Cafe\n10:00 am - 12:00 am");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Banani,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("01933448888");
                    } else if (marker.getTitle().equals("Tastebud")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Tastebud");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Cafe\n12:00 pm - 12:00 am");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Banani,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("+8801615250005");
                    } else if (marker.getTitle().equals("The Oriental Lounge")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("The Oriental Lounge");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Cafe\n11:00 am - 12:00 am");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Banani,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("+8801955800600");
                    } else if (marker.getTitle().equals("The Stage")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("The Stage");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Cafe\n11:00 am - 11:30 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Dhanmondi,Dhaka");
                    } else if (marker.getTitle().equals("White Hen Gourmet and Pastry Shop")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("White Hen Gourmet and Pastry Shop");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Cafe\nBanani,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("+8801678026225");
                    } else if (marker.getTitle().equals("FFC Gulshan")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("FFC");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Cafe\n10:00 am - 11:00 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Gulshan,Dhaka");
                    } else if (marker.getTitle().equals("FFC Mirpur")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("FFC");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Cafe\n10:00 am - 11:00 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Mirpur,Dhaka");
                    } else if (marker.getTitle().equals("KFC Restaurant Dhanmondi")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("KFC Restaurant");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Chicken Restaurant\n11:00 am - 12:00 am");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Dhanmondi,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("01787112112");
                    } else if (marker.getTitle().equals("KFC Restaurant Baily Road")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("KFC Restaurant");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Chicken Restaurant\n11:00 am - 12:00 am");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Baily Road,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("02-9342168");
                    } else if (marker.getTitle().equals("KFC Restaurant Mirpur")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("KFC Restaurant");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Chicken Restaurant\n11:00 am - 12:00 am");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Mirpur,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("02-8818327");
                    } else if (marker.getTitle().equals("KFC Restaurant Gulshan")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("KFC Restaurant");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Chicken Restaurant\n11:00 am - 12:00 am");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Gulshan,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("02-8837564-5");
                    } else if (marker.getTitle().equals("KFC Restaurant Paltan")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("KFC Restaurant");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Chicken Restaurant\n11:00 am - 12:00 am");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Paltan,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("02-8837564-5");
                    } else if (marker.getTitle().equals("KFC Restaurant Panthapath")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("KFC Restaurant");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Chicken Restaurant\n11:00 am - 12:00 am");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Panthapath,Dhaka");
                    } else if (marker.getTitle().equals("KFC Restaurant Banani")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("KFC Restaurant");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Chicken Restaurant\n11:00 am - 12:00 am");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Banani,Dhaka");
                    } else if (marker.getTitle().equals("Nando's Gulshan")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Nando's");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Chicken Restaurant\n12:00 pm - 12:00 am");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Gulshan,Dhaka");
                    } else if (marker.getTitle().equals("Nando's Dhanmondi")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Nando's");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Chicken Restaurant\n12:00 pm - 12:00 am");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Dhanmondi,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("09612400400");
                    } else if (marker.getTitle().equals("Mainland China")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Mainland China");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Chinese\n12:30 pm - 11:00 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Mymensingh Highway,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("02-7912588");
                    } else if (marker.getTitle().equals("Black Tent")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Black Tent");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Indian\n11:00 am - 10:00 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Gulshan,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("01732-431001");
                    } else if (marker.getTitle().equals("Bukhara Restaurant")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Bukhara Restaurant");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Indian\nKemal Ataturk Ave,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("02-8812411");
                    } else if (marker.getTitle().equals("Dhaba")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Dhaba");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Indian\nDhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("01756-710097");
                    } else if (marker.getTitle().equals("Great Indian Restaurant")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Great Indian Restaurant");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Indian\nJashimuddin Ave,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("01786-064031");
                    } else if (marker.getTitle().equals("Handi Indian Bistro")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Handi Indian Bistro");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Indian\n12:00 am - 11:30 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("01950-015077");
                    } else if (marker.getTitle().equals("Khana Khazana")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Khana Khazana");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Indian\n12:00 pm - 10:30 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Gulshan,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("01616770016");
                    } else if (marker.getTitle().equals("Locknow Restaurant")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Locknow Restaurant");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Indian\nBanani,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("01711-243466");
                    } else if (marker.getTitle().equals("The Mughal Kitchen")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("The Mughal Kitchen");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Indian\n12:30 pm - 10:00 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Gulshan,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("+8801841684425");
                    } else if (marker.getTitle().equals("Nawaab's")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Nawaab's");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Indian\n12:30 pm - 10:30 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Banani,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("01616292227");
                    } else if (marker.getTitle().equals("Santoor")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Santoor");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Indian\nMirpur,Dhaka");

                    } else if (marker.getTitle().equals("Tarka")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Tarka");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Indian\n12:30 pm - 11:30 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Banani,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("01671448844");
                    } else if (marker.getTitle().equals("Angaar")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Angaar");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Indian\n12:30 pm - 11:00 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Gulshan,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("+8801972261111");

                    } else if (marker.getTitle().equals("Hakka Dhaka")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Hakka Dhaka");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Indian\n12:30 pm - 4:00 pm and 6:00 pm - 11:00 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Banani,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("01616666543");

                    } else if (marker.getTitle().equals("Sajna")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Sajna");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Indian\n12:30 pm - 11:00 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Banani,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("02-8811684");

                    } else if (marker.getTitle().equals("Koyla")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Koyla");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Indian\n12:00 pm - 11:00 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Gulshan,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("01715-260609");

                    } else if (marker.getTitle().equals("Casa Greek")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Casa Greek");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- International\n10:00 am - 11:00 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("01841110991");
                    } else if (marker.getTitle().equals("Emma N Uelles Inn")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Emma N Uelles Inn");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- International\n11:00 am - 11:00 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Gulshan,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("01841-881186");
                    } else if (marker.getTitle().equals("Hungry Duck")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Hungry Duck");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- International\nUttara,Dhaka");

                    } else if (marker.getTitle().equals("Live Kitchen")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Live Kitchen");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- International\n11:30 am - 10:30 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Banani,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("+8801784880838");
                    } else if (marker.getTitle().equals("Rice n Noodies")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Rice n Noodies");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- International\n12:30 pm - 11:00 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("01710811918");
                    } else if (marker.getTitle().equals("The Chef")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("The Chef");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- International\n12:00 pm - 12:00 am");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("01791-555111");
                    } else if (marker.getTitle().equals("Voot the Restaurant")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Voot the Restaurant");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- International\n11:30 am - 11:00 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Banani,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("01979650751");
                    } else if (marker.getTitle().equals("Loiter D85")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Loiter D85");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- International\n11:00 am - 10:30 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Banani,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("01676858585");

                    } else if (marker.getTitle().equals("Spaghetti Jazz")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Spaghetti Jazz");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Italian\n6:00 am - 3:00 am");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Gulshan,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("02-8822062");
                    } else if (marker.getTitle().equals("Veni Vidi Vici")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Veni Vidi Vici");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Italian\n12:00 pm - 11:00 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Uttara,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("+8801705737888");
                    } else if (marker.getTitle().equals("Terra Bistro")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Terra Bistro");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Italian\nAlways Open");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Uttara,Dhaka");

                    } else if (marker.getTitle().equals("Japanese Restaurant Ichi")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Japanese Restaurant Ichi");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Japanese\n12:00 pm - 12:00 am");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Banani,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("+88-01759-317926");
                    } else if (marker.getTitle().equals("(Teriyaki) Kobe Japanese Restaurant")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("(Teriyaki) Kobe Japanese Restaurant");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Japanese\n12:00 pm - 12:00 am");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Banani,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("01791406080");
                    } else if (marker.getTitle().equals("Goong:The Castle")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Goong:The Castle");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Korean\nGulshan,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("017-8001-6161");
                    } else if (marker.getTitle().equals("Korean Restaurant")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Korean Restaurant");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Korean\n11:00 am - 10:30 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Gulshan,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("01621212334");
                    } else if (marker.getTitle().equals("Dae Jang Geum- Korean Cuisine")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Dae Jang Geum- Korean Cuisine");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Korean\n11:45 am - 11:00 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Gulshan,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("02-8837681");
                    } else if (marker.getTitle().equals("Suraon")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Suraon");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Korean\n12:00 pm - 3:00 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Gulshan,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("02-9860836");
                    } else if (marker.getTitle().equals("DuMiOk")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("DuMiOk");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Type- Korean\nBanani,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("029885512");
                    } else if (marker.getTitle().equals("Quesadilla La Mexicana Grill")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Quesadilla La Mexicana Grill");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Mexican\n10:00 am - 11:00 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Gulshan,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("01764480145");
                    } else if (marker.getTitle().equals("Sbarro Bangladesh")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Sbarro Bangladesh");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Pizza\nBanani,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("01777700664");
                    } else if (marker.getTitle().equals("Pizza Hut Baily Road")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Pizza Hut");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Pizza\n11:00 am - 11:00 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Baily Road,Dhaka");

                    } else if (marker.getTitle().equals("Pizza Hut Dhanmondi")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Pizza Hut");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Pizza\n11:00 am - 11:00 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Dhanmondi,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("01786064031");
                    } else if (marker.getTitle().equals("Pizza Hut Gulshan")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Pizza Hut");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Pizza\n11:00 am - 11:00 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Gulshan,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("02-8857535-8");
                    } else if (marker.getTitle().equals("Pizza Hut Uttara")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Pizza Hut");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Pizza\n11:00 am - 11:00 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Uttara,Dhaka");
                    } else if (marker.getTitle().equals("Pizza Hut Banani")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Pizza Hut");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Pizza\n11:00 am - 11:00 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Banani,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("09613-999777");
                    } else if (marker.getTitle().equals("Absolute Thai")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Absolute Thai");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Thai\n12:00 pm - 11:00 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Banani,Dhaka");

                    } else if (marker.getTitle().equals("Corner (Fusion) Thai Restaurant")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Corner (Fusion) Thai Restaurant");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Thai\n11:00 am - 11:30 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Gulshan,Dhaka");

                    } else if (marker.getTitle().equals("Red Tomato")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Red Tomato");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Thai\n12:00 pm - 11:00 pm");

                        TextView tvs1 = (TextView) v.findViewById(R.id.textView7);
                        tvs1.setText("Dhanmondi,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("+8801923850917");
                    } else if (marker.getTitle().equals("Soi 71")) {
                        TextView thana = (TextView) v.findViewById(R.id.thanName);
                        thana.setText("Soi 71");

                        TextView tvs = (TextView) v.findViewById(R.id.textView6);
                        tvs.setText("Type- Thai\nGulshan,Dhaka");

                        tvs2 = (TextView) v.findViewById(R.id.textView8);
                        tvs2.setText("01755515851");
                    }

                    return v;
                }
            });
        }
    }

    public void full_res() {
        mMap.clear();
        //spinner.setSelection(adapter1.getCount());
        marker = mMap.addMarker(new MarkerOptions().position(kabab_factory).title("Kabab Factory").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_bbq_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kabab_factory, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(bbq_flames).title("Barbecue Flames").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_bbq_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bbq_flames, 19.0f));

        marker = mMap.addMarker(new MarkerOptions().position(aadi).title("Aadi Dhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_bengali_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(aadi, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(kasturi_garden).title("Kasturi Garden").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_bengali_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kasturi_garden, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(dinning_room).title("The Dinning Room").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_bengali_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dinning_room, 19.0f));

        marker = mMap.addMarker(new MarkerOptions().position(cilantro).title("Cilantro").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_brazilian_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cilantro, 19.0f));

        marker = mMap.addMarker(new MarkerOptions().position(bread_b).title("Bread and Beyond").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bread_b, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(burger_boost).title("Burger n' Boost").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(burger_boost, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(cafe_droom).title("Cafe Droom").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cafe_droom, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(cafe_hollywood).title("Cafe Hollywood").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cafe_hollywood, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(cafe_italiano).title("Cafe Italiano").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cafe_italiano, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(coffee_rep_bd).title("Coffee Rpublic Bangladesh").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coffee_rep_bd, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(crossroads).title("Crossroads Lounge").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(crossroads, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(fiesta_res).title("Fiesta Restaurant").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fiesta_res, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(george_cafe).title("George's Cafe").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(george_cafe, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(gloria_jeans).title("Gloria Jean's").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(gloria_jeans, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(moka_cafe).title("Moka Cafe and Bistro").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(moka_cafe, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(fiesta_oven).title("Fiesta Oven Fresh bake and brew").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fiesta_oven, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(tastebud).title("Tastebud").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tastebud, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(oriental_lounge).title("The Oriental Lounge").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(oriental_lounge, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(stage).title("The Stage").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(stage, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(white_hen).title("White Hen Gourmet and Pastry Shop").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(white_hen, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(ffc_gulshan).title("FFC Gulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ffc_gulshan, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(ffc_mirpur).title("FFC Mirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_cafe_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ffc_mirpur, 19.0f));

        marker = mMap.addMarker(new MarkerOptions().position(kfc_dhanmondi).title("KFC Restaurant Dhanmondi").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_chicken_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kfc_dhanmondi, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(kfc_baily_road).title("KFC Restaurant Baily Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_chicken_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kfc_baily_road, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(kfc_mirpur).title("KFC Restaurant Mirpur").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_chicken_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kfc_mirpur, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(kfc_gulshan).title("KFC Restaurant Gulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_chicken_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kfc_gulshan, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(kfc_paltan).title("KFC Restaurant Paltan").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_chicken_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kfc_paltan, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(kfc_panthapath).title("KFC Restaurant Panthapath").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_chicken_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kfc_panthapath, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(kfc_banani).title("KFC Restaurant Banani").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_chicken_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kfc_banani, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(nandos_gul).title("Nando's Gulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_chicken_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nandos_gul, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(nandos_dhan).title("Nando's Dhanmondi").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_chicken_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nandos_dhan, 19.0f));

        marker = mMap.addMarker(new MarkerOptions().position(mainland).title("Mainland China").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_chinese_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mainland, 19.0f));

        marker = mMap.addMarker(new MarkerOptions().position(black_tent).title("Black Tent").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_indian_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(black_tent, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(bukhara_res).title("Bukhara Restaurant").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_indian_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bukhara_res, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(dhaba).title("Dhaba").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_indian_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dhaba, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(great_indian_res).title("Great Indian Restaurant").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_indian_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(great_indian_res, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(handi_indian).title("Handi Indian Bistro").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_indian_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(handi_indian, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(khana_khazana).title("Khana Khazana").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_indian_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(khana_khazana, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(loknow_res).title("Locknow Restaurant").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_indian_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loknow_res, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(mughal).title("The Mughal Kitchen").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_indian_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mughal, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(nawaab).title("Nawaab's").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_indian_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nawaab, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(santoor).title("Santoor").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_indian_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(santoor, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(tarka).title("Tarka").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_indian_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tarka, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(angaar).title("Angaar").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_indian_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(angaar, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(hakka_dhaka).title("Hakka Dhaka").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_indian_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hakka_dhaka, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(sajna).title("Sajna").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_indian_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sajna, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(koyla).title("Koyla").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_indian_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(koyla, 19.0f));

        marker = mMap.addMarker(new MarkerOptions().position(casa_greek).title("Casa Greek").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_international_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(casa_greek, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(emma).title("Emma N Uelles Inn").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_international_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(emma, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(hungry_duck).title("Hungry Duck").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_international_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hungry_duck, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(live_kitchen).title("Live Kitchen").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_international_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(live_kitchen, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(rice_noodles).title("Rice n Noodies").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_international_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(rice_noodles, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(chef).title("The Chef").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_international_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(chef, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(voot_res).title("Voot the Restaurant").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_international_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(voot_res, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(loiter).title("Loiter D85").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_international_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loiter, 19.0f));

        marker = mMap.addMarker(new MarkerOptions().position(spaghetti).title("Spaghetti Jazz").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_italian_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(spaghetti, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(veni).title("Veni Vidi Vici").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_italian_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(veni, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(terra).title("Terra Bistro").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_italian_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(terra, 19.0f));

        marker = mMap.addMarker(new MarkerOptions().position(japanese_res).title("Japanese Restaurant Ichi").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_japanese_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(japanese_res, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(kobe).title("(Teriyaki) Kobe Japanese Restaurant").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_japanese_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kobe, 19.0f));

        marker = mMap.addMarker(new MarkerOptions().position(goong).title("Goong:The Castle").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_korean_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(goong, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(korean_res).title("Korean Restaurant").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_korean_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(korean_res, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(dae_jang).title("Dae Jang Geum- Korean Cuisine").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_korean_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dae_jang, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(suraon).title("Suraon").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_korean_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(suraon, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(dumi).title("DuMiOk").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_korean_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dumi, 19.0f));

        marker = mMap.addMarker(new MarkerOptions().position(mexican_grill).title("Quesadilla La Mexicana Grill").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_mexican))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mexican_grill, 19.0f));

        marker = mMap.addMarker(new MarkerOptions().position(sbarro).title("Sbarro Bangladesh").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_pizza_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sbarro, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(pizza_hut_baily).title("Pizza Hut Baily Road").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_pizza_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pizza_hut_baily, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(pizza_hut_dhan).title("Pizza Hut Dhanmondi").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_pizza_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pizza_hut_dhan, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(pizza_hut_gul).title("Pizza Hut Gulshan").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_pizza_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pizza_hut_gul, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(pizza_hut_uttara).title("Pizza Hut Uttara").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_pizza_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pizza_hut_uttara, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(pizza_hut_banani).title("Pizza Hut Banani").icon((BitmapDescriptorFactory.fromResource(R.drawable.restaurent_pizza_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pizza_hut_banani, 19.0f));

        marker = mMap.addMarker(new MarkerOptions().position(absolute_thai).title("Absolute Thai").icon((BitmapDescriptorFactory.fromResource(R.drawable.thai_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(absolute_thai, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(corner_thai).title("Corner (Fusion) Thai Restaurant").icon((BitmapDescriptorFactory.fromResource(R.drawable.thai_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(corner_thai, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(red_tomato).title("Red Tomato").icon((BitmapDescriptorFactory.fromResource(R.drawable.thai_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(red_tomato, 19.0f));
        marker = mMap.addMarker(new MarkerOptions().position(soi).title("Soi 71").icon((BitmapDescriptorFactory.fromResource(R.drawable.thai_icon))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(soi, 19.0f));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        AreaLatLng = new ArrayList<>();

        //bbq
        AreaLatLng.add(new LatLng(23.8675231, 90.393985));
        AreaLatLng.add(new LatLng(23.7748592, 90.4164103));

        //bengali
        AreaLatLng.add(new LatLng(23.7922934, 90.4071356));
        AreaLatLng.add(new LatLng(23.780741, 90.412582));
        AreaLatLng.add(new LatLng(23.790062, 90.409208));

        //brazilian
        AreaLatLng.add(new LatLng(23.7464836, 90.3708556));

        //cafe
        AreaLatLng.add(new LatLng(23.7941337, 90.4023394));
        AreaLatLng.add(new LatLng(23.7916017, 90.4028049));
        AreaLatLng.add(new LatLng(23.7519387, 90.3700081));
        AreaLatLng.add(new LatLng(23.79017, 90.4077253));
        AreaLatLng.add(new LatLng(23.790749, 90.404144));
        AreaLatLng.add(new LatLng(23.7932342, 90.4056975));
        AreaLatLng.add(new LatLng(23.8724414, 90.3908849));
        AreaLatLng.add(new LatLng(23.7911873, 90.4013202));
        AreaLatLng.add(new LatLng(23.859406, 90.40061));
        AreaLatLng.add(new LatLng(23.7791694, 90.4163942));
        AreaLatLng.add(new LatLng(23.7900314, 90.4091737));
        AreaLatLng.add(new LatLng(23.791056, 90.40135));
        AreaLatLng.add(new LatLng(23.7899886, 90.4083459));
        AreaLatLng.add(new LatLng(23.7917089, 90.4080753));
        AreaLatLng.add(new LatLng(23.7416567, 90.3740877));
        AreaLatLng.add(new LatLng(23.792162, 90.4065017));
        AreaLatLng.add(new LatLng(23.8146947, 90.4117061));
        AreaLatLng.add(new LatLng(23.8160652, 90.3663683));

        //chicken
        AreaLatLng.add(new LatLng(23.7435263, 90.3734159));
        AreaLatLng.add(new LatLng(23.741921, 90.409687));
        AreaLatLng.add(new LatLng(23.816756, 90.36621));
        AreaLatLng.add(new LatLng(23.7753465, 90.4164758));
        AreaLatLng.add(new LatLng(23.7300124, 90.4111464));
        AreaLatLng.add(new LatLng(23.7527219, 90.3808549));
        AreaLatLng.add(new LatLng(23.7937659, 90.4055305));
        AreaLatLng.add(new LatLng(23.7765065, 90.4166861));
        AreaLatLng.add(new LatLng(23.7528937, 90.3699088));

        //chinese
        AreaLatLng.add(new LatLng(23.8624979, 90.3995916));

        //indian
        AreaLatLng.add(new LatLng(23.7805243, 90.4163378));
        AreaLatLng.add(new LatLng(23.793453, 90.405625));
        AreaLatLng.add(new LatLng(23.7919592, 90.4067516));
        AreaLatLng.add(new LatLng(23.8623038, 90.3995422));
        AreaLatLng.add(new LatLng(23.7907394, 90.4061173));
        AreaLatLng.add(new LatLng(23.7815527, 90.4160294));
        AreaLatLng.add(new LatLng(23.793282, 90.409186));
        AreaLatLng.add(new LatLng(23.7795916, 90.4145623));
        AreaLatLng.add(new LatLng(23.7913776, 90.4048285));
        AreaLatLng.add(new LatLng(23.7517616, 90.3772631));
        AreaLatLng.add(new LatLng(23.7913726, 90.4062018));
        AreaLatLng.add(new LatLng(23.7768125, 90.4151322));
        AreaLatLng.add(new LatLng(23.7913492, 90.4061964));
        AreaLatLng.add(new LatLng(23.7903135, 90.4070327));
        AreaLatLng.add(new LatLng(23.771037, 90.410760));


        //international
        AreaLatLng.add(new LatLng(23.792222, 90.406484));
        AreaLatLng.add(new LatLng(23.8146947, 90.4117061));
        AreaLatLng.add(new LatLng(23.8639891, 90.3993461));
        AreaLatLng.add(new LatLng(23.7914421, 90.4043662));
        AreaLatLng.add(new LatLng(23.875478, 90.384135));
        AreaLatLng.add(new LatLng(23.8645275, 90.4012877));
        AreaLatLng.add(new LatLng(23.8000712, 90.393107));
        AreaLatLng.add(new LatLng(23.7911763, 90.4042411));

        //italian
        AreaLatLng.add(new LatLng(23.7953583, 90.4143155));
        AreaLatLng.add(new LatLng(23.866336, 90.397706));
        AreaLatLng.add(new LatLng(23.7907891, 90.40291));

        //japanese
        AreaLatLng.add(new LatLng(23.790714, 90.404123));
        AreaLatLng.add(new LatLng(23.7903565, 90.4082295));

        //korean
        AreaLatLng.add(new LatLng(23.797308, 90.410514));
        AreaLatLng.add(new LatLng(23.785118, 90.419266));
        AreaLatLng.add(new LatLng(23.795184, 90.410712));
        AreaLatLng.add(new LatLng(23.803771, 90.413552));
        AreaLatLng.add(new LatLng(23.7906094, 90.4067919));

        //mexican
        AreaLatLng.add(new LatLng(23.7903698, 90.408209));

        //pizza
        AreaLatLng.add(new LatLng(23.7963265, 90.4132077));
        AreaLatLng.add(new LatLng(23.7418787, 90.4099441));
        AreaLatLng.add(new LatLng(23.7475012, 90.3702629));
        AreaLatLng.add(new LatLng(23.776444, 90.4168491));
        AreaLatLng.add(new LatLng(23.8730892, 90.3914297));
        AreaLatLng.add(new LatLng(23.791234, 90.4018546));

        //thai
        AreaLatLng.add(new LatLng(23.789293, 90.409268));
        AreaLatLng.add(new LatLng(23.7805685, 90.4163003));
        AreaLatLng.add(new LatLng(23.7527022, 90.3689003));
        AreaLatLng.add(new LatLng(23.8040873, 90.4136828));

        timecounter = 0;
        // inflat and return the layout
        View v = inflater.inflate(R.layout.fragment_restaurant, container, false);

        res_menu = (Button) v.findViewById(R.id.res_menu);
        res_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menu = new Intent("com.citylifeezy.citylifeezy.citylifeezy.Res_menu");
                startActivity(menu);
            }
        });

        spinner = (Spinner) v.findViewById(R.id.spinner);

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

        adapter1.add("Barbeque");
        adapter1.add("Bengali");
        adapter1.add("Brazilian");
        adapter1.add("Cafe");
        adapter1.add("Chicken");
        adapter1.add("Chinese");
        adapter1.add("Indian");
        adapter1.add("International");
        adapter1.add("Italian");
        adapter1.add("Japanese");
        adapter1.add("Korean");
        adapter1.add("Mexican");
        adapter1.add("Pizza");
        adapter1.add("Thai");
        adapter1.add("Show all");
        adapter1.add("Category");

        adapter1.setDropDownViewResource(R.layout.trends_spinner_list);
        spinner.setAdapter(adapter1);
        spinner.setSelection(adapter1.getCount()); //set the hint the default selection so it appears on launch.


        spinner2 = (Spinner) v.findViewById(R.id.spinner2);

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

        adapter2.add("Uttara");
        adapter2.add("Gulshan");
        adapter2.add("Banani");
        adapter2.add("Dhanmondi");
        adapter2.add("Mirpur");
        adapter2.add("Baily Road");
        adapter2.add("Show all");
        adapter2.add("Area");

        adapter2.setDropDownViewResource(R.layout.trends_spinner_list);
        spinner2.setAdapter(adapter2);
        spinner2.setSelection(adapter2.getCount());


        AutoCompleteTextView myTextView = (AutoCompleteTextView) getActivity().findViewById(R.id.autoCompleteTextViewOK);


        actv = (AutoCompleteTextView) v.findViewById(R.id.autoCompleteTextViewOK);


        autocomTextView = (AutoCompleteTextView) v.findViewById(R.id.autoCompleteTextViewOK);

        myImageView = (ImageView) v.findViewById(R.id.searchAreaView);

        mMapView = (MapView) v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);


        String[] areas = getResources().
                getStringArray(R.array.list_of_restaurant);

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, areas);

        actv.setAdapter(adapter);

        Menu menu = null;

        setUpMapIfNeeded_1(v);

        processMap(v);

        //button_click();

        sp = mContext.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        return v;
    }


    private AutoCompleteTextView actv;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        RestaurantFragment myListFragment = new RestaurantFragment();

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
                    spinner.setVisibility(View.GONE);
                    spinner2.setVisibility(View.GONE);
                    res_menu.setVisibility(View.GONE);
                    searchCounter = 0;
                } else {
                    autocomTextView.setVisibility(View.VISIBLE);
                    myImageView.setVisibility(View.VISIBLE);
                    spinner.setVisibility(View.VISIBLE);
                    spinner2.setVisibility(View.VISIBLE);
                    res_menu.setVisibility(View.VISIBLE);
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


            if (mMap != null) {
                mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                    @Override
                    public View getInfoWindow(Marker marker) {
                        return null;
                    }

                    @Override
                    public View getInfoContents(Marker marker) {

                        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View v = inflater.inflate(R.layout.windowlayout3, null);

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


    ImageView gpsCenterView, rectangleView_1, rectangleView_2, rectangleView_3, rectangleView_4, rectangleView_5;
    ImageView doSearchView;

    AutoCompleteTextView userInputTextView;


    // Calling the GPSListener and getting the Locate Me to Center

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        // get the button view
//        gpsView = (ImageView) getView().findViewById(R.id.gpslocationView);

        doSearchView = (ImageView) getView().findViewById(R.id.searchAreaView);

        // set a onclick listener for when the button gets clicked
//        gpsView.setOnClickListener(new View.OnClickListener() {
//            // Start new list activity
//            public void onClick(View v) {
//                gpsCounter = 0;
//
//                RestaurantFragment.this.GetMyLocation();
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
                        getStringArray(R.array.list_of_restaurant);

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
        SharedPreferences prefs = mContext.getSharedPreferences("AREAID111", Context.MODE_PRIVATE);
        int count = prefs.getInt("Count111", 0);
        ret = new int[count];
        for (int i = 0; i < count; i++) {
            ret[i] = prefs.getInt("IntValue_111" + i, i);
        }
        return ret;
    }


    public String[] getFromPrefsString_1() {
        String[] ret;
        SharedPreferences prefs = mContext.getSharedPreferences("AREA_STAT_1_211", Context.MODE_PRIVATE);
        int count = prefs.getInt("Count111", 0);
        ret = new String[count];
        for (int i = 0; i < count; i++) {
            ret[i] = prefs.getString("IntValue_111" + i, Integer.toString(i));
        }
        return ret;
    }


    public String[] getFromPrefsString_2() {
        String[] ret;
        SharedPreferences prefs = mContext.getSharedPreferences("AREA_STAT_2_211", Context.MODE_PRIVATE);
        int count = prefs.getInt("Count111", 0);
        ret = new String[count];
        for (int i = 0; i < count; i++) {
            ret[i] = prefs.getString("IntValue_111" + i, Integer.toString(i));
        }
        return ret;
    }


    public String[] getFromPrefsString_3() {
        String[] ret;
        SharedPreferences prefs = mContext.getSharedPreferences("AREA_STAT_3_211", Context.MODE_PRIVATE);
        int count = prefs.getInt("Count111", 0);
        ret = new String[count];
        for (int i = 0; i < count; i++) {
            ret[i] = prefs.getString("IntValue_111" + i, Integer.toString(i));
        }
        return ret;
    }


    public String[] getFromPrefsString_4() {
        String[] ret;
        SharedPreferences prefs = mContext.getSharedPreferences("AREA_STAT_4_211", Context.MODE_PRIVATE);
        int count = prefs.getInt("Count111", 0);
        ret = new String[count];
        for (int i = 0; i < count; i++) {
            ret[i] = prefs.getString("IntValue_111" + i, Integer.toString(i));
        }
        return ret;
    }


    public String[] getFromPrefsString_5() {
        String[] ret;
        SharedPreferences prefs = mContext.getSharedPreferences("UPDATETIME111", Context.MODE_PRIVATE);
        int count = prefs.getInt("Count111", 0);
        ret = new String[count];
        for (int i = 0; i < count; i++) {
            ret[i] = prefs.getString("IntValue_111" + i, Integer.toString(i));
        }
        return ret;
    }


    public String[] getFromPrefsString_6() {
        String[] ret;
        SharedPreferences prefs = mContext.getSharedPreferences("ISACTIVE111", Context.MODE_PRIVATE);
        int count = prefs.getInt("Count111", 0);
        ret = new String[count];
        for (int i = 0; i < count; i++) {
            ret[i] = prefs.getString("IntValue_111" + i, Integer.toString(i));
        }
        return ret;
    }


    public String[] getFromPrefsString_IncidentUpdateTime() {
        String[] ret;
        SharedPreferences prefs = mContext.getSharedPreferences("INCIDENT_UPDATE_TIME111", Context.MODE_PRIVATE);
        int count = prefs.getInt("Count111", 0);
        ret = new String[count];
        for (int i = 0; i < count; i++) {
            ret[i] = prefs.getString("IntValue_111" + i, Integer.toString(i));
        }
        return ret;
    }


    public String[] getFromPrefsString_IncidentIsActive() {
        String[] ret;
        SharedPreferences prefs = mContext.getSharedPreferences("INCIDENT_IS_ACTIVE111", Context.MODE_PRIVATE);
        int count = prefs.getInt("Count111", 0);
        ret = new String[count];
        for (int i = 0; i < count; i++) {
            ret[i] = prefs.getString("IntValue_111" + i, Integer.toString(i));
        }
        return ret;
    }


    public String[] getFromPrefsString_IncidentCentralCord() {
        String[] ret;
        SharedPreferences prefs = mContext.getSharedPreferences("INCIDENT_CENTRAL_CORD111", Context.MODE_PRIVATE);
        int count = prefs.getInt("Count111", 0);
        ret = new String[count];
        for (int i = 0; i < count; i++) {
            ret[i] = prefs.getString("IntValue_111" + i, Integer.toString(i));
        }
        return ret;
    }


    public String[] getFromPrefsString_IncidentName() {
        String[] ret;
        SharedPreferences prefs = mContext.getSharedPreferences("INCIDENT_NAME111", Context.MODE_PRIVATE);
        int count = prefs.getInt("Count111", 0);
        ret = new String[count];
        for (int i = 0; i < count; i++) {
            ret[i] = prefs.getString("IntValue_111" + i, Integer.toString(i));
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

