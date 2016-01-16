package com.citylifeezy.citylifeezy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.citylifeezy.citylifeezy.activity.R;
import com.citylifeezy.citylifeezy.adapters.ImportantNumberCardViewAdapter;
import com.citylifeezy.citylifeezy.models.Data;
import com.citylifeezy.citylifeezy.models.PhoneNumber;

import java.util.ArrayList;

public class ImportantPhoneDetailActivity extends AppCompatActivity {
    public static final String INTENT_NAME = "intent_name";
    public String intentName;
    private RecyclerView recyclerView;
    private ArrayList<PhoneNumber> phoneNumber;
    private RecyclerView.Adapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.important_phone_detail_activity);
        Intent intent = getIntent();
        intentName = intent.getStringExtra(INTENT_NAME);
        // intentName = "Rab";
    //    Toast.makeText(getApplicationContext(), intentName, Toast.LENGTH_LONG).show();
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(intentName);
       // loadBackdrop(id);
        initContrls();
    }

    private void loadBackdrop(int id) {
        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Glide.with(this).load(id).centerCrop().into(imageView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initContrls() {

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        switch (intentName) {
            case "Ambulance Service":
                phoneNumber = Data.populateAmbulanceServicePhoneNumber();
                loadBackdrop(R.drawable.ambulance);
                break;
            case "Blood Banks":
                phoneNumber = Data.populateBloodBankPhoneNumber();
                loadBackdrop(R.drawable.blood);

                break;
            case "Fire Brigades & Services":
                phoneNumber = Data.populateFireStationPhoneNumber();
                loadBackdrop(R.drawable.fire);
                break;
            case "Hospitals":
                phoneNumber = Data.populateHospitalPhoneNumber();
                loadBackdrop(R.drawable.hospital);
                break;
            case "Dhaka Police Thana":
                phoneNumber = Data.populatePolicePhoneNumber();
                loadBackdrop(R.drawable.police_stations);
                break;
            case "RAB":
                phoneNumber = Data.populateRabPhoneNumber();
                loadBackdrop(R.drawable.rab);
                break;
            case "WASA Emergency Water Delivery":
                phoneNumber = Data.populateWasaPhoneNumber();
                loadBackdrop(R.drawable.wasa);
                break;

        }
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ImportantNumberCardViewAdapter(phoneNumber);
        recyclerView.setAdapter(mAdapter);
    }
}
