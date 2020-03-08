package com.example.database_project_dataentryoperator.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.database_project_dataentryoperator.Entity.ProductEntity;

import java.util.List;

@Dao
public interface ProductDao
{
    @Insert
    void insert(ProductEntity productEntity);

    @Update
    void update(ProductEntity productEntity);

    @Delete
    void delete(ProductEntity productEntity);

    @Query("SELECT * FROM product")
    List<ProductEntity> getAllProducts();

    @Query("SELECT product_name FROM product")
    List<String> getAllProductNames();

    @Query("SELECT product_name FROM product WHERE :id=id")
    List<String> getName(int id);

}
