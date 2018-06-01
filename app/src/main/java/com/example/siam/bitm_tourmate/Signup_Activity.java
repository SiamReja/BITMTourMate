package com.example.siam.bitm_tourmate;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup_Activity extends AppCompatActivity implements View.OnClickListener {
    EditText emailET, passET;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_);
        emailET = findViewById(R.id.email_signup_ET_ID);
        passET  = findViewById(R.id.pass_signup_ET_ID);

        mAuth = FirebaseAuth.getInstance();
    }

    public void backToLogin(View view) {
        startActivity(new Intent(this, Login_Activity.class));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Signup_btn_ID:
                RegisterUser();
                break;
        }
    }

    private void RegisterUser() {
        String email = emailET.getText().toString().trim();
        String password = passET.getText().toString().trim();

        if(email.isEmpty()){
            emailET.setError("Email is required");
            emailET.requestFocus();
            return;
        }
        if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailET.setError("Please give valid email address");
            emailET.requestFocus();
            return;
        }

        if(password.isEmpty()){
            passET.setError("Email is required");
            passET.requestFocus();
            return;
        }
        if (password.length() < 5){
            passET.setError("password need to be at least 6 character");
            passET.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Signup_Activity.this, "Signup successfull", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
