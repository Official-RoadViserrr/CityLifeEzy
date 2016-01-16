package com.citylifeezy.citylifeezy.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import com.citylifeezy.citylifeezy.BReceiver;
import com.citylifeezy.citylifeezy.Fragment_Contact_List;
import com.citylifeezy.citylifeezy.Fragment_Map_View;
import com.citylifeezy.citylifeezy.Pending_request;
import com.citylifeezy.citylifeezy.Settings_frag;
import com.citylifeezy.citylifeezy.SlidingTabLayout;
import com.citylifeezy.citylifeezy.activity.R;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

/**
 * Created by Asif on 12/5/2015.
 */
public class Fnf extends android.support.v4.app.Fragment implements MaterialTabListener {

    public static Context mContext;
    private ViewPager viewPager;
    //private SlidingTabLayout mTabs;
    private MaterialTabHost tabHost;
    private static final int Map_View = 0;
    private static final int Contact_list = 1;
    private static final int Pending_Request = 3;
    private static final int Settings_list = 2;
    private View v = null;
    public static String toggleCheck = "StateCheck";
    private SharedPreferences sp;
    private ToggleButton tb;
    private MyPagerAdapter adapter;

    public static Fnf newInstance(Context context) {
        Fnf f = new Fnf();
        Bundle args = new Bundle();
        args.putString("index", context.toString());
        f.setArguments(args);
        mContext = context;
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        /*Fnf myListFragment = new Fnf();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout, R.anim.fadein, R.anim.fadeout);
        fragmentTransaction.show(myListFragment);
        fragmentTransaction.commit();

        i = new Intent(getContext(), BReceiver.class);
        i.putExtra("val", "You have a new request");*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fnf, container, false);
        /*mPager = (ViewPager) v.findViewById(R.id.pager);
        mPager.setAdapter(new MyPagerAdapter(getFragmentManager()));
        mTabs = (SlidingTabLayout) v.findViewById(R.id.tabs);
        mTabs.setDistributeEvenly(true);
        mTabs.setBackgroundColor(getResources().getColor(R.color.color_primary));
        mTabs.setSelectedIndicatorColors(getResources().getColor(R.color.white));
        mPager.setOffscreenPageLimit(3);
        mTabs.setViewPager(mPager);*/

        tabHost = (MaterialTabHost) v.findViewById(R.id.materialTabHost);
        viewPager = (ViewPager) v.findViewById(R.id.pager);
        //viewPager.setOffscreenPageLimit(4);
        adapter = new MyPagerAdapter(getActivity().getSupportFragmentManager());
       // viewPager.setAdapter(new MyPagerAdapter(getFragmentManager()));
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //viewPager.setOffscreenPageLimit(4);
            }

            @Override
            public void onPageSelected(int position) {
                tabHost.setSelectedNavigationItem(position);
                viewPager.setOffscreenPageLimit(3);
                //adapter.notifyDataSetChanged();
                //viewPager.setOffscreenPageLimit(4);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        for(int k =0;k<adapter.getCount();k++){
            tabHost.addTab(
                    tabHost.newTab().setText(adapter.getPageTitle(k)).setTabListener(new MaterialTabListener() {
                        @Override
                        public void onTabSelected(MaterialTab tab) {
                            viewPager.setCurrentItem(tab.getPosition());
                        }

                        @Override
                        public void onTabReselected(MaterialTab tab) {

                        }

                        @Override
                        public void onTabUnselected(MaterialTab tab) {

                        }
                    }));
        }

        return v;
    }

    Intent i;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onTabSelected(MaterialTab tab) {
        viewPager.setCurrentItem(tab.getPosition());
        //adapter.notifyDataSetChanged();
        //viewPager.setOffscreenPageLimit(4);
    }

    @Override
    public void onTabReselected(MaterialTab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }



    private class MyPagerAdapter extends FragmentStatePagerAdapter {

        String[] tabs;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            tabs = getResources().getStringArray(R.array.tabs);
        }

        @Override
        public Fragment getItem(int position) {
            //MyFragment myFragment = MyFragment.getInstance(position);
            Fragment fragment = null;
            switch (position) {
                case Map_View:
                    fragment = Fragment_Map_View.newInstance(mContext);
                    break;
                    //return new Fragment_Map_View();
                case Contact_list:
                    fragment = Fragment_Contact_List.newInstance(mContext);
                    //fragment = Pending_request.newInstance("pen1","pen2");
                    //adapter.notifyDataSetChanged();
                    break;

                    //return new Fragment_Contact_List();
                case Settings_list:
                    fragment = Pending_request.newInstance(mContext);
                    //fragment = Settings_frag.newInstance(mContext);
                    break;
                    //return new Settings_frag();
                case Pending_Request:
                    //fragment = Pending_request.newInstance(mContext);
                    fragment = Settings_frag.newInstance(mContext);
                    //adapter.notifyDataSetChanged();
                    break;
                    //return new Pending_request();

            }
            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
            //return super.getItemPosition(object);
        }
    }

    /*public static class MyFragment extends Fragment {
        private TextView textView;

        public static MyFragment getInstance(int position) {
            MyFragment myFragment = new MyFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            myFragment.setArguments(args);
            return myFragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View layout = inflater.inflate(R.layout.frag, container, false);
            textView = (TextView) layout.findViewById(R.id.position);
            Bundle bundle = getArguments();

            if (bundle != null) {
                textView.setText("The Selected Page is" + bundle.getInt("position"));
            }
            return layout;
        }
    }*/
}
