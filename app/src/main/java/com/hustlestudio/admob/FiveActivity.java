package com.hustlestudio.admob;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.admob.R;

import java.util.Locale;

public class FiveActivity extends AppCompatActivity {
    private ProgressBar mProgressBar, mProgressBar1;

    private static final int TIME_INTERVAL2=2000;
    private long backpresd;

    private MediaPlayer mediaPlayer;
    int length;

    private TextView TotalTimeText, hour;
    private Button countdownButton;

    private long timeLeftinMiliseconds=300000 ; //5minutes
    private boolean timerRunning;
    private CountDownTimer countDownTimer;
    private TextView displayTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);

        getWindow().setStatusBarColor(getResources().getColor(R.color.purple_500));

        getSupportActionBar().hide();


        TotalTimeText=findViewById(R.id.twentyFive);
        countdownButton=findViewById(R.id.Start);





        mProgressBar =  findViewById(R.id.progressbar_timerview);
        mProgressBar1 =  findViewById(R.id.progressbar1_timerview);

        countdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setTimer();
                mProgressBar.setVisibility(View.INVISIBLE);


                starttimer();
                mProgressBar1.setVisibility(View.VISIBLE);

            }
        });


        updateTimer();
    }



    private void starttimer() {



        countDownTimer = new CountDownTimer(timeLeftinMiliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftinMiliseconds = millisUntilFinished;

                mProgressBar1.setProgress((int) (millisUntilFinished));

                updateTimer();
                countdownButton.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFinish() {
                startaudio();






               // countdownButton.setText("Start");
               // TotalTimeText.setText("00:00");

                //mProgressBar.setVisibility(View.VISIBLE);
                mProgressBar1.setVisibility(View.GONE);

                countdownButton.setVisibility(View.INVISIBLE);

                Intent intent7=new Intent();
                intent7.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                intent7.setClassName("com.example.admob","MainActivity");
                startActivity(intent7);
                finish();

            }
        }.start();



       // countdownButton.setText("Pause");


        timerRunning=true;


    }



    private void updateTimer () {

        int minutes = (int) timeLeftinMiliseconds / 60000;
        int seconds = (int) timeLeftinMiliseconds % 60000 / 1000;

        String timeLeftText=String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);;


        TotalTimeText.setText(timeLeftText);


    }



    public void setTimer () {

        mProgressBar1.setMax(300000);



    }
    public  void startaudio(){
        mediaPlayer=MediaPlayer.create(this,R.raw.media);
        mediaPlayer.start();


    }


    @Override
    public void onBackPressed() {
        if (backpresd+TIME_INTERVAL2>System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }
        else {

            Toast.makeText(getBaseContext(), "Press Back again to exit ", Toast.LENGTH_SHORT).show();
        }
        backpresd=System.currentTimeMillis();
    }

}