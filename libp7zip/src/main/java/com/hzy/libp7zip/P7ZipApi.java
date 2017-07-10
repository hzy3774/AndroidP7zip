package com.hzy.libp7zip;

/**
 * Created by huzongyao on 17-7-5.
 */

public class P7ZipApi {

    public static native String get7zVersionInfo();
    public static native int executeCommand(String command);

    static {
        System.loadLibrary("p7zip");
    }
}
