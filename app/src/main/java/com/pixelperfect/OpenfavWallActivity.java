package com.pixelperfect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class OpenfavWallActivity extends AppCompatActivity {
    ImageView fav_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_openfav_wall);
fav_img = findViewById(R.id.fav_img);
         String url = getIntent().getStringExtra("wall_pos1");

        Glide.with(this).load(url).into(fav_img);


    }
}