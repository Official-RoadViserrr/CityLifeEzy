package com.citylifeezy.citylifeezy.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.citylifeezy.citylifeezy.activity.R;
import com.citylifeezy.citylifeezy.models.BeanSampleList;
import com.citylifeezy.citylifeezy.utils.SharedPreference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class FragmentList extends Fragment {

    public static FragmentList newInstance() {
        FragmentList fragment = new FragmentList();
        return fragment;
    }
    public FragmentList() {
        // Required empty public constructor
    }

    Activity activity;
    private ListView postsListView;
    private PostsListAdapter postsListAdapter;
    private ArrayList<BeanSampleList> postsBeanSampleList =new ArrayList<BeanSampleList>();

    SharedPreference sharedPreference;


    ArrayList<String> areaIdList = new ArrayList<String>();
    ArrayList<String> areaLatLngList = new ArrayList<String>();
    ArrayList<String> Area_Stat_Cond_1List = new ArrayList<String>();
    ArrayList<String> Area_Stat_Cond_2List = new ArrayList<String>();
    ArrayList<String> Area_Stat_Cond_3List = new ArrayList<String>();
    ArrayList<String> Area_Stat_Cond_4List = new ArrayList<String>();

    String AreaLatLngArray[];
    String Area_Stat_Cond_1Array[];
    String Area_Stat_Cond_2Array[];
    String Area_Stat_Cond_3Array[];
    String Area_Stat_Cond_4Array[];

    int[] myAreaId;



    public String[] AreaName = {
            "Abdullahpur Bus Stand", "House Building", "Azampur Intersection", "Rajlaxmi Complex",
            "Jasimuddin Intersection", "Airport Intersection", "Bashundhara R/A Entrance",
            "Badda Notun Bazar Intersection", "Badda Link Road Intersection", "Rampura Bridge",
            "Khilgaon Abul Hotel", "Malibagh Rail Gate", "Mouchak Intersection", "Moghbazar Intersection",
            "Bangla Motor Intersection", "Ruposi Bangla Hotel", "Malibagh-Shantinagar Intersection", "Shapla Chattar",
            "Fakirapool Intersection", "Dainik Bangla", "Purana Paltan Intersection", "Nilkhet Intersection",
            "Highcourt Main Gate", "Press Club Gate", "Shahbagh Intersection", "Elephant Road Bata Signal",
            "Science Lab Intersection", "Jigatola Bus Stand", "Shankar Bus Stand", "Lalmatia End", "Asad Gate",
            "Khamarbari Intersection", "Farmgate Center", "Shatrasta Intersection", "Nabisco", "Gulshan 1 Intersection",
            "Gulshan 2 Intersection", "Kamal Ataturk Avenue (Mr. Baker)", "Kakoli Intersection", "Amtoli Intersection",
            "Mohakhali Intersection", "Bijoy Sarani", "Mirpur 10 Intersection", "Mirpur 1 Intersection",
            "Gabtoli Technical Intersection", "Shyamoli Hall", "FDC Intersection", "Mohakhali Rupayan Center",
            "Bijaynagar Intersection", "Khilgaon Railgate Intersection", "Russell Square Intersection",
            "Dhanmondi 27 Persona", "Karwanbazaar Intersection", "Panthapath-Green Road Intersection",
            "Kalabagan Intersection",
            "Jahangir Gate", "Badda Post Office Point", "Khilkhet CNG Station Point", "Kakrail Comet CNG Point",
            "Madani Rd Bridge Point", "Gulshan 2 HSBC Point", "Rasulbagh Point", "Trust Filling Station Point",
            "Gulshan SCB Head Office", "Hazipara CNG Station Point", "Jamuna CNG Station Point", "Merul Filling Station Point",
            "Brac University", "Labaid Dhanmondi", "Kallyanpur Point", "Navana CNG Point", "Mirpur Shahjalal CNG Station Point",
            "Mirpur Aria CNG Station Point", "Nilkhet Kataban Intersection",
            "Dhanmondi 7 Intersection", "Kataban Intersection", "Kakrail Masjid - Officers Club Intersection",
            "Mirpur Thana Intersection", "Agargaon Bus Stand Intersection", "Chankharpul Intersection","Kuril Bus Stand",
            "Gulshan 2 Labaid Point", "Banani Artisan Point", "Westin Point","Gulshan Agora","Link Road Bridge Point",
            "Vasavi Point", "Brac Center", "Titumir College","Sainik Club Point", "Square Hospital", "Green Road Middle Section",
            "Chandrima Uddan Intersection", "Gulshan 2 Sweden Embassy", "Rabindra Sarobar Front", "Dhanmondi 8 Bridge",
            "Gulshan Rd 34 Point", "Gulshan Rd to 34 Banani Bridge Point","Gulshan 35/37 Intersection"
    };


    public int[] Jam_Area_1 = {
            1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 18, 18, 17, 18, 19, 22, 21, 22, 25, 24, 25,
            26, 29, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 43, 42, 43, 44, 52, 39, 20, 11, 30, 28,
            53, 32, 26, 41, 9, 6, 22, 7, 36, 34, 55, 36, 12, 13, 9, 35, 26, 45, 35, 42, 42, 14, 26, 24, 18, 42,
            41, 24, 6, 36, 81, 36, 36, 8, 35, 35, 35, 40, 50, 26, 42, 36, -9, -9, -9, -9, -9
    };

    public int[] Jam_Area_2 = {
            -9, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 24, 12, 16, 19, 20, 23, 26, 23, 20, 22, 26, 27,
            28, 51, 45, 31, 32, 33, 34, 40, 36, 37, 38, 39, 40, 41, 32, 38, 44, 45, 30, 33, 35, 19, 18, 26, 30,
            32, 52, 50, 40, 8, 5, 18, 6, 7, 40, 41, 35, 9, 46, 8, 40, 50, 44, 34, 31, 38, 22, 50, 52, 13, 43, 41,
            21, 57, 82, 37, 84, 35, 35, 40, 40, 40, 38, 53, 53, 41, 6, -9, -9, -9, -9, -9
    };

    public int[] Jam_Area_3 = {
            -9, -9, -9, -9, -9, -9, -9, 36, 35, 35, -9, 49, 16, 46, 52, 13, -9, 19, 16, 48, 48, 25, -9, -9, 15, 21,
            21, -9, 27, 30, 45, 42, 52, 46, 35, 47, 7, -9, 5, 47, 34, 34, 31, -9, -9, 29, 13, -9, -9, -9, 53, -9, 46,
            26, -9, -9, -9, -9, -9, 36, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 17, -9, 26, 22, -9, 42,
            -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 31, -9, -9, -9, -9, -9, -9
    };

    public int[] Jam_Area_4 = {
            -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 48, 25, -9, -9, -9, -9, 17, 22, 27, -9, -9, 23, 52,
            50, -9, -9, -9, -9, -9, 41, -9, -9, 8, 6, -9, -9, -9, -9, 30, 5, -9, -9, -9, 9, -9, -9, -9, -9, -9, 14,
            50, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 21, -9, -9, -9, -9,
            -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 45, -9, -9, -9, -9, -9, -9, -9
    };


    public int[] getFromPrefs(){
        int[] ret;
        SharedPreferences prefs = getActivity().getApplicationContext().getSharedPreferences("AREAID", Context.MODE_PRIVATE);
        int count = prefs.getInt("Count", 0);
        ret = new int[count];
        for (int i = 0; i < count; i++){
            ret[i] = prefs.getInt("IntValue_"+ i, i);
        }
        return ret;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        sharedPreference = new SharedPreference();

        myAreaId = new int[getFromPrefs().length];
        myAreaId = getFromPrefs();

        String temp[];

        for (int i = 0; i< myAreaId.length; i++)
        {
            int dummy = myAreaId[i];

            if (dummy == i)
            {
                postsBeanSampleList.add(new BeanSampleList(i,AreaName[i]));

            }
        }

    }

    Button doneButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);


        postsListView = (ListView) view.findViewById(R.id.posts_list);
        postsListAdapter = new PostsListAdapter(activity, postsBeanSampleList);
        postsListView.setAdapter(postsListAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        postsListAdapter.notifyDataSetChanged();
    }

    public class PostsListAdapter extends BaseAdapter {

        private Context context;
        ArrayList<BeanSampleList> postBeanSampleList;
        SharedPreference sharedPreference;

        public PostsListAdapter(Context context, ArrayList<BeanSampleList> postBeanSampleList) {

            this.context = context;
            this.postBeanSampleList = postBeanSampleList;
            sharedPreference = new SharedPreference();
        }

        private class ViewHolder {
            TextView txtTitle,txtSubTitle;
            ImageView btnFavourite;
        }

        @Override
        public int getCount() {
            return postBeanSampleList.size();
        }

        @Override
        public Object getItem(int position) {
            return postBeanSampleList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_posts_list, parent, false);
                holder = new ViewHolder();
                holder.txtTitle = (TextView) convertView
                        .findViewById(R.id.txtPostTitle);

                holder.btnFavourite = (ImageView) convertView
                        .findViewById(R.id.favouritesToggle);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            BeanSampleList beanSampleList = (BeanSampleList) getItem(position);
            holder.txtTitle.setText(beanSampleList.getArea_Name());


            if (checkFavoriteItem(beanSampleList)) {
                holder.btnFavourite.setImageResource(R.drawable.ic_favorite);
                holder.btnFavourite.setTag("active");
            } else {
                holder.btnFavourite.setImageResource(R.drawable.ic_favorite_outline);
                holder.btnFavourite.setTag("deactive");
            }
            holder.btnFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    String tag = holder.btnFavourite.getTag().toString();
                    if (tag.equalsIgnoreCase("deactive")) {
                        sharedPreference.addFavorite(context, postBeanSampleList.get(position));
                        holder.btnFavourite.setTag("active");
                        holder.btnFavourite.setImageResource(R.drawable.ic_favorite);
                    } else {
                        sharedPreference.removeFavorite(context, postBeanSampleList.get(position));
                        holder.btnFavourite.setTag("deactive");
                        holder.btnFavourite.setImageResource(R.drawable.ic_favorite_outline);
                    }
                }
            });
            return convertView;
        }


        public boolean checkFavoriteItem(BeanSampleList checkProduct) {
            boolean check = false;
            List<BeanSampleList> favorites = sharedPreference.loadFavorites(context);
            if (favorites != null) {
                for (BeanSampleList product : favorites) {
                    if (product.equals(checkProduct)) {
                        check = true;
                        break;
                    }
                }
            }
            return check;
        }
    }
}