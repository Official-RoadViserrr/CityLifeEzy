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
public class SubscriptionsFragment extends Fragment implements Html.ImageGetter {

    View rootview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootview = inflater.inflate(R.layout.fragment_subscriptions, container, false);

        onCreate(savedInstanceState);


        String code =

                "<p>"
                +
                "New subscription and each subsequent renewal to CityLifeEZy have a service validity of 30 days from the day of subscription. You can make a monthly subscription to CityLifeEzy by making a bKash payment of BDT 99. The step by step process is as follows: "
                +
                "<br/><br/>"
                +
                "1. Go to your bKash Menu by dialing *247#"
                +
                "<br/>"
                +
                "2.  In the menu bar select Make Payment option (Number 3) and press send"
                +
                "<br/>"
                +
                "3. In the merchant bKash wallet number field type <b>01990442819</b> and press Send"
                +
                "<br/>"
                +
                "4. In the enter amount field type 99 and press send"
                +
                "<br/>"
                +
                "5. In the enter reference field type your 11 digit phone number and press send"
                +
                "<br/>"
                +
                "6. In the enter counter number field type 1 and press send"
                +
                "<br/>"
                +
                "7. In the enter PIN field type your personal bKash PIN and press send"
                +
                "<br/><br/>"
                +
                "If you do not have a personal bKash wallet, you can make the payment through any personal bKash (non-merchant) wallet in the same process detailed above."
                +
                "<br/><br/>"
                +
                "After making the payment, you can continue enjoying your service within 15 minutes."
                +
                "</p>";


        Spanned spanned = Html.fromHtml(code, this, null);

        TextView newtext = (TextView) rootview.findViewById(R.id.textView2);

        newtext.setText(spanned);

        return rootview;


    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SubscriptionsFragment myListFragment = new SubscriptionsFragment();

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
