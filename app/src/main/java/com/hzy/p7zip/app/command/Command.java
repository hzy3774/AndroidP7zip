package com.hzy.p7zip.app.command;

import com.hzy.libp7zip.P7ZipApi;

/**
 * Created by huzongyao on 8/1/17.
 */

public class Command {

    public static final String P7Z = "7z";

    /**
     * Command	Description
     * a	Add
     * b	Benchmark
     * d	Delete
     * e	Extract
     * h	Hash
     * i	Show information about supported formats
     * l	List
     * rn	Rename
     * t	Test
     * u	Update
     * x	eXtract with full paths
     */
    public static final String CMD_ADD = "a";
    public static final String CMD_BENCHMARK = "b";
    public static final String CMD_DELETE = "d";
    public static final String CMD_EXTRACT = "e";
    public static final String CMD_HASH = "h";
    public static final String CMD_INFO = "i";
    public static final String CMD_LIST = "l";
    public static final String CMD_RENAME = "rn";
    public static final String CMD_TEST = "t";
    public static final String CMD_UPDATE = "u";
    public static final String CMD_EXTRACT1 = "x";

    /**
     * Switch	Description
     * -i	Include filenames
     * -m	Set Compression Method
     * -o	Set Output directory
     * -p	Set Password
     * -t	Type of archive
     * -u	Update options
     * -x	Exclude filenames
     * -y	Assume Yes on all queries
     */
    public static final String SWH_INCLUDE = "-i";
    public static final String SWH_METHOD = "-m";
    public static final String SWH_OUTPUT = "-o";
    public static final String SWH_PASSWORD = "-p";
    public static final String SWH_TYPE = "-t";
    public static final String SWH_UPDATE = "-u";
    public static final String SWH_EXCLUDE = "-x";
    public static final String SWH_YES = "-y";

    public static String getExtractCmd(String archivePath, String outPath) {
        return String.format("%s %s '%s' '%s%s' -aoa", P7Z, CMD_EXTRACT1,
                archivePath, SWH_OUTPUT, outPath);
    }

}
