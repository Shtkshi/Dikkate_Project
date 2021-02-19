package com.example.dikkate.Activity.Services_Activities.Ac_Service_and_Repair;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import com.example.dikkate.Activity.CartPage1;
import com.example.dikkate.Activity.Services_Activities.Appliance_Repair.Appliance;
import com.example.dikkate.Activity.Services_Activities.Appliance_Repair.SliderAdapter;
import com.example.dikkate.RoomDataBase.Catergory_User_mapping;
import com.example.dikkate.RoomDataBase.Dao;
import com.example.dikkate.RoomDataBase.database;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dikkate.R;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

import static android.view.View.INVISIBLE;

public class AC extends AppCompatActivity {
    Dao dao;
    int UserId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_c);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle("AC and Repair");

        dao = database.getInstance(getApplicationContext()).dao();

        //UserId
        SharedPreferences sharedPreferences = getSharedPreferences("email", MODE_PRIVATE);
        UserId = sharedPreferences.getInt("UserId", -1);


        // Animated Slider
        Slider();

        //Add the ite button
        Button button1 = findViewById(R.id.add_AC);
        button1.setBackgroundColor(Color.parseColor("#CAE1FF"));

        //Adding an item
        CardView CheckOut = (CardView) findViewById(R.id.Cart_ac);
        AddServices(button1, CheckOut, dao, UserId);

        //CheckCart
        int Cart = dao.NoOfOrders(UserId,1);

        if (Cart == 0) {
            CheckOut.setVisibility(View.INVISIBLE);
        } else {

            CheckOut.setVisibility(View.VISIBLE);
            TextView NoOfOrderDisplay = findViewById(R.id.no_of_orders_ac);
            NoOfOrderDisplay.setText(String.valueOf(Cart));
            CartService();
        }
        //CartService

        CartService();




    }


    void CartService() {


        LinearLayout Cart = (LinearLayout) findViewById(R.id.CartLinear_ac);
        Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AC.this, CartPage1.class);
                startActivity(intent);

            }
        });


    }

    void AddServices(Button button, CardView CheckOut, Dao dao, int UserId) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckOut.setVisibility(View.VISIBLE);
                Catergory_User_mapping catergory_user_mapping = new Catergory_User_mapping();
                catergory_user_mapping.setCategoryID(1);
                catergory_user_mapping.setUserID(UserId);
                catergory_user_mapping.setLevel(1);

                dao.EnterMappingUserCategories(catergory_user_mapping);

                int Orders = dao.NoOfOrders(UserId,1);

                TextView orderTextview = findViewById(R.id.no_of_orders_ac);
                orderTextview.setText(String.valueOf(Orders));
            }
        });


    }
    @Override
    protected void onResume() {
        super.onResume();
        TextView NoOfOrders=(TextView)findViewById(R.id.no_of_orders_ac);
        NoOfOrders.setText(String.valueOf(dao.NoOfOrders(UserId,1)));
        if(dao.NoOfOrders(UserId,1)==0){
            CardView Cart=findViewById(R.id.Cart_ac);
            Cart.setVisibility(INVISIBLE);
        }

    }


    void Slider() {
        ArrayList<Integer> sliderDataArrayList = new ArrayList<>();

        // initializing the slider view.
        SliderView sliderView = findViewById(R.id.slide_ac);


        // adding the urls inside array list
        sliderDataArrayList.add(R.drawable.ac_one);
        sliderDataArrayList.add(R.drawable.ac_two);
        sliderDataArrayList.add(R.drawable.ac_three);
        sliderDataArrayList.add(R.drawable.acservices);
        sliderDataArrayList.add(R.drawable.air);

        // passing this array list inside our adapter class.
        SliderAdapter adapter = new SliderAdapter(this, sliderDataArrayList);

        // below method is used to set auto cycle direction in left to
        // right direction you can change according to requirement.
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);

        // below method is used to
        // setadapter to sliderview.
        sliderView.setSliderAdapter(adapter);

        // below method is use to set
        // scroll time in seconds.
        sliderView.setScrollTimeInSec(3);

        // to set it scrollable automatically
        // we use below method.
        sliderView.setAutoCycle(true);

        // to start autocycle below method is used.
        sliderView.startAutoCycle();

    }
}