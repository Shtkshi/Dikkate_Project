package com.example.dikkate.Fragment;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dikkate.Activity.CartPage1;
import com.example.dikkate.R;
import com.example.dikkate.RoomDataBase.Catergory_User_mapping;
import com.example.dikkate.RoomDataBase.Dao;
import com.example.dikkate.RoomDataBase.database;
import com.example.dikkate.Util.CartAdapter;

import java.util.List;
import java.util.Random;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

import static android.content.Context.MODE_PRIVATE;

public class Fragment4 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment4,container,false);
    }
    int UserID;
    Dao dao;
    List<Catergory_User_mapping>arr;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //CartItems
        SharedPreferences sharedPreferences=getContext().getSharedPreferences("email",MODE_PRIVATE);
        UserID=sharedPreferences.getInt("UserId",-1);

        dao= database.getInstance(getContext()).dao();
        arr=dao.CartItemsLevelPlus(UserID,1);


        //RecyclerView
        RecyclerView recyclerView=view.findViewById(R.id.Recycler_Cart_fragment4);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        CartAdapter cartAdapter=new CartAdapter(getContext(),arr,dao,UserID);
        recyclerView.setAdapter(cartAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }
}