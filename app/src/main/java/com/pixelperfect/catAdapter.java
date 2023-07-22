package com.pixelperfect;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class catAdapter extends RecyclerView.Adapter<catAdapter.ViewHolder>{
    Context context;
    List<wallModel> list ;
    String id;
    String url;
    private Bitmap bitmap;
    public catAdapter(Context context, List<wallModel> list){
        this.context = context;
        this.list= list;
    }
    @NonNull
    @Override
    public catAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.wall_layout, parent, false);
        return new catAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull catAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {



        Glide
                .with(context)
                .load(list.get(position).url)
                .apply(new RequestOptions().override(170, 280))
                .centerCrop()
                .into(holder.wall_img);
        holder.wall_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(context,OpenCatWallActivity.class);
                intent.putExtra("wall_pos1",position);

                context.startActivity(intent);
            }
        });
        SharedPreferences pref1 = context.getSharedPreferences("email", MODE_PRIVATE);
        String useremail = pref1.getString("email_login", "");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users").child(useremail).child("favouraite");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> list1 = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String id = dataSnapshot.child("url").getValue(String.class);
                    list1.add(id);

                }

                if(list1.contains(list.get(position).url)){
                    holder.cbHeart.setChecked(true);


                }else{
                    holder.cbHeart.setChecked(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, "something went wrong!", Toast.LENGTH_SHORT).show();
            }



        });


        holder.cbHeart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(holder.cbHeart.isChecked()){
                    id = list.get(position).getId();
                    String user_id = String.valueOf(id);
                    url = list.get(position).getUrl();
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users").child(useremail).child("favouraite").child(user_id);

                    HashMap hashMap = new HashMap<>();
                    hashMap.put("id", id);
                    hashMap.put("url",url);
                    reference.setValue(hashMap);

                }else{

                    DatabaseReference reference3 = FirebaseDatabase.getInstance().getReference().child("users").child(useremail).child("favouraite");
                    String id = list.get(position).getId();
                    String fav_id = String.valueOf(id);
                    reference3.child(fav_id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context, "something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView wall_img;
        CheckBox cbHeart;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            wall_img = itemView.findViewById(R.id.wall_img);
            cbHeart = itemView.findViewById(R.id.cbHeart);
        }
    }
}
