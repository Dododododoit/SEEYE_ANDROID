package com.seeyetech.MVP.seeye;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kaitao on 1/23/19.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuViewHolder>{
    private Context mContext;
    private List<CategoryData> mCategoryList;

    MenuAdapter(Context mContext, List< CategoryData > mCategoryList) {
        this.mContext = mContext;
        this.mCategoryList = mCategoryList;
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        MenuViewHolder menuViewHolder = new MenuViewHolder(mView, mContext);
        return menuViewHolder;
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position) {
        holder.mImage.setImageResource(mCategoryList.get(position).getCatImage());
        holder.mTitle.setText(mCategoryList.get(position).getCatName());
        holder.mImage.setContentDescription(mCategoryList.get(position).getCatDescription());
    }

    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }

}



class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView mImage;
    TextView mTitle;
    Context context;

    MenuViewHolder(View itemView, Context context) {
        super(itemView);
        mImage = itemView.findViewById(R.id.ivImage);
        mTitle = itemView.findViewById(R.id.tvTitle);
        mImage.setOnClickListener(this);
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(context, ChairActivity.class);
        intent.putExtra("category_name", mTitle.getText());
        context.startActivity(intent);
    }
}


class CategoryData {

    private String catName;
    private String catDescription;
    private int catImage;

    public CategoryData(String catName, String catDescription, int catImage) {
        this.catName = catName;
        this.catDescription = catDescription;
        this.catImage = catImage;
    }

    public String getCatName() {
        return catName;
    }

    public String getCatDescription() {
        return catDescription;
    }

    public int getCatImage() {
        return catImage;
    }
}


