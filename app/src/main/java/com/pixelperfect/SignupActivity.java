package com.pixelperfect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText signupEmail, signupPassword;
    private SharedPreferences preferences ;
    private SharedPreferences.Editor editor;
    private Button signupBtn;
    private TextView loginRedirectText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        auth  = FirebaseAuth.getInstance();
        signupEmail = findViewById(R.id.userEmailSignUP);
        signupPassword = findViewById(R.id.userPasswordSignUP);
        signupBtn = findViewById(R.id.signup_btn);
        loginRedirectText = findViewById(R.id.loginRedirectText);




        signupBtn.setOnClickListener(view -> {

            String user = signupEmail.getText().toString().trim();
            preferences = getSharedPreferences("emailMatch",MODE_PRIVATE);
            preferences.getString("emailMatched","");

            editor= preferences.edit();
            editor.putString("emailMatched",user);
            editor.apply();

            String pass = signupPassword.getText().toString().trim();

            if (user.isEmpty()){
                signupEmail.setError("Email can't be empty");
            }
            if (pass.isEmpty()){
                signupPassword.setError("password can't be empty");
            }
            else {
                auth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        Toast.makeText(SignupActivity.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                        SharedPreferences pref = getSharedPreferences("signup", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putBoolean("flag2", true);
                        editor.apply();
                        SharedPreferences pref2 = getSharedPreferences("login", MODE_PRIVATE);
                        SharedPreferences.Editor editor3 = pref2.edit();
                        editor3.putBoolean("flag", true);
                        editor3.apply();
                        startActivity(new Intent(SignupActivity.this ,MainActivity.class ));
                        finish();
                    }
                    else {
                        Toast.makeText(SignupActivity.this, "SignUp Failed"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        loginRedirectText.setOnClickListener(view ->
                startActivity(new Intent(SignupActivity.this, LoginActivity.class)));




    }
}