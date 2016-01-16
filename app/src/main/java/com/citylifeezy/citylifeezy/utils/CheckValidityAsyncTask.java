package com.citylifeezy.citylifeezy.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.citylifeezy.citylifeezy.callbacks.OnValidityDateArrivedListener;
import com.citylifeezy.citylifeezy.models.User;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import org.joda.time.DateTime;

import java.net.MalformedURLException;
import java.util.concurrent.ExecutionException;

/**
 * Created by Tomas on 7/28/2015.
 */

public class CheckValidityAsyncTask extends AsyncTask<Void, Void, DateTime> {
    private Context context;
    private String phone;
    private OnValidityDateArrivedListener callback;

    public CheckValidityAsyncTask(Context context, OnValidityDateArrivedListener callback, String phone) {
        this.context = context;
        this.callback = callback;
        this.phone = phone;
    }

    @Override
    protected DateTime doInBackground(Void... params) {

        DateTime validityDate = null;
        try {
            MobileServiceClient mobileServiceClient = new MobileServiceClient(
                    "https://roadviserrrok.azure-mobile.net/",
                    "KCoGmJFoxpfxOfkAQvHTUOwNpbplcs63",
                    context);
            MobileServiceTable<User> userTable = mobileServiceClient.getTable(User.class);
            MobileServiceList<User> users = userTable.where().field("phone").eq(phone).execute().get();

            if(users.size()==1){
                User user = users.get(0);
                validityDate = user.getValidityDate();
            }else{
                Log.e("CheckValidity - XXXXX", "users returnd more than one row. " +
                        "There must be unique phone numbers in the table user");
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return validityDate;
    }

    @Override
    protected void onPostExecute(DateTime dateTime) {
        callback.onValidityDateArrived(dateTime);
    }
}
