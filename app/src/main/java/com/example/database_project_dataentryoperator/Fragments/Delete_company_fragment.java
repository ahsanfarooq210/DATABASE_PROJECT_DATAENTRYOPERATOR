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
import com.example.database_project_dataentryoperator.SkuCompany;
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
public class Delete_company_fragment extends Fragment
{

    //firebase database references
    private DatabaseReference companyReference,companyReferenceDelete;

    //buttons
    private Button delete;

    //spinner
    private Spinner companySpinner;

    //array adapter for the spinner
    private ArrayAdapter<SkuCompany> companyArrayAdapter;

    //the sku list
    List<SkuCompany> companyList;

    //linear layout for splas screen
    private LinearLayout linearLayout;

    Handler handler=new Handler();
    Runnable runnable=new Runnable()
    {
        @Override
        public void run()
        {
            linearLayout.setVisibility(View.VISIBLE);
        }
    };
    public Delete_company_fragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_delete_company_fragment, container, false);

        //firebase database reference to delete the node
        companyReference= FirebaseDatabase.getInstance().getReference("COMPANIES");
        companyReference.keepSynced(true);

        //settin up linear layout for splash screen
        linearLayout=v.findViewById(R.id.delete_company_fragment_linear1);

        //splash screen thread
        handler.postDelayed(runnable,500);

        //setting the sku list
        companyList=new ArrayList<>();

        //button reference
        delete=v.findViewById(R.id.company_delete_button);

        //setting the spinner
        companySpinner=v.findViewById(R.id.delete_company_spinner);

        //setting the array adapter
        companyArrayAdapter=new ArrayAdapter(getContext(),R.layout.spinner_text, companyList);
        companyArrayAdapter.setDropDownViewResource(R.layout.spinner_text_dropdown);

        //delete setclick listinner
        delete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SkuCompany company= (SkuCompany) companySpinner.getSelectedItem();
                if(company!=null)
                {
                    companyReferenceDelete=FirebaseDatabase.getInstance().getReference("COMPANIES").child(company.getId());
                    companyReferenceDelete.removeValue();
                    Toast.makeText(getContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return v;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        companyReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                companyList.clear();
                for(DataSnapshot sku:dataSnapshot.getChildren())
                {
                    companyList.add(sku.getValue(SkuCompany.class));
                }
                companySpinner.setAdapter(companyArrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
                Toast.makeText(getContext(), "Error in downloading the data", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
