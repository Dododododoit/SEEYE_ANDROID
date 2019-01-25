package com.seeyetech.MVP.seeye;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class PopupActivity extends Activity {

    private TextView link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.7),(int)(height*0.5));

        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.dimAmount = 0.75f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(layoutParams);

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        link = (TextView) findViewById(R.id.link);

        link.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.macys.com/shop/product/sunset-coffee-table-quick-ship?ID=2911648&CategoryID=35423#fn=sp%3D1%26spc%3D1538%26ruleId%3D129%7CBOOST%20SAVED%20SET%7CBOOST%20ATTRIBUTE%26searchPass%3DmatchNone%2"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


        Intent intent = getIntent();
        String str = intent.getStringExtra("name");
        if(str != null) {
            int id = ChairActivity.typeMap.get(str);
            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            imageView.setImageResource(id);
        }

    }
}
