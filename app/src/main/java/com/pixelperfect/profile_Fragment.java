package com.pixelperfect;

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
   ArrayList<profileModel> arrayList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_, container, false);

        recyclerView = view.findViewById(R.id.pro_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList.add(new profileModel("About"));
        arrayList.add(new profileModel("Privacy Policy"));
        arrayList.add(new profileModel("Logout"));
        arrayList.add(new profileModel("Rate Us"));


        profile_adapter adapter = new profile_adapter(getContext(),arrayList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;
    }
}