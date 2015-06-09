package com.hzy.p7zip;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.hzy.p7zip.R.id;

public class FileChooseActivity extends Activity {

	TextView tvFilePath = null;
	ListView lvFileList = null;
	LinearLayout llBottom = null;
	Button btDone = null;
	Button btCancel = null;

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
		llBottom = (LinearLayout) findViewById(id.linearLayoutFcBottom);
		btDone = (Button) findViewById(id.buttonFcDone);
		btCancel = (Button) findViewById(id.buttonFcCancel);

		files = new ArrayList<File>();
		
		Intent intent = getIntent();
		//get the filter
		int filter = intent.getIntExtra(STRING_FILTER, FILTER_FILE);
		chooseDir = (filter & FILTER_DIR) != 0;
		chooseFile = (filter & FILTER_FILE) != 0;
		chooseMulti = (filter & FILTER_MULTI) != 0;
		//wether need to show bottom controls
		if(chooseDir || chooseMulti){
			llBottom.setVisibility(View.VISIBLE);
		}

		//set listeners
		OnFileItemClickListener itemListener = new OnFileItemClickListener();
		OnFcButtonClickListener buttonListener = new OnFcButtonClickListener();
		lvFileList.setOnItemClickListener(itemListener);
		btDone.setOnClickListener(buttonListener);
		btCancel.setOnClickListener(buttonListener);
		
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
	
	//when button clicked
	private class OnFcButtonClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case id.buttonFcDone:
				Intent intent = new Intent();
				String retStr = null;
				if(chooseMulti){
					//return selected paths/files
					StringBuilder sbStr = new StringBuilder("");
					List<Boolean> flags = adapter.getCheckedIndexList();
					for(int i = 0; i < flags.size(); i++){
						if(flags.get(i)){
							sbStr.append(files.get(i) + "' '");
						}
					}
					if(sbStr.length() > 3){
						sbStr.setLength(sbStr.length() - 3);
					}
					retStr = sbStr.toString();
				}else{
					//return current path
					retStr = currentDir.getAbsolutePath();
				}
				intent.putExtra(STRING_RETURN, retStr);
				setResult(RESULT_OK, intent);
				finish();
				break;
			case id.buttonFcCancel:
				finish();
				break;
			default:
				break;
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
		adapter = new FileListAdapter(this, files, isRoot, chooseMulti);
		lvFileList.setAdapter(adapter);
	}

	// read the sub files to arraylist
	private void readSubFiles() {
		File[] fileArray = currentDir.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				// TODO Auto-generated method stub
				if(!pathname.isDirectory() && !chooseFile){
					return false;
				}else{
					return true;
				}
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
