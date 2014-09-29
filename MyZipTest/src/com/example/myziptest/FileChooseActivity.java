package com.example.myziptest;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.example.myziptest.R.id;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class FileChooseActivity extends Activity {

	TextView tvFilePath = null;
	ListView lvFileList = null;

	ArrayList<File> files = null;
	FileListAdapter adapter = null;
	
	boolean isRoot = false;
	File currentDir = null;

	// you can choose a directory
	public static final int FILTER_DIR = 0x01;
	// you can choose a file
	public static final int FILTER_FILE = 0x10;
	// you can choose more than one item
	public static final int FILTER_MULTI = 0x100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_choose);

		Intent intent = getIntent();

		tvFilePath = (TextView) findViewById(id.fcFilePath);
		lvFileList = (ListView) findViewById(id.fcFileList);

		files = new ArrayList<File>();

		lvFileList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				File file = (File) adapter.getItem(position);
				if (file.isDirectory()) {
					showSubFiles();
				}
			}
		});
		showSubFiles("/sdcard/");
	}

	// show subfile list to the UI
	private void showSubFiles(String dirPath) {
		currentDir = new File(dirPath);
		showSubFiles();
	}

	// show subfile list to the UI
	private void showSubFiles() {
		if(currentDir == null){
			return;
		}
		isRoot = (currentDir.getParent() == null);
		tvFilePath.setText(currentDir.getAbsolutePath()); // show current path
		files.clear(); // remove all the elements
		readSubFiles();
		sortSubFiles();
		if (!isRoot) { // father directory
			files.add(0, currentDir.getParentFile());
		}
		adapter = new FileListAdapter(this, files, isRoot);
		lvFileList.setAdapter(adapter);
	}

	// read the sub files to arraylist
	private void readSubFiles() {
		File[] fileArray = currentDir.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				// TODO Auto-generated method stub
				return true;
			}
		});
		if (null != fileArray && fileArray.length > 0) {
			for (File file : fileArray) {
				files.add(file);
			}
		}
	}

	// sort the arraylist
	private void sortSubFiles() {
		Collections.sort(files, new Comparator<File>() {
			@Override
			public int compare(File lhs, File rhs) {
				// TODO Auto-generated method stub
				if (lhs.isDirectory() && !rhs.isDirectory()) {	//directorys show first
					return -1;
				} else if (!lhs.isDirectory() && rhs.isDirectory()) {
					return 1;
				} else {
					return lhs.getName().compareTo(rhs.getName());
				}
			}
		});
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK){
			if(isRoot){
				this.finish();
			}else{
				
			}
		}
		return super.onKeyDown(keyCode, event);
	}

}
