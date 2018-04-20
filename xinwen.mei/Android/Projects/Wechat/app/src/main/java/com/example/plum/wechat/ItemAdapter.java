package com.example.plum.wechat;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by plum on 2018/4/19.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<Item> mItemList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemName;
        TextView itemContent;
        TextView itemTime;

        public ViewHolder(View view) {
            super(view);
            itemImage = (ImageView) view.findViewById(R.id.item_image);
            itemName = (TextView) view.findViewById(R.id.item_name);
            itemContent = (TextView) view.findViewById(R.id.item_content);
            itemTime = (TextView) view.findViewById(R.id.item_time);
        }
    }

    public ItemAdapter(List<Item> itemList) {
        mItemList = itemList;
    }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position){
            Item item = mItemList.get(position);
            holder.itemImage.setImageResource(item.getImageId());
            holder.itemName.setText(item.getName());
            holder.itemContent.setText(item.getContent());
            holder.itemTime.setText(item.getTime());
        }

        @Override
        public int getItemCount(){
            return mItemList.size();
        }
}
