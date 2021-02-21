package com.example.dikkate.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.dikkate.R;
import com.example.dikkate.RoomDataBase.Complaint;
import com.example.dikkate.RoomDataBase.Dao;
import com.example.dikkate.RoomDataBase.database;
import com.example.dikkate.Util.FloatingActionClicked;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class NavigationDrawerActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> CreateDialog());
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.getMenu().findItem(R.id.LogOut).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                SharedPreferences.Editor myEdit = getSharedPreferences("email", MODE_PRIVATE).edit();
                myEdit.putBoolean("loggedIn", false);
                myEdit.apply();
                Intent intent = new Intent(NavigationDrawerActivity.this, LoginScreen.class);
                startActivity(intent);
                finish();
                return true;
            }
        });
        Dao dao = database.getInstance(getApplicationContext()).dao();
        TextView Profile_Name = findViewById(R.id.Profile_Name);
        TextView Profile_Email = findViewById(R.id.Profile_Email);
        SharedPreferences pref = getSharedPreferences("email", MODE_PRIVATE);
        String email = pref.getString("email", "");
        int UserId = (dao.UserId(email));
        //Profile_Email.setText(email);
        //Profile_Name.setText(dao.EmployeeAssignedName(UserId));

    }

    FloatingActionClicked floatingActionClicked;


    public void CreateDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(NavigationDrawerActivity.this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.add_query, null, false);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        // alertDialog.setCancelable(false);
        Dao dao = database.getInstance(this).dao();
        Complaint user = new Complaint();
        SharedPreferences pref = getSharedPreferences("email", MODE_PRIVATE);
        String email = pref.getString("email", "");
        user.setSubmitted_by(dao.UserId(email));
        EditText text = dialogView.findViewById(R.id.add_edittext);
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

    // sets the instance of mainUI interface
    public void setFloatingActionClicked(FloatingActionClicked f) {
        floatingActionClicked = f;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}