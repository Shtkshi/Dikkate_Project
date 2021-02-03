package com.example.dikkate.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;
import com.example.dikkate.Fragment.Fragment1;
import com.example.dikkate.R;
import com.example.dikkate.RoomDataBase.Dao;
import com.example.dikkate.RoomDataBase.TotalUsers;
import com.example.dikkate.RoomDataBase.database;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class dialog extends AppCompatActivity {
    Button Save, Done;
    TextView Status_text, problem, Completed_status;
    SmartMaterialSpinner<String> spinner;
    int UserId = -1;
    List<TotalUsers> workers;
    ArrayList<String> names = new ArrayList<>();

    String name;
    int count = 0;
    int bill = 0;
    int Completed = 0;

    public void Initialize() {
        spinner = findViewById(R.id.SearchableSpinner);
        //Assign_Button = findViewById(R.id.employee_status_button);
        Status_text = findViewById(R.id.status_textview);
        problem = findViewById(R.id.problem_edittext);
        Save = findViewById(R.id.save_details);
        Done = findViewById(R.id.done);
        Completed_status = findViewById(R.id.completed);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        Initialize();
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            bill = extras.getInt("Complaint No");
            name = extras.getString("Complaint Name");
            SharedPreferences sharedPreferences = getSharedPreferences("Bill", Context.MODE_PRIVATE);
            sharedPreferences.edit().putInt("bill", bill).apply();

        }
        initSpinner(bill);
        Dao dao = database.getInstance(getApplicationContext()).dao();
        problem.setText(name);

        if (dao.Assigned(bill)) {

            Status_text.setText("Assigned");
            String s = dao.EmployeeName(bill);
            spinner.setHint(s);
            count = 0;


        } else {
            Status_text.setText("Not Assigned");
            count = 1;
        }
        Save.setOnClickListener(v -> {
            dao.ChangeAssigned(count != 1, bill);
            if(count!=1){
                Status_text.setText("Assigned");
            }
            Intent intent=new Intent(dialog.this,MainUI.class);
            startActivity(intent);
            finish();

        });
        Done.setOnClickListener(v -> {
            if(!Status_text.equals("Assigned")) {
                Completed = 1;
                Completed_status.setText("Completed");
                dao.Completed(bill, true);
                Intent intent = new Intent(dialog.this, Fragment1.class);
                intent.putExtra("Completed", true);
                setResult(RESULT_OK, intent);
                finish();
            }
            else{
                Toast.makeText(this, "Employee Required", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void initSpinner(int bill) {
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
                Toast.makeText(dialog.this, (CharSequence) names.get(position), Toast.LENGTH_SHORT).show();
                if (names.get(position).equals("No-One")) {
                    UserId = -1;
                    count = 1;
                    dao.EmployeeSelection(-1, bill);


                } else {
                    UserId = workers.get(position).getI();

                    count = 0;
                    dao.EmployeeSelection(workers.get(position).getI(), bill);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    @Override
    public void finish() {
        Intent returnIntent = new Intent();
        //returnIntent.putExtra("passed_item", itemYouJustCreated);
        // setResult(RESULT_OK);
        setResult(RESULT_OK, returnIntent); //By not passing the intent in the result, the calling activity will get null data.
        super.finish();
    }
}