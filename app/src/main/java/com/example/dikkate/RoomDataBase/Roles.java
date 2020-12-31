package com.example.dikkate.RoomDataBase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;

@Entity
public class Roles {
    @PrimaryKey
    private int RolesId;
    @ColumnInfo(name = "Role Names")
    private String name;

    public int getRolesId() {
        return RolesId;
    }

    public void setRolesId(int rolesId) {
        RolesId = rolesId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
