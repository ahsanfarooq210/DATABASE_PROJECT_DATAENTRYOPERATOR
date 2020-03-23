package com.example.database_project_dataentryoperator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;

public class main_dashboard_delete_sku extends AppCompatActivity
{
    SpaceNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard_delete_sku);

        navigationView=findViewById(R.id.space);
        navigationView.initWithSaveInstanceState(savedInstanceState);
        //navigationView.addSpaceItem(new SpaceItem("",R.drawable.icons8_delete_file_64px_2));
        navigationView.addSpaceItem(new SpaceItem("", R.drawable.icons8_delete_document_64px));
        navigationView.setCentreButtonIcon(R.drawable.icons8_box_26px_1);
        navigationView.addSpaceItem(new SpaceItem("", R.drawable.icons8_delete_file_64px_2));
        // navigationView.addSpaceItem(new SpaceItem("",R.drawable.icons8_delete_document_64px));
        navigationView.setCentreButtonSelectable(true);
    }
}
