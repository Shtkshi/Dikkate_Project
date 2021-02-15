package com.example.dikkate.Util;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dikkate.Activity.viewDetails;
import com.example.dikkate.R;

import java.util.List;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.TasksViewHolder> {

    private Context mCtx;
    int arr[];

    public CartAdapter(Context mCtx, int arr[]) {
        this.mCtx=mCtx;
        this.arr=arr;
    }


    @NonNull
    @Override
    public TasksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recycler_cart1, parent, false);
        return new TasksViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull TasksViewHolder holder, int position) {
        String a=String.valueOf(arr[(position)]);
        holder.ProblemType.setText(a);
        holder.ViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mCtx, viewDetails.class);
                mCtx.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return 7;
    }

    class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;
        TextView ProblemType,Date,Time,ViewDetails;

        public TasksViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            image=itemView.findViewById(R.id.recycler_image_cart1);
            ProblemType=itemView.findViewById(R.id.recycler_ProblemType_cart1);
            Date=itemView.findViewById(R.id.recycler_date_cart1);
            Time=itemView.findViewById(R.id.recycler_time_cart1);
            ViewDetails=itemView.findViewById(R.id.recycler_ViewDetails_cart1);

        }

        @Override
        public void onClick(View view) {
            Toast.makeText(mCtx, "CLICKED WITH TEXT" , Toast.LENGTH_LONG).show();
        }

    }

}