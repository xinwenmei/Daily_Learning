package com.example.plum.wechat;

import android.app.PendingIntent;
import android.widget.ImageView;

/**
 * Created by plum on 2018/4/19.
 */

public class Item {
    private String name;
    private int imageId;
    private String content;
    private String time;

    public Item(int imageId, String name, String time, String content){
        this.name = name;
        this.imageId = imageId;
        this.content = content;
        this.time = time;
    }

    public String getName(){
        return name;
    }

    public int getImageId(){
        return imageId;
    }

    public String getContent(){
        return content;
    }

    public String getTime(){
        return time;
    }
}
