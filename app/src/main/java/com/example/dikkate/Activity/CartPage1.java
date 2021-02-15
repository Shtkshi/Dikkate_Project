package com.example.dikkate.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dikkate.Util.CartAdapter;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dikkate.R;

public class CartPage1 extends AppCompatActivity {
    RecyclerView recyclerView;
    CartAdapter cartAdapter;
    int arr[]={1,2,3,4,5,6,7};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_page1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());
        recyclerView=(RecyclerView)findViewById(R.id.Recycler_Cart1);
        recyclerView.setLayoutManager(new LinearLayoutManager(CartPage1.this,LinearLayoutManager.VERTICAL,false));
        cartAdapter=new CartAdapter(CartPage1.this,arr);
        recyclerView.setAdapter(cartAdapter);


        CardView Cart=(CardView) findViewById(R.id.Cart2);
        Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CartPage1.this, Address.class);
                startActivity(intent);
            }
        });


    }
}