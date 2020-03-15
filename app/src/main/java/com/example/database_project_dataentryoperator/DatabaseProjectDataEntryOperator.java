package com.example.database_project_dataentryoperator;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class DatabaseProjectDataEntryOperator extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
