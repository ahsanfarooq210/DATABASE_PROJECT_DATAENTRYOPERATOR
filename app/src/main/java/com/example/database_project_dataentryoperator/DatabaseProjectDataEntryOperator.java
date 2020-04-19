package com.example.database_project_dataentryoperator;

import android.app.Application;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseProjectDataEntryOperator extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        FirebaseAuth firebaseAuth =FirebaseAuth.getInstance();
        FirebaseUser firebaseUser =firebaseAuth.getCurrentUser();

        if(firebaseUser!=null)
        {
            Intent intent=new Intent(DatabaseProjectDataEntryOperator.this,main_dashboard_activity.class);
            startActivity(intent);
        }
        else
        {
            Intent intent=new Intent(DatabaseProjectDataEntryOperator.this,main_dashboard_activity.class);
            startActivity(intent);
        }
    }
}
