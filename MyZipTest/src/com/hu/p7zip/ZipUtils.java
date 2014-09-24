package com.hu.p7zip;

public class ZipUtils {
	
	/**
	 * redirect stdout to a stream
	 * @param filePath
	 * @return
	 */
	public static int freopenStdout(String filePath){
		return freopenStdout(filePath, "w+");
	}
	
	/**
	 * redirect stderr to a stream
	 * @param filePath
	 * @return
	 */
	public static int freopenStderr(String filePath){
		return freopenStderr(filePath, "w+");
	}

	/**
	 * native interface
	 */
	/**
	 * Execute a command
	 * @param command command string
	 * @return return code
	 */
	public static native int executeCommand(String command);
	
	/**
	 * redirect stdout to a stream, default android stdout do nothing
	 * freopenStdout("/mnt/sdcard/log/stdout.txt", "w");
	 * @param filePath
	 * @param mode often w+
	 * @return
	 */
	public static native int freopenStdout(String filePath, String mode);
	
	/**
	 * redirect stderr to a stream, default android stderr do nothing
	 * freopenStderr("/mnt/sdcard/log/stderr.txt", "w");
	 * @param filePath
	 * @param mode
	 * @return
	 */
	public static native int freopenStderr(String filePath, String mode);
	
	//load library
	static {
		System.loadLibrary("p7zip");
	}
}
