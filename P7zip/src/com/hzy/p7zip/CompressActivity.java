package com.hzy.p7zip;

import java.io.File;

import com.hzy.p7zip.R.array;
import com.hzy.p7zip.R.id;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;

public class CompressActivity  extends Activity{
	
	EditText etSrc, etDst, etFileName, etPassword;
	Button btSrc, btDst, btExecute;
	CheckBox cbPassword, cbShowPwd, cbType;
	Spinner spType;
	OnClickListener btClickListener;
	OnCheckedChangeListener onCheckboxListener;
	
	boolean isPassword = false;
	boolean isSpecifyType = false;
	
	String[] fileTypes; 
	
	static final int REQUEST_CODE_SRC = 0;
	static final int REQUEST_CODE_DST = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compress);
		//edittext
		etSrc = (EditText) findViewById(id.editTextCoSrc);
		etDst = (EditText) findViewById(id.editTextCoDstPath);
		etFileName = (EditText) findViewById(id.editTextCoAchieveName);
		etPassword = (EditText) findViewById(id.editTextCoPasswd);
		//button
		btSrc = (Button) findViewById(id.buttonCoSrc);
		btDst = (Button) findViewById(id.buttonCoDstPath);
		btExecute = (Button) findViewById(id.buttonCoExecute);
		//checkbox
		cbPassword = (CheckBox) findViewById(id.checkBoxCoPasswd);
		cbShowPwd = (CheckBox) findViewById(id.checkBoxCoPwdVisible);
		cbType = (CheckBox) findViewById(id.checkBoxCoType);
		//spinner
		spType = (Spinner) findViewById(id.spinnerCoType);
		fileTypes = getResources().getStringArray(array.array_compress_type);
		//button listener
		btClickListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (v.getId()) {
				case id.buttonCoSrc:
					startFileChooser(FileChooseActivity.FILTER_DIR | 
							FileChooseActivity.FILTER_FILE | FileChooseActivity.FILTER_MULTI,
							REQUEST_CODE_SRC);
					break;
				case id.buttonCoDstPath:
					startFileChooser(FileChooseActivity.FILTER_DIR, REQUEST_CODE_DST);
					break;
				case id.buttonCoExecute:
					compressProcess();
					break;
				default:
					break;
				}
			}
		};
		//checkbox listener
		onCheckboxListener = new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				switch (buttonView.getId()) {
				case id.checkBoxCoPasswd:
					etPassword.setVisibility(isChecked ? View.VISIBLE : View.GONE);
					etPassword.requestFocus();
					cbShowPwd.setVisibility(isChecked ? View.VISIBLE : View.GONE);
					isPassword = isChecked;
					break;
				case id.checkBoxCoPwdVisible:
					int pwdType = isChecked ? InputType.TYPE_CLASS_TEXT
							: InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;
					etPassword.setInputType(pwdType);
					etPassword.selectAll();
					break;
				case id.checkBoxCoType:
					isSpecifyType = isChecked;
					spType.setVisibility(isChecked ? View.VISIBLE : View.INVISIBLE);
					break;
				default:
					break;
				}
			}
		};
		
		//set listeners
		btSrc.setOnClickListener(btClickListener);
		btDst.setOnClickListener(btClickListener);
		btExecute.setOnClickListener(btClickListener);
		
		cbPassword.setOnCheckedChangeListener(onCheckboxListener);
		cbShowPwd.setOnCheckedChangeListener(onCheckboxListener);
		cbType.setOnCheckedChangeListener(onCheckboxListener);
	}
	
	//open a file choose activity to choose file
	private void startFileChooser(int filter, int requestCode){
		Intent intent = new Intent(CompressActivity.this, FileChooseActivity.class);
		intent.putExtra(FileChooseActivity.STRING_FILTER, filter);
		startActivityForResult(intent, requestCode);
	}
	
	private void compressProcess(){
		StringBuilder sbCmd = new StringBuilder("7z a ");
		if(isSpecifyType){
			sbCmd.append("-t" + fileTypes[spType.getSelectedItemPosition()] + " ");	//7z a -t7z
		}
		sbCmd.append("'" + etDst.getText() + File.separator + etFileName.getText() + "' "); //7z a '/hh.7z'
		sbCmd.append("'" + etSrc.getText() + "' "); //7z a '/hh.7z' '/paths'
		if(isPassword){
			sbCmd.append("'-p" + etPassword.getText().toString() + "' ");
		}
		new ZipProcess(CompressActivity.this, sbCmd.toString()).start();
	}
	
	//get file chooser result
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(data == null){
			return;
		}
		String retStr = data.getStringExtra(FileChooseActivity.STRING_RETURN);
		switch (requestCode) {
		case REQUEST_CODE_SRC:
			etSrc.requestFocus();
			etSrc.setText(retStr);
			etSrc.selectAll();
			break;
		case REQUEST_CODE_DST:
			etDst.requestFocus();
			etDst.setText(retStr);
			etDst.selectAll();
			break;
		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
