package com.example.database_project_dataentryoperator.SKUActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.database_project_dataentryoperator.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class show_sku_rv_activity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private DatabaseReference skuReference;
    private ArrayList<Sku> skuArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_sku_rv_activity);
        recyclerView=findViewById(R.id.show_sku_recycler_view);
        skuReference= FirebaseDatabase.getInstance().getReference("SKU");
        skuArrayList=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onStart()
    {
        super.onStart();
        skuReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                skuArrayList.clear();
                for(DataSnapshot sku:dataSnapshot.getChildren())
                {
                    skuArrayList.add(sku.getValue(Sku.class));
                }
                Sku_rv_adapter sku_rv_adapter=new Sku_rv_adapter(skuArrayList,show_sku_rv_activity.this);
                recyclerView.setAdapter(sku_rv_adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
                Toast.makeText(show_sku_rv_activity.this, "Error in downloading the data", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
