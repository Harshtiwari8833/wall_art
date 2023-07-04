package com.pixelperfect;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class SignInGoogleActivity extends AppCompatActivity {
    GoogleSignInOptions gso;
    GoogleSignInClient  mGoogleSignInClient;

Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient( SignInGoogleActivity.this, gso);

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
                signIn();
            }
        });
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 1000);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 1000) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
               task.getResult(ApiException.class);
               navigateToSecondActivity();
            }catch (Exception e){
                Toast.makeText(this, "something went wrong!", Toast.LENGTH_SHORT).show();
            }

        }
    }

    void navigateToSecondActivity(){
        Intent intent = new Intent(SignInGoogleActivity.this, GoogleSigninFetchdetialsActivity.class);
        startActivity(intent);
    }

}