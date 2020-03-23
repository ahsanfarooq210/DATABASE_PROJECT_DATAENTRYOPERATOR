package com.example.database_project_dataentryoperator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

    DatabaseReference referencecompany,referencecatagory;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        String user=usernameTf.getText().toString();
        String pass=passeordTf.getText().toString();
        login(user,pass);
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
                            progressBarh.postDelayed(runnable1,100);
                            Intent intent=new Intent(MainActivity.this, Main_dashboard_adding_sku_activity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            progressBarh.postDelayed(runnable1,100);
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
}
