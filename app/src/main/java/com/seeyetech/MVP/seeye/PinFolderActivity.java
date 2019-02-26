package com.seeyetech.MVP.seeye;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class PinFolderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_folder);
        getSupportActionBar().setTitle("Saved Items");

        android.support.v7.app.ActionBar action = getSupportActionBar();
        action.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar);

        Toolbar toolbar=(Toolbar)action.getCustomView().getParent();
        toolbar.setContentInsetsAbsolute(0, 0);
        toolbar.getContentInsetEnd();
        toolbar.setPadding(0, 0, 0, 0);
        TextView titleView = findViewById(R.id.actionbar_textview);
        titleView.setText("Saved Items");
    }
}
