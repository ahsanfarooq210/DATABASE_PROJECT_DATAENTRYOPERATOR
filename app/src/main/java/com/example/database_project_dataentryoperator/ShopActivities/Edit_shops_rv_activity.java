package com.example.database_project_dataentryoperator.ShopActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.database_project_dataentryoperator.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Edit_shops_rv_activity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private DatabaseReference shopReference;
    private ArrayList<ShopDetails> shopDetailsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_shops_activity);

        recyclerView=findViewById(R.id.show_shop_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        shopDetailsArrayList=new ArrayList<>();
        shopReference= FirebaseDatabase.getInstance().getReference("SHOP");
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        shopReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                shopDetailsArrayList.clear();
                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    shopDetailsArrayList.add(snapshot.getValue(ShopDetails.class));
                }
                EditShopRvAdapter editShopRvAdapter=new EditShopRvAdapter(shopDetailsArrayList, Edit_shops_rv_activity.this);
                recyclerView.setAdapter(editShopRvAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }
}