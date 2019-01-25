package com.seeyetech.MVP.seeye;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;

public class MenuActivity extends AppCompatActivity {

    ImageButton chairButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().setTitle("SEEYE CATEGORY");
    }

}


