package com.example.myziptest;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

class FileListAdapter extends BaseAdapter {

	private ArrayList<File> files;
	private boolean isRoot;
	private LayoutInflater mInflater;

	public FileListAdapter(Context context, ArrayList<File> files,
			boolean isRoot) {
		this.files = files;
		this.isRoot = isRoot;
		mInflater = LayoutInflater.from(context);
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
		if (position == 0 && !isRoot) {
			viewHolder.fileIcon.setImageResource(R.drawable.icon_folder);
			viewHolder.fileName.setText("..");
			viewHolder.fileInfo.setText("Parent folder");
		} else {
			File file = (File) getItem(position);
			viewHolder.fileName.setText(file.getName());
			viewHolder.fileInfo.setText(getFileInfoString(file));
			if (file.isDirectory()) { // directories
				viewHolder.fileIcon.setImageResource(R.drawable.icon_folder);
				viewHolder.isChecked.setVisibility(View.VISIBLE);
			} else { // files
				viewHolder.fileIcon.setImageResource(R.drawable.icon_7z);
			}
		}
		return convertView;
	}

	class ViewHolder {
		private ImageView fileIcon;
		private TextView fileName;
		private TextView fileInfo;
		private CheckBox isChecked;
	}
}