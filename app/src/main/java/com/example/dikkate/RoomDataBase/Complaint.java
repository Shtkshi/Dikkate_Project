package com.example.dikkate.RoomDataBase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity(foreignKeys = @ForeignKey(entity = TotalUsers.class,
        parentColumns = "i",
        childColumns = "Submitted_by"))
public class Complaint {
    @PrimaryKey(autoGenerate = true)
    private int Bill_No;
    @ColumnInfo(name = "Query Names")
    private String query;
    private int Submitted_by;
    private boolean assigned = false;
    private int Employee = -1;
    private boolean completed = false;
    private String Feedback = "Feedback";
    private float Star = 0;
    private String Feedback_Text = "Feedback";
    private boolean Given = false;


    public float getStar() {
        return Star;
    }

    public String getFeedback_Text() {
        return Feedback_Text;
    }

    public void setFeedback_Text(String feedback_Text) {
        Feedback_Text = feedback_Text;
    }

    public boolean isGiven() {
        return Given;
    }

    public void setGiven(boolean given) {
        Given = given;
    }

    public void setStar(float star) {
        Star = star;
    }

    public String getFeedback() {
        return Feedback;
    }

    public void setFeedback(String feedback) {
        Feedback = feedback;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getEmployee() {
        return Employee;
    }

    public void setEmployee(int employee) {
        Employee = employee;
    }

    public int getBill_No() {
        return Bill_No;
    }

    public void setBill_No(int bill_No) {
        Bill_No = bill_No;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getSubmitted_by() {
        return Submitted_by;
    }

    public void setSubmitted_by(int submitted_by) {
        Submitted_by = submitted_by;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }
}
