package com.example.dikkate.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dikkate.RoomDataBase.Catergory_User_mapping;
import com.example.dikkate.RoomDataBase.Complaint;
import com.example.dikkate.RoomDataBase.Dao;
import com.example.dikkate.RoomDataBase.database;

import com.example.dikkate.R;

import com.example.dikkate.Util.CartAdapter;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class Fragment5 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment5,container,false);
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
        arr=dao.CartEmployee(UserID,3);


        //RecyclerView
        RecyclerView recyclerView=view.findViewById(R.id.Recycler_Cart_fragment5);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        CartAdapter cartAdapter=new CartAdapter(getContext(),arr,dao,UserID);
        recyclerView.setAdapter(cartAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


    }

}