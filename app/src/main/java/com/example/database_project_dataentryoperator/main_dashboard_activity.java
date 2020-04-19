package com.example.database_project_dataentryoperator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class main_dashboard_activity extends AppCompatActivity
{
    //employeee name
    TextView nameplate;
    //firebase user
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    //dasboard name plate
    private TextView dashboard_nameplate;
    //scroll view
    private ScrollView dashboardScrollView;
    //shpash screen handler
    private Handler handler=new Handler();
    Runnable runnable=new Runnable()
    {
        @Override
        public void run()
        {
            dashboard_nameplate.setVisibility(View.VISIBLE);
            dashboardScrollView.setVisibility(View.VISIBLE);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard_activity);

        //maindashboard name plate
        nameplate=findViewById(R.id.dashboard_name_plate);

        //getting the user
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser!=null)
        nameplate.setText(firebaseUser.getEmail());
        //initializing namplate and scroll view
        dashboard_nameplate=findViewById(R.id.dashboard_name_plate);
        dashboardScrollView=findViewById(R.id.dashboard_scroll_view);
        //splash screen
        handler.postDelayed(runnable,500);

    }

    public void addSku(View view)
    {
        Intent intent=new Intent(this,Main_dashboard_adding_sku_activity.class);
        startActivity(intent);
    }

    public void deleteSku(View view)
    {
        Intent intent=new Intent(this,main_dashboard_delete_sku_activity.class);
        startActivity(intent);
    }
    public void  showSku(View view)
    {
        Intent intent=new Intent(this,show_sku_rv_activity.class);
        startActivity(intent);
    }
    public void addSop(View view)
    {
        Intent intent=new Intent(this,add_shops_activity.class);
        startActivity(intent);
    }
    public void showShop(View view)
    {
        Intent intent=new Intent(this,show_shop_activity.class);
        startActivity(intent);
    }


}
