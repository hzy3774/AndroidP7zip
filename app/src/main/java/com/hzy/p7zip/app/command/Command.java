package com.hzy.p7zip.app.command;

/**
 * Created by huzongyao on 8/1/17.
 */

public class Command {

    public static String getExtractCmd(String archivePath, String outPath) {
        return String.format("7z x '%s' '-o%s' -aoa", archivePath, outPath);
    }

    public static String getCompressCmd(String filePath, String outPath, String type) {
        return String.format("7z a -t%s '%s' '%s'", type, outPath, filePath);
    }
}
