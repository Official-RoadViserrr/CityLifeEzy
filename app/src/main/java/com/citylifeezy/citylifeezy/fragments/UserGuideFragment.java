package com.citylifeezy.citylifeezy.fragments;


// This is the Home Fragment //


import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
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


/**
 * Created by tanvirsourov on 5/10/15.
 */
public class UserGuideFragment extends Fragment implements Html.ImageGetter {

    View rootview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootview = inflater.inflate(R.layout.fragment_user_guide, container, false);


        onCreate(savedInstanceState);



        String code =

                "<p>" +
                "CityLifeEzy traffic update service provides the user detailed real time update of the roads with pinpoint accuracy within its coverage. It also keeps the user updated of any unusual incident on the roads. When opened, the service shows the users’ real time position as the -" +
                "<br/><br/>" +
                "<img src = 'center.png' > - icon at the center of the interface with a view of the surrounding areas. The user is able to check the road situations in further details by clicking on the nearest " +
                "<br/><br/>" +
                "<img src = 'trafficpoint.png' >  - Icons. " +
                "For further convenience, these points have the following icons surrounding the points showing incoming traffic flow to respective traffic points: " +
                "<br/><br/>" +
                "<img src = 'green_traffic_indicator.png' ><b><i> - Green Flag: Free Road</i></b>" +
                "<br/>" +
                "<br/>" +
                "<img src = 'blue_traffic_indicator.png' ><b><i> - Blue Flag: Low Traffic</i></b>" +
                "<br/>" +

                "<br/>" +
                "<img src = 'yellow_traffic_indicator.png' ><b><i> - Yellow Flag: Moderate Traffic</i></b>" +
                "<br/>" +

                "<br/>" +
                "<img src = 'pink_traffic_indicator.png' ><b><i> - Pink Flag: Heavy Traffic</i></b>" +
                "<br/>" +

                "<br/>" +
                "<img src = 'red_traffic_indicator.png' ><b><i> - Red Flag: Extreme Traffic</i></b>" +
                "<br/><br/>" +

                "<b>This is to note that all flags refer to the incoming traffic condition to its respective traffic point.</b>"
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



        UserGuideFragment myListFragment = new UserGuideFragment();

        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        fragmentTransaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout, R.anim.fadein, R.anim.fadeout);

        fragmentTransaction.show(myListFragment);

        fragmentTransaction.commit();

    }



    @Override
    public Drawable getDrawable(String arg0) {
        // TODO Auto-generated method stub
        int id = 0;

        if(arg0.equals("trafficpoint.png")){
            id = R.drawable.trafficpoint;
        }

        if(arg0.equals("center.png")){
            id = R.drawable.center;
        }

        if(arg0.equals("red_traffic_indicator.png")){
            id = R.drawable.red_traffic_indicator;
        }

        if(arg0.equals("blue_traffic_indicator.png")){
            id = R.drawable.blue_traffic_indicator;
        }

        if(arg0.equals("green_traffic_indicator.png")){
            id = R.drawable.green_traffic_indicator;
        }

        if(arg0.equals("yellow_traffic_indicator.png")){
            id = R.drawable.yellow_traffic_indicator;
        }

        if(arg0.equals("pink_traffic_indicator.png")){
            id = R.drawable.pink_traffic_indicator;
        }

        LevelListDrawable d = new LevelListDrawable();
        Drawable empty = getResources().getDrawable(id);
        d.addLevel(0, 0, empty);
        d.setBounds(0, 0, empty.getIntrinsicWidth(), empty.getIntrinsicHeight());

        return d;
    }

}
