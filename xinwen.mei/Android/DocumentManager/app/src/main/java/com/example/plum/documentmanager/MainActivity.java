package com.example.plum.documentmanager;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

//    private Item[] items = {new Item("Video",R.drawable.video), new Item("decument", R.drawable.file),
//            new Item("search", R.drawable.search),new Item("photo", R.drawable.photo),
//            new Item("music", R.drawable.music),new Item("apk", R.drawable.apk),
//            new Item("zip", R.drawable.zip),new Item("favorite", R.drawable.favorite),
//            new Item("download", R.drawable.download)};

    private List<Item> itemList = new ArrayList<>();

    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.menu);
        }
        navView.setCheckedItem(R.id.location);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                mDrawerLayout.closeDrawers();
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,LocalActivity.class);
                startActivity(intent);
                return true;
            }
        });
        initItems();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ItemAdapter(itemList);
        recyclerView.setAdapter(adapter);
    }

    private void initItems() {
       Item video = new Item("视频",R.mipmap.video);
       itemList.add(video);
        Item music = new Item("音乐",R.mipmap.music);
        itemList.add(music);
        Item search = new Item("最近",R.mipmap.search);
        itemList.add(search);
        Item photo = new Item("图片",R.mipmap.photo);
        itemList.add(photo);
        Item decument = new Item("文档",R.mipmap.decument);
        itemList.add(decument);
        Item apk = new Item("安装包",R.mipmap.apk);
        itemList.add(apk);
        Item zip = new Item("压缩包",R.mipmap.zip);
        itemList.add(zip);
        Item favorite = new Item("收藏",R.mipmap.favorite);
        itemList.add(favorite);
        Item download = new Item("下载",R.mipmap.download);
        itemList.add(download);
        }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }
}
