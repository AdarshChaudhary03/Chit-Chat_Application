package com.example.dharamvirsingh.bluetoothproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    ImageView iv1;
    TextView tv1;
    private static final int TIME = 4*1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //no title
        //requestWindowFeature(Window.FEATURE_NO_TITLE);




        iv1 = (ImageView)findViewById(R.id.iv1);
        tv1 = (TextView)findViewById(R.id.tv1);
        TranslateAnimation animation = new TranslateAnimation(0,0,0,300);
        animation.setDuration(5000);
        animation.setFillAfter(true);
        iv1.startAnimation(animation);
        tv1.startAnimation(animation);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, Login_Page.class);
                startActivity(i);

                SplashActivity.this.finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        },TIME);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },TIME);

    }

    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }
}
