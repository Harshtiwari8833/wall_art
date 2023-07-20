package com.pixelperfect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
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

  openWallAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_open_wall);


         int pos = getIntent().getExtras().getInt("wall_pos");


         viewPager = findViewById(R.id.viewPager);



        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("wallpaper");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<wallModel> list = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    list.add(dataSnapshot.getValue(wallModel.class));
                }
                adapter= new openWallAdapter(OpenWallActivity.this,list);

                viewPager.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                viewPager.setCurrentItem(pos,false);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
}