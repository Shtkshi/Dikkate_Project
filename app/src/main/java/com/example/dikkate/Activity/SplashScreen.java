package com.example.dikkate.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.dikkate.R;
import com.example.dikkate.RoomDataBase.Category;
import com.example.dikkate.RoomDataBase.Dao;
import com.example.dikkate.RoomDataBase.database;

public class SplashScreen extends Activity {
    Handler handler;
    boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        handler = new Handler();
        SharedPreferences sharedPreferences=getSharedPreferences("email",MODE_PRIVATE);
        int UserID=sharedPreferences.getInt("UserId",-1);
        SharedPreferences sharedpreferences = getSharedPreferences("email", MODE_PRIVATE);
        loggedIn = sharedpreferences.getBoolean("loggedIn", false);
        boolean isFirstRun = getSharedPreferences("isFirstRun", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);
        handler.postDelayed(() -> {
            if (isFirstRun) {


                SharedPreferences SharedPreferences=getSharedPreferences("email",MODE_PRIVATE);
                SharedPreferences.edit().putInt("UserId",UserID).apply();


                //Set category values in table
                setCategories();


                getSharedPreferences("isFirstRun", MODE_PRIVATE).edit()
                        .putBoolean("isFirstRun", false).apply();
                Intent i = new Intent(SplashScreen.this, AhoyCardView.class);
                startActivity(i);
                finish();
            } else if (loggedIn) {
                SharedPreferences SharedPreferences=getSharedPreferences("email",MODE_PRIVATE);
                sharedPreferences.edit().putInt("UserId",UserID).apply();

                Intent i = new Intent(SplashScreen.this, NavigationDrawerActivity.class);
                startActivity(i);
                finish();
            } else {
                SharedPreferences SharedPreferences=getSharedPreferences("email",MODE_PRIVATE);
                sharedPreferences.edit().putInt("UserId",UserID).apply();

                Intent i = new Intent(SplashScreen.this, LoginScreen.class);
                startActivity(i);
                finish();
            }
        }, 3000);
    }

    void setCategories() {
        Category category = new Category();
        category.setCatergoryGivenId(1);
        category.setCategoryName("Ac");
        Dao dao = database.getInstance(getApplicationContext()).dao();
        dao.EnterCategories(category);
        category.setCatergoryGivenId(2);
        category.setCategoryName("Application");
        dao.EnterCategories(category);
        category.setCatergoryGivenId(3);
        category.setCategoryName("Painter");
        dao.EnterCategories(category);
        category.setCatergoryGivenId(4);
        category.setCategoryName("Cleaning and Disinfection");
        dao.EnterCategories(category);
        category.setCatergoryGivenId(5);
        category.setCategoryName("Electricians");
        dao.EnterCategories(category);
        category.setCatergoryGivenId(6);
        category.setCategoryName("Plumbers");
        dao.EnterCategories(category);
        category.setCatergoryGivenId(7);
        category.setCategoryName("Carpenters");
        dao.EnterCategories(category);
        category.setCatergoryGivenId(8);
        category.setCategoryName("Pest Controls");
        dao.EnterCategories(category);

    }
}