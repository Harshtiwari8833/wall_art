package com.pixelperfect;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
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
    DatabaseReference reference, dbref;
    Boolean check;
    ArrayList<wallModel> array = new ArrayList<>();
    SharedPreferences pref2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fav_, container, false);
         pref2 = getContext().getSharedPreferences("load_fragment", MODE_PRIVATE);
        check =  pref2.getBoolean("flag", false);
        TextView test = view.findViewById(R.id.test);

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "hello", Toast.LENGTH_SHORT).show();
                try {
                    Toast.makeText(getContext(), "try", Toast.LENGTH_SHORT).show();
                    adapter1.notifyDataSetChanged();
                }catch (Exception e){

                }
            }
        });





        fav_recycler = view.findViewById(R.id.recycle_fav);
        SharedPreferences pref1 = getContext().getSharedPreferences("email", MODE_PRIVATE);
        String email = pref1.getString("email_login", "");

        GridLayoutManager grid = new GridLayoutManager(getContext(),2);

        adapter1 = new favAdapter(getContext(), array);
        fav_recycler.setLayoutManager(grid);
        fav_recycler.setAdapter(adapter1);
        fav_recycler.setNestedScrollingEnabled(true);


        reference = FirebaseDatabase.getInstance().getReference();
        dbref = reference.child("users").child(email).child("favouraite");
//        reference.addValueEventListener(new ValueEventListener() {
//            ArrayList<wallModel> array = new ArrayList<>();
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//                Toast.makeText(getContext(), "something went wrong!", Toast.LENGTH_SHORT).show();
//            }
//
//        });

 dbref.addChildEventListener(new ChildEventListener() {
     @Override
     public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {


             wallModel data =   snapshot.getValue(wallModel.class);
             array.add(data);

//         Toast.makeText(getContext(), array.toString() +" ", Toast.LENGTH_SHORT).show();
         adapter1.notifyDataSetChanged();

     }

     @Override
     public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {


     }

     @Override
     public void onChildRemoved(@NonNull DataSnapshot snapshot) {

         adapter1.notifyDataSetChanged();

     }

     @Override
     public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

     }

     @Override
     public void onCancelled(@NonNull DatabaseError error) {

     }
 });

        return view;

    }

}