package com.hzy.libp7zip;

/**
 * Created by huzongyao on 17-7-5.
 */

public class P7ZipApi {

    /**
     * Get P7zip version info
     */
    public static native String get7zVersionInfo();

    /**
     * Execute some p7zip command
     *
     * @param command command string
     * @return  exit code
     * @see com.hzy.libp7zip.ExitCode
     */
    public static native int executeCommand(String command);

    static {
        System.loadLibrary("p7zip");
    }
}
