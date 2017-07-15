package com.hzy.p7zip.app.utils;

import com.hzy.p7zip.app.bean.FileInfo;
import com.hzy.p7zip.app.bean.FileType;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by huzongyao on 17-7-13.
 */

public class FileUtils {
    public static FileInfo getFileInfoFromPath(String filePath) {
        FileInfo info = new FileInfo();
        File file = new File(filePath);
        info.setFileName(file.getName());
        info.setFilePath(file.getAbsolutePath());
        info.setFileType(FileType.fileunknown);
        if (file.isDirectory() && file.canRead()) {
            info.setFolder(true);
            String[] fileList = file.list();
            if (fileList != null) {
                if (fileList.length > 0) {
                    info.setFileType(FileType.folderFull);
                } else {
                    info.setFileType(FileType.folderEmpty);
                }
            }
        } else {
            info.setFileType(FileType.fileunknown);
        }
        return info;
    }

    public static List<FileInfo> getInfoListFromPath(String path) {
        List<FileInfo> fileInfos = new ArrayList<>();
        File folder = new File(path);
        if (folder.exists() && folder.isDirectory() && folder.canRead()) {
            File[] fileNames = folder.listFiles();
            if (fileNames != null) {
                Arrays.sort(fileNames, new FileComparator());
                for (File file : fileNames) {
                    fileInfos.add(getFileInfoFromPath(file.getPath()));
                }
            }
        }
        return fileInfos;
    }

    private static class FileComparator implements Comparator<File> {
        @Override
        public int compare(File o1, File o2) {
            int ret = getFileScore(o2) - getFileScore(o1);
            if (ret == 0) {
                ret = o1.getName().compareToIgnoreCase(o2.getName());
            }
            return ret;
        }
    }

    private static int getFileScore(File file) {
        int score = 0;
        score |= file.isDirectory() ? 0x10 : 0;
        score |= file.isHidden() ? 0 : 0x01;
        return score;
    }
}
