package com.example.arunn.silfraagri.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arunn.silfraagri.R;
import com.example.arunn.silfraagri.activities.Weather_activity;

public class RecyclerViewFragment extends Fragment {


    public RecyclerViewFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);
        Bundle bundle = this.getArguments();
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Weather_activity weatheractivity = (Weather_activity) getActivity();
        recyclerView.setAdapter(weatheractivity.getAdapter(bundle.getInt("day")));
        return view;
    }

}
