package com.example.dikkate.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dikkate.R;

import in.aabhasjindal.otptextview.OtpTextView;

public class Phone extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);


        CardView cardView=(CardView) findViewById(R.id.Cart_Phone);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Phone.this,Payment.class);
                startActivity(intent);
            }
        });
    }

}