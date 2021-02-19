package com.example.dikkate.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;
import com.example.dikkate.R;
import com.example.dikkate.RoomDataBase.Dao;
import com.example.dikkate.RoomDataBase.TotalUsers;
import com.example.dikkate.RoomDataBase.database;
import com.example.dikkate.Util.ListItem;
import com.example.dikkate.Util.TimelineAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.widget.LinearLayout.HORIZONTAL;

public class viewDetails extends AppCompatActivity {
    List<TotalUsers> workers;
    SmartMaterialSpinner<String> spinner;
    int UserId = -1;
    ArrayList<String> names = new ArrayList<>();

    String name;
    int nextPosition = 0;
    int bill = 0;
    Dao dao;
    int PrimaryKeyID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        dao = database.getInstance(getApplicationContext()).dao();
        Timeline_Horizontal(nextPosition);
        Bundle extras = getIntent().getExtras();
        spinner = findViewById(R.id.SearchableSpinner_Fragment4);
        if (extras != null) {
            bill = extras.getInt("Complaint No");
            name = extras.getString("Complaint Name");
            SharedPreferences sharedPreferences = getSharedPreferences("Bill", Context.MODE_PRIVATE);
            sharedPreferences.edit().putInt("bill", bill).apply();

        }

        Intent intent = getIntent();
        PrimaryKeyID = intent.getExtras().getInt("PrimaryKeyID", -1);
        Timeline_Update();


    }

    void Timeline_Update() {


        nextPosition = dao.GetLevel(PrimaryKeyID);
        Visibility();
        if (nextPosition > 1)
            Timeline_Horizontal(nextPosition - 2);


        Button nextTimeline = findViewById(R.id.Next);
        nextTimeline.setOnClickListener(v -> {
            if (nextPosition < 6) {
                nextPosition++;
                dao.UpdateCart(dao.GetLevel(PrimaryKeyID) + 1, dao.GetLevel(PrimaryKeyID), PrimaryKeyID);
            }
            Visibility();
            Timeline_Horizontal(nextPosition - 2);
        });
        Button prevTimeline = findViewById(R.id.Previous);
        prevTimeline.setOnClickListener(v -> {
            if (nextPosition > 0) {
                nextPosition--;
                dao.UpdateCart(dao.GetLevel(PrimaryKeyID) - 1, dao.GetLevel(PrimaryKeyID), PrimaryKeyID);
            }
            Visibility();
            Timeline_Horizontal(nextPosition - 2);
        });


    }

    void Visibility() {
        if (nextPosition == 2) {//Call
            //Employee
            LinearLayout linearLayout1 = findViewById(R.id.employee_status_fragment4);
            linearLayout1.setVisibility(View.INVISIBLE);

            //Payment
            Button Payment = findViewById(R.id.Payment_Button);
            Payment.setVisibility(View.INVISIBLE);

            //Review
            Button Review = findViewById(R.id.Review_Button);
            Review.setVisibility(View.INVISIBLE);


            //Call
            LinearLayout linearLayout = findViewById(R.id.Linear_Call);
            linearLayout.setVisibility(View.VISIBLE);
            Button Call = findViewById(R.id.CallButton);
            Call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callPhoneNumber();
                }
            });

            Button NextLevel = findViewById(R.id.next_stage);
            NextLevel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dao.UpdateCart(2, 1, PrimaryKeyID);
                    int position = dao.GetLevel(PrimaryKeyID);
                    Timeline_Horizontal(position - 1);
                }
            });

        } else if (nextPosition == 3) {//employee
            //Call
            LinearLayout linearLayout = findViewById(R.id.Linear_Call);
            linearLayout.setVisibility(View.INVISIBLE);


            //Payment
            Button Payment = findViewById(R.id.Payment_Button);
            Payment.setVisibility(View.INVISIBLE);

            //Review
            Button Review = findViewById(R.id.Review_Button);
            Review.setVisibility(View.INVISIBLE);


            //Employee
            LinearLayout linearLayout1 = findViewById(R.id.employee_status_fragment4);
            linearLayout1.setVisibility(View.VISIBLE);
            initSpinner(bill, PrimaryKeyID);

        }else if(nextPosition==4){//Payment
            //Call
            LinearLayout linearLayout = findViewById(R.id.Linear_Call);
            linearLayout.setVisibility(View.INVISIBLE);


            //Payment
            Button Payment = findViewById(R.id.Payment_Button);
            Payment.setVisibility(View.VISIBLE);

            //Review
            Button Review = findViewById(R.id.Review_Button);
            Review.setVisibility(View.INVISIBLE);


            //Employee
            LinearLayout linearLayout1 = findViewById(R.id.employee_status_fragment4);
            linearLayout1.setVisibility(View.INVISIBLE);

        }else if(nextPosition==5){//Review
            //Call
            LinearLayout linearLayout = findViewById(R.id.Linear_Call);
            linearLayout.setVisibility(View.INVISIBLE);


            //Payment
            Button Payment = findViewById(R.id.Payment_Button);
            Payment.setVisibility(View.INVISIBLE);

            //Review
            Button Review = findViewById(R.id.Review_Button);
            Review.setVisibility(View.VISIBLE);
            Review.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(viewDetails.this,FeedbackPage.class);
                    intent.putExtra("PrimaryKeyID", PrimaryKeyID);
                    startActivity(intent);
                }
            });


            //Employee
            LinearLayout linearLayout1 = findViewById(R.id.employee_status_fragment4);
            linearLayout1.setVisibility(View.INVISIBLE);

        }
        else {
            //Call
            LinearLayout linearLayout = findViewById(R.id.Linear_Call);
            linearLayout.setVisibility(View.INVISIBLE);


            //Employee
            LinearLayout linearLayout1 = findViewById(R.id.employee_status_fragment4);
            linearLayout1.setVisibility(View.INVISIBLE);

            //Payment
            Button Payment = findViewById(R.id.Payment_Button);
            Payment.setVisibility(View.INVISIBLE);

            //Review
            Button Review = findViewById(R.id.Review_Button);
            Review.setVisibility(View.INVISIBLE);


        }
    }


    public void Timeline_Horizontal(int level) {
        RecyclerView recycler = (RecyclerView) findViewById(R.id.recycler_timeline);
        recycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        TimelineAdapter adapter = new TimelineAdapter(HORIZONTAL, getItems(), level);
        recycler.setAdapter(adapter);

    }

    private void callPhoneNumber() {
        try {
            if (Build.VERSION.SDK_INT > 22) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling

                    ActivityCompat.requestPermissions(viewDetails.this, new String[]{Manifest.permission.CALL_PHONE}, 101);

                    return;
                }

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + "0377778888"));
                startActivity(callIntent);

            } else {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + "0377778888"));
                startActivity(callIntent);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions,
                                           @NotNull int[] grantResults) {
        if (requestCode == 101) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callPhoneNumber();
            } else {
                Log.e("b", "Permission not Granted");
            }
        }
    }

    private List<ListItem> getItems() {
        List<ListItem> items = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            ListItem item = new ListItem();
            item.setName("Client number " + i);
            item.setAddress("Some address " + random.nextInt(100));
            items.add(item);
        }

        return items;
    }

    private void initSpinner(int bill, int PrimaryKeyID) {
        Dao dao = database.getInstance(getApplicationContext()).dao();
        workers = dao.getDesiredUser("Worker");

        for (int i = 0; i < workers.size(); i++) {
            TotalUsers t = workers.get(i);
            names.add(t.getName());
            names.size();
        }
        names.add("No-One");

        spinner.setItem(names);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(viewDetails.this, (CharSequence) names.get(position), Toast.LENGTH_SHORT).show();
                if (names.get(position).equals("No-One")) {
                    UserId = -1;
                    dao.UpdateEmployee(-1, PrimaryKeyID);
                    dao.EmployeeSelection(-1, bill);


                } else {
                    TextView textView = findViewById(R.id.EmployeeAssignedID);
                    UserId = workers.get(position).getI();
                    dao.UpdateEmployee(workers.get(position).getI(), PrimaryKeyID);
                    dao.EmployeeSelection(workers.get(position).getI(), bill);
                    textView.setText(dao.EmployeeAssignedName(PrimaryKeyID));

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //Intent intent=Intent(viewDetails.class, Fragment3.getActivity());
    }
}

