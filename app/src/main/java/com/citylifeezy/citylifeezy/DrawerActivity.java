package com.citylifeezy.citylifeezy;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.citylifeezy.citylifeezy.activity.R;
import com.citylifeezy.citylifeezy.fragments.AboutUsFragment;
import com.citylifeezy.citylifeezy.fragments.BestRouteFragment;
import com.citylifeezy.citylifeezy.fragments.CngFragment;
import com.citylifeezy.citylifeezy.fragments.FeedbackFragment;
import com.citylifeezy.citylifeezy.fragments.Fnf;
import com.citylifeezy.citylifeezy.fragments.IPhonesFragment;
import com.citylifeezy.citylifeezy.fragments.ReportFragment;
import com.citylifeezy.citylifeezy.fragments.TrafficFragment;
import com.citylifeezy.citylifeezy.fragments.YourPlacesFragment;

public class DrawerActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;
    ImageView ivFacebook, ivPlayStore;

    //Drawer Items
    Button btnNavTrafficUpdate, btnNavYourPlaces, btnNavBestRoute, btnNavFnF, btnNavTopRestaurent,
            btnNavBankAtm, btnNavPolice, btnNavCng, btntNavImportantPhones, btnNavYourProfile, btnNavFeedback,
            btnNavSubscription, btnNavUserGuide, btnNavAbout, btnNavExit;
    ImageView ivLeftNavFacebook, ivLeftGPlus, ivLeftNavTwitter, ivLeftNavShare;

    Context context;
    boolean isFirstTIme = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        context = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.txt_nav_open, R.string.txt_nav_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        setUpNavigationDrawer();

//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        if (navigationView != null) {
//            setupDrawerContent(navigationView);
//        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.content_frame, TrafficFragment.newInstance(context)).commit();
        setActionbarTitle("Traffic Info");


//        ivFacebook = (ImageView) findViewById(R.id.ivFacebook);
//        ivPlayStore = (ImageView) findViewById(R.id.ivRateUs);
//        ivFacebook.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent facebookIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/Ezyurlife"));
//                startActivity(facebookIntent);
//            }
//        });
//        ivPlayStore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent rateUsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.citylifeezy.citylifeezy.citylifeezy"));
//                startActivity(rateUsIntent);
//            }
//        });
    }

    private void setUpNavigationDrawer() {
        btnNavTrafficUpdate = (Button) mDrawerLayout.findViewById(R.id.btnNavTrafficUpdate);
        btnNavYourPlaces = (Button) mDrawerLayout.findViewById(R.id.btnNavYourPlaces);
        btnNavBestRoute = (Button) mDrawerLayout.findViewById(R.id.btnNavBestRoute);
        btnNavFnF = (Button) mDrawerLayout.findViewById(R.id.btnNavFnF);
//        btnNavBankAtm = (Button) mDrawerLayout.findViewById(R.id.btnNavBankAtm);
//        btnNavTopRestaurent = (Button) mDrawerLayout.findViewById(R.id.btnNavTopRestaurent);
//        btnNavPolice = (Button) mDrawerLayout.findViewById(R.id.btnNavPolice);
        btnNavCng = (Button) mDrawerLayout.findViewById(R.id.btnNavCng);
        btntNavImportantPhones = (Button) mDrawerLayout.findViewById(R.id.btntNavImportantPhones);
//        btnNavYourProfile = (Button) mDrawerLayout.findViewById(R.id.btnNavYourProfile);
        btnNavFeedback = (Button) mDrawerLayout.findViewById(R.id.btnNavFeedback);
//        btnNavSubscription = (Button) mDrawerLayout.findViewById(R.id.btnNavSubscription);
        //btnNavUserGuide = (Button) mDrawerLayout.findViewById(R.id.btnNavUserGuide);
        btnNavAbout = (Button) mDrawerLayout.findViewById(R.id.btnNavAbout);
        btnNavExit = (Button) mDrawerLayout.findViewById(R.id.btnNavExit);


        ivLeftNavFacebook = (ImageView) mDrawerLayout.findViewById(R.id.ivLeftNavFacebook);
        ivLeftGPlus = (ImageView) mDrawerLayout.findViewById(R.id.ivLeftGPlus);
        ivLeftNavTwitter = (ImageView) mDrawerLayout.findViewById(R.id.ivLeftNavTwitter);
        ivLeftNavShare = (ImageView) mDrawerLayout.findViewById(R.id.ivLeftNavShare);

        btnNavTrafficUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupDrawerContent(v);
            }
        });
        btnNavYourPlaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupDrawerContent(v);
            }
        });
        btnNavBestRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupDrawerContent(v);
            }
        });
        btnNavFnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupDrawerContent(v);
            }
        });
