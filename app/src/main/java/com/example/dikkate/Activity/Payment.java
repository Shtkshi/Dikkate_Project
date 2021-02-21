package com.example.dikkate.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.dikkate.R;
import com.example.dikkate.RoomDataBase.Catergory_User_mapping;
import com.example.dikkate.RoomDataBase.Dao;
import com.example.dikkate.RoomDataBase.database;

import java.util.List;

public class Payment extends AppCompatActivity {
    List<Catergory_User_mapping> cartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        //Cart
        SharedPreferences sharedPreferences = getSharedPreferences("email", MODE_PRIVATE);
        int UserID = sharedPreferences.getInt("UserId", -1);
        Dao dao = database.getInstance(getApplicationContext()).dao();
        TextView NoOfOrders = findViewById(R.id.no_of_orders_Payment);
        NoOfOrders.setText(String.valueOf(dao.NoOfOrders(UserID, 1)));


        CardView cardView = (CardView) findViewById(R.id.Cart_Payment);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao.UpdateCart_User(2, 1, UserID);
                Intent intent = new Intent(Payment.this, NavigationDrawerActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
}
