package com.example.database_project_dataentryoperator.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.database_project_dataentryoperator.R;
import com.example.database_project_dataentryoperator.Sku;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Delete_sku_fragment extends Fragment
{

    //firebase database references
    private DatabaseReference skuReference,skuReferenceDelete;

    //buttons
    private Button delete;

    //spinner
    private Spinner skuSpinner;

    //array adapter for the spinner
    private ArrayAdapter<Sku> skuArrayAdapter;

    //the sku list
    List<Sku> skuList;

    //linear layout for splas screen
    private LinearLayout linearLayout;

    //splash screen handler
    Handler handler=new Handler();
    Runnable runnable=new Runnable()
    {
        @Override
        public void run()
        {
            linearLayout.setVisibility(View.VISIBLE);
        }
    };

    public Delete_sku_fragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_delete_sku_fragment, container, false);

        //firebase database reference to delete the node
        skuReference= FirebaseDatabase.getInstance().getReference("SKU");
        skuReference.keepSynced(true);

        //settin up linear layout for splash screen
        linearLayout=v.findViewById(R.id.delete_sku_fragment_linear1);

        //splash screen thread
        handler.postDelayed(runnable,500);

        //setting the sku list
        skuList=new ArrayList<>();

        //button reference
        delete=v.findViewById(R.id.sku_delete_button);

        //setting the spinner
        skuSpinner=v.findViewById(R.id.delete_sku_spinner);

        //setting the array adapter
        skuArrayAdapter=new ArrayAdapter(getContext(),R.layout.spinner_text, skuList);
        skuArrayAdapter.setDropDownViewResource(R.layout.spinner_text_dropdown);

        //delete setclick listinner
        delete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Sku sku= (Sku) skuSpinner.getSelectedItem();
                skuReferenceDelete=FirebaseDatabase.getInstance().getReference("SKU").child(sku.getId());
                skuReferenceDelete.removeValue();
                Toast.makeText(getContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
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
                skuList.clear();
                for(DataSnapshot sku:dataSnapshot.getChildren())
                {
                    skuList.add(sku.getValue(Sku.class));
                }
                skuSpinner.setAdapter(skuArrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
                Toast.makeText(getContext(), "Error in downloading the data", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
