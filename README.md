AndroidP7zip(P7Zip安卓)
==================
An Android compress and extract library, P7Zip port for Android

### Details
#### Get Started
* Download to experience[download](https://github.com/hzy3774/AndroidP7zip/releases/latest)

* Add gradle dependencie:
``` gradle
dependencies {
    implementation 'com.hzy:libp7zip:1.7.0'
}
```
* Or just download the aar [here](https://jcenter.bintray.com/com/hzy/libp7zip/)

* If you want to add some abi filters
``` gradle
android {
    ...
    defaultConfig {
        ...
        ndk {
            abiFilters 'armeabi-v7a', 'arm64-v8a', 'x86'
         }
    }
}
```

* Simple interface
``` java
P7ZipApi.executeCommand(String command);
```

* Commands:
``` shell
7z a archive1.zip subdir\
7z x archive.zip -oc:\soft *.cpp -r
```

### Screenshot
![image](https://raw.githubusercontent.com/hzy3774/AndroidP7zip/master/misc/screenshot.gif)

#### This project is for me to learn Streams, NDK, and for fun.
 * More information: [http://p7zip.sourceforge.net/](http://p7zip.sourceforge.net/)
 * Compiled native code with gradle-experimental
 * Tested on android 4.x, 5.x and 6.0
 * Supported common archive formats:

 | Format | Creation | Filename Extensions |
 |:-------|:---------|:-----------------|
 | 7z | √ | 7z |
 | BZIP2 | √ | bz2 bzip2 tbz2 tbz |
 | GZIP | √ | gz gzip tgz |
 | TAR | √ | tar |
 | WIM | √ | wim swm |
 | XZ | √ | xz txz |
 | zip | √ | zip zipx jar xpi odt ods docx xlsx epub |

### About Me
 * GitHub: [https://huzongyao.github.io/](https://huzongyao.github.io/)
 * ITEye博客：[https://hzy3774.iteye.com/](https://hzy3774.iteye.com/)
 * 新浪微博: [https://weibo.com/hzy3774](https://weibo.com/hzy3774)

### Contact To Me
 * QQ: [377406997](https://wpa.qq.com/msgrd?v=3&uin=377406997&site=qq&menu=yes)
 * Gmail: [hzy3774@gmail.com](mailto:hzy3774@gmail.com)
 * Foxmail: [hzy3774@qq.com](mailto:hzy3774@qq.com)
 * WeChat: hzy3774

 ![image](https://raw.githubusercontent.com/hzy3774/AndroidP7zip/master/misc/wechat.png)

### Others
 * 想捐助我喝杯热水(¥0.01起捐)</br>
 ![donate](https://github.com/huzongyao/JChineseChess/blob/master/misc/donate.png?raw=true)