package com.seeyetech.MVP.seeye;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemListFragment extends Fragment {

    String title;



    public ItemListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_item_list, container, false);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
        title = ((AppCompatActivity)getActivity())
                .getSupportActionBar()
                .getTitle()
                .toString()
                .split(" ")[0]
                .toLowerCase();
        String barName = title.toLowerCase().split(" ")[0];
        for (int i = 0; i < 20; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_title", barName + " item " +i);
            hm.put("listview_discription", barName + " short description");
            hm.put("listview_image", Integer.toString(Constants.typeMap.get(barName)));
            aList.add(hm);
        }

        String[] from = {"listview_image", "listview_title", "listview_discription"};
        int[] to = {R.id.listview_image, R.id.listview_item_title, R.id.listview_item_short_description};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), aList, R.layout.list_row_item, from, to);
        ListView androidListView = (ListView) getActivity().findViewById(R.id.list_view);
        androidListView.setAdapter(simpleAdapter);

        addOnClickListener();
    }

    void addOnClickListener() {
        ListView listView = getActivity().findViewById(R.id.list_view);

        listView.setOnItemClickListener(new ListView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                LinearLayout linearLayout = (LinearLayout) view;
                LinearLayout linearLayout1 = (LinearLayout) linearLayout.getChildAt(1);
                TextView textView = (TextView) linearLayout1.getChildAt(0);
                String Name = textView.getText().toString().split(" ")[0];
                Intent intent = new Intent(getActivity(), PopupActivity.class);
                intent.putExtra("name", Name);
                startActivity(intent);
            }
        });

    }

}
