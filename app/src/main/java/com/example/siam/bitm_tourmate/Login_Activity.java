package com.example.siam.bitm_tourmate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Login_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
    }

    public void goToSignUp(View view) {
        startActivity(new Intent(this, Signup_Activity.class));
    }
}
