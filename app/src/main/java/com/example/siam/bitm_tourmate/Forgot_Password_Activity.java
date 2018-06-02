package com.example.siam.bitm_tourmate;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_Password_Activity extends AppCompatActivity {

    EditText forgetPass_ET;
    Button forgetpass_Btn;
    FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot__password_);
        forgetpass_Btn = findViewById(R.id.reset_Btn_FPA_ID);
        forgetPass_ET = findViewById(R.id.email_ET_FPA_ID);
        progressBar = findViewById(R.id.progressbar_FPA_ID);

        mAuth = FirebaseAuth.getInstance();

        forgetpass_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String resetEmail = forgetPass_ET.getText().toString().trim();

                if (resetEmail.isEmpty()) {
                    Toast.makeText(Forgot_Password_Activity.this, "enter your email", Toast.LENGTH_SHORT).show();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.sendPasswordResetEmail(resetEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(Forgot_Password_Activity.this, "email sent", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(Forgot_Password_Activity.this, MainActivity.class));
                            } else {
                                Toast.makeText(Forgot_Password_Activity.this, "Error in sending reset email", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
