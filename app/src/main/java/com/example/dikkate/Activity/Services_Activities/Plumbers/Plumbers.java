package com.example.dikkate.Activity.Services_Activities.Plumbers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import com.example.dikkate.Activity.CartPage1;
import com.example.dikkate.Activity.Services_Activities.Ac_Service_and_Repair.AC;
import com.example.dikkate.Activity.Services_Activities.Appliance_Repair.SliderAdapter;
import com.example.dikkate.RoomDataBase.Catergory_User_mapping;
import com.example.dikkate.RoomDataBase.Dao;
import com.example.dikkate.RoomDataBase.database;
import com.example.dikkate.Util.FeedbackAdapter;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dikkate.R;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.INVISIBLE;

public class Plumbers extends AppCompatActivity {
    int UserId;
    Dao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plumbers);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle("Plumber Problems");

        dao = database.getInstance(getApplicationContext()).dao();

        //UserId
        SharedPreferences sharedPreferences = getSharedPreferences("email", MODE_PRIVATE);
        UserId = sharedPreferences.getInt("UserId", -1);


        // Animated Slider
        Slider();

        //Add the ite button
        Button button1 = findViewById(R.id.add_plumbers);
        button1.setBackgroundColor(Color.parseColor("#CAE1FF"));

        //Adding an item
        CardView CheckOut = (CardView) findViewById(R.id.Cart_plumbers);
        AddServices(button1, CheckOut, dao, UserId);

        //CheckCart
        int Cart = dao.NoOfOrders(UserId,1);

        if (Cart == 0) {
            CheckOut.setVisibility(View.INVISIBLE);
        } else {

            CheckOut.setVisibility(View.VISIBLE);
            TextView NoOfOrderDisplay = findViewById(R.id.no_of_orders_plumbers);
            NoOfOrderDisplay.setText(String.valueOf(Cart));
            CartService();
        }
        //CartService

        CartService();


        //FeedbackStars
        Feedback();
        TextView AverageStars=findViewById(R.id.Stars_average_Plumber);
        AverageStars.setText(String.valueOf(dao.Average_Feedback(6,0)));

        TextView AverageStars2=findViewById(R.id.starAveragePlumber);
        AverageStars2.setText(String.valueOf(dao.Average_Feedback(6,0)));

    }
    @Override
    protected void onResume() {
        super.onResume();
        TextView NoOfOrders=(TextView)findViewById(R.id.no_of_orders_plumbers);
        NoOfOrders.setText(String.valueOf(dao.NoOfOrders(UserId,1)));
        if(dao.NoOfOrders(UserId,1)==0){
            CardView Cart=findViewById(R.id.Cart_plumbers);
            Cart.setVisibility(INVISIBLE);
        }

    }
    void CartService() {


        LinearLayout Cart = (LinearLayout) findViewById(R.id.CartLinear_plumbers);
        Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Plumbers.this, CartPage1.class);
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
                catergory_user_mapping.setCategoryID(6);
                catergory_user_mapping.setUserID(UserId);
                catergory_user_mapping.setLevel(1);
                dao.EnterMappingUserCategories(catergory_user_mapping);

                int Orders = dao.NoOfOrders(UserId,1);

                TextView orderTextview = findViewById(R.id.no_of_orders_plumbers);
                orderTextview.setText(String.valueOf(Orders));
            }
        });



    }

    void Slider() {
        ArrayList<Integer> sliderDataArrayList = new ArrayList<>();

        // initializing the slider view.
        SliderView sliderView = findViewById(R.id.slide_plumbers);


        // adding the urls inside array list
        sliderDataArrayList.add(R.drawable.plumberservices);
        sliderDataArrayList.add(R.drawable.plumberservices);
        sliderDataArrayList.add(R.drawable.plumberservices);
        sliderDataArrayList.add(R.drawable.plumberservices);
        sliderDataArrayList.add(R.drawable.plumberservices);

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
    RecyclerView RecyclerViewFeedback;
    FeedbackAdapter feedbackAdapter;
    List<Catergory_User_mapping> feedbacks;
    private void Feedback(){
        feedbacks=dao.Feedbacks(6,0);
        RecyclerViewFeedback=(RecyclerView)findViewById(R.id.recyclefeedbackPlumber);
        RecyclerViewFeedback.setLayoutManager(new LinearLayoutManager(Plumbers.this,LinearLayoutManager.VERTICAL,false));
        feedbackAdapter=new FeedbackAdapter(Plumbers.this,feedbacks,dao);
        RecyclerViewFeedback.setAdapter(feedbackAdapter);
        RecyclerViewFeedback.setLayoutManager(new LinearLayoutManager(this));


    }
}