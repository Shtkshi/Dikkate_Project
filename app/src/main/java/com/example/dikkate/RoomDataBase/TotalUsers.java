package com.example.dikkate.RoomDataBase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TotalUsers {
    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    @PrimaryKey(autoGenerate = true)
    private int i=0;
    @ColumnInfo(name = "Name")
    private String Name;
    @ColumnInfo(name = "tele no")
    private String PhoneNo;
    @ColumnInfo(name="Address")
    private String Address;
    @ColumnInfo(name="Date of Birth")
    private String dob;
    @ColumnInfo(name="Email")
    private String Email;
    @ColumnInfo(name = "Password")
    private String Password;
    @ColumnInfo(name = "role")
    private String Role;

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @ColumnInfo(name="ServiceType")
    private int ServiceID;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getServiceID() {
        return ServiceID;
    }

    public void setServiceID(int ServiceID) {
        this.ServiceID = ServiceID;
    }
}
