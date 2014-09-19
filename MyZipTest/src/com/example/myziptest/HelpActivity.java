package com.example.myziptest;

import com.example.myziptest.R.id;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;

public class HelpActivity extends Activity {

	WebView wvHelp = null;
	Button btBack, btForward;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);

		wvHelp = (WebView) findViewById(R.id.webViewHelp);
		wvHelp.loadUrl("file:///android_asset/help/index.htm");

		btBack = (Button) findViewById(R.id.button_help_back);
		btForward = (Button) findViewById(R.id.button_help_forward);

		OnClickListener listener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (v.getId()) {
				case id.button_help_back:
					if(wvHelp.canGoBack()){
						wvHelp.goBack();
					}else{
						HelpActivity.this.finish();
					}
					break;
				case id.button_help_forward:
					wvHelp.goForward();
					break;
				default:
					break;
				}
			}
		};

		btBack.setOnClickListener(listener);
		btForward.setOnClickListener(listener);
	}
}
