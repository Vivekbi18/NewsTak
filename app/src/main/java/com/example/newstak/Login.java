package com.example.newstak;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class Login extends AppCompatActivity {

    Button loginbtn;
    TextView forgottext,signuptext;
    EditText loginemail,loginpassword;
    FirebaseAuth mAuth;
    ProgressBar pbar;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginbtn = findViewById(R.id.loginbtn);
        forgottext = findViewById(R.id.forgot);
        signuptext = findViewById(R.id.signup);
        pbar = findViewById(R.id.pbar);
        loginemail = findViewById(R.id.loginEmail);
        loginpassword = findViewById(R.id.loginPassword);
        mAuth = FirebaseAuth.getInstance();


        forgottext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ForgotPassword.class);
                startActivity(intent);
                finish();
            }
        });

        signuptext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);
                finish();
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pbar.setVisibility(View.VISIBLE);
                String email,password;
                email = String.valueOf(loginemail.getText());
                password = String.valueOf(loginpassword.getText());

                if(TextUtils.isEmpty(email)){
                    pbar.setVisibility(View.INVISIBLE);
                    Toast.makeText(Login.this, "Email is empty", Toast.LENGTH_SHORT).show();
                    pbar.setVisibility(View.GONE);
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    pbar.setVisibility(View.INVISIBLE);
                    Toast.makeText(Login.this, "Password is empty", Toast.LENGTH_SHORT).show();
                    pbar.setVisibility(View.GONE);
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                pbar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(Login.this, "Logged in Successfully",
                                            Toast.LENGTH_SHORT).show();

                                } else {
                                    pbar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(Login.this, "Invalid Username or Password",
                                            Toast.LENGTH_LONG).show();

                                }
                            }
                        });

            }
        });

    }
}