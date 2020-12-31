package com.example.dikkate.RoomDataBase;


import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@androidx.room.Dao
public interface Dao {
    @Insert
    void EnterData(TotalUsers data);

    @Insert
    void EnterComplaint(Complaint data);

    @Query("SELECT * From Complaint")
     List<Complaint> ExtractComplaint();



    @Query("SELECT ServiceType FROM TotalUsers where Email=:email")
    int ServiceType(String email);

    @Delete
    void RemoveData(TotalUsers data);

    @Query("SELECT COUNT(Email) From TotalUsers where Email=:email")
    boolean AlreadyExist(String email);

    @Query("SELECT Password From TotalUsers where Email=:email ")
    String ExtractPassword(String email);

    @Query("SELECT i FROM TotalUsers where Email=:email")
    int UserId(String email);

}
