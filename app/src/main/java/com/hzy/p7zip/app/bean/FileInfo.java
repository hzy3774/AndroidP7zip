package com.hzy.p7zip.app.bean;

/**
 * Created by huzongyao on 17-7-13.
 */

public class FileInfo {
    private String fileName;
    private String filePath;
    private boolean isFolder;
    private FileType fileType;
    private int subCount;
    private long fileLength;

    public FileInfo(String fileName, String filePath, boolean isFolder, FileType fileType) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.isFolder = isFolder;
        this.fileType = fileType;
    }

    public FileInfo() {
    }

    public long getFileLength() {
        return fileLength;
    }

    public void setFileLength(long fileLength) {
        this.fileLength = fileLength;
    }

    public int getSubCount() {
        return subCount;
    }

    public void setSubCount(int subCount) {
        this.subCount = subCount;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public boolean isFolder() {
        return isFolder;
    }

    public void setFolder(boolean folder) {
        isFolder = folder;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }
}
