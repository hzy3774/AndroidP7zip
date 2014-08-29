package com.hu.p7zip;

public class ZipUtils {
	
	public static int myCmd(String cmd)
	{
		freopenStdout("/mnt/sdcard/log/stdout.txt", "w");
		freopenStderr("/mnt/sdcard/log/stderr.txt", "w");
		return ZipUtils.executeCommand(cmd);
	}
	
	
	/**
	 * native interface
	 */

	private static native int executeCommand(String command);
	private static native int freopenStdout(String filePath, String type);
	private static native int freopenStderr(String filePath, String type);
	static {
		System.loadLibrary("p7zip");
	}
}
