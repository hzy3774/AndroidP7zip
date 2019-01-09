package com.hzy.libp7zip;

/**
 * Created by huzongyao on 8/1/17.
 */

public class ExitCode {
    /**
     * 0	No error
     * 1	Warning (Non fatal error(s)). For example, one or more files
     *      were locked by some other application, so they were not compressed.
     * 2	Fatal error
     * 7	Command line error
     * 8	Not enough memory for operation
     * 255	User stopped the process
     */
    public static final int EXIT_OK = 0;
    public static final int EXIT_WARNING = 1;
    public static final int EXIT_FATAL = 2;
    public static final int EXIT_CMD_ERROR = 7;
    public static final int EXIT_MEMORY_ERROR = 8;
    public static final int EXIT_NOT_SUPPORT = 255;
}
