package com.hzy.p7zip.app;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

/**
 * Created by huzongyao on 17-7-7.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }
}
