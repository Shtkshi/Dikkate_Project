package com.example.dikkate.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.dikkate.R;

public class SplashScreen extends Activity {
    Handler handler;
    boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        handler = new Handler();
        SharedPreferences sharedPreferences = getSharedPreferences("email", MODE_PRIVATE);
        loggedIn = sharedPreferences.getBoolean("loggedIn", false);
        boolean isFirstRun = getSharedPreferences("isFirstRun", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);
        handler.postDelayed(() -> {
            if (isFirstRun) {
                getSharedPreferences("isFirstRun", MODE_PRIVATE).edit()
                        .putBoolean("isFirstRun", false).apply();
                Intent i = new Intent(SplashScreen.this, AhoyCardView.class);
                startActivity(i);
                finish();
            } else
                if (loggedIn) {
                Intent i = new Intent(SplashScreen.this, NavigationDrawerActivity.class);
                startActivity(i);
                finish();
            } else {
                Intent i = new Intent(SplashScreen.this, LoginScreen.class);
                startActivity(i);
                finish();
            }
        }, 3000);
    }
}