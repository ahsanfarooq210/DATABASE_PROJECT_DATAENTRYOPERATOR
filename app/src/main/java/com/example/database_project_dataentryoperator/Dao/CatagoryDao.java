package com.example.database_project_dataentryoperator.Dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.database_project_dataentryoperator.Entity.CatagoryEntity;

import java.util.List;

@Dao
public interface CatagoryDao
{
  @Insert
    void insert (CatagoryEntity catagoryEntity);
  @Update
    void update (CatagoryEntity catagoryEntity);

  @Delete
    void delete (CatagoryEntity catagoryEntity);

    @Query("SELECT * FROM catagory")
    List<CatagoryEntity> getAllCatagories();
}
