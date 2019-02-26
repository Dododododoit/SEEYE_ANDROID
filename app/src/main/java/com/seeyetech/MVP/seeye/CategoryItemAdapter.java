package com.seeyetech.MVP.seeye;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by kaitao on 2/3/19.
 */

public class CategoryItemAdapter extends ArrayAdapter<ProductData> implements View.OnClickListener{

        private ArrayList<ProductData> dataSet;
        Context mContext;

        public CategoryItemAdapter(ArrayList<ProductData> data, Context context) {
            super(context, R.layout.list_row_item, data);
            this.dataSet = data;
            this.mContext=context;

        }

        @Override
        public void onClick(View v) {

        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            ProductData productData = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view

            final View result;

            if (convertView == null) {

                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.list_row_item, parent, false);

            }

            TextView txtName = (TextView) convertView.findViewById(R.id.listview_item_title);
            TextView txtDescription = (TextView) convertView.findViewById(R.id.listview_item_short_description);
            TextView txtRating = (TextView) convertView.findViewById(R.id.listview_item_rating);
            ImageView info = (ImageView) convertView.findViewById(R.id.listview_image);
            ImageView imageButton = (ImageView)  convertView.findViewById(R.id.itemPin);

            if(productData.getPin()==1) {
                imageButton.setImageResource(R.drawable.pinned);
            }
            else {
                imageButton.setImageResource(R.drawable.notpinned);
            }
            txtName.setText(productData.getProductName());
            txtDescription.setText("Price: " + productData.getProductPrice() + "        " + "Rating: " + productData.getRating() + "/5");
            //txtRating.setText("Rating: " + productData.getRating() + "/5");
            Picasso.get().load(productData.getProductImage()).into(info);
            txtName.setTag(productData.getProductId()+"");
            info.setTag(productData.getProductImage());
            return convertView;
        }
}

class ProductData {

    private String productName;
    private Integer productId;
    private String productImage;
    private String productCategory;
    private String productPrice;
    private String rating;
    private Integer pin;

    public ProductData(String productName, int productId, String productImage, String productCategory, String productPrice, String rating, int pin) {
        this.productName = productName;
        this.productId = productId;
        this.productImage = productImage;
        this.productCategory = productCategory;
        this.productPrice = productPrice;
        this.rating = rating;
        this.pin = pin;
    }

    public ProductData(JSONObject obj) {
        try {
            this.productName = obj.getString("Name");
            this.productId = obj.getInt("ProductId");
            this.productImage = obj.getString("Image");
            this.productCategory = obj.getString("Image");
            this.productPrice = obj.getString("Price");
            this.rating = obj.getString("Rating");
            this.pin = obj.getInt("Pin");
        }
        catch (Exception e) {
            //Do nothing
        }
    }

    public String getProductName() {
        return productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public int getProductId() { return productId;}

    public String getRating() {
        return rating;
    }

    public Integer getPin() {
        return pin;
    }

    public void changePin() {
        if(pin == 1)
            pin = 0;
        else
            pin = 1;
    }
}
