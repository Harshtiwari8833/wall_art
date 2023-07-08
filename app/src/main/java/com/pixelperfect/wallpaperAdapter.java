package com.pixelperfect;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;


public class wallpaperAdapter extends RecyclerView.Adapter<wallpaperAdapter.ViewHolder>{
    Context context;
    List<wallModel> list ;
    private Bitmap bitmap;
    public wallpaperAdapter(Context context, List<wallModel> list){
        this.context = context;
        this.list= list;
    }
    @NonNull
    @Override
    public wallpaperAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.wall_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).url).into(holder.wall_img);
        holder.wall_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "hello app under-construction", Toast.LENGTH_SHORT).show();
                
                Intent intent = new Intent(context,OpenWallActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView wall_img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            wall_img = itemView.findViewById(R.id.wall_img);
        }
    }

    }
