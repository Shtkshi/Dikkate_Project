package com.example.dikkate.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dikkate.R;
import com.example.dikkate.RoomDataBase.Dao;
import com.example.dikkate.RoomDataBase.TotalUsers;
import com.example.dikkate.RoomDataBase.database;

public class SignIn extends Activity implements AdapterView.OnItemSelectedListener {
    EditText NameBox,ContactNoBox,EmailBox,dobBox,AddressBox,Password;
    Spinner RoleBox;
    Button RegisterBox;
    ImageButton calenderBox;
    enum UserRole{
        User,
        Admin,
        Employee
    }
    String userstype[ ]={"User","Worker","Admin"};
    int roletype=-1;
    long selectedDate=-1;
    String dobcalender="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        NameBox=(EditText)findViewById(R.id.name);
        ContactNoBox=(EditText)findViewById(R.id.phone);
        EmailBox=(EditText)findViewById(R.id.email);
        dobBox=(EditText)findViewById(R.id.dob);
        RoleBox=(Spinner)findViewById(R.id.spinnerRole);
        RegisterBox=(Button)findViewById(R.id.create);
        calenderBox=(ImageButton)findViewById(R.id.caldob);
        AddressBox=(EditText)findViewById(R.id.Address);
        Password=(EditText)findViewById(R.id.Password);
        RoleBox.setOnItemSelectedListener(this);


        Dao dao= database.getInstance(SignIn.this).dao();

        calenderBox.setOnClickListener(v -> {
            CalendarView simpleCalendarView = (CalendarView) findViewById(R.id.calenderlayout); // get the reference of CalendarView
            selectedDate = simpleCalendarView.getDate(); // get selected date in milliseconds

            dobcalender=Long.toString(selectedDate);
        });
        RegisterBox.setOnClickListener(v -> {
            if(dao.AlreadyExist(EmailBox.getText().toString())){
                Toast.makeText(getApplicationContext(),"EmailId already registered",Toast.LENGTH_SHORT).show();
                return;
            }
            if(NameBox.getText().toString().isEmpty()||ContactNoBox.getText().toString().isEmpty()||
                    EmailBox.getText().toString().isEmpty()||(dobBox.getText().toString().isEmpty()&&selectedDate==-1)||
                    roletype==-1 || AddressBox.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(),"Manditory fields. Please fill",Toast.LENGTH_SHORT).show();
                return;
            }
            TotalUsers data=new TotalUsers(); // ese set krne ki bajae youcan also use constructors
            data.setName(NameBox.getText().toString());
            data.setAddress(AddressBox.getText().toString());
            data.setEmail(EmailBox.getText().toString());
            data.setPassword(Password.getText().toString());
            data.setPhoneNo(ContactNoBox.getText().toString());
            data.setServiceID(roletype);
            data.setRole(userstype[roletype]);
            dao.EnterData(data);

            if(dobBox.getText().toString().isEmpty()){
                data.setDob(dobcalender);
            }
            else{
                data.setDob(dobBox.getText().toString());
            }
            SharedPreferences.Editor myEdit=getSharedPreferences("email",MODE_PRIVATE).edit();
            myEdit.putString("email",EmailBox.getText().toString());
            myEdit.apply();
            Intent intent=new Intent(SignIn.this, NavigationDrawerActivity.class);
            startActivity(intent);
            finish();
        });
        ArrayAdapter<String> SpinnerAdapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,userstype);
        SpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        RoleBox.setAdapter(SpinnerAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getItemAtPosition(position).toString().trim().equals("User")){
            roletype=0;
        }
        else if(parent.getItemAtPosition(position).toString().trim().equals("Worker")){
            roletype=1;
        }
        else if(parent.getItemAtPosition(position).toString().trim().equals("Admin")){
            roletype=2;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}