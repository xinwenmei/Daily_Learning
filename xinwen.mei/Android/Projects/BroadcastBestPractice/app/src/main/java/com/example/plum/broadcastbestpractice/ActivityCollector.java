package com.example.plum.broadcastbestpractice;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by plum on 2018/4/15.
 * 专门的集合类对所有的活动进行管理
 */

public class ActivityCollector {

    public static List<Activity> activities = new ArrayList<>();   //通过一个List来暂存活动

    public static void addActivity(Activity activity) {      //向List中添加一个活动
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){    //从List中移除活动
        activities.remove(activity);
    }

    public static void finishAll(){          //将List中存储的活动全部销毁掉
        for (Activity activity:activities){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
