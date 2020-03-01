package com.example.database_project_dataentryoperator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.database_project_dataentryoperator.Fragments.Company_fraagment;
import com.example.database_project_dataentryoperator.Fragments.Sku_frament;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

public class Main_dashboard_activity extends AppCompatActivity
{

    SpaceNavigationView navigationView;

    int error=1000;
    Sku_frament sku_frament;
    Company_fraagment company_fraagment;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard_activity);

        navigationView=findViewById(R.id.space);

        navigationView.initWithSaveInstanceState(savedInstanceState);

        navigationView.addSpaceItem(new SpaceItem("", R.drawable.icons8_sorting_64px));
        navigationView.addSpaceItem(new SpaceItem("", R.drawable.icons8_new_company_80px));

        navigationView.setCentreButtonSelectable(true);
        navigationView.setCentreButtonSelected();
        sku_frament=new Sku_frament();
        //getSupportFragmentManager().beginTransaction().add(R.id.main_dashboard_container,sku_frament).commit();
         company_fraagment=new Company_fraagment();


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
                    getSupportFragmentManager().beginTransaction().add(R.id.main_dashboard_container,sku_frament).commit();
                }
                else
                {
                    if(itemIndex==1)
                    {
                        getSupportFragmentManager().beginTransaction().add(R.id.main_dashboard_container,company_fraagment).commit();
                    }
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
