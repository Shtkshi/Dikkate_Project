package com.example.dikkate.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.dikkate.R;
import com.example.dikkate.RoomDataBase.Dao;
import com.example.dikkate.RoomDataBase.database;

import in.aabhasjindal.otptextview.OtpTextView;

public class Phone extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);


        //Cart
        SharedPreferences sharedPreferences=getSharedPreferences("email",MODE_PRIVATE);
        int UserID=sharedPreferences.getInt("UserId",-1);
        Dao dao= database.getInstance(getApplicationContext()).dao();
        TextView NoOfOrders=findViewById(R.id.no_of_orders_Phone);
        NoOfOrders.setText(String.valueOf(dao.NoOfOrders(UserID,1)));


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