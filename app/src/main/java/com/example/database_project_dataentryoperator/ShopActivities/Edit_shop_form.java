package com.example.database_project_dataentryoperator.ShopActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.database_project_dataentryoperator.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Edit_shop_form extends AppCompatActivity
{
    private RelativeLayout relativeLayout;
    private Button save;
    private EditText ownerName,ownerCnic,shopName,ownerMobile;



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
    private Handler handler=new Handler();
    private Runnable runnable=new Runnable()
    {
        @Override
        public void run()
        {
            relativeLayout.setVisibility(View.VISIBLE);
        }
    };
    private String id;

    private ShopDetails shopDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_order_form);

        progressBar=findViewById(R.id.edit_shop_details_my_progress_bar);
        relativeLayout=findViewById(R.id.edit_shop_details_relative_layout);
        progressBarh.postDelayed(runnable1,100);

        handler.postDelayed(runnable,1000);
        Intent intent=getIntent();
        id=intent.getStringExtra("shop_id");
        save=findViewById(R.id.edit_sop_details_save_button);
        ownerName=findViewById(R.id.edit_shop_owner_name);
        shopName=findViewById(R.id.edit_shop_shop_name);
        ownerCnic=findViewById(R.id.edit_shop_owner_cnic);
        ownerMobile=findViewById(R.id.edit_shop_details_ownernumber);

        save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                progressBar.setVisibility(View.VISIBLE);
                String ownernameStr,ownerMoileStr,ownerCnicStr,shopNameStr;
                ownernameStr=ownerName.getText().toString();
                ownerMoileStr=ownerMobile.getText().toString();
                ownerCnicStr=ownerCnic.getText().toString();
                shopNameStr=shopName.getText().toString();
                if(ownerName.getText().toString().length()==0)
                {
                    ownerName.setError("Please enter owner name");
                    return;
                }
                if(ownerCnic.getText().toString().length()==0)
                {
                    ownerCnic.setError("Please enter owner cnic");
                    return;
                }
                if(shopName.getText().toString().length()==0)
                {
                    shopName.setError("Please enter shop name");
                    return;
                }
                if(ownerMobile.getText().toString().length()==0)
                {
                    ownerMobile.setError("Please enter owner mobile no");
                    return;
                }

                FirebaseDatabase.getInstance().getReference().child("SHOP").child(id).child("ownerName").setValue(ownernameStr);
                FirebaseDatabase.getInstance().getReference().child("SHOP").child(id).child("ownerCnic").setValue(ownerCnicStr);
                FirebaseDatabase.getInstance().getReference().child("SHOP").child(id).child("shopName").setValue(shopNameStr);
                FirebaseDatabase.getInstance().getReference().child("SHOP").child(id).child("ownerMobile").setValue(ownerMoileStr);

                progressBarh.postDelayed(runnable1,100);
                Toast.makeText(Edit_shop_form.this, "Changes applied successfully", Toast.LENGTH_SHORT).show();

                ownerName.setText("");
                ownerCnic.setText("");
                ownerMobile.setText("");
                shopName.setText("");
            }
        });




    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Query q=FirebaseDatabase.getInstance().getReference().child("SHOP").orderByKey().equalTo(id).limitToFirst(1);
        q.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    shopDetails=snapshot.getValue(ShopDetails.class);
                }

                if(shopDetails!=null)
                {
                    shopName.setText(shopDetails.getShopName());
                    ownerName.setText(shopDetails.getOwnerName());
                    ownerCnic.setText(shopDetails.getOwnerCnic());
                    ownerMobile.setText(shopDetails.getOwnerMobile());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
                Toast.makeText(Edit_shop_form.this, "error in downloading the data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}