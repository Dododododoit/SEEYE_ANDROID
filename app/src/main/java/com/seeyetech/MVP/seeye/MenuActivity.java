package com.seeyetech.MVP.seeye;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    ImageButton chairButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


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
//            Toast.makeText(this, "Pin set", Toast.LENGTH_SHORT);
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
                Intent intent = new Intent(MenuActivity.this, PinFolderActivity.class);
                startActivity(intent);
            }
        });
    };


}


