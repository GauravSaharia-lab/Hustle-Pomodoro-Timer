package com.hustlestudio.admob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.admob.R;

public class SplashActivity extends AppCompatActivity {
    private static  int LIMIT=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();

        //to make purple in status bar but not in mainactivity
        getWindow().setStatusBarColor(getResources().getColor(R.color.purple_500));
        //loading for 2seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,HowActivity.class);
                startActivity(intent);
                finish();
            }
        },LIMIT);


    }
}