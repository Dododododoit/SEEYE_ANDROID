package com.seeyetech.MVP.seeye;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    //private TextView mTextMessage;

    ImageButton seeyeImageButton;
    ImageButton seeyeImageButton2;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if(getApplicationContext().getClass().getSimpleName() == MenuActivity.class.getSimpleName())
                        return true;
                    Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                    startActivity(intent);
//                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_search:
//                    mTextMessage.setText(R.string.title_search);
                    return true;
                case R.id.navigation_shoppingcart:
//                    mTextMessage.setText(R.string.title_shoppingcart);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        seeyeImageButton = (ImageButton) findViewById(R.id.image_button);
        seeyeImageButton2 = (ImageButton) findViewById(R.id.image_button2);

        seeyeImageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), PopupActivity.class);
                startActivity(i);
            }
        });
//        seeyeImageButton2.setOnClickListener((View.OnClickListener) this);
    }

}
