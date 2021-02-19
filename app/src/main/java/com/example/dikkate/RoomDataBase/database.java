package com.example.dikkate.RoomDataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TotalUsers.class,Complaint.class,Roles.class,Catergory_User_mapping.class,Category.class},version = 6)
public abstract class database extends RoomDatabase {
    public static database instance;
    abstract public Dao dao();
    public static database getInstance(Context context){
        if(instance==null){
            instance=Room.databaseBuilder(context,database.class,"data.db").allowMainThreadQueries()
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
