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
import android.os.Environment;
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
	boolean chooseDir = false;
	boolean chooseFile = false;
	boolean chooseMulti = false;

	public static final String STRING_FILTER = "filter_name";
	public static final String STRING_RETURN = "return_string";
	
	// you can choose a directory
	public static final int FILTER_DIR = 0x1;
	// you can choose a directory
	public static final int FILTER_FILE = 0x10;
	//multi choose
	public static final int FILTER_MULTI = 0x100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_choose);

		tvFilePath = (TextView) findViewById(id.fcFilePath);
		lvFileList = (ListView) findViewById(id.fcFileList);

		files = new ArrayList<File>();
		
		Intent intent = getIntent();
		//get the filter
		int filter = intent.getIntExtra(STRING_FILTER, FILTER_FILE);
		chooseDir = (filter & FILTER_DIR) != 0;
		chooseFile = (filter & FILTER_FILE) != 0;
		chooseMulti = (filter & FILTER_MULTI) != 0;

		OnFileItemClickListener itemListener = new OnFileItemClickListener();
		lvFileList.setOnItemClickListener(itemListener);
		//set start path
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			currentDir = Environment.getExternalStorageDirectory();
		}else{
			currentDir = new File("/");
		}
		showSubFiles();
	}
	
	//when file list item clicked
	private class OnFileItemClickListener implements OnItemClickListener{
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			File file = (File) adapter.getItem(position);
			if (file.isDirectory()) {
				currentDir = file;
				showSubFiles();
			}else{
				Intent intent = new Intent();
				intent.putExtra(STRING_RETURN, file.getAbsolutePath());
				setResult(RESULT_OK, intent);
				finish();
			}
		}
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
				//directorys show first
				if (lhs.isDirectory() && !rhs.isDirectory()) {
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
			currentDir = currentDir.getParentFile();
			if(currentDir == null){
				this.finish();
			}else{
				showSubFiles();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
