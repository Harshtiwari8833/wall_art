package com.pixelperfect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OpenWallActivity extends AppCompatActivity {
  ViewPager2 viewPager;
  List<wallModel> list = new ArrayList<>();
  openWallAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_open_wall);
   int id = getIntent().getExtras().getInt("wall_id");
        String wallid = String.valueOf(id);
         viewPager = findViewById(R.id.viewPager);
        adapter= new openWallAdapter(this,list);

        viewPager.setAdapter(adapter);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("wallpaper");
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("wallpaper").child(wallid);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Toast.makeText(OpenWallActivity.this, snapshot.toString(), Toast.LENGTH_SHORT).show();
                list.add(snapshot.getValue(wallModel.class));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    list.add(dataSnapshot.getValue(wallModel.class));
                }


                for (int i = id; i>0; i-- ){
                    list.remove(i);
                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        viewPager = findViewById(R.id.viewPager);
        adapter= new openWallAdapter(this,list);
        viewPager.setAdapter(adapter);


    }
}