package com.example.database_project_dataentryoperator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.database_project_dataentryoperator.ProfileActivities.Activites.activity_Edit_Profile;
import com.example.database_project_dataentryoperator.ProfileActivities.Entity.ProfileData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

    RelativeLayout rellay1,rally2,rellay2;
    TextView bottomTextView;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            rellay1.setVisibility(View.VISIBLE);
            rally2.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);

        }
    };
    ProgressBar progressBar;
    Handler progressBarh=new Handler();
    Runnable runnable1=new Runnable()
    {
        @Override
        public void run()
        {

            progressBar.setVisibility(View.GONE);
        }
    };
    EditText usernameTf,passeordTf;

    private FirebaseAuth mAuth;
    //profileData
//database reference
    private DatabaseReference profileDataReference;
    //array lists for the array adapters
    private List<ProfileData> profileDataList;    DatabaseReference referencecompany,referencecatagory;
    SharedPreferences prefreences ;
    String username,passsword;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//initializing databasee reference for downloading and uploading the data the data
            profileDataReference = FirebaseDatabase.getInstance().getReference("DataEntryOperatorProfileData");
        profileDataReference.keepSynced(true);
        profileDataList=new ArrayList<>();
        prefreences = getSharedPreferences(getResources().getString(R.string.SharedPreferences_FileName),MODE_PRIVATE);


        rellay1 = findViewById(R.id.rellay1);


        rally2=findViewById(R.id.bottom_rally2);
        rellay2=findViewById(R.id.rellay2);

        handler.postDelayed(runnable, 2000); //2000 is the timeout for the splash

        bottomTextView=findViewById(R.id.message_text_view);

        usernameTf=findViewById(R.id.username_tf);
        passeordTf=findViewById(R.id.password_tf);

        progressBar=findViewById(R.id.my_progress_bar);
        progressBarh.postDelayed(runnable1,100);

        mAuth = FirebaseAuth.getInstance();

        referencecompany=FirebaseDatabase.getInstance().getReference().child("CATAGORY");
        referencecatagory=FirebaseDatabase.getInstance().getReference().child("COMPANIES");
        referencecatagory.keepSynced(true);
        referencecompany.keepSynced(true);



    }

    public void loginButton(View view)
    {
         username=usernameTf.getText().toString();
         passsword=passeordTf.getText().toString();
        login(username,passsword);
        progressBar.setVisibility(View.VISIBLE);
    }
    public void login(String email,String password)
    {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information
                            whereTo();


                        } else {
                            // If sign in fails, display a message to the user.
                            progressBarh.postDelayed(runnable1,100);
                            Toast.makeText(MainActivity.this, "Error in login",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
    private void whereTo() {

        boolean found=false;
        for (int i=0; i<profileDataList.size();i++)
        {
            if(profileDataList.get(i).getEmail().equals(username))
            {
                found=true;
                break;
            }
        }
        if (!found) {
            // activity_Edit_Profile
            SharedPreferences.Editor editor = getSharedPreferences(getResources().getString(R.string.SharedPreferences_FileName),MODE_PRIVATE).edit();
            editor.putString(getResources().getString(R.string.SharedPreferences_DataEntryOperator),username);
            editor.putBoolean(getResources().getString(R.string.SharedPreferences_isProfileDataComplete),false);
            editor.commit();
            Intent edit_profile=new Intent(MainActivity.this, activity_Edit_Profile.class);
            progressBarh.postDelayed(runnable1, 100);
            startActivity(edit_profile);
        } else {
            Intent intent = new Intent(MainActivity.this, main_dashboard_activity.class);
            // activity_Edit_Profile
            SharedPreferences.Editor editor = getSharedPreferences(getResources().getString(R.string.SharedPreferences_FileName),MODE_PRIVATE).edit();
            editor.putString(getResources().getString(R.string.SharedPreferences_DataEntryOperator),username);
            editor.putBoolean(getResources().getString(R.string.SharedPreferences_isProfileDataComplete),true);
            editor.commit();
            progressBarh.postDelayed(runnable1, 100);
            startActivity(intent);
        }

    }
    @Override
    protected void onStart() {
        super.onStart();
        profileDataReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                profileDataList.clear();
                for (DataSnapshot profile : dataSnapshot.getChildren()) {
                    profileDataList.add(profile.getValue(ProfileData.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}
