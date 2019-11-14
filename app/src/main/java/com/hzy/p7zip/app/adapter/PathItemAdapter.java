package com.hzy.p7zip.app.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hzy.p7zip.app.R;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by huzongyao on 17-7-15.
 */

public class PathItemAdapter extends RecyclerView.Adapter<PathItemAdapter.ViewHolder> {

    private String[] mPathStringList;
    private View.OnClickListener mItemClickListener;
    private Activity mActivity;
    private String mCurPath;

    public PathItemAdapter(Activity activity, View.OnClickListener listener) {
        mActivity = activity;
        mItemClickListener = listener;
        mPathStringList = new String[0];
    }

    public void setPathView(String path) {
        mCurPath = path;
        mPathStringList = mCurPath.split(File.separator);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(mActivity).inflate(R.layout.path_list_item, parent, false);
        rootView.setOnClickListener(mItemClickListener);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String item = mPathStringList[position];
        String curPath = "";
        for (int i = 0; i < position + 1; i++) {
            curPath += mPathStringList[i] + File.separator;
        }
        holder.itemView.setTag(curPath);
        holder.pathText.setText(item);
    }

    @Override
    public int getItemCount() {
        return mPathStringList.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.path_list_text)
        TextView pathText;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
