package com.example.dikkate.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dikkate.Fragment.Fragment3;
import com.example.dikkate.Fragment.ui.home.HomeFragment;
import com.example.dikkate.R;

public class Payment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);


        CardView cardView=(CardView) findViewById(R.id.Cart_Payment);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Payment.this,Fragment3.class);
                startActivity(intent);

                /*Fragment fragment = new HomeFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_home, fragment, fragment.getClass().getSimpleName())
                        .commit();*/
            }
        });
    }
}
