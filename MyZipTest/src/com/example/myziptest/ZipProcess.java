package com.example.myziptest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

public class ZipProcess {
	
	Context context = null;
	Thread thread = null;
	ProgressDialog dialog = null;
	Handler handler = null;
	
	public ZipProcess(Context context, int type, String[] args) {
		// TODO Auto-generated method stub
		this.context = context;
		dialog = new ProgressDialog(context);
		dialog.setTitle(R.string.progress_title);
		dialog.setMessage(context.getText(R.string.progress_message));
		dialog.setCancelable(false);
		
		handler = new Handler(new Handler.Callback() {
			@Override
			public boolean handleMessage(Message msg) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				return false;
			}
		});
		
		thread = new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				handler.sendEmptyMessage(0);
				super.run();
			}
		};
	}
	
	void start(){
		dialog.show();
		thread.start();
	}
	
}
