package com.citylifeezy.citylifeezy;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.citylifeezy.citylifeezy.activity.R;
import com.citylifeezy.citylifeezy.fragments.Fnf;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Pending_request#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Pending_request extends android.support.v4.app.Fragment implements SwipeRefreshLayout.OnRefreshListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static String TAG = "Con";
    private JSONArray get_req;
    private ArrayList<String> get_user_req;
    private ArrayList<String> check_user_req;
    private ArrayList<String> get_user_req_number;
    private ListView lv;
    private ArrayAdapter<Request_contact> adapter;
    private TextView tvError;
    private static Context mContext;
    private RelativeLayout pending;
    private SwipeRefreshLayout swipePendingRequest;
    private String retriveUserName;


    public Pending_request() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * <p/>
     * /** @param param1 Parameter 1.
     * //@param param2 Parameter 2.
     *
     * @return A new instance of fragment Pending_request.
     */
    // TODO: Rename and change types and number of parameters
    /*public static Pending_request newInstance(String param1,String param2) {
        Pending_request fragment = new Pending_request();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/
    public static Pending_request newInstance(Context context) {
        Pending_request fragment = new Pending_request();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, context.toString());
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        mContext = context;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
        getDefaults(getContext());
        getUserName(getContext());
        get_request_data();
        /*Pending_request myListFragment = new Pending_request();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        //fragmentTransaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout, R.anim.fadein, R.anim.fadeout);
        fragmentTransaction.show(myListFragment);
        fragmentTransaction.commit();*/
        //adapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pending_request, container, false);
        pending = (RelativeLayout) v.findViewById(R.id.pending);
        tvError = (TextView) v.findViewById(R.id.tvPenError);
        swipePendingRequest = (SwipeRefreshLayout) v.findViewById(R.id.swipePendingRequest);
        swipePendingRequest.setOnRefreshListener(this);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void get_request_data() {
        //swipePendingRequest.setRefreshing(true);
        String req_url = "http://roadviserrr.net/citylifeezy1/get_user_request.php";
        get_user_req = new ArrayList<String>();
        get_user_req_number = new ArrayList<String>();
        check_user_req = new ArrayList<String>();
        adapter = new MyListAdapter();
        RequestQueue queue = Volley.newRequestQueue(mContext);
        StringRequest putRequest = new StringRequest(Request.Method.GET, req_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response From Server:" + response);
                if (response != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(response);


                        get_req = jsonObj.getJSONArray("request_process");
                        Log.d("TAG", "Getting request:" + get_req);

                        for (int i = 0; i < get_req.length(); i++) {
                            JSONObject c = get_req.getJSONObject(i);
                            Log.d(TAG, "JSON Object: " + c);

                            /*if(c == null){
                                tvError.setVisibility(View.VISIBLE);
                                tvError.setText("You have no pending requests");
                            }*/
                            //else {
                            String sender = c.getString("sender");
                            Log.d(TAG, "sender: " + sender);
                            String sender_number = c.getString("sender_number");
                            String receiver = c.getString("receiver");
                            Log.d(TAG, "receiver: " + receiver);

                            get_user_req.add(sender);
                            Log.d(TAG, "Friend Req Num: " + get_user_req);

                            get_user_req_number.add(sender_number);

                            check_user_req.add(receiver);
                            Log.d(TAG, "Friend Receiver Num: " + check_user_req);
                            //}
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    for (int j = 0; j < check_user_req.size(); j++) {
                        if (check_user_req.get(j).equals(retriveNumber)) {
                            request_contact.add(new Request_contact(get_user_req.get(j),get_user_req_number.get(j)));
                            Log.d(TAG, "No User Found");
                            adapter.notifyDataSetChanged();

                        } else {
                            Log.d(TAG, "No User Found");
                        }
                    }
                    populateListView();
                    swipePendingRequest.setRefreshing(false);
                    //Toast.makeText(getActivity(),"Data is loaded successfully",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                tvError.setVisibility(View.VISIBLE);
                if (error instanceof NetworkError) {
                    tvError.setText("Something Went Wrong. Please Check Your Network Connection");
                }
                swipePendingRequest.setRefreshing(false);
                //Toast.makeText(getActivity(),"Failed",Toast.LENGTH_LONG).show();
            }
        });
        queue.add(putRequest);
        //populateListView();
        adapter.notifyDataSetChanged();
    }

    public String getUserName(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        retriveUserName = preferences.getString("username", null);
        Log.d(TAG, "Fetching user is: " + retriveUserName);
        return retriveUserName;
    }

    String retriveNumber;
    Intent i;

    public String getDefaults(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        retriveNumber = preferences.getString("phoneNumb", null);
        Log.d(TAG, "Fetching number is: " + retriveNumber);
        return retriveNumber;
    }

    private void populateListView() {
        //adapter = new MyListAdapter();
        lv = (ListView) getActivity().findViewById(R.id.listPending);
        lv.setAdapter(adapter);

        //lv.notifyAll();
        //adapter.notifyDataSetChanged();
    }

    Button bAccept, bCancel;
    TextView phn1;
    String username,user_number;
    boolean isClicked = true;
    public List<Request_contact> request_contact = new ArrayList<Request_contact>();

    @Override
    public void onRefresh() {
        adapter.clear();
        adapter.notifyDataSetChanged();
        get_request_data();
        swipePendingRequest.setColorSchemeResources(R.color.color_primary);
    }

    private class MyListAdapter extends ArrayAdapter<Request_contact> {
        public MyListAdapter() {
            super(getActivity(), R.layout.req_pro, request_contact);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View itemView = convertView;
            if (itemView == null) {
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                itemView = inflater.inflate(R.layout.request_contact, parent, false);

                bAccept = (Button) itemView.findViewById(R.id.bRequest);
                bCancel = (Button) itemView.findViewById(R.id.bCancel);

                //bAccept.setTag(position);
                //bCancel.setTag(position);
            }

            /*sp = PreferenceManager.getDefaultSharedPreferences(getContext());*/

            final Request_contact phone_number = request_contact.get(position);

            TextView name = (TextView) itemView.findViewById(R.id.contact_name);
            name.setText(phone_number.getName());

            phn1 = (TextView) itemView.findViewById(R.id.phone_number);
            phn1.setText(phone_number.getPhone());

            final View finalItemView = itemView;

            bAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    username = (String) ((TextView) finalItemView.findViewById(R.id.contact_name)).getText();
                    user_number = (String) ((TextView) finalItemView.findViewById(R.id.phone_number)).getText();
                    accept_user();
                    reverse_accept_user();
                    accept_or_request_process();
                    adapter.remove(phone_number);
                    adapter.clear();
                    adapter.notifyDataSetChanged();
                    showConfirmation();
                    new loadStuff().execute();
                }
            });

            //done
            bCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    username = (String) ((TextView) finalItemView.findViewById(R.id.contact_name)).getText();
                    accept_or_request_process();
                    adapter.remove(phone_number);
                    adapter.clear();
                    adapter.notifyDataSetChanged();
                    delete_req();
                    new loadStuff().execute();
                }
            });
            return itemView;
        }
    }

    private void accept_or_request_process() {
        String req_url = "http://roadviserrr.net/roadviserrr/accept_or_reject.php";
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
                params.put("sender", username);
                params.put("receiver", retriveNumber);
                return params;
            }
        };
        queue.add(putRequest);
    }

    /*private void accept_user() {
        String req_url = "http://roadviserrr.net/roadviserrr/accept_user.php";
        final int req_checking =1;
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
                params.put("username", username);
                params.put("req_checking", String.valueOf(req_checking));
                return params;
            }
        };
        queue.add(putRequest);
    }*/

    private void accept_user() {
        String req_url = "http://roadviserrr.net/roadviserrr/already_friend.php";
        //final int req_checking =1;
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
                params.put("sender", username);
                params.put("sender_number", user_number);
                params.put("receiver", retriveUserName);
                return params;
            }
        };
        queue.add(putRequest);
    }

    private void reverse_accept_user() {
        String req_url = "http://roadviserrr.net/roadviserrr/already_friend.php";
        //final int req_checking =1;
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
                params.put("receiver", username);
                return params;
            }
        };
        queue.add(putRequest);
    }

    private void receiver_user() {
        String req_url = "http://roadviserrr.net/roadviserrr/receiver_user.php";
        final int req_checking = 1;
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
                params.put("username", retriveUserName);
                params.put("req_checking", String.valueOf(req_checking));
                return params;
            }
        };
        queue.add(putRequest);
    }

    private void delete_req() {
        final Snackbar snackbar = Snackbar.make(pending, username + " can not get your location", Snackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(getResources().getColor(R.color.snackbar_back));
        TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(getResources().getColor(R.color.snackbar_text));
        snackbar.show();
    }

    private void showConfirmation() {
        final Snackbar snackbar = Snackbar.make(pending, "Your location is visible to " + username, Snackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(getResources().getColor(R.color.snackbar_back));
        TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(getResources().getColor(R.color.snackbar_text));
        snackbar.show();
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
            get_request_data();
            //getLatestUserLoaction();
            //Toast.makeText(Main.this,"Load Successfully",Toast.LENGTH_SHORT).show();
        }
    }
}
