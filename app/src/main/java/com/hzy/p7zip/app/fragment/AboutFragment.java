package com.hzy.p7zip.app.fragment;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hzy.libp7zip.P7ZipApi;
import com.hzy.p7zip.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by huzongyao on 17-7-10.
 */

public class AboutFragment extends Fragment {

    @BindView(R.id.fragment_about_content)
    TextView mVersionInfo;
    @BindView(R.id.id_info_blog)
    TextView mInfoBlog;
    @BindView(R.id.id_info_github)
    TextView mInfoGitHub;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_about, null);
        ButterKnife.bind(this, rootView);
        mVersionInfo.setText(P7ZipApi.get7zVersionInfo());
        mInfoGitHub.setMovementMethod(LinkMovementMethod.getInstance());
        mInfoBlog.setMovementMethod(LinkMovementMethod.getInstance());
        return rootView;
    }
}
