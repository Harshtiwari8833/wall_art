package com.pixelperfect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapterCAT extends RecyclerView.Adapter<RecyclerAdapterCAT.ViewHolder> {

    Context context;
    ArrayList<catModel> arrayList;

    RecyclerAdapterCAT(Context context, ArrayList<catModel> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public RecyclerAdapterCAT.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.category,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterCAT.ViewHolder holder, int position) {

        holder.img.setImageResource(arrayList.get(position).img);
        holder.type.setText(arrayList.get(position).type);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView type;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);
            type = itemView.findViewById(R.id.txt);
        }
    }
}
