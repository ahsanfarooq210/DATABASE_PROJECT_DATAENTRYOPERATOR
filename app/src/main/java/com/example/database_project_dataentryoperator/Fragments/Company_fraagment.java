package com.example.database_project_dataentryoperator.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.database_project_dataentryoperator.R;
import com.example.database_project_dataentryoperator.SkuCatagory;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class Company_fraagment extends Fragment
{

    private EditText companyet;
    private Button save,back;
    private TextView message;

    private DatabaseReference company;

    private RelativeLayout rellay1,rally2,rellay2;



    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {

            rellay1.setVisibility(View.VISIBLE);
            rally2.setVisibility(View.VISIBLE);
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

    public Company_fraagment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_company_fraagment, container, false);
        //relative layout
        rellay1 =v. findViewById(R.id.company_rellay1);
        rally2=v.findViewById(R.id.company_bottom_rally2);
        rellay2=v.findViewById(R.id.company_rellay2);

        //edit text
        companyet=v.findViewById(R.id.company_tf);
        //progress bar
        progressBar=v.findViewById(R.id.company_my_progress_bar);
        progressBarh.postDelayed(runnable1,100);
        //buttons
        save=v.findViewById(R.id.company_save_button);
        back=v.findViewById(R.id.company_back);
        //textView
        message=v.findViewById(R.id.company_message_text_view);
        //splash
        handler.postDelayed(runnable, 1000); //2000 is the timeout for the splash
        //real time fatabase
        company= FirebaseDatabase.getInstance().getReference("COMPANIES");

        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                addCompany();
            }
        });
        return v;
    }


    public void addCompany()
    {
        progressBar.setVisibility(View.VISIBLE);
        String name=companyet.getText().toString();
        if(!TextUtils.isEmpty(name))
        {
            String id=company.push().getKey();
            SkuCatagory skuCatagory=new SkuCatagory(id,name);
            company.child(id).setValue(skuCatagory);
            progressBarh.postDelayed(runnable1,100);
            message.setText("Saved Successfully");
            Toast.makeText(getContext(), "Saved Successfully", Toast.LENGTH_SHORT).show();

        }
        else
        {
            message.setText("Please type a catagory");
        }
    }
}
