package com.example.dikkate.Activity;

import androidx.appcompat.app.AppCompatActivity;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_page);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Bill_No=extras.getInt("Bill No");
            User_Id=extras.getInt("User Id");
        }
        star = -1;
        MaterialRatingBar ratingBar = findViewById(R.id.feedback_star);
        ratingBar.setOnRatingChangeListener(new MaterialRatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChanged(MaterialRatingBar ratingBar, float rating) {
                star = rating;
            }
        });
        EditText feedback = findViewById(R.id.feedback_text);
        Button button=findViewById(R.id.feedback_done);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feed = feedback.getText().toString();
            }
        });
        findViewById(R.id.feedback_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (star != -1) {
                    Dao dao = database.getInstance(getApplicationContext()).dao();
                    Complaint complaint=dao.GetComplaint(Bill_No);
                    complaint.setBill_No(Bill_No);
                    complaint.setStar(star);
                    complaint.setGiven(true);
                    complaint.setFeedback_Text(feed);
                    dao.UpdateComplaint(complaint);
                }
            }
        });
    }
}