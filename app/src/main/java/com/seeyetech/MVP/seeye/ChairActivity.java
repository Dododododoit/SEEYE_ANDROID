package com.seeyetech.MVP.seeye;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChairActivity extends AppCompatActivity {



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

        setUpPinButton();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpPinButton();

    }

    void setUpPinButton() {
        FloatingActionButton floatingButton = (FloatingActionButton) findViewById(R.id.fab);
//        floatingButton.setAlpha(0.65f);
        MyProperties instance = MyProperties.getInstance();
        if(!instance.isPinSet) {
            instance.isPinSet = true;
            instance.pinX = floatingButton.getX();
            instance.pinY = floatingButton.getY();
            Log.v("First time pin", instance.pinX + " " + instance.pinY );
        }
        else {
//            TranslateAnimation animation = new TranslateAnimation(0, 0, instance.pinX, instance.pinY);
//            animation.setDuration(0); // duartion in ms
//            animation.setFillAfter(false);
//            floatingButton.startAnimation(animation);
            floatingButton.setX(instance.pinX);
            floatingButton.setY(instance.pinY);
            Log.v("Continuing time pin", instance.pinX + " " + instance.pinY );
        }
        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChairActivity.this, PinFolderActivity.class);
                startActivity(intent);
            }
        });
    };

}
