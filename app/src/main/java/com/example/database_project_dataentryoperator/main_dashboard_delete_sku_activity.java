package com.example.database_project_dataentryoperator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.database_project_dataentryoperator.Fragments.Delete_company_fragment;
import com.example.database_project_dataentryoperator.Fragments.Delete_sku_fragment;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

public class main_dashboard_delete_sku_activity extends AppCompatActivity
{
    //bottom navgation bar
    private SpaceNavigationView navigationView;

    //fragtmetns classes
    private Delete_sku_fragment delete_sku_fragment;
    private Delete_company_fragment delete_company_fragment;


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

        //initializing the delete sku fragment
        delete_sku_fragment=new Delete_sku_fragment();
        delete_company_fragment=new Delete_company_fragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_dashboard_delete_container,delete_sku_fragment).commit();
        navigationView.setSpaceOnClickListener(new SpaceOnClickListener()
        {
            @Override
            public void onCentreButtonClick()
            {
                Toast.makeText(main_dashboard_delete_sku_activity.this,"onCentreButtonClick", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.main_dashboard_delete_container,delete_sku_fragment).commit();
            }

            @Override
            public void onItemClick(int itemIndex, String itemName)
            {
                Toast.makeText(main_dashboard_delete_sku_activity.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
                if(itemIndex==0)
                {

                }
                else
                {
                    if(itemIndex==1)
                    {
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_dashboard_delete_container,delete_company_fragment).commit();
                    }
                }
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName)
            {
                Toast.makeText(main_dashboard_delete_sku_activity.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
