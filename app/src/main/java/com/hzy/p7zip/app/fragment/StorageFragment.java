package com.hzy.p7zip.app.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.SnackbarUtils;
import com.hzy.libp7zip.ExitCode;
import com.hzy.libp7zip.P7ZipApi;
import com.hzy.p7zip.app.R;
import com.hzy.p7zip.app.adapter.FileItemAdapter;
import com.hzy.p7zip.app.adapter.PathItemAdapter;
import com.hzy.p7zip.app.bean.FileInfo;
import com.hzy.p7zip.app.command.Command;
import com.hzy.p7zip.app.utils.P7zipUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by huzongyao on 17-7-10.
 */

public class StorageFragment extends Fragment
        implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener,
        View.OnLongClickListener {

    @BindView(R.id.fragment_storage_path)
    RecyclerView mPathListView;
    @BindView(R.id.fragment_storage_list)
    RecyclerView mStorageListView;
    @BindView(R.id.fragment_storage_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    private List<FileInfo> mCurFileInfoList;
    private String mCurPath;
    private FileItemAdapter mFileItemAdapter;
    private PathItemAdapter mFilePathAdapter;
    private ProgressDialog dialog;
    private ExecutorService mExecutor;

    public StorageFragment() {
        mCurFileInfoList = new ArrayList<>();
        mExecutor = Executors.newSingleThreadExecutor();
        mCurPath = Environment.getExternalStorageDirectory().getPath();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_storage, null);
        ButterKnife.bind(this, rootView);
        mPathListView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        mPathListView.setAdapter(mFilePathAdapter = new PathItemAdapter(getActivity(), this));
        mStorageListView.setLayoutManager(new LinearLayoutManager(getContext()));
        mStorageListView.setAdapter(mFileItemAdapter = new FileItemAdapter(getActivity(), this, this));
        mSwipeRefresh.setOnRefreshListener(this);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPathListView.postDelayed(() -> loadPathInfo(mCurPath), 100);
    }

    private void loadPathInfo(final String path) {
        PermissionUtils.permission(PermissionConstants.STORAGE)
                .callback(new PermissionUtils.SimpleCallback() {
                    @Override
                    public void onGranted() {
                        mCurFileInfoList.clear();
                        mExecutor.submit(() -> {
                            List<FileInfo> fileList = P7zipUtils.getInfoListFromPath(path);
                            mPathListView.post(() -> {
                                mCurFileInfoList.clear();
                                mCurFileInfoList.addAll(fileList);
                                mFileItemAdapter.setDataList(mCurFileInfoList);
                                mSwipeRefresh.setRefreshing(false);
                                mCurPath = path;
                                mFilePathAdapter.setPathView(mCurPath);
                                mStorageListView.smoothScrollToPosition(0);
                                mPathListView.scrollToPosition(mFilePathAdapter.getItemCount() - 1);
                            });
                        });
                    }

                    @Override
                    public void onDenied() {

                    }
                }).request();
    }

    @Override
    public void onRefresh() {
        loadPathInfo(mCurPath);
    }

    @Override
    public void onClick(View v) {
        Object tag = v.getTag();
        if (tag instanceof String) {
            loadPathInfo((String) tag);
        } else if (tag instanceof FileInfo) {
            FileInfo info = (FileInfo) tag;
            if (info.isFolder()) {
                loadPathInfo(info.getFilePath());
            } else {

            }
        }
    }

    @Override
    public boolean onLongClick(View v) {
        Object tag = v.getTag();
        if (tag instanceof FileInfo) {
            final FileInfo info = (FileInfo) tag;
            new MaterialDialog.Builder(ActivityUtils.getTopActivity())
                    .title(R.string.select_operation)
                    .items(R.array.popup_menu_items)
                    .itemsCallback((dialog, itemView, position, text) -> {
                        switch (position) {
                            case 0:
                                onCompressFile(info);
                                break;
                            case 1:
                                onExtractFile(info);
                                break;
                            case 2:
                                onRemoveFile(info);
                                break;
                        }
                    })
                    .show();
        }
        return true;
    }

    private void onCompressFile(FileInfo info) {
        String cmd = Command.getCompressCmd(info.getFilePath(),
                info.getFilePath() + ".7z", "7z");
        runCommand(cmd);
    }

    private void onExtractFile(final FileInfo info) {
        String cmd = Command.getExtractCmd(info.getFilePath(),
                info.getFilePath() + "-ext");
        runCommand(cmd);
    }

    private void onRemoveFile(final FileInfo info) {
        showProgressDialog();
        mExecutor.submit(() -> {
            String path = info.getFilePath();
            FileUtils.delete(path);
            mPathListView.post(() -> {
                dismissProgressDialog();
                onRefresh();
                SnackbarUtils.with(mSwipeRefresh).setMessage("removed: " + info.getFileName()).show();
            });
        });
    }

    private void runCommand(final String cmd) {
        showProgressDialog();
        mExecutor.submit(() -> {
            int result = P7ZipApi.executeCommand(cmd);
            mPathListView.post(() -> {
                dismissProgressDialog();
                showResult(result);
                onRefresh();
            });
        });
    }

    private void showProgressDialog() {
        if (dialog == null) {
            dialog = new ProgressDialog(getActivity());
            dialog.setTitle(R.string.progress_title);
            dialog.setMessage(getText(R.string.progress_message));
            dialog.setCancelable(false);
        }
        dialog.show();
    }

    private void dismissProgressDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    private void showResult(int result) {
        int retMsgId = R.string.msg_ret_success;
        switch (result) {
            case ExitCode.EXIT_OK:
                retMsgId = R.string.msg_ret_success;
                break;
            case ExitCode.EXIT_WARNING:
                retMsgId = R.string.msg_ret_warning;
                break;
            case ExitCode.EXIT_FATAL:
                retMsgId = R.string.msg_ret_fault;
                break;
            case ExitCode.EXIT_CMD_ERROR:
                retMsgId = R.string.msg_ret_command;
                break;
            case ExitCode.EXIT_MEMORY_ERROR:
                retMsgId = R.string.msg_ret_memmory;
                break;
            case ExitCode.EXIT_NOT_SUPPORT:
                retMsgId = R.string.msg_ret_user_stop;
                break;
            default:
                break;
        }
        SnackbarUtils.with(mSwipeRefresh).setMessage(getString(retMsgId)).show();
    }

}
