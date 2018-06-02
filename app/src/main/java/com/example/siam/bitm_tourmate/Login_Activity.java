package com.example.siam.bitm_tourmate;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_Activity extends AppCompatActivity implements View.OnClickListener {

    private Button loginBtn;
    private FirebaseAuth mAuth;
    private EditText emailET, passET;
    private TextView forgotPassTV;
    private ProgressBar progressBar;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        progressBar = findViewById(R.id.progressbar_login_ID);
        loginBtn = findViewById(R.id.loging_btn_ID);
        emailET = findViewById(R.id.email_ET_ID);
        passET = findViewById(R.id.pass_ET_ID);
        forgotPassTV = findViewById(R.id.forgotpassTV_ID);

        loginBtn.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.reg_TV_ID).setOnClickListener(this);
        findViewById(R.id.loging_btn_ID).setOnClickListener(this);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();

        if (firebaseUser!=null){
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.reg_TV_ID:
                finish();
                startActivity(new Intent(this, Signup_Activity.class));
                break;
            case R.id.loging_btn_ID:
                userLogin();

        }
    }

    private void userLogin() {
        String email = emailET.getText().toString().trim();
        String password = passET.getText().toString().trim();


        if (email.isEmpty()) {
            emailET.setError("Email is required");
            emailET.requestFocus();
            return;
        }
//        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//            emailET.setError("Please give valid email address");
//            emailET.requestFocus();
//            return;
//        }

        if (password.isEmpty()) {
            passET.setError("Email is required");
            passET.requestFocus();
            return;
        }
        if (password.length() < 5) {
            passET.setError("password need to be at least 6 character");
            passET.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    finish();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Toast.makeText(Login_Activity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void forgotpassAlertDialog(View view) {
        Intent intent = new Intent(this, Forgot_Password_Activity.class);
        startActivity(intent);
    }


}
