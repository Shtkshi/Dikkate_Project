package com.example.dikkate.RoomDataBase;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Category {
    @PrimaryKey(autoGenerate = true)
    private int CategoryId;

    String CategoryName;

    int CatergoryGivenId;

    public int getCatergoryGivenId() {
        return CatergoryGivenId;
    }

    public void setCatergoryGivenId(int catergoryGivenId) {
        CatergoryGivenId = catergoryGivenId;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }
}
