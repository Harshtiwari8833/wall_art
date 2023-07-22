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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class wallpaperAdapter extends RecyclerView.Adapter<wallpaperAdapter.ViewHolder>{
    Context context;
    String emailUsername;
    String url;
    String id;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        url = list.get(position).getUrl();

                Glide
                .with(context)
                .load(url)
                .apply(new RequestOptions().override(170, 280))
                .centerCrop()
                .into(holder.wall_img);
//        Picasso.get().load(url).resize(50,50).centerCrop().into(holder.wall_img);

        holder.wall_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = list.get(position).id;
                Intent intent = new Intent(context,OpenWallActivity.class);
//                intent.putExtra("wall_id",id);
                intent.putExtra("wall_pos",position);
                context.startActivity(intent);
            }
        });

        SharedPreferences pref = context.getSharedPreferences("user_email", MODE_PRIVATE);
       String user_email =  pref.getString("email", "");
        emailUsername = user_email;
        int index = emailUsername.indexOf('@');
        emailUsername = emailUsername.substring(0,index);
        SharedPreferences pref1 = context.getSharedPreferences("email", MODE_PRIVATE);
         String useremail = pref1.getString("email_login", "");
        SharedPreferences.Editor editor = pref1.edit();
        editor.putString("email_login",emailUsername );
        editor.apply();


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
                 String wall_url = list.get(position).url;
                  DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users").child(emailUsername).child("favouraite").child(user_id);

                  HashMap hashMap = new HashMap<>();
                  hashMap.put("id", id);
                  hashMap.put("url",wall_url);
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
