package com.example.dikkate.Util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dikkate.R;
import com.example.dikkate.RoomDataBase.Complaint;
import com.example.dikkate.RoomDataBase.Dao;

import java.util.List;

import at.markushi.ui.CircleButton;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.TasksViewHolder> {
    Context mCtx;
    List<Complaint> feedbacks, temp;
    Dao dao;
    int Bill_No;

    public FeedbackAdapter(Context mCtx, List<Complaint> feedbacks, Dao dao, int Bill_no) {
        this.mCtx = mCtx;
        this.feedbacks = feedbacks;
        this.dao = dao;
        this.Bill_No = Bill_no;
    }

    @NonNull
    @Override
    public TasksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from((mCtx)).inflate(R.layout.feedback_recyclerview, parent, false);
        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TasksViewHolder holder, int position) {
        Complaint feedback = feedbacks.get(position);
        holder.WorkId.setText(Integer.toString(feedback.getEmployee()));
        holder.Feedback.setText(feedback.getFeedback_Text());
        holder.BillNo.setText(Integer.toString(feedback.getBill_No()));
        holder.Star.setText(Float.toString(feedback.getStar()));
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedback.setGiven(false);
                dao.UpdateComplaint(feedback);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return feedbacks.size();
    }

    class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView BillNo, Star, Feedback, WorkId;
        CircleButton delete;


        public TasksViewHolder(View itemView) {
            super(itemView);
            BillNo = itemView.findViewById(R.id.F_BillNo);
            Star = itemView.findViewById(R.id.F_Star);
            Feedback = itemView.findViewById(R.id.F_Feedback);
            WorkId = itemView.findViewById(R.id.F_UserId);
            delete = itemView.findViewById(R.id.cancel);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(mCtx, "CLICKED WITH TEXT", Toast.LENGTH_LONG).show();
        }


    }

}
