package com.hustlestudio.admob;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.admob.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;

import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

     private static final int TIME_INTERVAL=2000;
      private long backpressed;

   private ProgressBar mProgressBar, mProgressBar1;

    private MediaPlayer mediaPlayer;
    int length;

    private TextView countDownText, hour;
    private Button countdownButton;
    private Button Reset_Button;
    private long timeLeftinMiliseconds =1500000; //25minutes
    private boolean timerRunning;
    private CountDownTimer countDownTimer;
    private TextView displayTotal;
    private TextView countTimes;
    int timerCounts = 0;

    private Dialog dialog;

    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        setAds();


        getWindow().setStatusBarColor(getResources().getColor(R.color.purple_500));
        getSupportActionBar().hide();
        // getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actiuon_bar2)));


        countDownText = findViewById(R.id.twentyFive);
        countdownButton = findViewById(R.id.Start);
        countTimes = findViewById(R.id.xCount);
        Reset_Button = findViewById(R.id.ResetButton);
        displayTotal = findViewById(R.id.min);
        hour = findViewById(R.id.hr);


        dialog = new Dialog(this);

        mProgressBar = findViewById(R.id.progressbar_timerview);
        mProgressBar1 = findViewById(R.id.progressbar1_timerview);

        countdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setTimer();
                mProgressBar.setVisibility(View.INVISIBLE);


                startStop();
                mProgressBar1.setVisibility(View.VISIBLE);

            }
        });
        Reset_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        updateTimer();
    }

    private void startStop() {
        if (timerRunning) {
            stoptimer();
        } else {
            starttimer();

        }
    }

    private void starttimer() {


        countDownTimer = new CountDownTimer(timeLeftinMiliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftinMiliseconds = millisUntilFinished;

                mProgressBar1.setProgress((int) (millisUntilFinished));

                updateTimer();
            }

            @Override
            public void onFinish() {
                startaudio();
                OpenDialogue();
                timerCounts++;

                int minutes = 25 * timerCounts;

                if (minutes >= 75) {
                    int hr = minutes / 60;
                    int min = minutes % 60;
                    hour.setText(  " = "+hr + ":" + min+ "hrs");

                }
                countTimes.setText("x" + Integer.toString(timerCounts));


                displayTotal.setText(Integer.toString(minutes) + "minutes");


                countdownButton.setText("Start");
                countDownText.setText("00:00");


                mProgressBar.setVisibility(View.VISIBLE);
                mProgressBar1.setVisibility(View.GONE);

                countdownButton.setVisibility(View.INVISIBLE);
                Reset_Button.setVisibility(View.VISIBLE);


            }
        }.start();


        countdownButton.setText("Pause");


        timerRunning = true;
        Reset_Button.setVisibility(View.INVISIBLE);

    }

    private void stoptimer() {

        countDownTimer.cancel();
        countdownButton.setText("Start");
        timerRunning = false;
        Reset_Button.setVisibility(View.VISIBLE);
    }

    private void resetTimer() {

        timeLeftinMiliseconds = 1500000;
        updateTimer();
        Reset_Button.setVisibility(View.INVISIBLE);
        countdownButton.setVisibility(View.VISIBLE);
        mProgressBar1.setVisibility(View.VISIBLE);

    }

    private void updateTimer() {

        int minutes = (int) timeLeftinMiliseconds / 60000;
        int seconds = (int) timeLeftinMiliseconds % 60000 / 1000;

        String timeLeftText = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);


        countDownText.setText(timeLeftText);


    }


    private void setTimer() {

        mProgressBar1.setMax(1500000);


    }

    private void startaudio() {

        mediaPlayer = MediaPlayer.create(this, R.raw.media);
        mediaPlayer.start();


    }

    private void OpenDialogue() {
        dialog.setContentView(R.layout.rewar2);
       dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView imageView_close = dialog.findViewById(R.id.close_img);

        Button five_brk = dialog.findViewById(R.id.shortbrk);
        Button fiften_break = dialog.findViewById(R.id.long_break);


        five_brk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mInterstitialAd != null) {
                    mInterstitialAd.show(MainActivity.this);

                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            super.onAdDismissedFullScreenContent();
                            Intent intent = new Intent(MainActivity.this, FiveActivity.class);
                            startActivity(intent);
                            dialog.dismiss();
                            mInterstitialAd=null;
                            setAds();

                        }
                    });
                } else {
                    Intent intent = new Intent(MainActivity.this, FiveActivity.class);
                    startActivity(intent);
                    dialog.dismiss();

                }



            }


        });

        fiften_break.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (mInterstitialAd != null) {
                    mInterstitialAd.show(MainActivity.this);

                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            super.onAdDismissedFullScreenContent();
                            Intent intent = new Intent(MainActivity.this, FiveActivity.class);
                            startActivity(intent);
                            dialog.dismiss();
                            mInterstitialAd=null;
                            setAds();

                        }
                    });
                }else{Intent intent4 = new Intent(MainActivity.this, LongActivity.class);
                    startActivity(intent4);
                    dialog.dismiss();
                }


            }
        });


        imageView_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }


        });


        dialog.show();

    }
    private void setAds(){

        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,"ca-app-pub-8041218622900190/8391517060", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i(TAG, loadAdError.getMessage());
                        mInterstitialAd = null;
                    }
                });

    }

    @Override
    public void onBackPressed() {
       if (backpressed+TIME_INTERVAL>System.currentTimeMillis()){

           super.onBackPressed();
           return;
       }
       else {

           Toast.makeText(getBaseContext(), "Press Back again to leave break  ", Toast.LENGTH_SHORT).show();
       }
       backpressed=System.currentTimeMillis();
    }
}
