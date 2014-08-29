/*
 * JniWrapper.h
 *
 *  Created on: 2014-8-12
 *      Author: HZY
 */

#ifndef JNIWRAPPER_H_
#define JNIWRAPPER_H_

#include "StdAfx.h"

#include <stdlib.h>
#include <string.h>

#include <jni.h>
#include <android/log.h>

#define LOG_TAG "jniLog"

#ifdef DEBUG
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG,__VA_ARGS__)
#else
#define LOGD(...)
#endif
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN,LOG_TAG,__VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,LOG_TAG,__VA_ARGS__)
#define LOGF(...) __android_log_print(ANDROID_LOG_FATAL,LOG_TAG,__VA_ARGS__)

int MY_CDECL main
(
  #ifndef _WIN32
  int numArgs, const char *args[]
  #endif
);

//char* Jstring2CStr(JNIEnv* env, jstring jstr);

int executeCommand(const char* cmd);

#endif /* JNIWRAPPER_H_ */
