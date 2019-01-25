package com.seeyetech.MVP.seeye;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChairActivity extends AppCompatActivity {

    public static final Map<String,Integer> typeMap = new HashMap<String, Integer>()
    {
        {
            put("chair", R.drawable.chair);
            put("bed", R.drawable.beds);
            put("table", R.drawable.tables);
            put("sofa", R.drawable.sofa);
            put("carbinet", R.drawable.cabinet);
            put("others", R.drawable.sofa);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chair);


        String barName = getIntent().getStringExtra("category_name").toLowerCase();
        if(barName == null) {
            barName = "SEEYE";
        }
        String barNameTitle = barName.toUpperCase() + " LIST";

        getSupportActionBar().setTitle(barNameTitle);



        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < 20; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_title", barName + " item " +i);
            hm.put("listview_discription", barName + " short description");
            hm.put("listview_image", Integer.toString(typeMap.get(barName)));
            aList.add(hm);
        }

        String[] from = {"listview_image", "listview_title", "listview_discription"};
        int[] to = {R.id.listview_image, R.id.listview_item_title, R.id.listview_item_short_description};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), aList, R.layout.list_row_item, from, to);
        ListView androidListView = (ListView) findViewById(R.id.list_view);
        androidListView.setAdapter(simpleAdapter);

        addOnClickListener();
    }

    void addOnClickListener() {
        ListView listView = findViewById(R.id.list_view);

        listView.setOnItemClickListener(new ListView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                LinearLayout linearLayout = (LinearLayout) view;
                LinearLayout linearLayout1 = (LinearLayout) linearLayout.getChildAt(1);
                TextView textView = (TextView) linearLayout1.getChildAt(0);
                String Name = textView.getText().toString().split(" ")[0];
                Intent intent = new Intent(ChairActivity.this, PopupActivity.class);
                intent.putExtra("name", Name);
                startActivity(intent);
            }
        });

    }
}
