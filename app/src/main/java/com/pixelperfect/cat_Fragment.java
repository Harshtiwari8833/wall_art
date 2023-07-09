package com.pixelperfect;

import android.content.Context;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class cat_Fragment extends Fragment {

   RecyclerView recyclerView;
    ArrayList<catModel> arrayList = new ArrayList<>();

    CardView card_nature, card_city,card_animal, card_landscape,card_amoled,card_dark,
                card_anime,card_cars, card_sports, card_space,card_superhero,card_ios,
                    card_solid, card_abstract, card_shapes,card_minimal;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cat_, container, false);

        card_nature = view.findViewById(R.id.card_nature);
        card_animal = view.findViewById(R.id.card_animal);
        card_landscape = view.findViewById(R.id.card_landscape);
        card_amoled = view.findViewById(R.id.card_amoled);
        card_dark = view.findViewById(R.id.card_dark);
        card_city = view.findViewById(R.id.card_city);
        card_anime = view.findViewById(R.id.card_anime);
        card_cars = view.findViewById(R.id.card_cars);
        card_sports = view.findViewById(R.id.card_sports);
        card_space = view.findViewById(R.id.card_space);
        card_superhero = view.findViewById(R.id.card_superhero);
        card_ios = view.findViewById(R.id.card_ios);
        card_solid = view.findViewById(R.id.card_solid);
        card_abstract = view.findViewById(R.id.card_abstract);
        card_shapes = view.findViewById(R.id.card_shapes);
        card_minimal = view.findViewById(R.id.card_minimal);

        card_nature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        return view;




        /*recyclerView = view.findViewById(R.id.recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);


            arrayList.add(new catModel("Nature", R.drawable.nature0));
            arrayList.add(new catModel("City", R.drawable.city0));
            arrayList.add(new catModel("Animal", R.drawable.animal0));
            arrayList.add(new catModel("Landscape", R.drawable.landscape0));
            arrayList.add(new catModel("Amoled", R.drawable.amoled0));
            arrayList.add(new catModel("Dark", R.drawable.dark0));
            arrayList.add(new catModel("Anime", R.drawable.anime0));
            arrayList.add(new catModel("Cars", R.drawable.cars0));
            arrayList.add(new catModel("Sports", R.drawable.sports0));
            arrayList.add(new catModel("Space", R.drawable.space0));
            arrayList.add(new catModel("SuperHeros", R.drawable.superhero0));
            arrayList.add(new catModel("iOS", R.drawable.ios0));
            arrayList.add(new catModel("Solid", R.drawable.solid02));
            arrayList.add(new catModel("Abstract", R.drawable.abst02));
            arrayList.add(new catModel("Shapes", R.drawable.shape0));
            arrayList.add(new catModel("Minimal", R.drawable.minimal0));

   RecyclerAdapterCAT adapter = new RecyclerAdapterCAT(getContext(), arrayList);

            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();  */


    }

}

