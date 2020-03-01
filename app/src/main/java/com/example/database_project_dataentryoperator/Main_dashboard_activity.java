package com.example.database_project_dataentryoperator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

public class Main_dashboard_activity extends AppCompatActivity
{

    SpaceNavigationView navigationView;

    int error=1000;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard_activity);

        navigationView=findViewById(R.id.space);

        navigationView.initWithSaveInstanceState(savedInstanceState);

        navigationView.addSpaceItem(new SpaceItem("", R.drawable.icons8_add_user_group_woman_man_32px));
        navigationView.addSpaceItem(new SpaceItem("", R.drawable.icons8_add_pie_chart_report_32px));

        navigationView.setCentreButtonSelectable(true);
        navigationView.setCentreButtonSelected();


        navigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                Toast.makeText(Main_dashboard_activity.this,"onCentreButtonClick", Toast.LENGTH_SHORT).show();
               // getSupportFragmentManager().beginTransaction().add(R.id.main_dashboard_container,homescreenfragment).commit();
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {

                Toast.makeText(Main_dashboard_activity.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
                if(itemIndex==0&&error!=itemIndex)
                {
                 //   getSupportFragmentManager().beginTransaction().add(R.id.main_dashboard_container,addSamesmanFragment).commit();
                }
                error=itemIndex;

            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                Toast.makeText(Main_dashboard_activity.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
