package com.example.dikkate.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dikkate.Activity.Services_Activities.Ac_Service_and_Repair.AC;
import com.example.dikkate.Activity.Services_Activities.Appliance_Repair.Appliance;
import com.example.dikkate.Activity.Services_Activities.Carpenters.Carpenters;
import com.example.dikkate.Activity.Services_Activities.Cleaning_Disinfection.Cleaning_Disinfection;
import com.example.dikkate.Activity.Services_Activities.Electricians.ELectricians;
import com.example.dikkate.Activity.Services_Activities.Painters.Painters;
import com.example.dikkate.Activity.Services_Activities.PestControls.PestControls;
import com.example.dikkate.Activity.Services_Activities.Plumbers.Plumbers;
import com.example.dikkate.R;
import com.example.dikkate.Util.Image;


public class Fragment3 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_FULLSCREEN);
        return inflater.inflate(R.layout.fragment3, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CallingGridView_AllCategories(view);
        CallingGridView_Pest(view);
        CallingGridView_Appliances(view);

        Button button1 = view.findViewById(R.id.buttonone);
        button1.setBackgroundColor(Color.parseColor("#CAE1FF"));

        Button button2 = view.findViewById(R.id.buttontwo);
        button2.setBackgroundColor(Color.parseColor("#CAE1FF"));


    }

    void CallingGridView_Appliances(View view) {
        GridView simpleGrid;
        int[] logos = {R.drawable.appone, R.drawable.appteo, R.drawable.appthree, R.drawable.appfour};

        simpleGrid = (GridView) view.findViewById(R.id.gridview_3_search); // init GridView
        // Create an object of CustomAdapter and set Adapter to GirdView
        Image image = new Image(getContext(), logos);
        simpleGrid.setAdapter(image);
        // implement setOnItemClickListener event on GridView
        simpleGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // set an Intent to Another Activity
                /*Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("image", logos[position]); // put image data in Intent
                startActivity(intent); // start Intent*/
            }
        });

    }

    void CallingGridView_Pest(View view) {
        GridView simpleGrid;
        int[] logos = {R.drawable.pestone, R.drawable.pestthree, R.drawable.pesttwo, R.drawable.pestfour};

        simpleGrid = (GridView) view.findViewById(R.id.gridview_2_search); // init GridView
        // Create an object of CustomAdapter and set Adapter to GirdView
        Image image = new Image(getContext(), logos);
        simpleGrid.setAdapter(image);
        // implement setOnItemClickListener event on GridView
        simpleGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // set an Intent to Another Activity
                /*Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("image", logos[position]); // put image data in Intent
                startActivity(intent); // start Intent*/
            }
        });

    }

    void CallingGridView_AllCategories(View view) {
        GridView simpleGrid;
        int[] logos = {R.drawable.ac_service_repair, R.drawable.appliance, R.drawable.painter, R.drawable.cleaning_disinfection,
                R.drawable.electrician, R.drawable.plumber, R.drawable.carpenter, R.drawable.pestcontrol};

        Class[] classes={AC.class, Appliance.class, Painters.class, Cleaning_Disinfection.class,
                ELectricians.class, Plumbers.class, Carpenters.class, PestControls.class};
        simpleGrid = (GridView) view.findViewById(R.id.gridview); // init GridView
        // Create an object of CustomAdapter and set Adapter to GirdView
        Image image = new Image(getContext(), logos);
        simpleGrid.setAdapter(image);
        // implement setOnItemClickListener event on GridView
        simpleGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // set an Intent to Another Activity
                Intent intent = new Intent(getContext(), classes[position]);
                startActivity(intent); // start Intent

            }
        });
    }

}