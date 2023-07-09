package com.pixelperfect;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class openWallAdapter extends RecyclerView.Adapter<openWallAdapter.wallViewHolder> {
Context context;


    public openWallAdapter(Context context, List<wallModel> list) {
        this.context = context;
        this.list = list;
    }

    List<wallModel> list;

    @NonNull
    @Override
    public openWallAdapter.wallViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(context).inflate(R.layout.each_wallpaper,parent,false);

        return new wallViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull openWallAdapter.wallViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(list.get(position).url).into(holder.wallpaper_img);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Set Wallpaper");
        builder.setMessage("Set wallpaper for:");
        builder.setCancelable(true);
        holder.set_wall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSetWallpaperDialog(list.get(position).url);

            }
        });




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class wallViewHolder  extends RecyclerView.ViewHolder{

        ImageView wallpaper_img, set_wall;

        public wallViewHolder(@NonNull View itemView) {
            super(itemView);

            set_wall  = itemView.findViewById(R.id.set_wall);
            wallpaper_img = itemView.findViewById(R.id.wallpaper_img);

        }
    }

//    public void setWallpaper(ImageView imageView){
//        Bitmap bitmap =((BitmapDrawable) imageView.getDrawable()).getBitmap();
//        WallpaperManager manager = WallpaperManager.getInstance(context);
//        try{
//            manager.setBitmap(bitmap);
//        }catch (Exception e){
//            Toast.makeText(context, "something went wrong!", Toast.LENGTH_SHORT).show();
//        e.printStackTrace();
//        }
//
//    }


    private void showSetWallpaperDialog(String wallpaperUrl) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Set Wallpaper");
        builder.setMessage("Set wallpaper for:");
        builder.setCancelable(true);

        builder.setPositiveButton("Home Screen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setWallpaper(wallpaperUrl, WallpaperManager.FLAG_SYSTEM);
                context.startActivity(new Intent(context, MainActivity.class));
            }
        });

        builder.setNegativeButton("Lock Screen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setWallpaper(wallpaperUrl, WallpaperManager.FLAG_LOCK);
                context.startActivity(new Intent(context, MainActivity.class));
            }
        });

        builder.setNeutralButton("Both", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setWallpaper(wallpaperUrl, WallpaperManager.FLAG_SYSTEM | WallpaperManager.FLAG_LOCK);
                context.startActivity(new Intent(context, MainActivity.class));
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private void setWallpaper(String wallpaperUrl ,int flag) {
        Glide.with(context)
                .asBitmap()
                .load(wallpaperUrl)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
                        try {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                wallpaperManager.setBitmap(resource, null, true, flag);
                            }
                            Toast.makeText(context, "Wallpaper set successfully", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "Failed to set wallpaper", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                        // Not used
                    }
                });
    }
}
