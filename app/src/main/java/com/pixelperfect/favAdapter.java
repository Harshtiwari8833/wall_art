package com.pixelperfect;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class favAdapter extends RecyclerView.Adapter<favAdapter.ViewHolder>  {
    Context context;

    public favAdapter(Context context, ArrayList<wallModel> list) {
        this.context = context;
        this.list = list;
    }

    ArrayList<wallModel> list;

    @NonNull
    @Override
    public favAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.fav_layout, parent, false);

       return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull favAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

                Glide
                .with(context)
                .load(list.get(position).url)
                .apply(new RequestOptions().override(170, 280))
                .centerCrop()
                .into(holder.wall_img);
        holder.wall_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OpenfavWallActivity.class);
                intent.putExtra("wall_pos1",list.get(position).getUrl());
                intent.putExtra("wall_id",list.get(position).getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView wall_img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            wall_img= itemView.findViewById(R.id.wall_img);
        }
    }
}
