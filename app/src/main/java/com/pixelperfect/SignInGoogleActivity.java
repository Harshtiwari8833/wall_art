package com.pixelperfect;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;

public class SignInGoogleActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private static final int RC_SIGN_IN = 9001;
    private GoogleApiClient googleApiClient;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_google);
        button = findViewById(R.id.button);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        button.setOnClickListener(v -> signIn());
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }



    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("SignInActivity", "onConnectionFailed:" + connectionResult);
        Toast.makeText(this, "Connection failed. Please try again.", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {

            GoogleSignInAccount account = result.getSignInAccount();
            String email = account.getEmail();
            String displayName = account.getDisplayName();



              SharedPreferences pref = getSharedPreferences("user_email", MODE_PRIVATE);
              pref.getString("email", "");
              SharedPreferences.Editor editor = pref.edit();
              editor.putString("email", email);
              editor.apply();


            SharedPreferences pref1 = getSharedPreferences("user_name", MODE_PRIVATE);
            pref1.getString("name", "");
            SharedPreferences.Editor editor1 = pref1.edit();
            editor1.putString("name", displayName);
            editor1.apply();

            try {
                String photoUrl = account.getPhotoUrl().toString();
                SharedPreferences pref2 = getSharedPreferences("user_email", MODE_PRIVATE);
                pref2.getString("photo", "");
                SharedPreferences.Editor editor2 = pref2.edit();
                editor2.putString("photo", photoUrl);
                editor2.apply();
            }catch (Exception e) {}

            SharedPreferences pref4 = getSharedPreferences("signup", MODE_PRIVATE);
            pref4.getBoolean("flag2", false);
            SharedPreferences.Editor editor3 = pref4.edit();
            editor3.putBoolean("flag2", true);
            editor3.apply();
            Intent intent = new Intent(SignInGoogleActivity.this, MainActivity.class);
            // Perform necessary actions with the obtained user details
            // ...

            // Example: Displaying the user's email in a toast message
            Toast.makeText(this, "signed in as: " + email, Toast.LENGTH_SHORT).show();
            startActivity(intent);
        } else {
            Toast.makeText(this, "Sign-in failed. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }
    }

