package com.hu.p7zip;

public class CommandHelper {
	/*
		7z <command> [<switch>...] <base_archive_name> [<arguments>...]
	
		<arguments> ::= <switch> | <wildcard> | <filename> | <list_file>
		<switch>::= <switch_symbol><switch_characters>[<option>]
		<switch_symbol> ::= '/' | '-' 
		<list_file> ::= @{filename}
		
		Expressions in square brackets (between '[' and ']') are optional.
		Expressions in curly braces ('{' and '}') mean that instead of that Expression (including braces), 
			the user must substitute some string.
		
		Expression
		expression1 | expression2 | ... | expressionN
		
		means that any (but only one) from these expressions must be specified. 
		Commands and switches can be entered in upper or lower case.
		Command is the first non-switch argument.
	 */
	
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
	
	
	public static int extractAll(String filePath, String outPath){
		StringBuilder builder = new StringBuilder(EXE_NAME);
		builder.append(" " + CMD_EXTRACT + " " + filePath + " ");
		builder.append(SWH_OUT_DIRECTORY + outPath);
		return ZipUtils.executeCommand(builder.toString());
	}
}
