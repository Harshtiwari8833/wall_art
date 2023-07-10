package com.pixelperfect;

import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//      this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
                boolean check = pref.getBoolean("flag", false);
                SharedPreferences pref4 = getSharedPreferences("signup", MODE_PRIVATE);
                boolean check_signup = pref4.getBoolean("flag2", false);
                SharedPreferences pref3 = getSharedPreferences("onboarding", MODE_PRIVATE);
                boolean check_onboarding = pref3.getBoolean("flag4", false);

                if (check_onboarding && !check_signup) {
                    Intent intent1 = new Intent(SplashActivity.this, SignInGoogleActivity.class);
                    finish();
                    startActivity(intent1);
                } else if (check_onboarding && check_signup) {
                    Intent intent1 = new Intent(SplashActivity.this, MainActivity.class);
                    finish();
                    startActivity(intent1);
                }
                else {
                    Intent intent = new Intent(SplashActivity.this, OnboardingActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },1500);
    }
}