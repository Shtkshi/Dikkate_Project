package com.example.dikkate.Util;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dikkate.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AllCategoriesAdapter extends RecyclerView.Adapter<AllCategoriesAdapter.TasksViewHolder> {

   ArrayList<String> Name;
   ArrayList<Integer>image;
   ArrayList<Class>classes;
   Context mCtx;
    public AllCategoriesAdapter(Context mCtx, ArrayList<String> Name, ArrayList<Integer> image, ArrayList<Class> classes) {
        this.mCtx = mCtx;
        this.Name = Name;
        this.image = image;
        this.classes = classes;
    }


    @NonNull
    @Override
    public TasksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.allcategoriesrecyclerview, parent, false);
        return new TasksViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull TasksViewHolder holder, int position) {
        holder.Name_Category.setText(Name.get(position));
        holder.Image_Category.setImageResource(image.get(position));
        holder.All.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mCtx,classes.get(position));
                mCtx.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return Name.size();
    }

    class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView Name_Category;
        ImageView Image_Category;
        LinearLayout All;


        public TasksViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            Name_Category= itemView.findViewById(R.id.Category_name);
            Image_Category=itemView.findViewById(R.id.image_category);
            All=itemView.findViewById(R.id.CategoryAllLinearView);

        }

        @Override
        public void onClick(View view) {
        }

    }


}