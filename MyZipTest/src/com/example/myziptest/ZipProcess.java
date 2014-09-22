package com.example.myziptest;

import com.hu.p7zip.ZipUtils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

public class ZipProcess {
	
	
	public static final int TYPE_NULL = 0;
	public static final int TYPE_COMMAND = 1;
	
	Context context = null;
	Thread thread = null;
	ProgressDialog dialog = null;
	Handler handler = null;
	int type = 0;
	String[] args = null;
	
	public ZipProcess(Context context, int type, String[] args) {
		// TODO Auto-generated method stub
		this.context = context;
		this.type = type;
		this.args = args;
		
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
					allProcess();
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
	
	private void allProcess(){
		switch (this.type) {
		case TYPE_COMMAND:
			ZipUtils.command(args[0]);
			break;

		default:
			break;
		}
	}
	
	void start(){
		dialog.show();
		thread.start();
	}
	
}
