package com.example.database_project_dataentryoperator.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.database_project_dataentryoperator.R;
import com.example.database_project_dataentryoperator.SKUActivities.Sku;
import com.example.database_project_dataentryoperator.SKUActivities.SkuCatagory;
import com.example.database_project_dataentryoperator.SKUActivities.SkuCompany;
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
    private DatabaseReference databaseReference1,databaseReference2,skuReference;

    private ArrayAdapter<SkuCompany> companyArrayAdapter;
    private ArrayAdapter<SkuCatagory> catagoryArrayAdapter;

    private TextView message;

    private List<SkuCatagory> catagoryList;
    private List<SkuCompany> companyList;

    private EditText productname,productSize;

    private RelativeLayout rellay1,rally3,rellay2;

    private Button save;

    private Spinner catagorySpinner,companySpinner;

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

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_adding_sku_fragment, container, false);
        //2000 is the timeout for the splash
        handler.postDelayed(runnable, 500);
        //spinners catagory and company
        catagorySpinner=v.findViewById(R.id.sku_catagory_spinner);
        companySpinner=v.findViewById(R.id.sku_company_spinner);
        //arraylists
        catagoryList=new ArrayList<>();
        companyList=new ArrayList<>();
        //company dropdown array adapter
        companyArrayAdapter=new ArrayAdapter(getContext() , R.layout.spinner_text ,companyList);
        companyArrayAdapter.setDropDownViewResource(R.layout.spinner_text_dropdown);
        //catragory dropdown array adapter
        catagoryArrayAdapter=new ArrayAdapter(getContext(),R.layout.spinner_text,catagoryList);
        catagoryArrayAdapter.setDropDownViewResource(R.layout.spinner_text_dropdown);

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



        //edit texts
        productname=v.findViewById(R.id.sku_product_edit_text);
        productSize=v.findViewById(R.id.sku_product_size_edit_text);

        //message text view
        message=v.findViewById(R.id.message_text_view);


        //progress bar
        progressBar=v.findViewById(R.id.my_progress_bar);
        progressBarh.postDelayed(runnable1,0);

        //database reference
        skuReference=FirebaseDatabase.getInstance().getReference("SKU");

        //save button reference
        save=v.findViewById(R.id.sku_save_button);

        //button click listinner
        save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                progressBar.setVisibility(View.VISIBLE);
                if(productname.getText().toString().trim().length()==0)
                {
                    // stopping the progress bar
                    progressBarh.postDelayed(runnable1,100);

                    //setting error in the text view
                    message.setText("Enter the product name");

                    //setting error in the edit text
                    productname.setError("Enter the product name");
                    return;
                }

                if(productSize.getText().toString().trim().length()==0)
                {
                    progressBarh.postDelayed(runnable1,100);
                    message.setText("Enter the product size");
                    productSize.setError("Enter the product name");
                    return;
                }

                String proName=productname.getText().toString().trim();
                int proSize=Integer.parseInt(productSize.getText().toString().trim());

                SkuCatagory catagory= (SkuCatagory) catagorySpinner.getSelectedItem();
                SkuCompany company=(SkuCompany) companySpinner.getSelectedItem();
                String id=skuReference.push().getKey();
                Sku sku=new Sku(id,company,catagory,proName,proSize);
                skuReference.child(id).setValue(sku);
                progressBarh.postDelayed(runnable1,100);


            }
        });


        return v;
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
                Toast.makeText(getContext(), "Error in downloading catagories", Toast.LENGTH_SHORT).show();
            }
        });

        databaseReference2.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                companyList.clear();
                for(DataSnapshot company:dataSnapshot.getChildren())
                {
                    companyList.add(company.getValue(SkuCompany.class));
                }
                companySpinner.setAdapter(companyArrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });


    }
}
