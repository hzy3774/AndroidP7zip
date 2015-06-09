package com.hzy.p7zip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.hzy.p7zip.R.id;

public class MainActivity extends Activity {
	
	Button btnCompress, btnDecompress, btnCommand, btnHelp, btnExit;
	OnClickListener listener = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnCompress = (Button) findViewById(R.id.button_compress);
		btnDecompress = (Button) findViewById(R.id.button_decompress);
		btnCommand = (Button) findViewById(R.id.button_command);
		btnHelp = (Button) findViewById(R.id.button_help);
		btnExit= (Button) findViewById(R.id.button_exit);
		
		this.listener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				Class<?> newActivity = null;
				switch (v.getId()) {
				case id.button_compress:
					newActivity = CompressActivity.class;
					break;
				case id.button_decompress:
					newActivity = DecompressActivity.class;
					break;
				case id.button_command:
					newActivity = CommandActivity.class;
					break;
				case id.button_help:
					newActivity = HelpActivity.class;
					break;
				case id.button_exit:
					MainActivity.this.finish();
					return;
				default:
					return;
				}
				startActivity(new Intent(MainActivity.this, newActivity));
			}
		};
		
		btnCompress.setOnClickListener(listener);
		btnDecompress.setOnClickListener(listener);
		btnCommand.setOnClickListener(listener);
		btnHelp.setOnClickListener(listener);
		btnExit.setOnClickListener(listener);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
