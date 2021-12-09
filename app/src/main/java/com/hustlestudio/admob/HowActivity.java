package com.hustlestudio.admob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admob.R;

public class HowActivity extends AppCompatActivity {
    private ImageView goTo;
    private TextView textViewGet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how);

        getWindow().setStatusBarColor(getResources().getColor(R.color.purple_500));


        getSupportActionBar().hide();

        goTo=findViewById(R.id.imageView2);
        textViewGet=findViewById(R.id.get);


        goTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HowActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        textViewGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4=new Intent(HowActivity.this,MainActivity.class);
                startActivity(intent4);
                finish();
            }
        });
    }
}