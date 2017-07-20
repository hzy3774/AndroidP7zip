package com.hzy.p7zip.app.fragment;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hzy.p7zip.app.R;
import com.hzy.p7zip.app.adapter.FileItemAdapter;
import com.hzy.p7zip.app.adapter.PathItemAdapter;
import com.hzy.p7zip.app.bean.FileInfo;
import com.hzy.p7zip.app.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static android.support.v7.widget.LinearLayoutManager.HORIZONTAL;

/**
 * Created by huzongyao on 17-7-10.
 */

public class StorageFragment extends Fragment
        implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, View.OnLongClickListener {

    @Bind(R.id.fragment_storage_path)
    RecyclerView mPathListView;

    @Bind(R.id.fragment_storage_list)
    RecyclerView mStorageListView;

    @Bind(R.id.fragment_storage_refresh)
    SwipeRefreshLayout mSwipRefresh;

    private List<FileInfo> mCurFileInfoList;
    private String mCurPath;
    private FileItemAdapter mFileItemAdapter;
    private PathItemAdapter mFilePathAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCurFileInfoList = new ArrayList<>();
        mCurPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        loadPathInfo(mCurPath);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_storage, null);
        ButterKnife.bind(this, rootView);

        mPathListView.setLayoutManager(new LinearLayoutManager(getContext(), HORIZONTAL, false));
        mPathListView.setAdapter(mFilePathAdapter = new PathItemAdapter(getActivity(), this));

        mStorageListView.setLayoutManager(new LinearLayoutManager(getContext()));
        mStorageListView.setAdapter(mFileItemAdapter = new FileItemAdapter(getActivity(), this, this));
        mSwipRefresh.setOnRefreshListener(this);
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
                        mSwipRefresh.setRefreshing(false);
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
        if (v.getTag() instanceof String) {
            loadPathInfo((String) v.getTag());
        } else {
            FileInfo info = (FileInfo) v.getTag();
            if (info.isFolder()) {
                loadPathInfo(info.getFilePath());
            } else {

            }
        }
    }

    @Override
    public boolean onLongClick(View v) {
        return true;
    }
}
