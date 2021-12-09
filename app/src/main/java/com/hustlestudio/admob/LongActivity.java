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

public class LongActivity extends AppCompatActivity {

    private static final int TIME_INTERVAL3=2000;
    private long backpressed3;


    private MediaPlayer mediaPlayer2;
    private ProgressBar pview, pRotate;
    private Button startBtn;
    private CountDownTimer count;
    private TextView total_fifteen;
    private long left = 900000;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long);
        getWindow().setStatusBarColor(getResources().getColor(R.color.purple_500));

        getSupportActionBar().hide();

        startBtn = findViewById(R.id.Start3);

        total_fifteen = findViewById(R.id.fifteen_minutes);

        pview = findViewById(R.id.progressbar8_timerview);
        pRotate = findViewById(R.id.progressbar2_timerview);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTimer();
                pview.setVisibility(View.INVISIBLE);

                stattTimer();
                pRotate.setVisibility(View.VISIBLE);
            }

        });


    }

    private void stattTimer() {

        count = new CountDownTimer(left, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                left = millisUntilFinished;

                pRotate.setProgress((int) (millisUntilFinished));

                update();
            }

            @Override
            public void onFinish() {
                audio();
                total_fifteen.setText("00:00");
                pview.setVisibility(View.VISIBLE);
                pRotate.setVisibility(View.INVISIBLE);
                startBtn.setVisibility(View.INVISIBLE);

                Intent intent=new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                intent.setClassName("com.example.admob","MainActivity");
                startActivity(intent);

            }
        }.start();

    }

    private void update() {
        int minutes = (int) left / 60000;
        int seconds = (int) left % 60000 / 1000;

        String LeftText=String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);;

        total_fifteen.setText(LeftText);

    }

    private void setTimer() {

        pRotate.setMax(900000);


    }

    public  void audio(){

        mediaPlayer2=MediaPlayer.create(this,R.raw.media);

        mediaPlayer2.start();
    }
    @Override
    public void onBackPressed() {
        if (backpressed3+TIME_INTERVAL3>System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }
        else {

            Toast.makeText(getBaseContext(), "Press Back again to exit ", Toast.LENGTH_SHORT).show();
        }
        backpressed3=System.currentTimeMillis();
    }

}