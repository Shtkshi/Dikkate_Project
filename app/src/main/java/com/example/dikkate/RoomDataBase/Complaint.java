package com.example.dikkate.RoomDataBase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity(foreignKeys = @ForeignKey(entity = TotalUsers.class,
        parentColumns = "i",
        childColumns = "Submitted By"))
public class Complaint {
    @PrimaryKey(autoGenerate = true)
    private int Bill_No;
    @ColumnInfo(name = "Query Names")
    private String query;
    @ColumnInfo(name = "Submitted By")
    private int Submitted_by;
    /*@ColumnInfo(name = "Employee Id")
    private int Employee;
    @ColumnInfo(name = "Date Assigned")
    private String date;*/
    @ColumnInfo(name = "Assigned")
    private boolean assigned = false;

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

    /*public int getEmployee() {
        return Employee;
    }

    public void setEmployee(int employee) {
        Employee = employee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }*/

    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }
}
