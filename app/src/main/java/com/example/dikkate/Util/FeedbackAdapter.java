package com.example.dikkate.Util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dikkate.R;
import com.example.dikkate.RoomDataBase.Catergory_User_mapping;
import com.example.dikkate.RoomDataBase.Dao;

import java.util.List;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.TasksViewHolder> {
    Context mCtx;
    List<Catergory_User_mapping> feedbacks;
    Dao dao;

    public FeedbackAdapter(Context mCtx, List<Catergory_User_mapping> feedbacks, Dao dao) {
        this.mCtx = mCtx;
        this.feedbacks = feedbacks;
        this.dao = dao;
    }

    @NonNull
    @Override
    public TasksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from((mCtx)).inflate(R.layout.feedback_recycler, parent, false);
        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TasksViewHolder holder, int position) {
        if (feedbacks.get(position).getStars() != 0) {
            holder.Name.setText(dao.Completefeedback(feedbacks.get(position).getI()));
            holder.Address.setText(dao.CompletefeedbackAddress(feedbacks.get(position).getI()) + ",");
            holder.Star.setText(String.valueOf(feedbacks.get(position).getStars()));
            holder.Review.setText(feedbacks.get(position).getReview());
        }

    }

    @Override
    public int getItemCount() {
        return feedbacks.size();
    }

    class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView ProfilePicture;
        TextView Name, Address, Date, Star, Review;

        public TasksViewHolder(View itemView) {
            super(itemView);
            ProfilePicture = itemView.findViewById(R.id.ProfilePictureFeedback);
            Name = itemView.findViewById(R.id.Name_feedback);
            Address = itemView.findViewById(R.id.Address_Feedback);
            Date = itemView.findViewById(R.id.dateFeedback);
            Star = itemView.findViewById(R.id.star_individual_feedback);
            Review = itemView.findViewById(R.id.review_text_feedback);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //Toast.makeText(mCtx, "CLICKED WITH TEXT", Toast.LENGTH_LONG).show();
        }


    }

}
