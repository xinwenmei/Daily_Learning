package com.example.plum.documentmanager;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class LocalActivity extends AppCompatActivity  {

    private List<MyFile> myFileList = new ArrayList<>();
    private MyFileAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        adapter = new MyFileAdapter(myFileList);
        initMyFile(Environment.getExternalStorageDirectory());
        RecyclerView recyclerView = findViewById(R.id.rv);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    private void initMyFile(File sourceFile) {
        myFileList.clear();
        List<File> files = new ArrayList<>();
        Collections.addAll(files, sourceFile.listFiles());
        for (File file : files) {
            String fileName = file.getName();
            //默认是未知文件
            int imageId = R.drawable.unknow;

            //下面开始判断
            if (file.isDirectory()) {
                imageId = R.drawable.folder;
            } else {
                //如果是文件，就从文件名的后缀名来判断是什么文件，从而添加对应图标
                //获取后缀名前的分隔符"."在fName中的位置。
                int dotIndex = fileName.lastIndexOf(".");
                if(dotIndex >= 0){
                    /* 获取文件的后缀名*/
                    String end= fileName.substring(dotIndex,fileName.length()).toLowerCase();
                    if(!Objects.equals(end, "")){
                        if (Objects.equals(end, ".mp3")||Objects.equals(end, ".ape")
                                || Objects.equals(end, ".flac")||Objects.equals(end, ".m4a")
                                ||Objects.equals(end, ".ape")||Objects.equals(end, ".wav")
                                ||Objects.equals(end, ".aac")){
                            //如果是音乐文件
                            imageId = R.drawable.music;
                        }else if (Objects.equals(end, ".mp4")||Objects.equals(end, ".mkv")
                                ||Objects.equals(end, ".avi")||Objects.equals(end, ".rmvb")
                                ||Objects.equals(end, ".rm")||Objects.equals(end, ".mov")
                                ||Objects.equals(end, ".mpeg")){
                            //如果是影视文件
                            imageId = R.drawable.play;
                        }
                    }
                }
            }

            String fileSize = "";
            long size = 0;
            //下面开始判断文件大小
            if (file.isDirectory()) {
                //如果是文件夹就要求出占用大小 = 总大小 - 可用大小
                try {
                    size = getTotalSizeOfFilesInDir(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                size = file.length();
            }
            //判断大小是该用什么单位,K\M\G
            if (size / 1024 / 1024 / 1024 > 0) {
                fileSize = size / 1024 / 1024 / 1024 + " G";
            } else if (size / 1024 / 1024 > 0) {
                fileSize = size / 1024 / 1024 + " M";
            } else {
                fileSize = size / 1024 + " K";
            }

            String fileDate = "";
            fileDate = getModifiedTime_2(file);

            MyFile myFile = new MyFile(fileName, imageId, fileSize, fileDate);
            myFileList.add(myFile);
        }
        adapter.notifyDataSetChanged();
    }


//获取文件夹的大小
    private class SubDirectoriesAndSize {

        final public long size;
        final public List<File> subDirectories;

        public SubDirectoriesAndSize(final long totalSize,
                                     final List<File> theSubDirs) {
            size = totalSize;
            subDirectories = Collections.unmodifiableList(theSubDirs);
        }
    }

    private SubDirectoriesAndSize getTotalAndSubDirs(final File file) {
        long total = 0;
        final List<File> subDirectories = new ArrayList<File>();
        if (file.isDirectory()) {
            final File[] children = file.listFiles();
            if (children != null)
                for (final File child : children) {
                    if (child.isFile())
                        total += child.length();
                    else
                        subDirectories.add(child);
                }
        }
        return new SubDirectoriesAndSize(total, subDirectories);
    }

    private long getTotalSizeOfFilesInDir(final File file)
            throws InterruptedException, ExecutionException, TimeoutException {
        final ExecutorService service = Executors.newFixedThreadPool(100);
        try {
            long total = 0;
            final List<File> directories = new ArrayList<File>();
            directories.add(file);
            while (!directories.isEmpty()) {
                final List<Future<SubDirectoriesAndSize>> partialResults = new ArrayList<Future<SubDirectoriesAndSize>>();
                for (final File directory : directories) {
                    partialResults.add(service
                            .submit(new Callable<SubDirectoriesAndSize>() {
                                public SubDirectoriesAndSize call() {
                                    return getTotalAndSubDirs(directory);
                                }
                            }));
                }
                directories.clear();
                for (final Future<SubDirectoriesAndSize> partialResultFuture : partialResults) {
                    final SubDirectoriesAndSize subDirectoriesAndSize = partialResultFuture
                            .get(100, TimeUnit.SECONDS);
                    directories.addAll(subDirectoriesAndSize.subDirectories);
                    total += subDirectoriesAndSize.size;
                }
            }
            return total;
        } finally {
            service.shutdown();
        }
    }

    //获取文件的修改时间
    public static String getModifiedTime_2(File f){
        Calendar cal = Calendar.getInstance();
        long time = f.lastModified();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        cal.setTimeInMillis(time);
        return formatter.format(cal.getTime());
    }


}

