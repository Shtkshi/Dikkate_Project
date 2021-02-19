package com.example.dikkate.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.dikkate.RoomDataBase.Catergory_User_mapping;
import com.example.dikkate.RoomDataBase.Dao;
import com.example.dikkate.RoomDataBase.database;
import com.example.dikkate.Util.CartAdapter;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dikkate.R;

import java.util.List;

public class CartPage1 extends AppCompatActivity {
    RecyclerView recyclerView;
    CartAdapter cartAdapter;
    List<Catergory_User_mapping> arr;
    Dao dao;
    int UserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_page1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Collapsing Toolbar
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle("Summary");
        toolBarLayout.setExpandedTitleTextColor((ColorStateList.valueOf(Color.parseColor("#000000"))));



        //CartItems
        SharedPreferences sharedPreferences=getSharedPreferences("email",MODE_PRIVATE);
        UserID=sharedPreferences.getInt("UserId",-1);

        dao= database.getInstance(getApplicationContext()).dao();
        arr=dao.CartItems(UserID,1);


        //RecyclerView
        recyclerView=(RecyclerView)findViewById(R.id.Recycler_Cart1);
        recyclerView.setLayoutManager(new LinearLayoutManager(CartPage1.this,LinearLayoutManager.VERTICAL,false));
        cartAdapter=new CartAdapter(CartPage1.this,arr,dao,UserID);
        recyclerView.setAdapter(cartAdapter);
        int a=dao.NoOfOrders(UserID,1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        //Cart
        cartAdapter.notifyDataSetChanged();
        TextView NoOfOrders=(TextView)findViewById(R.id.no_of_orders2);
        NoOfOrders.setText(String.valueOf(a));
        CardView Cart=(CardView) findViewById(R.id.Cart2);

        Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CartPage1.this, Address.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView NoOfOrders=(TextView)findViewById(R.id.no_of_orders2);
        NoOfOrders.setText(String.valueOf(dao.NoOfOrders(UserID,1)));
    }
}