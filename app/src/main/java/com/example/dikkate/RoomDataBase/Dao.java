package com.example.dikkate.RoomDataBase;


import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface Dao {
    @Insert
    void EnterData(TotalUsers data);

    @Insert
    void EnterComplaint(Complaint data);


    @Query("Update Complaint set completed=:value  where Bill_No=:bill")
    void Completed(int bill, boolean value);

    @Query("Update Complaint set completed=:value where Bill_No=:bill")
    void UpdateCompleted(boolean value, int bill);

    @Query("SELECT * From Complaint where Submitted_by=:id and completed=:value")
    List<Complaint> ExtractComplaint(int id, boolean value);

    @Query("SELECT * From Complaint where Submitted_by=:id")
    List<Complaint> Extract(int id);

    @Query("SELECT ServiceType FROM TotalUsers where Email=:email")
    int ServiceType(String email);

    @Query("SELECT COUNT(Email) From TotalUsers where Email=:email")
    boolean AlreadyExist(String email);

    @Query("SELECT Password From TotalUsers where Email=:email ")
    String ExtractPassword(String email);

    @Query("SELECT i FROM TotalUsers where Email=:email")
    int UserId(String email);

    @Query("Update Complaint set Assigned=:value where Bill_No=:bill")
    void ChangeAssigned(boolean value, int bill);


    @Query("Select * From TotalUsers where role=:role")
    List<TotalUsers> getDesiredUser(String role);

    @Query("Update Complaint set Employee=:emp where Bill_No=:bill")
    void EmployeeSelection(int emp, int bill);

    @Query("Select assigned From Complaint where Bill_No=:bill")
    boolean Assigned(int bill);

    @Query("Select TotalUsers.Name from TotalUsers left Join Complaint on TotalUsers.i = Complaint.Employee where Bill_No=:bill")
    String EmployeeName(int bill);

    @Query("Select count(Employee) from Complaint where Employee=:id")
    int No_Of_Queries(int id);


    @Update
    void UpdateComplaint(Complaint complaint);

    @Query("select * from COMPLAINT where Bill_No = :bill")
    Complaint GetComplaint(int bill);

    @Query("SELECT Submitted_by from Complaint where Bill_No=:bill")
    int EmployeeId(int bill);

    @Query("Select * from Complaint where Given Is 1 ")
    List<Complaint> getComplaintTotal();



}
