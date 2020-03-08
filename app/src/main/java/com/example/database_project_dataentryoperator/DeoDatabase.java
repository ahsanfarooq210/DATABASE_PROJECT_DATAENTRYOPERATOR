package com.example.database_project_dataentryoperator;

import android.content.Context;

import androidx.room.RoomDatabase;

import com.example.database_project_dataentryoperator.Dao.CatagoryDao;
import com.example.database_project_dataentryoperator.Dao.CompanyDao;
import com.example.database_project_dataentryoperator.Dao.ProductDao;

import static androidx.room.Room.databaseBuilder;

public abstract class DeoDatabase extends RoomDatabase
{

    private static final String DB_NAME="mobile_database";
    private static DeoDatabase instance;

    public static synchronized DeoDatabase getInstance(Context context)
    {

        if(instance==null)
        {

            instance= databaseBuilder(context,DeoDatabase.class,DB_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }
        return instance;
    }

    public abstract CatagoryDao getCatagoryDao();
    public abstract CompanyDao  getCompanyDao();
    public abstract ProductDao getProducrDao();

}
