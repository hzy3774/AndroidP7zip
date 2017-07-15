package com.hzy.p7zip.app.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hzy.p7zip.app.R;
import com.hzy.p7zip.app.bean.FileInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by huzongyao on 17-7-13.
 */

public class FileItemAdapter extends RecyclerView.Adapter<FileItemAdapter.ViewHolder> {

    private View.OnClickListener mItemClickListener;
    private Activity mActivity;
    private List<FileInfo> mFileInfoList;

    public FileItemAdapter(Activity activity, View.OnClickListener listener) {
        mActivity = activity;
        mItemClickListener = listener;
        mFileInfoList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(mActivity).inflate(R.layout.storage_list_item, parent, false);
        rootView.setOnClickListener(mItemClickListener);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FileInfo item = mFileInfoList.get(position);
        int iconId = R.drawable.icon_unknown;
        switch (item.getFileType()) {
            case folderFull:
                iconId = R.drawable.icon_folder_full;
                break;
            case folderEmpty:
                iconId = R.drawable.icon_folder_empty;
                break;
        }
        holder.itemView.setTag(item);
        holder.fileName.setText(item.getFileName());
        holder.icon.setImageResource(iconId);
    }

    @Override
    public int getItemCount() {
        return mFileInfoList.size();
    }

    public void setDataList(List<FileInfo> dataList) {
        this.mFileInfoList = dataList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.file_item_icon)
        ImageView icon;

        @Bind(R.id.file_item_name)
        TextView fileName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
