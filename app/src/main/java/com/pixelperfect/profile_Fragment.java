package com.pixelperfect;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class profile_Fragment extends Fragment {

   RecyclerView recyclerView;

   String s;

   CardView about, policy, logout, rate;
  /* ArrayList<profileModel> arrayList = new ArrayList<>(); */


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile_, container, false);

        about = view.findViewById(R.id.card_about);
        policy = view.findViewById(R.id.card_policy);
        logout = view.findViewById(R.id.card_logout);
        rate = view.findViewById(R.id.card_rate);

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
        return view;









       /*  recyclerView = view.findViewById(R.id.pro_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager); */

     /*   arrayList.add(new profileModel("About"));
        arrayList.add(new profileModel("Privacy Policy"));
        arrayList.add(new profileModel("Logout"));
        arrayList.add(new profileModel("Rate Us"));

        profile_adapter adapter = new profile_adapter(getContext(),arrayList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged(); */
    }
}
