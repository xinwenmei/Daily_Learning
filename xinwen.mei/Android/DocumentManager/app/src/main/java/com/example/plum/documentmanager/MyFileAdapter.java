package com.example.plum.documentmanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by plum on 2018/7/2.
 */


public class MyFileAdapter extends RecyclerView.Adapter<MyFileAdapter.ViewHolder> {
    private List<MyFile> myFileList;
    private Context mContext;

    public MyFileAdapter(List<MyFile> list) {
        super();
        myFileList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.directory, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MyFile myFile = myFileList.get(position);
        holder.myFileName.setText(myFile.getFileName());
        holder.myFileImage.setImageResource(myFile.getImageId());
        holder.myFileSize.setText(myFile.getFileSize());
        holder.myFileDate.setText(myFile.getFileDate());
    }

    @Override
    public int getItemCount() {
        return myFileList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView myFileName;
        ImageView myFileImage;
        TextView myFileSize;
        TextView myFileDate;

        public ViewHolder(View itemView) {
            super(itemView);
            myFileName = itemView.findViewById(R.id.myFileName);
            myFileImage = itemView.findViewById(R.id.myFileImage);
            myFileSize = itemView.findViewById(R.id.fileSize);
            myFileDate = itemView.findViewById(R.id.fileDate);
        }
    }
}