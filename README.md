AndroidP7zip(P7Zip安卓)
==================
An Android compress and extract library, P7Zip port for Android

### Details
#### Get Started

* Add gradle dependencie:
``` gradle
dependencies {
    implementation 'com.hzy:libp7zip:1.6.0'
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

### Screenshot
![image](https://raw.githubusercontent.com/hzy3774/AndroidP7zip/master/misc/screenshot.gif)

#### This project is for me to learn Streams, NDK, and for fun.
 * More information: [http://p7zip.sourceforge.net/](http://p7zip.sourceforge.net/)
 * Compiled native code with gradle-experimental
 * Tested on android 4.x, 5.x and 6.0
 * Supported common archive formats:

 | Format | Creation | Filename Extensions |
 |:-------|:---------|:-----------------|
 | 7z | X | 7z |
 | BZIP2 | X | bz2 bzip2 tbz2 tbz |
 | GZIP | X | gz gzip tgz |
 | TAR | X | tar |
 | WIM | X | wim swm |
 | XZ | X | xz txz |
 | zip | X | zip zipx jar xpi odt ods docx xlsx epub |

### About Me
 * GitHub: [https://huzongyao.github.io/](https://huzongyao.github.io/)
 * ITEye博客：[http://hzy3774.iteye.com/](http://hzy3774.iteye.com/)
 * 新浪微博: [http://weibo.com/hzy3774](http://weibo.com/hzy3774)

### Contact To Me
 * QQ: [377406997](http://wpa.qq.com/msgrd?v=3&uin=377406997&site=qq&menu=yes)
 * Gmail: [hzy3774@gmail.com](mailto:hzy3774@gmail.com)
 * Foxmail: [hzy3774@qq.com](mailto:hzy3774@qq.com)
 * WeChat: hzy3774

 ![image](https://raw.githubusercontent.com/hzy3774/AndroidP7zip/master/misc/wechat.png)