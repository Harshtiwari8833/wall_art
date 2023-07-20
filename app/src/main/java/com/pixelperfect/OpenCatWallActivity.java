package com.pixelperfect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OpenCatWallActivity extends AppCompatActivity {
    ViewPager2 viewPager;
    List<wallModel> list = new ArrayList<>();
    openWallAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_open_cat_wall);
        viewPager = findViewById(R.id.viewPager);
        adapter= new openWallAdapter(this,list);

        int pos = getIntent().getExtras().getInt("wall_pos1");


        SharedPreferences preferences = getSharedPreferences("Category",MODE_PRIVATE);
        String category  = preferences.getString("cat","");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("wallpaper");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String cate  =   dataSnapshot.child("cat").getValue(String.class);

                    if (cate.matches(category)) {
                        list.add(dataSnapshot.getValue(wallModel.class));
                    }

                }
                adapter= new openWallAdapter(OpenCatWallActivity.this,list);
                viewPager.setAdapter(adapter);

                viewPager.setCurrentItem(pos,false);


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






    }
}