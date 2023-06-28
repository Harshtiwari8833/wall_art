package com.pixelperfect;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class wall_Fragment extends Fragment {

    RecyclerView recycler;
    ArrayList<wallModel> array = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wall_, container, false);
        //toolbar

        recycler = view.findViewById(R.id.wall_recycler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recycler.setLayoutManager(gridLayoutManager);

        array.add(new wallModel(R.drawable.wall1));
        array.add(new wallModel(R.drawable.wall2));
        array.add(new wallModel(R.drawable.wall3));
        array.add(new wallModel(R.drawable.wall4));
        array.add(new wallModel(R.drawable.wall5));
        array.add(new wallModel(R.drawable.wall6));
        array.add(new wallModel(R.drawable.wall7));
        array.add(new wallModel(R.drawable.wall8));
        array.add(new wallModel(R.drawable.wall9));
        array.add(new wallModel(R.drawable.wall10));
        array.add(new wallModel(R.drawable.wall11));
        array.add(new wallModel(R.drawable.wall12));
        array.add(new wallModel(R.drawable.wall13));
        array.add(new wallModel(R.drawable.wall14));
        array.add(new wallModel(R.drawable.wall15));
        array.add(new wallModel(R.drawable.wall16));
        array.add(new wallModel(R.drawable.wall17));
        array.add(new wallModel(R.drawable.wall18));

        wallpaperAdapter adapter1 = new wallpaperAdapter(getContext(),array);
        recycler.setAdapter(adapter1);

        adapter1.notifyDataSetChanged();

        return view;
    }
}
