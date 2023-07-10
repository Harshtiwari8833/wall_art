package com.pixelperfect;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class profile_Fragment extends Fragment {

   RecyclerView recyclerView;

   String s;
   CircleImageView user_img;
   TextView user_name;
    private GoogleApiClient googleApiClient;
   String imgUrl;
   LinearLayout about,policy, logout, rate;
  /* ArrayList<profileModel> arrayList = new ArrayList<>(); */


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(getContext())
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso) // gso is your GoogleSignInOptions instance
                .build();
        googleApiClient.connect();
        View view = inflater.inflate(R.layout.fragment_profile_, container, false);
       user_img = view.findViewById(R.id.user_img);
       user_name = view.findViewById(R.id.user_name);
        about = view.findViewById(R.id.card_about);
        policy = view.findViewById(R.id.card_policy);
        logout = view.findViewById(R.id.card_logout);
        rate = view.findViewById(R.id.card_rate);

        SharedPreferences pref1 = getContext().getSharedPreferences("user_name", MODE_PRIVATE);
       String name = pref1.getString("name", "");

       user_name.setText("Hi! "+name);
        SharedPreferences pref2 = getContext().getSharedPreferences("user_email", MODE_PRIVATE);
       imgUrl =  pref2.getString("photo", "");

       if(imgUrl.equals("")){
           user_img.setImageDrawable(getContext().getResources().getDrawable(R.drawable.profile));

       }else{
           Glide.with(getContext()).load(imgUrl).into(user_img);
       }



        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), About_intent.class);
                startActivity(intent);
            }
        });

        policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getActivity(), activity_privacy.class);
                startActivity(intent1);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=one4studio.wallpaper.one4wall");
                Intent intent2 = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent2);


            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });


        return view;

    }


    private void logout() {
        SharedPreferences pref4 = getContext().getSharedPreferences("signup", MODE_PRIVATE);
        pref4.getBoolean("flag2", false);
        SharedPreferences.Editor editor3 = pref4.edit();
        editor3.putBoolean("flag2", false);
        editor3.apply();
        try{
    Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(status -> {
        // Optional: Update your UI or perform any other actions after logout
        // For example, you can navigate to the login screen
        Intent intent = new Intent(getContext(), SignInGoogleActivity.class);
        startActivity(intent);
        getActivity().finish();
    });
     }catch (Exception e){
    Toast.makeText(getContext(), "error: " +e, Toast.LENGTH_SHORT).show();
      }
    }
    @Override
    public void onStart() {
        super.onStart();
        googleApiClient.connect();
    }
    @Override
    public void onStop() {
        super.onStop();
        if (googleApiClient != null && googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
    }
}
