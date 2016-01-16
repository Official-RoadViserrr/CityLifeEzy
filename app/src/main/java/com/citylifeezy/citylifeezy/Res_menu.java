package com.citylifeezy.citylifeezy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.citylifeezy.citylifeezy.activity.R;
import com.citylifeezy.citylifeezy.adapters.MyMenuAdapter;

/**
 * Created by Asif on 9/13/2015.
 */
public class Res_menu extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.res_menu);

        final String[] r_menu ={"Aadi Dhaka","Barbecue Flames","Black Tent","Bread and Beyond",
                "Bukhara Restaurant","Burger n' Boost","Cafe Hollywood",
                "Cafe Italiano","Casa Greek","Cilantro",
                "Coffee Rpublic Bangladesh","Corner (Fusion) Thai Restaurant","Crossroads Lounge",
                "Droom","Dhaba","FFC",
                "Gloria Jean's","Great Indian Restaurant",
                "Hungry Duck",
                "Kabab Factory","Kasturi Garden","KFC Restaurant",
                "Khana Khazana","King Be","(Teriyaki) Kobe Japanese Restaurant","Korean Restaurant",
                "Locknow Restaurant","Mainland China","Moka Cafe and Bistro",
                "Nando's","Nawaab's","OvenFresh","Pizza Hut",
                "Quesadilla La Mexicana Grill","Red Tomato",
                "Santoor","Sbarro Bangladesh","Shawarma","Soi 71",
                "Tarka","The Chef",
                "The Mughal Kitchen","The Stage",
                "Voot the Restaurant","White Hen Gourmet and Pastry Shop",};

        ListAdapter listAdapter = new MyMenuAdapter(this,r_menu);
        ListView listView = (ListView) findViewById(R.id.menu_list);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String res_name = parent.getItemAtPosition(position).toString();

                if (res_name.equals("Kabab Factory")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "kabab");
                    startActivity(i);
                } else if (res_name.equals("Barbecue Flames")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "bbq");
                    startActivity(i);
                }else if (res_name.equals("Aadi Dhaka")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "adi");
                    startActivity(i);
                }else if (res_name.equals("Kasturi Garden")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "kasturi");
                    startActivity(i);
                }else if (res_name.equals("Cilantro")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "cilantro");
                    startActivity(i);
                }else if (res_name.equals("Bread and Beyond")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "bb");
                    startActivity(i);
                }else if (res_name.equals("Burger n' Boost")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "bnb");
                    startActivity(i);
                }else if (res_name.equals("Cafe Hollywood")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "ch");
                    startActivity(i);
                }else if (res_name.equals("Cafe Italiano")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "ci");
                    startActivity(i);
                }else if (res_name.equals("Coffee Rpublic Bangladesh")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "crb");
                    startActivity(i);
                }else if (res_name.equals("Crossroads Lounge")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "cl");
                    startActivity(i);
                }else if (res_name.equals("George's Cafe")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "gc");
                    startActivity(i);
                }else if (res_name.equals("Gloria Jean's")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "gj");
                    startActivity(i);
                }else if (res_name.equals("Moka Cafe and Bistro")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "mcb");
                    startActivity(i);
                }else if (res_name.equals("The Stage")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "ts");
                    startActivity(i);
                }else if (res_name.equals("White Hen Gourmet and Pastry Shop")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "wh");
                    startActivity(i);
                }else if (res_name.equals("FFC")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "ffc");
                    startActivity(i);
                }else if (res_name.equals("KFC Restaurant")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "kfc");
                    startActivity(i);
                }else if (res_name.equals("Nando's")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "nandos");
                    startActivity(i);
                }else if (res_name.equals("Mainland China")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "mainland");
                    startActivity(i);
                }else if (res_name.equals("Black Tent")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "black");
                    startActivity(i);
                }else if (res_name.equals("Bukhara Restaurant")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "bukhara");
                    startActivity(i);
                }else if (res_name.equals("Dhaba")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "dhaba");
                    startActivity(i);
                }else if (res_name.equals("Great Indian Restaurant")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "indian");
                    startActivity(i);
                }else if (res_name.equals("Handi Indian Bistro")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "handi");
                    startActivity(i);
                }else if (res_name.equals("Khana Khazana")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "khana");
                    startActivity(i);
                }else if (res_name.equals("Locknow Restaurant")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "locknow");
                    startActivity(i);
                }else if (res_name.equals("The Mughal Kitchen")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "mughel");
                    startActivity(i);
                }else if (res_name.equals("Nawaab's")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "nawab");
                    startActivity(i);
                }else if (res_name.equals("Santoor")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "santoor");
                    startActivity(i);
                }else if (res_name.equals("Tarka")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "tarka");
                    startActivity(i);
                }else if (res_name.equals("Casa Greek")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "casa");
                    startActivity(i);
                }else if (res_name.equals("Hungry Duck")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "duck");
                    startActivity(i);
                }else if (res_name.equals("Rice n Noodies")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "rice");
                    startActivity(i);
                }else if (res_name.equals("The Chef")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "chef");
                    startActivity(i);
                }else if (res_name.equals("Voot the Restaurant")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "voot");
                    startActivity(i);
                }else if (res_name.equals("Spaghetti Jazz")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "jazz");
                    startActivity(i);
                }else if (res_name.equals("Veni Vidi Vici")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "veni");
                    startActivity(i);
                }else if (res_name.equals("Quesadilla La Mexicana Grill")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "mexicana");
                    startActivity(i);
                }else if (res_name.equals("(Teriyaki) Kobe Japanese Restaurant")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "kobe");
                    startActivity(i);
                }else if (res_name.equals("Korean Restaurant")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "korean");
                    startActivity(i);
                }else if (res_name.equals("Sbarro Bangladesh")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "sbaro");
                    startActivity(i);
                }else if (res_name.equals("Pizza Hut")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "pizza");
                    startActivity(i);
                }else if (res_name.equals("Corner (Fusion) Thai Restaurant")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "corner");
                    startActivity(i);
                }else if (res_name.equals("Red Tomato")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "red");
                    startActivity(i);
                }else if (res_name.equals("Soi 71")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "soi");
                    startActivity(i);
                }else if (res_name.equals("Droom")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "droom");
                    startActivity(i);
                }else if (res_name.equals("King Be")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "king");
                    startActivity(i);
                }else if (res_name.equals("OvenFresh")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "oven");
                    startActivity(i);
                }else if (res_name.equals("Shawarma")) {
                    Intent i = new Intent("com.citylifeezy.citylifeezy.citylifeezy.SingleResMenu");
                    i.putExtra("res", "shawarma");
                    startActivity(i);
                }
            }
        });

    }
}
