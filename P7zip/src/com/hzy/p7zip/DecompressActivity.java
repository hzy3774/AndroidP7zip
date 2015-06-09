package com.hzy.p7zip;

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

import com.hzy.p7zip.R.id;

public class DecompressActivity extends Activity {

	Button btSrc, btDst, btExe;
	EditText etSrc, etDst, etWildcard, etPassword;
	CheckBox cbWildcard, cbPassword, cbShowPassword, cbFilesOnly;
	Spinner spOverwriteMode;
	OnCheckedChangeListener onCheckboxListener = null;
	OnClickListener onButtonClickListener = null;
	
	boolean isWildcard = false;
	boolean isPassword = false;
	
	static final int REQUEST_CODE_SRC = 0;
	static final int REQUEST_CODE_DST = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_decompress);
		
		//button
		btSrc = (Button) findViewById(id.buttonDeSrc);
		btDst = (Button) findViewById(id.buttonDeDst);
		btExe = (Button) findViewById(id.buttonDeExecute);
		//edittext
		etSrc = (EditText) findViewById(id.editTextDeSrc);
		etDst = (EditText) findViewById(id.editTextDeDst);
		etWildcard = (EditText) findViewById(id.editTextDeWildcard);
		etPassword = (EditText) findViewById(id.editTextDePasswd);
		//checkbox
		cbWildcard = (CheckBox) findViewById(id.checkBoxDeWildcard);
		cbPassword = (CheckBox) findViewById(id.checkBoxDePasswd);
		cbShowPassword = (CheckBox) findViewById(id.checkBoxDePwdVisible);
		cbFilesOnly = (CheckBox) findViewById(id.checkBoxDeFilesOnly);
		//Spiner
		spOverwriteMode = (Spinner) findViewById(id.spinnerDeOverwrite);
		//button listener
		onButtonClickListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (v.getId()) {
				case id.buttonDeSrc:
					startFileChooser(FileChooseActivity.FILTER_FILE, REQUEST_CODE_SRC);
					break;
				case id.buttonDeDst:
					startFileChooser(FileChooseActivity.FILTER_DIR, REQUEST_CODE_DST);
					break;
				case id.buttonDeExecute:
					extractProcess();
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
				case id.checkBoxDeWildcard:
					etWildcard.setVisibility(isChecked ? View.VISIBLE : View.GONE);
					etWildcard.requestFocus();
					isWildcard = isChecked;
					break;
				case id.checkBoxDePasswd:
					etPassword.setVisibility(isChecked ? View.VISIBLE : View.GONE);
					etPassword.requestFocus();
					cbShowPassword.setVisibility(isChecked ? View.VISIBLE : View.GONE);
					isPassword = isChecked;
					break;
				case id.checkBoxDePwdVisible:
					int pwdType = isChecked ? InputType.TYPE_CLASS_TEXT
							: InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;
					etPassword.setInputType(pwdType);
					etPassword.selectAll();
					break;
				default:
					break;
				}
			}
		};
		
		//set listener
		btSrc.setOnClickListener(onButtonClickListener);
		btDst.setOnClickListener(onButtonClickListener);
		btExe.setOnClickListener(onButtonClickListener);
		
		cbWildcard.setOnCheckedChangeListener(onCheckboxListener);
		cbPassword.setOnCheckedChangeListener(onCheckboxListener);
		cbShowPassword.setOnCheckedChangeListener(onCheckboxListener);
		
	}
	
	private void extractProcess(){
		StringBuilder sbCmd = new StringBuilder("7z ");
		sbCmd.append(cbFilesOnly.isChecked() ? "e " : "x ");	//7z e || 7z x
		//input file path
		sbCmd.append("'" + etSrc.getText().toString() + "' ");	//7z x 'aaa/bbb.zip'
		//output path
		sbCmd.append("'-o" + etDst.getText().toString() + "' ");	//7z x 'a.zip' '-o/out/'
		if(isWildcard){
			sbCmd.append("'" + etWildcard.getText().toString() + "' ");	//7z x 'a.zip' '-o/out/' '*.txt'
		}
		if(isPassword){
			sbCmd.append("'-p" + etPassword.getText().toString() + "' ");	//7z x 'a.zip' '-o/out/' '*.txt' -ppwd
		}
		//overwrite mode
		switch (spOverwriteMode.getSelectedItemPosition()) {
		case 0:
			sbCmd.append("-aoa ");	//-aoa Overwrite All existing files without prompt. 
			break;
		case 1:
			sbCmd.append("-aos ");	//-aos Skip extracting of existing files. 
			break;
		case 2:
			sbCmd.append("-aou ");	//-aou aUto rename extracting file (for example, name.txt will be renamed to name_1.txt). 
			break;
		case 3:
			sbCmd.append("-aot ");	//-aot auto rename existing file (for example, name.txt will be renamed to name_1.txt). 
			break;
		default:
			sbCmd.append("-y");	//-y
			break;
		}
		new ZipProcess(DecompressActivity.this, sbCmd.toString()).start();
	}
	
	//open a file choose activity to choose file
	private void startFileChooser(int filter, int requestCode){
		Intent intent = new Intent(DecompressActivity.this, FileChooseActivity.class);
		intent.putExtra(FileChooseActivity.STRING_FILTER, filter);
		startActivityForResult(intent, requestCode);
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