//        btnNavTopRestaurent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setupDrawerContent(v);
//            }
//        });
//        btnNavBankAtm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setupDrawerContent(v);
//            }
//        });
//        btnNavPolice.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setupDrawerContent(v);
//            }
//        });
        btntNavImportantPhones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupDrawerContent(v);
            }
        });
        btnNavCng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupDrawerContent(v);
            }
        });
//        btnNavYourProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setupDrawerContent(v);
//            }
//        });
        btnNavFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupDrawerContent(v);
            }
        });
//        btnNavSubscription.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setupDrawerContent(v);
//            }
//        });
        /*btnNavUserGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupDrawerContent(v);
            }
        });*/
        btnNavAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupDrawerContent(v);
            }
        });
        btnNavExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupDrawerContent(v);
            }
        });
        ivLeftNavFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/Ezyurlife"));
                startActivity(facebookIntent);
            }
        });
        ivLeftNavShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rateUsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.citylifeezy.citylifeezy.citylifeezy"));
                startActivity(rateUsIntent);
            }
        });
    }

    private void setActionbarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_flag:
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, ReportFragment.newInstance(context)).commit();
                setActionbarTitle("Report Traffic");
                return true;
            default:
                break;
        }
        //noinspection SimplifiableIfStatement

        return false;
    }

    private void setupDrawerContent(View view) {
        Fragment fragment = null;
        String title = "";

        switch (view.getId()) {
            case R.id.btnNavTrafficUpdate:
                mDrawerLayout.closeDrawers();
                title = "Traffic Info";
                Log.e("Trffic Update", "Got the Traffic");
                fragment = TrafficFragment.newInstance(context);
                break;
            case R.id.btnNavYourPlaces:
                mDrawerLayout.closeDrawers();
                title = "Your Frequent Places";
                fragment = YourPlacesFragment.newInstance(context);
//                Toast.makeText(getApplicationContext(), "Your Places", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btnNavBestRoute:
                mDrawerLayout.closeDrawers();
                title = "Best Route Finder";
                fragment = BestRouteFragment.newInstance(context);
//                Toast.makeText(getApplicationContext(), "Best Route", Toast.LENGTH_SHORT).show();
//                return;
                break;
//            case R.id.btnNavTopRestaurent:
//                mDrawerLayout.closeDrawers();
//                title = "Top Restaurnats";
//                fragment = RestaurantFragment.newInstance(context);
//                break;
            case R.id.btnNavFnF:
                mDrawerLayout.closeDrawers();
                title = "Find Friends and Family";
                fragment = Fnf.newInstance(context);
                break;
//            case R.id.btnNavBankAtm:
//                mDrawerLayout.closeDrawers();
//                title = "Bank/ATM Locator";
//              //  fragment = BankFragment.newInstance(context);
//                fragment = BankMapping.newInstance(context);
//                break;
            case R.id.btnNavCng:
                mDrawerLayout.closeDrawers();
                title = "CNG Station Queue Info";
                fragment = CngFragment.newInstance(context);
                break;
//            case R.id.btnNavPolice:
//                mDrawerLayout.closeDrawers();
//                title = "Police Stations";
//                fragment = PoliceStFragment.newInstance(context);
//                break;
            case R.id.btntNavImportantPhones:
                mDrawerLayout.closeDrawers();
                title = "Important Phones";
                fragment = IPhonesFragment.newInstance(context);
                break;
//            case R.id.btnNavYourProfile:
//                mDrawerLayout.closeDrawers();
//                title = "Your Profile";
//                fragment = YourProfileFragment.newInstance(context);
//                break;
            case R.id.btnNavFeedback:
                mDrawerLayout.closeDrawers();
                title = "Your Feedback";
                fragment = FeedbackFragment.newInstance(context);
                break;
//            case R.id.btnNavSubscription:
//                mDrawerLayout.closeDrawers();
//                title = "Subscriptions";
//                fragment = new SubscriptionsFragment();
//                break;
            /*case R.id.btnNavUserGuide:
                mDrawerLayout.closeDrawers();
                title = "User Guide";
                fragment = new UserGuideFragment();
                break;*/
            case R.id.btnNavAbout:
                mDrawerLayout.closeDrawers();
                title = "About CityLifeEzy";
                fragment = new AboutUsFragment();
                break;
            case R.id.btnNavExit:
//                mDrawerLayout.closeDrawers();
                this.finish();
                return;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
//        menuItem.setChecked(true);
        setActionbarTitle(title);
//        setTitle(menuItem.getTitle());
        mDrawerLayout.closeDrawers();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(true)
                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DrawerActivity.this.finish();
                    }
                })
                .setPositiveButton("No", null)
                .show();
    }

}
