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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemListFragment extends Fragment {

    String title;
    String URL = "http://10.0.2.2:8000/lookup.php?cat=";
    String URL_PIN = "http://10.0.2.2:8000/lookup.php?pin=1";


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
        title = ((AppCompatActivity)getActivity())
                .getSupportActionBar()
                .getTitle()
                .toString()
                .split(" ")[0]
                .toLowerCase();
        loadItems();
    }

    @Override
    public void onResume() {
        super.onResume();
        String barName = title.toLowerCase().split(" ")[0];
        if(barName.equals("saved")) {
            loadItems();
        }
    }

    void loadItems() {
        String barName = title.toLowerCase().split(" ")[0];
        String catDB = barName.substring(0, 1).toUpperCase() + barName.substring(1);
        ArrayList<ProductData> productDataList = new ArrayList<>();
        CategoryItemAdapter categoryItemAdapter = new CategoryItemAdapter(productDataList, getContext());
        ListView androidListView = (ListView) getActivity().findViewById(R.id.list_view);
        androidListView.setAdapter(categoryItemAdapter);

        if(!catDB.equals("Saved")) {
            loadDB(URL + catDB, categoryItemAdapter);
        }
        else {
            loadDB(URL_PIN, categoryItemAdapter);
        }
    }

    void addOnClickListener() {
        ListView listView = getActivity().findViewById(R.id.list_view);

        listView.setOnItemClickListener(new ListView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                LinearLayout linearLayout = (LinearLayout) view;
                ImageView imageView = (ImageView) linearLayout.getChildAt(0);
                LinearLayout linearLayout1 = (LinearLayout) linearLayout.getChildAt(1);
                TextView textView = (TextView) linearLayout1.getChildAt(0);
                //String Name = textView.getText().toString().toLowerCase().split(" ")[0];
                Intent intent = new Intent(getActivity(), PopupActivity.class);
//                intent.putExtra("name", Name);
//                intent.putExtra("ImageLink", );
                int productID = Integer.parseInt(textView.getTag().toString());
                String imageLink = imageView.getTag().toString();
                intent.putExtra("productID", productID);
                intent.putExtra("imageLink", imageLink);
                startActivity(intent);
            }
        });



    }

    void loadDB(final String url, final CategoryItemAdapter categoryItemAdapter) {
        Ion.with(this)
                .load(url)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if(e == null) {
                            try {
                                JSONArray jsonArray = new JSONArray(result);
                                for (int i = 0; i < jsonArray.length(); ++i) {
                                    JSONObject obj = jsonArray.getJSONObject(i);
                                    categoryItemAdapter.add(new ProductData(obj));
                                }
                            }
                            catch (Exception ex) {
                                //Do nothing
                            }
                            finally {
                                addOnClickListener();
                            }
                        }
                    }
                });


    }


}
