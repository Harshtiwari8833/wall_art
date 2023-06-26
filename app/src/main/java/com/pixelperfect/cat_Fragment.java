package com.pixelperfect;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class cat_Fragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

   private RecyclerView recyclerView;
    ArrayList<catModel> arrayList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cat_, container, false);

//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);

        recyclerView = view.findViewById(R.id.recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

            arrayList.add(new catModel("Nature", R.drawable.nature1));
            arrayList.add(new catModel("City", R.drawable.nature1));
            arrayList.add(new catModel("Animal", R.drawable.nature1));
            arrayList.add(new catModel("Landscape", R.drawable.nature1));
            arrayList.add(new catModel("Amoled", R.drawable.nature1));
            arrayList.add(new catModel("Dark", R.drawable.nature1));
            arrayList.add(new catModel("Anime", R.drawable.nature1));
            arrayList.add(new catModel("Cars", R.drawable.nature1));
            arrayList.add(new catModel("Sports", R.drawable.nature1));
            arrayList.add(new catModel("Space", R.drawable.nature1));
            arrayList.add(new catModel("SuperHeros", R.drawable.nature1));
            arrayList.add(new catModel("iOS", R.drawable.nature1));
            arrayList.add(new catModel("Solid", R.drawable.nature1));
            arrayList.add(new catModel("Abstract", R.drawable.nature1));
            arrayList.add(new catModel("Shapes", R.drawable.nature1));
            arrayList.add(new catModel("Minimal", R.drawable.nature1));

   RecyclerAdapterCAT adapter = new RecyclerAdapterCAT(getContext(), arrayList);

            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();



        return view;
    }
}
