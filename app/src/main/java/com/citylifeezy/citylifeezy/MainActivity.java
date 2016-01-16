package com.citylifeezy.citylifeezy;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.citylifeezy.citylifeezy.activity.R;
import com.citylifeezy.citylifeezy.fragments.AboutUsFragment;
import com.citylifeezy.citylifeezy.fragments.BestRouteFragment;
import com.citylifeezy.citylifeezy.fragments.CngFragment;
import com.citylifeezy.citylifeezy.fragments.FeedbackFragment;
import com.citylifeezy.citylifeezy.fragments.Fnf;
import com.citylifeezy.citylifeezy.fragments.IPhonesFragment;
import com.citylifeezy.citylifeezy.fragments.NavigationDrawerFragment;
import com.citylifeezy.citylifeezy.fragments.ReportFragment;
import com.citylifeezy.citylifeezy.fragments.UserGuideFragment;
import com.citylifeezy.citylifeezy.fragments.YourPlacesFragment;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;



    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();


        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));



    }

    public int menuValue = 0;

    @Override
    public void onNavigationDrawerItemSelected(int position) {

        Fragment objFragment = null;

        switch (position)
        {
            // OK
            case 0:
                menuValue = 0;
                objFragment = new IPhonesFragment();
                IPhonesFragment.newInstance(this);
//                objFragment = new TrafficFragment(); // Home
//                TrafficFragment.newInstance(this);
                break;

            // OK
            case 1:
                menuValue = 1;
                objFragment = new YourPlacesFragment(); // Your Places
                YourPlacesFragment.newInstance(this);
//                Toast.makeText(getApplicationContext(), "Your place is coming soon!", Toast.LENGTH_LONG).show();
                break;

            // OK
            case 2:
                menuValue = 2;
                objFragment = new BestRouteFragment();
                BestRouteFragment.newInstance(this);
//                Toast.makeText(getApplicationContext(), "Best Route is coming soon!", Toast.LENGTH_LONG).show(); // Best Route
                break;

            // OK
            case 3:
                menuValue = 3;
                objFragment = new Fnf(); // Report Traffic
                Fnf.newInstance(this);
                break;

            // OK
//            case 4:
//                menuValue = 4;
//                objFragment = new RestaurantFragment();
//                RestaurantFragment.newInstance(this);
//                //Toast.makeText(getApplicationContext(), "Restaurant Finder is coming soon!", Toast.LENGTH_LONG).show(); // Restaurant Finder
//                break;
//
//            // OK
//            case 5:
//                menuValue = 5;
//                objFragment = new BankFragment();
//                BankFragment.newInstance(this);
//                //Toast.makeText(getApplicationContext(), "Bank/ATM Locator is coming soon!", Toast.LENGTH_LONG).show(); // Bank/ATM Locator
//                break;


//            case 6:
//                menuValue = 6;
//                objFragment = new PoliceStFragment();
//                PoliceStFragment.newInstance(this);
                //Toast.makeText(getApplicationContext(), "Bank/ATM Locator is coming soon!", Toast.LENGTH_LONG).show(); // Bank/ATM Locator
//                break;


            // OK
            case 7:
                menuValue = 7;
                //objFragment = null;
                objFragment = new CngFragment();
                CngFragment.newInstance(this);
                //Toast.makeText(getApplicationContext(), "CNG Station are back in action", Toast.LENGTH_LONG).show(); // Emergency Phones
                break;


//            case 8:
//                menuValue = 8;
//                objFragment = new IPhonesFragment();
//                IPhonesFragment.newInstance(this);
//                //Toast.makeText(getApplicationContext(), "Emergency Phones are coming soon!", Toast.LENGTH_LONG).show(); // Emergency Phones
//                break;

            // OK
//            case 9:
//                menuValue = 9;
//                objFragment = new YourProfileFragment(); // Your Profile
//                YourProfileFragment.newInstance(this);
//                break;

            // OK
            case 10:
                menuValue = 10;
                objFragment = new FeedbackFragment(); // Feedback
                FeedbackFragment.newInstance(this);
                break;

            // OK
//            case 11:
//                menuValue = 11;
//                objFragment = new SubscriptionsFragment(); // Subscription
//                break;

            // OK
            case 12:
                menuValue = 12;
                objFragment = new UserGuideFragment(); // User Guide
                break;

            // OK
            case 13:
                menuValue = 13;
                objFragment = new AboutUsFragment(); // About
                break;

            // OK
            case 14:
                menuValue = 14;
                System.exit(0); // Exit
                break;
        }

        if (menuValue != 2 && menuValue != 1 ) {
            // update the main content by replacing fragments
            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction()
                    .replace(R.id.container, objFragment)
                    .commit();
        }
    }

    public void onSectionAttached(int number) {
        String[] stringArray = getResources().getStringArray(R.array.section_titles);
        if (number >= 1) {
            mTitle = stringArray[number - 1];
        }

    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.

            if (menuValue == 0)
            {
                getMenuInflater().inflate(R.menu.search, menu);
            }

            else
            {
                getMenuInflater().inflate(R.menu.main, menu);
            }
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }


    ArrayAdapter adapter;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id)
        {
            case R.id.facebook:
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/Ezyurlife"));
                startActivity(facebookIntent);
                break;
            case R.id.rateUs:
                Intent rateUsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.citylifeezy.citylifeezy.citylifeezy"));
                startActivity(rateUsIntent);
                break;
            case R.id.report_action:

                Fragment objFragment = null;

                menuValue = 1;
                objFragment = new ReportFragment();
                ReportFragment.newInstance(this);

                FragmentManager fragmentManager = getSupportFragmentManager();

                fragmentManager.beginTransaction()
                        .replace(R.id.container, objFragment)
                        .commit();

                break;

        }

        return super.onOptionsItemSelected(item);
    }



    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);


            return rootView;
        }





        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(true)
                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setPositiveButton("No", null)
                .show();
    }


}
