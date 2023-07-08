package com.pixelperfect;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class favRecyclerAdapter extends RecyclerView.Adapter <favRecyclerAdapter.ViewHolder>{

    Context context;
    List<wallModel> lists;

    public favRecyclerAdapter(Context context,List<wallModel> lists){
        this.context = context;
        this.lists = lists;
    }
    @NonNull
    @Override
    public favRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.favourate,parent,false);
        ViewHolder v =new ViewHolder(view);
        return  v;
    }

    @Override
    public void onBindViewHolder(@NonNull favRecyclerAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(lists.get(position).url).into(holder.img_wall);
        holder.img_wall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OpenWallActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_wall;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img_wall = itemView.findViewById(R.id.img_fav);

        }
    }
}
