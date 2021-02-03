package com.example.dikkate.Util;


import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dikkate.Activity.FeedbackPage;
import com.example.dikkate.Activity.dialog;
import com.example.dikkate.R;
import com.example.dikkate.RoomDataBase.Complaint;
import com.example.dikkate.RoomDataBase.Dao;

import java.util.List;

import static com.example.dikkate.Util.Constants.REQUEST_CODE;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.TasksViewHolder> {
    private Fragment mCtx;
    private List<Complaint> taskList;
    private Dao dao;
    int UserId;

    public CustomAdapter(Fragment mCtx, List<Complaint> taskList, Dao dao, int UserId) {
        this.mCtx = mCtx;
        this.UserId = UserId;
        this.taskList = taskList;
        this.dao = dao;
    }

    @NonNull
    @Override
    public TasksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx.getContext()).inflate(R.layout.recycler_view, parent, false);
        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TasksViewHolder holder, int position) {
        Complaint t = taskList.get(position);
        holder.text.setText(t.getQuery());
        holder.change.setOnClickListener(v -> {
            Intent intent = new Intent(mCtx.getActivity(), dialog.class);
            intent.putExtra("Complaint No", t.getBill_No());
            intent.putExtra("Complaint Name", t.getQuery());
            String s = t.getQuery();
            Log.d(t.getQuery(), "complaint ");
            mCtx.startActivityForResult(intent, REQUEST_CODE);
        });
        holder.feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx.getContext(), FeedbackPage.class);
                intent.putExtra("Bill No", t.getBill_No());
                intent.putExtra("User Id", t.getEmployee());
                mCtx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView text;
        Button change, feedback;


        public TasksViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.query_textview);
            change = itemView.findViewById(R.id.details_button);
            feedback = itemView.findViewById(R.id.Feedback);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(mCtx.getContext(), "CLICKED WITH TEXT" + text, Toast.LENGTH_LONG).show();
        }

    }


}



