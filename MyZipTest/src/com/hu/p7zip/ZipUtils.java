package com.hu.p7zip;

public class ZipUtils {
	
	private static final String EXE_NAME = "7z";
	
	/*  commands:
 		a Add 
		b Benchmark 
		d Delete 
		e Extract 
		l List 
		t Test 
		u Update 
		x eXtract with full paths 
	 */
	private static final char CMD_ADD = 'a';
	private static final char CMD_DELETE = 'd';
	private static final char CMD_EXTRACT = 'e';
	private static final char CMD_UPDATE = 'u';
	private static final char CMD_EXTRACT_FULL = 'x';
	
	/*
		Switch Description
		-- Stop switches parsing 
		-ai Include archive filenames 
		-an Disable parsing of archive_name 
		-ao Overwrite mode 
		-ax Exclude archive filenames 
		-i Include filenames 
		-m Set Compression Method 
		-o Set Output directory 
		-p Set Password 
		-r Recurse subdirectories 
		-scs Set charset for list files 
		-slp Set Large Pages mode 
		-slt Show technical information 
		-sfx Create SFX archive 
		-si Read data from StdIn 
		-so Write data to StdOut 
		-ssc Set Sensitive Case mode 
		-t Type of archive 
		-u Update options 
		-v Create Volumes 
		-w Set Working directory 
		-x Exclude filenames 
		-y Assume Yes on all queries 
	*/
	private static final String SWH_OUT_DIRECTORY = "-o";
	private static final String SWH_SET_PASSWORD = "-p";
	private static final String SWH_SET_COMPRESS_METHOD = "-m";
	private static final String SWH_RECURES_SUBDIRECTORIES = "-r";
	private static final String SWH_SET_CASE_SENSITIVE_MODE = "-ssc";
	private static final String SWH_TYPE_OF_ARCHIVE = "-t";
	private static final String SWH_UPDATE_OPTIONS = "-u";
	private static final String SWH_CREATE_VOLUMES = "-v";
	private static final String SWH_SET_WORKING_DIR = "-w";
	private static final String SWH_YES_TO_ALL = "-y";
	private static final String SWH_INCLUDE_FILE_NAMES = "-i";
	private static final String SWH_EXCLUDE_FILE_NAMES = "-x";
	
	/*
		0 No error 
		1 Warning (Non fatal error(s)). For example, one or more files were locked by some other application,
		  so they were not compressed. 
		2 Fatal error 
		7 Command line error 
		8 Not enough memory for operation 
		255 User stopped the process 
	*/
	private static final int RET_SUCCESS = 0;
	private static final int RET_WARNING = 1;
	private static final int RET_FAULT = 2;
	private static final int RET_COMMAND = 7;
	private static final int RET_MEMORY = 8;
	private static final int RET_USER_STOP = 255;
	
	public static int command(String cmd)
	{
		return ZipUtils.executeCommand(cmd);
	}
	
	public static int extractAll(String filePath, String outPath){
		StringBuilder builder = new StringBuilder(EXE_NAME);
		builder.append(" " + CMD_EXTRACT + " " + filePath + " ");
		builder.append(SWH_OUT_DIRECTORY + outPath);
		return ZipUtils.executeCommand(builder.toString());
	}
	
	
	/**
	 * native interface
	 */

	private static native int executeCommand(String command);
	
	//freopenStdout("/mnt/sdcard/log/stdout.txt", "w");
	private static native int freopenStdout(String filePath, String type);
	
	//freopenStderr("/mnt/sdcard/log/stderr.txt", "w");
	private static native int freopenStderr(String filePath, String type);
	
	//load library
	static {
		System.loadLibrary("p7zip");
	}
}
