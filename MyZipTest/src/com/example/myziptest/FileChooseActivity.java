package com.example.myziptest;

import java.io.File;

import com.example.myziptest.R.id;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class FileChooseActivity extends Activity{
	
	TextView tvFilePath = null;
	ListView lvFileList = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_choose);
		
		tvFilePath = (TextView) findViewById(id.fcFilePath);
		lvFileList = (ListView) findViewById(id.fcFileList);
	}
	
	private void showFiles(String dirPath){
		showFiles(new File(dirPath));
	}
	
	private void showFiles(File directory){
		
	}
}

