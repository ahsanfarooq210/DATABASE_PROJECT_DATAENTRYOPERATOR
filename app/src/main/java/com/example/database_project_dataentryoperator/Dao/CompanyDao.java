package com.example.database_project_dataentryoperator.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.database_project_dataentryoperator.Entity.CatagoryEntity;
import com.example.database_project_dataentryoperator.Entity.CompanyEntity;

import java.util.List;

@Dao
public interface CompanyDao
{
    @Insert
    void insert(CompanyEntity companyEntity);

    @Update
    void update(CompanyEntity companyEntity);

    @Delete
    void delete(CompanyEntity companyEntity);

    @Query("SELECT * FROM company")
    List<CompanyEntity> getAllCompany();

    @Query("SELECT company_name FROM company WHERE :id=id")
    List<String> getName(int id);

}
