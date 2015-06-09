/*
 * com_hu_p7zip_ZipUtils.cpp
 *
 * interface of jni
 *
 *  Created on: 2014-8-12
 *      Author: HZY
 */


#include "JniWrapper.h"


#ifdef __cplusplus
extern "C" {
#endif

#define NDK_FUNC(f) Java_com_hu_p7zip_ZipUtils_##f

/*
 * Class:     com_hu_p7zip_ZipUtils
 * Method:    executeCommand
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL NDK_FUNC(executeCommand)
(JNIEnv *env, jclass thiz, jstring command)
{
	const char* ccommand = (const char*)env->GetStringUTFChars(command, NULL);
	LOGI("start[%s]", ccommand);
	jint ret = executeCommand((const char*)ccommand);
	LOGI("end[%s]", ccommand);
	env->ReleaseStringUTFChars(command, ccommand);
	return ret;
}

#ifdef __cplusplus
}
#endif

