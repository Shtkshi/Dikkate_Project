package com.example.dikkate.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dikkate.R;
import com.example.dikkate.RoomDataBase.Complaint;
import com.example.dikkate.RoomDataBase.Dao;
import com.example.dikkate.RoomDataBase.database;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class FeedbackPage extends AppCompatActivity {

    float star;
    String feed = "";
    int Bill_No;
    int User_Id;
    int PrimaryKeyID;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_page);
        star = -1;
        Intent intent = getIntent();
        PrimaryKeyID = intent.getExtras().getInt("PrimaryKeyID", -1);

        Dao dao = database.getInstance(getApplicationContext()).dao();
        MaterialRatingBar ratingBar = findViewById(R.id.feedback_star);
        ratingBar.setElevation(dao.StarFill(PrimaryKeyID));
        ratingBar.setOnRatingChangeListener(new MaterialRatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChanged(MaterialRatingBar ratingBar, float rating) {
                star = rating;
            }
        });

        EditText feedback = findViewById(R.id.feedback_text);
        findViewById(R.id.feedback_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (star != -1) {
                    feed=feedback.getText().toString();
                    dao.ReviewStars(star,PrimaryKeyID,feed);
                }
            }
        });
    }
}