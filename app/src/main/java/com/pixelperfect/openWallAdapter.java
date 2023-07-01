package com.pixelperfect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class openWallAdapter extends RecyclerView.Adapter<openWallAdapter.wallViewHolder> {
Context context;


    public openWallAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    List<String> list;

    @NonNull
    @Override
    public openWallAdapter.wallViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(context).inflate(R.layout.each_wallpaper,parent,false);

        return new wallViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull openWallAdapter.wallViewHolder holder, int position) {
        Glide.with(context).load(list.get(position)).into(holder.wallpaper_img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class wallViewHolder  extends RecyclerView.ViewHolder{

        ImageView wallpaper_img;
        public wallViewHolder(@NonNull View itemView) {
            super(itemView);

            wallpaper_img = itemView.findViewById(R.id.wallpaper_img);

        }
    }
}
