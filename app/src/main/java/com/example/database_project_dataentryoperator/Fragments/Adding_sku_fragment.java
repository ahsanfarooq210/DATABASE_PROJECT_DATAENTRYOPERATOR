package com.example.database_project_dataentryoperator.Fragments;

import android.opengl.EGLExt;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.database_project_dataentryoperator.R;
import com.example.database_project_dataentryoperator.SkuCatagory;
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
public class Adding_sku_fragment extends Fragment
{
    private DatabaseReference databaseReference1,databaseReference2;
    ArrayAdapter<SkuCompany> companyArrayAdapter;
    ArrayAdapter<SkuCatagory> catagoryArrayAdapter;

    List<SkuCatagory> catagoryList;
    List<SkuCompany> companyList;
    private RelativeLayout rellay1,rally3,rellay2;

    Button save;
    Spinner catagorySpinner,companySpinner;

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {

            rellay1.setVisibility(View.VISIBLE);
            rally3.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);

        }
    };

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


    public Adding_sku_fragment()
    {
        // Required empty public constructor
    }



    @Override
    public void onStart()
    {
        super.onStart();
        databaseReference1.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                catagoryList.clear();
                for(DataSnapshot catagory:dataSnapshot.getChildren())
                {
                    catagoryList.add(catagory.getValue(SkuCatagory.class));
                }

                catagorySpinner.setAdapter(catagoryArrayAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });


    }




    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_adding_sku_fragment, container, false);
        //spinners catagory and company
        catagorySpinner=v.findViewById(R.id.sku_catagory_spinner);
        companySpinner=v.findViewById(R.id.sku_company_spinner);
        //arraylists
        catagoryList=new ArrayList<>();
        companyList=new ArrayList<>();
        //company dropdown array adapter
        companyArrayAdapter=new ArrayAdapter<SkuCompany>(getContext() , android.R.layout.simple_spinner_item ,companyList);
        companyArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //catragory dropdown array adapter
        catagoryArrayAdapter=new ArrayAdapter<SkuCatagory>(getContext(),android.R.layout.simple_spinner_item,catagoryList);
        catagoryArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //relative out for the splash
        rellay1=v.findViewById(R.id.adding_sku_rellay1);
        rellay2=v.findViewById(R.id.adding_sku_rellay2);
        rally3 =v.findViewById(R.id.adding_sku_bottom_rally2);
        //firebase
        databaseReference1=FirebaseDatabase.getInstance().getReference().child("CATAGORY");
        databaseReference2=FirebaseDatabase.getInstance().getReference().child("COMPANIES");
        //offline syncing
        databaseReference1.keepSynced(true);
        databaseReference2.keepSynced(true);

        handler.postDelayed(runnable, 500); //2000 is the timeout for the splash


        //progress bar
        progressBar=v.findViewById(R.id.my_progress_bar);
        progressBarh.postDelayed(runnable1,0);

//        save.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                progressBar.setVisibility(View.VISIBLE);
//            }
//        });


        return v;
      }
}
