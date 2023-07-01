package com.pixelperfect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignInGoogleActivity extends AppCompatActivity {
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_google);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getSharedPreferences("signup", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putBoolean("flag2", true);
                editor.apply();
                SharedPreferences pref2 = getSharedPreferences("login", MODE_PRIVATE);
                SharedPreferences.Editor editor3 = pref2.edit();
                editor3.putBoolean("flag", true);
                editor3.apply();
                startActivity(new Intent(SignInGoogleActivity.this, MainActivity.class));
            }
        });
    }
}