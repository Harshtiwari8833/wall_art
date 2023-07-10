package com.pixelperfect;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class catAdapter extends RecyclerView.Adapter<wallpaperAdapter.ViewHolder>{
    Context context;
    List<wallModel> list ;
    private Bitmap bitmap;
    public catAdapter(Context context, List<wallModel> list){
        this.context = context;
        this.list= list;
    }
    @NonNull
    @Override
    public wallpaperAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.wall_layout, parent, false);
        return new wallpaperAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull wallpaperAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).url).into(holder.wall_img);
        holder.wall_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int id = list.get(position).id;
                Intent intent = new Intent(context,OpenCatWallActivity.class);
                intent.putExtra("wall_id",id);

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
