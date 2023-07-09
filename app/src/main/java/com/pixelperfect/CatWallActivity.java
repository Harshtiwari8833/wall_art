package com.pixelperfect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CatWallActivity extends AppCompatActivity {
    RecyclerView recycler;
    wallpaperAdapter adapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_wall);
        recycler = findViewById(R.id.wall_recycler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(CatWallActivity.this, 2);
        recycler.setLayoutManager(gridLayoutManager);
 String category = getIntent().getStringExtra("cat");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("category").child(category);
        reference.addValueEventListener(new ValueEventListener() {
            List<wallModel> array = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    wallModel data =   dataSnapshot.getValue(wallModel.class);
                    array.add(data);


                }
                adapter1 = new wallpaperAdapter(CatWallActivity.this,array);

                recycler.setAdapter(adapter1);
                adapter1.notifyDataSetChanged();
                recycler.setNestedScrollingEnabled(true);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(CatWallActivity.this, "something went wrong!", Toast.LENGTH_SHORT).show();
            }

        });
    }
}