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
import com.example.database_project_dataentryoperator.SKUActivities.SkuCatagory;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class Catagory_fragment extends Fragment
{
    private EditText catagoryet;
    private Button save,back;
    private TextView message;

    private DatabaseReference catagory;

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
    public Catagory_fragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_catagory_frament, container, false);
        //relative layout
        rellay1 =v. findViewById(R.id.rellay1);
        rally2=v.findViewById(R.id.bottom_rally2);
        rellay2=v.findViewById(R.id.rellay2);
        //edit text
        catagoryet=v.findViewById(R.id.catagory_tf);
        //progress bar
        progressBar=v.findViewById(R.id.my_progress_bar);
        progressBarh.postDelayed(runnable1,100);
        //buttons
        save=v.findViewById(R.id.cataory_save_button);
        back=v.findViewById(R.id.catagory_back);
        //textView
        message=v.findViewById(R.id.catagory_message_text_view);
        //splash
        handler.postDelayed(runnable, 500); //2000 is the timeout for the splash
        //real time database
        catagory= FirebaseDatabase.getInstance().getReference("CATAGORY");

        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if(catagoryet.getText().toString().length()==0)
                {
                    catagoryet.setError("please enter a catagory");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                addCatagory();
            }
        });
        return v;

    }

    public void addCatagory()
    {
        progressBar.setVisibility(View.VISIBLE);
        String name=catagoryet.getText().toString();
        if(!TextUtils.isEmpty(name))
        {
            String id=catagory.push().getKey();
            SkuCatagory skuCatagory=new SkuCatagory(id,name);
            catagory.child(id).setValue(skuCatagory);
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
