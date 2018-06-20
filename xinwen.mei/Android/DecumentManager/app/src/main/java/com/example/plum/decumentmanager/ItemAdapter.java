package com.example.plum.decumentmanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by plum on 2018/6/13.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{

private Context mContext;

private List<Item> mItemList;

static class ViewHolder extends RecyclerView.ViewHolder {
    CardView cardView;
    ImageView itemImage;
    TextView itemName;
    TextView itemNum;

    public ViewHolder(View view) {
        super(view);
        cardView = (CardView) view;
        itemImage = (ImageView) view.findViewById(R.id.item_image);
        itemName = (TextView) view.findViewById(R.id.item_name);
    }
}

    public ItemAdapter(List<Item> itemList) {
        mItemList = itemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = mItemList.get(position);
        holder.itemName.setText(item.getName());
        Glide.with(mContext).load(item.getImageId()).into(holder.itemImage);
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

}

