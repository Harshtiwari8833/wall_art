package com.pixelperfect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth auth;
    private EditText loginEmial, loginPassword;
    private TextView signupRedirectText;
    private Button loginBtn;
    GoogleSignInClient mGoogleSignInClient;
    private static int RC_SIGN_IN =100;
    ImageView signInButton;
    int count = 1;
//    SignInButton   signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        loginEmial = findViewById(R.id.userEmailLogin);
        loginPassword = findViewById(R.id.userPasswordLogin);
        signupRedirectText = findViewById(R.id.signupRedirectText);
        loginBtn = findViewById(R.id.loginBtn);

        signInButton= findViewById(R.id.sign_in_button);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = loginEmial.getText().toString();

                String emailUsername;

                // Email Trim
                emailUsername = loginEmial.getText().toString();
                int index = emailUsername.indexOf('@');
                emailUsername = emailUsername.substring(0,index);

                SharedPreferences pref = getSharedPreferences("email", MODE_PRIVATE);
                pref.getString("emailId","");

                SharedPreferences.Editor editor = pref.edit();
                editor.putString("emailId",emailUsername);
                editor.apply();

                String pass  =loginPassword.getText().toString();
                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    if (!pass.isEmpty()){
                        auth.signInWithEmailAndPassword(email,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putBoolean("flag", true);
                                editor.apply();



                                SharedPreferences pref2 = getSharedPreferences("signup", MODE_PRIVATE);
                                SharedPreferences.Editor editor3 = pref2.edit();
                                editor3.putBoolean("flag2", true);
                                editor3.apply();

                                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                //here i have to cahnge the intent
                                //!!!!!!!!!!!@@@@@@@@@@@@@@@@###########
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                                startActivity(intent);

                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                    else {
                        loginPassword.setError("Password can't be empty");
                    }

                }
                else if(email.isEmpty()){
                    loginEmial.setError("Email can't be empty");
                }
                else{
                    loginEmial.setError("Please enter valid email");
                }
            }



        });

        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

//        signInButton.setSize(SignInButton.SIZE_STANDARD);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signIn();
            }

        });

    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
            finish();
        }

    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            if (acct != null) {
                String personName = acct.getDisplayName();
                String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                Uri personPhoto = acct.getPhotoUrl();



            }

            //here we have to change the intent
            //
            //@#$%%%%%%%%%%%%%%%%%%
            startActivity(new Intent(LoginActivity.this, MainActivity.class));

            // Signed in successfully, show authenticated UI.

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.d("message", e.toString());

        }
    }
    protected void  onStart() {
        super.onStart();




        if (auth.getCurrentUser() != null ){
//            Toast.makeText(this, "Already Logged In!", Toast.LENGTH_SHORT).show();
            SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("flag", true);
            editor.apply();

            finish();
        }
        else {
//            Toast.makeText(this, "You can login now", Toast.LENGTH_SHORT).show();
        }

    }


}