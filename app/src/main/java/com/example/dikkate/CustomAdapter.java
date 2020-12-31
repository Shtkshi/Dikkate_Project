package com.example.dikkate;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dikkate.RoomDataBase.Complaint;

import java.util.ArrayList;
import java.util.List;


/*
class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {


    private List<Complaint>LocalPeople;
    private  String[] arr;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final Button button;


        public ViewHolder(View view) {
            super(view);
            button=(Button) view.findViewById(R.id.details_button);
            textView = (TextView) view.findViewById(R.id.query_textview);
        }

        public Button getButton() {
            return button;
        }

        public TextView getTextView() {
            return textView;
        }
    }


    public CustomAdapter(List<Complaint>people,String[] arr) {
        LocalPeople = people;
        this.arr=arr;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view, viewGroup, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getTextView().setText(arr[position]);
        Log.d(String.valueOf(arr.length), "size");
        */
/*viewHolder.getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateDialog(v);
            }
        });*/
/*

    }


    @Override
    public int getItemCount() {

        return arr.length;
    }
int count=0;
    public void CreateDialog(View view) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.add_complaint, null, false);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        Dao dao = database.getInstance(view.getContext()).dao();
        Complaint user = new Complaint();
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        String email = pref.getString("email", "");
        user.setSubmitted_by(dao.UserId(email));
        dialogView.findViewById(R.id.status_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count % 2 == 0) {
                    user.setAssigned(true);
                    TextView t = dialogView.findViewById(R.id.status_textview);
                    t.setText("Assigned");

                    count = 1;
                } else {
                    TextView t = dialogView.findViewById(R.id.status_textview);
                    user.setAssigned(false);
                    t.setText("Not Assigned");
                    count = 0;
                }
            }
        });
        dialogView.findViewById(R.id.save_details).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
//        dao.EnterComplaint(user);
        alertDialog.show();
    }
}
*/
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.TasksViewHolder> {
    private Context mCtx;
    private List<Complaint> taskList;

    public CustomAdapter(Context mCtx, List<Complaint> taskList) {
        this.mCtx = mCtx;
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public TasksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recycler_view, parent, false);
        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TasksViewHolder holder, int position) {
        Complaint t = taskList.get(position);
        holder.text.setText(t.getQuery());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView text;
        Button change;

        public TasksViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.query_textview);
            change = itemView.findViewById(R.id.details_button);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(mCtx, "CLICKED WITH TEXT" + text, Toast.LENGTH_LONG).show();
            /*Complaint task = taskList.get(getAdapterPosition());

            Intent intent = new Intent(mCtx, CompaniesDetails.class);
            intent.putExtra("companyId", task.getCompanyID());

            mCtx.startActivity(intent);*/
        }
    }
}


/*
public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.viewholder> {

    List<Complaint> people;
    Layout Lay;


    public recyclerAdapter(List<Complaint> people) {
        this.people = people;

    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_view, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder( @NonNull viewholder holder, int position) {
        String t = people.get(position).getQuery();
        Log.d(String.valueOf(people.size()), "size");
        holder.getText().setText("asdfb");
        holder.getDetails().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateDialog(v);

            }
        });
    }


    @Override
    public int getItemCount() {
        return people.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        private final TextView text;
        private final Button details;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.query_textview);
            details = itemView.findViewById(R.id.details_button);
        }

        public TextView getText() {
            return text;
        }

        public Button getDetails() {
            return details;
        }
    }

    int count = 0;

    public void CreateDialog(View view) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.add_complaint, null, false);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        Dao dao = database.getInstance(view.getContext()).dao();
        Complaint user = new Complaint();
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        String email = pref.getString("email", "");
        user.setSubmitted_by(dao.UserId(email));
        dialogView.findViewById(R.id.status_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count % 2 == 0) {
                    user.setAssigned(true);
                    TextView t = dialogView.findViewById(R.id.status_textview);
                    t.setText("Assigned");

                    count = 1;
                } else {
                    TextView t = dialogView.findViewById(R.id.status_textview);
                    user.setAssigned(false);
                    t.setText("Not Assigned");
                    count = 0;
                }
            }
        });
        dialogView.findViewById(R.id.save_details).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
//        dao.EnterComplaint(user);
        alertDialog.show();
    }
}
*/
