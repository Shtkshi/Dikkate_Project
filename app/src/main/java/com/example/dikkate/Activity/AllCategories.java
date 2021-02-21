package com.example.dikkate.Activity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import com.example.dikkate.Activity.Services_Activities.Ac_Service_and_Repair.AC;
import com.example.dikkate.Activity.Services_Activities.Appliance_Repair.Appliance;
import com.example.dikkate.Activity.Services_Activities.Carpenters.Carpenters;
import com.example.dikkate.Activity.Services_Activities.Cleaning_Disinfection.Cleaning_Disinfection;
import com.example.dikkate.Activity.Services_Activities.Electricians.ELectricians;
import com.example.dikkate.Activity.Services_Activities.Painters.Painters;
import com.example.dikkate.Activity.Services_Activities.PestControls.PestControls;
import com.example.dikkate.Activity.Services_Activities.Plumbers.Plumbers;
import com.example.dikkate.Util.AllCategoriesAdapter;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.example.dikkate.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AllCategories extends AppCompatActivity {

    ArrayList<String>NameOfProblem=new ArrayList<>();
    ArrayList<Integer>icons = new ArrayList<>();
    ArrayList<Class>classes=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_all_categories2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle("All Categories");
        toolBarLayout.setExpandedTitleTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
        AddingItems();
        RecyclerView recyclerView=findViewById(R.id.AllCategoriesRecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(AllCategories.this,LinearLayoutManager.VERTICAL,false));
        AllCategoriesAdapter Adapter=new AllCategoriesAdapter(AllCategories.this,NameOfProblem,icons,classes);
        recyclerView.setAdapter(Adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }
    void AddingItems() {
        //problem name
        NameOfProblem.add("AC Problems");
        NameOfProblem.add("Appliances");
        NameOfProblem.add("Painters");
        NameOfProblem.add("Cleaning and Disinfection");
        NameOfProblem.add("Electricians");
        NameOfProblem.add("Plumbers");
        NameOfProblem.add("Carpenter");
        NameOfProblem.add("Pest Control");


        //Images for problems
        icons.add(R.drawable.acservices);
        icons.add(R.drawable.appliancesservices);
        icons.add(R.drawable.painterservices);
        icons.add(R.drawable.cleaningservices);
        icons.add(R.drawable.electriansservices);
        icons.add(R.drawable.plumberservices);
        icons.add(R.drawable.carpenterservices);
        icons.add(R.drawable.pestservices);

        classes.add(AC.class);
        classes.add(Appliance.class);
        classes.add(Painters.class);
        classes.add(Cleaning_Disinfection.class);
        classes.add(ELectricians.class);
        classes.add(Plumbers.class);
        classes.add(Carpenters.class);
        classes.add(PestControls.class);


    }
}