package com.example.database_project_dataentryoperator;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Shop_details_activity extends AppCompatActivity
{
    private Button save;
    private EditText ownerName,ownerCnic,shopName,ownerMobile;
    private Geocoder geo;
    private List<Address> address;
    private String stradress;
    private DatabaseReference shopreference;
    private LatLng latLng;
    private ProgressBar progressBar;
    private Handler progressBarh=new Handler();
    private Runnable runnable1=new Runnable()
    {
        @Override
        public void run()
        {

            progressBar.setVisibility(View.GONE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_details_activity);
        progressBar=findViewById(R.id.shop_details_my_progress_bar);
        latLng=getIntent().getExtras().getParcelable("latlan");
        geo = new Geocoder(Shop_details_activity.this, Locale.getDefault());
        try
        {
            address= geo.getFromLocation(latLng.latitude, latLng.longitude, 1);
        } catch (IOException e)
        {
            Toast.makeText(Shop_details_activity.this, "Error:" + e.getMessage().toString(), Toast.LENGTH_LONG).show();
        }

        stradress=address.toString();

        save=findViewById(R.id.sop_details_save_button);
        ownerName=findViewById(R.id.shop_details_name);
        shopName=findViewById(R.id.shop_details_shopname);
        ownerCnic=findViewById(R.id.shop_details_cnic);
        ownerMobile=findViewById(R.id.shop_details_ownernumber);
        shopreference= FirebaseDatabase.getInstance().getReference("SHOP");
        shopreference.keepSynced(true);
        save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                progressBar.setVisibility(View.VISIBLE);
                if(ownerName.getText().toString().length()==0||shopName.getText().toString().length()==0||ownerCnic.getText().toString().length()==0||ownerMobile.getText().toString().length()==0)
                {
                    Toast.makeText(Shop_details_activity.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
                    return;
                }
                String strOwnerName=ownerName.getText().toString().trim();
                String strShopName=shopName.getText().toString().trim(),strOwnerCnic=ownerCnic.getText().toString().trim(),strOwnerMobile=ownerMobile.getText().toString().trim();
                String id=shopreference.push().getKey();
                ShopDetails shopDetails=new ShopDetails(id,latLng,stradress,strOwnerName,strOwnerCnic,strShopName,strOwnerMobile);
                shopreference.child(id).setValue(shopDetails);
                progressBarh.postDelayed(runnable1,100);
            }

        });

    }
}
