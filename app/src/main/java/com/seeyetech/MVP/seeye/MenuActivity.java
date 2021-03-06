package com.seeyetech.MVP.seeye;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    ImageButton chairButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        android.support.v7.app.ActionBar action = getSupportActionBar();
        action.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar);

        Toolbar toolbar=(Toolbar)action.getCustomView().getParent();
        toolbar.setContentInsetsAbsolute(0, 0);
        toolbar.getContentInsetEnd();
        toolbar.setPadding(0, 0, 0, 0);


        setUpPinButton();
    }


    @Override
    protected void onResume() {
        super.onResume();
        setUpPinButton();

    }

    void setUpPinButton() {
        FloatingActionButton floatingButton = (FloatingActionButton) findViewById(R.id.fab);
        MyProperties instance = MyProperties.getInstance();
        if(!instance.isPinSet) {
            instance.isPinSet = true;
            instance.pinX = floatingButton.getX();
            instance.pinY = floatingButton.getY();
        }
        else {
            floatingButton.setX(instance.pinX);
            floatingButton.setY(instance.pinY);
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


