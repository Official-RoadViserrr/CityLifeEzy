package com.citylifeezy.citylifeezy;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.citylifeezy.citylifeezy.activity.R;

public class SplashActivity extends AppCompatActivity {

    Animation _translateAnimation;
    RelativeLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        _translateAnimation = new TranslateAnimation(TranslateAnimation.ABSOLUTE, 0f, TranslateAnimation.ABSOLUTE, -300f, TranslateAnimation.ABSOLUTE, 0f, TranslateAnimation.ABSOLUTE, 0f);
        _translateAnimation.setDuration(4000);
        _translateAnimation.setRepeatCount(-1);
        _translateAnimation.setRepeatMode(Animation.REVERSE);
        _translateAnimation.setInterpolator(new LinearInterpolator());

        root = (RelativeLayout) findViewById(R.id.global_relative);

        ImageView img = (ImageView) findViewById(R.id.image);
        img.startAnimation(_translateAnimation);
        ImageView imageButton = (ImageView) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, AndroidAnimationActivity_Before.class);
                startActivity(intent);
                finish();
            }
        });

        final Snackbar snackbar = Snackbar.make(root, "You are using beta trail version of CityLifeEzy. Enjoy!", Snackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(getResources().getColor(R.color.snackbar_back));
        TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(getResources().getColor(R.color.snackbar_text));
        /*snackbar.setDuration(Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Dismiss", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        snackbar.setActionTextColor(getResources().getColor(R.color.dismiss_color));*/
        snackbar.show();
    }

}
