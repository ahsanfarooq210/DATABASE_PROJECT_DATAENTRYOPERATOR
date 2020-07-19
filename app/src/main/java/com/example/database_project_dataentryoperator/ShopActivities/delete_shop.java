package com.example.database_project_dataentryoperator.ShopActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import android.os.Bundle;

import com.example.database_project_dataentryoperator.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class delete_shop extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private ArrayList<ShopDetails> shopDetailsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_shop);
        recyclerView = findViewById(R.id.delete_shop_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseReference = FirebaseDatabase.getInstance().getReference().child("SHOP");
        shopDetailsArrayList = new ArrayList<>();

    }

    @Override
    protected void onStart()
    {

        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                shopDetailsArrayList.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    shopDetailsArrayList.add(snapshot.getValue(ShopDetails.class));
                }
                DeleteShopRvAapter deleteShopRvAapter=new DeleteShopRvAapter(shopDetailsArrayList,delete_shop.this);
                recyclerView.setAdapter(deleteShopRvAapter);
                
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }
}