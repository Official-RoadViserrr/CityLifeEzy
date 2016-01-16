package com.citylifeezy.citylifeezy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.citylifeezy.citylifeezy.activity.R;
import com.citylifeezy.citylifeezy.fragments.FragmentList;



public class ListActivity extends FragmentActivity implements View.OnClickListener{

    @Override
    public void onClick(View v) {
        Toast.makeText(getApplicationContext(), "Your Places' List Updated Successfully", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, DrawerActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);


        Button myButton = (Button) findViewById(R.id.doneButton);
        myButton.setOnClickListener(this);




        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction ft = manager.beginTransaction();

        FragmentList fragmentList = FragmentList.newInstance();

        if (manager.findFragmentByTag("fragment_list") == null) {
            ft.replace(R.id.main_content, fragmentList, "fragment_list").commit();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }






}
