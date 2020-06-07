package com.example.database_project_dataentryoperator.ProfileActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.database_project_dataentryoperator.R;
import com.example.database_project_dataentryoperator.main_dashboard_activity;
import com.google.android.material.snackbar.Snackbar;

public class activity_Edit_Profile extends AppCompatActivity {
    EditText Name_EDITTEXT,Cnic_EDITTEXT,Email_EDITTEXT,dob_EDITTEXT,cell_no_EDITTEXT,education_EDITTEXT;
    Button cancel_button,save_Profile_button;
    //splash screen relative layout
    private RelativeLayout rellay1, rally3, rellay2;
    //handler for the splash screen
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable()
    {
        @Override
        public void run()
        {

            rellay1.setVisibility(View.VISIBLE);
            rally3.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);

        }
    };

    //handler for the progress bar
    private ProgressBar progressBar;
    private Handler progressBarh = new Handler();
    private Runnable runnable1 = new Runnable()
    {
        @Override
        public void run()
        {

            progressBar.setVisibility(View.GONE);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__edit__profile);
        Name_EDITTEXT=findViewById(R.id.Name_EDITTEXT);
        Cnic_EDITTEXT=findViewById(R.id.Cnic_EDITTEXT);
        Email_EDITTEXT=findViewById(R.id.Email_EDITTEXT);
        dob_EDITTEXT=findViewById(R.id.dob_EDITTEXT);
        cell_no_EDITTEXT=findViewById(R.id.cell_no_EDITTEXT);
        education_EDITTEXT=findViewById(R.id.education_EDITTEXT);


        Name_EDITTEXT.setText("Data Entry Operator ");

        Cnic_EDITTEXT.setText("33333-1234567-8");

        Email_EDITTEXT.setText("dataentryOpeartor@gmail.com");

        dob_EDITTEXT.setText("29/02/1996");

        education_EDITTEXT.setText("BSCS");
        //relative layouts
        rellay1 = findViewById(R.id.adding_sku_rellay1);
        rellay2 = findViewById(R.id.adding_sku_rellay2);
        rally3 = findViewById(R.id.adding_sku_bottom_rally2);

        //splash screen
        handler.postDelayed(runnable, 500);

        //progress bar
        progressBar = findViewById(R.id.my_progress_bar);
        progressBarh.postDelayed(runnable1, 0);

        cancel_button= findViewById(R.id.cancel_button);
        save_Profile_button=findViewById(R.id.save_Profile_button);
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendBack=new Intent(activity_Edit_Profile.this,main_dashboard_activity.class);
                startActivity(sendBack);
            }
        });
        save_Profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar
                        .make(v, "Save Button clicked in Activity_Edit_profile", Snackbar.LENGTH_LONG)
                        .setAction("ok", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent sendBack=new Intent(activity_Edit_Profile.this,main_dashboard_activity.class);
                                startActivity(sendBack);
                            }
                        });

                snackbar.show();
            }
        });

    }
}