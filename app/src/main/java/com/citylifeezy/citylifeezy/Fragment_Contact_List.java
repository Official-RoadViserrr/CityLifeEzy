package com.citylifeezy.citylifeezy;

/**
 * Created by Asif on 12/5/2015.
 */

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.VoiceInteractor;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.citylifeezy.citylifeezy.activity.R;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Contact_List#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Contact_List extends android.support.v4.app.Fragment implements SwipeRefreshLayout.OnRefreshListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ListView lv;
    private Cursor cursor;
    public static String TAG = "Con";
    private ArrayList<String> contacts_list = new ArrayList<String>();
    private ArrayList<String> number_list = new ArrayList<String>();
    private ArrayList<Integer> final_state = new ArrayList<Integer>();
    private ArrayList<String> split_number = new ArrayList<String>();
    private ArrayList<Integer> get_user_req_1;
    private ArrayList<String> check_user_req_1;
    private ArrayList<String> checking_receiver_name;
    private ArrayList<String> check_user_phone;
    private String phnNumber;
    private String contactName;
    private JSONArray friend_req;
    private JSONArray get_req_1, check_req;
    private ArrayList<String> f_req;
    private ArrayAdapter<Contact_custom> adapter;


    private String retriveUserName, retriveNumber;
    private SharedPreferences sp;
    private RelativeLayout root;
    private List<Contact_custom> Important_contact = new ArrayList<Contact_custom>();
    private Contact_custom phn_num;
    private TextView tvError;
    private static Context mContext;
    private SwipeRefreshLayout swipeContact;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * <p/>
     * //     * @param param1 Parameter 1.
     * //     * @param param2 Parameter 2.
     *
     * @return A new instance of fragment Fragment_Contact_List.
     */
    // TODO: Rename and change types and number of parameters
    /*public static Fragment_Contact_List newInstance(String param1, String param2) {
        Fragment_Contact_List fragment = new Fragment_Contact_List();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/
    public static Fragment_Contact_List newInstance(Context context) {
        Fragment_Contact_List fragment = new Fragment_Contact_List();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, context.toString());
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        mContext = context;
        return fragment;
    }

    public Fragment_Contact_List() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
        getUserName(getContext());
        getDefaults(getContext());
        //friend_req_data();
        get_request_data_1();
        checking_request();
        friend_req_data_fetch();

        ContentResolver resolver = getContext().getContentResolver();
        cursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        while (cursor.moveToNext()) {
            contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            phnNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            contacts_list.add(contactName);
            number_list.add(phnNumber);
            Log.d(TAG, "phn is: " + number_list);
        }
        Log.d(TAG, "Contact is: " + contacts_list);
        cursor.close();

        /*Fragment_Contact_List myListFragment = new Fragment_Contact_List();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        //fragmentTransaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout, R.anim.fadein, R.anim.fadeout);
        fragmentTransaction.show(myListFragment);
        fragmentTransaction.commit();*/

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment__contact__list, container, false);
        //lv = (ListView) v.findViewById(R.id.listContact);
        root = (RelativeLayout) v.findViewById(R.id.root);
        tvError = (TextView) v.findViewById(R.id.tvConError);
        swipeContact = (SwipeRefreshLayout) v.findViewById(R.id.swipeContact);
        swipeContact.setOnRefreshListener(this);
        sp = PreferenceManager.getDefaultSharedPreferences(getContext());
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    int i;

    private void friend_req_data() {
        String reg_url = "http://roadviserrr.net/citylifeezy1/get_friend_req.php";
        f_req = new ArrayList<String>();
        adapter = new MyListAdapter();
        RequestQueue queue = Volley.newRequestQueue(mContext);
        StringRequest putRequest = new StringRequest(Request.Method.GET, reg_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response From Server:" + response);
                if (response != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(response);
                        friend_req = jsonObj.getJSONArray("friend_request");
                        Log.d("TAG", "CNG UP:" + friend_req);

                        for (i = 0; i < friend_req.length(); i++) {
                            JSONObject c = friend_req.getJSONObject(i);
                            Log.d(TAG, "JSON Object: " + c);
                            String phone = c.getString("phone");
                            Log.d(TAG, "area: " + phone);

                            f_req.add(phone);
                            Log.d(TAG, "Friend Req Num: " + f_req);


                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    for (int j = 0; j < f_req.size(); j++) {
                        String sp_num = f_req.get(j);
                        String new_num = sp_num.substring(3);
                        split_number.add(new_num);
                        Log.d(TAG, "No User " + split_number);
                        for (int k = 0; k < number_list.size(); k++) {
                            if ((f_req.get(j).equals(number_list.get(k))) || (split_number.get(j).equals(number_list.get(k)))) {
                                //final_number.add(contacts_list.get(k) + "\n" + f_req.get(j));
                                Important_contact.add(new Contact_custom(contacts_list.get(k), f_req.get(j)));
                                adapter.notifyDataSetChanged();
                                //adapter.notifyDataSetChanged();
                                /*Log.d(TAG, "New Number is: " + final_number);
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.text, final_number);
                                lv.setAdapter(adapter);*/
                            } else {
                                Log.d(TAG, "No User Found");
                            }
                        }
                    }
                    //adapter.notifyDataSetChanged();
                }
                populateListView();
                swipeContact.setRefreshing(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvError.setVisibility(View.VISIBLE);
                if (error instanceof NetworkError) {
                    tvError.setText("Something Went Wrong. Please Check Your Network Connection");
                }
                swipeContact.setRefreshing(false);
            }
        });
        queue.add(putRequest);
        //populateListView();
        adapter.notifyDataSetChanged();
    }

    private void get_request_data_1() {
        String req_url_1 = "http://roadviserrr.net/citylifeezy1/get_user_request.php";
        get_user_req_1 = new ArrayList<Integer>();
        check_user_req_1 = new ArrayList<String>();
        //adapter = new MyListAdapter();
        RequestQueue queue = Volley.newRequestQueue(mContext);
        StringRequest putRequest = new StringRequest(Request.Method.GET, req_url_1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response From Server:" + response);
                if (response != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(response);


                        get_req_1 = jsonObj.getJSONArray("request_process");
                        Log.d("TAG", "Getting request:" + get_req_1);

                        for (int i = 0; i < get_req_1.length(); i++) {
                            JSONObject c = get_req_1.getJSONObject(i);
                            Log.d(TAG, "JSON Object: " + c);

                            String button_state_changed = c.getString("button_state");
                            Log.d(TAG, "sender: " + button_state_changed);
                            String receiver = c.getString("receiver");
                            Log.d(TAG, "receiver: " + receiver);

                            get_user_req_1.add(Integer.valueOf(button_state_changed));
                            Log.d(TAG, "state: " + get_user_req_1);

                            check_user_req_1.add(receiver);
                            Log.d(TAG, "Friend Receiver Num: " + check_user_req_1);
                            //}
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(putRequest);
    }

    private void checking_request() {
        String req_url_2 = "http://roadviserrr.net/citylifeezy1/cheking_friend.php";
        checking_receiver_name = new ArrayList<String>();
        check_user_phone = new ArrayList<String>();
        RequestQueue queue = Volley.newRequestQueue(mContext);
        StringRequest putRequest = new StringRequest(Request.Method.GET, req_url_2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response From Server:" + response);
                if (response != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(response);
                        check_req = jsonObj.getJSONArray("already_friend");
                        Log.d("TAG", "Getting request:" + check_req);

                        for (int i = 0; i < check_req.length(); i++) {
                            JSONObject c = check_req.getJSONObject(i);
                            Log.d(TAG, "Req: " + c);

                            String phone = c.getString("sender_number");
                            String u_req_checking = c.getString("receiver");

                            check_user_phone.add(phone);
                            Log.d(TAG, "req_num: " + check_user_phone);

                            checking_receiver_name.add(u_req_checking);
                            Log.d(TAG, "req_state: " + checking_receiver_name);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(putRequest);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public String getUserName(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        retriveUserName = preferences.getString("username", null);
        Log.d(TAG, "Fetching user is: " + retriveUserName);
        return retriveUserName;
    }

    public String getDefaults(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        retriveNumber = preferences.getString("phoneNumb", null);
        Log.d(TAG, "Fetching number is: " + retriveNumber);
        return retriveNumber;
    }

    public void populateListView() {
        //adapter = new MyListAdapter();
        lv = (ListView) getActivity().findViewById(R.id.listContact);
        lv.setAdapter(adapter);
        //lv.notifyAll();
        //adapter.notifyDataSetChanged();
    }

    Button bReq, bFriend;
    TextView phn1;
    String numbers, names;
    String numb, friend;

    @Override
    public void onRefresh() {
        adapter.clear();
        adapter.notifyDataSetChanged();
        checking_request();
        //get_request_data_1();
        new loadStuff_1().execute();
        swipeContact.setColorSchemeResources(R.color.color_primary);
    }

    public class MyListAdapter extends ArrayAdapter<Contact_custom> {

        //ViewHolderItem viewHolder;

        public MyListAdapter() {
            super(getActivity(), R.layout.custom_contact, Important_contact);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View itemView = convertView;
            if (itemView == null) {
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                itemView = inflater.inflate(R.layout.custom_contact, parent, false);

                bReq = (Button) itemView.findViewById(R.id.bRequest);
                bFriend = (Button) itemView.findViewById(R.id.bFriend);

            }

            Contact_custom phone_number = Important_contact.get(position);

            TextView name = (TextView) itemView.findViewById(R.id.contact_name);
            name.setText(phone_number.getName());

            phn1 = (TextView) itemView.findViewById(R.id.phone_number);
            phn1.setText(phone_number.getPhone());

            final View finalItemView = itemView;

            for (int g = 0; g < check_user_req_1.size(); g++) {
                numb = (String) ((TextView) finalItemView.findViewById(R.id.phone_number)).getText();
                if (check_user_req_1.get(g).equals(numb)) {
                    Log.d(TAG, "Condition fulfill 2");
                    if (get_user_req_1.get(g).equals(1)) {
                        Log.d(TAG, "Condition fulfill 3");
                        //Button bReq = (Button) finalItemView;
                        adapter.notifyDataSetChanged();
                        bReq.setText("Cancel Request");
                    }
                }
            }


            for (int gs = 0; gs < check_user_phone.size(); gs++) {
                friend = (String) ((TextView) finalItemView.findViewById(R.id.phone_number)).getText();
                //if (check_user_phone.get(gs).equals(friend)) {
                    for (int k = 0; k < checking_receiver_name.size(); k++) {
                        if (checking_receiver_name.get(k).equals(retriveUserName) && check_user_phone.get(k).equals(friend)) {

                            //Log.d(TAG, "Condition fulfill 2");
                            //Log.d(TAG, "Condition fulfill 3");
                            adapter.notifyDataSetChanged();
                            bReq.setVisibility(View.GONE);
                            bFriend.setVisibility(View.VISIBLE);

                        }
                    //}
                }
            }

            bReq.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    names = (String) ((TextView) finalItemView.findViewById(R.id.contact_name)).getText();
                    numbers = (String) ((TextView) finalItemView.findViewById(R.id.phone_number)).getText();

                    Button bReq = (Button) v;
                    if (bReq.getText().toString().equals("Send Request")) {
                        request_process();
                        showConfirmation();
                        bReq.setText("Cancel Request");
                        adapter.clear();
                        adapter.notifyDataSetChanged();
                        //friend_req_data();
                        new loadStuff().execute();

                    } else if (bReq.getText().toString().equals("Cancel Request")) {
                        delete_process();
                        delete_req();
                        bReq.setText("Send Request");
                        adapter.clear();
                        adapter.notifyDataSetChanged();
                        //friend_req_data();
                        new loadStuff().execute();
                    }
                }
            });
            return itemView;
        }

    }

    private void delete_req() {
        final Snackbar snackbar = Snackbar.make(root, "Location Access Request Cancel For " + names, Snackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(getResources().getColor(R.color.snackbar_back));
        TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(getResources().getColor(R.color.snackbar_text));
        snackbar.show();
    }

    private void showConfirmation() {
        final Snackbar snackbar = Snackbar.make(root, "Location Access Request Successfully Send To " + names, Snackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(getResources().getColor(R.color.snackbar_back));
        TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(getResources().getColor(R.color.snackbar_text));
        snackbar.show();
    }

    private void requestAUser() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setMessage("Do you want to send request to this user?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                request_process();
                            }
                        });
        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    private void request_process() {
        String req_url = "http://roadviserrr.net/roadviserrr/request_process.php";
        final int button_state = 1;
        RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest putRequest = new StringRequest(Request.Method.POST, req_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("sender", retriveUserName);
                params.put("sender_number", retriveNumber);
                params.put("receiver", numbers);
                params.put("button_state", String.valueOf(button_state));
                return params;
            }
        };
        queue.add(putRequest);
    }

    private void delete_process() {
        String req_url = "http://roadviserrr.net/roadviserrr/delete_process.php";
        RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest putRequest = new StringRequest(Request.Method.POST, req_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("sender", retriveUserName);
                params.put("receiver", numbers);
                return params;
            }
        };
        queue.add(putRequest);
    }

    public class loadStuff extends AsyncTask<Void, Integer, Void> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(getContext());
            dialog.setProgress(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("Loading...");
            dialog.setMax(100);
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            for (int i = 0; i < 50; i++) {
                publishProgress(5);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            dialog.incrementProgressBy(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            dialog.dismiss();
            get_request_data_1();
            friend_req_data();
            /*adapter.clear();
            adapter.notifyDataSetChanged();*/

        }
    }

    public class loadStuff_1 extends AsyncTask<Void, Integer, Void> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            /*dialog = new ProgressDialog(getContext());
            dialog.setProgress(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("Loading...");
            dialog.setMax(100);
            dialog.show();*/
        }

        @Override
        protected Void doInBackground(Void... params) {
            /*for (int i = 0; i < 50; i++) {
                publishProgress(5);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }*/
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            //dialog.incrementProgressBy(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //dialog.dismiss();
            get_request_data_1();
            friend_req_data_fetch();
            //friend_req_data();
            /*adapter.clear();
            adapter.notifyDataSetChanged();*/

        }
    }

    private void friend_req_data_fetch() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 10000ms
                friend_req_data();
            }
        }, 3000);
    }
}
