package com.example.plum.wechat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Item> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initItems();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //创建分割线对象，第一个参数为上下文，第二个参数为RecyclerView排列方向
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        //为RecyclerView添加分割线
        recyclerView.addItemDecoration(decoration);
        ItemAdapter adapter = new ItemAdapter(itemList);
        recyclerView.setAdapter(adapter);

    }

    private void initItems(){
        for(int i = 0;i<2;i++) {
            Item item1 = new Item(R.drawable.pic1, "订阅号", "16:23", "网络强国的中国实践——写在习近平总书记“4·19”重要讲话发表两周年之际");
            itemList.add(item1);

            Item item2 = new Item(R.drawable.pic2, "微信公众号", "15:51", "新华社北京4月18日电 题：网络强国的中国实践——写在习近平总书记“4·19”重要讲话发表两周年之际");
            itemList.add(item2);

            Item item3 = new Item(R.drawable.pic3, "File Transfer", "Thursday", "http://iconfont.cn/search/index?q=file&page=5");
            itemList.add(item3);

            Item item4 = new Item(R.drawable.pic4, "移动云-深圳", "15:23", "okay");
            itemList.add(item4);

            Item item5 = new Item(R.drawable.pic5, "洛宁一高群", "14:43", "fine");
            itemList.add(item5);

            Item item6 = new Item(R.drawable.pic6, "利强赶紧到30级", "12:21", "yeah");
            itemList.add(item6);

            Item item7 = new Item(R.drawable.pic7, "IT公司里的华农人", "12:20", "ahahaha");
            itemList.add(item7);
        }
    }
}
