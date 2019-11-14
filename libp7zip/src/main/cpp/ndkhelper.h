#ifndef ANDROIDUN7ZIP_NDKHELPER_H
#define ANDROIDUN7ZIP_NDKHELPER_H

#ifdef __cplusplus
extern "C" {
#endif

#include <jni.h>
#include <android/log.h>
#include <7zip/MyVersion.h>

#ifdef NATIVE_LOG
#define LOG_TAG "NATIVE.LOG"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG,__VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN,LOG_TAG,__VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,LOG_TAG,__VA_ARGS__)
#define LOGF(...) __android_log_print(ANDROID_LOG_FATAL,LOG_TAG,__VA_ARGS__)
#else
#define LOGD(...) do{}while(0)
#define LOGI(...) do{}while(0)
#define LOGW(...) do{}while(0)
#define LOGE(...) do{}while(0)
#define LOGF(...) do{}while(0)
#endif

#define JNI_FUNC(X) Java_com_hzy_libp7zip_P7ZipApi_##X

/**
 * To Get lib p7zip version info
 * @param env
 * @param type
 * @return
 */
JNIEXPORT jstring JNICALL
JNI_FUNC(get7zVersionInfo)(JNIEnv *env, jclass type);

/**
 * To execute some command with p7zip
 * @param env
 * @param type
 * @param command_
 * @return
 */
JNIEXPORT jint JNICALL
JNI_FUNC(executeCommand)(JNIEnv *env, jclass type, jstring command_);

#ifdef __cplusplus
}
#endif

#endif
