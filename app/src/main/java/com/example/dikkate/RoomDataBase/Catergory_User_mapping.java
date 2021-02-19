package com.example.dikkate.RoomDataBase;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Catergory_User_mapping {
    @PrimaryKey(autoGenerate = true)
    private int i;

    int CategoryID;
    int UserID;
    int level;
    int EmployeeIdAssigned;
    double stars=0;
    boolean completed=false;
    String Review="";

    public String getReview() {
        return Review;
    }

    public void setReview(String review) {
        Review = review;
    }

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getEmployeeIdAssigned() {
        return EmployeeIdAssigned;
    }

    public void setEmployeeIdAssigned(int employeeIdAssigned) {
        EmployeeIdAssigned = employeeIdAssigned;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int categoryID) {
        CategoryID = categoryID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}
