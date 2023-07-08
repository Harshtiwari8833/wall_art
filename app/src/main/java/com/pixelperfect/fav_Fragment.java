package com.pixelperfect;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
public class fav_Fragment extends Fragment {


    RecyclerView fav_recycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fav_, container, false);

        fav_recycler = view.findViewById(R.id.recycle_fav);

        GridLayoutManager grid = new GridLayoutManager(getContext(),2);

        return view;

    }
}