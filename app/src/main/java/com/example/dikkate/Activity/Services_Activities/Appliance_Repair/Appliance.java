package com.example.dikkate.Activity.Services_Activities.Appliance_Repair;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.dikkate.Activity.CartPage1;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.example.dikkate.R;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class Appliance extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appliance);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());

        // Animated Slider
        Slider();

        //Add the ite button
        Button button1 = findViewById(R.id.add_Appliances);
        button1.setBackgroundColor(Color.parseColor("#CAE1FF"));

        //Adding an item
        CardView CheckOut=(CardView) findViewById(R.id.Cart);
        AddServices(button1,CheckOut);

        //
        LinearLayout Cart=(LinearLayout)findViewById(R.id.CartLinear);
        Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Appliance.this, CartPage1.class);
                startActivity(intent);
            }
        });
    }


    void AddServices(Button button,CardView CheckOut){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckOut.setVisibility(View.VISIBLE);
            }
        });


    }

    void Slider(){
        ArrayList<Integer> sliderDataArrayList = new ArrayList<>();

        // initializing the slider view.
        SliderView sliderView = findViewById(R.id.slider);



        // adding the urls inside array list
        sliderDataArrayList.add(R.drawable.w);
        sliderDataArrayList.add(R.drawable.appliancessliderthree);
        sliderDataArrayList.add(R.drawable.z);
        sliderDataArrayList.add(R.drawable.s);
        sliderDataArrayList.add(R.drawable.q);

        /*sliderDataArrayList.add(new SliderData(url1));
        sliderDataArrayList.add(new SliderData(url2));
        sliderDataArrayList.add(new SliderData(url3));
*/
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