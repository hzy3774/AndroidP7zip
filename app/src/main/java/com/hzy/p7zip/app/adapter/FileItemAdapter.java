package com.hzy.p7zip.app.adapter;

import android.app.Activity;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hzy.p7zip.app.R;
import com.hzy.p7zip.app.bean.FileInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by huzongyao on 17-7-13.
 */

public class FileItemAdapter extends RecyclerView.Adapter<FileItemAdapter.ViewHolder> {

    private View.OnLongClickListener mLongClickListener;
    private View.OnClickListener mItemClickListener;
    private Activity mActivity;
    private List<FileInfo> mFileInfoList;

    public FileItemAdapter(Activity activity, View.OnClickListener clickListener, View.OnLongClickListener longClickListener) {
        mActivity = activity;
        mItemClickListener = clickListener;
        mLongClickListener = longClickListener;
        mFileInfoList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(mActivity).inflate(R.layout.storage_list_item, parent, false);
        rootView.setOnClickListener(mItemClickListener);
        rootView.setOnLongClickListener(mLongClickListener);
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
            case filearchive:
                iconId = R.drawable.icon_file_archive;
                break;
        }
        if (item.isFolder()) {
            holder.subCount.setText(mActivity.getString(R.string.items, item.getSubCount()));
        } else {
            holder.subCount.setText(Formatter.formatFileSize(mActivity, item.getFileLength()));
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

        @BindView(R.id.file_item_icon)
        ImageView icon;

        @BindView(R.id.file_item_name)
        TextView fileName;

        @BindView(R.id.file_sub_count)
        TextView subCount;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
