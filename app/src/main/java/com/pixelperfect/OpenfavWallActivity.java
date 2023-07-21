package com.pixelperfect;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

public class OpenfavWallActivity extends AppCompatActivity {
    ImageView fav_img;
    String useremail;
    String id;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_openfav_wall);
fav_img = findViewById(R.id.fav_img);
         url = getIntent().getStringExtra("wall_pos1");
         id =getIntent().getStringExtra("wall_id");

        Glide.with(this).load(url).into(fav_img);

        fav_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(OpenfavWallActivity.this,R.style.CustomBottomSheetDialogTheme);
                bottomSheetDialog.setContentView(R.layout.favsetwallpaperdilog);
                ImageView remove_fav = bottomSheetDialog.findViewById(R.id.remove_fav);
                SharedPreferences pref1 = getSharedPreferences("email", MODE_PRIVATE);
                useremail = pref1.getString("email_login", "");
                remove_fav.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatabaseReference reference3 = FirebaseDatabase.getInstance().getReference().child("users").child(useremail).child("favouraite");

                        reference3.child(id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(OpenfavWallActivity.this, "Wallpaper removed!", Toast.LENGTH_SHORT).show();
                                SharedPreferences pref1 = getSharedPreferences("load_fragment", MODE_PRIVATE);
                                 pref1.getBoolean("flag", false);
                               SharedPreferences.Editor editor = pref1.edit();
                               editor.putBoolean("flag", true);
                                editor.apply();
                                onBackPressed();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(OpenfavWallActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                Button set_wall = bottomSheetDialog.findViewById(R.id.set_wall);

                set_wall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showSetWallpaperDialog(url);
                    }
                });


                bottomSheetDialog.show();
            }
        });


    }
    private void showSetWallpaperDialog(String wallpaperUrl) {
        AlertDialog.Builder builder = new AlertDialog.Builder(OpenfavWallActivity.this);
        builder.setTitle("Set Wallpaper");
        builder.setMessage("Set wallpaper for:");
        builder.setCancelable(true);

        builder.setPositiveButton("Home Screen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setWallpaper(wallpaperUrl, WallpaperManager.FLAG_SYSTEM);
              startActivity(new Intent(OpenfavWallActivity.this, MainActivity.class));
            }
        });

        builder.setNegativeButton("Lock Screen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setWallpaper(wallpaperUrl, WallpaperManager.FLAG_LOCK);
             startActivity(new Intent(OpenfavWallActivity.this, MainActivity.class));
            }
        });

        builder.setNeutralButton("Both", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setWallpaper(wallpaperUrl, WallpaperManager.FLAG_SYSTEM | WallpaperManager.FLAG_LOCK);
               startActivity(new Intent(OpenfavWallActivity.this, MainActivity.class));
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private void setWallpaper(String wallpaperUrl ,int flag) {
        Glide.with(OpenfavWallActivity.this)
                .asBitmap()
                .load(wallpaperUrl)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        WallpaperManager wallpaperManager = WallpaperManager.getInstance(OpenfavWallActivity.this);
                        try {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                wallpaperManager.setBitmap(resource, null, true, flag);
                            }
                            Toast.makeText(OpenfavWallActivity.this, "Wallpaper set successfully", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(OpenfavWallActivity.this, "Failed to set wallpaper", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                        // Not used
                    }
                });
    }
}