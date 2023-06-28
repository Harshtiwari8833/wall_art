package com.pixelperfect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;


public class wallpaperAdapter extends RecyclerView.Adapter<wallpaperAdapter.ViewHolder>{
    Context context;
    ArrayList<wallModel> list;
    @NonNull
    @Override
    public wallpaperAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.fragment_wall_, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull wallpaperAdapter.ViewHolder holder, int position) {
//
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.wall_img);


        }
    }
}
