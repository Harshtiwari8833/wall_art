package com.pixelperfect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class GoogleSigninFetchdetialsActivity extends AppCompatActivity {
    GoogleSignInOptions gso;
    GoogleSignInClient mGoogleSignInClient;
    TextView name;
    TextView email;
    Button signoutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient( GoogleSigninFetchdetialsActivity.this, gso);
        setContentView(R.layout.activity_google_signin_fetchdetials);
        setContentView(R.layout.activity_sign_in_google);


        name= findViewById(R.id.name_txt);
        email = findViewById(R.id.email);
        signoutBtn = findViewById(R.id.signoutBtn);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null){
            String personName= acct.getDisplayName();
            String personEmail = acct.getEmail();

//            name.setText(personName);
//           email.setText(personEmail);
        }
        signoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 SignOut();
            }

            private void SignOut() {
                mGoogleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        finish();
                        startActivity(new Intent(GoogleSigninFetchdetialsActivity.this, SignInGoogleActivity.class));

                    }
                });
            }
        });

    }
}