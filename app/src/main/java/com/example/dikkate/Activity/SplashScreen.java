package com.example.dikkate.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.dikkate.R;

public class SplashScreen extends Activity {
    Handler handler;
    boolean loggedIn=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        handler = new Handler();
        SharedPreferences sharedPreferences=getSharedPreferences("loggedIn",MODE_PRIVATE);
        loggedIn=sharedPreferences.getBoolean("loggedIn",false);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                    Intent i = new Intent(SplashScreen.this, LoginScreen.class);
                    startActivity(i);
                    finish();

            }
        }, 3000);
    }
}