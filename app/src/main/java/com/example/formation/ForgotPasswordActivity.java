package com.example.formation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {


    private Button goToSignInActivity,btnReset;
    private EditText email;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        goToSignInActivity = findViewById(R.id.goToSignIn);
        btnReset = findViewById(R.id.btnResetPass);
        email = findViewById(R.id.emailForgetPass);
        firebaseAuth=FirebaseAuth.getInstance();

        goToSignInActivity.setOnClickListener(v -> {
            startActivity(new Intent(ForgotPasswordActivity.this, SignInActivity.class));

        });
        btnReset.setOnClickListener(view -> {
            firebaseAuth.sendPasswordResetEmail(email.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(ForgotPasswordActivity.this, "Password reset email is sent!", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(ForgotPasswordActivity.this,SignInActivity.class));

                    }
                    else{
                        Toast.makeText(ForgotPasswordActivity.this, "", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        });
    }
}