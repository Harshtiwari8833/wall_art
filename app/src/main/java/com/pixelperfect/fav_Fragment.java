package com.pixelperfect;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class fav_Fragment extends Fragment {


    RecyclerView fav_recycler;
    favAdapter adapter1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fav_, container, false);

        fav_recycler = view.findViewById(R.id.recycle_fav);
        SharedPreferences pref1 = getContext().getSharedPreferences("email", MODE_PRIVATE);
        String email = pref1.getString("email_login", "");

        GridLayoutManager grid = new GridLayoutManager(getContext(),2);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users").child(email).child("favouraite");
        reference.addValueEventListener(new ValueEventListener() {
            ArrayList<wallModel> array = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    wallModel data =   dataSnapshot.getValue(wallModel.class);
                    array.add(data);

                }
                adapter1 = new favAdapter(getContext(), array);
                fav_recycler.setLayoutManager(grid);
                fav_recycler.setAdapter(adapter1);
                adapter1.notifyDataSetChanged();
                fav_recycler.setNestedScrollingEnabled(true);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(), "something went wrong!", Toast.LENGTH_SHORT).show();
            }

        });



        return view;

    }
}