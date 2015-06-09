package com.hzy.p7zip;

import com.hzy.p7zip.R.id;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class CommandActivity extends Activity {

	EditText etCommand = null;
	Button btExecute, btHelp, btBack = null;
	OnClickListener listener = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_command);

		etCommand = (EditText) findViewById(R.id.editTextCommand);
		btExecute = (Button) findViewById(R.id.buttonCmdExecute);
		btBack = (Button) findViewById(R.id.buttonCmdBack);
		btHelp = (Button) findViewById(R.id.buttonCmdHelp);

		listener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (v.getId()) {
				case id.buttonCmdExecute:
					new ZipProcess(CommandActivity.this, etCommand.getText()
							.toString()).start();
					break;
				case id.buttonCmdBack:
					CommandActivity.this.finish();
					break;
				case id.buttonCmdHelp:
					startActivity(new Intent(CommandActivity.this,
							HelpActivity.class));
					break;
				default:
					break;
				}
			}
		};

		btExecute.setOnClickListener(listener);
		btBack.setOnClickListener(listener);
		btHelp.setOnClickListener(listener);
	}
}
