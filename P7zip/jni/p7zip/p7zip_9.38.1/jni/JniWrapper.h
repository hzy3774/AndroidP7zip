/*
 * JniWrapper.h
 *
 *  Created on: 2014-8-12
 *      Author: HZY
 */

#ifndef JNIWRAPPER_H_
#define JNIWRAPPER_H_

#include "7zTypes.h"

EXTERN_C_BEGIN

#include "StdAfx.h"

#include <stdlib.h>
#include <string.h>

#include <jni.h>
#include <android/log.h>

#define LOG_ENABLE

#ifdef LOG_ENABLE
#define LOG_TAG "p7zip_jni"
#define LOGI(...) do { __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__); } while(0)
#define LOGD(...) do { __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__); } while(0)
#define LOGE(...) do { __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__); } while(0)
#else
#define LOGI(...) do { } while(0)
#define LOGD(...) do { } while(0)
#define LOGE(...) do { } while(0)
#endif

int executeCommand(const char* cmd);

EXTERN_C_END

#endif /* JNIWRAPPER_H_ */
