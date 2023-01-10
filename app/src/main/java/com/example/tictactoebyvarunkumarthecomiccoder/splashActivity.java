package com.example.tictactoebyvarunkumarthecomiccoder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.tictactoe.R;

public class splashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Intent mainActivityIntent = new Intent(splashActivity.this, MainActivity.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(mainActivityIntent); // this will call main activity
                finish(); // this will remove current activity (splash screen) from call stack
            }
        }, 1500);
    }
}