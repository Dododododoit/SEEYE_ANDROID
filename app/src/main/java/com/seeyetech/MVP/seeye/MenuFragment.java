package com.seeyetech.MVP.seeye;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {


    RecyclerView mRecyclerView;
    List<CategoryData> mCatList;
    CategoryData mCatData;

    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView = getActivity().findViewById(R.id.recyclerview);
        mRecyclerView.setNestedScrollingEnabled(false);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        mCatList = new ArrayList<>();
        mCatData = new CategoryData("Chair", "",
                R.drawable.chair);
        mCatList.add(mCatData);
        mCatData = new CategoryData("Bed", "",
                R.drawable.beds);
        mCatList.add(mCatData);
        mCatData = new CategoryData("Table", "",
                R.drawable.tables);
        mCatList.add(mCatData);
        mCatData = new CategoryData("Sofa", "",
                R.drawable.sofa);
        mCatList.add(mCatData);
        mCatData = new CategoryData("Carbinet", "",
                R.drawable.cabinet);
        mCatList.add(mCatData);
        mCatData = new CategoryData("Others", "",
                R.drawable.sofa);
        mCatList.add(mCatData);

        MenuAdapter myAdapter = new MenuAdapter(getActivity(), mCatList);
        mRecyclerView.setAdapter(myAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        //((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("SEEYE CATEGORY");
        Log.v("MenuFrag", "resume");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("MenuFrag", "DESTROY");
    }

    @Override
    public void onStart() {
        super.onStart();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("SEEYE CATEGORY");
    }
}
