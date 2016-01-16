package com.citylifeezy.citylifeezy.fragments;


// This is the Home Fragment //


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.citylifeezy.citylifeezy.activity.R;


/**..
 * Created by tanvirsourov on 5/10/15.
 */
public class AboutUsFragment extends Fragment implements Html.ImageGetter {

    View rootview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootview = inflater.inflate(R.layout.fragment_about_us, container, false);

        onCreate(savedInstanceState);


        String code =

                "<p>"
                        +
                        "CityLifeEzy is the first premium lifestyle mobile solution for urban Bangladesh." +
                        " It has been designed to bring ultimate convenience in the fast pitched modern life in the city." +
                        " With an underlying vision to become the most preferred lifestyle service for urban Bangladesh, " +
                        "CityLifeEzy brings an umbrella of amazing services in a common platform never seen before in e-Bangladesh. " +
                        "Some of our current premium services are:"
                        +
                        "<br/><br/>"
                        +
                        "<b>1. The most detailed and accurate traffic update service for Dhaka city"
                        +
                        "<br/><br/>"
                        +
                        "<b>2. A best route adviser within Dhaka city"
                        +
                        "<br/><br/>"
                        +
                        "<b>3. CNG Station queue information within Dhaka city"
                        +
                        "<br/><br/>"
                        +
                        "<b>4. Friends & Family finding anywhere (subject to GPS and data connectivity)"
                        +
                        "<br/><br/>"
                        +
                        "<b>5. More exciting features soon to be launched"
                        +
                        "<br/><br/>"
                        +
                        "<br/><br/>"
                        +
                        "<b>Start using CityLifeEy. Start redefining convenience now!</b>"
                        +
                        "</p>";


        Spanned spanned = Html.fromHtml(code, this, null);

        TextView newtext = (TextView) rootview.findViewById(R.id.tvAbout);

        newtext.setText(spanned);

        return rootview;


    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        AboutUsFragment myListFragment = new AboutUsFragment();

        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        fragmentTransaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout, R.anim.fadein, R.anim.fadeout);

        fragmentTransaction.show(myListFragment);

        fragmentTransaction.commit();

    }

    @Override
    public Drawable getDrawable(String source) {
        return null;
    }
}
