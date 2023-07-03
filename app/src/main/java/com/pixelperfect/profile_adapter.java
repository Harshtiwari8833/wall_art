/* package com.pixelperfect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class profile_adapter extends RecyclerView.Adapter<profile_adapter.ViewHolder>
{
    Context context;
    ArrayList<profileModel> arrayList;

    profile_adapter(Context context, ArrayList<profileModel> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public profile_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.profile_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull profile_adapter.ViewHolder holder, int position) {
        holder.txt.setText(arrayList.get(position).txt);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt = itemView.findViewById(R.id.txt_profile);
        }
    }
}

 */
