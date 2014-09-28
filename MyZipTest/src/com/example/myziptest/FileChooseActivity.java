package com.example.myziptest;

import java.io.File;
import java.util.ArrayList;

import com.example.myziptest.R.id;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class FileChooseActivity extends Activity{
	
	TextView tvFilePath = null;
	ListView lvFileList = null;
	
	ArrayList<File> files = null;
	FileListAdapter adapter = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_choose);
		
		tvFilePath = (TextView) findViewById(id.fcFilePath);
		lvFileList = (ListView) findViewById(id.fcFileList);
		
		lvFileList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				File file = (File) adapter.getItem(position);
				if(file.isDirectory()){
					showSubFiles(file);
				}
			}
		});
		
		showSubFiles("/sdcard/");
	}
	
	private void showSubFiles(String dirPath){
		showSubFiles(new File(dirPath));
	}
	
	private void showSubFiles(File directory) {
		boolean isRoot = (directory.getParent() == null);
		tvFilePath.setText(directory.getAbsolutePath());
		files = new ArrayList<File>();
		if (!isRoot) {
			files.add(directory.getParentFile());
		}
		File[] fileArray = directory.listFiles();
		// mItemCount.setText(filterFiles.length + "items");
		if (null != fileArray && fileArray.length > 0) {
			for (File file : fileArray) {
				files.add(file);
			}
		}
		adapter = new FileListAdapter(this, files, isRoot);
		lvFileList.setAdapter(adapter);

	}
}

