package com.hzy.p7zip.app.fragment;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.blankj.utilcode.util.SnackbarUtils;
import com.hzy.libp7zip.P7ZipApi;
import com.hzy.p7zip.app.R;
import com.hzy.p7zip.app.adapter.FileItemAdapter;
import com.hzy.p7zip.app.adapter.PathItemAdapter;
import com.hzy.p7zip.app.bean.FileInfo;
import com.hzy.p7zip.app.command.Command;
import com.hzy.p7zip.app.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static android.support.v7.widget.LinearLayoutManager.HORIZONTAL;
import static com.hzy.p7zip.app.command.ExitCode.EXIT_CMD_ERROR;
import static com.hzy.p7zip.app.command.ExitCode.EXIT_FATAL;
import static com.hzy.p7zip.app.command.ExitCode.EXIT_MEMORY_ERROR;
import static com.hzy.p7zip.app.command.ExitCode.EXIT_NOT_SUPPORT;
import static com.hzy.p7zip.app.command.ExitCode.EXIT_OK;
import static com.hzy.p7zip.app.command.ExitCode.EXIT_WARNING;

/**
 * Created by huzongyao on 17-7-10.
 */

public class StorageFragment extends Fragment
        implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener,
        View.OnLongClickListener {

    private static final int REQUEST_READ_EXTERNAL_STORAGE = 1;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCurFileInfoList = new ArrayList<>();
        mCurPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            loadPathInfo(mCurPath);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            requestPermissions(new String[]
                    {Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_READ_EXTERNAL_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_EXTERNAL_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadPathInfo(mCurPath);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_storage, null);
        ButterKnife.bind(this, rootView);

        mPathListView.setLayoutManager(new LinearLayoutManager(getContext(), HORIZONTAL, false));
        mPathListView.setAdapter(mFilePathAdapter = new PathItemAdapter(getActivity(), this));

        mStorageListView.setLayoutManager(new LinearLayoutManager(getContext()));
        mStorageListView.setAdapter(mFileItemAdapter = new FileItemAdapter(getActivity(), this, this));
        mSwipeRefresh.setOnRefreshListener(this);
        return rootView;
    }

    private void loadPathInfo(final String path) {
        mCurFileInfoList.clear();
        Observable.create(new ObservableOnSubscribe<List<FileInfo>>() {
            @Override
            public void subscribe(ObservableEmitter<List<FileInfo>> e) throws Exception {
                mCurFileInfoList = FileUtils.getInfoListFromPath(path);
                e.onNext(mCurFileInfoList);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<FileInfo>>() {
                    @Override
                    public void accept(List<FileInfo> fileInfos) throws Exception {
                        mFileItemAdapter.setDataList(mCurFileInfoList);
                        mSwipeRefresh.setRefreshing(false);
                        mCurPath = path;
                        mFilePathAdapter.setPathView(mCurPath);
                        mStorageListView.smoothScrollToPosition(0);
                        mPathListView.scrollToPosition(mFilePathAdapter.getItemCount() - 1);
                    }
                });
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
            new MaterialDialog.Builder(getActivity())
                    .title(R.string.select_operation)
                    .items(R.array.popup_menu_items)
                    .itemsCallback(new MaterialDialog.ListCallback() {
                        @Override
                        public void onSelection(MaterialDialog dialog, View itemView,
                                                int position, CharSequence text) {
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
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> ev) throws Exception {
                String path = info.getFilePath();
                String result;
                File file = new File(path);
                try {
                    removeFile(file);
                    result = "removed: " + info.getFileName();
                } catch (Exception e) {
                    result = e.getMessage();
                }
                ev.onNext(result);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String result) throws Exception {
                        dismissProgressDialog();
                        onRefresh();
                        SnackbarUtils.with(mSwipeRefresh).setMessage(result).show();
                    }
                });
    }

    private void removeFile(File file) throws IOException {
        if (file != null && file.exists()) {
            if (file.isDirectory()) {
                for (File sub : file.listFiles()) {
                    removeFile(sub);
                }
            }
            file.delete();
        }
    }

    private void runCommand(final String cmd) {
        showProgressDialog();
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                int ret = P7ZipApi.executeCommand(cmd);
                e.onNext(ret);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        dismissProgressDialog();
                        showResult(integer);
                        onRefresh();
                    }
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
            case EXIT_OK:
                retMsgId = R.string.msg_ret_success;
                break;
            case EXIT_WARNING:
                retMsgId = R.string.msg_ret_warning;
                break;
            case EXIT_FATAL:
                retMsgId = R.string.msg_ret_fault;
                break;
            case EXIT_CMD_ERROR:
                retMsgId = R.string.msg_ret_command;
                break;
            case EXIT_MEMORY_ERROR:
                retMsgId = R.string.msg_ret_memmory;
                break;
            case EXIT_NOT_SUPPORT:
                retMsgId = R.string.msg_ret_user_stop;
                break;
            default:
                break;
        }
        SnackbarUtils.with(mSwipeRefresh).setMessage(getString(retMsgId)).show();
    }

}
