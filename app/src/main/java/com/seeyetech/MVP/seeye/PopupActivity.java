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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

public class PopupActivity extends Activity {

    private TextView link;
    String URL = "http://10.0.2.2:8000/lookup.php?id=";
    String URL_ALTER = "http://10.0.2.2:8000/alter.php?";
    ProductData productData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.7),(int)(height*0.6));

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
        Integer productId = intent.getIntExtra("productID", 0);
        String link = intent.getStringExtra("imageLink");
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        if(link == null) {
            int id = Constants.typeMap.get(link);
            imageView.setImageResource(id);
        }
        else {
            Picasso.get().load(link).into(imageView);
        }

        loadPinInfo(productId);
    }
    void loadPinInfo(int productId) {
        Ion.with(this)
                .load(URL + productId)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if(e == null) {
                            try {
                                JSONArray jsonArray = new JSONArray(result);
                                for (int i = 0; i < jsonArray.length(); ++i) {
                                    JSONObject obj = jsonArray.getJSONObject(i);
                                    productData = new ProductData(obj);
                                    Button button = findViewById(R.id.pinButton);
                                    updateButton();
                                    TextView textView = findViewById(R.id.link);
                                    textView.setText(productData.getRating() + " out of 5");
                                    button.setOnClickListener(new PinLisener());
                                }
                            }
                            catch (Exception ex) {
                                //Do nothing
                            }
                        }
                    }
                });

    }

    class PinLisener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            final String toastString;
            if(productData.getPin() == 0) {
                toastString = "Added to Pin Folder";
            }
            else {
                toastString = "Removed from Pin Folder";
            }
            productData.changePin();
            Ion.with(getApplication())
                    .load(URL_ALTER + "?id=" + productData.getProductId() + "&pin=" + productData.getPin())
                    .asString()
                    .setCallback(new FutureCallback<String>() {
                        @Override
                        public void onCompleted(Exception e, String result) {
                            //Do nothing
                            if(e != null) {
                                Toast.makeText(getApplication(), "Failed, please try again", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(getApplication(), "Successfully " + toastString, Toast.LENGTH_SHORT).show();
                                updateButton();
                            }
                        }
                    });
        }
    }


    void updateButton() {
        Button button = findViewById(R.id.pinButton);
        if(productData.getPin()==1) {
            button.setText("Remove from Pin Folder");
        }
        else {
            button.setText("Add to Pin Folder");
        }
    }
}
