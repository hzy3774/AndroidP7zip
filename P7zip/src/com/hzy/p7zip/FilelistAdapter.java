package com.hzy.p7zip;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.hzy.p7zip.R.drawable;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

class FileListAdapter extends BaseAdapter {

	private ArrayList<File> files;
	private boolean isRoot;
	private boolean isMulti;
	private LayoutInflater mInflater;
	private List<Boolean> mChecked;
	
	private static final String[] fileExts = {
		"7z", "cab", "iso", "rar", "tar", "zip"};
	
	private static final int[] fileIcons = {
		drawable.icon_7z,drawable.icon_cab, drawable.icon_iso, 
		drawable.icon_rar, drawable.icon_tar, drawable.icon_zip};

	public FileListAdapter(Context context, ArrayList<File> files,
			boolean isRoot, boolean isMulti) {
		this.files = files;
		this.isRoot = isRoot;
		this.isMulti = isMulti;
		mInflater = LayoutInflater.from(context);
		
		//init the checked flag with all false
		if(isMulti){
			mChecked = new ArrayList<Boolean>();
			for (int i = 0; i < files.size(); i++) {
				mChecked.add(false);
			}
		}
	}

	@Override
	public int getCount() {
		return files.size();
	}

	@Override
	public Object getItem(int position) {
		return files.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	private String getFileInfoString(File file) {
		long fileSize = file.length();
		String ret = new SimpleDateFormat("yyyy-MM-dd HH:mm  ").format(file
				.lastModified());
		if (file.isDirectory()) {
			File[] sub = file.listFiles();
			int subCount = 0;
			if (sub != null) {
				subCount = sub.length;
			}
			ret += subCount + " items";
		} else {
			float size = 0.0f;
			if (fileSize > 1024 * 1024 * 1024) {
				size = fileSize / (1024f * 1024f * 1024f);
				ret += new DecimalFormat("#.00").format(size) + "GB";
			} else if (fileSize > 1024 * 1024) {
				size = fileSize / (1024f * 1024f);
				ret += new DecimalFormat("#.00").format(size) + "MB";
			} else if (fileSize >= 1024) {
				size = fileSize / 1024;
				ret += new DecimalFormat("#.00").format(size) + "KB";
			} else {
				ret += fileSize + "B";
			}
		}
		return ret;
	}
	
	private int getFileIconId(File file){
		int id = R.drawable.icon_unknown;
		String fileName = file.getName();
		String fileExt = fileName.substring(fileName.lastIndexOf(".")+1);
		for(int i = 0; i < fileExts.length; i++){
			if (fileExt.endsWith(fileExts[i])) {
				id = fileIcons[i];
				break;
			}
		}
		return id;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.file_list_item, null);
			convertView.setTag(viewHolder);
			viewHolder.fileIcon = (ImageView) convertView
					.findViewById(R.id.fcFileIcon);
			viewHolder.fileName = (TextView) convertView
					.findViewById(R.id.fcFileName);
			viewHolder.fileInfo = (TextView) convertView
					.findViewById(R.id.fcFileInfo);
			viewHolder.isChecked = (CheckBox) convertView
					.findViewById(R.id.fcChooseFlag);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		//return parent
		if (position == 0 && !isRoot) {
			viewHolder.fileIcon.setImageResource(R.drawable.icon_folder);
			viewHolder.fileName.setText("..");
			viewHolder.fileInfo.setText("Parent folder");
			viewHolder.isChecked.setVisibility(View.GONE);
		} else {
			File file = (File) getItem(position);
			viewHolder.fileName.setText(file.getName());
			viewHolder.fileInfo.setText(getFileInfoString(file));
			if (file.isDirectory()) { // directories
				viewHolder.fileIcon.setImageResource(R.drawable.icon_folder);
				viewHolder.isChecked.setVisibility(isMulti?View.VISIBLE:View.GONE);
			} else { // files
				viewHolder.fileIcon.setImageResource(getFileIconId(file));
				viewHolder.isChecked.setVisibility(isMulti?View.VISIBLE:View.GONE);
			}
		}
		//to set listen the checkbox
		if (isMulti){
			final int p = position;
			viewHolder.isChecked.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					Log.d("file chooser", "checkbox " + p + isChecked);
					mChecked.set(p, isChecked);
				}
			});
		}
		return convertView;
	}
	
	public List<Boolean> getCheckedIndexList(){
		return mChecked;
	}

	class ViewHolder {
		private ImageView fileIcon;
		private TextView fileName;
		private TextView fileInfo;
		private CheckBox isChecked;
	}
}