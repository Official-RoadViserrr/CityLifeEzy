package com.citylifeezy.citylifeezy.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.citylifeezy.citylifeezy.callbacks.OnVerificationSuccessListener;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.microsoft.windowsazure.mobileservices.ApiJsonOperationCallback;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;

import java.net.MalformedURLException;

/**
 * Created by Tomas on 7/27/2015.
 */
public class VerifyAsyncTask extends AsyncTask<Void, Void, Void> {

    private Context context;
    private String phone;
    private int pin;
    private OnVerificationSuccessListener callback;

    public VerifyAsyncTask(Context context, String phone, int pin, OnVerificationSuccessListener callback) {
        this.context = context;
        this.phone = phone;
        this.pin = pin;
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(Void... params) {
        final Context ctx = context;
        try {
            MobileServiceClient mobileServiceClient = new MobileServiceClient(
                    "https://roadviserrrok.azure-mobile.net/",
                    "KCoGmJFoxpfxOfkAQvHTUOwNpbplcs63",
                    context
            );

            JsonObject body = new JsonObject();
            body.addProperty("phone", phone);
            body.addProperty("pin", pin);

            mobileServiceClient.invokeApi("verifyphone", body, new ApiJsonOperationCallback() {
                @Override
                public void onCompleted(JsonElement jsonObject, Exception exception, ServiceFilterResponse response) {
                    if (exception == null) {
                        Toast.makeText(ctx, "An SMS with a verification PIN is sent to your phone. Please wait for a minute", Toast.LENGTH_LONG).show();
                        callback.onVerificationSuccess(true);
                    } else {
                        Log.e("Exception: >>", exception.getMessage());
                        Toast.makeText(ctx, "Something went wrong. Please try again later", Toast.LENGTH_LONG).show();
                        exception.printStackTrace();
                        callback.onVerificationSuccess(false);
                    }
                }

            });

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
