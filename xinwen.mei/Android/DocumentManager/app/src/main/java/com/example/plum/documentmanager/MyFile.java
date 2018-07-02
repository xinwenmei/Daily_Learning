package com.example.plum.documentmanager;

/**
 * Created by plum on 2018/7/2.
 */

public class MyFile {
    private int imageId;
    private String fileName;
    private String fileSize;
    private String fileDate;

    public MyFile(String fileName, int imageId, String fileSize, String fileDate) {
        super();
        this.fileName = fileName;
        this.imageId = imageId;
        this.fileSize = fileSize;
        this.fileDate = fileDate;
    }

    public String getFileName() {
        return fileName;
    }

    public int getImageId() {
        return imageId;
    }

    public String getFileSize() {
        return fileSize;
    }

    public String getFileDate() {
        return fileDate;
    }
}
