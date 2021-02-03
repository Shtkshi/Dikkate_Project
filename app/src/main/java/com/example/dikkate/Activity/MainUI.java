package com.example.dikkate.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.dikkate.R;
import com.example.dikkate.RoomDataBase.Complaint;
import com.example.dikkate.RoomDataBase.Dao;
import com.example.dikkate.RoomDataBase.database;
import com.example.dikkate.Util.FloatingActionClicked;
import com.example.dikkate.ui.main.SectionsPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MainUI extends AppCompatActivity {
    EditText text;
    //property of MainUI
    FloatingActionClicked floatingActionClicked;


    // sets the instance of mainUI interface
    public void setFloatingActionClicked(FloatingActionClicked f) {
        floatingActionClicked = f;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_u_i);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(v -> CreateDialog());
    }


    public void CreateDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainUI.this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.add_query, null, false);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        // alertDialog.setCancelable(false);
        Dao dao = database.getInstance(this).dao();
        Complaint user = new Complaint();
        SharedPreferences pref = getSharedPreferences("email", MODE_PRIVATE);
        String email = pref.getString("email", "");
        user.setSubmitted_by(dao.UserId(email));
        text = dialogView.findViewById(R.id.add_edittext);
        int t = dao.UserId(email);
        dialogView.findViewById(R.id.save_add).setOnClickListener(v -> {
            user.setAssigned(false);
            String t1 = text.getText().toString().trim();
            if (t1.equals("")) {
                Toast.makeText(getApplicationContext(), "text needed", Toast.LENGTH_LONG).show();
                return;
            }
            user.setCompleted(false);
            user.setQuery(t1);
            dao.EnterComplaint(user);
            if (floatingActionClicked != null) {
                floatingActionClicked.onFloatingActionClicked();
            }
            alertDialog.cancel();
        });
        alertDialog.show();
    }
}