package com.pixelperfect;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class wall_Fragment extends Fragment {

    RecyclerView recycler;
    private ProgressBar progressBar;
    String url;
    wallpaperAdapter adapter1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wall_, container, false);
        //toolbar

        recycler = view.findViewById(R.id.wall_recycler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);



        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("wallpapers");
            reference.addValueEventListener(new ValueEventListener() {
                List<String> array = new ArrayList<>();
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        String data =   dataSnapshot.getValue(String.class);
                        array.add(data);
                    }
                    adapter1 = new wallpaperAdapter(getContext(),array);
                    recycler.setLayoutManager(gridLayoutManager);
                    recycler.setAdapter(adapter1);

                    recycler.setNestedScrollingEnabled(true);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                    Toast.makeText(getContext(), "something went wrong!", Toast.LENGTH_SHORT).show();
                }

            });


//        array.add(new wallModel("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"));
//
//        array.add(new wallModel(url));
//        wallpaperAdapter adapter1 = new wallpaperAdapter(getContext(),array);
//                        recycler.setAdapter(adapter1);
//                        adapter1.notifyDataSetChanged();
//        array.add(new wallModel(R.drawable.wall2));
//
//        array.add(new wallModel(R.drawable.wall4));
//        array.add(new wallModel(R.drawable.wall5));
//        array.add(new wallModel(R.drawable.wall6));
//        array.add(new wallModel(R.drawable.wall7));
//        array.add(new wallModel(R.drawable.wall8));
//        array.add(new wallModel(R.drawable.wall9));
//        array.add(new wallModel(R.drawable.wall10));
//        array.add(new wallModel(R.drawable.wall11));
//        array.add(new wallModel(R.drawable.wall12));
//        array.add(new wallModel(R.drawable.wall13));
//        array.add(new wallModel(R.drawable.wall14));
//        array.add(new wallModel(R.drawable.wall15));
//        array.add(new wallModel(R.drawable.wall16));
//        array.add(new wallModel(R.drawable.wall17));
//        array.add(new wallModel(R.drawable.wall18));



        return view;
    }
}
