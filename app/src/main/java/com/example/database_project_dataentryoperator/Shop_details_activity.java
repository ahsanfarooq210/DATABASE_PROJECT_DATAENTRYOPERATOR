package com.example.database_project_dataentryoperator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

public class Shop_details_activity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_details_activity);
        LatLng objLatLng=getIntent().getExtras().getParcelable("latlan");

        Toast.makeText(this, "latitude= "+objLatLng.latitude+"  longitude= "+objLatLng.longitude, Toast.LENGTH_SHORT).show();
    }
}
