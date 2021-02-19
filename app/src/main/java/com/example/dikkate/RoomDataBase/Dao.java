package com.example.dikkate.RoomDataBase;


import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface Dao {
    //Total Users:Admin,Worker,User
    @Insert
    void EnterData(TotalUsers data);

    @Query("SELECT ServiceType FROM TotalUsers where Email=:email")
    int ServiceType(String email);

    @Query("SELECT COUNT(Email) From TotalUsers where Email=:email")
    boolean AlreadyExist(String email);

    @Query("SELECT Password From TotalUsers where Email=:email ")
    String ExtractPassword(String email);

    @Query("SELECT i FROM TotalUsers where Email=:email")
    int UserId(String email);

    @Query("Select * From TotalUsers where role=:role")
    List<TotalUsers> getDesiredUser(String role);

    @Query("Select TotalUsers.Name from TotalUsers left Join Complaint on TotalUsers.i = Complaint.Employee where Bill_No=:bill")
    String EmployeeName(int bill);


    //Complaint Table
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

    @Query("Update Complaint set Assigned=:value where Bill_No=:bill")
    void ChangeAssigned(boolean value, int bill);

    @Query("Update Complaint set Employee=:emp where Bill_No=:bill")
    void EmployeeSelection(int emp, int bill);

    @Query("Select assigned From Complaint where Bill_No=:bill")
    boolean Assigned(int bill);

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




    //Categories
    @Insert
    void EnterCategories(Category category);





    //Mapping User-Category
    @Insert
    void EnterMappingUserCategories(Catergory_User_mapping catergory_user_mapping);

    @Query("Update Catergory_User_mapping set EmployeeIdAssigned=:CustomerID where i=:primaryID")
    void UpdateEmployee(int CustomerID, int primaryID);

    @Query("SELECT * FROM Catergory_User_mapping WHERE UserID=:UserID AND level<>:Level")
    List<Catergory_User_mapping> CartItemsLevelPlus(int UserID, int Level);

    @Query("SELECT * FROM Catergory_User_mapping WHERE UserID=:UserID AND level=:level")
    List<Catergory_User_mapping> CartItems(int UserID, int level);

    @Query("SELECT COUNT(CategoryID) FROM Catergory_User_mapping Where UserID=:UserID AND level=:level")
    int NoOfOrders(int UserID, int level);

    @Query("Update Catergory_User_mapping set level=:level_final where i=:UserID And level=:level_initial")
    void UpdateCart(int level_final, int level_initial, int UserID);

    @Query("DELETE FROM Catergory_User_mapping WHERE i=:i")
    void RemoveCartItemsBeforeCall(int i);


    @Query("Select TotalUsers.Name from TotalUsers left Join Catergory_User_mapping on TotalUsers.i =EmployeeIdAssigned where Catergory_User_mapping.i=:PrimaryKeyID")
    String EmployeeAssignedName(int PrimaryKeyID);

    @Query("SELECT level from Catergory_User_mapping where i=:PrimaryKeyID")
    int GetLevel(int PrimaryKeyID);

    @Query("UPDATE Catergory_User_mapping SET stars=:stars , Review=:review where i=:PrimaryKeyID")
    void ReviewStars(double stars, int PrimaryKeyID,String review);

    @Query("Select stars from Catergory_User_mapping where i=:PrimaryKeyID")
    float  StarFill(int PrimaryKeyID);


}
