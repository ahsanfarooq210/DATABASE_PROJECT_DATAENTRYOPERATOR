package com.example.database_project_dataentryoperator.SKUActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.database_project_dataentryoperator.Fragments.Adding_sku_fragment;
import com.example.database_project_dataentryoperator.Fragments.Company_fraagment;
import com.example.database_project_dataentryoperator.Fragments.Catagory_fragment;
import com.example.database_project_dataentryoperator.R;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

public class Main_dashboard_adding_sku_activity extends AppCompatActivity
{

    SpaceNavigationView navigationView;


    Catagory_fragment sku_frament;
    Company_fraagment company_fraagment;
    Adding_sku_fragment adding_sku_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sku_main_dashboard);
        //navigation view
        navigationView=findViewById(R.id.space);
        navigationView.initWithSaveInstanceState(savedInstanceState);
        //navigationView.addSpaceItem(new SpaceItem("",R.drawable.icons8_delete_file_64px_2));
        navigationView.addSpaceItem(new SpaceItem("", R.drawable.icons8_sorting_64px));
        navigationView.setCentreButtonIcon(R.drawable.icons8_product_documents_64px_1);
        navigationView.addSpaceItem(new SpaceItem("", R.drawable.icons8_new_company_80px));
       // navigationView.addSpaceItem(new SpaceItem("",R.drawable.icons8_delete_document_64px));
        navigationView.setCentreButtonSelectable(true);
        navigationView.setCentreButtonSelected();

        //fragment
        sku_frament=new Catagory_fragment();

         company_fraagment=new Company_fraagment();
        adding_sku_fragment=new Adding_sku_fragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_dashboard_container,adding_sku_fragment).commit();


        navigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
               // Toast.makeText(Main_dashboard_adding_sku_activity.this,"onCentreButtonClick", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.main_dashboard_container,adding_sku_fragment).commit();
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {

                //
              //  Toast.makeText(Main_dashboard_adding_sku_activity.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
                if(itemIndex==0)
                {

                   getSupportFragmentManager().beginTransaction().replace(R.id.main_dashboard_container,sku_frament).commit();

                }
                else
                {
                    if(itemIndex==1)
                    {

                        getSupportFragmentManager().beginTransaction().replace(R.id.main_dashboard_container,company_fraagment).commit();

                    }
                }


            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                Toast.makeText(Main_dashboard_adding_sku_activity.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();

            }
        });
    }

}
