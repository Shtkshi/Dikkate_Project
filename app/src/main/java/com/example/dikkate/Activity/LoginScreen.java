package com.example.dikkate.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codemybrainsout.onboarder.AhoyOnboarderActivity;
import com.example.dikkate.R;
import com.example.dikkate.RoomDataBase.Dao;
import com.example.dikkate.RoomDataBase.database;

public class LoginScreen extends AppCompatActivity  {
    EditText EmailBox;
    EditText PasswordBox;
    TextView SignInHyperButton;
    Button LogIn;
    boolean loggedIn=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        EmailBox= findViewById(R.id.LogInemail);
        PasswordBox= findViewById(R.id.LogInpassword);
        SignInHyperButton= findViewById(R.id.signin);
        LogIn= findViewById(R.id.LogInButton);


        /*if(EmailBox.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Manditory field",Toast.LENGTH_SHORT).show();
            return;
        }
        if(PasswordBox.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Manditory field",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!isEmailValid(EmailBox.getText().toString())){
            Toast.makeText(getApplicationContext(),"Type a valid email id",Toast.LENGTH_SHORT).show();
            return;
        }*/
        Dao dao=database.getInstance(LoginScreen.this).dao();

        Log.d(String.valueOf(dao.AlreadyExist(EmailBox.getText().toString())), "exist");
        LogIn.setOnClickListener(v -> {
            if(dao.AlreadyExist(EmailBox.getText().toString())){
                if(dao.ExtractPassword(EmailBox.getText().toString()).equals(PasswordBox.getText().toString())){
                    Intent intent=new Intent(LoginScreen.this, NavigationDrawerActivity.class);
                    loggedIn=true;
                    SharedPreferences sharedPreferences=getSharedPreferences("email",MODE_PRIVATE);
                    sharedPreferences.edit().putBoolean("loggedIn",loggedIn).apply();
                    sharedPreferences.edit().putInt("UserId",dao.UserId(EmailBox.getText().toString())).apply();
                    startActivity(intent);
                    int user=dao.UserId(EmailBox.getText().toString());
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Email or Password is incorrect",Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(getApplicationContext(),"Email or Password is incorrect",Toast.LENGTH_SHORT).show();
            }
        });

        SignInHyperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginScreen.this, SignIn.class);
                startActivity(intent);
                finish();
            }
            });

    }

    boolean isEmailValid(CharSequence email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}